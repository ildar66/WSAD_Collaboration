package com.hps.july.rts.object.numeration;

import com.hps.april.common.utils.FormatUtils;

public class ElementaryRequestNumerationBean extends ConsolidatedRequestNumerationBean {
	private static final long serialVersionUID = 1L;

	private long elementaryCode = 0;
	
	public long getElementaryCode() {
		return elementaryCode;
	}

	public void setElementaryCode(long elementaryCode) {
		this.elementaryCode = elementaryCode;
	}

	public void setConsolidatedNumetationBean(ConsolidatedRequestNumerationBean bean){
		setRequestCode(bean.getRequestCode());
		setRequestDate(bean.getRequestDate());
	}
	
	public void incrementElementaryCode(){
		this.elementaryCode++;
	}
	
	protected String formatElementaryCode(){
        return FormatUtils.toString(elementaryCode, "00");
	}
	
	public String toString() {
		// Временно есть возможность проставлять номер заявки у КЗ ключевым менеджером
		// временно генерим и у элементарных так же
		String str = getShortNumber(); 
		if(str!=null) 
			return getShortNumber() + "." + formatElementaryCode() + "-" + formatRequestDate();
		else 
			return "K" + formatRequestNumber() + "." + formatElementaryCode() + "-" + formatRequestDate();
	}
}
