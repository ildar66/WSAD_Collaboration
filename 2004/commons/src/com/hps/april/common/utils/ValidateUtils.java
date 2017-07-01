package com.hps.april.common.utils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.regexp.RE;

public class ValidateUtils {

	public static boolean isEmpty(Integer value){
		return value == null || value.intValue() == 0;
	}
	
	public static boolean isEmpty(Long value){
		return value == null || value.longValue() == 0;
	}

	public static boolean isEmpty(String value) {
		return value == null || value.trim().equals("");
	}

	public static boolean isEmpty(Class value) {
		return value == null;
	}

	/**
	 * Проверка на сответствие регулярному выражению
	 * @param value
	 * @param pattern
	 * @return
	 */
	public static boolean isNotMatch(String value, String pattern) {
		RE re = new RE(pattern);
		return !re.match(value);
	}

	/**
	 * Проверка объектов на соответствие с учетом нулевых значений
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static boolean isEqual(Object value1, Object value2) {
		if (value1 == value2) return true;
		
		if (value1 == null){
			if (value2 == null) return true;
			return false;
		}
		
		return value1.equals(value2);
	}

	/**
	 * Проверка входит ли объект в заданный массив объектов
	 * @param object
	 * @param objects
	 * @return
	 */
	public static boolean isContains(Object object, Object[] objects) {
		return ArrayUtils.contains(objects, object);
	}

	public static boolean isEqual(Long object, long value) {
		return isEqual(object, new Long(value));
	}

	public static boolean isEqual(Integer object, int value) {
		return isEqual(object, new Integer(value));
	}

	
}
