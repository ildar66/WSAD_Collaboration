<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@page contentType="text/html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<table width="100%" border="0" cellspacing="0" cellpadding="5">
  <tr>
	<td class="table-text">
      <html:radio property="selectType" value="group" styleId="aradio"/>
	  <bean:message key="specify.group"/>
	</td>
  </tr>
  <tr>
	<td class="complex-property">
	  <html:text property="group" size="20"/>	
	</td>
  </tr>
  <tr>
	<td class="table-text">
      <html:radio property="selectType" value="special" styleId="aradio"/>
	  <bean:message key="specify.special.subject"/>
	</td>
  </tr>
  <tr>
	<td class="complex-property">
	  <html:select property="specialSubject" styleId="adynselect"> 
	    <html:option key="special.everyone" value="EVERYONE"/>
	    <html:option key="special.allauthenticated" value="ALL_AUTHENTICATED"/>
      </html:select>
	</td>
  </tr>
</table>
