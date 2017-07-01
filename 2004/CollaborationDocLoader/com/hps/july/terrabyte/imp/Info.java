/*
 *  $Id: Info.java,v 1.2 2007/06/15 17:12:46 nizhikov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком    
 */
package com.hps.july.terrabyte.imp;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.2 $ $Date: 2007/06/15 17:12:46 $
 */
public class Info {
	public static final String INFO = "I";
	public static final String WARN = "W";
	public static final String ERROR = "E";

	private String info;
	private String comment;

	public Info(String info, String comment) {
		this.info = info;
		this.comment = comment;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
