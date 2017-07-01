package com.hps.beeline.imageLoader.helper;

import com.hps.framework.exception.BaseException;
import com.hps.beeline.loader.helpers.DirectoryHelper;
import com.sun.media.jai.codec.*;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.swing.*;
import java.io.*;
import java.awt.image.renderable.ParameterBlock;
import java.awt.image.BufferedImage;
import java.awt.image.AffineTransformOp;
import java.awt.image.RenderedImage;
import java.awt.geom.AffineTransform;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 17.03.2005
 * Time: 13:32:23
 * To change this template use File | Settings | File Templates.
 */
public class ImageHelper {

    private ImageHelper() {
    }

    public static String getMimeType(String fileName, int maxSize ) {
        if(maxSize>0)
            return "image/jpeg";
        else {
            return "image/"+fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
        }
    }


    private static final Set imageExtensions = new HashSet();
    private static final Set nonImageExtensions = new HashSet();
    static{
        imageExtensions.add("gif");
        imageExtensions.add("jpeg");
        imageExtensions.add("jpg");
        imageExtensions.add("tif");
        imageExtensions.add("tiff");
        imageExtensions.add("png");
        imageExtensions.add("bmp");

        nonImageExtensions.add("db");
        nonImageExtensions.add("mpg");
    }


    private static boolean isImage(String fileName, ImageTool ih) {
        String extension = fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
        if(imageExtensions.contains(extension))
            return true;
        if(nonImageExtensions.contains(extension))
            return false;
        return ih.isImage();
    }

    public static boolean isImage(String fileName) {
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if(imageExtensions.contains(ext)) return true;
        else return false;
    }

    public static byte[] compressImage(byte[] data, File file, int maxSize) throws BaseException {
            String fileName = file.getName().toLowerCase();
            String absoluteFileName = file.getAbsolutePath().toLowerCase();
            return compressImage(data, fileName, absoluteFileName, maxSize);
        }

    public static byte[] compressImage(byte[] data, String fileName, String absoluteFileName, int maxSize) throws BaseException {

        if(maxSize<1)
            return data;

        try {
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            ImageTool ih = new ImageTool(new ByteArraySeekableStream(data));
            if(!isImage(fileName, ih))
                return null;
            if(!ih.isImage())
                throw new BaseException("Файл "+absoluteFileName+" не может быть прочитан декодером!");

            if(fileName.indexOf(".png")!=-1 || fileName.indexOf(".gif")!=-1)
                ih.updateColorTable();

            try{
                ih.scaleToFit(maxSize, maxSize);
            }catch(Exception err) {
                err.printStackTrace();
                throw new BaseException("Произошла ошибка изменения размера картинки, см. лог, файл "+absoluteFileName);
            }

            try{
                ih.saveImage(result, "jpeg");
            }catch(Exception err) {
                err.printStackTrace();
                throw new BaseException("Произошла ошибка сохранения картинки, см. лог, файл "+absoluteFileName);
            }
            //ih.saveImageToGif(result);
            return result.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            throw new BaseException("Ошибка ввода вывода", e);
        }
    }

    public static byte[] compressImage(File file, int maxSize) throws BaseException {

        try {
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            ImageTool ih = new ImageTool((SeekableStream)new FileSeekableStream(file));
            if(!isImage(file.getName(), ih))
                return null;
            if(!ih.isImage())
                throw new BaseException("Файл "+file.getAbsolutePath()+" не может быть прочитан декодером!");

            if(file.getName().indexOf(".png")!=-1 || file.getName().indexOf(".gif")!=-1)
                ih.updateColorTable();

            try{
                ih.scaleToFit(maxSize, maxSize);
            }catch(Exception err) {
                err.printStackTrace();
                throw new BaseException("Произошла ошибка изменения размера картинки, см. лог, файл "+file.getAbsolutePath());
            }

            try{
                ih.saveImage(result, "jpeg");
            }catch(Exception err) {
                err.printStackTrace();
                throw new BaseException("Произошла ошибка сохранения картинки, см. лог, файл "+file.getAbsolutePath());
            }
            //ih.saveImageToGif(result);
            return result.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            throw new BaseException("Ошибка ввода вывода", e);
        }
    }



    public static void main(String[] args) throws BaseException, IOException {
        //File file = new File("F:\\Beeline\\docs\\fotos\\files\\002_Театральный проезд\\2003_07_09\\P7090001 copy.gif");
        File file = new File("F:\\052_Академическая\\2000_\\P1010913.JPG");
        byte[] data = DirectoryHelper.loadFile(file);
        byte[] shortData = compressImage(data, file, 1200);
        FileOutputStream fos = new FileOutputStream("C:\\temp\\test.jpeg");
        fos.write(shortData);
        fos.close();
    }
}
