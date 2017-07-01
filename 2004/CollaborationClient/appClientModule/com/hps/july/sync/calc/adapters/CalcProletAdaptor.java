package com.hps.july.sync.calc.adapters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ListIterator;

import com.hps.july.cdbc.lib.CDBCResultSet;
import com.hps.july.cdbc.lib.CDBCRowObject;

import com.hps.july.terrabyte.ext.client.*;
import com.hps.july.terrabyte.core.*;
import com.hps.july.core.*;

/**
 * @author Dmitry Dneprov
 * Адаптер для вызова процедуры обработки данных.
 * Вызывает внешний EXE-модуль расчета пролета
 */
public class CalcProletAdaptor extends DefaultCollaboration {

	private String locBeeplanDir;
	private String terrabyteHost;
	private int terrabytePort;

	private static class ProcMap extends ColumnMap {
		ProcMap() {
			super();
		}
	}

	public CalcProletAdaptor(DB targetDB, DB sourceDB, DB sconLOG_DB,
			String argBeeplanDir, String argTerrabyteHost, int argTerrabytePort) {
		super(targetDB, sourceDB, "dummy", "dummy", null, new ProcMap(), sconLOG_DB);
		locBeeplanDir = argBeeplanDir;
		terrabyteHost = argTerrabyteHost;
		terrabytePort = argTerrabytePort;
	}

	/**
	 * Выдача значения колонки в виде <TD>значение</TD>
	 * @param columnName
	 * @param fout
	 * @param ro
	 * @throws IOException
	 */
	protected void printColumn(String columnName, FileWriter fout, CDBCRowObject ro) throws IOException {
		fout.write("<TD>");
		fout.write(ro.getColumn(columnName).asString());
		fout.write("</TD>");
	}

	protected void printEmptyColumn(FileWriter fout) throws IOException {
		fout.write("<TD>");
		fout.write("</TD>");
	}

	/**
	 * Заполение запроса для программы расчета пролета
	 * @param fout
	 * @param ro
	 * @return
	 */
	protected boolean makeCalcRequest(String beeplanDir, FileWriter fout, CDBCRowObject ro) {
		boolean result = false;

		System.out.println("MakeCalcRequest Start");
		try {
			fout.write("<HTML>");
			fout.write("<BODY>");
			fout.write("<TABLE>");
			fout.write("<TR>");

			// Put fields
			printColumn("hopbeenetid", fout, ro);
			printColumn("posa_beenetid", fout, ro);
			printColumn("posa_name", fout, ro);
			printEmptyColumn(fout);
			printColumn("posa_long", fout, ro);
			printColumn("posa_lat", fout, ro);
			printColumn("posa_hset", fout, ro);
			printColumn("posb_beenetid", fout, ro);
			printColumn("posb_name", fout, ro);
			printEmptyColumn(fout);
			printColumn("posb_long", fout, ro);
			printColumn("posb_lat", fout, ro);
			printColumn("posb_hset", fout, ro);
			printColumn("restype", fout, ro);
			printColumn("resmodel", fout, ro);
			printColumn("reservtype", fout, ro);
			printColumn("antdiam", fout, ro);
			printColumn("frequency", fout, ro);
			printColumn("speed", fout, ro);
			printColumn("gain_factor", fout, ro);
			printColumn("power", fout, ro);
			printColumn("err_10e_3", fout, ro);
			printColumn("err_10e_6", fout, ro);

			fout.write("</TR>");
			fout.write("</TABLE>");
			fout.write("</BODY>");
			fout.write("</HTML>");
			result = true;
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		System.out.println("MakeCalcRequest END");
		return result;
	}

	/**
	 * Вызов программы расчета (EXE-модуля)
	 * @return
	 */
	protected boolean executeProgram(String beeplanDir) {
		boolean result = false;

		try {
		System.out.println("executeProgram Start");
		File f = new File(beeplanDir);
		try {
			Process p = Runtime.getRuntime().exec(new String[] {
				beeplanDir + "/AutoRRL2003.exe",
				"RRL.ini"
				}, null, f);

			int totalWait = 90;
			while (--totalWait > 0) {
				//System.out.println("Wait cycle: " + totalWait);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace(System.out);
				}
				try {
					if (p.exitValue() == 0)
						result = true;
					break; // After getting exit value - exit from cycle
				} catch (IllegalThreadStateException e) {
					// Process not terminated in 'response time'
					// Not terminated yet - wait again
				}
			}
			try {
				if (p.exitValue() == 0)
					result = true;
				System.out.println("Have exit value");
			} catch (IllegalThreadStateException e) {
				// Process not terminated in 'response time'
				System.out.println("Not terminated - destroy");
				p.destroy();
			}
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}

		System.out.println("executeProgram End");
		} catch (Throwable t) {
			t.printStackTrace(System.out);
		}
		return result;
	}

	/**
	 * Очистка каталога с результатами расчета
	 * @param beeplanDir
	 * @return
	 */
	protected boolean clearResult(String beeplanDir) {
		boolean result = true;
		System.out.println("Clearing folder:" + beeplanDir);
		File f = new File(beeplanDir);
		File[] files = f.listFiles();
		if (files != null) {
			for (int i=0; i<files.length; i++) {
				if (files [i].isFile())
					if (!files [i].delete()) {
						System.out.println("Cannot delete file: '" + files[i].getName() + "'");
						result = false;
					}
			}
		}
		return result;
	}

	/**
	 * Получение файла результата
	 * и запись в базу данных
	 * @param beeplanDir
	 * @return
	 */
	protected boolean getResult(Integer reqCode, Integer hopCode, String beeplanDir) {
		boolean result = false;
		System.out.println("getResult Start");
		File f = new File(beeplanDir);
		File[] files = f.listFiles();
		for (int i=0; i<files.length; i++) {
			if (files [i].isFile()) {
				System.out.println("Result file: " + files [i].getName());
				FileInputStream fis;
				try {
					fis = new FileInputStream(files[i]);
					Document doc = new Document(fis, null, null, files[i].getName());
					doc.setAttribute(Constants.ATTRIBUTE_NRI_TYPE_ID, new Integer(2)); //
					doc.setAttribute(Constants.ATTRIBUTE_FILE_DESCRIPTION, "Автоматический расчет пролета");
					doc.setAttribute(Constants.ATTRIBUTE_FILE_MIME_TYPE, "application/msword");
					TerrabyteExternalClient client = TerrabyteExternalClientFactory.getClient(terrabyteHost, terrabytePort, "collaboration");
					doc = client.addObjectDocument(doc, reqCode, ObjectTypes.REQUEST, "KzlLoader");
					fis.close();
					System.out.println("ADDED to TerraByte WITH ID=" + doc.getClientId());
					client.linkDocumentByObject(reqCode, ObjectTypes.REQUEST,
							hopCode, ObjectTypes.HOPS, "Рельеф линий", "KzlLoader");
					result = true;
				} catch (FileNotFoundException e) {
					System.out.println("File not added to terrabyte, exception:");
					e.printStackTrace(System.out);
				} catch (TerrabyteExternalClientException e) {
					System.out.println("File not added to terrabyte, exception:");
					e.printStackTrace(System.out);
				} catch (IOException e) {
					System.out.println("File not added to terrabyte, exception:");
					e.printStackTrace(System.out);
				} catch (Throwable t) {
					System.out.println("File not added to terrabyte, exception:");
					t.printStackTrace(System.out);
				}
				break;
			}
		}
		System.out.println("getResult END");
		return result;
	}

	/**
	 * Вызов расчета пролета
	 * @param ro
	 * @return
	 */
	protected boolean CalculateProlet(String beeplanDir, CDBCRowObject ro) {
		boolean result = false;
		final String beeplanOutDir = beeplanDir + File.separator + "outdocs";
		try {
			Integer reqCode = (Integer)ro.getColumn("paramid").asObject();
			if (clearResult(beeplanOutDir)) {
				String fPath = beeplanDir + File.separator + "data.xls";
				System.out.println("Creating file: " + fPath);

				File f = new File(fPath);
				f.createNewFile();
				FileWriter fout = new FileWriter(f);
				if (makeCalcRequest(beeplanDir, fout, ro)) {
					fout.flush();
					fout.close();
					if (executeProgram(beeplanDir)) {
						if (getResult(reqCode, (Integer)ro.getColumn("hopsid").asObject(), beeplanOutDir)) {
							result = true;
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		return result;
	}


	protected boolean doBeforeTask(Query qry) {
		boolean result = true;
		Connection conn = null;
		try {
			DB db = getTargetDb();
			CDBCResultSet rs = new CDBCResultSet();
			CDBCResultSet rs2 = new CDBCResultSet();
			conn = DB.getConnection(db);
			rs.executeQuery(conn, "SELECT * FROM proletparams WHERE readystate=2", new Object []{}, 1);
			ListIterator it = rs.listIterator();
			if (it.hasNext()) {
				CDBCRowObject ro = (CDBCRowObject)it.next();
				Integer reqCode = (Integer)ro.getColumn("paramid").asObject();
				if (!CalculateProlet(locBeeplanDir, ro)) {
					System.out.println("Calculate prolet error, setting readystate=4 for paramid = " + reqCode);
					rs2.executeUpdate(conn, "UPDATE proletparams SET readystate=4 WHERE paramid=" + reqCode, new Object[]{});
					QueryProcessing.addLogMessage(conn, qry, QueryProcessing.MSG_ERROR, "Ошибка расчета пролета для записи " + ro.getColumn("paramid").asString());
					result = false;
				} else {
					System.out.println("Calculate prolet success, setting readystate=3 for paramid = " + reqCode);
					if (!rs2.executeUpdate(conn, "UPDATE proletparams SET readystate=3 WHERE paramid=" + reqCode, new Object[]{}))
						result = false;
				}
			}
		} catch (Exception e) {
			result = false;
		} finally {
			try {conn.close();} catch (Exception e) {}
		}
		return result;
	}

}
