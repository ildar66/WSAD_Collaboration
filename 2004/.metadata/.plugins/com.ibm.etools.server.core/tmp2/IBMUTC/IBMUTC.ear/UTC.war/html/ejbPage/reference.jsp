<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<!--
 Licensed Material - Property of IBM 
 (C) Copyright IBM Corp. 2001 - All Rights Reserved. 
 US Government Users Restricted Rights - Use, duplication or disclosure 
 restricted by GSA ADP Schedule Contract with IBM Corp. 
-->

<%@ page errorPage="/error.jsp" import="com.ibm.etools.utc.*,com.ibm.etools.utc.model.ClassModel" contentType="text/html; charset=utf-8" %>
<jsp:useBean id="referenceView" scope="session" class="com.ibm.etools.utc.view.ReferenceView"/>
<%@ taglib uri="/utc" prefix="utc" %>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta http-equiv="Content-Style-Type" content="text/css">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <link rel=stylesheet type="text/css" href="/UTC/css/dynamic.css">
  <title><utc:resource key="ejbPageReferenceViewTitle"/></title>
</head>

<body class="page">

<% referenceView.cacheTree(request); %>
<%= Tree.getTableTree(request, "_parent", "/html/ejbPage/referencePage.jsp", referenceView.getTreeRoot(), false) %>

<% if (referenceView.getAddedObjects() != null) { %>
<br>
    <img src="/UTC/images/spacer.gif" width=8 height=5><utc:resource key="ejbPageReferenceViewLastAdded"/>
    <% ClassModel[] models = referenceView.getAddedObjects();
       int size = models.length;
       boolean dots = false;
       if (size > 10) {
         size = 10;
         dots = true;
       }
       for (int i = 0; i < size; i++) { %>
       <br><img src="/UTC/images/spacer.gif" width=16 height=5><%= JspUtil.convertStringToHTML(models[i].getName()) %>
    <% } %>
    <br><% if (dots) { %><img src="/UTC/images/spacer.gif" width=16 height=5>...<% } %>
  <% } %>

</body>
</html>