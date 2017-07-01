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
package com.ibm.odcb.tutorial.businessobjects;

/**
 * @author fenil
 */
public class Stock  implements java.io.Serializable
{

	private String symbol, currentPrice, volume, change;
	private double percentage;

	public Stock() {

	}

	public Stock(String symbol, float currentPrice) {
		setSymbol(symbol);
		setCurrentPrice(Float.toString(currentPrice));
		setVolume(Long.toString(0));
		setChange(Long.toString(0));
	}

	/**
	 * @return
	 */
	public String getChange() {
		return change;
	}

	/**
	 * @return
	 */
	public String getCurrentPrice() {
		return currentPrice;
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
	public String getVolume() {
		return volume;
	}

	/**
	 * @param string
	 */
	public void setChange(String string) {
		change = string;
	}

	/**
	 * @param string
	 */
	public void setCurrentPrice(String string) {
		currentPrice = string;
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
	public void setVolume(String string) {
		volume = string;
	}

	/**
	 * @return
	 */
	public double getPercentage() {
		return percentage;
	}

	/**
	 * @param d
	 */
	public void setPercentage(double d) {
		percentage = d;
	}

}
