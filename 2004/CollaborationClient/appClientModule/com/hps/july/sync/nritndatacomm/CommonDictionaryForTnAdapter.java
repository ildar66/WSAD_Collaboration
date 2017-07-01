package com.hps.july.sync.nritndatacomm;

import com.hps.july.core.*;

/**
 * Date: 06.02.2007
 * Time: 16:22:04
 *
 * @author <a href="mailto: NIzhikov@gmail.com">Izhikov Nikolay</a>
 */
public class CommonDictionaryForTnAdapter extends EmptyCollaboration {
    private DB nri_db;
    private DB tn_db;

    public CommonDictionaryForTnAdapter(DB tn_db, DB nri_db) {
        this.nri_db = nri_db;
        this.tn_db = tn_db;
    }

    public void findChangesAndUpdate(Query argQry) throws CollaborationException {
        System.out.println("CommonDictionaryForTnAdapter.findChangesAndUpdate");

        silentInsert(new AntennplacesForTnAdapter(tn_db, nri_db), argQry);
        silentInsert(new ApparatplacesForTnAdapter(tn_db, nri_db), argQry);
        silentInsert(new ApplacetypesForTnAdapter(tn_db, nri_db), argQry);
/*      silentInsert(new ResourcesForTnAdapter(tn_db, nri_db), argQry);
        silentInsert(new SitesForTnAdapter(tn_db, nri_db), argQry);*/
        silentInsert(new OrganizationForTnAdapter(tn_db, nri_db), argQry);

    }

    public void findDeletedInSourseDeleteInTarget(Query argQry) throws CollaborationException {
        System.out.println("CommonDictionaryForTnAdapter.findDeletedInSourseDeleteInTarget");

        silentDelete(new AntennplacesForTnAdapter(tn_db, nri_db), argQry);
        silentDelete(new ApparatplacesForTnAdapter(tn_db, nri_db), argQry);
        silentDelete(new ApplacetypesForTnAdapter(tn_db, nri_db), argQry);
/*      silentInsert(new ResourcesForTnAdapter(tn_db, nri_db), argQry);
        silentDelete(new SitesForTnAdapter(tn_db, nri_db), argQry);*/
        silentDelete(new OrganizationForTnAdapter(tn_db, nri_db), argQry);
    }

    private void silentDelete(Collaboration collaboration, Query query) {
        try {
            collaboration.findDeletedInSourseDeleteInTarget(query);
        } catch (Exception e) {
            System.err.println("Some error execution query " + query);
            e.printStackTrace();
        }
    }

    private void silentInsert(Collaboration collaboration, Query query) {
        try {
            collaboration.findChangesAndUpdate(query);
        } catch (Exception e) {
            System.err.println("Some error execution query " + query);
            e.printStackTrace();
        }
    }
}
