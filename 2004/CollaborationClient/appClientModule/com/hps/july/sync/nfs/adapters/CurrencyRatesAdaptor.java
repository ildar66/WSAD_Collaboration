package com.hps.july.sync.nfs.adapters;

import com.hps.july.cdbc.lib.CDBCResultSet;
import com.hps.july.cdbc.lib.CDBCRowObject;
import com.hps.july.util.Utils;
import com.hps.july.util.StatementHelper;
import com.hps.july.core.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.ListIterator;
import java.util.Vector;

/**
 * @author Dmitry Dneprov
 * Адаптер для переноса курсов валют.
 * Сильно отличается от стандартного, т.к. курсы формируются хранимой процедурой
 */
public class CurrencyRatesAdaptor extends DefaultCollaboration {

	private java.util.Date rateDate;

	private static class CurrencyRatesMap extends ColumnMap {
		/**
		 * Конструктор
		 */
		CurrencyRatesMap() {
			super();
			//         NFS              NRI            isKey
			addMap("idcurrencynfs", null, true);
			addMap("ratetype", "ratetype", true);
			addMap("idcurrencynri", "currency", true);
			addMap("rdate", "date", true);
			addMap("rate", "value", false);
		}
	}

	public CurrencyRatesAdaptor(DB argConNRI, DB argConNFS) {
		super(argConNRI, argConNFS, "rates", "APPS.XX_NRI_CURRENCY_VW", "last_update_date", new CurrencyRatesMap());
	}

	/// Найти и заполнить поля объект из системы NFS
	protected RowDBObject findObjectJoinDb(RowDBObject argObj) {
//System.out.println("from CurrencyRatesAdapter.findObjectJoinDb argObj="+ argObj.dump());//temp
		RowDBObject result = null;
		ResultSet rs = null;
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("dd.MM.yyyy");
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT (to_date('" + df.format(rateDate) + "','dd.mm.yyyy')) AS rdate, ");
		buf.append("xxifc.xxnri_pkg.get_rate_sql('");
		buf.append(argObj.getKeysColumns().get("idcurrencynfs").toString().trim());
		buf.append("', 'RUR', (to_date('" + df.format(rateDate) + "','dd.mm.yyyy')), '1000') AS rate from dual");
		try {
			setPreparedSelectSourceDb(getSourceDbConnection().prepareStatement(buf.toString()));
		} catch (SQLException e) {
			System.out.println("Cannot Prepare SQL=" + buf.toString());
			e.printStackTrace(System.out);
		}
		try {
			if (getPreparedSelectSourceDb() != null) {
				rs = getPreparedSelectSourceDb().executeQuery();
				if (rs != null) {
					if (rs.next()) {
						result = new RowDBObject();
                        // Store keys в правильном порядке
                        result.addKeyColumn("idcurrencynfs", argObj.getKeysColumns().get("idcurrencynfs"));
                        result.addKeyColumn("idcurrencynri", argObj.getKeysColumns().get("idcurrencynri"));
                        result.addKeyColumn("ratetype", argObj.getKeysColumns().get("ratetype"));
                        result.addKeyColumn("rdate", argObj.getKeysColumns().get("rdate"));
						if (!StatementHelper.populateColumns(result, getColumnMap().getColumnMap().keySet(), rs))
							result = null;
//System.out.println("from CurrencyRatesAdapter.findObjectJoinDb result="+ result.dump());//temp
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Error executing SQL=" + buf.toString());
			System.out.println("SQL Error=" + e.getErrorCode() + ", Message=" + e.getMessage());
			//e.printStackTrace(System.out);
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			e.printStackTrace(System.out);
		}
		try {
			rs.close();
		} catch (Exception e) {
		}
		try {
			getPreparedSelectSourceDb().close();
		} catch (Exception e) {
		}
		setPreparedSelectSourceDb(null);
		return result;
	}

	/// Проверка изменения всех объектов в NFS и обновление в NRI - содержательная часть
	protected void doFindChangesAndUpdate() throws CollaborationException {
		boolean result = true;
		if ((getSourceDbConnection() != null) && (getTargetDbConnection() != null)) {
			QueryProcessing.startProcessing(getTargetDbConnection(), getQuery());
			if (getQuery().getStartDate() == null) {
				result = false;
				QueryProcessing.addLogMessage(
					getTargetDbConnection(),
					getQuery(),
					QueryProcessing.MSG_ERROR,
					"Не задана начальная дата, с которой обновлять курсы");
			}
			if (getQuery().getEndDate() == null) {
				result = false;
				QueryProcessing.addLogMessage(
					getTargetDbConnection(),
					getQuery(),
					QueryProcessing.MSG_ERROR,
					"Не задана конечная дата, до которой обновлять курсы");
			}
			if (result) {
				int defaultRateType = Utils.getNamedValueInt(getTargetDbConnection(), getQuery(), Utils.DEFAULT_RATE);
//System.out.println("defaultRateType= " + defaultRateType);//temp
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(getQuery().getStartDate());
				rateDate = gc.getTime();
				java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("MM,dd,yyyy");
				while (rateDate.getTime() <= getQuery().getEndDate().getTime()) {
					rateDate = gc.getTime();
					CDBCResultSet changesSet = new CDBCResultSet();

					changesSet
						.executeQuery(
							getTargetDbConnection(),
							"SELECT MDY("
								+ df.format(rateDate)
								+ ") AS rdate, *, "
								+ defaultRateType
								+ " ratetype FROM nfs_lnkCurrency WHERE flagmain='Y'",
							new Object[] {
					}, 0);
					ListIterator it = changesSet.listIterator();
					while (it.hasNext()) {
						CDBCRowObject ro = (CDBCRowObject) it.next();
						RowDBObject objJOIN_DB = new RowDBObject();
						populateKeysCDBC(objJOIN_DB, getColumnMap().getKeysMap().keySet(), ro);
//System.out.println("from CurrencyRatesAdapter.doFindChangesAndUpdate : objJOIN_DB=" + objJOIN_DB.dump());//temp
						if (!findInSourceUpdateTarget(objJOIN_DB)) {
							String msg =
								"Ошибка переноса данных: " + getSourceTable() + ", Ключевые поля: " + objJOIN_DB;
							System.out.println(msg);
							QueryProcessing.addLogMessage(
								getTargetDbConnection(),
								getQuery(),
								QueryProcessing.MSG_ERROR,
								msg);
							result = false;
						}
					}
					gc.add(GregorianCalendar.DAY_OF_MONTH, 1);
				}
			}
		} else {
			result = false;
		}
		compareSourceAndTargetCounts();
		if (result)
			markSuccessTransfer();
		else
			markErrorTransfer();
	}

	/// Проверка существования объекта в системе TARGET_DB
	protected boolean isExistObjectInTargetDb(RowDBObject argObj) {
		boolean result = false;
		ResultSet rs = null;
		StringBuffer buf = new StringBuffer();
		if (getPreparedSelectTargetDb() == null) {
			buf.append("SELECT * FROM ");
			buf.append(getTargetTable());
			buf.append(" WHERE ");
			buf.append(StatementHelper.generateClause(getColumnMap().getKeysMap().values(), " AND "));
//System.out.println("CurrencyRetesAd.isExistObjectInTargetDb buf.toString()" + buf.toString());//temp
			try {
				setPreparedSelectTargetDb(getTargetDbConnection().prepareStatement(buf.toString()));
			} catch (SQLException e) {
				System.out.println("isExistObjectInTargetDb.Cannot Prepare SQL=" + buf.toString());
				e.printStackTrace(System.out);
			}
		}
		Vector list = new Vector(getColumnMap().getKeysMap().keySet());
		list.removeElement("idcurrencynfs");
		if (StatementHelper.setParams(getPreparedSelectTargetDb(), list, argObj.getKeysColumns(), 1)) {
			try {
				rs = getPreparedSelectTargetDb().executeQuery();
				if (rs.next())
					result = true;
			} catch (SQLException e) {
				System.out.println("CurrencyRatesAdaptor Error executing SQL=" + buf.toString());
				System.out.println("CurrencyRatesAdaptor SQL Error=" + e.getErrorCode() + ", Message=" + e.getMessage());
				//e.printStackTrace(System.out);
			} catch (Exception e) {
				System.out.println("CurrencyRatesAdaptor ERROR: " + e.getMessage());
				e.printStackTrace(System.out);
			}
		}
		try {
			rs.close();
		} catch (Exception e) {
		}
		return result;
	}

	/// Добавление объекта в систему TARGET_DB
	protected boolean insertInTarget(RowDBObject argObj) {
//System.out.println("CurrencyRatesAdaptor.insertInTarget()");
		boolean result = false;
		StringBuffer buf = new StringBuffer();
		if (getPreparedInsertTargetDb() == null) {
			buf.append("INSERT INTO ");
			buf.append(getTargetTable());
			buf.append(" ( ");
			buf.append(StatementHelper.generateColumnNameParam(getColumnMap().getKeysMap().values(), true, true));
			buf.append(StatementHelper.generateColumnNameParam(getColumnMap().getColumnMap().values(), false, true));
			//buf.append(generateColumnNameParam(getColumnMap().predefinedColumns.keySet(), false, true));
			buf.append(" ) VALUES ( ");
			buf.append(StatementHelper.generateColumnNameParam(getColumnMap().getKeysMap().values(), true, false));
			buf.append(StatementHelper.generateColumnNameParam(getColumnMap().getColumnMap().values(), false, false));
			//buf.append(generateColumnNameParam(columnMap.predefinedColumns.values(), false, false));
			buf.append(" )");
			try {
				setPreparedInsertTargetDb(getTargetDbConnection().prepareStatement(buf.toString()));
			} catch (SQLException e) {
				System.out.println("Cannot Prepare INSERT SQL=" + buf.toString());
				e.printStackTrace(System.out);
			} catch (Exception e) {
				System.out.println("ERROR: " + e.getMessage());
				e.printStackTrace(System.out);
			}
		}
		Vector list = new Vector(getColumnMap().getKeysMap().keySet());
		list.removeElement("idcurrencynfs");

		StatementHelper.setParams(getPreparedInsertTargetDb(), list, argObj.getKeysColumns(), 1);
		StatementHelper.setParams(
			getPreparedInsertTargetDb(),
			getColumnMap().getColumnMap().keySet(),
			argObj.getColumns(),
			list.size() + 1);

		try {
			getPreparedInsertTargetDb().executeUpdate();
			result = true;
		} catch (SQLException e) {
			System.out.println("CurrencyRatesAdaptor Error executing INSERT SQL = " + buf.toString());
			System.out.println("CurrencyRatesAdaptor SQL Error=" + e.getErrorCode() + ", Message=" + e.getMessage());
		} catch (Exception e) {
			System.out.println("CurrencyRatesAdaptor ERROR: " + e.getMessage());
			e.printStackTrace(System.out);
		}
		return result;
	}

	/// Обновление записи в системе TARGET_DB
	protected boolean performUpdateTargetDb(RowDBObject argObj) {
//System.out.println("CurrencyRatesAdaptor.performUpdateTargetDb()");//temp
		boolean result = false;
		StringBuffer buf = new StringBuffer();
		if (getPreparedUpdateTargetDb() == null) {
			buf.append("UPDATE ");
			buf.append(getTargetTable());
			buf.append(" SET ");
			buf.append(StatementHelper.generateClause(getColumnMap().getColumnMap().values(), " , "));
			//buf.append(generateSetPredefinedSeparatorTARGET_DB(argObj));
			//buf.append(generateClause(getColumnMap().predefinedColumns().keySet(), " , "));
			buf.append(" WHERE ");
			buf.append(StatementHelper.generateClause(getColumnMap().getKeysMap().values(), " AND "));
			try {
				setPreparedUpdateTargetDb(getTargetDbConnection().prepareStatement(buf.toString()));
			} catch (SQLException e) {
				System.out.println("CurrencyRatesAdaptor  Cannot Prepare UPDATE SQL=" + buf.toString());
				System.out.println("CurrencyRatesAdaptor  SQL Error=" + e.getErrorCode() + ", Message=" + e.getMessage());
				//e.printStackTrace(System.out);
			} catch (Exception e) {
				System.out.println("CurrencyRatesAdaptor  ERROR: " + e.getMessage());
				e.printStackTrace(System.out);
			}
		}
		StatementHelper.setParams(getPreparedUpdateTargetDb(), getColumnMap().getColumnMap().keySet(), argObj.getColumns(), 1);

		Vector list = new Vector(getColumnMap().getKeysMap().keySet());
		list.removeElement("idcurrencynfs");
        StatementHelper.setParams(
			getPreparedUpdateTargetDb(),
			list,
			argObj.getKeysColumns(),
			getColumnMap().getColumnMap().keySet().size() + 1);
		try {
			getPreparedUpdateTargetDb().executeUpdate();
			result = true;
		} catch (SQLException e) {
			System.out.println("CurrencyRatesAdaptor  Error executing UPDATE SQL = " + buf.toString());
			System.out.println("CurrencyRatesAdaptor  SQL Error=" + e.getErrorCode() + ", Message=" + e.getMessage());
			//e.printStackTrace(System.out);
		} catch (Exception e) {
			System.out.println("CurrencyRatesAdaptor ERROR: " + e.getMessage());
			e.printStackTrace(System.out);
		}
		return result;
	}
}
