package com.hps.july.mail.service.impl.dao;

import com.hps.july.mail.MailException;
import com.hps.july.mail.object.MailTransportObject;
import org.apache.log4j.Logger;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * TODO use spring jms support
 * @author Dimitry Krivenko
 * 15.03.2006
 */
public class MailDAOMessageBean implements MailDAO {

    protected Logger logger = Logger.getLogger(this.getClass());

    protected String queueJndiName = "java:comp/env/jms/mailQueue";
    protected String connectionFactoryJndiName = "java:comp/env/jms/mailQCF";
//    protected String queueJndiName = "cell/persistent/DefaultMailQueue";
//    protected String connectionFactoryJndiName = "cell/persistent/DefaultMailQCF";
//    protected String queueJndiName = "/nri-databaseNetwork/nodes/nri-database/servers/server2/jms/DefaultMailQueue";
//    protected String connectionFactoryJndiName = "/nri-databaseNetwork/nodes/nri-database/servers/server2/jms/DefaultMailQCF";


    public String getConnectionFactoryJndiName() {
        return connectionFactoryJndiName;
    }
    public void setConnectionFactoryJndiName(String connectionFactoryJndiName) {
        this.connectionFactoryJndiName = connectionFactoryJndiName;
    }
    public String getQueueJndiName() {
        return queueJndiName;
    }
    public void setQueueJndiName(String queueJndiName) {
        this.queueJndiName = queueJndiName;
    }

    public void sendMail(MailTransportObject mailTransportObject) throws MailException {

        if(mailTransportObject.getAuthInfo() == null) throw new MailException("Identification must be not null, object 'AuthInfo' is null");
        if(mailTransportObject.getAuthInfo().getPersonId() == null) throw new MailException("Identification must be not null, person is null");

        InitialContext context = null;
        Connection sqlConnection = null;
        PreparedStatement insertLog = null;
        PreparedStatement getId = null;
        ResultSet rs = null;
        DataSource dataSource = null;
        try {
            context = new InitialContext();
            dataSource = (DataSource)context.lookup("java:comp/env/jdbc/mail");
            if(dataSource != null) {
				sqlConnection = dataSource.getConnection();
                insertLog = sqlConnection.prepareStatement("INSERT INTO maillog (servicename, man, logdate, mailto, mailfrom, subject, msgtype) VALUES (?, ?, ?, ?, ?, ?, ?)");
                getId = sqlConnection.prepareStatement("select dbinfo('sqlca.sqlerrd1') as last_inserted from systables where tabname = 'maillog'");
                insertLog.setString(1, mailTransportObject.getSenderService());
                insertLog.setInt(2, mailTransportObject.getAuthInfo().getPersonId().intValue());
                insertLog.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                StringBuffer mailTo = new StringBuffer();
                mailTo.append("Recipients: ").append((mailTransportObject.getMailMessage().getRecipients() != null)?mailTransportObject.getMailMessage().getRecipients().toString():"[]");
                mailTo.append("CC Recipients: ").append((mailTransportObject.getMailMessage().getRecipientsCC() != null)?mailTransportObject.getMailMessage().getRecipientsCC().toString():"[]");
                mailTo.append("BCC Recipients: ").append((mailTransportObject.getMailMessage().getRecipientsBCC() != null)?mailTransportObject.getMailMessage().getRecipientsBCC().toString():"[]");
                insertLog.setString(4, mailTo.toString());
                insertLog.setString(5, mailTransportObject.getMailMessage().getFrom());
                insertLog.setString(6, mailTransportObject.getMailMessage().getSubject());
				insertLog.setString(7, "S");
                insertLog.executeUpdate();

                //getId.setString(1, "mailLog");
                rs = getId.executeQuery();
                Integer id = null;
                if(rs.next()) {
                    int idInt = rs.getInt("last_inserted");
                    if(!rs.wasNull()) id = new Integer(idInt);
                }
                if(id == null) throw new MailException("Insert info was unsucceeded");

                mailTransportObject.getMailMessage().setId(id);
            }
        } catch (NamingException e) {
            logger.error("Cannot find datasource [java:comp/env/jdbc/mail], cause : " + e.getMessage(), e);
            //throw new MailException("Cannot insert mail log, cause : " + e.getMessage(), e);
        } catch (SQLException e) {
            logger.error("Cannot insert mail log, cause : " + e.getMessage() + " sql error code ["+e.getErrorCode()+"]", e);
            //throw new MailException("Cannot insert mail log, cause : " + e.getMessage() + " sql error code ["+e.getErrorCode()+"]", e);
        } catch (Exception e) {
            logger.error("Unexcepted error while insert mail log, cause : " + e.getMessage(), e);
            //throw new MailException("Unexcepted error while insert mail log, cause : " + e.getMessage(), e);
        } finally {
            if(rs != null) try { rs.close(); } catch(Exception e) {}
            if(insertLog != null) try { insertLog.close(); } catch(Exception e) {}
            if(getId != null) try { getId.close(); } catch(Exception e) {}
            if(sqlConnection != null) try { sqlConnection.close(); } catch(Exception e) {}
        }

        QueueConnection connection = null;
        try {
            if(context == null) context = new InitialContext();
/*
            Object obj = context.lookup("cell/persistent/jms/Test");
            logger.debug("XXXXX-> obj ["+obj+"]");
            logger.debug("XXXXX-> obj ["+obj.getClass().getName()+"]");
*/
			//System.out.println("--->queue ["+queueJndiName+"]");
			//logger.debug("queue ["+queueJndiName+"]");
            Queue mailQueue = (Queue)context.lookup(queueJndiName);
            //logger.debug("queue class ["+mailQueue.getClass().getName()+"]");
			//System.out.println("--->factory queue ["+connectionFactoryJndiName+"]");
			//logger.debug("factory queue ["+connectionFactoryJndiName+"]");
            QueueConnectionFactory factory = (QueueConnectionFactory)context.lookup(connectionFactoryJndiName);
            //logger.debug("factory queue class ["+factory.getClass().getName()+"]");
            //logger.debug("queue name ["+(mailQueue.getQueueName()) +"]");

            connection = factory.createQueueConnection();
            QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            QueueSender sender = session.createSender(mailQueue);

            ObjectMessage message = session.createObjectMessage();
			message.setJMSType(MailTransportObject.JMS_MESSAGE_TYPE);
            message.setObject(mailTransportObject);
            
			//BytesMessage message = session.createBytesMessage();
			//message.setJMSType(MailTransportObject.JMS_MESSAGE_TYPE);
			//ByteArrayOutputStream bos = new ByteArrayOutputStream();
			//ObjectOutputStream dos = new ObjectOutputStream(bos);
			//dos.writeObject(mailTransportObject);
			//message.writeBytes(bos.toByteArray());

            sender.send(message);
        } catch (JMSException e) {
            logger.error("Mail not send: cause problem with jms server : " + e.getMessage(), e);
            updateLogInfo(dataSource, mailTransportObject, e);
            throw new MailException("Mail not send: cause problem with jms server : " + e.toString());
        } catch (NamingException e) {
            logger.error("Mail not send. Not lookup jms objects: Queue:'"+queueJndiName+"' or ConnectionFactory:'"+connectionFactoryJndiName+"'. Message:" + e.getMessage(), e);
            updateLogInfo(dataSource, mailTransportObject, e);
            throw new MailException("Mail not send. Not lookup jms objects: Queue:'"+queueJndiName+"' or ConnectionFactory:'"+connectionFactoryJndiName+"'. Message:" + e.getMessage());
        } catch (Exception e) {
            logger.error("Mail not send. Not lookup jms objects: Queue:'"+queueJndiName+"' or ConnectionFactory:'"+connectionFactoryJndiName+"'. Message:" + e.getMessage(), e);
            updateLogInfo(dataSource, mailTransportObject, e);
            throw new MailException("Mail not send. Not lookup jms objects: Queue:'"+queueJndiName+"' or ConnectionFactory:'"+connectionFactoryJndiName+"'. Message:" + e.getMessage());
        } finally {
             if(connection != null) try { connection.close(); } catch (Exception me) {}
        }
    }

    protected void updateLogInfo(DataSource dataSource, MailTransportObject mailTransportObject, Throwable t) {
        Connection connection = null;
        PreparedStatement updateLog = null;
        try {
            if(dataSource != null) {
                connection = dataSource.getConnection();
                updateLog = connection.prepareStatement("UPDATE maillog SET msgtype = 'E', modified = CURRENT, errortext = ? WHERE id = ?");
				updateLog.setString(1, "Mail not sent: " + t.toString());
                updateLog.setInt(2, mailTransportObject.getMailMessage().getId().intValue());
                updateLog.executeUpdate();
            } else {
                logger.error("Cannot update mail log, cause DataSource is null ");
            }
        } catch (SQLException e) {
            logger.error("Cannot update mail log, cause : " + e.getMessage() + " sql error code ["+e.getErrorCode()+"]", e);
            //throw new MailException("Cannot update mail log, cause : " + e.getMessage() + " sql error code ["+e.getErrorCode()+"]", e);
        } catch (Exception e) {
            logger.error("Unexcepted error while update mail log, cause : " + e.getMessage(), e);
            //throw new MailException("Unexcepted error while update mail log, cause : " + e.getMessage(), e);
        } finally {
            if(updateLog != null) try { updateLog.close(); } catch(Exception e) {}
            if(connection != null) try { connection.close(); } catch(Exception e) {}
        }
    }

}
