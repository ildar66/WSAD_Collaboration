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
	<h1>Search</h1>
<html:form action="/SearchCriteriaAction" target="_top">
	<table border="0">
		<tr>

			<td>
				<label for="last_name">
					<B><bean:message key="WhitePages.Search.SearchCriteria.last_name"/></B>
				</label>
			</td>
			<% java.util.Vector formListlast_name = (java.util.Vector)request.getSession().getAttribute("PeopleSearchCriteriaList");
			java.util.Hashtable valueslast_name = new java.util.Hashtable();
			for (int i = 0; i < formListlast_name.size(); i++)
			{
				WhitePages1.PeopleObject obj = (WhitePages1.PeopleObject)formListlast_name.get(i);
				if (obj.getLast_name() != null)
					valueslast_name.put(obj.getLast_name(), obj);
			}
			java.util.Enumeration leftlast_name = valueslast_name.elements();
			java.util.Vector alast_name = new java.util.Vector();
			while (leftlast_name.hasMoreElements())
			{
				alast_name.addElement(leftlast_name.nextElement());
			}
         for (int j = 0; j < alast_name.size() - 1; j++) {
            for (int i = 0; i < alast_name.size() - 1; i++) {
				 WhitePages1.PeopleObject first = (WhitePages1.PeopleObject)alast_name.elementAt(i);
				 WhitePages1.PeopleObject second = (WhitePages1.PeopleObject)alast_name.elementAt(i + 1);
                if (first.getLast_name().compareTo(second.getLast_name()) > 0) {
                    alast_name.setElementAt(first, i + 1);
                    alast_name.setElementAt(second, i);
                }
            }
         }
			request.setAttribute("last_nameList", alast_name); %>
			<td>
				<html:select property="last_name" styleId="last_name">
					<html:options collection="last_nameList" property="last_name" labelProperty="last_name"/>
				</html:select>
			</td>

			<td>
				<html:submit onclick="document.SearchCriteriaForm._$action.value='Submit0'" >
					<bean:message key="WhitePages.Search.SearchCriteria.Submit0"/>
				</html:submit>
			</td>
  </tr><tr>
			<td>
				<label for="location">
					<B><bean:message key="WhitePages.Search.SearchCriteria.city"/></B>
				</label>
			</td>
			<% java.util.Vector formListcity = (java.util.Vector)request.getSession().getAttribute("PeopleSearchCriteriaList");
			java.util.Hashtable valuescity = new java.util.Hashtable();
			for (int i = 0; i < formListcity.size(); i++)
			{
				WhitePages1.PeopleObject obj = (WhitePages1.PeopleObject)formListcity.get(i);
				if (obj.getLocation() != null)
					valuescity.put(obj.getLocation(), obj);
			}
			java.util.Enumeration leftcity = valuescity.elements();
			java.util.Vector acity = new java.util.Vector();
			while (leftcity.hasMoreElements())
			{
				acity.addElement(leftcity.nextElement());
			}
         for (int j = 0; j < acity.size() - 1; j++) {
            for (int i = 0; i < acity.size() - 1; i++) {
				 WhitePages1.PeopleObject first = (WhitePages1.PeopleObject)acity.elementAt(i);
				 WhitePages1.PeopleObject second = (WhitePages1.PeopleObject)acity.elementAt(i + 1);
                if (first.getLocation().compareTo(second.getLocation()) > 0) {
                    acity.setElementAt(first, i + 1);
                    acity.setElementAt(second, i);
                }
            }
         }
			request.setAttribute("locationList", acity); %>
			<td>
				<html:select property="location" styleId="location">
					<html:options collection="locationList" property="location" labelProperty="location"/>
				</html:select>
			</td>

			<td>
				<html:submit onclick="document.SearchCriteriaForm._$action.value='Submit1'" >
					<bean:message key="WhitePages.Search.SearchCriteria.Submit1"/>
				</html:submit>
			</td>
  </tr><tr>
			<td>
				<label for="category">
					<B><bean:message key="WhitePages.Search.SearchCriteria.email_address"/></B>
				</label>
			</td>
			<% java.util.Vector formListemail_address = (java.util.Vector)request.getSession().getAttribute("PeopleSearchCriteriaList");
			java.util.Hashtable valuesemail_address = new java.util.Hashtable();
			for (int i = 0; i < formListemail_address.size(); i++)
			{
				WhitePages1.PeopleObject obj = (WhitePages1.PeopleObject)formListemail_address.get(i);
				if (obj.getCategory() != null)
					valuesemail_address.put(obj.getCategory(), obj);
			}
			java.util.Enumeration leftemail_address = valuesemail_address.elements();
			java.util.Vector aemail_address = new java.util.Vector();
			while (leftemail_address.hasMoreElements())
			{
				aemail_address.addElement(leftemail_address.nextElement());
			}
         for (int j = 0; j < aemail_address.size() - 1; j++) {
            for (int i = 0; i < aemail_address.size() - 1; i++) {
				 WhitePages1.PeopleObject first = (WhitePages1.PeopleObject)aemail_address.elementAt(i);
				 WhitePages1.PeopleObject second = (WhitePages1.PeopleObject)aemail_address.elementAt(i + 1);
                if (first.getCategory().compareTo(second.getCategory()) > 0) {
                    aemail_address.setElementAt(first, i + 1);
                    aemail_address.setElementAt(second, i);
                }
            }
         }
			request.setAttribute("categoryList", aemail_address); %>
			<td>
				<html:select property="category" styleId="category">
					<html:options collection="categoryList" property="category" labelProperty="category"/>
				</html:select>
			</td>

			<td>
				<html:submit onclick="document.SearchCriteriaForm._$action.value='Submit'" >
					<bean:message key="WhitePages.Search.SearchCriteria.Submit"/>
				</html:submit>
			</td>
  </tr><tr>
		<td>
			<label for="dummy">
				<B><bean:message key="WhitePages.Search.SearchCriteria.dummy"/></B> 
			</label>
		</td>
			<td>
				<bean:write name="SearchCriteriaForm" property="dummy" /><BR>
			</td>
		</tr>
	</table>
	<html:hidden property="_$action"/>
</html:form>
<html:form action="/SearchContentAction" target="_top">
	<%! private int SearchContentFormTableIndex = 0;
		private String writeSearchContentFormRowIndex() {Integer ret = new Integer(SearchContentFormTableIndex++); return ("id=\"rowId"+ ret.toString() + "\" onclick=\"selectSearchContentFormRow(" + ret.toString() + ")\"");}
		private String initSearchContentFormTableIndex() {SearchContentFormTableIndex = 0; return("");}
	%>
	<table border="1" width="100%" frame="hsides" rules="rows">
	<%=initSearchContentFormTableIndex()%>
	<tr>
			<th  align="left">
  			<bean:message key="WhitePages.Search.SearchContent.last_name"/>
			</th>
			<th  align="left">
  			<bean:message key="WhitePages.Search.SearchContent.city"/>
			</th>
			<th  align="left">
  			<bean:message key="WhitePages.Search.SearchContent.email_address"/>
			</th>
			<th  width="1%">
			</th>
			<th  align="left">
  			<bean:message key="WhitePages.Search.SearchContent.ClientId"/>
			</th>
			<th  align="left">
  			<bean:message key="WhitePages.Search.SearchContent.Comments"/>
			</th>
			<th  align="left">
  			<bean:message key="WhitePages.Search.SearchContent.Bill"/>
			</th>
	</tr>
		<logic:iterate id="SearchContentAction" name="PeopleSearchContentList">
			<tr <%=writeSearchContentFormRowIndex()%>>

			<th align="left">
				<bean:write name="SearchContentAction" property="last_name" filter="true"/>
			</th>
			<td align="left">
				<bean:write name="SearchContentAction" property="location" filter="true"/>
			</td>
			<td align="left">
				<bean:write name="SearchContentAction" property="category" filter="true"/>
			</td>
			<td align="center">
				<html:submit onclick="document.SearchContentForm._$action.value='SearchContentSubmit'" >
					<bean:message key="WhitePages.Search.SearchContent.Submit"/>
				</html:submit>
			</td>
			<td align="left">
				<bean:write name="SearchContentAction" property="client_idString" filter="true"/>
			</td>
			<td align="left">
				<bean:write name="SearchContentAction" property="comments" filter="true"/>
			</td>
			<td align="left">
				<bean:write name="SearchContentAction" property="bill" filter="true"/>
			</td>
			
			</tr>
		</logic:iterate>
	</table>
	<table border="0">
		<tr>
		</tr>
	</table>
	<html:hidden property="selectedIndex"/>
	<html:hidden property="_$action"/>
</html:form>
<SCRIPT LANGUAGE="JavaScript">
lastSelectedRow = null;
function selectSearchContentFormRow(rowId)
{
	document.SearchContentForm.selectedIndex.value = rowId;
}
</SCRIPT>
<html:errors/>

</body>
</html>
