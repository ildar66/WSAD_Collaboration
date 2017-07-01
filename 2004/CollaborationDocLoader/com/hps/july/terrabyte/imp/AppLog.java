package com.hps.july.terrabyte.imp;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 24.06.2005
 * Time: 17:05:47
 */
public class AppLog {

    private String name = "outTerra";
    public AppLog(String process) {
        SimpleDateFormat sdf = new SimpleDateFormat("yy.MM.dd_HH.mm.ss");
        name = process + "_" +name + "_" + sdf.format(new Date()) + ".log";
    }

    public void log(String log) {
        FileOutputStream fos = null;
        try {
            String userHome = null;//System.getProperty("user.home");
            //if(userHome == null) userHome = "";
            String fileName = (userHome == null)?name:(userHome + File.separator + name);
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

    public void log(Throwable t) {
        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            String userHome = null;//System.getProperty("user.home");
            //if(userHome == null) userHome = "";
            String fileName = (userHome == null)?name:(userHome + File.separator + name);
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
