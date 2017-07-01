package com.hps.july.sync.nfs.adapters;

import com.hps.july.core.*;
import com.hps.july.util.StatementHelper;

import java.sql.SQLException;

/**
 * @author Dmitry Dneprov
 * Адаптер для переноса валют.
 */
public class DailyCategoriesAdaptor extends DefaultCollaboration {

    public static class DailyCategoriesMap extends ColumnMap {
        /**
         * Конструктор
         */
		DailyCategoriesMap() {
            super();
            //         NFS              NRI            isKey
            addMap("CATEGORY_ID", "categoryid", true);
            addMap("SEGMENT1", "segment1", false);
			addMap("SEGMENT2", "segment2", false);
			addMap("SEGMENT3", "segment3", false);
			addMap("SEGMENT4", "segment4", false);
			addMap("SEGMENT5", "segment5", false);
			addMap("DESCRIPTION", "description", false);

			/*
            addMap("creation_date", "datecreate", false);
            addMap("created_by", "usercreate", false);
            addMap("last_update_date", "datemodify", false);
            addMap("last_updated_by", "usermodify", false);
            */

            // Колонки, предопределенные в таблице NRI
            addPredefinedColumnTargetDb("flagworknri", "N");
            addPredefinedColumnTargetDb("recordstatus", "A");
        }
    }

	/// Сформировать SQL для определения изменившихся записей в JOIN_DB
	protected String getSqlChangeInSource() {
		StringBuffer result = new StringBuffer();
		result.append("select distinct i.CATEGORY_ID, i.SEGMENT1, i.SEGMENT2, i.SEGMENT3, i.SEGMENT4, i.SEGMENT5, tl.DESCRIPTION DESCRIPTION ");
		result.append("from APPS.XX_NRI_MTL_CATEGORIES_B i, APPS.XX_NRI_MTL_CATEGORIES_TL tl ");
		result.append("WHERE tl.category_id = i.category_id AND tl.language = 'RU' AND i.segment1 = 'TD' ");
		java.sql.Timestamp lastdate = getLastUpdateDate();
		if (lastdate != null && nameLastUpdateDateColumnInSource != null) {
			result.append(" AND ");
			result.append(nameLastUpdateDateColumnInSource + " > ? ");
		}
		return result.toString();
	}

	/// Найти и заполнить поля объекта из системы JOIN_DB
	protected RowDBObject findObjectJoinDb(RowDBObject argObj) throws CollaborationException {
		RowDBObject result = null;
		StringBuffer buf = new StringBuffer();
		if (preparedSelectSourceDb == null) {
			buf.append("select distinct i.CATEGORY_ID, i.SEGMENT1, i.SEGMENT2, i.SEGMENT3, i.SEGMENT4, i.SEGMENT5, tl.DESCRIPTION DESCRIPTION ");
			buf.append("from APPS.XX_NRI_MTL_CATEGORIES_B i, APPS.XX_NRI_MTL_CATEGORIES_TL tl ");
			buf.append("WHERE tl.category_id = i.category_id AND tl.language = 'RU' AND i.segment1 = 'TD' ");
			buf.append(" AND ");
			buf.append(StatementHelper.generateClauseWithPrefix(columnMap.getKeysMap().keySet(), " AND ", "i"));
			try {
				preparedSelectSourceDb = sourceDbConnection.prepareStatement(buf.toString());
				//System.out.println("preparedSelectSourceDb ="+buf.toString());//temp
			} catch (SQLException e) {
				System.out.println("Cannot Prepare SQL=" + buf.toString());
				e.printStackTrace(System.out);
			}
		}
		result = super.findObjectJoinDb(argObj);
		return result;
	}


    public DailyCategoriesAdaptor(DB argConNRI, DB argConNFS) {
        super(argConNRI, argConNFS, "nfsrescategories", "APPS.XX_NRI_MTL_CATEGORIES_B", "i.last_update_date", new DailyCategoriesMap());
    }
}
