package com.hps.april.common.utils.jdbc;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.SqlTypeValue;

/**
 * @author dimitry
 * Created on 22.12.2005
 */
public class SqlPropertiesImpl implements SqlProperties {
	private Log logger = LogFactory.getLog(this.getClass());
	
	private String separator = ",";

	private List propertyList = new ArrayList();	
	private List valueList = new ArrayList();
	private List typeList = new ArrayList();

	public SqlPropertiesImpl(){ 
	
	}

	public SqlPropertiesImpl(String separator){ 
		setSeparator(separator);
	}

	public void add(String property){
		propertyList.add(property);		
	}
	
	public void add(String property, Object value) {
		if (value instanceof SqlProperties){
			add(property, (SqlProperties)value);
		}
		
		add(property, value, SqlTypeValue.TYPE_UNKNOWN);
	}

	public void add(String property, SqlProperties properties){
		propertyList.add(property);
		valueList.addAll(properties.getValueList());
		typeList.addAll(properties.getTypeList());
	}
	
	public void add(String property, Object value, int type) {
		add(property, null, value, type);
	}

	public void add(String property, String beanProperty, Object bean){
		add(property, beanProperty, bean, SqlTypeValue.TYPE_UNKNOWN);
	}
	
	public void add(String property, String beanProperty, Object bean, int type){
		try {
			Object value = beanProperty != null ? PropertyUtils.getProperty(bean, beanProperty) : bean;
			
			propertyList.add(property);
			valueList.add(value);
			typeList.add(new Integer(type));
			
		} catch (IllegalAccessException e) {
			logger.error(e,e);
			throw new SqlPropertyException(e);
		} catch (InvocationTargetException e) {
			logger.error(e,e);
			throw new SqlPropertyException(e);
		} catch (NoSuchMethodException e) {
			logger.error(e,e);
			throw new SqlPropertyException(e);
		} 
	}
	

	/* (non-Javadoc)
	 * @see com.hps.april.common.util.jdbc.SqlProperties#add(java.util.List, java.util.List)
	 */
	public void add(List properties, List values) {
		List types = createList(new Integer(SqlTypeValue.TYPE_UNKNOWN), values.size());
		add(properties, values, types);		
	}
	
	protected List createList(Object value, int size){
		List list = new ArrayList();
		for (int i=0; i<size; i++) list.add(value);
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.hps.april.common.util.jdbc.SqlProperties#add(java.util.List, java.util.List, java.util.List)
	 */
	public void add(List properties, List values, List types) {
		propertyList.addAll(properties);
		valueList.addAll(values);
		typeList.addAll(types);
	}

	/* (non-Javadoc)
	 * @see com.hps.april.common.util.jdbc.SqlProperties#add(java.lang.String[], java.lang.Object[])
	 */
	public void add(String[] properties, Object[] values) {
		int[] types = new int[values.length];
		for (int i=0; i<values.length; i++)
			types[i] = SqlTypeValue.TYPE_UNKNOWN;
				
		add(properties, values, types);		
	}

	/* (non-Javadoc)
	 * @see com.hps.april.common.util.jdbc.SqlProperties#add(java.lang.String[], java.lang.Object[], int[])
	 */
	public void add(String[] properties, Object[] values, int[] types) {
		propertyList.addAll(Arrays.asList(properties));
		valueList.addAll(Arrays.asList(values));
		typeList.addAll(Arrays.asList(ArrayUtils.toObject(types)));
	}

	/* (non-Javadoc)
	 * @see com.hps.april.common.util.jdbc.SqlProperties#addMultiple(java.lang.String, java.util.List)
	 */
	public void addMultiple(String property, List multipleValues) {
		addMultiple(property, multipleValues, SqlTypeValue.TYPE_UNKNOWN);
	}

	/* (non-Javadoc)
	 * @see com.hps.april.common.util.jdbc.SqlProperties#addMultiple(java.lang.String, java.util.List, int)
	 */
	public void addMultiple(String property, List multipleValues, int type) {
		addMultiple(property, null, multipleValues, type);
	}

	
	public void addMultiple(String property, String beanProperty, List multipleValues) {
		addMultiple(property, beanProperty, multipleValues, SqlTypeValue.TYPE_UNKNOWN);
	}
	
	/* (non-Javadoc)
	 * @see com.hps.april.common.util.jdbc.SqlProperties#addMultiple(java.lang.String, java.lang.String, java.util.List)
	 */
	public void addMultiple(String property, String beanProperty, List multipleValues, int type) {
		for (int i=0; i<multipleValues.size(); i++)
			add(property, beanProperty, multipleValues.get(i), type);		
	}
	
/*	protected void addMultiple(String property, String beanProperty, List multipleValues, List multipleTypes){
		for (int i=0; i<multipleValues.size(); i++)
			add(property, beanProperty, multipleValues.get(i), ((Integer)multipleTypes.get(i)).intValue());		
	}
*/	

	/* (non-Javadoc)
	 * @see com.hps.april.common.util.jdbc.SqlProperties#setSeparator(java.lang.String)
	 */
	public void setSeparator(String separator) {
		this.separator = separator;		
	}

	public String toPropertyString(){
		String result = "";
		Iterator itr = propertyList.iterator();
		while (itr.hasNext()){
			result += " " + (String)itr.next() + " ";
			if (itr.hasNext()) result += " " + separator + " ";
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hps.april.common.util.jdbc.SqlProperties#toString()
	 */
	public String toString() {
		return toPropertyString();
	}

	public Object[] toValueArray() {
		return valueList.toArray();
	}

	/* (non-Javadoc)
	 * @see com.hps.april.common.util.jdbc.SqlProperties#toTypeArray()
	 */
	public int[] toTypeArray() {
		int[] types = new int[typeList.size()];
		for (int i=0; i<typeList.size(); i++){
			Integer type = (Integer) typeList.get(i);
			types[i] = type.intValue();
		}

		return types;
	}

	/* (non-Javadoc)
	 * @see com.hps.april.common.util.jdbc.SqlProperties#isEmpty()
	 */
	public boolean isEmpty() {
		return propertyList.isEmpty();
	}

	public List getPropertyList() {
		return propertyList;
	}

	public List getTypeList() {
		return typeList;
	}

	public List getValueList() {
		return valueList;
	}

	/* (non-Javadoc)
	 * @see com.hps.april.common.util.jdbc.SqlProperties#size()
	 */
	public int size() {
		return propertyList.size();
	}
	
	





}
