package com.hps.july.sync.kladr.model;

/**
 * Date: 05.04.2007
 * Time: 16:58:45
 *
 * @author <a href="mailto: NIzhikov@gmail.com">Izhikov Nikolay</a>
 */
public class Kladr {
    private String code;
    private String name;
    private String socr;


    public String getSocr() {
        return socr;
    }

    public void setSocr(String socr) {
        this.socr = socr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Kladr{" +
                "code='" + code + "'" +
                ", name='" + name + "'" +
                ", socr='" + socr + "'" +
                ", status='" + status + "'" +
                "}";
    }
}
