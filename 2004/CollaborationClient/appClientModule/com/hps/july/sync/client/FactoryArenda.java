/*
 *  $Id: FactoryArenda.java,v 1.4 2007/06/15 17:12:33 nizhikov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.july.sync.client;

import com.hps.july.core.Collaboration;
import com.hps.july.core.DB;
import com.hps.july.core.Query;
import com.hps.july.sync.arenda.adapters.ArendaTaskLeaseProlongateAdapter;

import java.util.Properties;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.4 $ $Date: 2007/06/15 17:12:33 $
 */
public class FactoryArenda extends AbstractFactory {

	public static final String QRY_CHECKARENDAPROLONGATE = "CHECKARENDALEASEPROLONGATE";

	public Collaboration getAdapter(Query qry, DB logDB, Properties prop) {
		DB targetDB = new DB(prop, "NRI");
		Collaboration adaptor = null;
		if (qry.isQueryType(QRY_CHECKARENDAPROLONGATE)) {
			adaptor = new ArendaTaskLeaseProlongateAdapter(targetDB, logDB, prop);
		}
		return adaptor;
	}
}
