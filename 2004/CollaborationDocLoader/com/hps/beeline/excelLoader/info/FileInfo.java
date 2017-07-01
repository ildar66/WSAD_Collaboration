package com.hps.beeline.excelLoader.info;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 18.11.2004
 * Time: 14:15:02
 * To change this template use File | Settings | File Templates.
 */
public class FileInfo {
    private Integer id_file;
    private String file_name;
    private String sheet_name;

    public FileInfo() {
    }


    public Integer getId_file() {
        return id_file;
    }

    public void setId_file(Integer id_file) {
        this.id_file = id_file;
    }


    public String getFile_name() {
        return file_name;
    }

    public String getSheet_name() {
        return sheet_name;
    }


    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public void setSheet_name(String sheet_name) {
        this.sheet_name = sheet_name;
    }

}
