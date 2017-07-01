package old.com.hps.july.sync;

/**
 * @author Dmitry Dneprov
 * Бизнес-объект системы NRI.
 */
public class NRIBusinessObject  extends BusinessObject {

    public NRIBusinessObject(ColumnMap argMap) {
        super(argMap);
    }

    public void addKeyColumn(String argKey, Object argValue) {
        keysNRI.put(argKey, argValue);
        // Find JOIN_DB column
        String keyJOIN_DB = (String) colMap.keysMapNRI2JOIN_DB.get(argKey);
        keysJOIN_DB.put(keyJOIN_DB, argValue);
    }

    public void addColumn(String argKey, Object argValue) {
        valuesNRI.put(argKey, argValue);
        // Find JOIN_DB column
        String keyJOIN_DB = (String) colMap.columnMapNRI2JOIN_DB.get(argKey);
        valuesJOIN_DB.put(keyJOIN_DB, argValue);
    }

}