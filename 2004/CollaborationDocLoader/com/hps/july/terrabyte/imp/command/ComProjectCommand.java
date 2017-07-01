package com.hps.july.terrabyte.imp.command;

import java.sql.Connection;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 30.06.2005
 * Time: 12:58:26
 */
public class ComProjectCommand extends AbstractCommand {

    public ComProjectCommand(Integer idQuery, Connection connection, Connection logConnection) {
        super(idQuery, connection, logConnection);
    }

}
