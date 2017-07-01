<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.*"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute id="readOnly" name="readOnly" classname="java.lang.String"/>

<% 	boolean val = false;
	if (readOnly != null && readOnly.equals("true"))
		val = true;
%>

      <table width="100%" border="0" cellspacing="0" cellpadding="2">
        <tr> 
          <td class="table-text"> 
		    <html:checkbox property="errEnablePrinting" styleId="acheckbox1" disabled="<%=val%>"/>
		    <label for="acheckbox1"><bean:message key="JVMLogs.print.label"/></label>
          </td>
		</tr>
        <tr> 
          <td class="complex-property"> 
		    <html:checkbox property="errFormatPrinting" styleId="acheckbox2" disabled="<%=val%>"/>
		    <label for="acheckbox2"><bean:message key="JVMLogs.print.format"/></label>
          </td>
		</tr>
      </table>