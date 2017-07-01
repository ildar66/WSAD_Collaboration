package com.hps.beeline.loader.helpers;

import com.hps.beeline.loader.info.ExtDocInfo;
import com.hps.beeline.loader.info.PassListInfo;
import com.hps.beeline.loader.info.SiteDocInfo;
import com.hps.beeline.loader.DocConfig;
import com.hps.beeline.Config;
import com.hps.beeline.Config;
import com.hps.beeline.LogHelper;
import com.hps.framework.exception.BaseException;
import com.hps.framework.sql.StoredProc;
import com.hps.framework.sql.BinaryStreamData;
import com.hps.framework.exception.BaseException;
import com.hps.framework.sql.StoredProc;
import com.hps.framework.sql.BinaryStreamData;

import java.io.*;
import java.util.*;
import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 11.11.2004
 * Time: 14:44:21
 * To change this template use File | Settings | File Templates.
 */
public class DirectoryHelper {
    public static final String WAY_MAPS = "Маршрутные_карты";
    public static final String PASS_LIST = "Списки";

    private static final String FILE_TYPE_GSM = "g";
    private static final String FILE_TYPE_DUMPS = "a";
    private static final String FILE_TYPE_WLAN = "w";

    private static final byte[] temporaryBuffer = new byte[100000];

    private Map dirs = new HashMap();

    public DirectoryHelper() {
        dirs.put(FILE_TYPE_GSM, new HashMap());
        dirs.put(FILE_TYPE_DUMPS, new HashMap());
        dirs.put(FILE_TYPE_WLAN, new HashMap());
    }


    private static DirectoryHelper instance=new DirectoryHelper();
    public static DirectoryHelper getInstance() {
        return instance;
    }


    public void findFile(ExtDocInfo info, String fileType) throws BaseException {
        File dir = findDir(info);
        File subDir = findSubDir(dir, fileType, info);
        File result = getFirstFile(subDir);
        info.setFile(result);
    }

    public void findFiles(ExtDocInfo info, String fileType) throws BaseException {
        File dir = findDir(info);
        File subDir = findSubDir(dir, fileType, info);
        Collection files = getFiles(subDir, info.getStorageplace());
        info.setPassFiles(files);
    }

    private boolean isFileLoadable(File file) {
        String name =file.getName().toUpperCase();
        if(name.endsWith(".tmp") || file.isDirectory())
            return false;
        else
            return true;
    }


    private static final  GregorianCalendar calend = new GregorianCalendar();
    private static int calcYear(long date) {
        calend.setTime(new Date(date));
        return calend.get(GregorianCalendar.YEAR);
    }

    public static int getYear(File file) {
        String fileName = file.getName();
        int pos = fileName.lastIndexOf(".");
        if(pos!=-1)
            fileName = fileName.substring(0,pos);
        if(fileName.length()>=4) {
            String year = fileName.substring(fileName.length()-4);
            try{
                return Integer.parseInt(year);
            }catch(NumberFormatException err) {
                return -1;
            }
        } else
            return -1;
    }

    private Date getEnd(int year) {
        //GregorianCalendar calend= new GregorianCalendar();
        calend.set(GregorianCalendar.DAY_OF_MONTH, 31);
        calend.set(GregorianCalendar.MONTH, 11);
        calend.set(GregorianCalendar.YEAR, year);
        return new Date(calend.getTime().getTime());
    }

    private Collection getFiles(File subDir, Integer storagePlace) {
        File[] files = subDir.listFiles();
        Set sortedList = new TreeSet(new Comparator() {
            public int compare(Object o, Object o1) {
                PassListInfo a = (PassListInfo) o;
                PassListInfo b = (PassListInfo) o1;
                int yearA = getYear(a.getFile());
                int yearB = getYear(b.getFile());
                //System.out.println("year name="+a.getFile().getName()+" a ="+yearA);
                if(yearA==-1) {
                    a.setWarning(true);
                    yearA = calcYear(a.getFile().lastModified());
                }
                if(yearB==-1) {
                    b.setWarning(true);
                    yearB = calcYear(b.getFile().lastModified());
                }
                a.setYear(yearA);
                b.setYear(yearB);

                if(yearA != yearB) {
                    if(yearA > yearB)
                        return 1;
                    else if(yearA == yearB)
                        return 0;
                    else
                        return -1;
                }
                return a.getFileDate().compareTo(b.getFileDate());
            }
        }) ;

        for(int i=0;i<files.length;i++) {
            if(isFileLoadable(files[i])) {
                PassListInfo info = new PassListInfo(files[i]);
                info.setStorageplace(storagePlace);
                sortedList.add(info);
            }
        }

        PassListInfo infoNext = calcFileExpireDates(sortedList);

        if(infoNext!=null) {
            infoNext.setExpireDate(null);
        }
        Collection result = new ArrayList(sortedList);
        return result;
    }

    private PassListInfo calcFileExpireDates(Set sortedList) {
        PassListInfo info=null, infoNext=null;
        PassListInfo[] mas = (PassListInfo[]) sortedList.toArray(new PassListInfo[sortedList.size()]);
        GregorianCalendar calend = new GregorianCalendar();
        for(int i=0;i<mas.length-1;i++) {
            info = mas[i];
            infoNext = mas[i+1];

            //System.out.println("info year="+info.getYear());
            //System.out.println("infoNext year="+infoNext.getYear());

            if(infoNext.getYear() == info.getYear()) {
                calend.setTime(infoNext.getFileDate());
                calend.add(Calendar.DATE, -1);
                info.setExpireDate(new Date(calend.getTime().getTime()));
            } else {
                info.setExpireDate(getEnd(info.getYear()));
            }
            System.out.println("устанавливаем для файла "+info.getFile().getName()+" дату окончания срока действия "+info.getExpireDate());
        }
        return infoNext;
    }

    private File getFirstFile(File subDir) {
        File[] files = subDir.listFiles();
        if(files.length!=0)
            return files[0];
        else
            return null;
    }

    private File findSubDir(File dir, final String fileType,ExtDocInfo info) throws BaseException {
        File [] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File file, String s) {
                if(s.equalsIgnoreCase(fileType))
                    return true;
                else
                    return false;
            }
        });
        if(files.length!=0)
            return files[0];
        else
            throw new BaseException("Не найдена директория "+fileType+" соответствующая записи gsmId="+info.getGsmId()+" dumpsId="+info.getDumpsId()+" wlanId="+info.getWlanId());
    }

    private File findDir(ExtDocInfo info) throws BaseException {
        String basePath = DocConfig.getInstance().getFilePath();
        File baseDir = new File(basePath);
        File result = findSpecificDir(baseDir, FILE_TYPE_GSM, info.getGsmId());
        if(result==null)
            result = findSpecificDir(baseDir, FILE_TYPE_DUMPS, info.getDumpsId());
        if(result==null)
            result = findSpecificDir(baseDir, FILE_TYPE_WLAN, info.getWlanId());
        if(result==null)
            throw new BaseException("Не найдена директория соответствующая записи gsmId="+info.getGsmId()+" dumpsId="+info.getDumpsId()+" wlanId="+info.getWlanId());
        return result;
    }

    private File findSpecificDir(File baseDir, final String fileType, final Integer id) {
        if(id==null)
            return null;

        Map map = (Map) dirs.get(fileType);
        return (File) map.get(id);

    }

   public static synchronized BinaryStreamData loadData(File file) throws  BaseException {
        try{
            return new BinaryStreamData(new FileInputStream(file), (int)file.length());
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new BaseException("Не найден файл "+file.getAbsolutePath());
        }catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            throw new BaseException("Ошибка загрузки файла "+file.getAbsolutePath());
        }
    }


    public static synchronized  byte[] loadFile(File file) throws  BaseException {
        try{
            FileInputStream fis = new FileInputStream(file);
            int count = 0;
            ByteArrayOutputStream bos = new ByteArrayOutputStream((int)file.length());

            while(count!=-1) {
                count = fis.read(temporaryBuffer);
                if(count!=-1)
                    bos.write(temporaryBuffer,0,count);
            }
            return bos.toByteArray();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new BaseException("Не найден файл "+file.getAbsolutePath());
        }catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            throw new BaseException("Ошибка загрузки файла "+file.getAbsolutePath());
        }
    }

    public void loadFile(Integer idQuery, SiteDocInfo info) throws BaseException {
        //byte[] file = loadFile(info.getFile());
        BinaryStreamData file = loadData(info.getFile());
        StoredProc proc = new StoredProc("update sitedocfiles set sitedocfilebody=?," +
                "  sitedocfilename=?, " +
                "  sitedocfiledate=? where sitedocfile=?");
        proc.setObject(1, file);
        proc.setObject(2, info.getFile().getName());
        proc.setObject(3, new Date(info.getFile().lastModified()));
        proc.setObject(4, info.getSitedocfile());
        proc.executeFunctionCall();
    }

    public void init() throws BaseException {
        String idStr;
        Map map = null;
        String basePath = DocConfig.getInstance().getFilePath();
        File dir = new File(basePath);
        File[] files = dir.listFiles();
        if(files==null||!dir.exists())
            throw new BaseException("В указанной директории не найдены папки с файлами, или директория не существует "+basePath);
        for(int i=0;i<files.length;i++) {
            File place = files[i];
            String name = place.getName();
            if(name.startsWith("@"))
                continue;
            if(name.startsWith(FILE_TYPE_DUMPS)) {
                map = (HashMap) dirs.get(FILE_TYPE_DUMPS);
                idStr = name.substring(1,name.indexOf("_"));
            } else if(name.startsWith(FILE_TYPE_WLAN)) {
                map = (HashMap) dirs.get(FILE_TYPE_WLAN);
                idStr = name.substring(1,name.indexOf("_"));
            } else {
                map = (HashMap) dirs.get(FILE_TYPE_GSM);
                idStr = name.substring(0,name.indexOf("_"));
            }

            Integer id = new Integer(idStr);
            map.put(id,place);
        }
    }

    public void endWork() {
        dirs = null;
    }

    public void loadFiles(Integer idQuery, Iterator iterator) throws BaseException {
        while(iterator.hasNext()) {
            PassListInfo info = (PassListInfo) iterator.next();
            if (info.isBlobUpdatable())
                loadFile(idQuery, info);
            if(info.isWarning())
                LogHelper.logWarning(idQuery, "В файле "+info.getFile().getName()+" название не заканчиваетя на номер года");
            System.out.println("Обновлен файл "+info.getFile().getName()+" sitedocfile="+info.getSitedocfile());
        }
    }

}
