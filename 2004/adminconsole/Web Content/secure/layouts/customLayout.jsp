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

                    
   <tiles:useAttribute id="label" name="label" classname="java.lang.String"/>
   <tiles:useAttribute id="formBean" name="bean" classname="java.lang.String"/>
   <tiles:useAttribute id="jspPage" name="jspPage" classname="java.lang.String"/>
   <tiles:useAttribute id="desc" name="desc" classname="java.lang.String"/>
   <tiles:useAttribute id="readOnly" name="readOnly" classname="java.lang.String"/>
   <tiles:useAttribute id="size" name="size" classname="java.lang.String" />
   <tiles:useAttribute id="units" name="units" classname="java.lang.String"/>
   <tiles:useAttribute id="property" name="property" classname="java.lang.String" />
   <tiles:useAttribute id="isRequired" name="isRequired" classname="java.lang.String" />

   <bean:define id="bean" name="<%=formBean%>"/>
   
   <%-- // defect 126608
   <tr valign="top">
   --%>

        <td class="table-text"  scope="row" valign="top" nowrap>
            <label  for="acustom"> <bean:message key="<%=label%>" /></label>                                
        </td>

        <td class="table-text" nowrap valign="top">
            <fieldset id="acustom">
<%			if (readOnly != null && readOnly.equals("true"))
			{ %>
	            <tiles:insert page="<%=jspPage%>">
	            	<tiles:put name="readOnly" value="true"/>
                        <tiles:put name="formBean" value="<%=formBean%>" />
                        <tiles:put name="size" value="<%=size%>" />
                        <tiles:put name="units" value="<%=units%>"/>
                        <tiles:put name="property" value="<%=property%>" />
                        <tiles:put name="isRequired" value="<%=isRequired%>" />
	           	</tiles:insert>
<%	        } else {%>        
			            
	            <tiles:insert page="<%=jspPage%>">
	            	<tiles:put name="readOnly" value="false"/>
                        <tiles:put name="formBean" value="<%=formBean%>" />
                        <tiles:put name="size" value="<%=size%>" />
                        <tiles:put name="units" value="<%=units%>"/>
                        <tiles:put name="property" value="<%=property%>" />
                        <tiles:put name="isRequired" value="<%=isRequired%>" />
	           	</tiles:insert>
<%			} %>           
            </fieldset>
        </td>
       
   <%-- // defect 126608
        <td class="table-text" valign="top"><a href="transformer.jsp?xml=was_page_help&xsl=was_page_help" target="WAS_Help">
            <img src="<%=request.getContextPath()%>/images/more.gif" border="0" alt="View more information about this field" align="texttop"></a>
            <bean:message key="<%=desc%>"/>
        </td>
   </tr>
   --%>

    

   
   
 
