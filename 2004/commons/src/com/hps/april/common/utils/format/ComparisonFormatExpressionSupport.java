package com.hps.april.common.utils.format;

import java.util.Iterator;
import java.util.List;

public abstract class ComparisonFormatExpressionSupport extends FormatExpressionSupport implements FormatExpression {

	private String beanProperty;
	private Object[] sortValues;
	
	public ComparisonFormatExpressionSupport() {
	}

	public ComparisonFormatExpressionSupport(String property, Object[] values) {
		this.beanProperty = property;
		this.sortValues = values;
	}

	public String getBeanProperty() {
		return beanProperty;
	}
	public void setBeanProperty(String property) {
		this.beanProperty = property;
	}
	public Object[] getSortValues() {
		return sortValues;
	}
	public void setSortValues(Object[] values) {
		this.sortValues = values;
	}

	public final List execute(List target) {
		Iterator items = target.iterator();
		while (items.hasNext()) {
			Object itemProperty = defineProperty(items.next(), beanProperty);
			
			for (int i=0; i<sortValues.length; i++){
				if (!compare(itemProperty, sortValues[i])){
					items.remove();
				} 
			}
		}
		return target;
	}

	protected abstract boolean compare(Object beanProperty, Object value);
}
