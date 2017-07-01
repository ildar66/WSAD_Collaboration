package com.hps.july.terrabyte.imp.command;

import java.sql.Connection;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 23.06.2005
 * Time: 15:04:41
 */
public interface Command {
    public Integer getIdQuery();
    public Connection getConnection();
    public Connection getLogConnection();

    public Object getParameter(String name);
}
