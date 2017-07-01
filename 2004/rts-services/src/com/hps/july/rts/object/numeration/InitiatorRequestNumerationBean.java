package com.hps.july.rts.object.numeration;

import com.hps.april.common.utils.FormatUtils;

public class InitiatorRequestNumerationBean extends RequestNumerationBean {
	private static final long serialVersionUID = 1L;

	private long initiatorCode = 0;

	public long getInitiatorCode() {
		return initiatorCode;
	}

	public void setInitiatorCode(long initiatorCode) {
		this.initiatorCode = initiatorCode;
	}
	
	protected String formatInitiatorCode(){
		return FormatUtils.toString(initiatorCode, "00");
	}
	
	public String toString(){
		return formatInitiatorCode() + 
			formatRequestNumber() + "-" + formatRequestDate();
	}
}
