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
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>

<ibmcommon:detectLocale/>


<html:html locale="true">
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<META HTTP-EQUIV="Expires" CONTENT="0">
<TITLE>Detail Page</TITLE><script language="JavaScript" src="scripts/menu_functions.js"></script>

<%@ include file = "/secure/layouts/browser_detection.jsp" %>
</head>

<tiles:useAttribute id="label" name="label" classname="java.lang.String"/>
<tiles:useAttribute id="formName" name="formName" classname="String"/>
<tiles:useAttribute id="formAction" name="formAction" classname="String"/>
<tiles:useAttribute id="formType" name="formType" classname="String"/>
<tiles:useAttribute id="size" name="size" classname="String"/>
<tiles:useAttribute id="property" name="property" classname="String"/>

<bean:define id="totalRows"     name="<%=formName%>" property="totalRows" type="java.lang.String"/>
<bean:define id="filteredRows"  name="<%=formName%>" property="filteredRows" type="java.lang.String"/>

<html:form action="<%=formAction%>" name="<%=formName%>" type="<%=formType%>">

<table border="0" cellpadding="0" cellspacing="10" width="100%" summary="Properties Framing Table" >

   <tr valign="top">
        <td class="table-text"><bean:message key="paging.total.label"/>: <%=totalRows%>, <bean:message key="paging.filtered.total.label"/>: <%=filteredRows%><BR>
   </tr>

   <tr valign="top">

        <td class="table-text"  scope="row" valign="top" nowrap>
            <label  for="atextfield"> <bean:message key="retrieve.lines.label" /></label>                                
            <html:text property="retrieveLineRange" name="<%=formName%>" size="30" />
            <input type="submit" name="Refresh"  value="<bean:message key="probdetermination.button.refresh"/>" class="buttons" id="other">
        </td>
       
   </tr>


    <tr>
        <td class="framing-table">

            <table border="0" cellpadding="3" cellspacing="1" width="100%" summary="List table" class="framing-table">
               
              <tr> 
                <th class="column-head-name" scope="col"> <a name="additionalProperties"></a><bean:message key="<%=label %>"/></th>
            
              </tr>
              <tr> 
                   <td class="table-text" headers="header1">
                   <pre>
                    <bean:write property="<%=property%>" name="<%=formName%>"/>
                   </pre>
                    </td>
              </tr>

              <tr>
                <td class="button-section" >
                <input type="submit" name="ok.nosave" value="<bean:message key="button.ok"/>" class="buttons" id="navigation">
                </td>
             </tr>
           </table>

        </td>
    </tr>
 </table>
          
</html:form>
</html:html>

