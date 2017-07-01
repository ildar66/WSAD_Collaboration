

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


package com.ibm.odcb.simple.wdo4jsmediators.Hello;

import java.io.Writer;
import com.ibm.odcb.jrender.mediators.Mediator;
import com.ibm.odcb.jrender.mediators.PageContext;
import com.ibm.odcb.jrender.mediators.ExportException;
import java.io.StringWriter;

//     *********************************************************
//     ** THIS CODE IS GENERATED AND **MUST NOT** BE MODIFIED **
//     *********************************************************

public class Hello_Hello_wdo4js_xmi extends Mediator
 {
   public Hello_Hello_wdo4js_xmi() { super("Hello", " "); }
   public String getSignature(Object O, String modelName, boolean First, int ComplexPropertyCount)
    {
      if (O == null) return "";
      try
       {
         com.ibm.odcb.simple.businessobjects.Hello Obj = (com.ibm.odcb.simple.businessobjects.Hello) O;
         StringBuffer SigStr = new StringBuffer(128);
         SigStr.append("`").append(Obj.getText());

         return First == true ? modelName+"`com.ibm.odcb.simple.businessobjects.Hello"+SigStr.toString() : SigStr.toString();
       }
      catch (Exception e)
       {
         if (e instanceof java.lang.ClassCastException)
          {
            System.out.println("ClassCastException: Expecting type 'com.ibm.odcb.simple.businessobjects.Hello' but found object of type '"+O.getClass().getName()+"'"); 
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
         Out.write("+\"<ecore:EPackage xmi:version=\\\"2.0\\\" xmlns:xmi=\\\"http://www.omg.org/XMI\\\" xmlns:xsi=\\\"http://www.w3.org/2001/XMLSchema-instance\\\" xmlns:ecore=\\\"http://www.eclipse.org/emf/2002/Ecore\\\" xmlns:odc=\\\"http://www.ibm.com/Odyssey\\\" name=\\\"Hello\\\" nsURI=\\\"com/ibm/odcb/simple/wdo4jsmediators/Hello/Hello_client.ecore\\\" nsPrefix=\\\"\\\">\"\n");
       }

         Out.write("+\"  <eClassifiers xsi:type=\\\"ecore:EClass\\\" name=\\\"Hello\\\">\"\n");
         Out.write("+\"    <eAttributes name=\\\"ODCstatus\\\" eType=\\\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EChar\\\"/>\"\n");
         Out.write("+\"    <eAttributes name=\\\"ODCid\\\" eType=\\\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\\\"/>\"\n");
         Out.write("+\"    <eAttributes name=\\\"xmi:id\\\" eType=\\\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\\\"/>\"\n");
         Out.write("+\"    <eAttributes name=\\\"text\\\" eType=\\\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\\\" lowerBound=\\\"0\\\" upperBound=\\\"1\\\"/>\"\n");
         Out.write("+\"  </eClassifiers>\"\n");

       Ctx.addToExportedSchemaList("Hello");
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
         Out.write("+\"    <eReferences name=\\\"Hello\\\" eType=\\\"#//Hello\\\" lowerBound=\\\"0\\\" upperBound=\\\"-1\\\" containment=\\\"false\\\"/>\"\n");
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
          Out.write("var WDO4JSModelRoot_"); Out.write(Ctx.getLastModelName()); Out.write("=new XMILoader(WDO4JSModel_"); Out.write(Ctx.getSchemaExportName()); Out.write(", 'Hello', 'com/ibm/odcb/simple/wdo4jsmediators/Hello/Hello_client.ecore');\n");
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
       com.ibm.odcb.simple.businessobjects.Hello Obj = (com.ibm.odcb.simple.businessobjects.Hello) O;

       boolean FirstTime = Ctx.isFirstDataExport();
       if (FirstTime == true)
         Out.write("<SCRIPT> var InstanceData = \"<xmi:XMI xmi:version=\\\"2.0\\\" xmlns:xmi=\\\"http://www.omg.org/XMI\\\">  <xmi:Documentation>  <exporter>Odyssey XMI Serializer</exporter>  <exporterVersion>1.0</exporterVersion>  </xmi:Documentation>\"\n");

       StringWriter InnerOut = new StringWriter(256);


       String InnerText = InnerOut.toString();
       Out.write(" + \"<Hello xmi:id=\\\""+Var+"\\\" text="+com.ibm.odcb.jrender.misc.StringUtil.QuoteDoubleAndEscapeForXML(Obj.getText(),true)+"/>\"\n");

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
