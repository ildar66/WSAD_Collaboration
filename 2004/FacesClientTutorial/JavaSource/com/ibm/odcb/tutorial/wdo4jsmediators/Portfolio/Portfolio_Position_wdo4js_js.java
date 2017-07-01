

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

public class Portfolio_Position_wdo4js_js extends Mediator
 {
   public Portfolio_Position_wdo4js_js() { super("Position", ","); }
   public String getSignature(Object O, String modelName, boolean First, int ComplexPropertyCount)
    {
      if (O == null) return "";
      try
       {
         com.ibm.odcb.tutorial.businessobjects.Position Obj = (com.ibm.odcb.tutorial.businessobjects.Position) O;
         StringBuffer SigStr = new StringBuffer(128);
         SigStr.append("`").append(Obj.getRefNum());
         SigStr.append((Obj.getPurchaseDate())==null?-1:(Obj.getPurchaseDate()).getTime());

         return First == true ? modelName+"`com.ibm.odcb.tutorial.businessobjects.Position"+SigStr.toString() : SigStr.toString();
       }
      catch (Exception e)
       {
         if (e instanceof java.lang.ClassCastException)
          {
            System.out.println("ClassCastException: Expecting type 'com.ibm.odcb.tutorial.businessobjects.Position' but found object of type '"+O.getClass().getName()+"'"); 
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

       Out.write("var PositionClass=new EClass(\"Position\",1);"); 
       Out.write("R(WDO4JSModel_"); Out.write(Ctx.getSchemaExportName()); Out.write(",[[\"Position\",PositionClass,0,-1,0,0]]);\n");

       super.ExportSchema(Out, Ctx, "com.ibm.odcb.tutorial.businessobjects.User`_wdo4js_js");
       super.ExportSchema(Out, Ctx, "com.ibm.odcb.tutorial.businessobjects.Portfolio`_wdo4js_js");
       super.ExportSchema(Out, Ctx, "com.ibm.odcb.tutorial.businessobjects.Stock`_wdo4js_js");

       Out.write("A(PositionClass,[[\"xmi:id\",\"id\",0],[\"refNum\",\"int\",0,0,1,1],[\"price\",\"float\",0,0,1,0],[\"quantity\",\"int\",0,0,1,0],[\"symbol\",\"string\",0,0,1,0],[\"value\",\"double\",1,\"this.getPrice()*this.getQuantity()\",0],[\"currentPrice\",\"double\",1,\"this.getStock().getCurrentPrice()\",0],[\"gains\",\"double\",1,\"(this.getStock().getCurrentPrice()-this.getPrice())*this.getQuantity()\",0],[\"currentValue\",\"double\",1,\"this.getStock().getCurrentPrice()*this.getQuantity()\",0],[\"label\",\"string\",1,\"this.getStock().getSymbol()+': $'+Math.round((this.getStock().getCurrentPrice()*this.getQuantity())*100)/100.0\",0],[\"purchaseDate\",\"date\",0,0,1,1]]);\n");
       Out.write("R(PositionClass,[[\"user\",UserClass,1,1,0,0],[\"portfolio\",PortfolioClass,1,1,0,0],[\"stock\",StockClass,1,1,0,0]]);\n");

       if (FirstTime == true)
        { Out.write("var WDO4JSModelRoot_"); Out.write(Ctx.getLastModelName()); Out.write("=new XMILoader(WDO4JSModel_"); Out.write(Ctx.getSchemaExportName()); Out.write(", 'Position', 'com/ibm/odcb/tutorial/wdo4jsmediators/Portfolio/Portfolio_client.ecore');\n");
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
          Out.write("var WDO4JSModelRoot_"); Out.write(Ctx.getLastModelName()); Out.write("=new XMILoader(WDO4JSModel_"); Out.write(Ctx.getSchemaExportName()); Out.write(", 'Position', 'com/ibm/odcb/tutorial/wdo4jsmediators/Portfolio/Portfolio_client.ecore');\n");
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
       com.ibm.odcb.tutorial.businessobjects.Position Obj = (com.ibm.odcb.tutorial.businessobjects.Position) O;

       boolean FirstTime = Ctx.isFirstDataExport();
       if (FirstTime == true)
        Out.write("<SCRIPT> var C; var XTOTOX1=C; C=new EFactory().create; var I; var XTOTOX2=I; I=new ECreator().Init;\n");

       if (Obj == null)
        {
          Out.write("var "+Var+" = null;");
          return;
        }
       Out.write("var "+Var+" = C(PositionClass);");

       String UserVar3= Mediator.ExportData(Out, Ctx, Obj.getUser(), "com.ibm.odcb.tutorial.businessobjects.User`_wdo4js_js", DebugMode);
       String PortfolioVar4= Mediator.ExportData(Out, Ctx, Obj.getPortfolio(), "com.ibm.odcb.tutorial.businessobjects.Portfolio`_wdo4js_js", DebugMode);
       String StockVar5= Mediator.ExportData(Out, Ctx, Obj.getStock(), "com.ibm.odcb.tutorial.businessobjects.Stock`_wdo4js_js", DebugMode);
       Out.write("I("+Var+",[\""+Var+"\""
                +","+Obj.getRefNum()
                +","+Obj.getPrice()
                +","+Obj.getQuantity()
                +","+com.ibm.odcb.jrender.misc.StringUtil.QuoteDoubleAndEscape(Obj.getSymbol(),false)
                +","+(Obj.getPurchaseDate() == null?-1:Obj.getPurchaseDate().getTime())
                +"],["
                +" "+UserVar3
                +","+PortfolioVar4
                +","+StockVar5+"]);\n");

       if (FirstTime == true)
        { Out.write("WDO4JSModelRoot_"); Out.write(Ctx.getLastModelName()); Out.write(".Root.eAdd('Position', "+Var+");"); 
          Out.write("C=XTOTOX1;I=XTOTOX2;</SCRIPT>\n"); }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
     }
 }
