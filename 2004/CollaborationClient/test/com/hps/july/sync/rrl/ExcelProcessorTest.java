package com.hps.july.sync.rrl;

import com.hps.july.AbstractTest;
import com.hps.july.core.DB;

/**
 * @author <a href="mailto: NIzhikov@gmail.com">Izhikov Nikolay</a>
 */
public class ExcelProcessorTest extends AbstractTest {
    private ExcelProcessor excelProcessor;

    protected void setUp() throws Exception {
        super.setUp();
        excelProcessor = new ExcelProcessor(new DB(properties, "NRI"), properties.getProperty("RRLExcelProcessorDir"));
    }

    public void testDoTask() throws Exception {
        excelProcessor.doTask(getTaskQuery());
    }

    public void testFile() throws Exception {
//        excelProcessor.processFile(new File("/usr/local/src/collaboration/docs/files/Поволжье/Разрешения Астрахань.xls"));
//        excelProcessor.processFile(new File("/usr/local/src/collaboration/docs/files/Москва/Разрешения Москва.xls"));
    }

}
