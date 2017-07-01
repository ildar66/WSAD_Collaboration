/*
 * Created on 23.10.2006
 *
 */
package com.hps.july.sync.beeplan.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.ColumnMap;
import com.hps.july.core.DefaultCollaboration;

/**
 * @author irogachev
 *
 */

public class ControllersAdapter extends DefaultCollaboration {

	public static class	ControllersMap extends ColumnMap {
		ControllersMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("equipment", "equipment", true);
			//
			addMap("resource", "resource", false);
			addMap("switch", "switch", false);
			addMap("number", "number", false);
			addMap("name", "name", false);
			addMap("qty_cells", "qty_cells", false);
			addMap("qty_trx", "qty_trx", false);
			addMap("qty_abis", "qty_abis", false);
			addMap("qty_a", "qty_a", false);
			addMap("con_type", "con_type", false);
			addMap("qty_stat", "qty_stat", false);
			addMap("qty_shell", "qty_shell", false);
			addMap("qty_fact_trs", "qty_fact_trs", false);
			addMap("ver_po", "ver_po", false);
			addMap("strm2ss7", "strm2ss7", false);
			addMap("load_max_day", "load_max_day", false);
			addMap("load_max_hour", "load_max_hour", false);
			addMap("traf", "traf", false);
			addMap("qty_try_term", "qty_try_term", false);
			addMap("qty_try_orig", "qty_try_orig", false);
			addMap("koef_kk", "koef_kk", false);
			addMap("qty_cell_over_tcn", "qty_cell_over_tcn", false);
			addMap("qty_cell_over_sdc", "qty_cell_over_sdc", false);
			addMap("res_tech_cap_aint", "res_tech_cap_aint", false);
			addMap("avg_w_aint_drop", "avg_w_aint_drop", false);
			addMap("avg_w_aint_over", "avg_w_aint_over", false);
			addMap("avg_w_proc_dc_sdc", "avg_w_proc_dc_sdc", false);
			addMap("avg_w_proc_dc_tch", "avg_w_proc_dc_tch", false);
			addMap("comment_ctrl", "comment_ctrl", false);
			addMap("mfs_num", "mfs_num", false);
			addMap("omcr_num", "omcr_num", false);
			addMap("lac_numbers", "lac_numbers", false);
			addMap("trxfr_qty_max", "trxfr_qty_max", false);
			addMap("trxfr_qty", "trxfr_qty", false);
			addMap("trx_use_percent", "trx_use_percent", false);
			addMap("atr_qty_max", "atr_qty_max", false);
			addMap("trxhr_qty", "trxhr_qty", false);
			addMap("atr_qty", "atr_qty", false);
			addMap("aint_qty", "aint_qty", false);
			addMap("hway_qty_voice", "hway_qty_voice", false);
			addMap("hway_qty_gprs", "hway_qty_gprs", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public 	ControllersAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "smedvetskiy.controllers", "controllers", null, new ControllersMap(), dbSource);
	}

}


