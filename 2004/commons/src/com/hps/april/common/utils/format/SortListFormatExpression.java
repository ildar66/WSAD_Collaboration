package com.hps.april.common.utils.format;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortListFormatExpression extends FormatExpressionSupport implements FormatExpression {

	private String property;
	private Object[] values;

	public SortListFormatExpression() {
	}

	public SortListFormatExpression(String property, Object[] values) {
		this.property = property;
		this.values = values;
	}

	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public Object[] getValues() {
		return values;
	}
	public void setValues(Object[] values) {
		this.values = values;
	}
	
	public List execute(List target) {
		Comparator comparator = new SortComparator(property, values);
		Collections.sort(target, comparator);
		return target;
	}
	
	
	protected class SortComparator implements Comparator {

		protected List sortValues;
		protected String property;

		public SortComparator(String property, Object[] sortValues) {
			this.property = property;
			this.sortValues = Arrays.asList(sortValues);
		}

		protected int getListPosition(Object object) {
			Object objProperty = defineProperty(object, property);
			return sortValues.lastIndexOf(objProperty); 
		}
		
		// obj1 < obj2 : -1
		// obj1 = obj2 : 0
		// obj1 > obj2 : +1
		public int compare(Object obj1, Object obj2) {
			int pos1 = getListPosition(obj1);
			int pos2 = getListPosition(obj2);
			return pos1 - pos2; 
		}
	}


	
}
