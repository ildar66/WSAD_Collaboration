/************************************************************************
 * Licensed Materials - Property of IBM
 * (c) Copyright IBM Corp. 2003.  All rights reserved.
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

package com.ibm.odcb.tutorial;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.eclipse.emf.ecore.EObject;
import java.io.IOException;
import com.ibm.odcb.jrender.diff.DiffHelper;
import com.ibm.odcb.jrender.diff.DiffTraverser;
import com.ibm.odcb.tutorial.businessobjects.Portfolio;
import com.ibm.odcb.tutorial.businessobjects.Root;
import com.ibm.odcb.tutorial.businessobjects.User;
import com.ibm.odcb.jrender.diff.BaseHandler;
import com.ibm.odcb.tutorial.diffHandlers.PositionHandler;
import com.ibm.odcb.tutorial.diffHandlers.RootLookupHelper;

public class PortfolioServlet extends HttpServlet {
	public void doPost(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		performTask(request, response);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		performTask(request, response);
	}

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		try {
			String diffStr = request.getParameter("PortfolioModelDiff");
			Root R = WDO4JSEmitterTest.getSampleDataset();
			if (R == null) {
				R = new Root();
				WDO4JSEmitterTest.UpdateSampleDataset(R);
			}
			if (diffStr != null) {
				EObject eobj =
					DiffHelper.getRootEObjectFromDiffString(
						"Root",
						diffStr);
				DiffHelper.printDiff(eobj);
				DiffTraverser dt = new DiffTraverser(eobj);
				dt.addHandler("User", new BaseHandler());
				dt.addHandler("Portfolio", new BaseHandler());
				dt.addHandler("Position", new PositionHandler(R));
				dt.addHandler("Stock", new BaseHandler());
				dt.traverse();
				WDO4JSEmitterTest.UpdateSampleDataset(R);
			}

			request.setAttribute("PortfolioData", R);

			//set Pierre Jackson's PJ Portfolio 1 portfolio as the Starting Portfolio
			User U = RootLookupHelper.LookupUser(R, 1);
			//Pierre Jackson's user refnum is 1 (hardcode here)
			Portfolio P = RootLookupHelper.LookupPortfolio(U, "PJ portfolio 1");
			//Fenil Change...adding user[0] as a usebean to the request cache as well...needed to dynamically update the heading in the panel 3 based on OnHighlight events in the tree
			request.setAttribute("StartingUser", U);
			//System.out.println("@@@ Setting Starting Portfolio : " + P);
			request.setAttribute("StartingPortfolio", P);

			RequestDispatcher rd =
				this.getServletContext().getRequestDispatcher(
					"emitters/Portfolio.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			response.sendError(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
				"Some error occured. We apologize.");
		}
	}
}