package com.hps.framework.log;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

/**
 * Осуществляет вывод в лог сообщений о ходе работы программы.
 */
public class AppLog  {
    private static int logLevel=-1;
    public static final int ERROR_LEVEL = 0;
    public static final int INFO_LEVEL = 1;
    public static final int DEBUG_LEVEL = 2;
    public static final int DEBUG_EXT_LEVEL = 3;
    private static ThreadLocal level = new ThreadLocal();

    private AppLog() {
    }

    private static int getDefaultLogLovel() {
        return INFO_LEVEL;
    }


    public static void setThreadLogLevel(int logLevel ) {
        level.set(new Integer(logLevel));
    }

    public static void setDefaultLogLevel() {
        level.set(new Integer(getDefaultLogLovel()));
    }


    private static int getLogLevel() {
        if(level.get()==null)
            return getDefaultLogLovel();
        else
            return ((Integer)level.get()).intValue();
    }

    /**
     * печатает в лог stack-trace сообщения об ошибке
     * @param e ошибка
     */
    public static void trace(Throwable e) {
        try {
              if( e instanceof SQLException ) {
                    error("code[" + ((SQLException)e).getErrorCode() + "], state[" + ((SQLException)e).getSQLState() + "] \n");
              }
              e.printStackTrace();
              System.out.println("");

//            ByteArrayOutputStream  os = new ByteArrayOutputStream();
//            PrintStream            ps = new PrintStream(os);
//            ps.print( "\n" );
//            e.printStackTrace( ps );
//
//            String msg = "";
//            if( e instanceof SQLException ) {
//                msg += "SQLException: code[" + ((SQLException)e).getErrorCode() + "], state[" + ((SQLException)e).getSQLState() + "] \n";
//            }
//            String tmp = os.toString();
//            tmp = new String( tmp.getBytes("8859_1"), "Cp1251" );
//
//            msg += tmp;
//            error( msg );
        } catch(Exception ex) {
            throw new RuntimeException( ex.toString() );
        }
    }
    
    
    /**
     * Печатает в лог текст ошибки
     * @param e ошибка
     */
    public static void error(Throwable e) {
        trace(e);
    }


    /**
     * Печатает в лог сообщение об ошибке
     * @param msg текст сообщения
     */
    public static void error(String msg) {
        print(msg, ERROR_LEVEL);
    }


    /**
     * Печатает в лог информационное сообщение
     * @param msg текст сообщения
     */
    public static void info(String msg) {
        print(msg, INFO_LEVEL);
    }

    /**
     * Печатает в лог отладочное сообщение
     * @param msg текст сообщения
     */
    public static void debug(String msg) {
        print(msg, DEBUG_LEVEL);
    }

    public static void debug(Date msg) {
        print(msg.toString(), DEBUG_LEVEL);
    }

    public static void debug(Short msg) {
        print(msg.toString(), DEBUG_LEVEL);
    }

    public static void debug(BigDecimal msg) {
        print(msg.toString(), DEBUG_LEVEL);
    }

    public static void debug(Integer msg) {
        print(msg.toString(), DEBUG_LEVEL);
    }


    public static void debugExt(String msg) {
        print(msg, DEBUG_EXT_LEVEL);
    }



    private static void print(String msg, int level) {
        try {
            if (level <= getLogLevel()) {
            //System.out.println(msg);
                System.out.print("");
                System.out.write(msg.getBytes("Cp1251"));
                System.out.println("");
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
            //throw new RuntimeException(e);
        }
    }


}
