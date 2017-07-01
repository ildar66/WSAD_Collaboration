package old.com.hps.july.sync;

import java.sql.*;
import java.util.*;
import com.hps.july.cdbc.lib.*;

/**
 * @author Dmitry Dneprov
 * Обработчик очереди запросов на синхронизацию данных.
 */
public class QueryQueue {
	SyncConnection con;

	/// Конструктор
	public QueryQueue(SyncConnection argCon) {
		con = argCon;
	}

	/// Нахождение самого древнего необработанного запроса
	public Query findLatestQuery() {
		Query result = null;
		CDBCResultSet datesSet = new CDBCResultSet();
		Connection acon = con.getConnection();
		datesSet.executeQuery(
			acon,
			"SELECT * FROM JOIN_DB_query WHERE reqstate = 'R' ORDER BY posttime ASC, idquery ASC",
			null,
			0);
		ListIterator it = datesSet.listIterator();
		if (it.hasNext()) {
			result = new Query(con);
			CDBCRowObject ro = (CDBCRowObject) it.next();
			result.setReqType(ro.getColumn("reqtype").asString());
			Integer qryId = (Integer) ro.getColumn("idquery").asObject();
			result.setQueryId(qryId.intValue());
			result.setStartDate(
				(java.sql.Date) ro.getColumn("selstartdate").asObject());
			result.setEndDate(
				(java.sql.Date) ro.getColumn("selenddate").asObject());
			Integer idApp = (Integer) ro.getColumn("idapp").asObject();
			result.setIdApp(idApp.intValue());
			}
		try {
			acon.close();
		} catch (Exception e) {
		}
		return result;
	}
}
