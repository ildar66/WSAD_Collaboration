package com.hps.framework.utils;

import java.util.Date;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public final class Win32FileAttributes {
    public static native long getCreationDate(byte[] path);

    public static native long getAccessDate(byte[] path);

    private static byte[] getFileName(byte[] original) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(original.length+1);
        bos.write(original);
        bos.write(0);
        return bos.toByteArray();
    }

    public static long getCreationDateAsLong(File file) {
        try {
            byte[] original =  file.getAbsolutePath().getBytes("Cp1251");
            byte[] fileName = getFileName(original);
            return getCreationDate(fileName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return 0;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return 0;
        }
    }

    public static long getAccessDateAsLong(File file) {
        try {
            byte[] original =  file.getAbsolutePath().getBytes("Cp1251");
            byte[] fileName = getFileName(original);
            return getAccessDate(fileName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return 0;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return 0;
        }
    }

    private Win32FileAttributes() {
    }

    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy HH:mm:ss.SSS");
        File file = new File("F:\\Beeline\\docs\\fotos\\files\\003_Маросейка\\2001_09_27\\P1010022.jpg");
        for(int i=0;i<100000000;i++) {
            long creationDateTime = Win32FileAttributes.getCreationDateAsLong(file);
            System.out.println(file.getPath() + " created: " + dateFormat.format(new Date(creationDateTime)));
        }
    }
    static {
        System.loadLibrary("Win32FileAttributes");
    }
}