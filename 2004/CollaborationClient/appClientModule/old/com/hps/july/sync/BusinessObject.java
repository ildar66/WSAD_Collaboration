package old.com.hps.july.sync;

import java.util.*;

/**
 * @author Dmitry Dneprov
 *
 * Бизнес-объект. Содержит ключи и поля объекта
 * а также предопределенные колонки (со значениями)
 */
public abstract class BusinessObject {
    public ColumnMap colMap;
	public TreeMap keysNRI;
	public TreeMap valuesNRI;
    public TreeMap keysJOIN_DB;
    public TreeMap valuesJOIN_DB;

    public final static int KEYS_JOIN_DB = 1;
    public final static int KEYS_NRI = 2;

    public BusinessObject(ColumnMap argMap) {
        colMap = argMap;
        keysNRI = new TreeMap();
        keysJOIN_DB = new TreeMap();
        valuesNRI = new TreeMap();
        valuesJOIN_DB = new TreeMap();
    }

    /**
     * Добавление ключевой колонки в Бизнес-объект
     * Должно быть реализовано с учетом системы
     * @param argKey - наименование колонки
     * @param argValue - значение колонки
     */
    public abstract void addKeyColumn(String argKey, Object argValue);

    /**
     * Добавление колонки в Бизнес-объект
     * Должно быть реализовано с учетом системы
     * @param argKey - наименование колонки
     * @param argValue - значение колонки
     */
    public abstract void addColumn(String argKey, Object argValue);

    public String dumpKeys(int keysType) {
        StringBuffer buf = new StringBuffer();
        Set keyset = null;
        TreeMap keys = null;
        if (keysType == KEYS_JOIN_DB)
            keys = keysJOIN_DB;
        else
            keys = keysNRI;
        Iterator it = keys.keySet().iterator();
        boolean isFirst = true;
        while (it.hasNext()) {
            String key = (String)it.next();
            Object value = keys.get(key);
            if (!isFirst)
                buf.append(", ");
            isFirst = false;
            buf.append(key);
            buf.append("=");
            buf.append(value);
        }
        return buf.toString();
    }
}


