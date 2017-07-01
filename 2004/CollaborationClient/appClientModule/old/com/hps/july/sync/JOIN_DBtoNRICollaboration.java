package old.com.hps.july.sync;

import com.hps.july.sync.nfs.adapters.*;
import com.hps.july.sync.*;

import java.sql.*;
import java.util.*;
import java.io.*;

/**
 * @author Ildar
 *
 * базовый абстракный класс для реализации Алгоритм переноса данных из NFS в NRI
 */
public abstract class JOIN_DBtoNRICollaboration {
	protected Properties prop;	
	
	/// Основной метод
	protected abstract void process() throws IOException, FileNotFoundException; 	

	/// Интервал между опросами таблицы запросов (в миллисекундах)
	public static final long SERVICE_TIME = 10000;

	public void processRequest(Query qry, Adaptor adaptor) {
		if (qry.isDelQuery())
			adaptor.findDeletedJOIN_DBDeleteNRI(qry);
		else
			adaptor.findChangesJOIN_DBUpdateNRI(qry);
	}

	protected void readSyncProperties(String CONFIGFILENAME)
		throws IOException, FileNotFoundException {
		prop = new Properties();
		try {
			File fl = new File(CONFIGFILENAME);
			FileInputStream inStream = new FileInputStream(fl);
			try {
				prop.load(inStream);
				prop.list(System.out);
			} catch (IOException e) {
				System.out.println("Cannot Load properties");
				throw e;
			}
		} catch (FileNotFoundException e) {
			System.out.println("Config file " + CONFIGFILENAME + " not found");
			throw e;
		}
	}
}
