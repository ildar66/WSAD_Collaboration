<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>
<tiles:useAttribute name="actionForm" classname="java.lang.String" />
<tiles:useAttribute name="formType" classname="java.lang.String" />
<tiles:useAttribute name="configStep" classname="java.lang.String" />
<tiles:useAttribute name="actionHandler" classname="java.lang.String" />



<html:form action="<%=actionHandler%>" name="<%=actionForm%>" type="<%=formType%>">

    <input type="hidden" name="currentStep" value="this">
    <table border="0" cellpadding="8" cellspacing="0" width="100%" summary="Multiple Step Process Table" class="framing-table">
    <TBODY>
      <tr>
        <td class="wizard-step-expanded">	
          <tiles:insert page="<%=configStep%>" flush="true" >
             <tiles:put name="actionForm" beanName="actionForm" beanScope="page"/>
          </tiles:insert>
        </td>
      </tr>
      <tr>

    <th class="button-section" colspan="3">
      <input type="submit" name="apply" value="<bean:message key="button.apply"/>" class="buttons" id="navigation">
      <input type="submit" name="save" value="<bean:message key="button.ok"/>" class="buttons" id="navigation"> 
      <input type="reset" name="reset" value="<bean:message key="button.reset"/>" class="buttons" id="navigation">
      <input type="submit" name="cancel" value="<bean:message key="button.cancel"/>" class="buttons" id="navigation">
    </th>
  </tr>
  </TBODY>      
</table>
</html:form>

