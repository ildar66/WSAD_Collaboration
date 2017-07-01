package com.hps.july.terrabyte.imp.command;

import com.hps.july.terrabyte.imp.command.AbstractCommand;

import java.sql.Connection;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 07.07.2006
 * Time: 13:01:42
 */
public class ListBsForSORMCommand extends AbstractCommand {

    public static String DIRECTORY = "DIRECTORY";
    private String directory;

    public ListBsForSORMCommand(Integer idQuery, Connection connection, Connection logConnection) {
        super(idQuery, connection, logConnection);
    }

    public Object getParameter(String name) {
        if(name.equals(DIRECTORY)) return getDirectory();
        return null;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }



}
