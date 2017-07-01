package com.hps.july.sync.kladr;

import com.hps.july.core.DB;
import com.hps.july.sync.kladr.model.AdminRegion;

import java.sql.SQLException;
import java.util.List;

/**
 * Date: 11.04.2007
 * Time: 14:38:19
 *
 * @author <a href="mailto: NIzhikov@gmail.com">Izhikov Nikolay</a>
 */
public class KladrService {
    private DB nri;

    public KladrService(DB nri) {
        this.nri = nri;
    }

    public void deleteFreeAdminRegions(int parentRegionId, int level) {
        System.out.println("KladrService.deleteFreeAdminRegions");
        System.out.println("parentRegionId = " + parentRegionId);

        NriDAO nriDAO = new NriDAO(nri);
        int equals = 1;

        while (equals != 0) {
            equals = 0;
            List nriRegions = nriDAO.getAllKladrList(parentRegionId);
            for (int i = 0; i < nriRegions.size(); i++) {
                AdminRegion adminRegion = (AdminRegion) nriRegions.get(i);
                try {
                   nriDAO.deleteAdminRegion(adminRegion.getRegionId());
                   equals++;
                } catch (SQLException e) {
                    System.out.println("exception with id = " + adminRegion.getRegionId());
                }
            }
        }
    }

    public void markDeleted() {
        NriDAO nriDAO = new NriDAO(nri);
        nriDAO.markDeleted();
    }

    public int getRussianId() {
        NriDAO nriDAO = new NriDAO(nri);
        return nriDAO.getRussianId();
    }

    public void updateNames() {
        NriDAO nriDAO = new NriDAO(nri);
        nriDAO.updateNames();
    }

    public void insertNew() {
        NriDAO nriDAO = new NriDAO(nri);
        nriDAO.insertNew();
    }

    public void processRegionWithoutKladr(int russianId) {
        NriDAO nriDAO = new NriDAO(nri);

        List regionWithNulKladr = nriDAO.getAllRegionWithNullKladr();
        System.out.println("NEED UPDATE " + regionWithNulKladr.size());
        for (int i = 0; i < regionWithNulKladr.size(); i++) {
            if (i % 10 == 0) {
                System.out.println(i + " - processed");
            }

            AdminRegion adminRegion = (AdminRegion) regionWithNulKladr.get(i);
            try {
                if (nriDAO.isRoot(adminRegion, new Integer(russianId))) {
                    nriDAO.deleteAdminRegion(adminRegion.getRegionId());
                }
            } catch(SQLException e) {
                nriDAO.setDeleted(adminRegion.getRegionId());
            }
        }
    }
}
