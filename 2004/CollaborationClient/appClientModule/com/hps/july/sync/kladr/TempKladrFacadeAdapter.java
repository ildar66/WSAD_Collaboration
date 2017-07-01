package com.hps.july.sync.kladr;

import com.hps.july.core.DB;
import com.hps.july.core.Query;
import com.hps.july.core.CollaborationException;
import com.hps.july.core.Collaboration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Date: 11.04.2007
 * Time: 18:43:39
 *
 * @author <a href="mailto: NIzhikov@gmail.com">Izhikov Nikolay</a>
 */
public class TempKladrFacadeAdapter implements Collaboration {
    private KladrService kladrService;
    private TempKladrService tempKladrService;

    private DB nri;
    private DB kladr;
    //private CountKladrService countKladrService;

    public TempKladrFacadeAdapter(DB nri, DB kladr) {
        tempKladrService = new TempKladrService(kladr, nri);
        //countKladrService = new CountKladrService(kladr, nri);
        kladrService = new KladrService(nri);

        this.nri = nri;
        this.kladr = kladr;
    }

    public void doTask(Query argQry) throws CollaborationException {
/*
        QueryProcessing.startProcessing(nri, argQry);
        try {
            int russianId = tempKladrService.getRussianId();

            boolean updateParent;
            String parameter = argQry.getParameter("updateParent");
            if (parameter == null || "".equals(parameter)) {
                updateParent = false;
            } else {
                updateParent = Boolean.getBoolean(parameter);
            }
*/
            int countWrong = tempKladrService.changeParent(true, argQry);
            System.out.println("countWrong = " + countWrong);
/*
//            tempKladrService.processKladr(NriDAO.LEVEL_PROVINCE, russianId, KladrDAO.getPattern(NriDAO.LEVEL_PROVINCE, "0000000000000"), "0000000000000");

            QueryProcessing.finishSuccess(nri, argQry);
        } catch(KladrException e) {
            QueryProcessing.finishError(nri, argQry);
        } catch(Exception e) {
            QueryProcessing.finishError(nri, argQry);
        }
*/
    }

    public static void main(String[] args) {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("c:\\java\\project\\nri_tn_datacomm_cvs\\CollaborationClient\\appClientModule\\sync.cfg"));

            TempKladrFacadeAdapter kladrFacadeAdapter = new TempKladrFacadeAdapter(new DB(prop, "NRI"), new DB(prop, "KLADR"));
            kladrFacadeAdapter.doTask(new Query(""));
        } catch (CollaborationException e) {
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void findChangesAndUpdate(Query argQry) throws CollaborationException {
        throw new UnsupportedOperationException("this method unsupported");
    }

    public void findDeletedInSourseDeleteInTarget(Query argQry) throws CollaborationException {
        throw new UnsupportedOperationException("this method unsupported");
    }
}