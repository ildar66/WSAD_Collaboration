<!--**************************************************************-->
<!--                                                              -->
<!-- This code is generated automatically by IBM WebSphere Studio -->
<!--                                                              -->
<!--**************************************************************-->
<html>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<HEAD>
<%@ page 
	language="java"
	contentType="text/html"
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link type="text/css" rel="stylesheet" href="theme/WhitePages.css"/>
</head>

<body>
	<h1>Details</h1>
<html:form action="/DetailsAction" target="_top">
	<table border="0">
		<tr>

		<td>
			<label for="last_name">
				<B><bean:message key="WhitePages.Details.Details.last_name"/></B> 
			</label>
		</td>
			<td>
				<bean:write name="DetailsForm" property="last_name" /><BR>
			</td>
  </tr><tr>
		<td>
			<label for="location">
				<B><bean:message key="WhitePages.Details.Details.city"/></B> 
			</label>
		</td>
			<td>
				<bean:write name="DetailsForm" property="location" /><BR>
			</td>
  </tr><tr>
		<td>
			<label for="category">
				<B><bean:message key="WhitePages.Details.Details.email_address"/></B> 
			</label>
		</td>
			<td>
				<bean:write name="DetailsForm" property="category" /><BR>
			</td>
  </tr><tr>
			<td>
				<label for="bill">
					<B><bean:message key="WhitePages.Details.Details.Bill"/></B>
				</label>
			</td>
			<td>
				<html:text property="bill" styleId="bill"/><BR>
			</td>
  </tr><tr>
			<td>
				<label for="comments">
					<B><bean:message key="WhitePages.Details.Details.Comments"/></B>
				</label>
			</td>
			<td>
				<html:text property="comments" styleId="comments"/><BR>
			</td>
		</tr>
	</table>
	<html:hidden property="_$action"/>
</html:form>
<html:errors/>

</body>
</html>
