package com.hps.july.sync.kladr;

import com.hps.july.core.DB;
import com.hps.july.core.Query;
import com.hps.july.sync.kladr.model.AdminRegion;
import com.hps.july.sync.kladr.model.Kladr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Date: 11.04.2007
 * Time: 18:44:31
 *
 * @author <a href="mailto: NIzhikov@gmail.com">Izhikov Nikolay</a>
 */
public class TempKladrService {
    private DB kladr;
    private DB nri;
    private static final int REGION_TYPE_CITY = 1000;

    public TempKladrService(DB kladr, DB nri) {
        this.kladr = kladr;
        this.nri = nri;
    }

    public void processKladr(int level, int parentRegionId, String[] patterns, String parentCode) {
        System.out.println("KladrFacadeAdapter.processKladr");
        System.out.println("parentRegionId = " + parentRegionId);
        System.out.println("pattern = " + patterns);

        NriDAO nriDAO = new NriDAO(nri);
        KladrDAO kladrDAO = new KladrDAO(nri);

        processRowWithNotNullKladr(level, parentRegionId, patterns, parentCode, nriDAO, kladrDAO);
        processRow(level, parentRegionId, patterns, parentCode, nriDAO, kladrDAO);
    }

    private void processRowWithNotNullKladr(int level, int parentRegionId, String[] patterns, String parentCode, NriDAO nriDAO, KladrDAO kladrDAO) {
        for (int j = 0; j < patterns.length; j++) {
            String pattern = patterns[j];

            List nriRegionsSortedByKladr = nriDAO.getKladrListSortedByKladrCode(parentRegionId);
            List kladrRegionsSortedByKladr = kladrDAO.getKladrListSortedByKladr(pattern, parentCode);

            int nriCounter = 0;
            int kladrCounter = 0;

            while (nriCounter< nriRegionsSortedByKladr.size() && kladrCounter< kladrRegionsSortedByKladr.size()) {
                AdminRegion adminRegion = (AdminRegion) nriRegionsSortedByKladr.get(nriCounter);
                Kladr kladr = (Kladr) kladrRegionsSortedByKladr.get(kladrCounter);

                int comparsionResult = adminRegion.getKladrCode().compareToIgnoreCase(kladr.getCode());
                if (comparsionResult==0) {
                    kladrCounter++;
                    nriCounter++;

                    int nextLevel = NriDAO.getNextLevel(level);
                    if (nextLevel == -1) {
                        continue;
                    }

                    processKladr(nextLevel, adminRegion.getRegionId().intValue(), KladrDAO.getPattern(nextLevel, kladr.getCode()), kladr.getCode());
                } else if (comparsionResult > 0) {
                    kladrCounter++;
                } else {
                    nriCounter++;
                }
            }
        }
    }


    public void processRow(int level, int parentRegionId, String[] patterns, String parentCode, NriDAO nriDAO, KladrDAO kladrDAO) {
        for (int j = 0; j < patterns.length; j++) {
            String pattern = patterns[j];
            List kladrRegionsSortedByName = kladrDAO.getKladrListSortedByName(pattern, parentCode);
            List nriRegionsSortedByName = nriDAO.getKladrListSortedByName(parentRegionId);

            int nriCounter = 0;
            int kladrCounter = 0;
            int equalCount = 0;

            NameComparator nameComparator = new NameComparator();
            while (nriCounter< nriRegionsSortedByName.size() && kladrCounter< kladrRegionsSortedByName.size()) {
                AdminRegion adminRegion = (AdminRegion) nriRegionsSortedByName.get(nriCounter);
                Kladr kladr = (Kladr) kladrRegionsSortedByName.get(kladrCounter);

                int comparsionResult = nameComparator.compare(adminRegion.getRegionName(), kladr.getName());

                if (comparsionResult==0) {
                    nriRegionsSortedByName.remove(nriCounter);
                    kladrRegionsSortedByName.remove(kladrCounter);

                    boolean flag = false;
                    while (nriCounter < nriRegionsSortedByName.size() &&
                           nameComparator.compare(((AdminRegion)nriRegionsSortedByName.get(nriCounter)).getRegionName(), kladr.getName()) == 0) {
                        nriRegionsSortedByName.remove(nriCounter);
                        flag = true;
                    }

                    if (flag) {
                        continue;
                    }

                    equalCount++;

                    nriDAO.setKladrCode(adminRegion.getRegionId(), kladr.getCode());

                    int nextLevel = NriDAO.getNextLevel(level);
                    if (nextLevel == NriDAO.LEVEL_EMPTY) {
                        continue;
                    }

                    processKladr(nextLevel, adminRegion.getRegionId().intValue(), KladrDAO.getPattern(nextLevel, kladr.getCode()), kladr.getCode());
                } else if (comparsionResult > 0) {
                    kladrCounter++;
                } else {
                    nriCounter++;
                }
            }

            if (NriDAO.LEVEL_CITY==level || NriDAO.LEVEL_SUBCITY==level) {
                for (int i = 0; i < kladrRegionsSortedByName.size(); i++) {
                    tryFindOnNextLevel((Kladr) kladrRegionsSortedByName.get(i), nriDAO, parentRegionId);
                }
            }

            System.out.println("equalCount = " + equalCount);
        }
    }

    private boolean tryFindOnNextLevel(Kladr kladr, NriDAO nriDAO, int parentRegionId) {
        AdminRegion adminRegion = nriDAO.findByParentAndName(parentRegionId, kladr.getName());

        if (adminRegion == null) {
            return false;
        }

        nriDAO.updateRegion(adminRegion.getRegionId().intValue(), parentRegionId, REGION_TYPE_CITY, kladr.getCode());
        return true;
    }

    public int getRussianId() {
        NriDAO nriDAO = new NriDAO(nri);
        return nriDAO.getRussianId();
    }

    public int changeParent(boolean updateParent, Query query) {
        NriDAO nriDAO = new NriDAO(nri);
        int countWrong = 0;

        List nriRegionsSortedByKladr = nriDAO.getAllRegions();
        Map regionById = getRegionMap(nriRegionsSortedByKladr);

        for (int i = 0; i < nriRegionsSortedByKladr.size(); i++) {
            AdminRegion adminRegion = (AdminRegion) nriRegionsSortedByKladr.get(i);
            AdminRegion wrongParent = (AdminRegion) regionById.get(adminRegion.getParentRegionId());
            if (wrongParent == null) {
                System.out.println("can't find parent with id = " + adminRegion.getRegionId() + ", probably RussiaRegion");
                continue;
            }

            String realParentCode = KladrDAO.getParent(adminRegion.getKladrCode());
            String parentCode = wrongParent.getKladrCode();

            if (!realParentCode.equals(parentCode)) {
                countWrong++;

                AdminRegion writeParent = nriDAO.findByKladrCode(realParentCode);

                String message = "adminRegionID = " + adminRegion.getRegionId() + ", name = " + adminRegion.getRegionName() +
                                 ", kladrCode = " + adminRegion.getKladrCode() + ", wrongParentRegionID = " + adminRegion.getParentRegionId();

                if (writeParent != null) {
                    message += ", writeParentRegionID = " + writeParent.getRegionId() + ", writeName = " + writeParent.getRegionName();
                } else {
                    message += " CAN'T FIND PARENT REGION";
                }

                if (updateParent) {
                    if (writeParent == null) {
                        writeParent = insertParent(realParentCode, adminRegion.getKladrCode(), adminRegion.getRegionName());
                    }

                    nriDAO.updateRegion(adminRegion.getRegionId().intValue(), writeParent.getRegionId().intValue());
                } else {
                    System.out.println(message);
                    //QueryProcessing.addLogMessage(nri, query, QueryProcessing.MSG_INFO, message);
                }
            }

            if (i%1000 == 0) {
                System.out.println(i + " of " + nriRegionsSortedByKladr.size());
            }
        }

        return countWrong;
    }

    private AdminRegion insertParent(String parentCode, String childCode, String childName) {
        KladrDAO kladrDAO = new KladrDAO(nri);
        NriDAO nriDAO = new NriDAO(nri);

        Kladr kladr = kladrDAO.findByCode(parentCode);
        String parent = KladrDAO.getParent(kladr.getCode());

        AdminRegion adminRegion = nriDAO.findByKladrCode(parent);
        if (adminRegion == null) {
            adminRegion = insertParent(parent, childCode, childName);
        }

        System.out.println("=================================");
        System.out.println("ParentName - " + adminRegion.getRegionName() + ", kladr - " + adminRegion.getKladrCode());
        System.out.println("Insert name - " + kladr.getName() + ", code - " + kladr.getCode());
        System.out.println("AdminRegion Without Parent - " + childName + ", code - " + childCode);

        return nriDAO.insertAdminRegion(adminRegion.getRegionId().intValue(), kladr, NriDAO.LEVEL_CITY);
    }

    private Map getRegionMap(List nriRegionsSortedByKladr) {
        Map result = new HashMap();
        for (int i = 0; i < nriRegionsSortedByKladr.size(); i++) {
            AdminRegion adminRegion = (AdminRegion) nriRegionsSortedByKladr.get(i);
            result.put(adminRegion.getRegionId(), adminRegion);
        }

        return result;
    }
}
