package com.hps.framework.utils;

import com.hps.framework.exception.BaseException;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class BeanFactory {
    private static BeanFactory ourInstance;

    public static synchronized BeanFactory getInstance() {
        if (ourInstance == null) {
            ourInstance = new BeanFactory();
        }
        return ourInstance;
    }

    private BeanFactory() {
    }

    public Object createBean(String className, Map record) throws BaseException {
        if(className==null)
            return null;
        try{
            Class classImpl = Class.forName(className);
            Object instance = classImpl.newInstance();
            BeanInfo info = Introspector.getBeanInfo(classImpl);

            PropertyDescriptor[] descs = info.getPropertyDescriptors();
            for(int i=0;i<descs.length;i++){
                String propertyName = descs[i].getName();
                Method method = descs[i].getWriteMethod();
//                AppLog.debug("propertyName="+propertyName);
//                if(method!=null)
//	                AppLog.debug("Method name="+method.getName());
//	            else
//	            	AppLog.debug("Method name=null");

                if(!propertyName.equals("class") && method!=null && record.containsKey(propertyName)) {
                    Object value = record.get(propertyName);
                    if(value!=null) {
                        //AppLog.debug("value class="+value.getClass());
                        method = classImpl.getMethod(method.getName(), new Class[]{value.getClass()});
                    }
                    method.invoke(instance, new Object[]{value});
                }
            }
            return instance;
        }catch(Exception err) {
            err.printStackTrace();
            throw new BaseException("Ошибка генерации Bean",err);
        }
    }
}