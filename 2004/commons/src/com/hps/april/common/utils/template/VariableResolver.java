package com.hps.april.common.utils.template;

import java.util.Map;

/**
 * ����������� � ������ var �������� �� Map variables
 * ���������� �� OSCore 
 * @author dimitry
 * 17.12.2006
 */
public interface VariableResolver {

	Object resolve(String var, Map variables);
	
}
