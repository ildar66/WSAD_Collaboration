package com.hps.july.terrabyte.imp;

import java.io.FileOutputStream;
import java.io.File;
import java.io.PrintWriter;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 24.06.2005
 * Time: 17:05:47
 */
public class AppLog1 {

    public static void log(String log) {
        FileOutputStream fos = null;
        try {
            String userHome = null;//System.getProperty("user.home");
            //if(userHome == null) userHome = "";
            String fileName = (userHome == null)?"outTerra.log":(userHome + File.separator + "outTerra.log");
            fos = new FileOutputStream(fileName, true);
            fos.write(log.getBytes());
            fos.write("\n".getBytes());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if(fos != null) fos.close(); } catch(Exception e) {}
        }
    }

    public static void log(Throwable t) {
        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            String userHome = null;//System.getProperty("user.home");
            //if(userHome == null) userHome = "";
            String fileName = (userHome == null)?"outTerra.log":(userHome + File.separator + "outTerra.log");
            fos = new FileOutputStream(fileName, true);
            pw = new PrintWriter(fos);
            t.printStackTrace(pw);
            pw.write("\n");
            pw.flush();
        } catch (Exception e) {
            System.out.println("Kirdik !" + e.toString());
            System.err.println("Kirdik !" + e.toString());
            e.printStackTrace();
        } finally {
            try { if(fos != null) fos.close(); } catch(Exception e) {}
            try { if(pw != null) pw.close(); } catch(Exception e) {}
        }
    }



}
