<%-- IBM Confidential OCO Source Material --%>
<%-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 1997, 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" import="com.ibm.ws.console.appmanagement.form.*"%>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute name="actionForm" classname="java.lang.String" />

<bean:define id="globalForm" name="globalForm" scope="session" type="GlobalForm"/>
<bean:define id="map" name="globalForm" property="resRefTypeMap" scope="page" type="java.util.HashMap"/>

<table border="0" cellpadding="3" cellspacing="1" width="100%" summary="List table">
  <tr valign="baseline" >
      <td class="wizard-step-text">
          <bean:write name="<%=actionForm%>" property="goalString" />
      </td>
  </tr>
</table>

<%
    String prevRefType = "startType";

    AppInstallForm thisForm = (AppInstallForm) session.getAttribute(actionForm);
    String[] column2 = thisForm.getColumn2();
    String[] column3 = thisForm.getColumn3();
    String[] column4 = thisForm.getColumn4();
    String[] column5 = thisForm.getColumn5();
    String[] column6 = thisForm.getColumn6();
    String[] column8 = thisForm.getColumn8();
    
    String[] selectedList = thisForm.getSelectedList();  
    if(selectedList == null)
    {
    	selectedList = new String[column2.length];
    	for(int m = 0; m < selectedList.length; m++)
    	{
    		selectedList[m] = "";
    	}
    } 
%>

  <html:hidden property="column8" value="<%= (String)column8[0]%>"/>

<%
    String refProp = new String();
    for (int i=1; i < column2.length; i++) {
        String thisRefType = column6[i];
        if (!prevRefType.equals(thisRefType)) {
	    refProp = (String) map.get(thisRefType); 	   
            if (i > 1) {
%>
</table>
<br>
<%         }  %>
<table class="framing-table" border="0" cellpadding="3" cellspacing="1" width="100%" summary="List table">
   <tr>
      <td class="column-head" colspan="6">
         <%= thisRefType %>
      </td>
   </tr>
<%
            if (map.containsKey(thisRefType)) {
%>
   <tr>
      <td class="table-text" colspan="6">
	  <bean:message key="appinstall.specify.jndi.name"/>
            <html:select property="selectedList" value="<%=selectedList[i-1]%>">         
            <html:option value="" >
	      <bean:message key="appinstall.select"/>
	    </html:option>
	    <logic:iterate id="dataSource" name="globalForm" property="<%=refProp%>" type="java.lang.String"> 
<%
                             int index = dataSource.indexOf(":");
                             String value = dataSource.substring(index+1);
%>
              <html:option value="<%= value %>" >
		   <%= dataSource %>
	      </html:option>
	    </logic:iterate>
          </html:select>
          <html:submit property="ApplyReset" styleId="other" styleClass="buttons">
              <bean:message key="appmanagement.button.apply"/>
          </html:submit>
      </td>
   </tr>
<%
            } else {
%>
            <html:hidden property="selectedList" value=""/>
<%
            }
            String updateStr = "updateCheckAll(this.form, '" + refProp + "CheckBox')";
%>
      
  <tr>
    <th class="column-head-name" scope="col" width="1%">
        <LABEL><input type="checkbox" name="allchecked<%=refProp%>" value="checkall" onclick="<%= updateStr %>" onkeypress="<%= updateStr %>" TITLE="<bean:message key="collection.checkall.checkbox.alt"/>"></LABEL>    </th>
    <th class="column-head-name" scope="col"><%= (String)column2[0]%> </th>
    <th class="column-head-name" scope="col"><%= (String)column3[0]%> </th>
    <th class="column-head-name" scope="col"><%= (String)column4[0]%> </th>
    <th class="column-head-name" scope="col"><%= (String)column5[0]%> </th>
    <th class="column-head-name" scope="col"><%= (String)column8[0]%> </th>
  </tr>
<%
		}
%>

   <tr>
     <td class="table-text"  width="1%">
<%
        if (map.containsKey(thisRefType)) {
	  String checkBoxStr = refProp + "CheckBox";	
%>
          <input type="checkbox" name="<%= checkBoxStr%>" value="<%=Integer.toString(i)%>"/>
<%
	}
%>
     </td>
     <td class="table-text" ><%=(String)column2[i]%></td>
     <td class="table-text" ><%=(String)column3[i]%></td>
     <td class="table-text" ><%=(String)column4[i]%></td>
     <td class="table-text" ><%=(String)column5[i]%></td>
     <td class="table-text" >
	  <html:text property="column8" size="25" value="<%=(String)column8[i]%>" />
     </td>
   </tr>

<%
 	   prevRefType = thisRefType;
	} 
%>
</table>
<br>
	<%-- the hidden field will ensure that selectedList will always be a String[] --%>
	<html:hidden property="selectedList" value="hidden"/>

