<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%-- jsf:codeBehind language="java" location="/JavaSource/pagecode/Response.java" --%><%-- /jsf:codeBehind --%>
<HTML>
<HEAD>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://www.ibm.com/jsf/html_extended" prefix="hx"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
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
<TITLE>response.jsp</TITLE>
</HEAD>
<f:view>
	<BODY><hx:scriptCollector id="scriptCollector1">
	<h:form styleClass="form" id="form1"><P>Click   the back button to see the state of the portfolio page restored.<BR>
	<BR>
			<hx:commandExButton type="submit" value="Back"
				styleClass="commandExButton" id="button1" action="success"></hx:commandExButton>
	</P></h:form>
	</hx:scriptCollector></BODY>
</f:view>
</HTML>
