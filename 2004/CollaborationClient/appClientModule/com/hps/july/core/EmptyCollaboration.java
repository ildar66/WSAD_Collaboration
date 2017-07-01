package com.hps.july.core;

import org.apache.log4j.Logger;

/**
 * @author <a href="mailto: NIzhikov@gmail.com">Izhikov Nikolay</a>
 */
public class EmptyCollaboration implements Collaboration {
    private Logger log = Logger.getLogger(EmptyCollaboration.class);

    public void findChangesAndUpdate(Query argQry) throws CollaborationException {
        log.debug("EmptyCollaboration.findChangesAndUpdate");
        log.error("THIS METHOD PROBABLY NOT SUPPORTED!");
    }

    public void findDeletedInSourseDeleteInTarget(Query argQry) throws CollaborationException {
        log.debug("EmptyCollaboration.findDeletedInSourseDeleteInTarget");
        log.error("THIS METHOD PROBABLY NOT SUPPORTED!");
    }

    public void doTask(Query argQry) throws CollaborationException {
        log.debug("EmptyCollaboration.doTask");
        log.error("THIS METHOD PROBABLY NOT SUPPORTED!");
    }
}
