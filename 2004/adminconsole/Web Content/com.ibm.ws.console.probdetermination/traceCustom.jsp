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

<tiles:useAttribute name="label" classname="java.lang.String" />
<tiles:useAttribute  name="units" classname="java.lang.String"/>
<tiles:useAttribute  name="isRequired" classname="java.lang.String"/>
<tiles:useAttribute  name="property" classname="java.lang.String"/>
<tiles:useAttribute  name="desc" classname="java.lang.String"/>
<tiles:useAttribute  name="bean" classname="java.lang.String" />
<tiles:useAttribute  name="readOnly" classname="java.lang.String"/>
<tiles:useAttribute name="formAction" classname="java.lang.String" />
<tiles:useAttribute name="formType" classname="java.lang.String" />
<tiles:useAttribute name="size" classname="java.lang.String" />

<%
if (property.trim().startsWith("traceSpecification")){ 
   String jspName ="/com.ibm.ws.console.probdetermination/customAddLayout.jsp";
%>
   <tiles:insert name="<%=jspName%>" flush="false" >
   <tiles:put name="label" value="<%=label%>" />
   <tiles:put name="isRequired" value="<%=isRequired%>" />
   <tiles:put name="units" value=""/>
   <tiles:put name="readOnly" value="<%=readOnly%>"/>
   <tiles:put name="property" value="<%=property%>" />
   <tiles:put name="desc" value="<%=desc%>"/>
   <tiles:put name="bean" value="<%=bean%>" />
   <tiles:put name="formAction" value="<%=formAction%>" />
   <tiles:put name="formType" value="<%=formType%>" />
   <tiles:put name="size" value="5" />
   </tiles:insert>
        
<%
  } 
  else if (property.trim().startsWith("traceOutputType")){
   String jspName ="/com.ibm.ws.console.probdetermination/customRadioTraceLayout.jsp";
%>
   <tiles:insert name="<%=jspName%>" flush="false" >
   <tiles:put name="label" value="<%=label%>" />
   <tiles:put name="isRequired" value="<%=isRequired%>" />
   <tiles:put name="units" value=""/>
   <tiles:put name="readOnly" value="<%=readOnly%>"/>
   <tiles:put name="property" value="<%=property%>" />
   <tiles:put name="desc" value="<%=desc%>"/>
   <tiles:put name="bean" value="<%=bean%>" />
   <tiles:put name="formAction" value="<%=formAction%>" />
   <tiles:put name="formType" value="<%=formType%>" />
   <tiles:put name="size" value="5" />
   </tiles:insert>
        
<%
  } 
  else if (property.endsWith("FileName") || property.endsWith("Filename")) {
   String jspName ="/com.ibm.ws.console.probdetermination/customSelectDropLayout.jsp";
%>
   <tiles:insert name="<%=jspName%>" flush="false" >
   <tiles:put name="label" value="<%=label%>" />
   <tiles:put name="isRequired" value="<%=isRequired%>" />
   <tiles:put name="units" value=""/>
   <tiles:put name="property" value="<%=property%>" />
   <tiles:put name="readOnly" value="<%=readOnly%>"/>
   <tiles:put name="desc" value="<%=desc%>"/>
   <tiles:put name="bean" value="<%=bean%>" />
   <tiles:put name="formAction" value="<%=formAction%>" />
   <tiles:put name="formType" value="<%=formType%>" />
   <tiles:put name="size" value="5" />
   </tiles:insert>
        
<%
  } 
%>
