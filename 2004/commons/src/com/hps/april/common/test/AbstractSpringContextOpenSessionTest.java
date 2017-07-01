package com.hps.april.common.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public abstract class AbstractSpringContextOpenSessionTest extends AbstractSpringContextTest {

	private static String SESSION_FACTORY_KEY = "april.hibernateSessionFactory";

	private SessionFactory sessionFactory;
	private Session session;
	private boolean participate = false;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		sessionFactory = 
			(SessionFactory) getBean(SESSION_FACTORY_KEY, SessionFactory.class);

		if (TransactionSynchronizationManager.hasResource(sessionFactory)) {
			// Do not modify the Session: just set the participate flag.
			participate = true;
		}
		else {
			logger.debug("Opening single Hibernate Session");
			session = getSession(sessionFactory);
			TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));
		}
	}
	
	protected void tearDown() throws Exception {
		if (!participate) {
			// single session mode
			TransactionSynchronizationManager.unbindResource(sessionFactory);
			logger.debug("Closing single Hibernate Session");
			try {
				closeSession(session, sessionFactory);
			}
			catch (RuntimeException ex) {
				logger.error("Unexpected exception on closing Hibernate Session", ex);
			}
		}

		super.tearDown();
	}
	
	private Session getSession(SessionFactory sessionFactory) throws DataAccessResourceFailureException {
		Session session = SessionFactoryUtils.getSession(sessionFactory, true);
		//session.setFlushMode(FlushMode.NEVER);
		return session;
	}
	
	private void closeSession(Session session, SessionFactory sessionFactory) {
		SessionFactoryUtils.releaseSession(session, sessionFactory);
	}

	
}
