<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<!--
 Licensed Material - Property of IBM 
 (C) Copyright IBM Corp. 2001 - All Rights Reserved. 
 US Government Users Restricted Rights - Use, duplication or disclosure 
 restricted by GSA ADP Schedule Contract with IBM Corp. 
-->

<html>

<%@ page errorPage="/error.jsp" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/utc" prefix="utc" %>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta http-equiv="Content-Style-Type" content="text/css"/>
  <meta http-equiv="Pragma" content="no-cache"/>
  <meta http-equiv="Cache-Control" content="no-cache"/>
  <meta http-equiv="expires" content="0"/>
  <link rel="shortcut icon" href="/UTC/images/testClient.ico"/>
  <title><utc:resource key="utcTitle"/></title>
</head>

<% String nextPage = request.getParameter("nextPage");
  if (nextPage == null || nextPage.length() == 0) {
    nextPage = "/UTC/html/homePage/homeFrame.jsp";
  } %>

<!-- frames -->
<frameset rows="25,*" border="0" frameborder="0" frameborder="no" framespacing="0">
  <frame name="globalnav" src="/UTC/html/toolbar/global_nav.jsp" marginwidth="4" marginheight="2" scrolling="no" frameborder="0" noresize>
  <frame name="main" src="<%= nextPage %>" marginwidth="1" marginheight="0" scrolling="no" frameborder="0">
</frameset>
</html>