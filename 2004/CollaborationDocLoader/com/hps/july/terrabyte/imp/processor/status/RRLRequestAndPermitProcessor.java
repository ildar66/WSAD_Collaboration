package com.hps.july.terrabyte.imp.processor.status;

import com.hps.july.terrabyte.imp.command.Command;
import com.hps.july.terrabyte.imp.command.CommandNames;
import com.hps.july.terrabyte.imp.processor.AbstractProcessor;
import com.hps.july.terrabyte.imp.essence.status.BasestationStatusRequestAndPermit;
import com.hps.july.terrabyte.imp.essence.status.RRLStatusRequestAndPermit;
import com.hps.july.terrabyte.imp.essence.SimpleDirInfo;
import com.hps.july.terrabyte.imp.essence.SimpleFileInfo;
import com.hps.july.terrabyte.imp.essence.IFileInfo;
import com.hps.july.terrabyte.imp.AppLog;
import com.hps.beeline.LoaderException;
import com.hps.framework.exception.BaseException;
import jxl.Workbook;
import jxl.Sheet;
import jxl.Cell;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.sql.*;

/**
 * ��������� �������������� ����� � ����������� � ������� � ����������� �� ���
 * �� ������������ �������
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 09.11.2005
 * Time: 11:22:23
 */
public class RRLRequestAndPermitProcessor extends AbstractProcessor {

    private Command command = null;
    public static String ANALYSIS = "������ �� ���������";
    public static String REQUESTS = "������";

    public static String ON = "��������";
    public static String DECLARATED = "��������"; //��������
    public static String DECISION = "� ����������"; //� ���������� ����
    public static String LICENCE = "� ����������"; //� ����������


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

    private static HashMap filials = new HashMap();

    static {    
        filials.put("C������", new Integer(92));
        filials.put("������", new Integer(28));
        filials.put("�����. ����", new Integer(42));
        filials.put("�����������", new Integer(52));
        filials.put("���������", new Integer(29));
        filials.put("��������", new Integer(12));
        filials.put("������", new Integer(2));
        filials.put("��������", new Integer(3));
        filials.put("���������", new Integer(30));
        filials.put("�������", new Integer(53));
        filials.put("�������", new Integer(13));
        filials.put("��������", new Integer(31));
        filials.put("������������", new Integer(67));
        filials.put("�������", new Integer(4));
        filials.put("������", new Integer(69));
        filials.put("���������", new Integer(32));
        filials.put("������-���", new Integer(27));
        filials.put("���������-��������", new Integer(33));
        filials.put("������", new Integer(25));
        filials.put("��������", new Integer(34));
        filials.put("������", new Integer(5));
        filials.put("���������-���������", new Integer(35));
        filials.put("��������", new Integer(43));
        filials.put("�����", new Integer(62));
        filials.put("����", new Integer(56));
        filials.put("��������", new Integer(6));
        filials.put("���������", new Integer(36));
        filials.put("����������", new Integer(44));
        filials.put("������", new Integer(64));
        filials.put("�����", new Integer(14));
        filials.put("������", new Integer(15));
        filials.put("������", new Integer(1));
        filials.put("��������", new Integer(58));
        filials.put("�.��������", new Integer(21));
        filials.put("�������� ��", new Integer(57));
        filials.put("��������", new Integer(59));
        filials.put("�����������", new Integer(45));
        filials.put("����", new Integer(46));
        filials.put("����", new Integer(16));
        filials.put("��������", new Integer(65));
        filials.put("�����", new Integer(22));
        filials.put("�����", new Integer(66));
        filials.put("������������", new Integer(55));
        filials.put("�����", new Integer(60));
        filials.put("����. �����", new Integer(41));
        filials.put("������ �� ����", new Integer(37));
        filials.put("������", new Integer(7));
        filials.put("�-���������", new Integer(61));
        filials.put("������", new Integer(23));
        filials.put("�������", new Integer(20));
        filials.put("�������", new Integer(24));
        filials.put("���. ������", new Integer(38));
        filials.put("��������", new Integer(8));
        filials.put("����������", new Integer(39));
        filials.put("������ ��", new Integer(72));
        filials.put("������", new Integer(17));
        filials.put("�����", new Integer(9));
        filials.put("�����", new Integer(48));
        filials.put("����", new Integer(10));
        filials.put("����", new Integer(49));
        filials.put("������", new Integer(68));
        filials.put("���������", new Integer(26));
        filials.put("���", new Integer(18));
        filials.put("�������", new Integer(50));
        filials.put("�����-��������", new Integer(70));
        filials.put("���������", new Integer(19));
        filials.put("���������", new Integer(71));
        filials.put("����", new Integer(72));
        filials.put("���������", new Integer(11));

    }


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

                Sheet [] sheets = workbook.getSheets();
                for(int i = 0; i < sheets.length; i++) {
                    Sheet sheet = sheets[i];
                    String sheetName = sheet.getName().trim();
                    if(sheetName.equals(ANALYSIS) ||
                            sheetName.equals(REQUESTS)) {
                        sheet = null;
                        continue;
                    }

                    currentSheetName = sheetName;
                    //System.out.println("["+currentFileName+"] ["+currentSheetName+"]");
                    Collection rrl = findRRLRequestAndPermit(sheet, "1");
                    store(rrl);
                    rrl = null;
                }
                sheets = null;
                //log("end processing !");
                try { if(workbook != null) workbook.close(); } catch(Exception e) {}
                try { if(file != null) file.close(); } catch(Exception e) {}
                workbook = null;
                file = null;
            }

            //Sheet gsm900 = workbook.getSheet("�� GSM-900");

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
        Connection connection = this.command.getLogConnection();
        PreparedStatement pstmt = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("DELETE FROM smss_rrl_permits ");
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
            RRLStatusRequestAndPermit obj = (RRLStatusRequestAndPermit) iterator.next();
            //isObjectExist(obj);
            Connection connection = this.command.getLogConnection();
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                if(obj.getIdent() == null) {
                    //System.out.println("Create ["+currentFileName+"] ["+currentSheetName+"] ["+obj.getBsNumber()+"] ["+obj.getSupRegCode()+"]["+obj.getCurrent9001800()+"]["+obj.getCurrentGSMDCS()+"] ["+obj.getPositionId()+"] ["+getNumberFromString(obj.getOn())+"] ["+getNumberFromString(obj.getDeclarated())+"] ["+obj.getDecisionNumber()+"] ["+obj.getLicenceNumber()+"] ");

                    //create
                    StringBuffer sql = new StringBuffer();
                    sql.append("INSERT INTO smss_rrl_permits ");
                    sql.append(" (positionid1, positionid2, address1, address2, ");
                    sql.append("  bs_on, bs_declarated, bs_decision_number, bs_licence_number, ");
                    sql.append("  position_name, region_code, ");
                    sql.append("  city, range, equip_name, annul, air, request,  ");
                    sql.append("  was_error, interval, sitenumber1, sitenumber2,   ");
                    sql.append("  filialid ) ");
                    sql.append(" VALUES ");
                    sql.append(" (?, ?, ?, ?, ?, ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ");
                    pstmt = connection.prepareStatement(sql.toString());
                    setInteger(pstmt, 1, obj.getPositionId1());
                    setInteger(pstmt, 2, obj.getPositionId2());
                    setString(pstmt, 3, obj.getAddress1());
                    setString(pstmt, 4, obj.getAddress2());
                    setInteger(pstmt, 5, obj.getOn());
                    setInteger(pstmt, 6, getNumberFromString(obj.getDeclarated()));
                    setString(pstmt, 7, obj.getDecisionNumber());
                    setString(pstmt, 8, obj.getLicenceNumber());
                    setString(pstmt, 9, obj.getInterval());
                    setString(pstmt, 10, obj.getSupRegCode());
                    setString(pstmt, 11, obj.getRegionName());
                    //setFilial(pstmt, 12, obj.getRange());
                    setString(pstmt, 12, obj.getRangeNumber());
                    setString(pstmt, 13, obj.getEquipment());
                    setInteger(pstmt, 14, obj.getAnnul());
                    setInteger(pstmt, 15, obj.getAir());
                    setString(pstmt, 16, obj.getRequest());
                    setInteger(pstmt, 17, obj.getWasError());
                    setString(pstmt, 18, obj.getInterval());
                    setString(pstmt, 19, obj.getSiteNumber1());
                    setString(pstmt, 20, obj.getSiteNumber2());
                    setInteger(pstmt, 21, obj.getFilial());
                    pstmt.executeUpdate();
                    addCreated();
                } else {
                    ///System.out.println("Update ["+currentFileName+"] ["+currentSheetName+"] ["+obj.getBsNumber()+"]["+obj.getSupRegCode()+"]["+obj.getCurrent9001800()+"]["+obj.getCurrentGSMDCS()+"]["+obj.getPositionId()+"] ["+getNumberFromString(obj.getOn())+"] ["+getNumberFromString(obj.getDeclarated())+"] ["+obj.getDecisionNumber()+"] ["+obj.getLicenceNumber()+"] ");
                    //update
/*
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
*/
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
    }

    public void isObjectExist(BasestationStatusRequestAndPermit object) {
        if(object.getPositionId() != null) {
            Connection connection = this.command.getLogConnection();
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


    protected Collection findRRLRequestAndPermit(Sheet sheet, String type) {
        ArrayList positions = new ArrayList();
        Cell [] title = sheet.getRow(0);
        int airColumn = getColumnNumberByName(title, "� �����", 0);
        int anullColumn = getColumnNumberByName(title, "�������", 0);
        int regionColumn = 0;//getColumnNumberByName(title, "������", 0);
        int intervalColumn = getColumnNumberByName(title, "��������", 0);
        int equipColumn = getColumnNumberByName(title, "�����", 0);
        int rangeColumn = getColumnNumberByName(title, "��������", 0);
        int address1Column = getColumnNumberByName(title, "�����", 0);
        int address2Column = getColumnNumberByName(title, "�����", address1Column + 1);
        int onColumn = getColumnNumberByName(title, ON, 0);
        int declaratedColumn = getColumnNumberByName(title, DECLARATED, onColumn);
        int decisionColumn = getColumnNumberByName(title, DECISION, declaratedColumn);
        int licenceColumn = getColumnNumberByName(title, LICENCE, decisionColumn);
        addLogMessage("["+onColumn+"] ["+declaratedColumn+"] ["+decisionColumn+"] ["+licenceColumn+"] ["+airColumn+"] ["+anullColumn+"] ["+intervalColumn+"] ["+equipColumn+"] ["+rangeColumn+"] ["+address1Column+"] ["+address2Column+"] ", "I", null);
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
        //title = null;
        //get basestation numbers
        Cell [] columns = sheet.getColumn(0);
        for(int i = 3; i < columns.length; i++) {
            Cell column = columns[i];
            if(column.isHidden()) continue;
            String siteNames = sheet.getCell(intervalColumn, i).getContents();
            if(siteNames != null && siteNames.trim().length() == 0) siteNames = null;
            String siteName1 = null;
            String siteName2 = null;
            Integer error = new Integer(0);
            if(siteNames != null) {
                siteNames = siteNames.trim();
                try {
                    StringTokenizer st = new StringTokenizer(siteNames, " - ");
                    if(st.countTokens() != 2) error = new Integer(1);
                    siteName1 = st.nextToken().trim();
                    siteName2 = st.nextToken().trim();
                } catch (Exception e) {
                    error = new Integer(1);
                }

            }
            if(siteName1 != null && siteName1.trim().length() == 0) {
                siteName1 = null;
            }
            if(siteName2 != null && siteName2.trim().length() == 0) {
                siteName2 = null;
            }

            String isOn = sheet.getCell(onColumn, i).getContents();
            if(isOn == null) isOn = "";
            //System.out.println("!!! ["+currentFileName+"] ["+currentSheetName+"] ["+siteNames+"] HID["+column.isHidden()+"]");
            if((isOn.trim().length() == 0 || isOn.equals("1") || isOn.equals("0"))) {
                Integer siteNumber1 = getSiteNumber(siteName1);
                Integer siteNumber2 = getSiteNumber(siteName2);
                if(siteNumber1 == null || siteNumber2 == null) error = new Integer(1);
                if(siteNames != null) {
                    addFounded();
                    //real site number
                    RRLStatusRequestAndPermit obj = new RRLStatusRequestAndPermit();
                    //
                    obj.setSiteNumber(siteNames);
                    obj.setSiteNumber1(siteName1);
                    obj.setSiteNumber2(siteName2);
                    obj.setInterval(siteNames);
                    obj.setSupRegCode(currentSupRegCode);
                    String region = sheet.getCell(regionColumn, i).getContents();
                    if(region != null) region = region.trim();
                    obj.setRegionName(region);
                    obj.setFilial((Integer)filials.get(region));
                    String annul = sheet.getCell(anullColumn, i).getContents();
                    if(annul != null && annul.trim().length() == 0) annul = null;
                    if(annul != null && annul.equals("+")) annul = "1";
                    obj.setAnnul(getNumberFromString(annul));
                    String air = sheet.getCell(airColumn, i).getContents();
                    if(air != null && air.trim().length() == 0) air = null;
                    if(air != null && air.equals("+")) air = "1";
                    obj.setAir(getNumberFromString(air));
                    String address1 = sheet.getCell(address1Column, i).getContents();
                    obj.setAddress1(address1);
                    String address2 = sheet.getCell(address2Column, i).getContents();
                    obj.setAddress2(address2);
                    String equipment = sheet.getCell(equipColumn, i).getContents();
                    obj.setEquipment(equipment);
                    String range = sheet.getCell(rangeColumn, i).getContents();
                    obj.setRange(getNumberFromString(range));
                    obj.setRangeNumber(range);

                    StringBuffer request = null;
                    for(int j = (airColumn + 1); j < onColumn; j++) {
                        String set = sheet.getCell(j, i).getContents();
                        if(set != null && set.trim().length() > 0) {
                            if(set.equals("1")) {
                                String preRequest = title[j].getContents();
                                if(request == null) request = new StringBuffer(preRequest);
                                else request.append(";").append(preRequest);
                            }
                        }
                    }
                    if(request != null) obj.setRequest(request.toString());

/*
                    isObjectExist(obj);
                    if(obj.getUsed() != null) {
                        addSkiped();
                        continue;
                    }
*/
                    Integer positionId1 = null;
                    Integer positionId2 = null;
                    positionId1 = findStoragePlace(siteNumber1, getNumberFromString(isOn), siteNames);
                    obj.setPositionId1(positionId1);
                    positionId2 = findStoragePlace(siteNumber2, getNumberFromString(isOn), siteNames);
                    obj.setPositionId2(positionId2);
                    //errors
                    if(positionId1 == null) error = new Integer(2);
                    if(positionId2 == null) error = new Integer(3);
                    if(positionId1 == null && positionId2 == null) error = new Integer(4);
                    //licence !!
                    obj.setOn(getNumberFromString(isOn));
                    String declarated = sheet.getCell(declaratedColumn, i).getContents();
                    obj.setDeclarated(declarated);
                    String decision = sheet.getCell(decisionColumn, i).getContents();
                    if(decision != null) decision = decision.trim();
                    obj.setDecisionNumber(decision);
                    String licence = sheet.getCell(licenceColumn, i).getContents();
                    if(licence != null) licence = licence.trim();
                    if(licence != null && licence.trim().length() == 0) licence = null;
                    obj.setLicenceNumber(licence);

                    obj.setWasError(error);
                    //System.out.println("!!! ["+currentFileName+"] ["+currentSheetName+"] ["+obj.getInterval()+"] ["+obj.getRegionName()+"] ");
                    positions.add(obj);



                    //address !!

/*
                    if(positionId1 != null) {
                        addProceeded();
                        obj.setInterval(siteNames);
                        String declarated = sheet.getCell(declaratedColumn, i).getContents();
                        obj.setDeclarated(declarated);
                        String decision = sheet.getCell(decisionColumn, i).getContents();
                        if(decision != null) decision = decision.trim();
                        obj.setDecisionNumber(decision);
                        String licence = sheet.getCell(licenceColumn, i).getContents();
                        if(licence != null) licence = licence.trim();
                        obj.setLicenceNumber(licence);
                        //System.out.println("!!! ["+currentFileName+"] ["+currentSheetName+"] ["+obj.getSiteNumber()+"] ["+obj.getSupRegCode()+"]["+obj.getCurrent9001800()+"]["+obj.getCurrentGSMDCS()+"] ["+obj.getPositionId1()+"] ["+getNumberFromString(obj.getOn())+"] ["+getNumberFromString(obj.getDeclarated())+"] ["+obj.getDecisionNumber()+"] ["+obj.getLicenceNumber()+"] ");
                        positions.add(obj);
                    } else {
                        addSkiped();
                    }
*/
                }
            } else break;
        }
        return positions;
    }

    protected Integer findStoragePlace(Integer gsmId, Integer isOn, String name) {
        //���� ������ �� �������� ����� !
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
        Connection connection = this.command.getLogConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
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
        //   /^.*��/i
        name = name.toLowerCase();
        Pattern pattern = Pattern.compile("^.*������.*$");
        Matcher matcher = pattern.matcher(name);
        if(matcher.matches()) {
            return "0";
        } else {
            pattern = Pattern.compile("^.*�-�����.*$");
            matcher = pattern.matcher(name);
            if(matcher.matches()) {
                return "1";
            } else {
                pattern = Pattern.compile("^.*�����.*$");
                matcher = pattern.matcher(name);
                if(matcher.matches()) {
                    return "2";
                } else {
                    pattern = Pattern.compile("^.*�����.*$");
                    matcher = pattern.matcher(name);
                    if(matcher.matches()) {
                        return "3";
                    } else {
                        pattern = Pattern.compile("^.*�����.*$");
                        matcher = pattern.matcher(name);
                        if(matcher.matches()) {
                            return "4";
                        } else {
                            pattern = Pattern.compile("^.*�����.*$");
                            matcher = pattern.matcher(name);
                            if(matcher.matches()) {
                                return "5";
                            } else {
                                pattern = Pattern.compile("^.*�����.*$");
                                matcher = pattern.matcher(name);
                                if(matcher.matches()) {
                                    return "6";
                                } else {
                                    pattern = Pattern.compile("^.*��.*$");
                                    matcher = pattern.matcher(name);
                                    if(matcher.matches()) {
                                        return "8";
                                    } else {
                                        pattern = Pattern.compile("^.*����.*$");
                                        matcher = pattern.matcher(name);
                                        if(matcher.matches()) {
                                            return "7";
                                        } else {
                                            pattern = Pattern.compile("^.*�������.*$");
                                            matcher = pattern.matcher(name);
                                            if(matcher.matches()) {
                                                return "8";
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
