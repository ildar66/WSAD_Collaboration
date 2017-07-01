package com.hps.july.sync.nfs.adapters;

import com.hps.july.core.*;
import com.hps.july.util.StatementHelper;

import java.sql.SQLException;

/**
 * @author Dmitry Dneprov
 * Адаптер для переноса валют.
 */
public class DailyResourcesAdaptor extends DefaultCollaboration {

    private static class DailyResourcesMap extends ColumnMap {
        /**
         * Конструктор
         */
		DailyResourcesMap() {
            super();
            //         NFS              NRI            isKey
            addMap("INVENTORY_ITEM_ID", "item_id", true);
            addMap("SEGMENT1", "segment1", false);
			addMap("DESCRIPTION", "description", false);
			addMap("ENGDESCR", "engdescr", false);

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
		result.append("SELECT distinct i.INVENTORY_ITEM_ID, i.SEGMENT1 ");
		result.append("FROM APPS.XX_NRI_MTL_SYSTEM_ITEMS_B i, ");
		result.append("APPS.XX_NRI_MTL_ITEM_CATEGORIES c, APPS.XX_NRI_MTL_CATEGORIES_B b ");
		result.append("WHERE ");
		result.append("c.organization_id = i.organization_id AND ");
		result.append("c.inventory_item_id = i.inventory_item_id AND ");
		result.append("b.category_id = c.category_id AND b.segment1 = 'TD' ");
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
			buf.append("SELECT distinct i.INVENTORY_ITEM_ID, i.SEGMENT1, ");
			buf.append(" (SELECT min(UNIQUE tl.DESCRIPTION) FROM APPS.XX_NRI_MTL_SYSTEM_ITEMS_TL tl ");
			buf.append("     WHERE tl.inventory_item_id = i.inventory_item_id AND tl.SOURCE_LANG = 'RU' ");
			buf.append("  ) description, ");
			buf.append(" (SELECT min(UNIQUE tl2.DESCRIPTION) FROM APPS.XX_NRI_MTL_SYSTEM_ITEMS_TL tl2 ");
			buf.append("     WHERE tl2.inventory_item_id = i.inventory_item_id AND tl2.SOURCE_LANG = 'US' ");
			buf.append("  ) engdescr ");
			buf.append("FROM APPS.XX_NRI_MTL_SYSTEM_ITEMS_B i ");
			buf.append("WHERE ");
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


	/// Установка связи с существующим объектом TARGET_DB
	protected boolean makeRelationInTargetDb(RowDBObject argObj) throws CollaborationException {
		/* Temprorarily commented until best times
		Object itemID = argObj.getKeysColumns().get("inventory_item_id");
		System.out.println("Getting serials for itemid = " + itemID);
		DailySerialsAdaptor a = new DailySerialsAdaptor(getTargetDb(), getSourceDb(), itemID);
		a.findChangesAndUpdate(getQuery());
		*/
		return true;
	}


    public DailyResourcesAdaptor(DB argConNRI, DB argConNFS) {
        super(argConNRI, argConNFS, "nfsresources", "APPS.XX_NRI_MTL_SYSTEM_ITEMS_B", "i.last_update_date", new DailyResourcesMap());
    }
}
