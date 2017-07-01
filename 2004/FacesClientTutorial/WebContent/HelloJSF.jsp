<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:codeBehind language="java" location="/JavaSource/pagecode/HelloJSF.java" --%><%-- /jsf:codeBehind --%>
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
<LINK href="theme/Master.css" rel="stylesheet"
	type="text/css">
<TITLE>HelloJSF.jsp</TITLE>
</HEAD>
<f:view>
	<BODY>
	<hx:scriptCollector>
		<odc:clientData id="dm1" value="#{pc_HelloJSF.hello}" />
		<H1 align="center">Faces  Client Hello World Sample</H1>
		<BR />
		<BR />
		<CENTER><h:form id="myForm" enctype="multipart/form-data">
			<h:outputLabel for="input1">
				<h:outputText value="Model Data Binder1 : " />
			</h:outputLabel>
			<h:inputText id="input1">
				<odc:clientBinder value="#{pc_HelloJSF.hello.text}" />
			</h:inputText>
			<BR />
			<HR />
			<BR />
			<h:outputLabel for="input2">
				<h:outputText value="Model Data Binder2 : " />
			</h:outputLabel>
			<h:inputText id="input2">
				<odc:clientBinder value="#{pc_HelloJSF.hello.text}" />
			</h:inputText>
		</h:form></CENTER>
	</hx:scriptCollector>
	</BODY>
</f:view>
</HTML>
