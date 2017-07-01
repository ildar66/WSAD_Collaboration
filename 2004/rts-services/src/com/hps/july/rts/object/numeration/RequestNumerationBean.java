package com.hps.july.rts.object.numeration;

import java.io.Serializable;
import java.util.Date;
import java.util.StringTokenizer;

import org.apache.commons.lang.time.DateFormatUtils;

import com.hps.april.common.utils.FormatUtils;

public abstract class RequestNumerationBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private long requestCode = 0;
    private Date requestDate;
    // Номер заявки
    private String number;
    // Номер конс. заявки без года "-06"
    private String shortNumber;

    public long getRequestCode() {
        return requestCode;
    }
    public void setRequestCode(long requestCode) {
        this.requestCode = requestCode;
    }
    public Date getRequestDate() {
        return requestDate;
    }
    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public void incrementRequestCode() {
        this.requestCode = requestCode + 1;
    }

    protected String formatRequestDate() {
        return DateFormatUtils.format(requestDate, "yy");
    }

    protected String formatRequestNumber() {
        return FormatUtils.toString(requestCode, "0000");
    }

    public abstract String toString();
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String string) {
        number = string;
    }

    public String getShortNumber() {
        if(getNumber()!=null) {
            StringTokenizer st = new StringTokenizer(getNumber(), "-", false);
            int index = 0;
            while(st.hasMoreTokens()) {
                index = getNumber().indexOf(st.nextToken());
                if(index>0) shortNumber = getNumber().substring(0, index-1);
            }
        }
        return shortNumber;
    }

    public void setShortNumber(String string) {
        shortNumber = string;
    }

}
