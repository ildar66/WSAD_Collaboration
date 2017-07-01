

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

public class Portfolio_Root_wdo4js_js extends Mediator
 {
   public Portfolio_Root_wdo4js_js() { super("Root", ","); }
   public String getSignature(Object O, String modelName, boolean First, int ComplexPropertyCount)
    {
      if (O == null) return "";
      try
       {
         com.ibm.odcb.tutorial.businessobjects.Root Obj = (com.ibm.odcb.tutorial.businessobjects.Root) O;
         StringBuffer SigStr = new StringBuffer(128);

         return First == true ? modelName+"`com.ibm.odcb.tutorial.businessobjects.Root"+SigStr.toString() : SigStr.toString();
       }
      catch (Exception e)
       {
         if (e instanceof java.lang.ClassCastException)
          {
            System.out.println("ClassCastException: Expecting type 'com.ibm.odcb.tutorial.businessobjects.Root' but found object of type '"+O.getClass().getName()+"'"); 
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

       Out.write("var RootClass=new EClass(\"Root\",1);"); 
       Out.write("R(WDO4JSModel_"); Out.write(Ctx.getSchemaExportName()); Out.write(",[[\"Root\",RootClass,0,-1,0,0]]);\n");

       super.ExportSchema(Out, Ctx, "com.ibm.odcb.tutorial.businessobjects.User`_wdo4js_js");
       super.ExportSchema(Out, Ctx, "com.ibm.odcb.tutorial.businessobjects.Stock`_wdo4js_js");

       Out.write("A(RootClass,[[\"xmi:id\",\"id\",0],[\"name\",\"string\",0,0,1,0]]);\n");
       Out.write("R(RootClass,[[\"users\",UserClass,0,-1,1,0],[\"stocks\",StockClass,0,-1,1,0],[\"placeHolderStock\",StockClass,1,1,1,0]]);\n");

       if (FirstTime == true)
        { Out.write("var WDO4JSModelRoot_"); Out.write(Ctx.getLastModelName()); Out.write("=new XMILoader(WDO4JSModel_"); Out.write(Ctx.getSchemaExportName()); Out.write(", 'Root', 'com/ibm/odcb/tutorial/wdo4jsmediators/Portfolio/Portfolio_client.ecore');\n");
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
          Out.write("var WDO4JSModelRoot_"); Out.write(Ctx.getLastModelName()); Out.write("=new XMILoader(WDO4JSModel_"); Out.write(Ctx.getSchemaExportName()); Out.write(", 'Root', 'com/ibm/odcb/tutorial/wdo4jsmediators/Portfolio/Portfolio_client.ecore');\n");
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
       com.ibm.odcb.tutorial.businessobjects.Root Obj = (com.ibm.odcb.tutorial.businessobjects.Root) O;

       boolean FirstTime = Ctx.isFirstDataExport();
       if (FirstTime == true)
        Out.write("<SCRIPT> var C; var XTOTOX1=C; C=new EFactory().create; var I; var XTOTOX2=I; I=new ECreator().Init;\n");

       if (Obj == null)
        {
          Out.write("var "+Var+" = null;");
          return;
        }
       Out.write("var "+Var+" = C(RootClass);");

       Mediator.ExportData(Out, Ctx, Obj.getUsers(), "com.ibm.odcb.tutorial.businessobjects.User`_wdo4js_js", DebugMode);
       String UserVar1= Ctx.PopArrayVar("com.ibm.odcb.tutorial.businessobjects.User`_wdo4js_js");
       Mediator.ExportData(Out, Ctx, Obj.getStocks(), "com.ibm.odcb.tutorial.businessobjects.Stock`_wdo4js_js", DebugMode);
       String StockVar2= Ctx.PopArrayVar("com.ibm.odcb.tutorial.businessobjects.Stock`_wdo4js_js");
       String StockVar3= Mediator.ExportData(Out, Ctx, Obj.getPlaceHolderStock(), "com.ibm.odcb.tutorial.businessobjects.Stock`_wdo4js_js", DebugMode);
       Out.write("I("+Var+",[\""+Var+"\""
                +","+com.ibm.odcb.jrender.misc.StringUtil.QuoteDoubleAndEscape(Obj.getName(),false)
                +"],["
                +" "+(UserVar1==null?"null":"["+UserVar1+"]")
                +","+(StockVar2==null?"null":"["+StockVar2+"]")
                +","+(StockVar3.length() == 0 ? "null": StockVar3)+"]);\n");

       if (FirstTime == true)
        { Out.write("WDO4JSModelRoot_"); Out.write(Ctx.getLastModelName()); Out.write(".Root.eAdd('Root', "+Var+");"); 
          Out.write("C=XTOTOX1;I=XTOTOX2;</SCRIPT>\n"); }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
     }
 }
