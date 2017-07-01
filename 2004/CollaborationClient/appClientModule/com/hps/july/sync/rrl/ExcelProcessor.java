package com.hps.july.sync.rrl;

import com.hps.july.core.CollaborationException;
import com.hps.july.core.DB;
import com.hps.july.core.EmptyCollaboration;
import com.hps.july.core.Query;
import com.hps.july.sync.rrl.model.ExcelRrlPermits;
import com.hps.july.util.Utils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author <a href="mailto: NIzhikov@gmail.com">Izhikov Nikolay</a>
 */
//TODO В документе неправильная структура папок
//TODO добавить библиотеки в класпас
public class ExcelProcessor extends EmptyCollaboration {
    public static final String[] FIELD_NAMES = new String[] {
            "siteNumber",
            "address",
            "vkVkr",
            "equipmentType",
            "count",
            "siteNumberReverse",
            "addressReverse",
            "isWork",
            "number",
            "date",
            "numberCc",
            "dateCc",
            "endDateCc",
            "attachedDocumentCc",
            "numberRes",
            "dateRes",
            "endDateRes",
            "numberTempRes",
            "dateTempRes",
            "dateEndTempRes"
    };

    private Logger log = Logger.getLogger(ExcelProcessor.class);

    private Connection connection;
    private DB nri;
    private File directory;

    public ExcelProcessor(DB nri, String directoryPath) {
        this.nri = nri;
    }

    public void doTask(Query query) throws CollaborationException {
        connection = DB.getConnection(nri);

        this.directory = new File(query.getParameter("EXCEL_DIRECTORY_PATH"));

        deleteAllPreviousData();

        log.debug("ExcelProcessor.doTask");
        File[] regionDirs = directory.listFiles(new FileFilter(){
            public boolean accept(File file) {
                return file.isDirectory();
            }
        });

        for (int i = 0; i < regionDirs.length; i++) {
            File regionDir = regionDirs[i];
            log.debug("regionDir = " + regionDir);

            File[] files = regionDir.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.endsWith("xls");
                }
            });

            try {
                for (int j = 0; j < files.length; j++) {
                    File file = files[j];
                    processFile(file);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Utils.closeConnection(connection);
    }

    private void deleteAllPreviousData() {
        PreparedStatement delete = null;
        try {
            delete = connection.prepareStatement("DELETE FROM rrl_permits");
            delete.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            Utils.closeStatement(delete);
        }
    }

    void processFile(File file) throws IOException {
        log.debug("============================================================================");
        log.info("Обрабатываем файл " + file);

        InputStream inputStream = new FileInputStream(file);
        POIFSFileSystem fs = new POIFSFileSystem(inputStream);
        HSSFWorkbook workbook = new HSSFWorkbook(fs);

        List excelRrlPermits = getExcelRrlPermitsList(workbook, file);
        log.info("В файле найдено " + excelRrlPermits.size() + " записей.");
        for (int i = 0; i < excelRrlPermits.size(); i++) {
            ExcelRrlPermits excelRrlPermit = (ExcelRrlPermits) excelRrlPermits.get(i);
            try {
                processExcelRllPermit(excelRrlPermit, file.getName());
            } catch (SQLException e) {
                log.error("Error When processing rrlPermit", e);
            }
        }
    }

    private void processExcelRllPermit(ExcelRrlPermits excelRrlPermit, String name) throws SQLException {
        log.debug("excelRrlPermit = " + excelRrlPermit);

        PreparedStatement insert = connection.prepareStatement("INSERT INTO rrl_permits(sitenumber, address, vkvkr, equipmenttype, count, sitenumberreverse, addressreverse, iswork, number, date, numbercc, datecc, enddatecc, attacheddocumentcc, numberres, dateres, enddateres, numbertempres, datetempres, dateendtempres, filename, id) " +
                                                               "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");


        insert.setString(1, excelRrlPermit.getSiteNumber());
        insert.setString(2, excelRrlPermit.getAddress());
        insert.setString(3, excelRrlPermit.getVkVkr());
        insert.setString(4, excelRrlPermit.getEquipmentType());
        Utils.setInt(insert, 5, excelRrlPermit.getCount());
        Utils.setInt(insert, 6, excelRrlPermit.getSiteNumberReverse());
        insert.setString(7, excelRrlPermit.getAddressReverse());
        insert.setString(8, excelRrlPermit.getIsWork());
        insert.setString(9, excelRrlPermit.getNumber());
        Utils.setDate(insert, 10, excelRrlPermit.getDate());
        insert.setString(11, excelRrlPermit.getNumberCc());
        Utils.setDate(insert, 12, excelRrlPermit.getDateCc());
        Utils.setDate(insert, 13, excelRrlPermit.getEndDateCc());
        insert.setString(14, excelRrlPermit.getAttachedDocumentCc());
        insert.setString(15, excelRrlPermit.getNumberRes());
        Utils.setDate(insert, 16, excelRrlPermit.getDateRes());
        Utils.setDate(insert, 17, excelRrlPermit.getEndDateRes());
        insert.setString(18, excelRrlPermit.getNumberTempRes());
        Utils.setDate(insert, 19, excelRrlPermit.getDateTempRes());
        insert.setString(20, excelRrlPermit.getDateEndTempRes());
        insert.setString(21, name);

        Integer id = Utils.getId("tables.rrl_permits", connection);
        insert.setInt(22, id.intValue());

        insert.execute();
    }

    private List getExcelRrlPermitsList(HSSFWorkbook workbook, File file) {
        HSSFSheet transport = workbook.getSheet("Транспорт");
        if (transport != null) {
            return processSheet(transport, workbook);
        } else {
            HSSFSheet transportVK = workbook.getSheet("Транспорт ВК");
            HSSFSheet transportVKR = workbook.getSheet("Транспорт ВКР");

            if (transportVK != null && transportVKR != null) {
                List result = processSheet(transportVK, workbook);
                result.addAll(processSheet(transportVKR, workbook));

                return result;
            } else {
                log.error("Ошибка при обработке " + file.getName() + " файла, не найдены необходимые листы");
                return Collections.EMPTY_LIST;
            }

        }
    }

    private List processSheet(HSSFSheet sheet, HSSFWorkbook workbook) {
        Iterator iRow = sheet.rowIterator();
        skipFirst2Row(iRow);
        int rowCount = 0;

        List result = new ArrayList();
        while (iRow.hasNext()) {
            rowCount++;
            HSSFRow row = (HSSFRow) iRow.next();
            ExcelRrlPermits excelRrlPermits = new ExcelRrlPermits();

            short i = 0;
            try {
                i = 0;
                for (i=0; i<FIELD_NAMES.length; i++) {
                    HSSFCell cell = row.getCell(i);

                    String cellValue = getCellValue(cell, workbook, sheet, row);
                    if (cellValue != null) {
                        BeanUtils.setProperty(excelRrlPermits, FIELD_NAMES[i], cellValue);
                    }
                }

                if (!excelRrlPermits.isEmpty()) {
                    result.add(excelRrlPermits);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                if(e.getCause() instanceof RuntimeException) {
                    log.error("Ошибка при обработке " + (rowCount+2) + " строки, в " + i + "-ой ячейке. Сообщение об ошибке - " + e.getCause().getMessage());
                } else {
                    throw new RuntimeException(e);
                }
            } catch(Exception e) {
                log.error("Ошибка при обработке " + (rowCount+2) + " строки, в " + i + "-ой ячейке. Сообщение об ошибке - " + e.getMessage());
            }
        }

        return result;
    }

    /**
     * @param cell
     * @param workbook
     * @param sheet
     * @param row
     * @return string representation of cell, or <code>null</code> if <code>cell == null</code> or empty
     */
    private String getCellValue(HSSFCell cell, HSSFWorkbook workbook, HSSFSheet sheet, HSSFRow row) {
        if (cell == null) {
            return null;
        }

        switch(cell.getCellType()) {
            case HSSFCell.CELL_TYPE_BOOLEAN:
                return Boolean.valueOf(cell.getBooleanCellValue()).toString();
            case HSSFCell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    return Utils.date2String(cell.getDateCellValue());
                }

                Double result = new Double(cell.getNumericCellValue());

                if(result.toString().endsWith(".0")) {
                    return new Long(result.longValue()).toString();
                } else {
                    return result.toString();
                }

            case HSSFCell.CELL_TYPE_STRING:
                return cell.getRichStringCellValue().getString();
            case HSSFCell.CELL_TYPE_BLANK:
                return null;
            case HSSFCell.CELL_TYPE_FORMULA:
                return getFormulaValue(sheet, workbook, cell, row);
            case HSSFCell.CELL_TYPE_ERROR:
                //TODO stop processing row
                return "ERROR";
            default:
                throw new RuntimeException("wrong cell type");
        }
    }

    private String getFormulaValue(HSSFSheet sheet, HSSFWorkbook workbook, HSSFCell cell, HSSFRow row) {
        HSSFFormulaEvaluator evaluator = new HSSFFormulaEvaluator(sheet, workbook);
        evaluator.setCurrentRow(row);

        HSSFFormulaEvaluator.CellValue cellValue = evaluator.evaluate(cell);
        switch(cellValue.getCellType()) {
            case HSSFCell.CELL_TYPE_BOOLEAN:
                return Boolean.valueOf(cellValue.getBooleanValue()).toString();
            case HSSFCell.CELL_TYPE_NUMERIC:
                return new Double(cellValue.getNumberValue()).toString();
            case HSSFCell.CELL_TYPE_STRING:
                return cellValue.getRichTextStringValue().getString();
            case HSSFCell.CELL_TYPE_ERROR:
                //TODO stop processing row
                return "ERROR";
            case HSSFCell.CELL_TYPE_BLANK:
                return "";
            case HSSFCell.CELL_TYPE_FORMULA:
                throw new RuntimeException("wrong cell type");
            default:
                throw new RuntimeException("wrong cell type");
        }
    }

    private void skipFirst2Row(Iterator iRow) {
        if (!iRow.hasNext()) {
            //TODO make write exception class
            throw new RuntimeException("can't skip first row");
        }
        iRow.next();
        if (!iRow.hasNext()) {
            throw new RuntimeException("can't skip second row");
        }
        iRow.next();
    }
}
