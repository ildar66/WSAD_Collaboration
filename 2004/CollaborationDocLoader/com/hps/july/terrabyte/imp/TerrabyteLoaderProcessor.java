package com.hps.july.terrabyte.imp;

import com.hps.beeline.LoaderException;
import com.hps.july.terrabyte.imp.command.*;
import com.hps.july.terrabyte.imp.processor.*;
import com.hps.framework.exception.BaseException;

import java.util.HashMap;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 23.06.2005
 * Time: 14:10:05
 */
public class TerrabyteLoaderProcessor {

    private static HashMap processors;
    static {
        processors = new HashMap();
        processors.put("com.hps.july.terrabyte.imp.command.status.RRLRequestAndPermitCommand", "com.hps.july.terrabyte.imp.processor.status.RRLRequestAndPermitProcessor");
        processors.put("com.hps.july.terrabyte.imp.command.status.RequestAndPermitCommand", "com.hps.july.terrabyte.imp.processor.status.RequestAndPermitProcessor");
        processors.put("com.hps.july.terrabyte.imp.command.LoadPositionGRPImageCommand", "com.hps.july.terrabyte.imp.processor.ImageLoaderProcessor");
        processors.put("com.hps.july.terrabyte.imp.command.AnsimovCommand", "com.hps.july.terrabyte.imp.processor.AnsimovImageLoaderProcessor");
        processors.put("com.hps.july.terrabyte.imp.command.DrawingCommand", "com.hps.july.terrabyte.imp.processor.DrawingLoaderProcessor");
        processors.put("com.hps.july.terrabyte.imp.command.DrawingPPCCommand", "com.hps.july.terrabyte.imp.processor.DrawingPPCLoaderProcessor");
        processors.put("com.hps.july.terrabyte.imp.command.InitialDataCommand", "com.hps.july.terrabyte.imp.processor.InitialDataLoaderProcessor");
        processors.put("com.hps.july.terrabyte.imp.command.SanPassportCommand", "com.hps.july.terrabyte.imp.processor.SanPassportLoaderProcessor");
        processors.put("com.hps.july.terrabyte.imp.command.MapWaysLoadCommand", "com.hps.july.terrabyte.imp.processor.MapWaysLoaderProcessor");
        processors.put("com.hps.july.terrabyte.imp.command.BSProjectCommand", "com.hps.july.terrabyte.imp.processor.BSProjectFilesLoaderProcessor");
        processors.put("com.hps.july.terrabyte.imp.command.ArendaLeaseCommand", "com.hps.july.terrabyte.imp.processor.ArendaLeaseFilesLoaderProcessor");
        processors.put("com.hps.july.terrabyte.imp.command.SLDCommand", "com.hps.july.terrabyte.imp.processor.CreateSecondLevelDirectoryProcessor");
        processors.put("com.hps.july.terrabyte.imp.command.terrabyte.TerrabyteTransferCommand", "com.hps.july.terrabyte.imp.processor.terrabyte.TerrabyteTransferProcessor");
        processors.put("com.hps.july.terrabyte.imp.command.terrabyte.TerrabyteRenameCommand", "com.hps.july.terrabyte.imp.processor.terrabyte.TerrabyteRenameProcessor");
        processors.put("com.hps.july.terrabyte.imp.command.ListBsForSORMCommand", "com.hps.july.terrabyte.imp.processor.ListBsForSORMProcessor");
        processors.put("com.hps.july.terrabyte.imp.command.ComProjectCommand", "com.hps.july.terrabyte.imp.processor.ComProjectFilesLoaderProcessor");
        processors.put("com.hps.july.terrabyte.imp.command.arenda.OfficeMemoHeadersCommand", "com.hps.july.terrabyte.imp.processor.arenda.OfficeMemoHeadersFilesLoaderProcessor");
        processors.put("com.hps.july.terrabyte.imp.command.arenda.LeaseMutualActsCommand", "com.hps.july.terrabyte.imp.processor.arenda.LeaseMutualActsFilesLoaderProcessor");
        processors.put("com.hps.july.terrabyte.imp.command.arenda.CompletWorkActsCommand", "com.hps.july.terrabyte.imp.processor.arenda.CompletWorkActsFilesLoaderProcessor");
        processors.put("com.hps.july.terrabyte.imp.command.arenda.CheckLeaseProlongateCommand", "com.hps.july.terrabyte.imp.processor.arenda.CheckLeaseProlongateProcessor");
    }

    private TerrabyteLoaderProcessor() {
    }

    /**
     * Вызов процессора
     * @param command -  исполняемая команда
     * @throws com.hps.beeline.LoaderException
     */
    public static void executeLoaderCommand(Command command, String hostName, Integer port) throws LoaderException, BaseException {
        Processor processor = null;
        try {
            String clazzName = (String)processors.get(command.getClass().getName());
            Class processorClazz = Class.forName(clazzName);
            Object processorObj = null;
            try {
                System.out.println("host ["+hostName+"] "  + port);
                Constructor constructor = null;
                if(hostName != null) {
                    constructor = processorClazz.getConstructor(new Class [] {String.class, Integer.class});
                    processorObj = constructor.newInstance(new Object [] {hostName, port});
                } else {
                    constructor  = processorClazz.getConstructor(new Class [] {Connection.class});
                    processorObj = constructor.newInstance(new Object [] {command.getConnection()});
                }
            } catch(Exception e) {
                processorObj = processorClazz.newInstance();
            }
            Method executeMethod = processorClazz.getMethod("execute", new Class [] {Command.class});
            executeMethod.invoke(processorObj, new Object [] {command});
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new LoaderException("Cannot find processor, cause: " + e.toString());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new LoaderException("Cannot find methods in processor, cause: " + e.toString());
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new LoaderException("Cannot get instance of processor, cause: " + e.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new LoaderException("Permission denied, cause: " + e.toString());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new LoaderException("error while invocation processor, cause: " + e.toString());
        }

    }
}
