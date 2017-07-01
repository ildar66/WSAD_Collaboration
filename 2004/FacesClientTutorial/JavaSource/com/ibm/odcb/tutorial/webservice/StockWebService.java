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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.StringTokenizer;

/**
 * @author fenil
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class StockWebService {

	private String BASE_URL =
		"http://finance.yahoo.com/d/quotes.csv?f=sl1d1t1c1ohgv&e=.csv&s=";

	public QuoteDetail getQuote(String symbol) throws Exception {
		URL url = new URL(BASE_URL + symbol);

		// get the quote as a comma separated value string, as in the following example:
		// "IBM",80.85,"11/6/2002","2:20pm",-0.68,80.80,81.500,80.10,5697700

		InputStream is = url.openStream();
		Reader reader = new InputStreamReader(is);
		BufferedReader bf = new BufferedReader(reader);
		String line = bf.readLine();
		StringTokenizer st = new StringTokenizer(line, ",\"");
		String outSymbol = st.nextToken();
		if (!symbol.equalsIgnoreCase(outSymbol)) {
			throw new Exception("Wrong symbol received: " + outSymbol);
		}
		QuoteDetail quote = new QuoteDetail();
		quote.setSymbol(outSymbol);
		quote.setLastPrice(Float.parseFloat(st.nextToken()));
		quote.setDate(st.nextToken());
		quote.setTime(st.nextToken());
		quote.setChange(Float.parseFloat(st.nextToken()));
		quote.setOpen(Float.parseFloat(st.nextToken()));
		quote.setHigh(Float.parseFloat(st.nextToken()));
		quote.setLow(Float.parseFloat(st.nextToken()));
		quote.setVolume(Float.parseFloat(st.nextToken()));
		/*StreamTokenizer st = new StreamTokenizer(reader);
		
		// get the symbol string token, e.g. IBM
		st.nextToken();
		String outSymbol = st.sval;
		if (!symbol.equalsIgnoreCase(outSymbol)) {
			throw new Exception("Wrong symbol received: " + outSymbol);
		}
		
		QuoteDetail quote = new QuoteDetail();
		quote.setSymbol(outSymbol);
		
		// skip the comma token
		st.nextToken();
		
		// get the price number token, e.g. 80.85
		st.nextToken();
		quote.setLastPrice((float) st.nval);
		
		// skip the comma token
		st.nextToken();
		
		//get date token, e.g. 2/5/2004
		st.nextToken();
		quote.setDate(st.sval);
		
		//skip the comma token
		st.nextToken();
		
		//get the time token e.g. 2:23 p.m.
		st.nextToken();
		quote.setTime(st.sval);
		
		//skip the comma token
		st.nextToken();
		
		//get the change token e.g. -1.17
		st.nextToken();
		quote.setChange((float)st.nval);
		
		//		skip the comma token
		st.nextToken();
		
		//get the open price token e.g. 100.00
		st.nextToken();
		quote.setOpen((float)st.nval);
		
		//		skip the comma token
		st.nextToken();
		
		//get the high price token e.g. 100.00
		st.nextToken();
		quote.setHigh((float)st.nval);
		
		//		skip the comma token
		st.nextToken();
		
		//get the low price token e.g. 100.00
		st.nextToken();
		quote.setLow((float)st.nval);
		
		//		skip the comma token
		st.nextToken();
		
		//get the volume token e.g. 1000000
		st.nextToken();
		quote.setVolume((float)st.nval);
		
		reader.close();*/
		bf.close();
		return quote;
	}

}
