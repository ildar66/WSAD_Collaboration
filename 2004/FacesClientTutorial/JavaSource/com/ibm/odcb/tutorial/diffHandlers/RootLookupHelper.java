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

import com.ibm.odcb.tutorial.businessobjects.Portfolio;
import com.ibm.odcb.tutorial.businessobjects.Position;
import com.ibm.odcb.tutorial.businessobjects.Root;
import com.ibm.odcb.tutorial.businessobjects.Stock;
import com.ibm.odcb.tutorial.businessobjects.User;

/**
 * @author ldh
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class RootLookupHelper
 {
   public static User LookupUser(Root R, int RefNum)
    {
      User[] users = R.getUsers();
      for (int i = 0; i < users.length; ++i)
       {
         User U = users[i];
         if (U.getRefNum() == RefNum)
          {
            return U;
          }
       }
      return null;
    }

   public static Portfolio LookupPortfolio(User U, String PortfolioName)
    {
      Portfolio[] portfolios = U.getPortfolios();
      for (int i = 0; i < portfolios.length; ++i)
       {
         Portfolio Port = portfolios[i];
         if (Port.getPortfolioName().equals(PortfolioName) == true)
          {
            return Port;
          }
       }
      return null;
    }

   public static Position LookupPosition(Portfolio Port, int RefNum)
    {
      Position[] positions = Port.getPositions();
      for (int i = 0; i < positions.length; ++i)
       {
         Position Pos = positions[i];
         if (Pos.getRefNum() == RefNum)
          {
            return Pos;
          }
       }
      return null;
    }

   public static Stock LookupStock(Root R, String Symbol)
    {
      Stock[] stocks = R.getStocks();
      for (int i = 0; i < stocks.length; ++i)
       {
         Stock S = stocks[i];
         if (S.getSymbol().equals(Symbol) == true)
          {
            return S;
          }
       }
      return null;
    }   
 }
