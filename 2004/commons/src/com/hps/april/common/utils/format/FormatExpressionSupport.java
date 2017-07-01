package com.hps.april.common.utils.format;

import org.apache.commons.beanutils.PropertyUtils;

public abstract class FormatExpressionSupport {

	protected Object defineProperty(Object bean, String property){
		if (property == null || property.equals("")) return bean;
		
		try {
			Object objProperty = PropertyUtils.getProperty(bean, property);
			return objProperty;	
		} catch (Exception e) {
			throw new FormatException("���������� ���������� �������� �������: ������� �������� �������� property ("+property+")?", e);
		}
	}

	
	
}
