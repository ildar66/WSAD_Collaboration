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

import java.util.ArrayList;

/**
 * @author ldh
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */

public class User implements java.io.Serializable // that's a test class with explicit getters and setters
{
	//public static final String COPYRIGHT = com.ibm.odcb.utilities.IBMCopyright.SHORT_COPYRIGHT;
	public User(){
		
	}

	public User(int RefNum, String LastName, String Id) {
		_RefNum = RefNum;
		_LastName = LastName;
		_Id = Id;
	}
	protected int _RefNum;
	protected String _LastName;
	protected String _Id;

	protected ArrayList _Portfolios = new ArrayList(5);

	public int getRefNum() {
		return _RefNum;
	}
	public String getLastName() {
		return _LastName;
	}
	public String getId() {
		return _Id;
	}
	public Portfolio[] getPortfolios() {
		return (Portfolio[]) _Portfolios.toArray(new Portfolio[_Portfolios.size()]);
	}
	
	//public List getPortfolios() { return _Portfolios; }
	
	public void setRefNum(int v) {
		_RefNum = v;
	}
	public void setLastName(String v) {
		_LastName = v;
	}
	public void setId(String v) {
		_Id = v;
	}
	public void addPortfolio(Portfolio P) {
		_Portfolios.add(P);
	}
}
