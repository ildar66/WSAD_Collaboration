package com.hps.july.mail.text;

import java.lang.reflect.Method;
import java.util.Vector;
import java.util.StringTokenizer;
import java.util.Map;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 23.05.2006
 * Time: 13:04:54
 */
public class ObjectUtil {

    public static Object obtainObject(String name, Map params) {
        Object result = null;
        Object source = null;
        StringTokenizer tokens = new StringTokenizer(name, ".");
        boolean isFirst = true;
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            if(isFirst) {
                isFirst = false;
                source = params.get(token);
                if(source == null) return null;
                if(!tokens.hasMoreTokens()) return source;
                continue;
            }
            result = getObject(source, token);
            if (result != null) {
                System.out.println("result [" + result.getClass() + "] key [" + token + "]");
                // transform Object[] to Collection interface
                if (result instanceof Object[]) {
                    Object[] stringArray = (Object[]) result;
                    if (stringArray.length == 1) result = stringArray[0];
                    else {
                        Vector items = new Vector();
                        for (int i = 0; i < stringArray.length; i ++)
                            items.addElement(stringArray[i]);
                        result = items;
                    }
                }
                if (tokens.hasMoreTokens()) {
                    // need continue
                    source = result;
                    result = null;
                } else return result;
            }
        }

        return null;
    }

    /**
     * Возвращает результат выполения метода переданного объекта. Имя метода определяется на основании переданного имени. <br>
     * порядок перебора следующий : <br>
     * get(&lt;name&gt;); <br>
     * get&lt;name&gt;(); <br>
     * is&lt;name&gt;(); <br>
     * &lt;name&gt;(); <br>
     * getValue(&lt;name&gt;); <br>
     * getAttribute(&lt;name&gt;); <br>
     * getParameterValues(&lt;name&gt;); <br>
     * getParameter(&lt;name&gt;); <br>
     *
     * @param source объект, в котором осуществляется поиск
     * @param name имя, по которому осуществляется поиск
     * @return Object - резльтат выполения метода переданного объекта. Если метод не найден возвращается null.
     */
    public static Object getObject(Object source, String name) {

        Object result = null;
        try {
            Method method = source.getClass().getMethod("get", new Class[]{Object.class});
            method.setAccessible(true);
            result = method.invoke(source, new Object[]{ name });
        } catch (Exception e) {
        }
        if (result != null) return result;
        try {
            StringBuffer methodName = new StringBuffer("get");
            methodName.append(name.substring(0, 1).toUpperCase());
            methodName.append(name.substring(1));
            Method method = source.getClass().getMethod(methodName.toString(), null);
            method.setAccessible(true);
            result = method.invoke(source, null);
        } catch (Exception e) {
        }
        if (result != null) return result;
        try {
            StringBuffer methodName = new StringBuffer("is");
            methodName.append(name.substring(0, 1).toUpperCase());
            methodName.append(name.substring(1));
            Method method = source.getClass().getMethod(methodName.toString(), null);
            method.setAccessible(true);
            result = method.invoke(source, null);
        } catch (Exception e) {
        }
        if (result != null) return result;
        try {
            Method method = source.getClass().getMethod(name, null);
            method.setAccessible(true);
            result = method.invoke(source, null);
        } catch (Exception e) {
        }
        if (result != null) return result;
        try {
            Method method = source.getClass().getMethod("getValue", new Class[]{String.class});
            method.setAccessible(true);
            result = method.invoke(source, new Object[]{ name });
        } catch (Exception e) {
        }
        if (result != null) return result;
        try {
            Method method = source.getClass().getMethod("getAttribute", new Class[]{String.class});
            method.setAccessible(true);
            result = method.invoke(source, new Object[]{ name });
        } catch (Exception e) {
        }
        if (result != null) return result;
        try {
            Method method = source.getClass().getMethod("getParameterValues", new Class[]{String.class});
            method.setAccessible(true);
            result = method.invoke(source, new Object[]{ name });
        } catch (Exception e) {
        }
        if (result != null) return result;
        try {
            Method method = source.getClass().getMethod("getParameter", new Class[]{String.class});
            method.setAccessible(true);
            result = method.invoke(source, new Object[]{ name });
        } catch (Exception e) {
        }

        if (result != null) return result;

        return null;
    }

}
