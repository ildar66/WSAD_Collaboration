/*
 *  $Id: HttpMailDao.java,v 1.7 2007/06/09 12:32:23 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком    
 */
package com.hps.july.mail.service.impl.dao;

import com.hps.july.mail.object.MailTransportObject;
import com.hps.july.mail.MailException;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.cookie.CookieSpec;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Properties;
import java.io.*;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.7 $ $Date: 2007/06/09 12:32:23 $
 */
public class HttpMailDao implements MailDAO {

	private final static String PROPERTY_FILE = "nri-mail-client.properties";

	private String host;
    private int port;
    private String context;
    private HttpClient client;
    private HttpConnectionManager manager;
    private HostConfiguration config;
	private Log log = LogFactory.getLog(HttpMailDao.class);

	public HttpMailDao() {
        Properties prop = new Properties();
        InputStream is = null;
        try {
			is = HttpMailDao.class.getClassLoader().getResourceAsStream(PROPERTY_FILE);
			if(is == null) {
				is = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTY_FILE);
				if(is == null) {
					is = ClassLoader.getSystemClassLoader().getResourceAsStream(PROPERTY_FILE);
				}
			}
			prop.load(is);
            host = prop.getProperty("host");
			context = prop.getProperty("context"); 
            port = Integer.parseInt(prop.getProperty("port"));
			log.info("Configuration ["+host+"] ["+port+"] ["+context+"] ");
		} catch(Throwable e) {
            log.error("Error getting property from file 'nri-mail-client.properties'");
            throw new IllegalArgumentException("Error getting property from file 'nri-mail-client.properties'");
        } finally {
            if(is != null) {
				try {
					is.close();
				} catch (Exception e) {}
			}
        }
    }

    public void sendMail(MailTransportObject mailTransportObject) throws MailException {
    	client = new HttpClient();
		client.getHostConfiguration().setHost(host, port, "http");
		client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
		CookieSpec cookiespec = CookiePolicy.getDefaultSpec();

    	
        //manager = new SimpleHttpConnectionManager();
        //config = new HostConfiguration();
        //config.setHost(host, port);

        File tempFile = null;
        PostMethod method = null;
		PostMethod authpost = null;
        InputStream response = null;
        HttpConnection connection = null;
        ObjectOutputStream output = null;
        try {
            //serialize object
            FilePart filePart = null;

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            output = new ObjectOutputStream(buffer);
            output.writeObject(mailTransportObject);
            output.flush();
            byte[] bytes = buffer.toByteArray();

            //send
            StringBuffer url = new StringBuffer();
			if(context != null && context.trim().length() > 0) {
				url.append("/");
				url.append(context);
			}
			url.append("/externalMailListener");
			authpost = new PostMethod("/july-mail-service-web/j_security_check");
			//PostMethod authpost = new PostMethod("/rts/RequestsListAction.do");
			// Prepare login parameters
			NameValuePair action   = new NameValuePair("action", "login");
			NameValuePair userid   = new NameValuePair("j_username", "vad");
			NameValuePair password = new NameValuePair("j_password", "vad01");
			authpost.setRequestBody(
			  new NameValuePair[] {action, userid, password});

			client.executeMethod(authpost);
			authpost.releaseConnection();
			Cookie[] logoncookies = cookiespec.match(
				host, port, "/", false, client.getState().getCookies());
			//System.out.println("Logon cookies:");
			Cookie ltpaToken = null;
			if (logoncookies.length == 0) {
				System.out.println("None");
			} else {
				for (int i = 0; i < logoncookies.length; i++) {
					//System.out.println("- " + logoncookies[i].toString());
					if(logoncookies[i].getName().equals("LtpaToken"))
						ltpaToken = logoncookies[i];
				}
			}

			//url.append(url.toString());
			log.info("["+url+"]");
			System.out.println("["+url+"]");
            method = new PostMethod(url.toString());
            //method = new PostMethod("http://" + host + ":" + port + "/july-mail-server/mailListener");
            Part[] parts = new Part[]{new FilePart("mail_transport_object", new org.apache.commons.httpclient.methods.multipart.ByteArrayPartSource("mail_transport_object", bytes))};
            method.setRequestEntity(new MultipartRequestEntity(parts, method.getParams()));
            int statusCode = 0;
            int count = 0;
            while(statusCode != HttpStatus.SC_OK) {
				
				client.executeMethod(method);

                //connection = manager.getConnection(config);
                //if(!connection.isOpen())
                //    connection.open();
                //method.execute(new HttpState(), connection);
                statusCode = method.getStatusCode();
                if(statusCode != HttpStatus.SC_OK) {
                    //System.out.println("retry! error statusCode="+statusCode);
                    method.releaseConnection();
                    count++;
                }
                if(count > 1) break;
            }
			//TODO : можно более подробно обработать ошибки !
            if(statusCode != HttpStatus.SC_OK) {
                throw new MailException("Sending mail was unsuccessful, HTTP status code  ["+statusCode+"], check server logs !");
            }
            response = method.getResponseBodyAsStream();
            //process response
            Object result = getReturnObject(response);

            //if(connection != null) manager.releaseConnection(connection);
			method.releaseConnection();

        } catch(MailException e) {
            e.printStackTrace();
            throw e;
        } catch(Exception e) {
        	e.printStackTrace();
            log.error("Cannot send mail object to server ", e);
            throw new MailException(e.getMessage());
        } finally {
            if(output != null) {
				try {
					output.close();
				} catch (Exception e) {}
			}
            if(response != null) {
				try {
					response.close();
				} catch (Exception e) {}
			}
			if(authpost != null) {
				authpost.releaseConnection();
			}
            if(method != null) {
				method.releaseConnection();
			}
            if(connection != null) {
            	if(manager != null)
				manager.releaseConnection(connection);
			}
        }
    }

    private Object getReturnObject(InputStream response) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(response);
        return ois.readObject();
    }


}
