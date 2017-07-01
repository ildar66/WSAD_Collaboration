package com.hps.beeline.initialDataLoader.data;

import com.hps.beeline.global.data.AbstractDirectoryInfo;
import com.hps.beeline.global.helper.FileHelper;
import com.hps.beeline.global.helper.AbstractStoragePlaceHelper;
import com.hps.beeline.LogHelper;
import com.hps.beeline.initialDataLoader.helper.SiteDocHelper;
import com.hps.framework.exception.BaseException;
import com.hps.framework.log.AppLog;
import com.hps.framework.log.MessageService;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 08.04.2005
 * Time: 17:11:13
 * To change this template use File | Settings | File Templates.
 */
public class PassListDirInfo extends AbstractDirectoryInfo {
    protected PassListInfo data = new PassListInfo();
    private String subDirName;
    private GregorianCalendar calend= new GregorianCalendar();

    public PassListDirInfo(String subDirName) {
        this.subDirName = subDirName;
    }

    public void init(Integer idQuery, Integer wlanId, Integer dumpsId, Integer gsmId, File dir) throws BaseException {
        if(subDirName!=null)
            dir = FileHelper.getSubDir(dir, subDirName);
        super.init(idQuery, wlanId, dumpsId, gsmId, dir);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public void processSubFiles() {
        AppLog.info("StoragePlaceId="+storagePlaceId);
        File[] files = dir.listFiles();
        Date expireDate;
        Arrays.sort(files,new Comparator(){
            public int compare(Object o, Object o1) {
                File a = (File) o;
                File b = (File) o1;
                if(a.lastModified()>b.lastModified())
                    return 1;
                else if(a.lastModified()==b.lastModified())
                    return 0;
                else
                    return -1;
            }
        });

        for(int i=0;i<files.length;i++) {
            File file = files[i];
            if(i<files.length-1)
                expireDate = getExpireDate(file, files[i+1]);
            else
                expireDate = getExpireDate(file, null);

            if(isFileLoadable(file))
                processFile(file, expireDate);
        }
    }

    private void processFile(File file, Date expireDate) {
        AppLog.info("process file="+file.getName());
        try{
            data.init(file, "Автоматическая загрузка списков прохода", storagePlaceId, expireDate);
            data.createOrUpdate();
        }catch(BaseException e) {
            e.printStackTrace();
            LogHelper.logError(idQuery, e.getMessage());
            MessageService.getInstance().addMessage("Во время загрузки начальных данных произошла ошибка, см. лог");
        }
    }

    private boolean isFileLoadable(File file) {
        String name =file.getName().toUpperCase();
        if(name.endsWith(".tmp") || file.isDirectory())
            return false;
        else
            return true;
    }

     private Date getExpireDate(File a, File b) {
        if(b==null) {
            calend.setTime(new Date(a.lastModified()));
            calend.set(GregorianCalendar.DAY_OF_MONTH, 31);
            calend.set(GregorianCalendar.MONTH, 11);
        } else {
            calend.setTime(new Date(b.lastModified()));
            calend.add(Calendar.DATE, -1);
        }
        return new Date(calend.getTime().getTime());
    }

}