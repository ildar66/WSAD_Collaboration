package com.hps.july.rts.task;

import com.hps.july.rts.auth.service.RTSAuthService;
import com.hps.july.rts.RTSContextProvider;
import com.hps.july.rts.task.service.RTSTaskService;
//import com.hps.july.rts.core.CoreContextProvider;
//import com.hps.july.rts.SystemException;
//import com.hps.july.commons.task.Task;
import org.hibernate.SessionFactory;
//import org.hibernate.Session;
//import org.hibernate.FlushMode;
//import org.springframework.transaction.support.TransactionSynchronizationManager;
//import org.springframework.orm.hibernate3.SessionHolder;
//import org.springframework.orm.hibernate3.SessionFactoryUtils;
//import org.springframework.dao.DataAccessResourceFailureException;
import org.apache.log4j.Logger;

import java.util.Date;

/**
 *
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 30.05.2006
 * Time: 17:45:50
 */
public abstract class RTSTimerTask //extends Task { 
{

    protected Logger logger = Logger.getLogger(RTSTimerTask.class);
    protected long DEFAULT_PERIOD = 5000L;

    protected Integer requestId;
    protected String requestNumber;
    protected String requestType;
    protected Date created;

    private RTSTaskService taskService;
    private RTSAuthService authService;

//    private SessionFactory sessionFactory;

    public Object getBean(String beanName){
        return RTSContextProvider.getBean(beanName);
    }

    public RTSAuthService getAuthService() {
        if (authService == null) authService = (RTSAuthService)getBean(RTSAuthService.SERVICE_NAME);
        return authService;
    }

    public RTSTaskService getTaskService() {
        if (taskService == null) taskService = (RTSTaskService)getBean(RTSTaskService.SERVICE_NAME);
        return taskService;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

//    public CommentService getCommentService() {
//        if (commentService == null) commentService = (CommentService)getBean(CommentService.SERVICE_NAME);
//        return commentService;
//    }
//
//    public NotificationService getNotificationService() {
//        if (notificationService == null) notificationService = (NotificationService)getBean(NotificationService.SERVICE_NAME);
//        return notificationService;
//    }
//
//    public RTSOperatorService getOperatorService() {
//        if (operatorService == null) operatorService = (RTSOperatorService)getBean(RTSOperatorService.SERVICE_NAME);
//        return operatorService;
//    }
//
//    public RTSMailService getMailService() {
//        if (mailService == null) mailService = (RTSMailService) getBean(RTSMailService.SERVICE_NAME);
//        return mailService;
//    }
//
//    public RTSAuthService getAuthService() {
//        if (authService == null) authService = (RTSAuthService)getBean(RTSAuthService.SERVICE_NAME);
//        return authService;
//    }
//
//    public ConsolidatedRequestService getConsolidatedRequestService() {
//        if (consolidatedRequestService == null)
//            consolidatedRequestService = (ConsolidatedRequestService) getBean(ConsolidatedRequestService.SERVICE_NAME);
//        return consolidatedRequestService;
//    }
//
//    public InitiatorRequestService getInitiatorRequestService() {
//        if (initiatorRequestService == null)
//            initiatorRequestService = (InitiatorRequestService) getBean(InitiatorRequestService.SERVICE_NAME);
//        return initiatorRequestService;
//    }
//
//    public ElementaryRequestService getElementaryRequestService() {
//        if(elementaryRequestService == null) {
//            elementaryRequestService = (ElementaryRequestService)getBean(ElementaryRequestService.SERVICE_NAME);
//        }
//        return elementaryRequestService;
//    }
//
//    public RTSStatusService getRtsStatusService() {
//        if (statusService == null) statusService = (RTSStatusService) getBean(RTSStatusService.SERVICE_NAME);
//        return statusService;
//    }

//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

//    public void channel() {
//        Session session = null;
//        boolean participate = false;
//
//        if (isSingleSession()) {
//            // single session mode
//            if (TransactionSynchronizationManager.hasResource(sessionFactory)) {
//                // Do not modify the Session: just set the participate flag.
//                participate = true;
//            }
//            else {
//                logger.debug("Opening single Hibernate Session in " + this.getClass().getName());
//                session = getSession(sessionFactory);
//                TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));
//            }
//        }
//        else {
//            // deferred close mode
//            if (SessionFactoryUtils.isDeferredCloseActive(sessionFactory)) {
//                // Do not modify deferred close: just set the participate flag.
//                participate = true;
//            }
//            else {
//                SessionFactoryUtils.initDeferredClose(sessionFactory);
//            }
//        }
//
//        try {
//            process();
//        } catch (RuntimeException e) {
//            getLogger().debug("Uexpected error during executing process ", e);
//        } catch (SystemException e) {
//            getLogger().debug("Internal system error during executing process ", e);
//        } finally {
//            if (!participate) {
//                if (isSingleSession()) {
//                    // single session mode
//                    TransactionSynchronizationManager.unbindResource(sessionFactory);
//                    logger.debug("Closing single Hibernate Session in " + this.getClass().getName());
//                    try {
//                        closeSession(session, sessionFactory);
//                    }
//                    catch (RuntimeException ex) {
//                        logger.error("Unexpected exception on closing Hibernate Session", ex);
//                    }
//                }
//                else {
//                    // deferred close mode
//                    SessionFactoryUtils.processDeferredClose(sessionFactory);
//                }
//            }
//        }
//    }

//    public boolean cancel() {
//        this.sessionFactory = null;
//        return super.cancel();
//    }
//
//    protected boolean isSingleSession() {
//        return true;
//    }

    /**
     * Get a Session for the SessionFactory that this filter uses.
     * Note that this just applies in single session mode!
     * <p>The default implementation delegates to SessionFactoryUtils'
     * getSession method and sets the Session's flushMode to NEVER.
     * <p>Can be overridden in subclasses for creating a Session with a custom
     * entity interceptor or JDBC exception translator.
     * @param sessionFactory the SessionFactory that this filter uses
     * @return the Session to use
     * @throws org.springframework.dao.DataAccessResourceFailureException if the Session could not be created
     * @see org.springframework.orm.hibernate3.SessionFactoryUtils#getSession(SessionFactory, boolean)
     * @see org.hibernate.FlushMode#NEVER
     */
//    protected Session getSession(SessionFactory sessionFactory) throws DataAccessResourceFailureException {
//        Session session = SessionFactoryUtils.getSession(sessionFactory, true);
//        session.setFlushMode(FlushMode.NEVER);
//        return session;
//    }

    /**
     * Close the given Session.
     * Note that this just applies in single session mode!
     * <p>The default implementation delegates to SessionFactoryUtils'
     * releaseSession method.
     * <p>Can be overridden in subclasses, e.g. for flushing the Session before
     * closing it. See class-level javadoc for a discussion of flush handling.
     * Note that you should also override getSession accordingly, to set
     * the flush mode to something else than NEVER.
     * @param session the Session used for filtering
     * @param sessionFactory the SessionFactory that this filter uses
     */
//    protected void closeSession(Session session, SessionFactory sessionFactory) {
//        SessionFactoryUtils.releaseSession(session, sessionFactory);
//    }
}