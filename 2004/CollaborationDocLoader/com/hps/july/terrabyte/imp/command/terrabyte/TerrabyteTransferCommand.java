package com.hps.july.terrabyte.imp.command.terrabyte;

import com.hps.july.terrabyte.imp.command.AbstractCommand;
import com.hps.july.terrabyte.imp.command.CommandNames;

import java.sql.Connection;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 04.03.2006
 * Time: 20:11:54
 */
public class TerrabyteTransferCommand extends AbstractCommand {

    public static final String CATALOG_NAME = "ÎÝÒÑ";

    public TerrabyteTransferCommand(Integer idQuery, Connection connection, Connection logConnection) {
        super(idQuery, connection, logConnection);
    }

    public Object getParameter(String name) {
        if(name.equals(CommandNames.FILE_CATALOG)) return CATALOG_NAME;
        return null;
    }

}
