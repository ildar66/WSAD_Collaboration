package old.com.hps.july.sync.msaccess.adapters;

import java.sql.*;
import old.com.hps.july.sync.*;
import com.hps.july.cdbc.lib.CDBCResultSet;

/**
 * @author Ildar
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ForNRITableAdaptor extends SimpleAdaptor {
    public ForNRITableAdaptor(SyncConnection argConNRI, SyncConnection argConNFS) {
        super(argConNRI, argConNFS, "dbspositions", "Для_NRI_таблица");
        setColumnMap(new ForNRITableMap());
    }
    private class ForNRITableMap extends ColumnMap {
        /**
         * Конструктор
         */
        ForNRITableMap() {
            super();
            // addMap( MSAccess, NRI, isKey)
            addMap("Код", "idrecord", true);
            addMap("№DAMPS", "dampsid", false);
            addMap("№GSM", "gsmid", false);
            //addMap("%WLAM", "wlanid", false);
            addMap("Название", "name", false);
            addMap("Название2", "name2", false);
            addMap("Разм_БС", "apparattype", false);
            addMap("Завод", "containertype", false);
            addMap("Тип_БС", "placetype", false);
            addMap("Где_БС", "apparatplace", false);
            addMap("Разм_опор", "oporaplace", false);
            addMap("Сущ", "isouropora", false);
            addMap("Тип_опор", "oporatype", false);
            addMap("Где_ант", "antennaplace", false);
            addMap("Высота", "heightopora", false);
            addMap("Отв_Фиш", "fiootvexpl", false);
            addMap("Тав№", "tabnumotvexpl", false);
            addMap("Эфир", "statebs", false);
            addMap("Демонтаж", "datederrick", false);
            addMap("Выезд", "dateonsitereview", false);
            addMap("Дата_марш_карт", "lastupdmarshkarta", false);
            addMap("Дата_список", "lastupdlistprohod", false);
            addMap("Посл_изм", "lastupdposition", false);                                                           

            // Колонки, предопределенные в таблице NRI
            //addPredefinedColumnNRI("", "");
        }
    }    
}
