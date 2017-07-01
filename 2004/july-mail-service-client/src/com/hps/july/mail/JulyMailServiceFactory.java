package com.hps.july.mail;

import com.hps.july.mail.service.JulyMailService;
import com.hps.july.mail.service.impl.JulyMailServiceImpl;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * ������� ��� �������� ������� �������� ����� �� ������ ������
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 05.07.2006
 * Time: 16:09:06
 */
public class JulyMailServiceFactory {

    public static final String MAIL_TESTING = "MAIL_TESTING";

    private static JulyMailServiceImpl julyMailService;

    /**
     * ����� ��� ������ ������� �� ������ ����������
     * @return ������ ��� �������� �����
     */
    public static JulyMailService createHttpMailService() {
        if(julyMailService == null) {
            julyMailService = new JulyMailServiceImpl(false);
            julyMailService.setMailService(MailServiceFactory.createHttpMailService());
        }
        return julyMailService;
    }

    /**
     * ����������� ������� ������������ ��� ������� ������ AppUtils
     * ������������ ������ � ���������� july
     * @return ������ ��� �������� �����
     */
    public static JulyMailService createMailService() {
        if(julyMailService == null) {
            boolean isTest = false;
            try {
                Class appUtilsClazz = Class.forName("com.hps.july.util.AppUtils");
                Method getStringMethod = appUtilsClazz.getMethod("getNamedValueString", new Class [] {String.class});
                Object appUtils = appUtilsClazz.newInstance();
                Object isTestObj = getStringMethod.invoke(appUtils, new Object [] { "MAIL_TESTING" });
                if(isTestObj != null) {
                    String isTestStr = (String)isTestObj;
                    isTest = !isTestStr.equals("");
                }
            } catch(Throwable e) {
            }
            System.out.println("RTS_TESTING [" + isTest + "] ");
            julyMailService = new JulyMailServiceImpl(isTest);
            julyMailService.setMailService(MailServiceFactory.createMailService());
        }
        return julyMailService;
    }

    /**
     * ����������� ������� ������������ � ����� ���������� �
     * ������������ �������� � �� july
     * @return ������ ��� �������� �����
     */
    public static JulyMailService createMailService(Connection connection) {
        if(julyMailService == null) {
            boolean isTest = isTestServer(connection);
            System.out.println("RTS_TESTING [" + isTest + "] ");
            julyMailService = new JulyMailServiceImpl(isTest);
            julyMailService.setMailService(MailServiceFactory.createMailService());
        }
        return julyMailService;
    }

    /**
     * ����������� ������� �����
     * @return ������ ��� �������� �����
     */
    public static JulyMailService createMailService(boolean isTest) {
        if(julyMailService == null) {
            julyMailService = new JulyMailServiceImpl(isTest);
            julyMailService.setMailService(MailServiceFactory.createMailService());
        }
        return julyMailService;
    }

    private static boolean isTestServer(Connection connection) {
        boolean isTest = false;
        String charValue = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT charvalue FROM namedvalues WHERE id = ?");
            pstmt = connection.prepareStatement(sql.toString());
            pstmt.setString(1, MAIL_TESTING);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                charValue = rs.getString("charvalue");
                if(rs.wasNull()) charValue = null;
                isTest = (charValue != null && !charValue.equals(""));
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) try { rs.close(); } catch(Exception e) {}
            if(pstmt != null) try { pstmt.close(); } catch(Exception e) {}
        }
        return isTest;
    }

}
