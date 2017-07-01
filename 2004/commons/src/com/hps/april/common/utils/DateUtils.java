package com.hps.april.common.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * –абота с датой
 * @author dkrivenko
 */
public class DateUtils {

	public static Integer getMonth(Date date){
		if (date == null) return null;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return FormatUtils.toInteger(calendar.get(Calendar.MONTH) + 1);
	}
	
	public static Integer getDay(Date date){
		if (date == null) return null;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return FormatUtils.toInteger(calendar.get(Calendar.DAY_OF_MONTH));
	}

	public static Integer getYear(Date date){
		if (date == null) return null;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return FormatUtils.toInteger(calendar.get(Calendar.YEAR));
	}

	/**
	 * ¬ывод даты по набору параметров. ≈сли не задано какой-либо параметр, беретс€ из текущего времени
	 */
	public static Date getDate(Integer day, Integer month, Integer year){
		if (day == null || month == null || year == null) return null;
		return getDate(day != null ? day.intValue() : getDay(new Date()).intValue(),
				month != null ? month.intValue() : getMonth(new Date()).intValue(),
				year != null ? year.intValue() : getYear(new Date()).intValue());
	}

	
	public static Date getDate(int day, int month, int year){
		Calendar calendar = Calendar.getInstance();
		calendar.set( Calendar.MONTH, month - 1 );
	    calendar.set( Calendar.DAY_OF_MONTH, day );
	    calendar.set( Calendar.YEAR, year );
	    //calendar.set( Calendar.HOUR_OF_DAY, 0 );
	    //calendar.set( Calendar.MINUTE, 0 );
	    //calendar.set( Calendar.SECOND, 0 );
	    //calendar.set( Calendar.MILLISECOND, 0 );
	   
	    return calendar.getTime();
	}
	
}
