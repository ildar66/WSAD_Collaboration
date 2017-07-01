package com.hps.april.common.utils.template;

import java.util.Map;

/**
 * Подстановка в строку var значений из Map variables
 * Скопировал из OSCore 
 * @author dimitry
 * 17.12.2006
 */
public interface VariableResolver {

	Object resolve(String var, Map variables);
	
}
