<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.*,com.ibm.ws.console.core.*,com.ibm.websphere.product.*,com.ibm.ws.sm.workspace.RepositoryContext,com.ibm.ws.workspace.query.*"%>
<%@ page errorPage="error.jsp" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<% 	
      WorkSpaceQueryUtil util = WorkSpaceQueryUtilFactory.getUtil();    
      RepositoryContext cellContext = (RepositoryContext) session.getAttribute(Constants.CURRENTCELLCTXT_KEY);
      boolean isND = false;
      try {
          if (util.isStandAloneCell(cellContext)) {
              isND = false;
          }
          else {
              isND = true;
          }
      }
      catch (Exception e) {
          isND = true;
      }
%>

    <table border="0" cellpadding="3" cellspacing="1" width="100%" summary="Properties Table" class="framing-table">
        <tbody>
        <tr valign="top">
          <td class="table-text" scope="row" nowrap><label for="{attributeName}"><bean:message key="NameSpaceBinding.bindingType.displayName"/></label></td>
          <td class="table-text" nowrap valign="top"><bean:message key="environment.label.ejb"/></td>
          <td class="table-text" valign="top">
             <bean:message key="NameSpaceBinding.bindingType.description"/>
          </td>
        </tr>

        <tr valign="top">
                    <tiles:insert page="/secure/layouts/textFieldLayout.jsp" flush="false">
                        <tiles:put name="property" value="name" />
                        <tiles:put name="isReadOnly" value="no" />
                        <tiles:put name="isRequired" value="yes" />
                        <tiles:put name="label" value="NameSpaceBinding.name.displayName" />
                        <tiles:put name="size" value="30" />
                        <tiles:put name="units" value=""/>
                        <tiles:put name="desc" value="NameSpaceBinding.name.description"/>
                        <tiles:put name="bean" value="com.ibm.ws.console.environment.EjbNameSpaceBindingDetailForm" />
                    </tiles:insert>
           <td class="table-text" valign="top">
               <bean:message key="NameSpaceBinding.name.description"/>
           </td>
        </tr>
        <tr valign="top">
                    <tiles:insert page="/secure/layouts/textFieldLayout.jsp" flush="false">
                        <tiles:put name="property" value="nameInNameSpace" />
                        <tiles:put name="isReadOnly" value="no" />
                        <tiles:put name="isRequired" value="yes" />
                        <tiles:put name="label" value="NameSpaceBinding.nameInNameSpace.displayName" />
                        <tiles:put name="size" value="30" />
                        <tiles:put name="units" value=""/>
                        <tiles:put name="desc" value="NameSpaceBinding.nameInNameSpace.description"/>
                        <tiles:put name="bean" value="com.ibm.ws.console.environment.EjbNameSpaceBindingDetailForm" />
                    </tiles:insert>
           <td class="table-text" valign="top">
               <bean:message key="NameSpaceBinding.nameInNameSpace.description"/>
           </td>
        </tr>
        <tr valign="top">
                    <tiles:insert name="/com.ibm.ws.console.environment/EjbNameSpaceBindingCustom.jsp" flush="false" >
                        <tiles:put name="label" value="EjbNameSpaceBinding.bindingLocation.displayName" />
                        <tiles:put name="isRequired" value="no" />
                        <tiles:put name="units" value=""/>
                        <tiles:put name="property" value="bindingLocation" />
                        <tiles:put name="desc" value="EjbNameSpaceBinding.bindingLocation.description"/>
                        <tiles:put name="bean" value="com.ibm.ws.console.environment.EjbNameSpaceBindingDetailForm" />
                        <tiles:put name="readOnly" value="false" />
                        <!--<tiles:put name="formAction" value="ejbNameSpaceBindingDetail.do" />
                        <tiles:put name="formType" value="" />
                        <tiles:put name="size" value="5" />-->
                    </tiles:insert>
           <td class="table-text" valign="top"> 
<%
   if (isND)
	{
%>
               <bean:message key="EjbNameSpaceBinding.bindingLocation.description.nd"/>
<% 
   }
	else
	{
%>
               <bean:message key="EjbNameSpaceBinding.bindingLocation.description.base"/>
<%
   }
%>
           </td>
        </tr>
        <tr valign="top">
                    <tiles:insert page="/secure/layouts/textFieldLayout.jsp" flush="false">
                        <tiles:put name="property" value="applicationServerName" />
                        <tiles:put name="isReadOnly" value="no" />
                        <tiles:put name="isRequired" value="yes" />
                        <tiles:put name="label" value="EjbNameSpaceBinding.applicationServerName.displayName" />
                        <tiles:put name="size" value="30" />
                        <tiles:put name="units" value=""/>
                        <tiles:put name="desc" value="EjbNameSpaceBinding.applicationServerName.description"/>
                        <tiles:put name="bean" value="com.ibm.ws.console.environment.EjbNameSpaceBindingDetailForm" />
                    </tiles:insert>
           <td class="table-text" valign="top">
               <bean:message key="EjbNameSpaceBinding.applicationServerName.description"/>
           </td>
        </tr>
        <tr valign="top">
                    <tiles:insert page="/secure/layouts/textFieldLayout.jsp" flush="false">
                        <tiles:put name="property" value="ejbJndiName" />
                        <tiles:put name="isReadOnly" value="no" />
                        <tiles:put name="isRequired" value="yes" />
                        <tiles:put name="label" value="EjbNameSpaceBinding.ejbJndiName.displayName" />
                        <tiles:put name="size" value="30" />
                        <tiles:put name="units" value=""/>
                        <tiles:put name="desc" value="EjbNameSpaceBinding.ejbJndiName.description"/>
                        <tiles:put name="bean" value="com.ibm.ws.console.environment.EjbNameSpaceBindingDetailForm" />
                    </tiles:insert>
           <td class="table-text" valign="top">
               <bean:message key="EjbNameSpaceBinding.ejbJndiName.description"/>
           </td>
        </tr>
        </tbody>
    </table>
