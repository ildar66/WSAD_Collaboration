package old.com.hps.july.sync;

/**
 * @author Dmitry Dneprov
 * Бизнес-объект системы JOIN_DB.
 */
public class JOIN_DBBusinessObject extends BusinessObject {

    public JOIN_DBBusinessObject(ColumnMap argMap) {
        super(argMap);
    }

    public void addKeyColumn(String argKey, Object argValue) {
        if (argKey != null) {
            keysJOIN_DB.put(argKey, argValue);
            // Find NRI column
            String keyNRI = (String) colMap.keysMapJOIN_DB2NRI.get(argKey);
            if (keyNRI != null)
                keysNRI.put(keyNRI, argValue);
        }
    }

    public void addColumn(String argKey, Object argValue) {
        if (argKey != null) {
            valuesJOIN_DB.put(argKey, argValue);
            // Find NRI column
            String keyNRI = (String) colMap.columnMapJOIN_DB2NRI.get(argKey);
            if (keyNRI != null)
                valuesNRI.put(keyNRI, argValue);
        }
    }
}

