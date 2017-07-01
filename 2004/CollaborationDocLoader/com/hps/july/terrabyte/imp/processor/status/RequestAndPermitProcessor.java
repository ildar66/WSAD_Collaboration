package com.hps.july.terrabyte.imp.processor.status;

import com.hps.july.terrabyte.imp.command.Command;
import com.hps.july.terrabyte.imp.command.CommandNames;
import com.hps.july.terrabyte.imp.processor.AbstractProcessor;
import com.hps.july.terrabyte.imp.essence.status.BasestationStatusRequestAndPermit;
import com.hps.july.terrabyte.imp.essence.SimpleDirInfo;
import com.hps.july.terrabyte.imp.essence.SimpleFileInfo;
import com.hps.july.terrabyte.imp.essence.IFileInfo;
import com.hps.beeline.LoaderException;
import com.hps.framework.exception.BaseException;
import jxl.Workbook;
import jxl.Sheet;
import jxl.Cell;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.sql.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


/**
 * процессор обрабатывающий файлы с информацией о заявках и разрешениях на БС
 * от Львова Сергея
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 09.11.2005
 * Time: 11:22:23
 */
public class RequestAndPermitProcessor extends AbstractProcessor {

    private Command command = null;
    public static String GSM900 = "БС GSM-900";
    public static String GSM1800 = "БС GSM-1800";
    public static String DCS900 = "БС DCS-900";
    public static String DCS1800 = "БС DCS-1800";

    public static String ON = "включена";
    public static String DECLARATED = "зяавлена"; //заявлена
    public static String DECISION = "№ заключения"; //№ заключения ГРЧЦ
    public static String LICENCE = "№ разрешения"; //№ разрешения
    public static String REGION_NAME = "регион"; //№ разрешения


    private String currentFileName = null;
    private String currentSheetName = null;
    private String currentSupRegCode = null;
    private int current9001800 = -1;
    private int currentGSMDCS = -1;

    private int founded = 0;
    private int skiped = 0;
    private int proceeded = 0;
    private int created = 0;
    private int updated = 0;

    public void execute(Command command) throws LoaderException, BaseException {

        this.command = command;

        init();
        Workbook workbook = null;
        FileInputStream file = null;
        try {
            IFileInfo [] files = getFiles((String)command.getParameter(CommandNames.FILE_CATALOG));
            for(int j = 0; j < files.length; j++) {
                IFileInfo info = files[j];
                file = new FileInputStream(info.getFile());
                currentFileName = info.getName();
                currentSupRegCode = getSupRegCode(currentFileName);
                if(currentSupRegCode == null) continue;
                //System.out.println("name["+currentFileName+"] ["+getSupRegCode(currentFileName)+"]");
                //log("Start processing...");
                workbook = Workbook.getWorkbook(file);

                Sheet sheetGSM900 = workbook.getSheet(GSM900);
                if(sheetGSM900 != null) {
                    currentSheetName = GSM900;
                    current9001800 = 0;
                    currentGSMDCS = 0;
                    Collection gsm900 = findRequestAndPermit(sheetGSM900, GSM900);
                    store(gsm900);
                }
                Sheet sheetGSM1800 = workbook.getSheet(GSM1800);
                if(sheetGSM1800 != null) {
                    currentSheetName = GSM1800;
                    current9001800 = 1;
                    currentGSMDCS = 1;
                    Collection gsm1800 = findRequestAndPermit(sheetGSM1800, GSM1800);
                    store(gsm1800);
                }
                Sheet sheetDCS900 = workbook.getSheet(DCS900);
                if(sheetDCS900 != null) {
                    currentSheetName = DCS900;
                    current9001800 = 0;
                    currentGSMDCS = 2;
                    Collection gsm900 = findRequestAndPermit(sheetDCS900, DCS900);
                    store(gsm900);
                }
                Sheet sheetDCS1800 = workbook.getSheet(DCS1800);
                if(sheetDCS1800 != null) {
                    currentSheetName = DCS1800;
                    current9001800 = 1;
                    currentGSMDCS = 3;
                    Collection gsm1800 = findRequestAndPermit(sheetDCS1800, DCS1800);
                    store(gsm1800);
                }

/*
                Sheet sheetGSM1800 = workbook.getSheet(GSM1800);
                Sheet sheetDCS900 = workbook.getSheet(DCS900);
                Sheet sheetDCS1800 = workbook.getSheet(DCS1800);

                Sheet [] sheets = workbook.getSheets();
                for(int i = 0; i < sheets.length; i++) {
                    Sheet sheet = sheets[i];
                    String sheetName = sheet.getName();
                    if(sheetName.toUpperCase().equals(GSM900)) {
                        currentSheetName = sheetName;
                        current9001800 = 0;
                        currentGSMDCS = 0;
                        Collection gsm900 = findRRLRequestAndPermit(sheet, GSM900);
                        store(gsm900);
                        //positions.addAll(gsm900);
                    } else {
                        if(sheetName.toUpperCase().equals(GSM1800)) {
                            currentSheetName = sheetName;
                            current9001800 = 1;
                            currentGSMDCS = 1;
                            Collection gsm1800 = findRRLRequestAndPermit(sheet, GSM1800);
                            store(gsm1800);
                            //positions.addAll(gsm1800);
                        } else {
                            if(sheetName.toUpperCase().equals(DCS900)) {
                                currentSheetName = sheetName;
                                current9001800 = 0;
                                currentGSMDCS = 2;
                                Collection dcs900 = findRRLRequestAndPermit(sheet, DCS900);
                                store(dcs900);
                                //positions.addAll(dcs900);
                            } else {
                                if(sheetName.toUpperCase().equals(DCS1800)) {
                                    currentSheetName = sheetName;
                                    current9001800 = 1;
                                    currentGSMDCS = 3;
                                    Collection dcs1800 = findRRLRequestAndPermit(sheet, DCS1800);
                                    store(dcs1800);
                                    //positions.addAll(dcs1800);
                                }
                            }
                        }
                    }
                }
*/

                //log("end processing !");
                try { if(workbook != null) workbook.close(); } catch(Exception e) {}
                try { if(file != null) file.close(); } catch(Exception e) {}
            }

            //Sheet gsm900 = workbook.getSheet("БС GSM-900");

        } catch(IOException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try { if(workbook != null) workbook.close(); } catch(Exception e) {}
            try { if(file != null) file.close(); } catch(Exception e) {}
        }
        System.out.println("ALL["+getFounded()+"] C["+getCreated()+"] U["+getUpdated()+"] S["+getSkiped()+"] P["+getProceeded()+"]");
    }

    protected void init() {
        Connection connection = this.command.getConnection();
        PreparedStatement pstmt = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM smss_gsm_permits ");
            pstmt = connection.prepareStatement(sql.toString());
            pstmt.executeUpdate();
        } catch(SQLException e) {
            System.out.println("---Code ["+e.getErrorCode()+"]");
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
        }
    }

    protected void store(Collection positions) {

        for(Iterator iterator = positions.iterator(); iterator.hasNext();) {
            BasestationStatusRequestAndPermit obj = (BasestationStatusRequestAndPermit) iterator.next();
            //isObjectExist(obj);
            Connection connection = this.command.getConnection();
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                if(obj.getIdent() == null) {
                    System.out.println("Create ["+currentFileName+"] ["+currentSheetName+"] ["+obj.getBsNumber()+"] ["+obj.getSupRegCode()+"]["+obj.getCurrent9001800()+"]["+obj.getCurrentGSMDCS()+"] ["+obj.getPositionId()+"] ["+getNumberFromString(obj.getOn())+"] ["+getNumberFromString(obj.getDeclarated())+"] ["+obj.getDecisionNumber()+"] ["+obj.getLicenceNumber()+"] ");

                    //create
                    StringBuffer sql = new StringBuffer();
                    sql.append("INSERT INTO smss_gsm_permits ");
                    sql.append(" (positionid, bs_on, bs_declarated, bs_decision_number, bs_licence_number, position_name, region_code, ");
                    sql.append(" type, frequency, region_name) ");
                    sql.append(" VALUES ");
                    sql.append(" (? , ?, ?, ?, ?, ? , ?, ?, ?, ?) ");
                    pstmt = connection.prepareStatement(sql.toString());
                    if(obj.getPositionId() != null)
                        pstmt.setInt(1, obj.getPositionId().intValue());
                    else
                        pstmt.setNull(1, Types.INTEGER);
                    pstmt.setInt(2, getNumberFromString(obj.getOn()).intValue());
                    pstmt.setInt(3, getNumberFromString(obj.getDeclarated()).intValue());
                    pstmt.setString(4, obj.getDecisionNumber());
                    pstmt.setString(5, obj.getLicenceNumber());
                    pstmt.setString(6, obj.getBsNumber());
                    pstmt.setString(7, obj.getSupRegCode());
                    pstmt.setInt(8, currentGSMDCS);
                    pstmt.setInt(9, current9001800);
                    pstmt.setString(10, obj.getRegionName());
                    pstmt.executeUpdate();
                    addCreated();
                } else {
                    System.out.println("Update ["+currentFileName+"] ["+currentSheetName+"] ["+obj.getBsNumber()+"]["+obj.getSupRegCode()+"]["+obj.getCurrent9001800()+"]["+obj.getCurrentGSMDCS()+"]["+obj.getPositionId()+"] ["+getNumberFromString(obj.getOn())+"] ["+getNumberFromString(obj.getDeclarated())+"] ["+obj.getDecisionNumber()+"] ["+obj.getLicenceNumber()+"] ");
                    //update
                    StringBuffer sql = new StringBuffer();
                    sql.append("SELECT count_last_update FROM smss_gsm_permits WHERE id = ?");
                    pstmt = connection.prepareStatement(sql.toString());
                    pstmt.setInt(1, obj.getIdent().intValue());
                    rs = pstmt.executeQuery();
                    int lastUpdate = 0;
                    if(rs.next()) {
                        lastUpdate = rs.getInt("count_last_update");
                        if(rs.wasNull()) lastUpdate = 0;
                    }
                    if(rs != null) rs.close();
                    if(pstmt != null) pstmt.close();


                    sql = new StringBuffer();
                    sql.append("UPDATE smss_gsm_permits ");
                    sql.append(" SET bs_on = ?, bs_declarated = ?,  ");
                    sql.append(" bs_decision_number = ?, bs_licence_number = ?, updated = CURRENT, count_last_update = ?, ");
                    sql.append(" position_name = ?, region_code = ?, type = ?, frequency = ?, ");
                    sql.append(" positionid = ?");
                    sql.append(" WHERE id = ? ");
                    pstmt = connection.prepareStatement(sql.toString());
                    pstmt.setInt(1, getNumberFromString(obj.getOn()).intValue());
                    pstmt.setInt(2, getNumberFromString(obj.getDeclarated()).intValue());
                    pstmt.setString(3, obj.getDecisionNumber());
                    pstmt.setString(4, obj.getLicenceNumber());
                    //pstmt.setTimestamp(5, new Timestamp((new java.util.Date()).getTime()));
                    pstmt.setInt(5, ++lastUpdate);
                    pstmt.setString(6, obj.getBsNumber());
                    pstmt.setString(7, obj.getSupRegCode());
                    pstmt.setInt(8, currentGSMDCS);
                    pstmt.setInt(9, current9001800);
                    pstmt.setInt(10, obj.getPositionId().intValue());
                    pstmt.setInt(11, obj.getIdent().intValue());
                    pstmt.executeUpdate();
                    addUpdated();
                }
            } catch(SQLException e) {
                System.out.println("---Code ["+e.getErrorCode()+"]");

                e.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                try { if(rs != null) rs.close(); } catch(Exception e) {}
                try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
            }


        }
        StringBuffer sb = new StringBuffer();
        sb.append("File [");
        sb.append(currentFileName);
        sb.append("] sheet [");
        sb.append(currentSheetName);
        sb.append("] : info: ");
        sb.append(" created ["+getCreated()+"] updated ["+getUpdated()+"] ");

    }

    public void isObjectExist(BasestationStatusRequestAndPermit object) {
        if(object.getPositionId() != null) {
            Connection connection = this.command.getConnection();
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                //pstmt = connection.prepareStatement("SELECT * FROM smss_gsm_permits WHERE positionid = ? AND type = ? AND frequency = ?");
                pstmt = connection.prepareStatement("SELECT * FROM smss_gsm_permits WHERE position_name = ? AND type = ? AND frequency = ? AND region_code = ?");
                pstmt.setString(1, object.getBsNumber());
                pstmt.setInt(2, object.getCurrentGSMDCS());
                pstmt.setInt(3, object.getCurrent9001800());
                pstmt.setString(4, object.getSupRegCode());
                rs = pstmt.executeQuery();
                if(rs.next()) {
                    Integer id = null;
                    int idInt = rs.getInt("id");
                    if(!rs.wasNull()) id = new Integer(idInt);
                    object.setIdent(id);
                    int used = rs.getInt("not_used");
                    Integer notUsed = null;
                    if(!rs.wasNull()) notUsed = new Integer(used);
                    object.setUsed(notUsed);
                    String nriNumber = rs.getString("nri_pos_name");
                    object.setNriNumber(nriNumber);
                }
            } catch(Exception e) {
                if(e instanceof SQLException) {
                    System.out.println("---Code ["+((SQLException)e).getErrorCode()+"]");
                }
                e.printStackTrace();
            } finally {
                try { if(rs != null) rs.close(); } catch(Exception e) {}
                try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
            }
        }
    }

    protected IFileInfo [] getFiles(String dirName) throws BaseException {
        File dir = new File(dirName);
        if(!dir.isDirectory()) return new SimpleFileInfo[] {};
        SimpleDirInfo dirInfo = new SimpleDirInfo(dir);
        return dirInfo.getFiles();
    }


    protected Collection findRequestAndPermit(Sheet sheet, String type) {
        ArrayList positions = new ArrayList();
        Cell [] title = sheet.getRow(0);
        int nameColumn = getColumnNumberByName(title, "название бс", 0);
        int regionNameColumn = getColumnNumberByName(title, REGION_NAME, 0);
        int onColumn = getColumnNumberByName(title, ON, 0);
        int declaratedColumn = getColumnNumberByName(title, DECLARATED, onColumn);
        int decisionColumn = getColumnNumberByName(title, DECISION, declaratedColumn);
        int licenceColumn = getColumnNumberByName(title, LICENCE, decisionColumn);
        System.out.println("["+onColumn+"] ["+declaratedColumn+"] ["+decisionColumn+"] ["+licenceColumn+"] ");
        if(onColumn == -1) {
            log("Cannot find column '"+ON+"'" );
            addLogMessage("Cannot find column '"+ON+"'", "E", null);
        }
        if(declaratedColumn == -1) {
            log("Cannot find column '"+DECLARATED+"'" );
            addLogMessage("Cannot find column '"+DECLARATED+"'", "E", null);
        }
        if(decisionColumn == -1) {
            log("Cannot find column '"+DECISION+"'" );
            addLogMessage("Cannot find column '"+DECISION+"'", "E", null);
        }
        if(licenceColumn == -1) {
            log("Cannot find column '"+LICENCE+"'" );
            addLogMessage("Cannot find column '"+LICENCE+"'", "E", null);
        }
        title = null;
        //get basestation numbers
        Cell [] columns = sheet.getColumn(0);
        for(int i = 3; i < columns.length; i++) {
            Cell column = columns[i];
            //if(column.isHidden()) continue;
            String siteNumberStr = column.getContents();
            String siteName = sheet.getCell(nameColumn, i).getContents();
            if(siteName != null) siteName = siteName.trim();
            if(siteName != null && siteName.trim().length() == 0) siteName = null;
            String isOn = sheet.getCell(onColumn, i).getContents();
            //System.out.println("BS ["+siteNumberStr+"] ["+isOn+"] ");
            if(isOn == null) isOn = "";
            if((isOn.trim().length() == 0 || isOn.equals("1") || isOn.equals("0"))) {
                Integer siteNumber = getSiteNumber(siteNumberStr);
                System.out.println("!!! ["+i+"] ["+currentFileName+"] ["+currentSheetName+"] ["+siteNumberStr+"] ["+siteNumber+"]  ["+siteName+"]  HID["+column.isHidden()+"]");
                if(siteNumber != null) {
					if(siteNumber.equals(new Integer(0)))
						continue;
                    addFounded();
                    //real site number
                    BasestationStatusRequestAndPermit obj = new BasestationStatusRequestAndPermit();
                    obj.setBsNumber(siteNumberStr);
                    obj.setCurrent9001800(current9001800);
                    obj.setCurrentGSMDCS(currentGSMDCS);
                    obj.setSupRegCode(currentSupRegCode);
                    if(regionNameColumn != -1)
                        obj.setRegionName(sheet.getCell(regionNameColumn, i).getContents());
//                    isObjectExist(obj);
//                    if(obj.getUsed() != null) {
//                        addSkiped();
//                        continue;
//                    }
                    Integer positionId = null;
                    if(obj.getNriNumber() == null)
                        positionId = findStoragePlace(siteNumber, getNumberFromString(isOn), siteName);
                    else
                        if(getSiteNumber(obj.getNriNumber()) != null)
                            positionId = findStoragePlace(getSiteNumber(obj.getNriNumber()), getNumberFromString(isOn), siteName);
                    obj.setPositionId(positionId);

                    //if(positionId != null) {
                        addProceeded();
                        if(current9001800 == 0) obj.set900(true);
                        if(currentGSMDCS == 0 || currentGSMDCS == 1) obj.setGSM(true);
                        obj.setBsName(siteName);
                        String declarated = sheet.getCell(declaratedColumn, i).getContents();
                        obj.setDeclarated(declarated);
                        String decision = sheet.getCell(decisionColumn, i).getContents();
                        if(decision != null) decision = decision.trim();
                        obj.setDecisionNumber(decision);
                        obj.setOn(isOn);
                        String licence = sheet.getCell(licenceColumn, i).getContents();
                        if(licence != null) licence = licence.trim();
                        if(licence != null && licence.trim().length() == 0) licence = null;
                        obj.setLicenceNumber(licence);
                        System.out.println("!!! ["+currentFileName+"] ["+currentSheetName+"] ["+obj.getBsName()+"] ");
                        positions.add(obj);
                    //} else {
                        //addSkiped();
                    //}
                }
            } else break;
        }
        StringBuffer sb = new StringBuffer();
        sb.append("File [");
        sb.append(currentFileName);
        sb.append("] sheet [");
        sb.append(currentSheetName);
        sb.append("] : info: ");
        sb.append(" proceed ["+getProceeded()+"] skiped ["+getSkiped()+"] ");

        return positions;
    }

    protected Integer findStoragePlace(Integer gsmId, Integer isOn, String name) {
        //если ошибка то сообщать сразу !
        Integer result = null;
        Connection connection = this.command.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = -1;
        int positionId = -1;
        if(gsmId == null) {
            addLogMessage("position for ["+gsmId+"] not found ["+name+"]", "E", null);
            return null;
        }
        StringBuffer gsmIdStr = new StringBuffer();
        gsmIdStr.append(gsmId);
        boolean is5 = false;
        if(gsmId.toString().length() <= 5) is5 = true;
        if(gsmId.toString().length() < 4) {
            int c = gsmId.toString().length();
            for(int i = c; i < 4; i++) {
                gsmIdStr.insert(0, "0");
            }
        }
        if(!(currentSupRegCode.equals("2") || currentSupRegCode.equals("0")))
            if(gsmId.toString().length() == 4) gsmIdStr.insert(0, currentSupRegCode);

        try {
            StringBuffer sb = new StringBuffer();
            sb.append(" SELECT nvl(count(storageplace),0) as c, nvl(min(storageplace),0) as s ");
            sb.append(" FROM positions p, regions r, superregions s WHERE p.gsmid = ? AND p.regionid = r.regionid AND r.supregid = s.supregid AND s.supregcode = ?");
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setInt(1, (new Integer(gsmIdStr.toString())).intValue());
            pstmt.setInt(2, (new Integer(currentSupRegCode)).intValue());
            rs = pstmt.executeQuery();
            if(rs.next()) {
                count = rs.getInt("c");
                positionId = rs.getInt("s");
            }
        } catch(SQLException e) {
            System.out.println("---Code ["+e.getErrorCode()+"]");
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
        }
        if(count > 1) {
            addLogMessage("Too many position for ["+gsmIdStr.toString()+"] ["+name+"]", "E", null);
        } else {
            if(count == 0) {
                addLogMessage("position for ["+gsmIdStr.toString()+"] not found ["+name+"]", "E", null);
            } else {
                if(positionId != -1) result = new Integer(positionId);
            }
        }

        if(result == null && is5) {
            gsmIdStr = new StringBuffer();
            gsmIdStr.append(gsmId);
            String gsmId1 = null;
            if(gsmIdStr.length() == 5) gsmId1 = gsmIdStr.substring(1);
            else gsmId1 = gsmIdStr.toString();
            try {
                StringBuffer sb = new StringBuffer();
                sb.append(" SELECT nvl(count(storageplace),0) as c, nvl(min(storageplace),0) as s ");
                sb.append(" FROM positions p, regions r, superregions s WHERE p.gsmid = ? AND p.regionid = r.regionid AND r.supregid = s.supregid AND s.supregcode = ?");
                pstmt = connection.prepareStatement(sb.toString());
                pstmt.setInt(1, (new Integer(gsmId1)).intValue());
                pstmt.setInt(2, (new Integer(currentSupRegCode)).intValue());
                rs = pstmt.executeQuery();
                if(rs.next()) {
                    count = rs.getInt("c");
                    positionId = rs.getInt("s");
                }
            } catch(SQLException e) {
                System.out.println("---Code ["+e.getErrorCode()+"]");
                e.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                try { if(rs != null) rs.close(); } catch(Exception e) {}
                try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
            }
            if(count > 1) {
                addLogMessage("Too many position for ["+gsmId1+"] ["+name+"]", "E", null);
            } else {
                if(count == 0) {
                    addLogMessage("position for ["+gsmId1+"] not found ["+name+"]", "E", null);
                } else {
                    if(positionId != -1) result = new Integer(positionId);
                }
            }
        }

/*
        if(gsmId.toString().length() == 4) gsmIdStr.insert(0, currentSupRegCode);

        try {
            StringBuffer sb = new StringBuffer();
            sb.append(" SELECT nvl(count(storageplace),0) as c, nvl(min(storageplace),0) as s ");
            sb.append(" FROM positions WHERE gsmid = ? ");
            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setInt(1, (new Integer(gsmIdStr.toString())).intValue());
            rs = pstmt.executeQuery();
            if(rs.next()) {
                count = rs.getInt("c");
                positionId = rs.getInt("s");
            }
        } catch(SQLException e) {
            System.out.println("---Code ["+e.getErrorCode()+"]");
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
        }
        if(count > 1) {
            addLogMessage("Too many position for ["+gsmIdStr.toString()+"]", "E", null);
        } else {
            if(count == 0) {
                addLogMessage("position for ["+gsmIdStr.toString()+"] not found", "E", null);
            } else {
                if(positionId != -1) result = new Integer(positionId);
            }
        }
*/

/*
        try {
            StringBuffer sb = new StringBuffer();
            sb.append(" SELECT nvl(count(storageplace),0) as c, nvl(min(storageplace),0) as s ");
            sb.append("  FROM positions p, basestations b, equipment e WHERE p.gsmid = ? and p.storageplace = e.position");
            sb.append(" AND e.equipment = b.equipment AND b.type IN ( \"S\" ,\"G\", \"C\" )");

            pstmt = connection.prepareStatement(sb.toString());
            pstmt.setInt(1, (new Integer(gsmIdStr.toString())).intValue());
            rs = pstmt.executeQuery();
            if(rs.next()) {
                count = rs.getInt("c");
                positionId = rs.getInt("s");
            }
        } catch(SQLException e) {
            System.out.println("---Code ["+e.getErrorCode()+"]");
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
        }
        if(count == -1 && positionId == -1) {
            addLogMessage("Error finding basestation for ["+gsmIdStr.toString()+"]", "E", null);
        } else {
            if(count > 1) {
                addLogMessage("Too many basestation for ["+gsmIdStr.toString()+"]", "E", null);
                count = -1;
                positionId = -1;
                try {
                    StringBuffer sb = new StringBuffer();
                    sb.append(" SELECT nvl(count(storageplace),0) as c, nvl(min(storageplace),0) as s ");
                    sb.append("  FROM positions p, basestations b, equipment e WHERE p.gsmid = ? and p.storageplace = e.position");
                    sb.append(" AND e.equipment = b.equipment ");
                    if(current9001800 == 0) {
                        sb.append(" AND b.type IN ( \"S\" ,\"G\" )");
                    } else {
                        sb.append(" AND b.type IN ( \"G\", \"C\" )");
                    }
                    pstmt = connection.prepareStatement(sb.toString());
                    pstmt.setInt(1, (new Integer(gsmIdStr.toString())).intValue());
                    rs = pstmt.executeQuery();
                    if(rs.next()) {
                        count = rs.getInt("c");
                        positionId = rs.getInt("s");
                    }
                } catch(SQLException e) {
                    System.out.println("---Code ["+e.getErrorCode()+"]");
                    e.printStackTrace();
                } catch(Exception e) {
                    e.printStackTrace();
                } finally {
                    try { if(rs != null) rs.close(); } catch(Exception e) {}
                    try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
                }
                if(count > 1) {
                    addLogMessage("Too many basestation for ["+gsmIdStr.toString()+"] ["+(((currentGSMDCS == 0) || (currentGSMDCS == 1))?"GSM":"DCS")+"]["+(((current9001800 == 0))?"900":"1800")+"]", "E", null);
                } else {
                    if(count == 0) {
                        addLogMessage("basestation for ["+gsmIdStr.toString()+"] ["+(((currentGSMDCS == 0) || (currentGSMDCS == 1))?"GSM":"DCS")+"]["+(((current9001800 == 0))?"900":"1800")+"] not found", "E", null);
                    } else {
                        if(positionId != -1) result = new Integer(positionId);
                    }
                }

            } else {
                if(count == 0) {
                    addLogMessage("basestaion for ["+gsmIdStr.toString()+"] not found", "E", null);
                    try {
                        StringBuffer sb = new StringBuffer();
                        sb.append(" SELECT nvl(count(storageplace),0) as c, nvl(min(storageplace),0) as s ");
                        sb.append(" FROM positions WHERE gsmid = ? ");
                        pstmt = connection.prepareStatement(sb.toString());
                        pstmt.setInt(1, (new Integer(gsmIdStr.toString())).intValue());
                        rs = pstmt.executeQuery();
                        if(rs.next()) {
                            count = rs.getInt("c");
                            positionId = rs.getInt("s");
                        }
                    } catch(SQLException e) {
                        System.out.println("---Code ["+e.getErrorCode()+"]");
                        e.printStackTrace();
                    } catch(Exception e) {
                        e.printStackTrace();
                    } finally {
                        try { if(rs != null) rs.close(); } catch(Exception e) {}
                        try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
                    }
                    if(count > 1) {
                        addLogMessage("Too many position for ["+gsmIdStr.toString()+"]", "E", null);
                    } else {
                        if(count == 0) {
                            addLogMessage("position for ["+gsmIdStr.toString()+"] not found", "E", null);
                        } else {
                            if(positionId != -1) result = new Integer(positionId);
                        }
                    }
                } else {
                    if(positionId != -1) result = new Integer(positionId);
                }
            }
        }
*/
        return result;
    }


    protected int getColumnNumberByName(Cell [] title, String name, int start) {
        if(start == -1) start = 0;
        int result = -1;
        //System.out.println("------------------------------------");
        for(int i = start; i < title.length; i++) {
            Cell cell = title[i];
            String content = cell.getContents();
            //System.out.println(" ["+content.trim().toLowerCase()+"] ["+name+"]");
            if(content != null && content.trim().toLowerCase().startsWith(name)) {
                result = i;
                break;
            }
        }
        return result;
    }

    protected Integer getSiteNumber(String siteNumber) {
        Integer result = null;
        if(isValid(siteNumber)) {
            try {
                result = new Integer(siteNumber);
            } catch(Exception e) {}
        }
        return result;
    }

    protected Integer getNumberFromString(String s) {
        Integer result = new Integer(0);
        if(isValid(s)) {
            try {
                result = new Integer(s);
            } catch(Exception e) {}
        }
        return result;
    }

    protected void addLogMessage(String argMessageText, String argMessageType, BasestationStatusRequestAndPermit object) {
/*
        Connection connection = this.command.getLogConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
*/
        StringBuffer sb = new StringBuffer();
        sb.append("File [");
        sb.append(currentFileName);
        sb.append("] sheet [");
        sb.append(currentSheetName);
        sb.append("] : info: ");
        sb.append(argMessageText );
/*
        try {
            String sql = "INSERT INTO JOIN_DB_querylog(idquery, typemsg, txtmsg) VALUES (?, ?, ?)";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, this.command.getIdQuery().intValue());
            pstmt.setString(2, argMessageType);
            pstmt.setString(3, sb.toString());
            pstmt.executeUpdate();
        } catch(Exception e) {
            if(e instanceof SQLException) {
                System.out.println("---Code ["+((SQLException)e).getErrorCode()+"]");
            }
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
            connection = null;
        }
*/
        System.out.println(sb.toString());
    }

    protected void log(String argMessageText) {
        ///System.out.println("File ["+currentFileName+"] sheet ["+currentSheetName+"] : info: " + argMessageText);
    }

    private String getSupRegCode(String name) {
		//   /^.*ПИ/i
		name = name.toLowerCase();
		Pattern pattern = Pattern.compile("^.*москва.*$");
		Matcher matcher = pattern.matcher(name);
		if(matcher.matches()) {
			return "0";
		} else {
			pattern = Pattern.compile("^.*северо-запад.*$");
			matcher = pattern.matcher(name);
			if(matcher.matches()) {
				return "1";
			} else {
				pattern = Pattern.compile("^.*центр.*$");
				matcher = pattern.matcher(name);
				if(matcher.matches()) {
					return "2";
				} else {
					pattern = Pattern.compile("^.*черно.*$");
					matcher = pattern.matcher(name);
					if(matcher.matches()) {
						return "3";
					} else {
						pattern = Pattern.compile("^.*сибир.*$");
						matcher = pattern.matcher(name);
						if(matcher.matches()) {
							return "4";
						} else {
							pattern = Pattern.compile("^.*повол.*$");
							matcher = pattern.matcher(name);
							if(matcher.matches()) {
								return "5";
							} else {
								pattern = Pattern.compile("^.*кавка.*$");
								matcher = pattern.matcher(name);
								if(matcher.matches()) {
									return "6";
								} else {
									pattern = Pattern.compile("^.*восток.*$");
									matcher = pattern.matcher(name);
									if(matcher.matches()) {
										return "8";
									} else {
										pattern = Pattern.compile("^.*урал.*$");
										matcher = pattern.matcher(name);
										if(matcher.matches()) {
											return "7";
										} else {
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
    }


    public int getProceeded() {
        return proceeded;
    }

    public void addProceeded() {
        this.proceeded++;
    }

    public int getSkiped() {
        return skiped;
    }

    public void addSkiped() {
        this.skiped++;
    }

    public int getFounded() {
        return founded;
    }

    public void addFounded() {
        this.founded++;
    }

    public int getUpdated() {
        return updated;
    }

    public void addUpdated() {
        this.updated++;
    }

    public int getCreated() {
        return created;
    }

    public void addCreated() {
        this.created++;
    }

}
