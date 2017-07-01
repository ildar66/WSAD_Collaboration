package com.hps.april.common.utils.template;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DefaultVariableResolver implements VariableResolver {

	protected Log logger = LogFactory.getLog(this.getClass());
	
	/**
	 * Parses a string for instances of "${foo}" and returns a string with all
	 * instances replaced with the string value of the foo object (<b>foo.toString()</b>).
	 * If the string being passed in only refers to a single variable and
	 * contains no other characters (for example: ${foo}), then the actual
	 * object is returned instead of converting it to a string.
	 */
	public Object resolve(String str, Map variables) {
		String temp = str.trim();

		if (temp.startsWith("${") && temp.endsWith("}")
				&& (temp.indexOf('$', 1) == -1)) {
			// the string is just a variable reference, don't convert it to a
			// string
			String var = temp.substring(2, temp.length() - 1);

			return resolveVar(var, variables);
		} else {
			// the string passed in contains multiple variables (or none!) and
			// should be treated as a string
			while (true) {
				int startPos = str.indexOf("${");
				int endPos = str.indexOf("}", startPos);

				if ((startPos != -1) && (endPos != -1)) {
					String var = str.substring(startPos + 2, endPos);
					String tmpStr = null;
					Object obj = resolveVar(var, variables);

					if (obj != null) {
						tmpStr = obj.toString();
					}

					if (tmpStr != null) {
						str = str.substring(0, startPos) + tmpStr + str.substring(endPos + 1);
					} else {
						// the variable doesn't exist, so don't display anything
						str = str.substring(0, startPos) + str.substring(endPos + 1);
					}
				} else {
					break;
				}
			}

			return str;
		}
	}

	protected Object resolveVar(String var, Map variables){
		try {
			return PropertyUtils.getProperty(variables, var);
		} catch (IllegalAccessException e) {
			logger.error(e,e);
		} catch (InvocationTargetException e) {
			logger.error(e,e);
		} catch (NoSuchMethodException e) {
			logger.error(e,e);
		} catch (NestedNullException e){
			logger.error(e,e);
		}
		
		return null;
	}

	// Test
	public static void main(String[] args) {
		VariableResolver variableResolver = new DefaultVariableResolver();
		
		Map map = new HashMap();
		Map map2 = new HashMap();
		map2.put("key2", "test");
		
		map.put("map1", map2);
		
		Object object = variableResolver.resolve("${map1.key2}", map);
		System.out.println(object);
		
	}
	
	
}
