/*
 *  $Id: Coordinate.java,v 1.2 2006/11/08 11:10:22 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком    
 */
package com.hps.april.common.utils.coord;

import java.math.BigDecimal;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.2 $ $Date: 2006/11/08 11:10:22 $
 */
public class Coordinate {

    private int degree;
    private int minute;
    private int second;
    private BigDecimal decimalValue;

    public Coordinate(int degree, int minute, int second) {
        this.degree = degree;
        if(minute < 0) throw new IllegalArgumentException("Minute must be positive");
        this.minute = minute;
        if(second < 0) throw new IllegalArgumentException("Second must be positive");
        this.second = second;
    }

    public Coordinate(BigDecimal decimalValue) {
        if(decimalValue == null) throw new IllegalArgumentException("Decimal value cannot be null");
        this.decimalValue = decimalValue;
        this.decimalValue.setScale(6, BigDecimal.ROUND_UP);
        parse(decimalValue);
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
        parse(degree, minute, second);
    }

    public void setDegree(String degree) {
        try {
            this.degree = Integer.parseInt(degree);
            parse(this.degree, minute, second);
        } catch(Exception e) {
            throw new IllegalArgumentException("Parameter ["+degree+"] cannot be converted to degree.");
        }
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        if(minute < 0) throw new IllegalArgumentException("Minute must be positive");
        this.minute = minute;
        parse(degree, minute, second);
    }

    public void setMinute(String minute) {
        try {
            this.minute = Integer.parseInt(minute);
            parse(degree, this.minute, second);
        } catch(Exception e) {
            throw new IllegalArgumentException("Parameter ["+minute+"] cannot be converted to minute.");
        }
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        if(second < 0) throw new IllegalArgumentException("Second must be positive");
        this.second = second;
        parse(degree, minute, second);
    }

    public void setSecond(String second) {
        try {
            this.second = Integer.parseInt(second);
            parse(degree, minute, this.second);
        } catch(Exception e) {
            throw new IllegalArgumentException("Parameter ["+second+"] cannot be converted to second.");
        }
    }

    public BigDecimal getDecimalValue() {
        return decimalValue;
    }

    public void setDecimalValue(BigDecimal decimalValue) {
        this.decimalValue = decimalValue;
    }

    public String getGeographicFormat() {
        return degree + "\u00B0" + minute + "\u2019" + second + "\u201D";
    }

    private void parse(BigDecimal decimalValue) {
        boolean isNegative = false;
        if(decimalValue.intValue() < 0) isNegative = true;
        decimalValue = decimalValue.abs();
        int d = decimalValue.intValue();
        BigDecimal a = decimalValue.subtract(new BigDecimal(d)).multiply(new BigDecimal(60d));
        int m = a.intValue();
        BigDecimal b = a.subtract(new BigDecimal(m)).multiply(new BigDecimal(60d));
        int s = b.intValue();
        if(isNegative) d *= -1;
        degree = d;
        minute = m;
        second = s;
    }

    private void parse(int degree, int minute, int second) {
        boolean isNegative = false;
        if(degree < 0) isNegative = true;
        BigDecimal bigD = new BigDecimal(degree);
        bigD = bigD.abs();
        BigDecimal bigM = new BigDecimal(minute);
        bigM = bigM.divide(new BigDecimal(60), 6, BigDecimal.ROUND_UP);
        BigDecimal bigS = new BigDecimal(second);
        bigS = bigS.divide(new BigDecimal(3600), 6, BigDecimal.ROUND_UP);
        bigD = bigD.add(bigM).add(bigS);
        if(isNegative) bigD = bigD.negate();
        decimalValue = bigD;

    }

}
