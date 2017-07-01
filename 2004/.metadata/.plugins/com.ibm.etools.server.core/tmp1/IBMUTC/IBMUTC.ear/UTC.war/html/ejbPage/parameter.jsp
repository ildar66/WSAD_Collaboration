<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<!--
 Licensed Material - Property of IBM 
 (C) Copyright IBM Corp. 2001 - All Rights Reserved. 
 US Government Users Restricted Rights - Use, duplication or disclosure 
 restricted by GSA ADP Schedule Contract with IBM Corp. 
-->

<%@ page errorPage="/error.jsp" import="java.util.List,com.ibm.etools.utc.Tree,com.ibm.etools.utc.Resource,com.ibm.etools.utc.view.*,com.ibm.etools.utc.model.*" contentType="text/html; charset=utf-8" %>
<jsp:useBean id="parameterView" scope="session" class="com.ibm.etools.utc.view.ParameterView"/>
<jsp:useBean id="modelManager" scope="session" class="com.ibm.etools.utc.ClassModelManager"/>
<jsp:useBean id="resultView" scope="session" class="com.ibm.etools.utc.view.ResultView"/>
<%@ taglib uri="/utc" prefix="utc" %>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta http-equiv="Content-Style-Type" content="text/css">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <link rel=stylesheet type="text/css" href="/UTC/css/dynamic.css">
  <title><utc:resource key="ejbPageParameterViewTitle"/></title>
</head>

<% parameterView.loadManagerObjects(modelManager);
  parameterView.loadManagerClasses(modelManager); %>

<% if (parameterView.isLoadClassView()) { %>
<!-- *************** Load class *************** -->
<body class="margin">
<form name="newClassForm" action="/UTC/toolbox" method="post" target="formParameters" enctype="multipart/form-data">
<input type="hidden" name="random" value="<%= Math.random() %>"/>
<input type="hidden" name="utility" value="2"/>
<table cellpadding=3 cellspacing=0 border=0 bgcolor=white width=100%>
<tr>
  <td colspan="3"><utc:resource key="infoToolboxLoadClass"/></td>
</tr>
<tr>
  <td colspan="3" valign="middle" height="15"><img src="/UTC/images/line.gif" width="100%" height="2"></td>
</tr>
<tr>
  <td width=20%><label for="text"><utc:resource key="ejbPageParameterViewLoadClass"/></label></td>
  <td width=*><input type="text" id="text" class="textenter" name="class" size=25/></td>
  <td width=15%><input type=submit value="<utc:resource key="ejbPageParameterViewLoad"/>" class="button"/></td>
</tr>

</table>
</form>

<% } else if (parameterView.isClassCastView()) { %>
<!-- *************** Class Cast *************** -->
<body class="margin">
<form name="newClassForm" action="/UTC/toolbox" method="post" target="formParameters" enctype="multipart/form-data">
<input type="hidden" name="random" value="<%= Math.random() %>"/>
<input type="hidden" name="utility" value="0"/>
<table cellpadding=3 cellspacing=0 border=0 bgcolor=white width=100%>
<tr>
  <td colspan="3"><utc:resource key="infoToolboxCastClass"/></td>
</tr>
<tr>
  <td colspan="3" valign="middle" height="15"><img src="/UTC/images/line.gif" width="100%" height="2"></td>
</tr>
<tr>
  <td width=20%><label for="object"><utc:resource key="ejbPageParameterViewObject"/></label></td>
  <td>
<select name="object" class="selectlist" id="object">
<% List castObjects = parameterView.getManagerObjects();
  int size = castObjects.size();
  for (int j = 0; j < size; j++) {
  ObjectModel model = (ObjectModel) castObjects.get(j); %>
  <option value="<%= j %>"><%= model.getName() %></option>
<% } %>
</select>
  </td>
  <td></td>
</tr>
<tr>
  <td><label for="class"><utc:resource key="ejbPageParameterViewCastType"/></label></td>
  <td>

<select name="class" class="selectlist" id="class">
<% List castClasses = parameterView.getManagerClasses();
  size = castClasses.size();
  for (int j = 0; j < size; j++) {
  ClassModel model = (ClassModel) castClasses.get(j); %>
  <option value="<%= j %>"><%= model.getName() %></option>
  <% } %>
</select>
  </td>
  <td width=15%><input type=submit value="<utc:resource key="ejbPageParameterViewCast"/>" class="button"/></td>
</tr>

</table>
</form>

<% } else if (parameterView.isArrayView()) { %>
<!-- *************** Array Creation *************** -->
<body class="margin">
<form name="createArrayForm" action="/UTC/toolbox" method="post" target="formParameters" enctype="multipart/form-data">
<input type="hidden" name="random" value="<%= Math.random() %>"/>
<input type="hidden" name="utility" value="1"/>
<table cellpadding=3 cellspacing=0 border=0 bgcolor=white width=100%>
<tr>
  <td colspan="3"><utc:resource key="infoToolboxCreateArray"/></td>
</tr>
<tr>
  <td colspan="3" valign="middle" height="15"><img src="/UTC/images/line.gif" width="100%" height="2"></td>
</tr>
<tr>
  <td width=20%><label for="class"><utc:resource key="ejbPageParameterViewArrayType"/></label></td>
  <td>
<select name="class" class="selectlist" id="class">
<% List castClasses = parameterView.getManagerClasses();
  int size = castClasses.size();
  for (int j = 0; j < size; j++) {
  ClassModel model = (ClassModel) castClasses.get(j); %>
  <option value="<%= j %>"><%= model.getName() %></option>
  <% } %>
</select>

  </td>
  <td width=15%></td>
</tr>
<tr>
  <td><label for="size"><utc:resource key="ejbPageParameterViewArraySize"/></label></td>
  <td><input type="text" id="size" class="textenter" name="size" value="1" size=5/></td>
  <td><input type=submit value="<utc:resource key="ejbPageParameterViewArrayCreate"/>" class="button"/></td>
</tr>

</table>
</form>

<% } else if (parameterView.isCollectionToArrayView()) { %>
<!-- *************** Collection To Array *************** -->
<% parameterView.loadManagerCollections(modelManager); %>
<body class="margin">
<form name="collectionToArrayForm" action="/UTC/toolbox" method="post" target="formParameters" enctype="multipart/form-data">
<input type="hidden" name="random" value="<%= Math.random() %>"/>
<input type="hidden" name="utility" value="3"/>
<table cellpadding=3 cellspacing=0 border=0 bgcolor=white width=100%>
<tr>
  <td colspan="2">Collection -&gt; Object[]</td>
</tr>
<tr>
  <td colspan="3" valign="middle" height="15"><img src="/UTC/images/line.gif" width="100%" height="2"></td>
</tr>
<tr>
  <td width=20%><label for="collection"><utc:resource key="ejbPageParameterViewCollection"/></label></td>
  <td>
<select name="collection" class="selectlist" id="collection">
<% List collections = parameterView.getManagerCollections();
  int size = collections.size();
  for (int j = 0; j < size; j++) {
  ClassModel model = (ClassModel) collections.get(j); %>
  <option value="<%= j %>"><%= model.getName() %></option>
  <% } %>
</select>
  </td>
  <td width=15%><input type=submit value="<utc:resource key="ejbPageParameterViewConvert"/>" class="button"/></td>
</tr>

</table>
</form>

<% } else if (parameterView.isArrayToListView()) { %>
<!-- *************** Array To List *************** -->
<% parameterView.loadManagerArrays(modelManager); %>
<body class="margin">
<form name="arrayToListForm" action="/UTC/toolbox" method="post" target="formParameters" enctype="multipart/form-data">
<input type="hidden" name="random" value="<%= Math.random() %>"/>
<input type="hidden" name="utility" value="4"/>
<table cellpadding=3 cellspacing=0 border=0 bgcolor=white width=100%>
<tr>
  <td colspan="3">Object[] -&gt; List</td>
</tr>
<tr>
  <td colspan="3" valign="middle" height="15"><img src="/UTC/images/line.gif" width="100%" height="2"></td>
</tr>
<tr>
  <td width=20%><label for="array"><utc:resource key="ejbPageParameterViewArray"/></label></td>
  <td>
<select name="array" class="selectlist" id="array">
<% List arrays = parameterView.getManagerArrays();
  int size = arrays.size();
  for (int j = 0; j < size; j++) {
  ClassModel model = (ClassModel) arrays.get(j); %>
  <option value="<%= j %>"><%= model.getName() %></option>
  <% } %>
</select>
  </td>
  <td width=15%><input type=submit value="<utc:resource key="ejbPageParameterViewConvert"/>" class="button"/></td>
</tr>

</table>
</form>

<% } else if (parameterView.isSetArrayElementView()) { %>
<!-- *************** Set Array Element *************** -->
<% parameterView.loadManagerArrays(modelManager); %>
<% parameterView.loadManagerObjects(modelManager); %>
<body class="margin">
<form name="arrayElementForm" action="/UTC/toolbox" method="post" target="formParameters" enctype="multipart/form-data">
<input type="hidden" name="random" value="<%= Math.random() %>"/>
<input type="hidden" name="utility" value="5"/>
<table cellpadding=3 cellspacing=0 border=0 bgcolor=white width=100%>
<tr>
  <td colspan="3"><utc:resource key="infoToolboxSetArrayElement"/></td>
</tr>
<tr>
  <td colspan="3" valign="middle" height="15"><img src="/UTC/images/line.gif" width="100%" height="2"></td>
</tr>
<tr>
  <td width=20%><label for="array"><utc:resource key="ejbPageParameterViewArray"/></label></td>
  <td>
<select name="array" class="selectlist" id="array">
<% List arrays = parameterView.getManagerArrays();
  int size = arrays.size();
  for (int j = 0; j < size; j++) {
  ClassModel model = (ClassModel) arrays.get(j); %>
  <option value="<%= j %>"><%= model.getName() %></option>
  <% } %>
</select>
  </td>
  <td width=15%></td>
</tr>
<tr>
  <td><label for="element"><utc:resource key="ejbPageParameterViewElement"/></label></td>
  <td><input type="text" class="textenter" id="element" name="element" value="0" size=5/></td>
  <td></td>
</tr>
<tr>
  <td><label for="object"><utc:resource key="ejbPageParameterViewObject"/></label></td>
  <td>
<select name="object" class="selectlist" id="object">
<% List objects = parameterView.getManagerObjects();
  size = objects.size();
  for (int j = 0; j < size; j++) {
  ClassModel model = (ClassModel) objects.get(j); %>
  <option value="<%= j %>"><%= model.getName() %></option>
  <% } %>
</select>
  </td>
  <td><input type=submit value="<utc:resource key="ejbPageParameterViewSet"/>" class="button"/></td>
</tr>

</table>
</form>

<% } else if (parameterView.isInspectorView()) { %>
<!-- *************** Field Inspector *************** -->
<% parameterView.cacheInspectorTree(modelManager); %>
<body class="margin">
<%= Tree.getTableTree(request, "_self", "/html/ejbPage/parameter.jsp", parameterView.getInspectorTreeRoot(), true) %>

<% } else if (parameterView.isJNDIObjectView()) { %>
<!-- *************** JNDI Object view *************** -->
<body class="margin">
<% if (parameterView.isTransactionCurrent()) { %>

<% TransactionObject transaction = (TransactionObject) parameterView.getCurrent(); %>

<br>
<b><utc:resource key="jndiObjectsPageTransactionStatus"/></b> <%= transaction.getStatus() %>
<p>

<table cellpadding=3 cellspacing=0 border=0>
<tr>
<% if (transaction.showBeginAction()) { %>
<form action="/UTC/userTransaction" method="get">
  <td><input type=hidden name="action" value="begin">
  <input type=submit value="<utc:resource key="jndiObjectsPageTransactionBegin"/>" id="begin" class="button"></td>
</form>

<% } %>

<% if (transaction.showStateActions()) { %>
<form action="/UTC/userTransaction" method="get">
  <td><input type=hidden name="action" value="commit">
  <input type=submit value="<utc:resource key="jndiObjectsPageTransactionCommit"/>" id="commit" class="button"></td>
</form>
<p>
<form action="/UTC/userTransaction" method="get">
  <td><input type=hidden name="action" value="rollback">
  <input type=submit value="<utc:resource key="jndiObjectsPageTransactionRollback"/>" id="rollback" class="button"></td>
</form>
<p>
<form action="/UTC/userTransaction" method="get">
  <td><input type=hidden name="action" value="rollbackOnly">
  <input type=submit value="<utc:resource key="jndiObjectsPageTransactionRollbackOnly"/>" id="rollbackonly" class="button"></td>
</form>

<% } %>
</tr>
</table>

<% } else if (parameterView.isDataSourceCurrent()) { %>

<% DataSourceObject dataSource = (DataSourceObject) parameterView.getCurrent(); %>

<div class="datasource">
<table cellpadding=3 cellspacing=0 border=0>
<tr>
  <td><utc:resource key="jndiObjectsPageDataSourceDriver"/></td>
  <td><%= dataSource.getDatabaseProductName() %>, <%= dataSource.getDatabaseProductVersion() %></td>
</tr>
<tr class="alternating">
  <td><utc:resource key="jndiObjectsPageDataSourceDriver"/></td>
  <td><%= dataSource.getDriverName() %>, <%= dataSource.getDriverVersion() %></td>
</tr>
<tr>
  <td><utc:resource key="jndiObjectsPageDataSourceUser"/></td>
  <td><%= dataSource.getUserName() %></td>
</tr>
<tr class="alternating">
  <td><utc:resource key="jndiObjectsPageDataSourceMaxConnections"/></td>
  <td><%= dataSource.getMaxConnections() %></td>
</tr>
<tr>
  <td><utc:resource key="jndiObjectsPageDataSourceURL"/></td>
  <td><%= dataSource.getURL() %></td>
</tr>
</table>
</div>

<% } else { %>
  <utc:resource key="jndiObjectsPageInvalid"/>
<% } %>

<% } else if (parameterView.isHierarchyView()) { %>

<!-- *************** Hierarchy/Method Visibility *************** -->
<body class="margin">
<utc:resource key="ejbPageParameterVisibility"/> <%= parameterView.getHierarchyClassFullName() %><br>

<form action="/UTC/setHierarchy" target="formReferences" method="post" enctype="multipart/form-data">
<input type="hidden" name="random" value="<%= Math.random() %>"/>

<div class="hierarchy">
<table cellpadding=3 cellspacing=0 border=0 width=100%>

<tr>
  <td colspan="2"><utc:resource key="ejbPageParameterVisibilitySuperclasses"/></td>
</tr>
<tr>
  <td colspan="2" valign="middle" height="15"><img src="/UTC/images/line.gif" width="100%" height="2"></td>
</tr>
<%= parameterView.getHierarchySuperclassForm() %>

<tr><td height=20></td></tr>

<tr>
  <td colspan="2"><utc:resource key="ejbPageParameterVisibilitySuperinterfaces"/></td>
</tr>
<tr>
  <td colspan="2" valign="middle" height="15"><img src="/UTC/images/line.gif" width="100%" height="2"></td>
</tr>
<%= parameterView.getHierarchyInterfaceForm() %>

</table>
</div>
<br>

<input type=submit value="<utc:resource key="ejbPageParameterVisibilitySet"/>" class="button"/>

</form>

<% } else { %>
<!-- *************** Constructor/Method Invocation *************** -->
<body class="margin">
<div class="parameterPage">
<% if (parameterView.isValid()) { 
String form = parameterView.getHTMLForm(modelManager);
%>
<%=form%>

<% } %>

<% } %>

 <!-- *************** Results Section *************** -->

<a name="result"/>
<table cellpadding=1 cellspacing=0 width=100% border=0>
  <tr><td height=30></td></tr>
  <tr><td>
  <% if (resultView.isExpanded()) { %>
    <a href="/UTC/results?action=close&random=<%= Math.random() %>"><img src="/UTC/images/twist_open.gif" width="11" height="10" border=0></a>
  <% } else { %>
    <a href="/UTC/results?action=open&random=<%= Math.random() %>"><img src="/UTC/images/twist_close.gif" width="11" height="10" border=0></a>
  <% } %>
  <% if (!resultView.showSource()) { %>
    <utc:resource key="ejbPageParameterViewResults"/></td></tr>
  <% } else { %>
    <utc:resource key="ejbPageParameterViewSource"/> <img src="<%= resultView.getSourceImage() %>" width=16 height=16/><%= resultView.getSource() %>
  <% } %>
  
  <tr>
    <td colspan="3" valign="middle" height="15"><img src="/UTC/images/line.gif" width="100%" height="2"></td>
  </tr>
  
  <% if (resultView.isExpanded()) { %>

  <tr>
  <td colspan="3">

<!-- Result Page --->
<% if (resultView.isValid()) { %>

  <% if (resultView.isErrorResult()) { %>
    <font color=red><%= resultView.getError() %></font>
  <% } else if (resultView.isVoidResult()) { %>
    <utc:resource key="ejbPageResultViewVoid"/>
  <% } else if (resultView.isNullResult()) { %>
    <utc:resource key="ejbPageResultViewNull"/>
  <% } else if (resultView.isExceptionResult()) { %>
    <img src="/UTC/images/error.gif" width=16 height=16 align="top"/>
    <%= resultView.getExceptionMessage() %>
  <% } else { %>
    <% if (resultView.getModel() instanceof ObjectModel) { %>
      <img src="/UTC/images/object.gif" width=16 height=16 align="top"/>&nbsp;<%= resultView.getObjectValue() %>&nbsp;(<%= resultView.getModelFullName() %>)
    <% } else { %>
      <img src="/UTC/images/class.gif" width=16 height=16 align="top"/>&nbsp;<%= resultView.getModelFullName() %>
    <% } %>
  <% } %>

  <% if (resultView.isExceptionResult() || resultView.isObjectResult()) { %>
    <% if (modelManager.contains(resultView.getModel())) { %>
      <utc:resource key="ejbPageResultViewInUse"/>
    <% } else { %>
      <form name="work" action="/UTC/addObject" method="get" target="formReferences">
      <input type="hidden" name="random" value="<%= Math.random() %>"/>
      <input type=submit value="<utc:resource key="ejbPageResultViewUse"/>" class="button"/>
      </form>
    <% } %>
    
    <% if (resultView.isCollection()) { %>
      <form name="workAll" action="/UTC/addAllObjects" method="get" target="formReferences">
      <input type="hidden" name="random" value="<%= Math.random() %>"/>
      <input type=submit value="<utc:resource key="ejbPageResultViewUseAll"/>" class="longbutton"/>
      </form>
    <% } %>
  <% } %>

  <% if (resultView.isExceptionResult()) { %>
    <pre class="exception"><% resultView.printException(out); %></pre>
  <% } %>

<% } else { %>
  <utc:resource key="ejbPageResultViewInvalid"/>
<% } %>

  </td>
 </tr>
 
<% } %>
</table>

</body>
</html>