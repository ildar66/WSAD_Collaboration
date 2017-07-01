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

package com.ibm.odcb.tutorial.diffHandlers;

import org.eclipse.emf.ecore.EObject;
import com.ibm.odcb.jrender.diff.DiffInfo;
import com.ibm.odcb.jrender.diff.BaseHandler;
import com.ibm.odcb.jrender.misc.EMFHelper;
import com.ibm.odcb.tutorial.businessobjects.Portfolio;
import com.ibm.odcb.tutorial.businessobjects.Position;
import com.ibm.odcb.tutorial.businessobjects.Root;
import com.ibm.odcb.tutorial.businessobjects.User;
import java.util.Date;

/**
 * @author Titania Gupta
 *
 * The DiffHandler that will save any changes to a Position object contained within a Diff.
 */
public class PositionHandler extends BaseHandler {
	public PositionHandler(Root R) {
		super();
		_R = R;
	}
	protected Root _R;

	protected void handleCreate(DiffInfo Diff) throws Exception {

		// No creation at this time

	}

	protected void handleUpdate(DiffInfo Diff) throws Exception {

		Portfolio Port = getPortfolio(_R, Diff);
		Integer PositionRefNum =
			(Integer) EMFHelper.getEFeatureValue(Diff.getOriginal(), "refNum");
		Position Pos =
			RootLookupHelper.LookupPosition(Port, PositionRefNum.intValue());

		Pos.setPrice(
			((Float) EMFHelper.getEFeatureValue(Diff.getCurrent(), "price"))
				.floatValue());
		Pos.setQuantity(
			((Integer) EMFHelper
				.getEFeatureValue(Diff.getCurrent(), "quantity"))
				.intValue());
		Date date =
			(Date) EMFHelper.getEFeatureValue(
				Diff.getCurrent(),
				"purchaseDate");
		Pos.setPurchaseDate(date);

	}

	protected void handleDelete(DiffInfo Diff) throws Exception {

		Portfolio Port = getPortfolio(_R, Diff);
		Integer PositionRefNum =
			(Integer) EMFHelper.getEFeatureValue(Diff.getOriginal(), "refNum");
		Position[] positions = Port.getPositions();
		for (int i = 0; i < positions.length; ++i) {
			Position Pos = positions[i];
			if (Pos.getRefNum() == PositionRefNum.intValue()) {
				Port.removePosition(Pos);
				break;
			}
		}

	}

	protected static Portfolio getPortfolio(Root R, DiffInfo Diff)
		throws Exception {
		// The path to a Position is Root, User, Portfolio, Position. We'll follow that path and get to the right 
		// Position object. The root is not that interesting to us, so we'll start directly with the User (index = 1).
		EObject O = (EObject) Diff.getAncestors().get(1); // The User;
		Integer UserRefNum = (Integer) EMFHelper.getEFeatureValue(O, "refNum");
		User U = RootLookupHelper.LookupUser(R, UserRefNum.intValue());

		O = (EObject) Diff.getAncestors().get(2); // The Portfolio;
		String PortfolioName =
			(String) EMFHelper.getEFeatureValue(O, "portfolioName");
		Portfolio Port = RootLookupHelper.LookupPortfolio(U, PortfolioName);

		return Port;
	}
}
