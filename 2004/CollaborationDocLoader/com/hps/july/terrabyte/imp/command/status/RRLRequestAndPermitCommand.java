package com.hps.july.terrabyte.imp.command.status;

import com.hps.july.terrabyte.imp.command.AbstractCommand;
import com.hps.july.terrabyte.imp.command.CommandNames;

import java.sql.Connection;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 30.06.2005
 * Time: 12:58:26
 */
public class RRLRequestAndPermitCommand extends AbstractCommand {


    public RRLRequestAndPermitCommand(Integer idQuery, Connection connection, Connection logConnection) {
        super(idQuery, connection, logConnection);
    }

}
