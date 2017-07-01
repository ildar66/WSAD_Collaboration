package com.hps.july.sync.nfs.adapters;

import com.hps.july.core.*;
import com.hps.july.util.StatementHelper;

import java.sql.SQLException;

/**
 * @author Dmitry Dneprov
 * Адаптер для переноса серийных номеров ресурсов NFS.
 */
public class DailySerialsAdaptor extends DefaultCollaboration {

	private Object itemID;

    private static class DailySerialsMap extends ColumnMap {
        /**
         * Конструктор
         */
		DailySerialsMap(Object argItemID) {
            super();
            //         NFS                    NRI     isKey
            addMap("SERIAL_NUMBER",        "serial", true);
			//addMap("ATTRIBUTE2",        "inventserial", false);
			addPredefinedKeyColumnTargetDb("item_id", argItemID);

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
		result.append("SELECT distinct s.INVENTORY_ITEM_ID, s.SERIAL_NUMBER ");
		result.append("FROM APPS.XX_NRI_MTL_ITEM_CATEGORIES i, APPS.XX_NRI_MTL_SYSTEM_ITEMS_TL tl, ");
		result.append("APPS.XX_NRI_MTL_ITEM_CATEGORIES c, APPS.XX_NRI_MTL_CATEGORIES_B b, APPS.XX_NRI_MTL_SERIAL_NUMBERS s ");
		result.append("WHERE tl.inventory_item_id = i.inventory_item_id AND tl.SOURCE_LANG = 'RU' AND ");
		result.append("tl.organization_id = i.organization_id AND ");
		result.append("c.organization_id = i.organization_id AND ");
		result.append("c.inventory_item_id = i.inventory_item_id AND ");
		result.append("b.category_id = c.category_id AND b.segment1 = 'TD' AND ");
		result.append("s.inventory_item_id = i.inventory_item_id AND ");
		result.append("s.inventory_item_id = '");
		result.append(itemID);
		result.append("' ");
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
			buf.append("SELECT distinct s.INVENTORY_ITEM_ID, s.SERIAL_NUMBER ");
			buf.append("FROM APPS.XX_NRI_MTL_SERIAL_NUMBERS s ");
			buf.append("WHERE s.inventory_item_id = '");
			buf.append(itemID);
			buf.append("'");
			buf.append(" AND ");
			buf.append(StatementHelper.generateClauseWithPrefix(columnMap.getKeysMap().keySet(), " AND ", "s"));
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

    public DailySerialsAdaptor(DB argConNRI, DB argConNFS, Object argItemID) {
        super(argConNRI, argConNFS, "nfsserials", "APPS.XX_NRI_MTL_SERIAL_NUMBERS", null, new DailySerialsMap(argItemID));
        itemID = argItemID;
    }
}
