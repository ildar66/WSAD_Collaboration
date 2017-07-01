

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

public class Portfolio_Stock_wdo4js_js extends Mediator
 {
   public Portfolio_Stock_wdo4js_js() { super("Stock", ","); }
   public String getSignature(Object O, String modelName, boolean First, int ComplexPropertyCount)
    {
      if (O == null) return "";
      try
       {
         com.ibm.odcb.tutorial.businessobjects.Stock Obj = (com.ibm.odcb.tutorial.businessobjects.Stock) O;
         StringBuffer SigStr = new StringBuffer(128);
         SigStr.append("`").append(Obj.getSymbol());

         return First == true ? modelName+"`com.ibm.odcb.tutorial.businessobjects.Stock"+SigStr.toString() : SigStr.toString();
       }
      catch (Exception e)
       {
         if (e instanceof java.lang.ClassCastException)
          {
            System.out.println("ClassCastException: Expecting type 'com.ibm.odcb.tutorial.businessobjects.Stock' but found object of type '"+O.getClass().getName()+"'"); 
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

       Out.write("var StockClass=new EClass(\"Stock\",1);"); 
       Out.write("R(WDO4JSModel_"); Out.write(Ctx.getSchemaExportName()); Out.write(",[[\"Stock\",StockClass,0,-1,0,0]]);\n");


       Out.write("A(StockClass,[[\"xmi:id\",\"id\",0],[\"symbol\",\"string\",0,0,1,1],[\"currentPrice\",\"string\",0,0,1,0],[\"volume\",\"string\",0,0,1,0],[\"change\",\"string\",0,0,1,0],[\"percentage\",\"double\",1,\"this.getCurrentPrice()-this.getChange()==0?0:this.getChange()/(this.getCurrentPrice()-this.getChange())\",0]]);\n");

       if (FirstTime == true)
        { Out.write("var WDO4JSModelRoot_"); Out.write(Ctx.getLastModelName()); Out.write("=new XMILoader(WDO4JSModel_"); Out.write(Ctx.getSchemaExportName()); Out.write(", 'Stock', 'com/ibm/odcb/tutorial/wdo4jsmediators/Portfolio/Portfolio_client.ecore');\n");
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
          Out.write("var WDO4JSModelRoot_"); Out.write(Ctx.getLastModelName()); Out.write("=new XMILoader(WDO4JSModel_"); Out.write(Ctx.getSchemaExportName()); Out.write(", 'Stock', 'com/ibm/odcb/tutorial/wdo4jsmediators/Portfolio/Portfolio_client.ecore');\n");
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
       com.ibm.odcb.tutorial.businessobjects.Stock Obj = (com.ibm.odcb.tutorial.businessobjects.Stock) O;

       boolean FirstTime = Ctx.isFirstDataExport();
       if (FirstTime == true)
        Out.write("<SCRIPT> var C; var XTOTOX1=C; C=new EFactory().create; var I; var XTOTOX2=I; I=new ECreator().Init;\n");

       if (Obj == null)
        {
          Out.write("var "+Var+" = null;");
          return;
        }
       Out.write("var "+Var+" = C(StockClass);");

       Out.write("I("+Var+",[\""+Var+"\""
                +","+com.ibm.odcb.jrender.misc.StringUtil.QuoteDoubleAndEscape(Obj.getSymbol(),false)
                +","+com.ibm.odcb.jrender.misc.StringUtil.QuoteDoubleAndEscape(Obj.getCurrentPrice(),false)
                +","+com.ibm.odcb.jrender.misc.StringUtil.QuoteDoubleAndEscape(Obj.getVolume(),false)
                +","+com.ibm.odcb.jrender.misc.StringUtil.QuoteDoubleAndEscape(Obj.getChange(),false)
                +"],["+"]);\n");

       if (FirstTime == true)
        { Out.write("WDO4JSModelRoot_"); Out.write(Ctx.getLastModelName()); Out.write(".Root.eAdd('Stock', "+Var+");"); 
          Out.write("C=XTOTOX1;I=XTOTOX2;</SCRIPT>\n"); }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
     }
 }
