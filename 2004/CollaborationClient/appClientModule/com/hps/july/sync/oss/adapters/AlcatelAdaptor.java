package com.hps.july.sync.oss.adapters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ListIterator;
import java.util.StringTokenizer;

import com.hps.july.cdbc.lib.CDBCResultSet;
import com.hps.july.cdbc.lib.CDBCRowObject;
import com.hps.july.core.Query;
import com.hps.july.core.QueryProcessing;
import com.hps.july.core.DB;
import com.hps.july.core.ColumnMap;

/**
 * @author Dmitry Dneprov
 * Адаптер для загрузки данных от оборудования Alcatel.
 */
public class AlcatelAdaptor extends OSSAdaptor {

	private String strAlcatelARIEDataDir;
	private String strAlcatelACIEDataDir;
	private Integer plmn;

	/**
	 * Конструктор по-умолчанию
	 * @author dima
	 *
	 */
	private static class ProcMap extends ColumnMap {
		ProcMap() {
			super();
		}
	}

	/**
	 * Конструктор с параметрами
	 * @param targetDB
	 * @param sourceDB
	 * @param sconLOG_DB
	 * @param argAlcatelARIEDataDir
	 * @param argAlcatelACIEDataDir
	 * @param argPlmn
	 */
	public AlcatelAdaptor(DB targetDB, DB sourceDB, DB sconLOG_DB,
			String argAlcatelARIEDataDir, String argAlcatelACIEDataDir, Integer argPlmn) {
		super(targetDB, sourceDB, "dummy", "dummy", null, new ProcMap(), sconLOG_DB);
		strAlcatelARIEDataDir = argAlcatelARIEDataDir;
		strAlcatelACIEDataDir = argAlcatelACIEDataDir;
		plmn = argPlmn;
	}


	/**
	 * Очистка предыдущих данных загрузки за туже дату
	 * @param conn
	 * @param dataLoadID
	 * @return
	 */
	protected boolean ClearLoad(Connection conn, Integer dataLoadID) {
		boolean result = true;
		CDBCResultSet rs = new CDBCResultSet();
		if (!rs.executeUpdate(conn, "DELETE FROM alcatelequipdata WHERE dataheadid IN (SELECT eh.dataheadid FROM alcatelequipdataheader eh WHERE eh.dataloadid = ?)", new Object []{dataLoadID})) {
			result = false;
			System.out.println("ALCATEL: Cannot delete alcatelequipdata");
			System.out.println("ALCATEL: Mark load as FAILED!");
		} else {
			if (!rs.executeUpdate(conn, "DELETE FROM alcatelequipdataheader WHERE dataloadid = ?", new Object []{dataLoadID})) {
				result = false;
				System.out.println("ALCATEL: Cannot delete alcatelequipdataheader");
				System.out.println("ALCATEL: Mark load as FAILED!");
			} else {
				if (!rs.executeUpdate(conn, "DELETE FROM alcatelbts WHERE dataloadid = ?", new Object []{dataLoadID})) {
					result = false;
					System.out.println("ALCATEL: Cannot delete alcatelbts");
					System.out.println("ALCATEL: Mark load as FAILED!");
				} else {
					if (!rs.executeUpdate(conn, "DELETE FROM alcatelsector WHERE dataloadid = ?", new Object []{dataLoadID})) {
						result = false;
						System.out.println("ALCATEL: Cannot delete alcatelsector");
						System.out.println("ALCATEL: Mark load as FAILED!");
					} else {
						result = true;
					}
				}
			}
		}

		return result;
	}

	/**
	 * Создание заголовка файла
	 * @param conn
	 * @param idLoad
	 * @param argFilename
	 * @return
	 */
	protected Integer CreateEquipmentHeader(Connection conn, Integer idLoad, String argFilename) {
		Integer result = null;
		CDBCResultSet rs = new CDBCResultSet();
		if (rs.executeUpdate(conn, "INSERT INTO alcatelequipdataheader(dataloadid, filename) VALUES(?, ?)",
				new Object []{idLoad, argFilename})) {
			rs.executeQuery(conn, "SELECT DBINFO('sqlca.sqlerrd1') serres FROM dummy",
				new Object []{}, 1);
			ListIterator it = rs.listIterator();
			if (it.hasNext()) {
				CDBCRowObject ro = (CDBCRowObject)it.next();
				result = (Integer)ro.getColumn("serres").asObject();
			}
		} else {
			System.out.println("Cannot create alcatelequipdataheader");
		}

		return result;
	}

	/**
	 * Определение признака конца файла
	 * В первой позиции - символ E
	 * @param s
	 * @return
	 */
	boolean isEndMarker(String s) {
		if (s.length() > 1) {
			return s.startsWith("E");
		}
		return false;
	}

	/**
	 * Обработка строки комментариев - извлечение полезных полей
	 * @param conn
	 * @param qry
	 * @param headerId
	 * @param s
	 * @return
	 */
	boolean ProcessCommentLine(Connection conn, Query qry, Integer headerId, String s) {
		boolean result = true;

		CDBCResultSet rs = new CDBCResultSet();
		if (s.startsWith("C-OrderData-NetId")) {
			if (s.length() >= 29) {
				String netID = s.substring(28);
				if (!rs.executeUpdate(conn, "UPDATE alcatelequipdataheader SET netid = ? WHERE dataheadid = ?", new Object []{netID, headerId})) {
					System.out.println("OSS:Alcatel:Cannot read NetID: " + s);
					QueryProcessing.addLogMessage(conn, qry, QueryProcessing.MSG_ERROR, "Cannot read NetID");
					//result = false;
				}
			}
		} else if (s.startsWith("C-OrderData-Cypher")) {
			if (s.length() >= 29) {
				String cypher = s.substring(28);
				if (!rs.executeUpdate(conn, "UPDATE alcatelequipdataheader SET cypher = ? WHERE dataheadid = ?", new Object []{cypher, headerId})) {
					System.out.println("OSS:Alcatel:Cannot read Cypher: " + s);
					QueryProcessing.addLogMessage(conn, qry, QueryProcessing.MSG_ERROR, "Cannot read Cypher");
					//result = false;
				}
			}
		} else if (s.startsWith("C-OrderData-Location")) {
			if (s.length() >= 29) {
				String location = s.substring(28);
				if (!rs.executeUpdate(conn, "UPDATE alcatelequipdataheader SET location = ? WHERE dataheadid = ?", new Object []{location, headerId})) {
					System.out.println("OSS:Alcatel:Cannot read Location: " + s);
					QueryProcessing.addLogMessage(conn, qry, QueryProcessing.MSG_ERROR, "Cannot read Location");
					//result = false;
				}
			}
		} else if (s.startsWith("C-OrderData-NetElement")) {
			if (s.length() >= 29) {
				String netelem = s.substring(28);
				if (!rs.executeUpdate(conn, "UPDATE alcatelequipdataheader SET netelement = ? WHERE dataheadid = ?", new Object []{netelem, headerId})) {
					System.out.println("OSS:Alcatel:Cannot read NetElement: " + s);
					QueryProcessing.addLogMessage(conn, qry, QueryProcessing.MSG_ERROR, "Cannot read NetElement");
					//result = false;
				}
			}
		} else if (s.startsWith("C-OrderData-Time")) {
			if (s.length() >= 29) {
				String sdata = s.substring(28);
				SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
				java.sql.Timestamp ts;
				try {
					ts = new java.sql.Timestamp(df.parse(sdata).getTime());
					if (!rs.executeUpdate(conn, "UPDATE alcatelequipdataheader SET loadtime = ? WHERE dataheadid = ?", new Object []{ts, headerId})) {
						System.out.println("OSS:Alcatel:Cannot read Time: " + s);
						QueryProcessing.addLogMessage(conn, qry, QueryProcessing.MSG_ERROR, "Cannot read Time");
						//result = false;
					}
				} catch (ParseException e) {
					System.out.println("OSS:Alcatel:Cannot read Time: " + s);
					QueryProcessing.addLogMessage(conn, qry, QueryProcessing.MSG_ERROR, "Cannot read Time");
					//e.printStackTrace(System.out);
					//result = false;
				}
			}
		} else if (s.startsWith("C-FirstCommDate")) {
			if (s.length() >= 29) {
				String sdata = s.substring(28);
				SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
				java.sql.Date ts;
				try {
					ts = new java.sql.Date(df.parse(sdata).getTime());
					if (!rs.executeUpdate(conn, "UPDATE alcatelequipdataheader SET firstcommdate = ? WHERE dataheadid = ?", new Object []{ts, headerId})) {
						System.out.println("OSS:Alcatel:Cannot update FirstCommDate: " + s);
						QueryProcessing.addLogMessage(conn, qry, QueryProcessing.MSG_ERROR, "Cannot update FirstCommDate");
						System.out.println("ALCATEL: Mark load as FAILED!");
						result = false;
					}
				} catch (ParseException e) {
					System.out.println("OSS:Alcatel:Cannot read FirstCommDate: " + s);
					QueryProcessing.addLogMessage(conn, qry, QueryProcessing.MSG_ERROR, "Cannot read FirstCommDate");
					//e.printStackTrace(System.out);
					//result = false;
				}
			}
		} else if (s.startsWith("C-OMC-comment")) {
			int startInd = s.indexOf('\t');
			int endInd = s.indexOf('\t', startInd+1);
			String bscuserlabel = s.substring(startInd+1, endInd);
			Integer bscId = extractBSCNumber(bscuserlabel);
			String label = "BTS user label:";
			int startInd2 = s.indexOf(label);
			startInd2 = s.indexOf('\t', startInd2);
			String btsuserlabel = null;
			if (startInd2 != -1)
				btsuserlabel = s.substring(startInd2+1);
			Integer gsmId = extractGSMNumber(btsuserlabel);
			if (!rs.executeUpdate(conn, "UPDATE alcatelequipdataheader SET bscuserlabel = ?, btsuserlabel = ?, bscid = ?, gsmid = ? WHERE dataheadid = ?",
					new Object []{bscuserlabel, btsuserlabel, bscId, gsmId, headerId})) {
				System.out.println("OSS:Alcatel:Cannot update OMC-comment: " + s);
				QueryProcessing.addLogMessage(conn, qry, QueryProcessing.MSG_ERROR, "Cannot update OMC-comment");
				System.out.println("ALCATEL: Mark load as FAILED!");
				result = false;
			}
		}

		return result;
	}

	/**
	 * Выделение из строки номера контроллера
	 * @param bscuserlabel
	 * @return
	 */
	Integer extractBSCNumber(String bscuserlabel) {
		int gendInd = bscuserlabel.indexOf('_');
		String sbscid = null;
		if (gendInd != -1)
			sbscid = bscuserlabel.substring(0, gendInd);
		else
			sbscid = bscuserlabel;
		int bscId = 0;
		try {
			bscId = Integer.parseInt(sbscid);
		} catch (NumberFormatException e) {
			System.out.println("Cannot Extract BSC number from string: " + bscuserlabel);
		}
		Integer bscIdI = null;
		if (bscId != 0)
			bscIdI = new Integer(bscId);
		return bscIdI;
	}

	/**
	 * Выделение из строки номера GSM
	 *   оказалось это вовсе не тот номер GSM (не общепринятый)
	 * @param btsuserlabel
	 * @return
	 */
	Integer extractGSMNumber(String btsuserlabel) {
		int gendInd = btsuserlabel.lastIndexOf('_');
		String sgsmid = null;
		if (gendInd != -1)
			sgsmid = btsuserlabel.substring(gendInd+1);
		else
			sgsmid = btsuserlabel;
		int gsmId = 0;
		try {
			gsmId = Integer.parseInt(sgsmid);
		} catch (NumberFormatException e) {
			System.out.println("Cannot Extract GSM number from string: " + btsuserlabel);
		}
		Integer gsmIdI = null;
		if (gsmId != 0)
			gsmIdI = new Integer(gsmId);
		return gsmIdI;
	}

	/**
	 * Исключение пробелов из середины строки
	 * @param s
	 * @return
	 */
	String excludeSpaces(String s) {
		StringBuffer sb = new StringBuffer(s);
		int i = 0;
		while (true) {
			if (i >= sb.length())
				break;
			if (sb.charAt(i) == ' ')
				sb.deleteCharAt(i);
			else
				i = i + 1;
		}
		return sb.toString();
	}

	/**
	 * Установка соответствия между ресурсом Alcatel и типом оборудования (ресурсом NRI)
	 */
	protected boolean addResource(Connection conn, String argModel) {
		boolean result = true;
		CDBCResultSet rs = new CDBCResultSet();
		rs.executeQuery(conn, "SELECT * FROM alcres2nrires WHERE partnumber = ?",
			new Object[] {argModel}, 1);
		ListIterator it = rs.listIterator();
		if (!it.hasNext()) {
			result = rs.executeUpdate(conn, "INSERT INTO alcres2nrires(partnumber) " +
				"VALUES(?)", new Object[] {argModel});
			if (!result) {
				System.out.println("ALCATEL: Mark load as FAILED!");
			}
		}
		return result;
	}

	/**
	 * Обработка строки с данными
	 * @param conn
	 * @param qry
	 * @param headerId
	 * @param s
	 * @return
	 */
	boolean ProcessDataLine(Connection conn, Query qry, Integer headerId, String s) {
		boolean result = true;

		//System.out.println("Processing data line for headerId: " + headerId);
		CDBCResultSet rs = new CDBCResultSet();
		int level = Integer.parseInt(s.substring(0, 1));
		String sdatestr = s.substring(3, 19);
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		java.sql.Timestamp scandate = null;
		try {
			scandate = new java.sql.Timestamp(df.parse(sdatestr).getTime());
		} catch (ParseException e) {
			System.out.println("OSS:Alcatel:Cannot read ScanDate: " + s);
			QueryProcessing.addLogMessage(conn, qry, QueryProcessing.MSG_ERROR, "Cannot read ScanDate");
			e.printStackTrace(System.out);
			System.out.println("ALCATEL: Mark load as FAILED!");
			result = false;
		}
		String labelgen = s.substring(24, 25);
		int rack = Integer.parseInt(s.substring(29, 32).trim());
		int shelf = Integer.parseInt(s.substring(36, 39).trim());
		int slot = Integer.parseInt(s.substring(42, 45).trim());
		String mnemonic = s.substring(48, 56).trim();
		String serial = s.substring(72, 84).trim();
		String partNumber = excludeSpaces(s.substring(88, 102).trim());
		String ics = s.substring(104, 107).trim();
		SimpleDateFormat df1 = new SimpleDateFormat("dd.MM.yyyy");
		java.sql.Date endofwarrdate = null;
		java.sql.Date firstcommdate = null;
		java.sql.Date lastrepdate = null;
		try {
			endofwarrdate = new java.sql.Date(df.parse(s.substring(107, 117)).getTime());
		} catch (ParseException e) {
			// Ignore errors
		}
		try {
			firstcommdate = new java.sql.Date(df.parse(s.substring(118, 128)).getTime());
		} catch (ParseException e) {
			// Ignore errors
		}
		try {
			lastrepdate = new java.sql.Date(df.parse(s.substring(129, 139)).getTime());
		} catch (ParseException e) {
			// Ignore errors
		}
		if (!rs.executeUpdate(conn, "INSERT INTO alcatelequipdata (dataheadid, level, scandate, labelgen, rack, shelf, slot, mnemonic, serial, partnumber, ics, endofwarrdate, firstcommdate, lastrepdate) " +
			"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
				new Object []{headerId, new Integer(level), scandate, labelgen, new Integer(rack), new Integer(shelf), new Integer(slot), mnemonic, serial, partNumber, ics, endofwarrdate, firstcommdate, lastrepdate})) {
			System.out.println("OSS:Alcatel:Cannot INSERT LINE: " + s);
			QueryProcessing.addLogMessage(conn, qry, QueryProcessing.MSG_ERROR, "Cannot INSERT LINE");
			System.out.println("ALCATEL: Mark load as FAILED!");
			result = false;
		} else
			if (!addResource(conn, partNumber))
				result = false;
		return result;
	}

	/**
	 * Обработка строки исходного файла
	 * @param conn
	 * @param qry
	 * @param headerId
	 * @param s
	 * @return
	 */
	boolean ProcessString(Connection conn, Query qry, Integer headerId, String s) {
		boolean result = true;

		if (s.length() > 1) {
			String firstChar = s.substring(0, 1);
			if ("C".equals(firstChar))
				result = ProcessCommentLine(conn, qry, headerId, s);
			else if ("1".equals(firstChar) || "2".equals(firstChar) || "3".equals(firstChar))
				result = ProcessDataLine(conn, qry, headerId, s);
			else {
				QueryProcessing.addLogMessage(conn, qry, QueryProcessing.MSG_ERROR, "Unknown string in input file: " + s);
				result = false;
			}
		}

		return result;
	}

	/**
	 * Процедура обработки исходного файла
	 * @param conn
	 * @param idLoad
	 * @param inFile
	 * @return
	 */
	boolean ProcessLoadFile(Connection conn, Query qry, Integer idLoad, File inFile) {
		boolean result = true;

		try {
			BufferedReader br = new BufferedReader(new FileReader(inFile));
			System.out.println("OSS:Alcatel:Loading file: " + inFile.getName());
			QueryProcessing.addLogMessage(conn, qry, QueryProcessing.MSG_INFO, "Loading file: " + inFile.getName());
			Integer headerId = CreateEquipmentHeader(conn, idLoad, inFile.getName());
			if (headerId != null) {
				while (true) {
					String s = br.readLine();
					if (s == null)
						break;
					if (isEndMarker(s))
						break;
					if (!ProcessString(conn, qry, headerId, s))
						result = false;
				}
			} else
				result = false;
		} catch (FileNotFoundException e) {
			e.printStackTrace(System.out);
			result = false;
		} catch (IOException e) {
			e.printStackTrace(System.out);
			result = false;
		}

		if (!result)
			System.out.println("ALCATEL: Mark load as FAILED!");
		return result;
	}

	/**
	 * Процедура обработки каталога с исходными файлами
	 * полученными от интерфейса ARIE
	 * @param conn
	 * @param idLoad
	 * @return
	 */
	boolean LoadARIEFiles(Connection conn, Query qry, Integer idLoad) {
		boolean result = true;

		System.out.println("OSS:Alcatel:Loading Files from folder:" + strAlcatelARIEDataDir);
		try {
			File f = new File(strAlcatelARIEDataDir);
			File[] files = f.listFiles(new FileFilter() {
				public boolean accept(File pathname) {
					String name = pathname.getName();
					if ( (name.length() == 12) && (name.indexOf(".") == 8) )
						return true;
					System.out.println("Skipping file: '" + name + "' does not conform to 8.3 format");
					return false;
				}
			});
			if (files != null) {
				for (int i=0; i<files.length; i++) {
					if (files [i].isFile())
						if (!ProcessLoadFile(conn, qry, idLoad, files [i]))
							result = false;
				}
			}
		} catch (Throwable t) {
			System.out.println("ARIE Load Error:");
			t.printStackTrace(System.out);
			result = false;
		}

		return result;
	}

	/**
	 * Процедура извлечения BSM ID из строки
	 * @param s
	 * @return
	 */
	protected String extractBSMID(String s) {
		String result = null;

		int indFB = s.indexOf('{');
		if (indFB != -1) {
			String s1 = s.substring(indFB + 1);
			int indSB = s1.indexOf('{');
			if (indSB != -1) {
				String s2 = s1.substring(indSB + 1);
				int indTB = s2.indexOf('}');
				if (indTB != -1) {
					result = s2.substring(0, indTB).trim();
				}
			}
		}
		return result;
	}

	/**
	 * Процедура извлечения CI из строки
	 * @param s
	 * @return
	 */
	protected Integer extractCI(String s) {
		try {
			int cipos = s.indexOf("ci");
			String result = s.substring(cipos + 3, s.length()-1);
			// Old extract mechanic
			// String result = s.substring(s.length()-7, s.length()-1);
			int ci = Integer.parseInt(result.trim());
			if (ci != 0)
				return new Integer(ci);
			else
				return null;
		} catch (Throwable e) {
			return null;
		}
	}

	/**
	 * Процедура нахождения позиции токена с заданным содержанием
	 * @param s
	 * @param colName
	 * @return
	 */
	protected int findColumnPosition(String s, String colName) {
		int result = -1;
		int colIndex = -1;
		StringTokenizer st = new StringTokenizer(s, ";");
		while (st.hasMoreTokens()) {
			String tok = st.nextToken();
			colIndex = colIndex + 1;
			if (colName.equalsIgnoreCase(tok)) {
				result = colIndex;
				break;
			}
		}
		return result;
	}

	/**
	 * Процедура пропуска заданного количества токенов
	 * @param st
	 * @param delim
	 * @param nSkip
	 * @return
	 */
	protected int skipTokens(StringTokenizer st, String delim, int nSkip) {
		String s;
		int i = 0;
		while (true) {
			if (i >= nSkip)
				break;
			s = st.nextToken();
			if (delim.equals(s))
				i = i + 1;
		}
		return i;
	}

	/**
	 * Процедура обработки исходного файла секторов
	 * интерфейса Alcatel ACIE
	 * @param conn
	 * @param idLoad
	 * @param inFile
	 * @return
	 */
	public boolean ProcessLoadACIESectorsFile(Connection conn, Query qry, Integer idLoad, String omcrName, File inFile) {
		boolean result = true;

		try {
			BufferedReader br = new BufferedReader(new FileReader(inFile));
			System.out.println("OSS:Alcatel:Loading file: " + inFile.getName());
			QueryProcessing.addLogMessage(conn, qry, QueryProcessing.MSG_INFO, "Loading file: " + inFile.getName());
			br.readLine(); // Skip header line
			String colNames = br.readLine();
			int colPosSI = findColumnPosition(colNames, "AlcatelBts_SectorInstanceIdentifier");
			int colPosGI = findColumnPosition(colNames, "CellGlobalIdentity");
			//System.out.println("SectID pos = " + colPosSI + ", CellGlobalIdentity position = " + colPosGI);
			while (true) {
				String s = br.readLine();
				if (s == null)
					break;
				try {
					StringTokenizer st = new StringTokenizer(s, ";", true);
					int nSkipped = skipTokens(st, ";", colPosSI);
					String sectID = st.nextToken();
					skipTokens(st, ";", colPosGI-nSkipped);
					String globalId = st.nextToken();
					//System.out.println("SectID = " + sectID + ", globalID = " + globalId);
					String bsmID = extractBSMID(sectID);
					Integer ci = extractCI(globalId);
					Integer ciIndex = null;
					Integer gsmid = null;
					if (ci != null) {
						gsmid = makeBSIndexFromCellID(conn, ci, plmn);
						ciIndex = extractCellIndex(ci);
						//gsmid = new Integer(Math.round(new Double(Math.floor(ci.floatValue() / (float)10.0)).floatValue()));
						//System.out.println("CI = " + ci + ", ciIndex = " + ciIndex + ", gsmid = " + gsmid);
					}
					CDBCResultSet rs = new CDBCResultSet();
					if (!rs.executeUpdate(conn, "INSERT INTO alcatelsector (dataloadid, bsmid, ci, gsmid, omcr) " +
						"VALUES (?, ?, ?, ?, ?)",
							new Object []{idLoad, bsmID, ci, gsmid, omcrName})) {
						System.out.println("OSS:Alcatel:Cannot INSERT Sector LINE: " + s);
						QueryProcessing.addLogMessage(conn, qry, QueryProcessing.MSG_ERROR, "Cannot INSERT Sector LINE");
						System.out.println("ALCATEL: Mark load as FAILED!");
						result = false;
					}
					if (gsmid != null) {
						if (!addGSMID2Network(conn, gsmid, ciIndex, plmn)) {
							result = false;
							System.out.println("ALCATEL: Mark load as FAILED!");
						}
					}
				} catch (Throwable e) {
					System.out.println("Line Load Exception");
					e.printStackTrace(System.out);
					System.out.println("ALCATEL: Mark load as FAILED!");
					result = false;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace(System.out);
			System.out.println("ALCATEL: Mark load as FAILED!");
			result = false;
		} catch (IOException e) {
			e.printStackTrace(System.out);
			System.out.println("ALCATEL: Mark load as FAILED!");
			result = false;
		}

		return result;
	}

	/**
	 * Процедура обработки исходного файла BTS
	 * интерфейса Alcatel ACIE
	 * @param conn
	 * @param idLoad
	 * @param inFile
	 * @return
	 */
	public boolean ProcessLoadACIEBTSFile(Connection conn, Query qry, Integer idLoad, String omcrName, File inFile) {
		boolean result = true;

		try {
			BufferedReader br = new BufferedReader(new FileReader(inFile));
			System.out.println("OSS:Alcatel:Loading file: " + inFile.getName());
			QueryProcessing.addLogMessage(conn, qry, QueryProcessing.MSG_INFO, "Loading file: " + inFile.getName());
			String bscline = br.readLine();
			StringTokenizer st1 = new StringTokenizer(bscline, ";", true);
			skipTokens(st1, ";", 5);
			String bscname = st1.nextToken();
			//System.out.println("BSCName = " + bscname);
			//String bscuserlabel = bscname.substring(0, bscname.length()-1);
			String bscuserlabel = bscname;
			String colNames =  br.readLine();
			int colPosSI = findColumnPosition(colNames, "AlcatelBtsSiteManagerInstanceIdentifier");
			int colPosBG = findColumnPosition(colNames, "BTS_Generation");
			int colPosUL = findColumnPosition(colNames, "UserLabel");
			//System.out.println("SIPos = " + colPosSI + ", BTS Gen Pos = " + colPosBG + ", UserLabel position = " + colPosUL);
			while (true) {
				String s = br.readLine();
				if (s == null)
					break;
				try {
					StringTokenizer st = new StringTokenizer(s, ";", true);
					int nSkipped = skipTokens(st, ";", colPosSI);
					String btsID = st.nextToken();
					String bsmID = btsID.substring(1, btsID.length()-1).trim();
					nSkipped = nSkipped + skipTokens(st, ";", colPosBG-nSkipped);
					String btsGen = st.nextToken();
					skipTokens(st, ";", colPosUL-nSkipped);
					String btsLabelQ = st.nextToken();
					String btsLabel = btsLabelQ.substring(1, btsLabelQ.length()-1).trim();
					//System.out.println("BSMID = " + bsmID + ", btsLabel = " + btsLabel + ", BTS Generation = " + btsGen);
					CDBCResultSet rs = new CDBCResultSet();
					if (!rs.executeUpdate(conn, "INSERT INTO alcatelbts (dataloadid, bsmid, btslabel, bsclabel, omcr, bscuserlabel, btsgeneration) " +
						"VALUES (?, ?, ?, ?, ?, ?, ?)",
							new Object []{idLoad, bsmID, btsLabel, bscname, omcrName, bscuserlabel, btsGen})) {
						System.out.println("OSS:Alcatel:Cannot INSERT BTS LINE: " + s);
						QueryProcessing.addLogMessage(conn, qry, QueryProcessing.MSG_ERROR, "Cannot INSERT BTS LINE");
						System.out.println("ALCATEL: Mark load as FAILED!");
						result = false;
					}
				} catch (Throwable e) {
					System.out.println("Line Load Exception");
					e.printStackTrace(System.out);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace(System.out);
			System.out.println("ALCATEL: Mark load as FAILED!");
			result = false;
		} catch (IOException e) {
			e.printStackTrace(System.out);
			System.out.println("ALCATEL: Mark load as FAILED!");
			result = false;
		}

		return result;
	}

	/**
	 * Процедура обработки каталога с исходными файлами
	 * полученными от интерфейса ACIE
	 * @param conn
	 * @param idLoad
	 * @return
	 */
	boolean LoadACIEFiles(Connection conn, Query qry, Integer idLoad, java.util.Date loadDate) {
		boolean result = true;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		System.out.println("OSS:Alcatel:Loading ACIE Files from folder:" + strAlcatelACIEDataDir);
		File f = new File(strAlcatelACIEDataDir);
		File[] files = f.listFiles();
		if (files != null) {
			for (int i=0; i<files.length; i++) {
				if (files [i].isDirectory()) {
					String omcrName = files [i].getName();
					String bssDir = files [i].getAbsolutePath() + "\\" + df.format(loadDate);
					System.out.println("LOADING FROM Date Directory: " + bssDir);
					File bf = new File(bssDir);
					File[] bfiles = bf.listFiles();
					if (bfiles != null) {
						for (int j=0; j<bfiles.length; j++) {
							if (bfiles [j].isDirectory()) {
								System.out.println("LOADING FROM BSS Directory: " + bfiles [j]);
								String sectFile = bfiles [j].getAbsolutePath() + "\\AlcatelBts_Sector.csv";
								String bssFile = bfiles [j].getAbsolutePath() + "\\AlcatelBtsSiteManager.csv";
								System.out.println("LOADING SectorFile: " + sectFile);
								if (!ProcessLoadACIESectorsFile(conn, qry, idLoad, omcrName, new File(sectFile))) {
									result = false;
									System.out.println("ALCATEL: Mark load as FAILED!");
								}
								System.out.println("LOADING BTSFile: " + bssFile);
								if (!ProcessLoadACIEBTSFile(conn, qry, idLoad, omcrName, new File(bssFile))) {
									result = false;
									System.out.println("ALCATEL: Mark load as FAILED!");
								}
							}
						}
					}
				}
			}
		}

		//File f = new File("AlcatelBts_Sector.csv");
		return result;
	}

	/**
	 * Определение даты файлов в каталоге ARIE - по первому файлу
	 * @return
	 */
	protected java.util.Date findARIEDate() {
		File f = new File(strAlcatelARIEDataDir);
		File[] files = f.listFiles();
		if (files != null) {
			return new java.util.Date(files [0].lastModified());
		}
		return null;
	}

	/**
	 * Проверка наличия ACIE файлов на дату ARIE-файла
	 * @return
	 */
	protected boolean checkACIEDate(Connection conn, Query qry, java.util.Date arieDate) {
		boolean result = true;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		System.out.println("OSS:Alcatel:Checking ACIE Files existence in folder:" + strAlcatelACIEDataDir);
		File f = new File(strAlcatelACIEDataDir);
		File[] files = f.listFiles();
		if (files != null) {
			for (int i=0; i<files.length; i++) {
				if (files [i].isDirectory()) {
					String omcrName = files [i].getName();
					String bssDir = files [i].getAbsolutePath() + "\\" + df.format(arieDate);
					System.out.println("Checking Date Directory: " + bssDir);
					File bf = new File(bssDir);
					if (!bf.exists()) {
						result = false;
						System.out.println("ALCATEL: No Date Directory, load not performed");
						QueryProcessing.addLogMessage(conn, qry, QueryProcessing.MSG_ERROR, "No ACIE dir, load not performed: " + bssDir);
					}
				}
			}
		}
		return result;
	}

	/**
	 * Перекрываем метод в исходном Collaboration
	 */
	protected boolean doBeforeTask(Query qry) {
		boolean result = true;
		Connection conn = null;
		try {
			DB db = getTargetDb();
			conn = DB.getConnection(db);
			///Calendar cal = new GregorianCalendar();
			//cal.set(2005, 06, 27);
			java.util.Date loadDate = findARIEDate(); //cal.getTime();
			System.out.println("ARIEDate = " + loadDate);
			if (loadDate != null) {
				if (checkACIEDate(conn, qry, loadDate)) {
					Integer idLoad = CreateLoad(conn, loadDate, plmn);
					if (idLoad != null) {
						if (ClearLoad(conn, idLoad)) {
							if (!LoadARIEFiles(conn, qry, idLoad))
								result = false;
							if (!LoadACIEFiles(conn, qry, idLoad, loadDate))
								result = false;
						} else
							result = false;
					} else
						result = false;
				}
			} else {
				System.out.println("ARIE Date: NULL");
				QueryProcessing.addLogMessage(conn, qry, QueryProcessing.MSG_ERROR, "Cannot Find ARIE Date!!!!");
				result = false;
			}
		} catch (Exception e) {
			result = false;
		} finally {
			try {conn.close();} catch (Exception e) {}
		}
		return result;
	}
}
