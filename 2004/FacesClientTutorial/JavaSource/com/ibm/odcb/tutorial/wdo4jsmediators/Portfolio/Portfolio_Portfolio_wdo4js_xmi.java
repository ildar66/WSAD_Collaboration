

////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
//
//     *********************************************************
//     ** THIS CODE IS GENERATED AND **MUST NOT** BE MODIFIED **
//     *********************************************************
//
// Object to XMI-WDO4JS Mediator: version 1.
////  1 : - Supports references and unicity
//      - Mediator to generic Java classes (through public member variables, explitic and generic gets/sets)
//      - Supports ArrayList member variables for multiplicity
//      - No support for XMI Schema yet
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
import java.io.StringWriter;

//     *********************************************************
//     ** THIS CODE IS GENERATED AND **MUST NOT** BE MODIFIED **
//     *********************************************************

public class Portfolio_Portfolio_wdo4js_xmi extends Mediator
 {
   public Portfolio_Portfolio_wdo4js_xmi() { super("Portfolio", " "); }
   public String getSignature(Object O, String modelName, boolean First, int ComplexPropertyCount)
    {
      if (O == null) return "";
      try
       {
         com.ibm.odcb.tutorial.businessobjects.Portfolio Obj = (com.ibm.odcb.tutorial.businessobjects.Portfolio) O;
         StringBuffer SigStr = new StringBuffer(128);
         SigStr.append("`").append(Obj.getPortfolioName());
         if (Obj.getUser() == null)
          {
            SigStr.append("`").append(1+ComplexPropertyCount).append("�");
          }
         else
          {
            SigStr.append("`").append(Obj.getUser().getRefNum());
          }

         return First == true ? modelName+"`com.ibm.odcb.tutorial.businessobjects.Portfolio"+SigStr.toString() : SigStr.toString();
       }
      catch (Exception e)
       {
         if (e instanceof java.lang.ClassCastException)
          {
            System.out.println("ClassCastException: Expecting type 'com.ibm.odcb.tutorial.businessobjects.Portfolio' but found object of type '"+O.getClass().getName()+"'"); 
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
       boolean FirstTime = (Ctx.getExportedSchemaCount()<=0);
       boolean exportDateEDataType = (Ctx.isExportDateEDataType());
       if (FirstTime == true)
       {
         Out.write("<SCRIPT> var ecoreSchema = \"\"");
         Out.write("+\"<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?>\"\n");
         Out.write("+\"<ecore:EPackage xmi:version=\\\"2.0\\\" xmlns:xmi=\\\"http://www.omg.org/XMI\\\" xmlns:xsi=\\\"http://www.w3.org/2001/XMLSchema-instance\\\" xmlns:ecore=\\\"http://www.eclipse.org/emf/2002/Ecore\\\" xmlns:odc=\\\"http://www.ibm.com/Odyssey\\\" name=\\\"Portfolio\\\" nsURI=\\\"com/ibm/odcb/tutorial/wdo4jsmediators/Portfolio/Portfolio_client.ecore\\\" nsPrefix=\\\"\\\">\"\n");
       }

         Out.write("+\"  <eClassifiers xsi:type=\\\"ecore:EClass\\\" name=\\\"Portfolio\\\">\"\n");
         Out.write("+\"    <eAttributes name=\\\"ODCstatus\\\" eType=\\\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EChar\\\"/>\"\n");
         Out.write("+\"    <eAttributes name=\\\"ODCid\\\" eType=\\\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\\\"/>\"\n");
         Out.write("+\"    <eAttributes name=\\\"xmi:id\\\" eType=\\\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\\\"/>\"\n");
         Out.write("+\"    <eAttributes name=\\\"portfolioName\\\" eType=\\\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\\\" lowerBound=\\\"0\\\" upperBound=\\\"1\\\"/>\"\n");
         Out.write("+\"    <eReferences name=\\\"positions\\\" eType=\\\"#//Position\\\" lowerBound=\\\"0\\\" upperBound=\\\"-1\\\" containment=\\\"true\\\"/>\"\n");
         Out.write("+\"    <eReferences name=\\\"user\\\" eType=\\\"#//User\\\" lowerBound=\\\"1\\\" upperBound=\\\"1\\\" containment=\\\"false\\\"/>\"\n");
         Out.write("+\"  </eClassifiers>\"\n");

       Ctx.addToExportedSchemaList("Portfolio");
       super.ExportSchema(Out, Ctx, "com.ibm.odcb.tutorial.businessobjects.Position`_wdo4js_xmi");
       super.ExportSchema(Out, Ctx, "com.ibm.odcb.tutorial.businessobjects.User`_wdo4js_xmi");
       if (FirstTime == true)
       {
         if (Ctx.isExportDateEDataType())
         {
         Out.write("+\"  <eClassifiers xsi:type=\\\"ecore:EDataType\\\" name=\\\"\\\" instanceClassName=\\\"java.util.Date\\\"/>\"\n");
         }
         Out.write("+\"  <eClassifiers xsi:type=\\\"ecore:EClass\\\" name=\\\"Diff\\\">\"\n");
         Out.write("+\"    <eReferences name=\\\"Current\\\" eType=\\\"#//ClientRoot\\\" lowerBound=\\\"1\\\" upperBound=\\\"1\\\" containment=\\\"true\\\"/>\"\n");
         Out.write("+\"    <eReferences name=\\\"Original\\\" eType=\\\"#//ClientRoot\\\" lowerBound=\\\"1\\\" upperBound=\\\"1\\\" containment=\\\"true\\\"/>\"\n");
         Out.write("+\"  </eClassifiers>\"\n");
         Out.write("+\"  <eClassifiers xsi:type=\\\"ecore:EClass\\\" name=\\\"ClientRoot\\\">\"\n");
         Out.write("+\"    <eReferences name=\\\"Stock\\\" eType=\\\"#//Stock\\\" lowerBound=\\\"0\\\" upperBound=\\\"-1\\\" containment=\\\"false\\\"/>\"\n");
         Out.write("+\"    <eReferences name=\\\"Position\\\" eType=\\\"#//Position\\\" lowerBound=\\\"0\\\" upperBound=\\\"-1\\\" containment=\\\"false\\\"/>\"\n");
         Out.write("+\"    <eReferences name=\\\"User\\\" eType=\\\"#//User\\\" lowerBound=\\\"0\\\" upperBound=\\\"-1\\\" containment=\\\"false\\\"/>\"\n");
         Out.write("+\"    <eReferences name=\\\"Root\\\" eType=\\\"#//Root\\\" lowerBound=\\\"0\\\" upperBound=\\\"-1\\\" containment=\\\"false\\\"/>\"\n");
         Out.write("+\"    <eReferences name=\\\"Portfolio\\\" eType=\\\"#//Portfolio\\\" lowerBound=\\\"0\\\" upperBound=\\\"-1\\\" containment=\\\"false\\\"/>\"\n");
         Out.write("+\"  </eClassifiers>\"\n");
         Out.write("+\"</ecore:EPackage>\"\n");
         Out.write(";</SCRIPT>\n");
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
          Out.write("var WDO4JSModelRoot_"); Out.write(Ctx.getLastModelName()); Out.write("=new XMILoader(WDO4JSModel_"); Out.write(Ctx.getSchemaExportName()); Out.write(", 'Portfolio', 'com/ibm/odcb/tutorial/wdo4jsmediators/Portfolio/Portfolio_client.ecore');\n");
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
       com.ibm.odcb.tutorial.businessobjects.Portfolio Obj = (com.ibm.odcb.tutorial.businessobjects.Portfolio) O;

       boolean FirstTime = Ctx.isFirstDataExport();
       if (FirstTime == true)
         Out.write("<SCRIPT> var InstanceData = \"<xmi:XMI xmi:version=\\\"2.0\\\" xmlns:xmi=\\\"http://www.omg.org/XMI\\\">  <xmi:Documentation>  <exporter>Odyssey XMI Serializer</exporter>  <exporterVersion>1.0</exporterVersion>  </xmi:Documentation>\"\n");

       StringWriter InnerOut = new StringWriter(256);

       Mediator.ExportData(Out, Ctx, Obj.getPositions(), "com.ibm.odcb.tutorial.businessobjects.Position`_wdo4js_xmi", DebugMode);
       String PositionVar1= Ctx.PopArrayVar("com.ibm.odcb.tutorial.businessobjects.Position`_wdo4js_xmi");
       String UserVar2= Mediator.ExportData(Out, Ctx, Obj.getUser(), "com.ibm.odcb.tutorial.businessobjects.User`_wdo4js_xmi", DebugMode);

       String InnerText = InnerOut.toString();
       Out.write(" + \"<Portfolio xmi:id=\\\""+Var+"\\\" portfolioName="+com.ibm.odcb.jrender.misc.StringUtil.QuoteDoubleAndEscapeForXML(Obj.getPortfolioName(),true)+" positions=\\\""+PositionVar1+"\\\" user=\\\""+UserVar2+"\\\"/>\"\n");

       if (FirstTime == true)
        { Out.write("+\"</xmi:XMI>\";\n");
          Out.write("WDO4JSModelRoot_"+Ctx.getLastModelName()+".LoadXMIString(InstanceData); ");  
          Out.write(" </SCRIPT>\n"); }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
     }
 }
