package com.hps.july.sync.kladr;

import com.hps.july.core.*;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Date: 19.03.2007
 * Time: 15:43:18
 *
 * @author <a href="mailto: NIzhikov@gmail.com">Izhikov Nikolay</a>
 */
public class KladrFacadeAdapter implements Collaboration {
    private DB kladr;
    private DB nri;

    private KladrService kladrService;

    public KladrFacadeAdapter(DB nri, DB kladr) {
        this.nri = nri;
        this.kladr = kladr;
        kladrService = new KladrService(nri);
    }

    public void findChangesAndUpdate(Query query) throws CollaborationException {
        QueryProcessing.startProcessing(nri, query);
        try {
            silentInsert(new AltnamesAdapter(nri, kladr), query);
            silentInsert(new SocrbaseAdapter(nri, kladr), query);
            silentInsert(new KladrAdapter(nri, kladr), query);
            silentInsert(new StreetAdapter(nri, kladr), query);

            QueryProcessing.finishSuccess(nri, query);
        } catch (Exception e) {
            QueryProcessing.finishError(nri, query);
        }

    }

    public void findDeletedInSourseDeleteInTarget(Query query) throws CollaborationException {
        QueryProcessing.startProcessing(nri, query);

        try {
            //silentDelete(new KladrAdapter(nri, kladr), query);
            silentDelete(new AltnamesAdapter(nri, kladr), query);
            silentDelete(new SocrbaseAdapter(nri, kladr), query);
            //silentDelete(new StreetAdapter(nri, kladr), query);

            QueryProcessing.finishSuccess(nri, query);
        } catch (Exception e) {
            QueryProcessing.finishError(nri, query);
        }
    }

    public void doTask(Query query) throws CollaborationException {
        QueryProcessing.startProcessing(nri, query);
        try {
            int russianId = kladrService.getRussianId();
            kladrService.markDeleted();
            kladrService.updateNames();
            kladrService.processRegionWithoutKladr(russianId);
            kladrService.insertNew();

            QueryProcessing.finishSuccess(nri, query);
        } catch(KladrException e) {
            QueryProcessing.finishError(nri, query);
        } catch(Exception e) {
            QueryProcessing.finishError(nri, query);
        }
    }

    /**
     * Calls when  KLADR_LOAD task
     * @param collaboration
     * @param query
     * @see com.hps.july.sync.client.FactoryKladr
     */
    private void silentDelete(Collaboration collaboration, Query query) throws Exception {
        try {
            collaboration.findDeletedInSourseDeleteInTarget(query);
        } catch (Exception e) {
            System.err.println("Some error execution query " + query);
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Calls when  KLADR_LOAD task
     * @param collaboration
     * @param query
     * @see com.hps.july.sync.client.FactoryKladr
     */
    private void silentInsert(Collaboration collaboration, Query query) {
        try {
            collaboration.findChangesAndUpdate(query);
        } catch (Exception e) {
            System.err.println("Some error execution query " + query);
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("c:\\java\\project\\nri_tn_datacomm_cvs\\CollaborationClient\\appClientModule\\sync.cfg"));

            KladrFacadeAdapter kladrFacadeAdapter = new KladrFacadeAdapter(new DB(prop, "NRI"), new DB(prop, "NFSDAILY"));
            kladrFacadeAdapter.findChangesAndUpdate(new Query(""));
        } catch (CollaborationException e) {
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
