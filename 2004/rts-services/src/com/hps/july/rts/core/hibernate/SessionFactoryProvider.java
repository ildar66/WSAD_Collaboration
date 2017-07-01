package com.hps.july.rts.core.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hps.july.rts.RTSContextProvider;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 02.02.2006
 * Time: 13:23:20
 * @deprecated use Spring DAOSupport
 */
public class SessionFactoryProvider {

	protected Logger logger = Logger.getLogger(SessionFactoryProvider.class);
	
	private static SessionFactoryProvider instance = new SessionFactoryProvider();
    private SessionFactory sessionFactory;
    
    public SessionFactory getSessionFactory(){
    	if (sessionFactory == null) {
    		logger.debug("OPEN SESSION FACTORY");
    		
    		sessionFactory = (SessionFactory) 
    			RTSContextProvider.getBean("july.hibernateSessionFactory", SessionFactory.class);
    	}
    	return sessionFactory;
    }
    
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected SessionFactoryProvider(){}
	
    public final ThreadLocal sessionThread = new ThreadLocal();

    public static Session currentSession()
            throws HibernateException {

    	return getInstance().getSession();
     }

    public Session getSession(){
       	Session session = (Session)sessionThread.get();
        if(session == null) {
        	session = getSessionFactory().openSession();
        	sessionThread.set(session);
        }
        return session;
    }
    
    public void releaseSession(){
        Session session = (Session) sessionThread.get();
        sessionThread.set(null);
        if (session != null) session.close();
    }
    
    
    public static void closeSession()
            throws HibernateException {
    	getInstance().releaseSession();
    }

    public static SessionFactoryProvider getInstance(){
    	return instance;
    }

}
