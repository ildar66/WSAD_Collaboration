/*
 * Created on 09.06.2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.hps.july.mail.test;

import java.util.ArrayList;

/**
 * @author S
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TestHttpMail {

	public static void main(String[] args) {
		TestJulyMailService test = new TestJulyMailService();
		ArrayList l = new ArrayList();
		l.add("abaykov@beeline.ru");
		test.startTestHttpJulyMailService(l);
	}
}
