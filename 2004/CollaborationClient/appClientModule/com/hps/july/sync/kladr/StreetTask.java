package com.hps.july.sync.kladr;

import com.hps.july.core.*;

/**
 * Date: 11.04.2007
 * Time: 17:09:44
 *
 * @author <a href="mailto: NIzhikov@gmail.com">Izhikov Nikolay</a>
 */
public class StreetTask implements Collaboration {
    private DB nri;

    public StreetTask(DB nri) {
        this.nri = nri;
    }

    public void doTask(Query query) throws CollaborationException {
        QueryProcessing.startProcessing(nri, query);
        try {
            StreetDAO kladrDAO = new StreetDAO(nri);

            kladrDAO.markDeletedWithNullKladr();
            kladrDAO.markDeleted();
            kladrDAO.updateNames();
            kladrDAO.insertNew();

            System.out.println("***DONE STREET_PROCESS ALL OK***");
            QueryProcessing.finishSuccess(nri, query);
        } catch(Exception e) {
            e.printStackTrace();
            QueryProcessing.finishError(nri, query);
        }
    }

    public void findChangesAndUpdate(Query argQry) throws CollaborationException {
        throw new UnsupportedOperationException("this method unsupported");
    }

    public void findDeletedInSourseDeleteInTarget(Query argQry) throws CollaborationException {
        throw new UnsupportedOperationException("this method unsupported");
    }
}
