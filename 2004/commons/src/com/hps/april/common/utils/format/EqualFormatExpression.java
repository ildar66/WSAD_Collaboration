package com.hps.april.common.utils.format;

import com.hps.april.common.utils.ValidateUtils;


public class EqualFormatExpression extends ComparisonFormatExpressionSupport implements FormatExpression {

	public EqualFormatExpression() {
		super();
	}

	public EqualFormatExpression(String property, Object[] values) {
		super(property, values);
	}

	protected boolean compare(Object beanProperty, Object value) {
		return ValidateUtils.isEqual(beanProperty, value); 
	}


}
