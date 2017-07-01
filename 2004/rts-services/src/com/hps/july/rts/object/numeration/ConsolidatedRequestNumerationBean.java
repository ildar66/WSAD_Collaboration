package com.hps.july.rts.object.numeration;

public class ConsolidatedRequestNumerationBean extends RequestNumerationBean {
	private static final long serialVersionUID = 1L;

	public String toString() {
		return "K" + formatRequestNumber() + "-" + formatRequestDate();
	}

}
