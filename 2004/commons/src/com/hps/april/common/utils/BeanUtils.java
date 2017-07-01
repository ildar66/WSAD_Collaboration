package com.hps.april.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import com.hps.april.common.IllegalUsageException;

public class BeanUtils {

	/**
	 * Возвращает список свойств property для списка объектов objectList 
	 * @param objectList
	 * @param property
	 * @return List of Object
	 */
	public static List getPropertiesFromObjectList(List objectList, String property){
		List result = new ArrayList();
		if (objectList == null) return result;

		for (int i=0; i<objectList.size(); i++){
			Object object = objectList.get(i);
			Object value;
			try {
				value = PropertyUtils.getProperty(object, property);
			} catch (IllegalAccessException e) {
				throw new IllegalUsageException("Невозможно получить доступ к свойству " + property + 
						" класса " + object.getClass().getName() + ": " + e.getMessage(), e);
			} catch (InvocationTargetException e) {
				throw new IllegalUsageException("Ошибка при обращении к свойству " + property + 
						" класса " + object.getClass().getName() + ": " + e.getMessage(), e);
			} catch (NoSuchMethodException e) {
				throw new IllegalUsageException("Не найдено свойство " + property + 
						" класса " + object.getClass().getName() + ": " + e.getMessage(), e);
			}

			result.add(value);
		}

		return result;
	}

	
	
	
}
