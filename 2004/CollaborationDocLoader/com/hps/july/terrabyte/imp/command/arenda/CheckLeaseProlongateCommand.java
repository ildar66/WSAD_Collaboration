/*
 *  $Id: CheckLeaseProlongateCommand.java,v 1.2 2007/06/15 17:12:47 nizhikov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком    
 */
package com.hps.july.terrabyte.imp.command.arenda;

import com.hps.july.terrabyte.imp.command.AbstractCommand;

import java.sql.Connection;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.2 $ $Date: 2007/06/15 17:12:47 $
 */
public class CheckLeaseProlongateCommand extends AbstractCommand {

	public static final String COUNT_DAY_WARNING = "AddDayWarning";

	public CheckLeaseProlongateCommand(Integer idQuery, Connection connection, Connection logConnection, Integer day) {
		super(idQuery, connection, logConnection);
		setParameter(COUNT_DAY_WARNING, day);
	}

}
