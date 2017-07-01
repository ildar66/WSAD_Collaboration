package com.hps.july.terrabyte.imp.command;

import java.sql.Connection;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 23.06.2005
 * Time: 15:08:22
 */
public class DrawingCommand extends AbstractCommand {

    // Чертежи_от_подрядчиков
    // Чертежи_РРС
    // Чертежи_ТУ

    private String directory = null;
    private String catalog = null;
    public static String LOG_NAME = "LOG_NAME";

    public DrawingCommand(Integer idQuery, Connection connection, Connection logConnection) {
        super(idQuery, connection, logConnection);
    }

    public DrawingCommand(Integer idQuery, Connection connection, Connection logConnection, String directory, String metka) {
        super(idQuery, connection, logConnection);
        this.directory = directory;
        setParameter(LOG_NAME, metka);
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public Object getParameter(String name) {
        if(name.equals(CommandNames.CURRENT_DIRECTORY)) return getDirectory();
        if(name.equals(CommandNames.FILE_CATALOG)) return getCatalog();
        return super.getParameter(name);
    }
}
