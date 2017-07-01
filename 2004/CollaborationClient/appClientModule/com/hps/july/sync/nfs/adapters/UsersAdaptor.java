package com.hps.july.sync.nfs.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.DefaultCollaboration;
import com.hps.july.core.ColumnMap;

/**
 * @author Dmitry Dneprov
 * ������� ��� �������� ������.
 */
public class UsersAdaptor extends DefaultCollaboration {

    private static class UsersMap extends ColumnMap {
        /**
         * �����������
         */
        UsersMap() {
            super();
            //         NFS              NRI            isKey
            addMap("person_id", "personId", true);
            addMap("user_id", "idUserNfs", false);
            addMap("user_name", "login", false);
            addMap("full_name", "fullName", false);

            // �������, ���������������� � ������� NRI
            addPredefinedColumnTargetDb("flagworknri", "N");
            addPredefinedColumnTargetDb("recordstatus", "A");
        }
    }

    public UsersAdaptor(DB argConNRI, DB argConNFS) {
        super(argConNRI, argConNFS, "nfs_users", "APPS.XX_NRI_USERS_VW", new UsersMap());
    }
}
