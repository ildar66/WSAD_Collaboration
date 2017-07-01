<%--
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
--%>
<%@ page contentType="text/html" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.PrintWriter" %>

<%@ page import="com.ibm.odcb.jrender.emitters.*" %>
<%@ page import="com.ibm.odcb.jrender.misc.*" %>

<%@ page import="com.ibm.odcb.simple.businessobjects.*" %>

<%-- Create the bean for the PageContext object --%>
<jsp:useBean id="ODCPCTX"
	class="com.ibm.odcb.jrender.mediators.PageContext" scope="request" />

<HTML>
<HEAD></HEAD>
<BODY>
<H1 align="center">Faces Client Hello World Sample</H1>
<BR/><BR/>
<%-- The InitializationEmitter initializes all the supporting javascript libraries for the Odyssey controls --%>
<%
         PrintWriter Out = new PrintWriter(out);
         URLRewriter UrlRewriter = new ServletURLRewriter(request);
         InitializationEmitter Init = new InitializationEmitter(true, -1, "en_US", UrlRewriter, request.getServerName(), request.getServerPort());
         Init.Export(Out, ODCPCTX);
%>

<%-- Initialize the Hello business object(this is our root object), create the emitter to export out business object,
create a new model passing in the Hello object as the root object for the model, and finally export our business object and get the export id for the object--%>

<%
         com.ibm.odcb.simple.businessobjects.Hello H = new com.ibm.odcb.simple.businessobjects.Hello();
         WDO4JSEmitter W = new WDO4JSEmitter();
         W.Init(H, "HelloModel");
         W.Export(Out, ODCPCTX);
         String rootId = W.getExportIDByObject(H);
         int ExportCount = W.getExportCount();
         System.out.println(ExportCount+" records output");

%>

<CENTER>
<LABEL>Model Data Binder1 : </LABEL>
<%-- We create an input text box emitter which is binds the contents of the input box control to the Text attribute of the Hello object --%>
<%
	InputTextEmitter i1 = new InputTextEmitter();
	i1.Init("HelloModel", rootId, "text");
	i1.Export(Out, ODCPCTX);
%>
<BR/>
<HR/>
<BR/>
<LABEL>Model Data Binder2 : </LABEL>
<%-- We do the same as above and bind the Text attribute to another input box control --%>
<%
	InputTextEmitter i2 = new InputTextEmitter();
	i2.Init("HelloModel", rootId, "text");
	i2.Export(Out, ODCPCTX);		
%>

</CENTER>
</BODY>
</HTML>