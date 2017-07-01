package com.hps.july.terrabyte.imp.command;

import java.sql.Connection;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 30.06.2005
 * Time: 12:58:26
 */
public class AnsimovCommand extends AbstractCommand {

    public static final String CATALOG_NAME = "����";

    public AnsimovCommand(Integer idQuery, Connection connection, Connection logConnection) {
        super(idQuery, connection, logConnection);
    }

    public Object getParameter(String name) {
        if(name.equals(CommandNames.FILE_CATALOG)) return CATALOG_NAME;
        return null;
    }

}