package com.hps.april.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author ABaykov
 * 
 */
public class FormatUtils {

	private static String DEFAULT_DATE_FORMAT = "dd.MM.yyyy";
	private static Log logger = LogFactory.getLog(FormatUtils.class);
	
	public static String toString(Integer value){
		return value != null ? value.toString() : "";
	}
	
	public static String toString(int value){
		return String.valueOf(value);
	}
	
	public static String formatDate(Date date){
		return formatDate(date, DEFAULT_DATE_FORMAT);
	}
	
    public static String formatDate(Date date, String pattern) {
        if (date == null) return null;
        
        if (pattern == null) 
        	throw new IllegalArgumentException("Argument pattern can not be null");
                
    	SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

	public static Integer toInteger(int value) {
		return new Integer(value);
	}

	public static int toInt(String value) {
		Integer integerValue = toInteger(value);
		return toInt(integerValue);
	}
	
	public static int toInt(Long value){
		return value != null ? value.intValue() : 0;
	}
	
	public static Integer toInteger(String value) {
		if (value == null || value.length() == 0) return null;
		try {
			return Integer.valueOf(value);
		} catch( NumberFormatException e ) {
			logger.warn(e);
			return null;
		}
	}
	
	public static Long toLong(String value) {
		if (value == null || value.length() == 0) return null;
		try {
			return Long.valueOf(value);
		} catch( NumberFormatException e ) {
			logger.warn(e);
			return null;
		}
	}

	

	public static Date parseDate(String date){
		return parseDate(date, DEFAULT_DATE_FORMAT);
	}
	
	public static Date parseDate(String value, String format){
		if (value == null) return null;
		if (format == null)
			throw new IllegalArgumentException("Argument format can not be null");
			
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(value, new ParsePosition(0));
	}

	public static java.sql.Date toSqlDate(String value, String format) {
		Date date = parseDate(value, format);
		return date != null ? new java.sql.Date(date.getTime()) : null;
	}
	
	public static java.sql.Date toSqlDate(String value) {
		return toSqlDate(value, DEFAULT_DATE_FORMAT);
	}

	public static int toInt(Integer value) {
		// TODO log message 
		if (value == null) return 0;
		return value.intValue();
	}

	/**
	 * Перевод Integer в Long. Если передано null, возвращается null
	 * @param value
	 * @return Long
	 */
	public static Long toLong(Integer value){
		if (value == null) return null;
		return new Long(value.longValue());
	}
	
	/**
	 * Перевод Long в Integer. Если передано null, возвращается null
	 * @param value
	 * @return Integer
	 */
	public static Integer toInteger(Long value){
		if (value == null) return null;
		return new Integer(value.intValue());
	}
	
	
	
//	public static Date parseDateTime(String strValue, String datePattern) { 
//		Locale locale = Locale.getDefault();
//		return parseDateTime(strValue,datePattern,locale);
//	}
//
//	public static Date parseDateTime(String strValue, String datePattern, Locale locale) { 
//		return parseGregorianDateTime(strValue,datePattern,locale);
//	}
//
//	
//	private static Date parseGregorianDateTime(String strValue, String datePattern, Locale locale)
//	{
//		SimpleDateFormat df = new SimpleDateFormat(datePattern, locale);
//		df.setLenient(false);
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(1955,1,1);
//		df.set2DigitYearStart(calendar.getTime());
//		Date date = null;
//		ParsePosition pp = new ParsePosition(0);
//		date = df.parse(strValue, pp);
//		return date;
//	}
	
	/**
	 * Возвращает строковое представление числа в заданном формате. 
	 * Описание форматов см. в JavaDoc: java.text.DecimalFormat
	 * @author dkrivenko
	 * @param number
	 * @param pattern
	 * @return
	 */
	public static String toString(double number, String pattern){
		DecimalFormat numberFormat = (DecimalFormat) NumberFormat.getInstance();
		numberFormat.applyPattern(pattern);
		return numberFormat.format(number);
	}
	
	public static Number toNumber(String string) throws ParseException {
		DecimalFormat numberFormat = (DecimalFormat) NumberFormat.getInstance();
		return numberFormat.parse(string);
	}

	/**
	 * Можно использовать там где не существенна потеря точности при преобразовании
	 * @param BigDecimal value 
	 * @return Double
	 */
	public static Double toDouble(BigDecimal value) {
		if (value == null) return null;
		return new Double(value.doubleValue());
	}

	
	public static BigDecimal toBigDecimal(Double value) {
		if (value == null) return null;
		return new BigDecimal(value.doubleValue());
	}



    


}

