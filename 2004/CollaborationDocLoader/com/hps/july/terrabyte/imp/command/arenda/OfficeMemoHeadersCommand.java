/*
 *  $Id: OfficeMemoHeadersCommand.java,v 1.2 2007/06/15 17:12:47 nizhikov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком    
 */
package com.hps.july.terrabyte.imp.command.arenda;

import com.hps.july.terrabyte.imp.command.AbstractCommand;

import java.sql.Connection;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 07.07.2006
 * Time: 13:01:42
 */
public class OfficeMemoHeadersCommand extends AbstractCommand {

    public static String DIRECTORY = "DIRECTORY";
    private String directory;

    public OfficeMemoHeadersCommand(Integer idQuery, Connection connection, Connection logConnection) {
        super(idQuery, connection, logConnection);
    }

    public Object getParameter(String name) {
        if(name.equals(OfficeMemoHeadersCommand.DIRECTORY)) return getDirectory();
        return null;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    



}
