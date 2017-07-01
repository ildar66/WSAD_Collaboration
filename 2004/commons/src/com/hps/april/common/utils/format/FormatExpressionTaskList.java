package com.hps.april.common.utils.format;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;

public class FormatExpressionTaskList implements InitializingBean {

	private List expressionList;

	public void afterPropertiesSet() throws Exception {
		
	}
	
	public List getExpressionList() {
		return expressionList;
	}
	public void setExpressionList(List expressionList) {
		this.expressionList = expressionList;
	}

	public List execute(List target){
		for (int i=0; i<expressionList.size(); i++){
			FormatExpression expression = (FormatExpression) expressionList.get(i);
			target = expression.execute(target);
		}
		
		return target;
	}
	
	
	
}
