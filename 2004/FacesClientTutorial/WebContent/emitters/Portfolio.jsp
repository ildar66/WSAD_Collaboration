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

<%@ page import="com.ibm.odcb.jrender.emitters.*" %>  
<%@ page import="com.ibm.odcb.tutorial.businessobjects.*" %>
<%@ page import="com.ibm.odcb.jrender.misc.*" %>

<HTML>
<HEAD>
<LINK rel="stylesheet" type="text/css" href="theme/stylesheet.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="theme/datagrid.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="theme/tree.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="theme/tabpanel.css"
	title="Style">
</HEAD><BODY>

<jsp:useBean id="ODCPCTX" class="com.ibm.odcb.jrender.mediators.PageContext" scope="request" />
<jsp:useBean id="StartingPortfolio" class="com.ibm.odcb.tutorial.businessobjects.Portfolio" scope="request" />
<jsp:useBean id="StartingUser" class="com.ibm.odcb.tutorial.businessobjects.User" scope="request" />
<jsp:useBean id="PortfolioData" class="com.ibm.odcb.tutorial.businessobjects.Root" scope="request" />

<form name="PortfolioSubmit" id="PortfolioSubmit" action="PortfolioServlet"  method="POST">
<%
         URLRewriter UrlRewriter = new ServletURLRewriter(request);
         InitializationEmitter Init = new InitializationEmitter(true, -1, "en_US", UrlRewriter, request.getServerName(), request.getServerPort());
         Init.Export(out, ODCPCTX);

         ClientConverterHelper.initJSLibraries(Locale.getDefault(),out,ODCPCTX);
%>

<SCRIPT> Log.pageSeparation("Sample Portfolio JSP"); </SCRIPT>


<%
         WDO4JSEmitter W = new WDO4JSEmitter(); 
         W.Init(PortfolioData, "PortfolioModel");
         W.Export(out, ODCPCTX);
         int ExportCount = W.getExportCount();
         System.out.println(ExportCount+" records output");
%>

<%    
         String RootId = W.getExportIDByObject(PortfolioData);
         String DGID = StartingPortfolio == null ? null : W.getExportIDByObject(StartingPortfolio);

		 PanelEmitter TreePanel = new PanelEmitter("Tree Panel", "Tree", null, null, false);

%>

<div id="<%=TreePanel.getPanelID()%>">
  <TABLE border=0><TR><TD>&nbsp;&nbsp;</TD><TD>
<%

         System.out.println("RootId is: "+RootId);
         TreeViewEmitter T1 = new TreeViewEmitter("PortfolioModel", RootId);
		 NodeAttributeEmitterHelper N = new NodeAttributeEmitterHelper("Root");
		 N.addStyleMap("name", null, "users");
		 T1.addNode(N);
		 N = new NodeAttributeEmitterHelper("User");
		 N.addStyleMap("lastName", null, "portfolios");
		 N.addIconMap(UrlRewriter.Rewrite("jsl/tree/icons/folder.gif"), UrlRewriter.Rewrite("jsl/tree/icons/folderopen.gif"), null, null, new Boolean(true));
		 T1.addNode(N);
		 N = new NodeAttributeEmitterHelper("Portfolio");
		 N.addStyleMap("portfolioName", null, null);
		 T1.addNode(N);
         T1.Export(out, ODCPCTX);
         System.out.println("Tree1 SECTION"); 
%>
  </TD></TR></TABLE>
  </div>
<%
		 PanelEmitter DGPanel = new PanelEmitter("DataGrid Panel", "DG", null, null, true);
%>
<div id="<%=DGPanel.getPanelID()%>">

<TABLE BORDER=0 ALIGN=CENTER> <TR><TD><CENTER> <H2 id="HeaderLabel"> Pierre Jackson's Portfolio 1</H2></CENTER></TD></TR><TR><TD ALIGN="CENTER">
<%      
         System.out.println("StartingPortfolioID is: "+DGID);
         DataGridEmitter  DG1 = new DataGridEmitter();
         DG1.Init("PortfolioModel", DGID, "positions", true, 5,null,"dataGrid");	
         String dateConverterString = ClientConverterHelper.getJsDateConverterString("MM/dd/yyyy HH:mm:ss z", "date", "long", null, Locale.getDefault());
         String currencyConverterString = ClientConverterHelper.getJsNumberConverter("#,##0.00;(#,##0.00)", "currency", null, "US$", null, null, 2, 2, null, Locale.getDefault());	 
         DG1.addColumn(new DataGridEmitterHelper("symbol"      , "Symbol"       , true, "center", "160", false, null));
         DG1.addColumn(new DataGridEmitterHelper("quantity"    , "Quantity"     , true, "center" , "40", false, null));
         DG1.addColumn(new DataGridEmitterHelper("price"       , "Price"        , true, "right" , "140", false, currencyConverterString));
         DG1.addColumn(new DataGridEmitterHelper("currentPrice", "Current Price", true, "right" , "140", false, currencyConverterString));
         DG1.addColumn(new DataGridEmitterHelper("gains"       , "Gains/Losses" , true, "right" , "140", false, currencyConverterString));
         DG1.addColumn(new DataGridEmitterHelper("purchaseDate", "Purchase Date" , true, "center" , "140", false, dateConverterString));
         DG1.Export(out, ODCPCTX);
%>
<% System.out.println("DataGrid1 SECTION"); %>
</TD></TR></TABLE>
</div>

<%
		 PanelEmitter FullPanel = new PanelEmitter("Full Panel", "Full", null, null, true);
%>
<div id="<%=FullPanel.getPanelID()%>">

<TABLE BORDER=0 ALIGN=CENTER WIDTH="100%"><TR><TD VALIGN=TOP ALIGN=LEFT WIDTH="30%">

<%
         TreeViewEmitter T2 = new TreeViewEmitter("PortfolioModel", RootId);
         N = new NodeAttributeEmitterHelper("Root");
		 N.addStyleMap("name", null, "users");
		 T2.addNode(N);
		 N = new NodeAttributeEmitterHelper("User");
		 N.addStyleMap("lastName", null, "portfolios");
		 T2.addNode(N);
		 N = new NodeAttributeEmitterHelper("Portfolio");
		 N.addStyleMap("portfolioName", null, null);
		 T2.addNode(N);
         T2.Export(out, ODCPCTX);
%>
<% System.out.println("Tree2 SECTION"); %>

</TD><TD WIDTH="70%">
<TABLE ALIGN=CENTER>
	<TR>
		<TD>
			<H2 id="HeaderLabel2"><%--d:binder>
				<h:output_text id="phUserName" valueRef="Root.users[0].lastName" /> 's 
				<h:output_text id="phPortName" valueRef="Root.users[0].portfolios[0].portfolioName" />
			</d:binder--%>
			<%
				String userId = W.getExportIDByObject(StartingUser);
				OutputTextEmitter O6 = new OutputTextEmitter();
                O6.Init("PortfolioModel", userId, "lastName");
                O6.Export(out, ODCPCTX);
			%> 's
			<%
				OutputTextEmitter O7 = new OutputTextEmitter();
                O7.Init("PortfolioModel", DGID, "portfolioName");
                O7.Export(out, ODCPCTX);
			%>
			</H2>
		</TD>			
	</TR>
</TABLE>
<BR />
<%
         DataGridEmitter  DG2 = new DataGridEmitter();
         //DG2.Init("PortfolioModel", DGID, "positions", true, 5, true, "Item", true, "center", 100, null, null, 0, false, null, null, null);				 
         DG2.Init("PortfolioModel", DGID, "positions", true, 5, true, "Item", true, "center", "100", null, "dataGrid", 0, false, null, null, null);
         DG2.addColumn(new DataGridEmitterHelper("symbol"      , "Symbol"       , true , "center", "160", true , null));
         DG2.addColumn(new DataGridEmitterHelper("quantity"    , "Quantity"     , false, "center" ,  "40", false, null));
         DG2.addColumn(new DataGridEmitterHelper("price"       , "Price Paid"   , true , "right" , "140", false, currencyConverterString));
         DG2.addColumn(new DataGridEmitterHelper("value"       , "Base Value"   , true , "right" , "140", false, currencyConverterString));
         DG2.addColumn(new DataGridEmitterHelper("currentPrice", "Current Price", true , "right" , "140", false, currencyConverterString));
         DG2.addColumn(new DataGridEmitterHelper("currentValue", "Current Value", true , "right" , "140", false, currencyConverterString));
         DG2.addColumn(new DataGridEmitterHelper("gains"       , "Gains/Losses" , true , "right" , "140", false, currencyConverterString));
         DG2.addColumn(new DataGridEmitterHelper("purchaseDate", "Purchase Date" , true, "center" , "140", false, dateConverterString));
         DG2.Export(out, ODCPCTX);
%>
<% System.out.println("DataGrid2 SECTION"); %>

</TD></TR></TABLE>
</div>

   

<%
		 TabbedPanelEmitter TabbedPanel = new TabbedPanelEmitter();
		 TabbedPanel.Init("320", "950", 3, null, null, null, null, true, true, "tabbedPanel", true, true, -1, -1, -1, -1);
		 TabbedPanel.addPanel(TreePanel);
		 TabbedPanel.addPanel(DGPanel);
		 TabbedPanel.addPanel(FullPanel);
		 TabbedPanel.Export(out, ODCPCTX);
%>
</form>
<BR>
<%
String StockID = W.getExportIDByObject(PortfolioData.getPlaceHolderStock());
%>

<%
		 PanelEmitter GraphPanel = new PanelEmitter("Graph Panel", "Graph", null, null, true);
%>
<div id="<%=GraphPanel.getPanelID()%>">
<CENTER>
<%
         //GD.Init("PortfolioModel", DGID, "positions", "currentValue", "label", "stocks", true, "600", "248", true, true, true,GraphDrawEmitter.PIE_CHART );
         //GD.Export(out, ODCPCTX);
         
		GraphDrawEmitter GD = new GraphDrawEmitter();    
        GD.Init(
				"graphDraw1",
				"PortfolioModel",
				DGID,
				"positions",
				"800",
				"800",
				"Pie",
				true,
				true,
				true,
				20,
				"My chart",
				"x",
				"y",
				true,
				false
				);     
        GD.setShowLabel(true);     
		GD.setLabelAttributeName("symbol");
        GD.setGrouped(false);    
        GD.addDataSeries("s1", "price", "price", "None");    
        GD.addDataSeries("s2", "quantity", "quantity", "None");
        GD.setLabelFormat(GraphDrawEmitter.NO_FORMAT);
        GD.setLabelConverterScript("");
        String numberConverterString = ClientConverterHelper.getJsNumberConverter("#,##0.00;(#,##0.00)", "number", null, null, null, null, 2, 2, null, Locale.getDefault());
        GD.setDataConverterScript(numberConverterString);
        GD.Export(out, ODCPCTX);

         
%>
</CENTER>
</div>
<%
         WebServicesEmitter WS = new WebServicesEmitter();
         WS.Init("http://localhost:9080/FacesClientTutorial/services/StockWebService/wsdl/StockWebService.wsdl", "StockWebService", "getQuote");
         
         WS.AddInputParameter ("PortfolioModel", StockID, "symbol", null, null, false);
         WS.AddOutputParameter("PortfolioModel", StockID, "currentPrice", "lastPrice", null);
         WS.AddOutputParameter("PortfolioModel", StockID, "volume", "volume", null);
         WS.AddOutputParameter("PortfolioModel", StockID, "change", "change", null);

         WS.Export(out, ODCPCTX);
%>
<%
		 PanelEmitter WSPanel = new PanelEmitter("WebServices Panel", "WS", null, null, true);
%>
<div id="<%=WSPanel.getPanelID()%>">
<CENTER>
         <TABLE BORDER=0 CELLPADDING=4>
           <TR><TD ALIGN=right>Stock Symbol :</TD><TD>
           <% 
		      InputTextEmitter I = new InputTextEmitter();
              I.Init("PortfolioModel", StockID, "symbol");
              I.Export(out, ODCPCTX);
           %>
           </TD><TD>
           
           <input type=button value="Fetch Info" onClick="<%=WS.ADAPTER_VAR%><%=WS.getExportID()%>.Execute();">
           
           </TD></TR>

           <TR><TD COLSPAN=2><HR></TD></TR>

           <TR><TD ALIGN=right>Symbol :</TD><TD>
           <% 
		      OutputTextEmitter O1 = new OutputTextEmitter();
              O1.Init("PortfolioModel", StockID, "symbol");
              O1.Export(out, ODCPCTX);
           %>
           </TD></TR>
           <TR><TD ALIGN=right>Market Price :</TD><TD>
           <% 
		      OutputTextEmitter O2 = new OutputTextEmitter();
              O2.Init("PortfolioModel", StockID, "currentPrice");
              O2.Export(out, ODCPCTX);
           %>
           </TD></TR>
           <TR><TD ALIGN=right>Change :      </TD><TD>
           <% 
		      OutputTextEmitter O3 = new OutputTextEmitter();
              O3.Init("PortfolioModel", StockID, "change");
              O3.Export(out, ODCPCTX);
           %>

           &nbsp;&nbsp;(&nbsp;
           <%
                OutputTextEmitter O4 = new OutputTextEmitter();
                O4.Init("PortfolioModel", StockID, "percentage");
                O4.Export(out, ODCPCTX);
           %>
           &nbsp;%&nbsp;)

           </TD></TR>
           <TR><TD ALIGN=right>Volume :      </TD><TD>
           <% 
		      OutputTextEmitter O5 = new OutputTextEmitter();
              O5.Init("PortfolioModel", StockID, "volume");
              O5.Export(out, ODCPCTX);
           %>
           </TD></TR>
         </TABLE>
</CENTER>
</div>

<%
		 TabbedPanel = new TabbedPanelEmitter();
		 TabbedPanel.Init("320", "950", 2, null, null, null, null, false, true, "tabbedPanel", false, true, -1, -1, -1, -1);
		 TabbedPanel.addPanel(GraphPanel);
		 TabbedPanel.addPanel(WSPanel);
		 TabbedPanel.Export(out, ODCPCTX);
		 
         EventSelectAndActivateEmitter E1 = new EventSelectAndActivateEmitter(T1, "OnHighlight", "Portfolio", DG1, null);
         E1.Export(out, ODCPCTX);
         
         EventSelectAndActivateEmitter E2 = new EventSelectAndActivateEmitter(T2, "OnHighlight", "Portfolio", DG2, null);
         E2.addTargetEmitter(GD, null);
         E2.addTargetEmitter(O6, "user");
         E2.addTargetEmitter(O7, null);
         E2.Export(out, ODCPCTX);
         
         EventSelectAndSetEmitter E3 = new EventSelectAndSetEmitter(DG2, "PortfolioModel", "OnHighlight", "Position");
         E3.addControlMetadataHelper(new ControlMetadataHelper("stock", RootId, "placeHolderStock"));
         E3.Export(out, ODCPCTX);
%>

</BODY></HTML>

