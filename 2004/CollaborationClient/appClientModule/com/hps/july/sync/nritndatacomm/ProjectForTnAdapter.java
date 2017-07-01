package com.hps.july.sync.nritndatacomm;

import com.hps.july.cdbc.lib.CDBCRowObject;
import com.hps.july.cdbc.lib.CDBCResultSet;
import com.hps.july.core.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;

/**
 * Date: 06.02.2007
 * Time: 13:53:36
 *
 * По параметру ищем измененный/добавленный
 * проект и заносит его в БД Tn
 *
 * @author <a href="mailto: NIzhikov@gmail.com">Izhikov Nikolay</a>
 */
public class ProjectForTnAdapter extends EmptyCollaboration {
    private final String PARAMETER_NAME = "idBsProject";
    public static final String QUERY = "INSERT INTO bsprojects(projectid, numberBs, name, address, latitude, longitude, stateProject, bandBs, applacetypeid, applaceid, antplaceid, str_transivers, numberBsc, obj, producer, casecount, tcode, cmnemonic, created, mode) \n" +
                                       "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    protected DB sconTARGET_DB;
    protected DB sconJOIN_DB;

    public ProjectForTnAdapter(DB sconTARGET_DB, DB sconJOIN_DB) {
        this.sconTARGET_DB = sconTARGET_DB;
        this.sconJOIN_DB = sconJOIN_DB;
    }

    private void insertBsProject(DB sconTARGET_db, CDBCRowObject rowObject) throws SQLException {
        Connection conn = null;
        String sql = "INSERT INTO bsprojects(projectid, numberBs, name, address, latitude, longitude, stateProject, bandBs, applacetypeid, applaceid, antplaceid, str_transivers, numberBsc, obj, producer, casecount, tcode, cmnemonic, created, mode) VALUES(";
        try {
            conn = DB.getConnection(sconTARGET_db);
            PreparedStatement preparedStatement = conn.prepareStatement(QUERY);
            for(int i=3; i<21; i++) {
                if (rowObject.getColumn("col"+i).asObject() != null) {
                    preparedStatement.setObject(i-2, rowObject.getColumn("col"+i).asObject());
                    sql = sql + rowObject.getColumn("col"+i).asObject() + ",";
                } else {
                    preparedStatement.setNull(i-2, Types.CHAR);
                    sql = sql + "NULL, ";
                }
            }

            preparedStatement.setDate(19, new java.sql.Date(new Date().getTime()));
            preparedStatement.setString(20, "A");
            preparedStatement.execute();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void doTask(Query argQry) {
        boolean isError = false;
        Connection conn = null;
        try {
            QueryProcessing.startProcessing(sconJOIN_DB, argQry);

            Integer projectId = Integer.valueOf(argQry.getParameter(PARAMETER_NAME));
            CDBCResultSet resultSet = new CDBCResultSet();

            conn =  DB.getConnection(sconJOIN_DB);
            resultSet.executeQuery(conn, "EXECUTE FUNCTION tn_getinfobsproject(?)", new Object[]{projectId}, 1);
            if (!resultSet.listIterator().hasNext()){
                isError = true;
                throw new CollaborationException("Can't find project with id = " + projectId);
            }

            CDBCRowObject rowObject = (CDBCRowObject) resultSet.listIterator().next();
            if (((Integer)rowObject.getColumn("col1").asObject()).intValue() == -1) {
                throw new CollaborationException("EXECUTE FUNCTION tn_getinfobsproject(" + projectId + ") fail with message - " + rowObject.getColumn("col2").asString());
            }

            if (sconTARGET_DB.getParameter("enabled").equals("true")) {
                insertBsProject(sconTARGET_DB, rowObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
            isError = true;
        } finally {
            if (!isError) {
                QueryProcessing.finishSuccess(conn, argQry);
            } else {
                QueryProcessing.addLogMessage(conn, argQry, QueryProcessing.MSG_ERROR, "Произошла ошибка во время добавления информации о проекте в БД Tn., idproject=" + argQry.getParameter(PARAMETER_NAME));
                QueryProcessing.finishError(conn, argQry);
            }

            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException("SQLException. message -" + e.getMessage());
            }
        }
    }

    public String toString() {
        return "ProjectForTnAdapter{" +
                "targetDb=" + sconTARGET_DB +
                ", sourceDb=" + sconJOIN_DB +
                '}';
    }
}
