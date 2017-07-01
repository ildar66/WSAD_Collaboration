package com.hps.july.terrabyte.imp.command;

import java.sql.Connection;
import java.util.HashMap;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 23.06.2005
 * Time: 15:08:43
 */
public abstract class AbstractCommand implements Command {
    private Integer idQuery = null;
    private Connection connection = null;
    private Connection logConnection = null;
    private HashMap parameters = new HashMap();

    public AbstractCommand(Integer idQuery, Connection connection, Connection logConnection) {
        this.idQuery = idQuery;
        this.connection = connection;
        this.logConnection = logConnection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Integer getIdQuery() {
        return idQuery;
    }

    public void setIdQuery(Integer idQuery) {
        this.idQuery = idQuery;
    }

    public Connection getLogConnection() {
        return logConnection;
    }

    public void setLogConnection(Connection logConnection) {
        this.logConnection = logConnection;
    }

    public Object getParameter(String name) {
        return this.parameters.get(name);
    }

    public void setParameter(String name, Object value) {
        this.parameters.put(name, value);
    }
}
