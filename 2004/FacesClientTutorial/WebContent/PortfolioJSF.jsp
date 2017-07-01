<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:codeBehind language="java" location="/JavaSource/pagecode/PortfolioJSF.java" --%><%-- /jsf:codeBehind --%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://www.ibm.com/jsf/BrowserFramework" prefix="odc" %>
<%@ taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="theme/Master.css" rel="stylesheet" type="text/css">
<LINK rel="stylesheet" type="text/css" href="theme/tabpanel.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="theme/datagrid.css"
	title="Style">
<LINK rel="stylesheet" type="text/css" href="theme/tree.css"
	title="Style">
<TITLE>PortfolioJSF.jsp</TITLE>
<SCRIPT type="text/javascript">
function func_1(thisObj, thisEvent) {
//use 'thisObj' to refer directly to this component instead of keyword this
//use 'thisEvent' to refer to the event generated instead of keyword event
//thisEvent.eobject is the object mapped to the node that the user clicked on and thisEvent.propertyName is the name of the attribute which supplies the label for the highlighted node.
//actionBegin: action.odc.selectandactivate
SelectAndActivateEventHandler(thisEvent, [ODCRegistry.getClientAdapter('dataGrid1')]);//actionEnd: action.odc.selectandactivate

}
function func_2(thisObj, thisEvent) {
//use 'thisObj' to refer directly to this component instead of keyword this
//use 'thisEvent' to refer to the event generated instead of keyword event
//thisEvent.eobject is the object mapped to the node that the user clicked on and thisEvent.propertyName is the name of the attribute which supplies the label for the highlighted node.
//actionBegin: action.odc.selectandactivate
SelectAndActivateEventHandler(thisEvent, ODCRegistry.getClientAdapters([['phPortName', null], ['dataGrid2', null], ['graphDraw1', null]]));//actionEnd: action.odc.selectandactivate
//actionBegin: action.odc.selectandactivate
SelectAndActivateEventHandler(thisEvent, [[ODCRegistry.getClientAdapter('phUserName'), 'user']]);//actionEnd: action.odc.selectandactivate

}
function func_3(thisObj, thisEvent) {
//use 'thisObj' to refer directly to this component instead of keyword this
//use 'thisEvent' to refer to the event generated instead of keyword event
//thisEvent.eobject is the object mapped to the currently highlighted row.
//actionBegin: action.odc.selectandset
SelectAndSetEventHandler(thisEvent, [["stock", findEObjectByVBL("#{pc_PortfolioJSF.root.placeHolderStock}")]]);//actionEnd: action.odc.selectandset

}</SCRIPT></HEAD>
<f:view>
<BODY>
		<hx:scriptCollector>
		<h:form id="PortfolioSubmit" enctype="multipart/form-data">

			<odc:clientData id="RootDataModel" value="#{pc_PortfolioJSF.root}"/>

			<odc:tabbedPanel id="upperTabbedPanel" width="1100" height="400"
				showBackNextButton="true" showTabs="true" onFinishSubmit="true"
				styleClass="tabbedPanel">
				<odc:bfPanel id="treePanel" name="Tree Panel"
					showFinishCancelButton="false">
					<TABLE border=0>
						<TR>
							<TD>&nbsp;&nbsp;</TD>
							<TD><odc:tree id="tree1" styleClass="tree">
								<odc:clientBinder value="#{pc_PortfolioJSF.root}"></odc:clientBinder>
								<odc:treeNodeAttr id="Root2"
									className="com.ibm.odcb.tutorial.businessobjects.Root"
									attributeName="name" referenceName="users"
									closeIcon="jsl/tree/icons/folder.gif"
									openIcon="jsl/tree/icons/folderopen.gif" />
								<odc:treeNodeAttr id="Users"
									className="com.ibm.odcb.tutorial.businessobjects.User"
									attributeName="lastName" referenceName="portfolios" />
								<odc:treeNodeAttr id="Portfolio"
									className="com.ibm.odcb.tutorial.businessobjects.Portfolio"
									attributeName="portfolioName" onhighlight="return func_1(this, event);"/>
							</odc:tree></TD>
						</TR>
					</TABLE>
				</odc:bfPanel>
				<odc:bfPanel id="datagridPanel" name="DataGrid Panel"
					showFinishCancelButton="true">
					<TABLE border=0>
						<TR>
							<TD>&nbsp;&nbsp;</TD>
							<TD><odc:dataGrid id="dataGrid1"
								allowRowAddAndDelete="false" pageSize="5"
								showSelectionColumn="false" navBarPosition="0" styleClass="dataGrid">
								<odc:clientBinder value="#{pc_PortfolioJSF.root.users[0].portfolios[0].positions}"></odc:clientBinder>
								<odc:dataGridCol id="grid1col1" attributeName="symbol"
									colHead="Symbol" readOnly="true" alignment="center" width="160" />
								<odc:dataGridCol id="grid1col2" attributeName="quantity"
									alignment="center" readOnly="false" width="40"
									colHead="Quantity" />
								<odc:dataGridCol id="grid1col3" attributeName="price"
									colHead="Purchase Price US$" readOnly="true" alignment="right"
									width="140" >
									<f:convertNumber pattern="0.00"/>
								</odc:dataGridCol>
								<odc:dataGridCol id="grid1col4" attributeName="value"
									colHead="Base Value US$" readOnly="true" alignment="right"
									width="140" >
									<f:convertNumber pattern="0.00"/>
								</odc:dataGridCol>
								<odc:dataGridCol id="grid1col5" attributeName="currentPrice"
									colHead="Current Price US$" readOnly="true" alignment="right"
									width="140" >
									<f:convertNumber type="number" pattern="0.00"/>
								</odc:dataGridCol>
								<odc:dataGridCol id="grid1col6" attributeName="currentValue"
									colHead="Current Value US$" readOnly="true" alignment="right"
									width="140" >
									<f:convertNumber pattern="0.00"/>
								</odc:dataGridCol>
								<odc:dataGridCol id="grid1col7" attributeName="gains"
									colHead="Gains/Losses US$" readOnly="true" alignment="right"
									width="140" >
									<f:convertNumber pattern="0.00"/>
								</odc:dataGridCol>
							</odc:dataGrid></TD>
						</TR>
					</TABLE>
				</odc:bfPanel>
				<odc:bfPanel id="fullPanel" name="Full Panel"
					showFinishCancelButton="true">
					<TABLE BORDER=0 ALIGN=CENTER WIDTH="100%">
						<TR>
							<TD VALIGN=TOP ALIGN=LEFT WIDTH="30%"><odc:tree id="tree2" styleClass="tree">
								<odc:clientBinder value="#{pc_PortfolioJSF.root}"></odc:clientBinder>
								<odc:treeNodeAttr id="Root22"
									className="com.ibm.odcb.tutorial.businessobjects.Root"
									attributeName="name" referenceName="users"
									closeIcon="jsl/tree/icons/folder.gif"
									openIcon="jsl/tree/icons/folderopen.gif" />
								<odc:treeNodeAttr id="Users2"
									className="com.ibm.odcb.tutorial.businessobjects.User"
									attributeName="lastName" referenceName="portfolios" />
								<odc:treeNodeAttr id="Portfolio2"
									className="com.ibm.odcb.tutorial.businessobjects.Portfolio"
									attributeName="portfolioName" onhighlight="return func_2(this, event);"/>
							</odc:tree></TD>
							<TD WIDTH="70%">
							<TABLE ALIGN=CENTER>
								<TR>
									<TD>
									<H2 id="HeaderLabel2"><h:outputText id="phUserName">
										<odc:clientBinder
											value="#{pc_PortfolioJSF.root.users[0].lastName}" />
									</h:outputText> 's <h:outputText id="phPortName">
										<odc:clientBinder
											value="#{pc_PortfolioJSF.root.users[0].portfolios[0].portfolioName}" />
									</h:outputText></H2>
									</TD>
								</TR>
							</TABLE>
							<BR />
							<SPAN id="PortfolioDataGrid2"></SPAN> <odc:dataGrid
								id="dataGrid2"
								allowRowAddAndDelete="false" pageSize="5"
								showSelectionColumn="true" selectionColName="Items"
								multiSelection="true" selectionColAlignment="center"
								selectionColWidth="100"
								navBarPosition="0" styleClass="dataGrid" onhighlight="return func_3(this, event);">
								<odc:clientBinder value="#{pc_PortfolioJSF.root.users[0].portfolios[0].positions}"></odc:clientBinder>
								<odc:dataGridCol id="grid2col1" attributeName="symbol"
									readOnly="true" alignment="center" width="160" colHead="Symbol" />
								<odc:dataGridCol id="grid2col2" attributeName="quantity"
									alignment="center" readOnly="false" width="40"
									colHead="Quantity" />
								<odc:dataGridCol id="grid2col3" attributeName="price"
									readOnly="true" colHead="Purchase Price US$" alignment="right"
									width="140" >
									<f:convertNumber pattern="0.00"/>
								</odc:dataGridCol>
								<odc:dataGridCol id="grid2col4" attributeName="value"
									readOnly="true" colHead="Base Value US$" alignment="right"
									width="140" >
									<f:convertNumber pattern="0.00"/>
								</odc:dataGridCol>
								<odc:dataGridCol id="grid2col5" attributeName="currentPrice"
									readOnly="true" colHead="Current Price US$" alignment="right"
									width="140" >
									<f:convertNumber pattern="0.00"/>
								</odc:dataGridCol>
								<odc:dataGridCol id="grid2col6" attributeName="currentValue"
									readOnly="true" colHead="Current Value US$" alignment="right"
									width="140" >
									<f:convertNumber pattern="0.00"/>
								</odc:dataGridCol>
								<odc:dataGridCol id="grid2col7" attributeName="gains"
									readOnly="true" colHead="Gains/Losses US$" alignment="right"
									width="140" >
									<f:convertNumber pattern="0.00"/>
								</odc:dataGridCol>
								<odc:dataGridCol id="grid2col8" attributeName="purchaseDate"
									readOnly="true" colHead="Purchase Date" alignment="right"
									width="140" >
									<f:convertDateTime />
								</odc:dataGridCol>
							</odc:dataGrid></TD>
						</TR>
					</TABLE>
				</odc:bfPanel>
				<f:facet name="back">
					<hx:commandExButton type="submit" value="&lt; Back"
						id="upperTabbedPanel_back" style="display:none"></hx:commandExButton>
				</f:facet>
				<f:facet name="next">
					<hx:commandExButton type="submit" value="Next &gt;"
						id="upperTabbedPanel_next" style="display:none"></hx:commandExButton>
				</f:facet>
				<f:facet name="finish">
					<hx:commandExButton type="submit" value="Finish"
						id="upperTabbedPanel_finish" style="display:none"
						action="#{pc_PortfolioJSF.doUpperTabbedPanel_finishAction}"></hx:commandExButton>
				</f:facet>
				<f:facet name="cancel">
					<hx:commandExButton type="submit" value="Cancel"
						id="upperTabbedPanel_cancel" style="display:none"></hx:commandExButton>
				</f:facet>
			</odc:tabbedPanel>
			<BR />
			<odc:webService id="webservice1"
				wsdlURL="http://localhost:9080/FacesClientTutorial/services/StockWebService/wsdl/StockWebService.wsdl"
				wsAlias="StockWebService" webServiceOperation="getQuote">
				<odc:wsInput id="wbinput1">
					<odc:clientBinder value="#{pc_PortfolioJSF.root.placeHolderStock.symbol}"></odc:clientBinder>
				</odc:wsInput>
				<odc:wsOutput id="wboutput1"
					responseElementName="lastPrice">
					<odc:clientBinder value="#{pc_PortfolioJSF.root.placeHolderStock.currentPrice}"></odc:clientBinder>
				</odc:wsOutput>
				<odc:wsOutput id="wbinput2"
					responseElementName="volume">
					<odc:clientBinder value="#{pc_PortfolioJSF.root.placeHolderStock.volume}"/>
				</odc:wsOutput>
				<odc:wsOutput id="wboutput2"
					responseElementName="change">
						<odc:clientBinder value="#{pc_PortfolioJSF.root.placeHolderStock.change}"/>
				</odc:wsOutput>
			</odc:webService>
			<odc:tabbedPanel id="lowerTabbedPanel" width="1100" height="500"
				showBackNextButton="false" showTabs="true" styleClass="tabbedPanel">
				<odc:bfPanel id="graphPanel" name="Graph Panel"
					showFinishCancelButton="true">
					<CENTER><odc:graphDraw height="400" width="800" title="Stock Chart"
						id="graphDraw1"
						splitYAxis="false" pie="true" bar="true" line="true"
						firstChart="Pie" xaxisTitle="Stocks" yaxisTitle="Parameters"
						yaxisDivisions="10" showLegend="true" showLabel="false">
						<odc:clientBinder value="#{pc_PortfolioJSF.root.users[0].portfolios[0].positions}"/>
						<odc:graphDrawData id="graphDrawData1" grouped="false">
							<odc:graphDrawDataSeries attributeName="price"
								groupOperation="None" seriesName="Price">
							</odc:graphDrawDataSeries>
							<odc:graphDrawDataSeries attributeName="quantity"
								groupOperation="None" seriesName="Quantity">
							</odc:graphDrawDataSeries>
						</odc:graphDrawData>
						<odc:graphDrawLabels id="graphDrawLabels1" attributeName="symbol">
						</odc:graphDrawLabels>
					</odc:graphDraw></CENTER>
				</odc:bfPanel>
				<odc:bfPanel id="webServicesPanel" name="WebServices Panel"
					showFinishCancelButton="true">
					<CENTER>
					<TABLE BORDER=0 CELLPADDING=4>
						<TR>
							<TD ALIGN=right>Stock Symbol :</TD>
							<TD><h:inputText id="sb1">
								<odc:clientBinder
									value="#{pc_PortfolioJSF.root.placeHolderStock.symbol}" />
							</h:inputText></TD>
							<TD><input type="button" name="Fetch Info" value="Fetch Info"
								onclick="executeWebService('webservice1');" /></TD>
						</TR>
						<TR>
							<TD COLSPAN=2 height="24">
							<HR>
							</TD>
						</TR>

						<TR>
							<TD ALIGN=right height="27">Symbol :</TD>
							<TD height="27"><h:outputText id="sym1">
								<odc:clientBinder
									value="#{pc_PortfolioJSF.root.placeHolderStock.symbol}" />
							</h:outputText></TD>
						</TR>
						<TR>
							<TD ALIGN=right>Market Price :</TD>
							<TD><h:outputText id="pr1">
								<odc:clientBinder
									value="#{pc_PortfolioJSF.root.placeHolderStock.currentPrice}" />
							</h:outputText></TD>
						</TR>
						<TR>
							<TD ALIGN=right>Change :</TD>
							<TD><h:outputText id="chg1">
								<odc:clientBinder
									value="#{pc_PortfolioJSF.root.placeHolderStock.change}" />
							</h:outputText> &nbsp;&nbsp;(&nbsp; <h:outputText id="pc1">
								<odc:clientBinder
									value="#{pc_PortfolioJSF.root.placeHolderStock.percentage}" />
							<f:convertNumber type="percent" pattern="0.00%"/></h:outputText> &nbsp;)</TD>
						</TR>

						<TR>
							<TD ALIGN=right>Volume :</TD>
							<TD><h:outputText id="vol1">
								<odc:clientBinder
									value="#{pc_PortfolioJSF.root.placeHolderStock.volume}" />
							</h:outputText></TD>
						</TR>
					</TABLE>
					</CENTER>
				</odc:bfPanel>
				<f:facet name="back">
					<hx:commandExButton type="submit" value="&lt; Back"
						id="lowerTabbedPanel_back" style="display:none"></hx:commandExButton>
				</f:facet>
				<f:facet name="next">
					<hx:commandExButton type="submit" value="Next &gt;"
						id="lowerTabbedPanel_next" style="display:none"></hx:commandExButton>
				</f:facet>
				<f:facet name="finish">
					<hx:commandExButton type="submit" value="Finish"
						id="lowerTabbedPanel_finish" style="display:none"></hx:commandExButton>
				</f:facet>
				<f:facet name="cancel">
					<hx:commandExButton type="submit" value="Cancel"
						id="lowerTabbedPanel_cancel" style="display:none"></hx:commandExButton>
				</f:facet>
			</odc:tabbedPanel>
		</h:form>
	</hx:scriptCollector>
		
	</BODY>

</f:view>
</HTML>
