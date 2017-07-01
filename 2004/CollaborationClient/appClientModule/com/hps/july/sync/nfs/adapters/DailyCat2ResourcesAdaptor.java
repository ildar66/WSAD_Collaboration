package com.hps.july.sync.nfs.adapters;

import com.hps.july.core.*;
import com.hps.july.util.StatementHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Dmitry Dneprov
 * Адаптер для переноса валют.
 */
public class DailyCat2ResourcesAdaptor extends DefaultCollaboration {

	PreparedStatement checkTargetStmt;

    public static class DailyCat2ResourcesMap extends ColumnMap {
        /**
         * Конструктор
         */
		DailyCat2ResourcesMap() {
            super();
            //         NFS              NRI            isKey
			addMap("INVENTORY_ITEM_ID", "item_id", true);
			//addMap("ORGANIZATION_ID", "organization_id", true);
            addMap("CATEGORY_ID", "category_id", true);

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
		result.append("SELECT distinct m.inventory_item_id, m.category_id ");
		result.append("FROM APPS.XX_NRI_MTL_ITEM_CATEGORIES m, APPS.XX_NRI_MTL_CATEGORIES_B i, APPS.XX_NRI_MTL_CATEGORIES_TL tl ");
		result.append("WHERE tl.category_id = i.category_id AND tl.language = 'RU' AND i.segment1 = 'TD' AND ");
		result.append("m.category_id = i.category_id ");
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
			buf.append("SELECT distinct m.inventory_item_id, m.category_id ");
			buf.append("FROM APPS.XX_NRI_MTL_ITEM_CATEGORIES m ");
			buf.append("WHERE ");
			buf.append(StatementHelper.generateClauseWithPrefix(columnMap.getKeysMap().keySet(), " AND ", "m"));
			try {
				preparedSelectSourceDb = sourceDbConnection.prepareStatement(buf.toString());
				//System.out.println("preparedSelectSourceDb ="+buf.toString());//temp
			} catch (SQLException e) {
				System.out.println("Cannot Prepare SQL=" + buf.toString());
				e.printStackTrace(System.out);
			}
		}
		result = super.findObjectJoinDb(argObj);
		if (result != null) {
			if (checkTargetStmt == null) {
				StringBuffer buf2 = new StringBuffer();
				buf2.append("SELECT item_id FROM nfsresources WHERE item_id = ?");
				try {
					checkTargetStmt = targetDbConnection.prepareStatement(buf2.toString());
				} catch (SQLException e) {
					System.out.println("Cannot Prepare SQL=" + buf2.toString());
					e.printStackTrace(System.out);
				}
			}
			// Check resource existence
			//Object ido = result.getKeysColumns().get("inventory_item_id");
			//System.out.println("class=" + ido.getClass() + ", value=" + ido);
			String id = ((java.math.BigDecimal)result.getKeysColumns().get("inventory_item_id")).toString();
			ResultSet rs = null;
			try {
				checkTargetStmt.setString(1, id);
				rs = checkTargetStmt.executeQuery();
				if (!rs.next()) {
					System.out.println("Skipping resource: " + id);
					result = null;
				}
			} catch (SQLException e) {
				System.out.println("ERROR executing SQL in BUF2");
				e.printStackTrace(System.out);
			}
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {}
		}
		return result;
	}


    public DailyCat2ResourcesAdaptor(DB argConNRI, DB argConNFS) {
        super(argConNRI, argConNFS, "nfsres2categories", "APPS.XX_NRI_MTL_ITEM_CATEGORIES", "m.last_update_date", new DailyCat2ResourcesMap());
    }
}
