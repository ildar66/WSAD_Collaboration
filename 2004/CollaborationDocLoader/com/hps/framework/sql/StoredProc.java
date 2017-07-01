package com.hps.framework.sql;

//import com.hps.veksel.ejb.utils.Db2MessageEncoder;
//import com.hps.veksel.constants.Db2ErrorProcessor;

import com.hps.beeline.Config;
import com.hps.framework.exception.BaseException;
import com.hps.framework.log.AppLog;
import com.hps.framework.utils.AdvHashMap;
import com.hps.framework.utils.BeanFactory;

import javax.naming.NamingException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * Представляет собой обертку для вызова хранимых процедур.
 */
public class StoredProc {
    public static final Integer RESULT1 = new Integer(1);
    public static final Integer RESULT2 = new Integer(2);
    public static final Integer RESULT3 = new Integer(3);
    public static final Integer RESULT4 = new Integer(4);
    public static final Integer RESULT5 = new Integer(5);
    public static final Integer RESULT6 = new Integer(6);
    public static final Integer RESULT7 = new Integer(7);
    public static final Integer RESULT8 = new Integer(8);
    public static final Integer RESULT9 = new Integer(9);
    public static final Integer RESULT10 = new Integer(10);
    public static final Integer RESULT11 = new Integer(11);
    public static final Integer RESULT12 = new Integer(12);
    public static final Integer RESULT13 = new Integer(13);
    public static final Integer RESULT14 = new Integer(14);
    public static final Integer RESULT15 = new Integer(15);

    private List  params = new ArrayList();
    private List  paramTypes = new ArrayList();
    private List  outParamTypes = new ArrayList();
    private List  outParamNumbers = new ArrayList();

    private Map outResults = new AdvHashMap();

    private String  sql    = null;

    private Connection         conn = null;
    private PreparedStatement  stmt = null;
    //private DataSource         dsrc = null;
    private String             dsrc_name = null;

    public void clearInfo() {
        params.clear();
        paramTypes.clear();
    }

    private String getErrorMessage(Exception err) {
        return err.getMessage();
    }

    /**
     * Обработать Exception, в случае если Exception не фатальный, 
     * то сгенерировать соответсвующий NonFatalUserException  
     * @param err Exception ошибки
     */
    private void processException(Exception err) throws BaseException {
        System.out.println("error occured during executing sql!!!!!!");
        System.out.println("sql='"+this.sql+"'");
        for(int i=0;i<params.size();i++) {
            System.out.println("param "+(i+1)+" value='"+params.get(i)+"'");
        }

        if(stmt!=null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        if(err instanceof SQLException) {
            err.printStackTrace();
            SQLException sqlErr = (SQLException) err;
            System.out.println("user_error_code="+sqlErr.getErrorCode());
            System.out.println("sql_state="+sqlErr.getSQLState());
            throw new BaseException("sql error",err);
        }else
        if(err instanceof BaseException) {
            throw (BaseException)err;
        } else{
			AppLog.error( err );
            throw new BaseException("internal error",err);
        }    
    }
    
    /**
     * Установить параметр хранимой процедуры
     * @param val значение параметра
     * @param index номер параметра
     */
    public void setParam( int index, Object val) throws BaseException {
        if(index==1)
            clearInfo();

        if(val!=null)
            setParam(index,val,Types.CHAR);
        else
            throw new BaseException("Error: NULL param allowed only with type !!! Please use another method setParam!!!", null);
    }

    public void setObject( int index,Object val) throws BaseException {
        setParam(index, val);
    }

    /**
     * Установить параметр хранимой процедуры
     * @param val значение параметра
     * @param index номер параметра
     * @param type тип параметра
     */
    public void setParam( int index,Object val, int type)  {
        if(index==1)
            clearInfo();
        params.add( val );
        paramTypes.add(new Integer(type));
    }

    public void setObject( int index,Object val, int type)  {
        setParam(index, val, type);
    }

    public void registerOutParam( int type,int index) {
        if(index==1)
            clearInfo();

        this.outParamTypes.add(new Integer(type));
        this.outParamNumbers.add(new Integer(index));
    }

    private void initOutParams() throws Exception {
        Integer      number = null;
        Integer      type = null;
        for( int i = 0;i<outParamTypes.size(); i++ ) {
            number = (Integer) outParamNumbers.get(i);
            type = (Integer) outParamTypes.get(i);

            ((CallableStatement )stmt).registerOutParameter(number.intValue(),type.intValue());
			AppLog.debug("Register output parameter number="+number.intValue()+" type="+type.intValue());
        }
    }

    private void readOutParams() throws Exception {
        Integer      number = null;
        Integer      type = null;
        for( int i = 0;i<outParamTypes.size(); i++ ) {
            number = (Integer) outParamNumbers.get(i);
            type = (Integer) outParamTypes.get(i);

            Object value = ((CallableStatement )stmt).getObject(number.intValue());
            this.outResults.put(number,value);
            AppLog.debug("put out param, number='"+number+"' value='"+value+"'");
        }
    }

    /**
     * Проинициализировать параметры хранимой процедуры
     * @throws Exception
     */
    private void initParams() throws Exception {
        Iterator  en = params.iterator();
        Object       param = null;
        for( int i = 1; en.hasNext(); i++ ) {
            param = en.next();
            if(param!=null) {
                if(param instanceof String)
					AppLog.debug("setting SQL parameter #" + i + " = '" + param.toString()+"'");
                else
					AppLog.debug("setting SQL parameter #" + i + " = " + param.toString());
            } else
				AppLog.debug("setting SQL parameter #" + i + " = null");
            
            if(param instanceof java.util.Date) 
                param = new java.sql.Date(((java.util.Date)param).getTime());

            if(param instanceof InputStream) {
                stmt.setBinaryStream(i, (InputStream)param, 1000000);
            }else if(param instanceof BinaryStreamData) {
                BinaryStreamData data = (BinaryStreamData) param;
                stmt.setBinaryStream(i, data.getInputStream(), data.getLength());
            } else if(param!=null) {
                try{
                    stmt.setObject( i, param );
                }catch(SQLException err) {
                    System.out.println("exception during set parameter "+i+" value="+param);
                    throw err;
                }
            } else {
                stmt.setNull(i,((Integer)paramTypes.get(i-1)).intValue());
            }
        }
    }

    private void initConnection() throws SQLException, NamingException, BaseException {
         if(conn==null)
            conn = Config.getInstance().getDatabaseConnection();
//        if(conn==null) {
//            this.dsrc = (DataSource)(new InitialContext()).lookup( dsrc_name );
//            conn = dsrc.getConnection();
//            conn.setAutoCommit(false);
//            try{
//                if(conn.getTransactionIsolation()!=Connection.TRANSACTION_READ_UNCOMMITTED)
//                    conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
//            }catch(SQLException err) {
//                AppLog.debug("tried to set transaction isolation - not success");
//            }
//        }
    }

    /**
     * Проинициализировать вызов хранимой процедуры
     * @throws Exception
     */    
    private void initStmt() throws Exception {
        initConnection();
		AppLog.debug( sql );
        if(stmt==null)
            stmt = conn.prepareStatement( sql);
        initParams();     
    }
    
    /**
     * Проинициализировать вызов хранимой процедуры вызова
     * @throws Exception
     */ 
    private void initCallStmt() throws Exception {
        initConnection();
		AppLog.debug( sql );
        if(stmt==null)
            stmt = conn.prepareCall( sql );
        initParams();     
    }
    
    /**
     * Прочитать результат работы хранимой процедуры
     * @param rset результат запроса
     * @return таблица значений
     * @throws Exception
     */
    private Map readData(ResultSet     rset) throws Exception {
        if(rset==null || rset.next() == false ) {
            AppLog.debug("No result of sql query");
            return null;              // empty table
        }


        Map  result = new AdvHashMap();
         // obtain column names
        //
        Collection  columns = new ArrayList();
        ResultSetMetaData  rmdt    = rset.getMetaData();
        for( int i=1; i <= rmdt.getColumnCount(); i++ ) {
            columns.add( rmdt.getColumnName(i) );
        }

        // fill-in result
        //
        result.clear();
        String       key   = null;
        Object       value = null;
        Iterator  keys  = columns.iterator();
        while( keys.hasNext() ) {
            key   = (String)keys.next();
            value = rset.getObject( key );
            if(!key.equals("(expression)")) {
                AppLog.debug("getting SQL result key=" + key + " value = " + value );
                result.put( key, value );
            }
        }

        AppLog.debug("return column count="+columns.size());
        for(int i=1;i<=columns.size();i++) {
            value = rset.getObject(i);
            result.put(new Integer(i),value);
            AppLog.debug("getting SQL result #" + i + " = " + value );
        }
        
        rset.close();

        return result;            
    }

    public static Collection readFullData(ResultSet rset, String beanClassName) throws SQLException, BaseException {
        if(rset==null )
            return new ArrayList();              // empty table


         // obtain column names
        //
        Collection  columns = new ArrayList();
        ResultSetMetaData  rmdt    = rset.getMetaData();
        for( int i=1; i <= rmdt.getColumnCount(); i++ ) {
            columns.add( rmdt.getColumnName(i) );
        }

        Collection result = new ArrayList();
        AdvHashMap record;

        while(rset.next()) {
            // fill-in result
            //
            record = new AdvHashMap();

            String       key   = null;
            Object       value = null;
            Iterator  keys  = columns.iterator();
            boolean hasValueNotNull = false;
            for(int i=1;i<=columns.size();i++) {
                key   = (String)keys.next();
                value = rset.getObject( i );
                record.put( key, value );
                record.put( new Integer(i), value );
                //AppLog.debug("put, name='"+key+"' value='"+value+"'");
                //AppLog.debug("put, name='r"+i+"' value='"+value+"'");
                if(value!=null)
                    hasValueNotNull = true;
            }

//            for(int i=1;i<=columns.size();i++) {
//                value = rset.getObject(i);
//                record.put(new Integer(i),value);
//                record.put("r"+i,value);
//                //AppLog.debug("put, r"+i+"' value='"+value+"'");
//            }
            //AppLog.debug("add record");
            if(hasValueNotNull) {
                if(beanClassName==null)
                    result.add(record);
                else
                    result.add(BeanFactory.getInstance().createBean(beanClassName, record));
            }
        }

        rset.close();
        AppLog.debug("Readed rows number="+result.size());
        return result;
    }

    /**
     * execute query and return a result (AdvHashMap)
     */
    public Map executeQuery() throws BaseException {
        Map  result = new AdvHashMap();
        ResultSet     rset = null;

        try {            
            initStmt();
			AppLog.debug("Execute query["+sql+"]");
            rset = stmt.executeQuery();
            result = readData(rset);
            return result;
        } catch( Exception e ) {
            processException(e);
            return null;
        }
    }

     /**
     * execute query and return a result (Object)
     */
    public Object executeQuery(String beanClassName) throws BaseException {
        AdvHashMap  result = new AdvHashMap();
        ResultSet     rset = null;

        try {
            initStmt();
			AppLog.debug("Execute query["+sql+"]");
            rset = stmt.executeQuery();
            Map record =  readData(rset);
            if(record==null)
                return null;
            return BeanFactory.getInstance().createBean(beanClassName, record);
        } catch( Exception e ) {
            processException(e);
            return null;
        }
    }

    /**
     * execute query and return full result (Vector)
     */
    public Collection executeQueryVector() throws BaseException {
        try {
            initStmt();
			AppLog.debug("Execute query["+sql+"]");
            ResultSet rset = stmt.executeQuery();
            return readFullData(rset, null);
        } catch( Exception e ) {
            processException(e);
            return null;
        }
    }

    public ResultSet executeQueryClassic() throws BaseException {
            try {
                initStmt();
                AppLog.debug("Execute query["+sql+"]");
                ResultSet rset = stmt.executeQuery();
                return rset;
            } catch( Exception e ) {
                processException(e);
                return null;
            }
        }


    public Collection executeQueryVector(String beanClassName) throws BaseException {
        try {
            initStmt();
			AppLog.debug("Execute query["+sql+"]");
            ResultSet rset = stmt.executeQuery();            
            return readFullData(rset, beanClassName);
        } catch( Exception e ) {
            processException(e);
            return null;
        }
    }



    /**
     * execute update and return the count of records changed
     */
    public int executeUpdate() throws BaseException {
        try {
            initStmt();
            int result = stmt.executeUpdate();
            return result;
        } catch( Exception e ) {
            processException(e);
            return 0;
        }
    }

    public int executeFalseUpdate() throws BaseException {
        try {
            //initStmt();
            return 0;
        } catch( Exception e ) {
            processException(e);
            return 0;
        }
    }

    /**
     * execute insert and return the id of inserted record
     */
    public int executeInsert(String tableName) throws Exception {
        try {
            initStmt();
            stmt.executeUpdate();
            stmt = conn.prepareStatement("select dbinfo('sqlca.sqlerrd1') as last_inserted from systables where tabname='" + tableName + "'");
            ResultSet  rset = stmt.executeQuery();
            if( !rset.next() )
                throw new Exception("record inserted, but id can't be determined");
            return rset.getInt("last_inserted");
        } catch( Exception e ) {
            processException(e);
            return 0;
        }
    }
    
    /**
     * execute call and return result
     */
    public Collection executeComplexFunctionCall()throws BaseException {
        try {
            this.initCallStmt();
            this.initOutParams();
            stmt.execute();
            this.readOutParams();
            ResultSet rset = stmt.getResultSet();
            return readFullData(rset, null);
        } catch( Exception e ) {
            processException(e);
            return null;
        }
    }

    public Map executeFunctionCall() throws BaseException {
        try {
            this.initCallStmt();
            this.initOutParams();
            stmt.execute();
            this.readOutParams();
            ResultSet rset = stmt.getResultSet();

            return readData(rset);
        } catch( Exception e ) {
            processException(e);
            return null;
        }
    }

    public Map getOutResults() {
        return outResults;
    }



    /**
     * execute insert by call and return the id of inserted record
     */
    
    public Integer executeInsertStoredProc() throws Exception {                    
        Map result = executeQuery();
        Integer resultInt = (Integer)result.get(RESULT1);
        return resultInt;
    }

    /**
     * plain - indicates whether sql is plain statement or sp name
     */
    public StoredProc(String sql)   {
        this.sql = sql;
    }

    public StoredProc(String sql, Connection connection)   {
        this(sql);
        this.conn = connection;
    }

    public Connection getConnection() {
        return conn;
    }

    public void close() throws BaseException {
        try{
            if(stmt!=null)
                this.stmt.close();
            this.stmt = null;
            this.conn = null;
        } catch( Exception e ) {
            processException(e);
        }
    }
}
