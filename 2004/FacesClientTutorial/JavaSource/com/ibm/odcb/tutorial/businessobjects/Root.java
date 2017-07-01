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
import java.util.Arrays;
import java.util.List;

import com.ibm.odcb.jrender.diff.DiffTraverser;
import com.ibm.odcb.jrender.diff.BaseHandler;
import com.ibm.odcb.tutorial.WDO4JSEmitterTest;
import com.ibm.odcb.tutorial.diffHandlers.PositionHandler;

/**
 * @author fenil
 */
public class Root implements java.io.Serializable {

	private String name;
	private List users, stocks;
	private Stock placeHolderStock;

	public Root() {
		Root R = WDO4JSEmitterTest.getSampleDataset();
		if (R != null) {
			this.name = R.name;
			this.users = R.users;
			this.stocks = R.stocks;
			this.placeHolderStock = R.placeHolderStock;
		} else {
			users = new ArrayList(7);
			stocks = new ArrayList(7);
			placeHolderStock = new Stock("", 0);
			name = "All Users";

			// Creating 5 stocks      
			Stock IBM = new Stock("IBM", (float) 123.83);
			stocks.add(IBM);
			Stock SUNW = new Stock("SUNW", (float) 5.61);
			stocks.add(SUNW);
			Stock MSFT = new Stock("MSFT", (float) 34.55);
			stocks.add(MSFT);
			Stock AMZN = new Stock("AMZN", (float) 43.35);
			stocks.add(AMZN);
			Stock YHOO = new Stock("YHOO", (float) 49.91);
			stocks.add(YHOO);

			// Creating User PJ
			User U = new User(1, "Pierre Jackson", "PJ");
			users.add(U);
			// Creating one portfolio
			Portfolio Port = new Portfolio(U, "PJ portfolio 1");
			U.addPortfolio(Port);
			// Creating the positions    
			Position Pos = new Position(2, U, Port, IBM, (float) 61.85, 200);
			Port.addPosition(Pos);
			Pos = new Position(3, U, Port, SUNW, (float) 12.65, 133);
			Port.addPosition(Pos);
			Pos = new Position(4, U, Port, SUNW, (float) 1.95, 400);
			Port.addPosition(Pos);
			Pos = new Position(5, U, Port, YHOO, (float) 12.41, 175);
			Port.addPosition(Pos);
			Pos = new Position(6, U, Port, SUNW, (float) 2.55, 100);
			Port.addPosition(Pos);
			Pos = new Position(7, U, Port, MSFT, (float) 48.12, 250);
			Port.addPosition(Pos);
			Pos = new Position(8, U, Port, YHOO, (float) 10.21, 300);
			Port.addPosition(Pos);
			Pos = new Position(9, U, Port, IBM, (float) 54.75, 500);
			Port.addPosition(Pos);
			Pos = new Position(10, U, Port, AMZN, (float) 9.85, 500);
			Port.addPosition(Pos);

			// Creating User William Wenders
			U = new User(5, "William Wenders", "WW");
			users.add(U);
			// Creating first portfolio
			Port = new Portfolio(U, "WW portfolio 1");
			U.addPortfolio(Port);
			// Creating Positions
			Pos = new Position(11, U, Port, IBM, (float) 120.85, 100);
			Port.addPosition(Pos);
			Pos = new Position(12, U, Port, MSFT, (float) 40.32, 250);
			Port.addPosition(Pos);
			Pos = new Position(13, U, Port, YHOO, (float) 12.41, 300);
			Port.addPosition(Pos);
			// Creating second portfolio
			Port = new Portfolio(U, "WW portfolio 2");
			U.addPortfolio(Port);
			// Creating Positions
			Pos = new Position(21, U, Port, AMZN, (float) 8.15, 250);
			Port.addPosition(Pos);
			WDO4JSEmitterTest.UpdateSampleDataset(this);
		}
	}

	public boolean Synch(String diffStr) {
		try {
			DiffTraverser dt = new DiffTraverser("Root", diffStr);
			dt.addHandler("User", new BaseHandler());
			dt.addHandler("Portfolio", new BaseHandler());
			dt.addHandler("Position", new PositionHandler(this));
			dt.addHandler("Stock", new BaseHandler());
			dt.traverse();
			//System.out.println("Updating the object");
			WDO4JSEmitterTest.UpdateSampleDataset(this);
			return true;
		} catch (Exception e) {
			System.out.println("Exception while doing diff gram traversal for the root object");
			e.printStackTrace();
		}
		return false;
	}


	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public Stock getPlaceHolderStock() {
		return placeHolderStock;
	}

	/**
	 * @return
	 */
	public Stock[] getStocks() {
		return (Stock[]) stocks.toArray(new Stock[stocks.size()]);
	}
	//	public List getStocks() {
	//		return stocks;
	//	}

	/**
	 * @return
	 */
	public User[] getUsers() {
		return (User[]) users.toArray(new User[users.size()]);
	}
	//	public List getUsers() {
	//		return users;
	//	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * @param stock
	 */
	public void setPlaceHolderStock(Stock stock) {
		placeHolderStock = stock;
	}

	/**
	 * @param list
	 */
	public void setStocks(Stock[] stocks) {
		this.stocks = Arrays.asList(stocks);
	}

	//	public void setStocks(List stocks) {
	//		this.stocks = stocks;
	//	}

	/**
	 * @param list
	 */
	public void setUsers(User[] users) {
		this.users = Arrays.asList(users);
	}

	//	public void setUsers(List users) {
	//		this.users = users;
	//	}
}
