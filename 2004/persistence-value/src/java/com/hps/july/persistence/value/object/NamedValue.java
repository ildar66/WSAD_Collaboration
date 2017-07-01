/*
 *  $Id: NamedValue.java,v 1.1 2006/11/16 16:36:11 vglushkov Exp $
 *  Copyright (c) 2003-2006 ОАО Вымпелком
 */
package com.hps.july.persistence.value.object;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.1 $
 * Date: 15.09.2006
 * Time: 16:54:57
 */
public class NamedValue {

    private String id;
    private String stringValue;
    private Integer intValue;
    private BigDecimal decimalValue;
    private Timestamp dateValue;
    private String note;

    public NamedValue() {
        this.id = null;
        this.stringValue = null;
        this.intValue = null;
        this.decimalValue = null;
        this.dateValue = null;
        this.note = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    public BigDecimal getDecimalValue() {
        return decimalValue;
    }

    public void setDecimalValue(BigDecimal decimalValue) {
        this.decimalValue = decimalValue;
    }

    public Timestamp getDateValue() {
        return dateValue;
    }

    public void setDateValue(Timestamp dateValue) {
        this.dateValue = dateValue;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
