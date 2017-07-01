package com.hps.july.rts.object.numeration;

import com.hps.april.common.utils.FormatUtils;

/**
 * Код канала
 * (консолидированный и элементарный канал)
 * Нумерация каналов орагнизована в соответствии с принятой в компании
 * "СИСТЕМОЙ НУМЕРАЦИИ СРЕДСТВ СВЯЗИ
 * ТРАНСПОРТНОЙ СЕТИ БИ НЕТ В РЕГИОНАХ"
 * Created by IntelliJ IDEA.
 * User: ABaykov
 * Date: 20.06.2006
 * Time: 10:41:42
 * To change this template use File | Settings | File Templates.
 */
public class ChannelCodeNumerationBean extends RequestNumerationBean {

    private static final long serialVersionUID = 1L;

    private long initiatorCode = 0;

    public long getInitiatorCode() {
        return initiatorCode;
    }

    public void setInitiatorCode(long initiatorCode) {
        this.initiatorCode = initiatorCode;
    }

    protected String formatInitiatorCode(){
        return FormatUtils.toString(initiatorCode, "00");
    }

    public String toString(){
        return formatInitiatorCode() +
            formatRequestNumber() + "-" + formatRequestDate();
    }

}
