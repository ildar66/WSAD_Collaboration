/************************************************************************
 * Licensed Materials - Property of IBM
 * (c) Copyright IBM Corp. 2004.  All rights reserved.
 *
 * US Government Users Restricted Rights - Use, duplication or
 * disclosure restricted by GSA ADP Schedule Contract with IBM
 * Corp.
 *
 * DISCLAIMER OF WARRANTIES.  The following [enclosed] code is
 * sample code created by IBM Corporation.  This sample code is
 * not part of any standard or IBM product and is provided to you
 * solely for the purpose of assisting you in the development of
 * your applications.  The code is provided "AS IS", without
 * warranty of any kind.  IBM shall not be liable for any damages
 * arising out of your use of the sample code, even if they have
 * been advised of the possibility of such damages.
 *************************************************************************/
package com.ibm.odcb.tutorial.webservice;

/**
 * @author fenil
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class QuoteDetail {
	
	private String symbol, date, time;
	private float lastPrice, change, open, high, low, volume;
		
	/**
	 * @return
	 */
	public float getChange() {
		return change;
	}

	/**
	 * @return
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @return
	 */
	public float getHigh() {
		return high;
	}

	/**
	 * @return
	 */
	public float getLastPrice() {
		return lastPrice;
	}

	/**
	 * @return
	 */
	public float getLow() {
		return low;
	}

	/**
	 * @return
	 */
	public float getOpen() {
		return open;
	}

	/**
	 * @return
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @return
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @return
	 */
	public float getVolume() {
		return volume;
	}

	/**
	 * @param f
	 */
	public void setChange(float f) {
		change = f;
	}

	/**
	 * @param string
	 */
	public void setDate(String string) {
		date = string;
	}

	/**
	 * @param f
	 */
	public void setHigh(float f) {
		high = f;
	}

	/**
	 * @param f
	 */
	public void setLastPrice(float f) {
		lastPrice = f;
	}

	/**
	 * @param f
	 */
	public void setLow(float f) {
		low = f;
	}

	/**
	 * @param f
	 */
	public void setOpen(float f) {
		open = f;
	}

	/**
	 * @param string
	 */
	public void setSymbol(String string) {
		symbol = string;
	}

	/**
	 * @param string
	 */
	public void setTime(String string) {
		time = string;
	}

	/**
	 * @param f
	 */
	public void setVolume(float f) {
		volume = f;
	}

}
