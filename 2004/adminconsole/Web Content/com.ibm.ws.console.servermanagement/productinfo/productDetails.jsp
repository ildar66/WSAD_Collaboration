<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.*"%>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute name="colSpan" classname="java.lang.String" />
<tiles:useAttribute name="xmldata" classname="java.lang.String" />
<tiles:useAttribute name="headerList" classname="java.util.List"/>

<html:html locale="true">                                              

<body CLASS="content">              


<html:form action="productInfoDetail.do" name="com.ibm.ws.console.servermanagement.ProductInfoDetailForm" type="com.ibm.ws.console.servermanagement.productinfo.ProductInfoDetailForm">

<input type="hidden" name="action" lang="en_US" value="Edit">
  <table class="framing-table" border="0" cellpadding="3" cellspacing="1" width="100%" summary="Properties Table" >
        
        <tbody>
          <tr> 
            <th colspan="<%=colSpan%>" class="column-head" scope="rowgroup"><a name="generalProps"></a><bean:message key="config.general.properties"/></th> 
		  </tr>
		  <tr valign="top"> 
            <% Iterator iter = headerList.iterator();
            String header = null;
            boolean firstHeader = true;
            while (iter.hasNext()) {
                header = (String) iter.next();
                System.out.println("header " + header);
                if (firstHeader) {
                    firstHeader = false;
             %>
                    <td class="column-head-name"  nowrap valign="top" scope="row"><bean:message key="<%=header%>"/></td>
           <% } else { %>          
               <td class="column-head-name" nowrap valign="top"><bean:message key="<%=header%>"/></td>
            <% }
            }
            %>
		  </tr>
          <bean:define id="xmlOutput" property="<%=xmldata%>" name="com.ibm.ws.console.servermanagement.ProductInfoDetailForm"/>
		  <%= xmlOutput %>          
		  <tr> 
            <th valign="top" class="button-section" colspan="<%=colSpan%>"> 
              <input type="submit" name="productInfo" value="<bean:message key="button.ok"/>" class="buttons" id="navigation"/>
            </th>
		  </tr>

  </tbody>
 </table>

 <br/>     
</html:form>   
</body>
</html:html>



