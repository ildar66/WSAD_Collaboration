package com.hps.july.terrabyte.imp.processor;

import com.hps.beeline.LoaderException;
import com.hps.framework.exception.BaseException;
import com.hps.july.terrabyte.imp.command.Command;
import com.hps.july.terrabyte.imp.command.ListBsForSORMCommand;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 07.07.2006
 * Time: 12:50:48
 */
public class ListBsForSORMProcessor extends AbstractProcessor {

	private Command command;
	private String fileName;
	private String eofFilename;
	private String dir;
	private static final String SEPARATOR = "|";
	private static final int SEPARATOR_LENGTH = SEPARATOR.getBytes().length;

	private Logger logger = Logger.getLogger(ListBsForSORMProcessor.class);

	public ListBsForSORMProcessor() {
		this.command = null;
	}

	public void execute(Command command) throws LoaderException, BaseException {
		this.command = command;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss");
		dir = (String)command.getParameter(ListBsForSORMCommand.DIRECTORY);
		String tempFileName = "file-" + (sdf.format(new Date()));
		fileName = dir + File.separator + tempFileName  + ".txt";
		eofFilename = dir + File.separator + tempFileName  + ".eof";
		saveDataToFile();
	}

	protected void saveDataToFile() throws LoaderException {
		int emptyAddress = 0;
		int notCorrectBS = 0;
		int emptyAzimuth = 0;
		int CHANGED = 0;
		Throwable errorT = null;
		Connection connection = command.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FileOutputStream fos = null;
		FileOutputStream eofStream = null;
		File eof = null;
		File file = null;
		boolean error = false;
		try {
			pstmt = connection.prepareStatement("execute procedure getListBSForSorm()");
			file = new File(fileName);
			if(!file.exists()) file.createNewFile();
			fos = new FileOutputStream(fileName);
			rs = pstmt.executeQuery();
			boolean first = true;
			long count = 0;
			while(rs.next()) {

				int rsResult = rs.getInt(1);
				Integer idPosition = null;
				if(!rs.wasNull()) idPosition = new Integer(rsResult);

				rsResult = rs.getInt(2);
				Integer idBs = null;
				if(!rs.wasNull()) idBs = new Integer(rsResult);

				String posName = rs.getString(3);
				if(rs.wasNull()) posName = "";

				String address = rs.getString(4);
				if(rs.wasNull()) address = "";

				String sectorNumber = rs.getString(5);
				if(rs.wasNull()) sectorNumber = "";

				rsResult = rs.getInt(6);
				Integer sectorAzimuth = null;
				if(!rs.wasNull()) sectorAzimuth = new Integer(rsResult);

				rsResult = rs.getInt(7);
				Integer regionId = null;
				if(!rs.wasNull()) regionId = new Integer(rsResult);

				String regionName = rs.getString(8);
				if(rs.wasNull()) regionName = "";

				rsResult = rs.getInt(9);
				Integer filialId = null;
				if(!rs.wasNull()) filialId = new Integer(rsResult);

				String filialName = rs.getString(10);
				if(rs.wasNull()) filialName = "";

				String posState = rs.getString(11);
				if(rs.wasNull()) posState = "";

				rsResult = rs.getInt(12);
				Integer changed = null;
				if(!rs.wasNull()) changed = new Integer(rsResult);
				if(rsResult == 1) CHANGED++;

				//write to file !

				if(!first) {
					fos.write("\n".getBytes());
					count += "\n".getBytes().length;
				}
				first = false;

				if(idPosition != null) {
					fos.write(idPosition.toString().getBytes());
					count += idPosition.toString().getBytes().length;
				}

				fos.write(SEPARATOR.getBytes());
				count += SEPARATOR_LENGTH;

				if(idBs != null) {
					fos.write(idBs.toString().getBytes());
					count += idBs.toString().getBytes().length;
					if(idBs.toString().getBytes().length < 5) notCorrectBS++;
				} else {
					notCorrectBS++;
				}

				fos.write(SEPARATOR.getBytes());
				count += SEPARATOR_LENGTH;

				fos.write(posName.getBytes());
				count += posName.getBytes().length;

				fos.write(SEPARATOR.getBytes());
				count += SEPARATOR_LENGTH;

				fos.write(address.getBytes());
				count += address.getBytes().length;
				if(address.length() == 0) emptyAddress++;

				fos.write(SEPARATOR.getBytes());
				count += SEPARATOR_LENGTH;

				fos.write(sectorNumber.getBytes());
				count += sectorNumber.getBytes().length;
				fos.write(SEPARATOR.getBytes());
				count += SEPARATOR_LENGTH;

				if(sectorAzimuth != null) {
					fos.write(sectorAzimuth.toString().getBytes());
					count += sectorAzimuth.toString().getBytes().length;
				} else {
					emptyAzimuth++;
				}

				fos.write(SEPARATOR.getBytes());
				count += SEPARATOR_LENGTH;

				if(regionId != null) {
					fos.write(regionId.toString().getBytes());
					count += regionId.toString().getBytes().length;
				}

				fos.write(SEPARATOR.getBytes());
				count += SEPARATOR_LENGTH;

				fos.write(regionName.getBytes());
				count += regionName.getBytes().length;

				fos.write(SEPARATOR.getBytes());
				count += SEPARATOR_LENGTH;

				if(filialId != null) {
					fos.write(filialId.toString().getBytes());
					count += filialId.toString().getBytes().length;
				}

				fos.write(SEPARATOR.getBytes());
				count += SEPARATOR_LENGTH;

				fos.write(filialName.getBytes());
				count += filialName.getBytes().length;

				fos.write(SEPARATOR.getBytes());
				count += SEPARATOR_LENGTH;

				fos.write(posState.getBytes());
				count += posState.getBytes().length;

				fos.write(SEPARATOR.getBytes());
				count += SEPARATOR_LENGTH;

				fos.write(changed.toString().getBytes());
				count += changed.toString().getBytes().length;

				fos.write(SEPARATOR.getBytes());
				count += SEPARATOR_LENGTH;

				fos.flush();
			}
			logger.info("emptyAddress ["+emptyAddress+"]");
			logger.info("emptyAzimuth ["+emptyAzimuth+"]");
			logger.info("notCorrectBS ["+notCorrectBS+"]");
			logger.info("changed ["+CHANGED+"]");
			fos.close();
			eof = new File(eofFilename);
			if(!eof.exists()) eof.createNewFile();
			eofStream = new FileOutputStream(eofFilename);
			eofStream.write((new Long(count)).toString().getBytes());
			eofStream.flush();
			eofStream.close();
		} catch (SQLException e) {
			logger.error("Database error while creating SORM file, " + e.toString(), e);
			error = true;
			errorT = e;
		} catch (Exception e) {
			logger.error("Unexpected error while creating SORM file, " + e.toString(), e);
			e.printStackTrace(System.out);
			error = true;
			errorT = e;
		} finally {
			if(fos != null) try { fos.close(); } catch (Exception e) {}
			if(eofStream != null) try { eofStream.close(); } catch (Exception e) {}
			if(rs != null) try { rs.close(); } catch (Exception e) {}
			if(pstmt != null) try { pstmt.close(); } catch (Exception e) {}
			if(connection != null) try { connection.close(); } catch (Exception e) {}
		}
		if(error) {
			deleteFile(file);
			deleteFile(eof);
			throw new LoaderException(errorT.getMessage());
		}

		deleteArchiveFiles();

	}

	private void deleteFile(File file) {
		if(file != null && file.exists())
			for(int i = 0; i < 30 && !file.delete(); i++);
	}

	private void deleteArchiveFiles() {
		File mainDir = new File(dir);
		if(mainDir.isDirectory()) {
			File [] list = mainDir.listFiles();
			for(int i = 0; i < list.length; i++) {
				File file = list[i];
				if(file.isFile()) {
					long lastModified = file.lastModified();
					GregorianCalendar calendar = new GregorianCalendar();
					calendar.setTimeInMillis(System.currentTimeMillis());
					calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
					long currentDate = calendar.getTimeInMillis();
					if(currentDate > lastModified) {
						logger.info("["+file.getName()+"] stored in archive more than one month - delete");
						deleteFile(file);
					}
				}
			}
		} else {
			logger.warn("”казаный путь ["+dir+"] не €вл€етс€ директорией ");
		}


	}
}
