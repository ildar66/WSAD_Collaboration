package old.com.hps.july.sync.nfs.adapters;

import java.sql.*;
import java.util.*;
import old.com.hps.july.sync.*;

import com.hps.july.cdbc.lib.*;
import com.hps.july.util.TimeCounter;

/**
 * @author Dmitry Dneprov
 * Адаптер для переноса курсов валют.
 * Сильно отличается от стандартного, т.к. курсы формируются хранимой процедурой
 */
public class CurrencyRatesAdaptor extends SimpleAdaptor {

    private java.util.Date rateDate;

    public class CurrencyRatesMap extends ColumnMap {
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

    public CurrencyRatesAdaptor(SyncConnection argConNRI, SyncConnection argConNFS) {
        super(argConNRI, argConNFS, "rates", "APPS.XX_NRI_CURRENCY_VW");
        setColumnMap(new CurrencyRatesMap());
    }

    /// Найти и заполнить поля объект из системы NFS
    protected BusinessObject findObjectNFS(BusinessObject argObj) {
        BusinessObject result = null;
        ResultSet rs = null;
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("dd.MM.yyyy");
        StringBuffer buf = new StringBuffer();
        buf.append("SELECT (to_date('" + df.format(rateDate) + "','dd.mm.yyyy')) AS rdate, ");
        //buf.append("apps.gl_currency_api.get_rate_sql('");
        buf.append("xxifc.xxnri_pkg.get_rate_sql('");
        buf.append(argObj.keysJOIN_DB.get("idcurrencynfs").toString().trim());
        buf.append("', 'RUR', (to_date('" + df.format(rateDate) + "','dd.mm.yyyy')), '1000') AS rate from dual");
        try {
            preparedSelectJOIN_DB = conJOIN_DB.prepareStatement(buf.toString());
        } catch (SQLException e) {
            System.out.println("Cannot Prepare SQL=" + buf.toString());
            e.printStackTrace(System.out);
        }
        try {
            if (preparedSelectJOIN_DB != null) {
                rs = preparedSelectJOIN_DB.executeQuery();
                if (rs != null) {
                    if (rs.next()) {
                        result = new JOIN_DBBusinessObject(argObj.colMap);
                        // Store keys from parent object
                        result.addKeyColumn("idcurrencynfs", argObj.keysJOIN_DB.get("idcurrencynfs"));
                        result.addKeyColumn("idcurrencynri", argObj.keysJOIN_DB.get("idcurrencynri"));
                        result.addKeyColumn("ratetype", argObj.keysJOIN_DB.get("ratetype"));
                        result.addKeyColumn("rdate", argObj.keysJOIN_DB.get("rdate"));
                        populateValues(result, rs);
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
        try {rs.close();} catch (Exception e) {}
        try {preparedSelectJOIN_DB.close();} catch (Exception e) {}
        preparedSelectJOIN_DB = null;
        return result;
    }

    /// Проверка изменения всех объектов в NFS и обновление в NRI - содержательная часть
    protected void dofindChangesNFSUpdateNRI() {
        boolean result = true;
        if ( (conJOIN_DB != null) && (conNRI != null)) {
            qry.startProcessing();
            if (qry.getStartDate() == null) {
                result = false;
                qry.addLogMessage(Query.MSG_ERROR, "Не задана начальная дата, с которой обновлять курсы");
            }
            if (qry.getEndDate() == null) {
                result = false;
                qry.addLogMessage(Query.MSG_ERROR, "Не задана конечная дата, до которой обновлять курсы");
            }
            if (result) {
                int defaultRateType = Utils.getNamedValueInt(conNRI, qry, Utils.DEFAULT_RATE);
                GregorianCalendar gc = new GregorianCalendar();
                gc.setTime(qry.getStartDate());
                rateDate = gc.getTime();
                java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("MM,dd,yyyy");
                while (rateDate.getTime() <= qry.getEndDate().getTime()) {
                    rateDate = gc.getTime();
                    CDBCResultSet changesSet = new CDBCResultSet();

                    changesSet.executeQuery(conNRI, "SELECT MDY(" + df.format(rateDate) + ") AS rdate, *, " +
                            defaultRateType + " ratetype FROM nfs_lnkCurrency WHERE flagmain='Y'", new Object[]{}, 0);
                    ListIterator it = changesSet.listIterator();
                    while (it.hasNext()) {
                        CDBCRowObject ro = (CDBCRowObject)it.next();
                        BusinessObject objNFS = new JOIN_DBBusinessObject(getColumnMap());
                        populateKeysCDBC(objNFS, ro);
                        if (!findJOIN_DBUpdateNRI(objNFS)) {
                            String msg = "Ошибка переноса данных: " + tableNameJOIN_DB + ", Ключевые поля: " + objNFS.dumpKeys(BusinessObject.KEYS_JOIN_DB);
                            System.out.println(msg);
                            qry.addLogMessage(Query.MSG_ERROR, msg);
                            result = false;
                        }
                    }
                    gc.add(GregorianCalendar.DAY_OF_MONTH, 1);
                }
            }
        } else {
            result = false;
        }
        compareJOIN_DB2NRICounts();
        if (result)
            markSuccessTransfer();
        else
            markErrorTransfer();
    }

}
