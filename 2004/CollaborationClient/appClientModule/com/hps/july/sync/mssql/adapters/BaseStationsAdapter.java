package com.hps.july.sync.mssql.adapters;

import com.hps.july.core.ColumnMap;
import com.hps.july.core.DB;
import com.hps.july.core.DefaultCollaboration;

import java.sql.Timestamp;


/**
 * @author Shafigullin Ildar
 * јдаптер дл€ переноса контрактов.
 */
public class BaseStationsAdapter extends DefaultCollaboration {

	public static class BaseStationsMap extends ColumnMap {
		BaseStationsMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("ci", "g_ci", true);
			addMap("repeater", "g_repeater", true);

			addMap("lac", "g_lac", false);
			addMap("status", "g_status", false);
			addMap("latitude", "g_latitude", false);
			addMap("longitude", "g_longitude", false);
			addMap("name", "g_name", false);
			addMap("address", "g_address", false);
			addMap("bsc", "g_bsc", false);
			addMap("frequency", "g_frequency", false);
			addMap("trx", "g_trx", false);
			addMap("rack", "g_rack", false);
			addMap("cellname", "g_cellname", false);
			addMap("config", "g_config", false);
			addMap("azimuth", "g_azimuth", false);
			addMap("height", "g_height", false);
			addMap("tilt", "g_tilt", false);
			addMap("gain", "g_gain", false);
			addMap("ant_number", "g_ant_number", false);
			addMap("ant_location", "g_ant_location", false);
			addMap("trpower", "g_trpower", false);
			addMap("form", "g_form", false);
			addMap("date", "g_date_ent", false);
			addMap("date_id", "g_date_id", false);
			addMap("name_bsc", "g_name_bsc", false);
			addMap("trx_location", "g_trx_location", false);
			addMap("diagram", "g_diagram", false);
			addMap("cabletype", "g_cabletype", false);
			addMap("rxa_m", "g_rxa_m", false);
			addMap("tx_m", "g_tx_m", false);
			addMap("rxb_m", "g_rxb_m", false);
			addMap("mha", "g_mha", false);
			addMap("db_comb", "g_db_comb", false);
			addMap("fl", "g_fl", false);
			addMap("booster", "g_booster", false);
			addMap("pos", "g_pos", false);
			addMap("pos_search", "g_pos_search", false);
			addMap("geo_zone", "g_geo_zone", false);
			addMap("cell_type", "g_cell_type", false);
			addMap("date_on", "g_date_on", false);
			addMap("environment", "g_environment", false);
			addMap("basis", "g_basis", false);
			addMap("trx_dr", "g_trx_dr", false);
			addMap("id_autor", "g_id_autor", false);
			addMap("posname", "g_posname", false);
			// олонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
			addPredefinedColumnTargetDb("modiftime", new Timestamp(new java.util.Date().getTime()));
		}
	}

	public 	BaseStationsAdapter(DB sourseTargerDB, DB sourseJoinDB, String argTableNameTARGET_DB, String argTableNameJOIN_DB, ColumnMap colMap) {
		super(sourseTargerDB, sourseJoinDB, argTableNameTARGET_DB, argTableNameJOIN_DB, colMap);
	}

	/**
	 * ќбновление записи в системе TARGET_DB
	 * »зменение стратегии переноса данных по сравнению со стандартным
	 * collaboration. —равниваем изменилась ли запись (сравниваем все пол€)
	 * ≈сли запись изменилась - проставл€ем дату изменени€
	 */
/*
	protected boolean performUpdateTargetDb(RowDBObject argObj) {
		// ѕроверим, что колонки изменились
		//System.out.println("PERFORM UPDATE");
		boolean isDifferent = false;
		RowDBObject targObj = new RowDBObject();
		Iterator it1 = argObj.getKeysColumns().entrySet().iterator();
		while (it1.hasNext()) {
			Map.Entry bcol = (Map.Entry)it1.next();
			String keyName = (String)bcol.getKey();
			Object value = bcol.getValue();
			targObj.addKeyColumn(keyName, value);
		}

		targObj = findObjectTargetDb(targObj);
		java.util.Iterator it = argObj.getColumns().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry acol = (Map.Entry)it.next();
			//System.out.println("Checking column: " + acol.getKey() + ", old=" + acol.getValue() + ", new=" + targObj.getColumns().get(acol.getKey()) );
			if (
				((acol.getValue() != null) &&
			    (acol.getValue().equals(targObj.getColumns().get(acol.getKey())))
			    ) ||
			    ( (acol.getValue() == null) && (targObj.getColumns().get(acol.getKey()) == null))
				)
					continue;
			else {
				isDifferent = true;
				break;
			}
		}
		if (isDifferent) {
			//System.out.println("DIFFERENT");
			argObj.getColumns().put("modiftime", new Timestamp(new java.util.Date().getTime()));
			return super.performUpdateTargetDb(argObj);
		} else {
			//System.out.println("DIFFERENT");
			return true;
		}
	}
*/
}
