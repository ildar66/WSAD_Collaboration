

////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
//
//     *********************************************************
//     ** THIS CODE IS GENERATED AND **MUST NOT** BE MODIFIED **
//     *********************************************************
//
// Object to JavaScript-WDO4JS Mediator: version 1.
//
//  1   : - Supports references and unicity
//        - Mediators to generic Java classes (through public member variables, explitic and generic gets/sets)
//        - Supports List or Iterator member variables for multiplicity
//        - Supports aliasing
//        - Generate automatic primary keys
//
//
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////


//     *********************************************************
//     ** THIS CODE IS GENERATED AND **MUST NOT** BE MODIFIED **
//     *********************************************************


package com.ibm.odcb.tutorial.wdo4jsmediators.Portfolio;

import java.io.Writer;
import com.ibm.odcb.jrender.mediators.Mediator;
import com.ibm.odcb.jrender.mediators.PageContext;
import com.ibm.odcb.jrender.mediators.ExportException;

//     *********************************************************
//     ** THIS CODE IS GENERATED AND **MUST NOT** BE MODIFIED **
//     *********************************************************

public class Portfolio_User_wdo4js_js extends Mediator
 {
   public Portfolio_User_wdo4js_js() { super("User", ","); }
   public String getSignature(Object O, String modelName, boolean First, int ComplexPropertyCount)
    {
      if (O == null) return "";
      try
       {
         com.ibm.odcb.tutorial.businessobjects.User Obj = (com.ibm.odcb.tutorial.businessobjects.User) O;
         StringBuffer SigStr = new StringBuffer(128);
         SigStr.append("`").append(Obj.getRefNum());

         return First == true ? modelName+"`com.ibm.odcb.tutorial.businessobjects.User"+SigStr.toString() : SigStr.toString();
       }
      catch (Exception e)
       {
         if (e instanceof java.lang.ClassCastException)
          {
            System.out.println("ClassCastException: Expecting type 'com.ibm.odcb.tutorial.businessobjects.User' but found object of type '"+O.getClass().getName()+"'"); 
            System.out.println("Please make sure that the ecore and emap are defined correctly."); 
          }
         e.printStackTrace();
         return null; 
       }
    }

//     *********************************************************
//     ** THIS CODE IS GENERATED AND **MUST NOT** BE MODIFIED **
//     *********************************************************

   public void Export(Writer Out, PageContext Ctx) throws ExportException
     {
      try
      {
       boolean FirstTime = Ctx.isFirstSchemaExport();
       if (FirstTime == true)
        {
          Out.write("<SCRIPT> var A; var XTOTOX1=A; A=new ECreator().AddEAs; var R; var XTOTOX2=R; R=new ECreator().AddERs;");
          Out.write("var WDO4JSModel_"); Out.write(Ctx.getSchemaExportName()); Out.write("=new EClass(\"WDO4JSMR_"); Out.write(Ctx.getLastModelName()); Out.write("\");\n");
        }

       Out.write("var UserClass=new EClass(\"User\",1);"); 
       Out.write("R(WDO4JSModel_"); Out.write(Ctx.getSchemaExportName()); Out.write(",[[\"User\",UserClass,0,-1,0,0]]);\n");

       super.ExportSchema(Out, Ctx, "com.ibm.odcb.tutorial.businessobjects.Portfolio`_wdo4js_js");

       Out.write("A(UserClass,[[\"xmi:id\",\"id\",0],[\"refNum\",\"int\",0,0,1,1],[\"lastName\",\"string\",0,0,1,0],[\"id\",\"string\",0,0,1,0]]);\n");
       Out.write("R(UserClass,[[\"portfolios\",PortfolioClass,0,-1,1,0]]);\n");

       if (FirstTime == true)
        { Out.write("var WDO4JSModelRoot_"); Out.write(Ctx.getLastModelName()); Out.write("=new XMILoader(WDO4JSModel_"); Out.write(Ctx.getSchemaExportName()); Out.write(", 'User', 'com/ibm/odcb/tutorial/wdo4jsmediators/Portfolio/Portfolio_client.ecore');\n");
          Out.write("A=XTOTOX1;R=XTOTOX2;</SCRIPT>\n");
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
     }

//     *********************************************************
//     ** THIS CODE IS GENERATED AND **MUST NOT** BE MODIFIED **
//     *********************************************************

   public void ExportXMILoader(Writer Out, PageContext Ctx) throws ExportException     {
      try
      {
        boolean FirstTime = Ctx.isFirstSchemaExport();
        if (FirstTime == true)
        {
          Out.write("<SCRIPT>");
          Out.write("var WDO4JSModelRoot_"); Out.write(Ctx.getLastModelName()); Out.write("=new XMILoader(WDO4JSModel_"); Out.write(Ctx.getSchemaExportName()); Out.write(", 'User', 'com/ibm/odcb/tutorial/wdo4jsmediators/Portfolio/Portfolio_client.ecore');\n");
          Out.write("</SCRIPT>\n");
         }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
     }

//     *********************************************************
//     ** THIS CODE IS GENERATED AND **MUST NOT** BE MODIFIED **
//     *********************************************************

   public void Export(Writer Out, PageContext Ctx, Object O, String Var, boolean DebugMode) throws ExportException
     {
      try
        {
       com.ibm.odcb.tutorial.businessobjects.User Obj = (com.ibm.odcb.tutorial.businessobjects.User) O;

       boolean FirstTime = Ctx.isFirstDataExport();
       if (FirstTime == true)
        Out.write("<SCRIPT> var C; var XTOTOX1=C; C=new EFactory().create; var I; var XTOTOX2=I; I=new ECreator().Init;\n");

       if (Obj == null)
        {
          Out.write("var "+Var+" = null;");
          return;
        }
       Out.write("var "+Var+" = C(UserClass);");

       Mediator.ExportData(Out, Ctx, Obj.getPortfolios(), "com.ibm.odcb.tutorial.businessobjects.Portfolio`_wdo4js_js", DebugMode);
       String PortfolioVar3= Ctx.PopArrayVar("com.ibm.odcb.tutorial.businessobjects.Portfolio`_wdo4js_js");
       Out.write("I("+Var+",[\""+Var+"\""
                +","+Obj.getRefNum()
                +","+com.ibm.odcb.jrender.misc.StringUtil.QuoteDoubleAndEscape(Obj.getLastName(),false)
                +","+com.ibm.odcb.jrender.misc.StringUtil.QuoteDoubleAndEscape(Obj.getId(),false)
                +"],["
                +" "+(PortfolioVar3==null?"null":"["+PortfolioVar3+"]")+"]);\n");

       if (FirstTime == true)
        { Out.write("WDO4JSModelRoot_"); Out.write(Ctx.getLastModelName()); Out.write(".Root.eAdd('User', "+Var+");"); 
          Out.write("C=XTOTOX1;I=XTOTOX2;</SCRIPT>\n"); }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
     }
 }
