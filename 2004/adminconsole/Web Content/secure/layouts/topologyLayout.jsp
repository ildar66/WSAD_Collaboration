<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<tiles:useAttribute name="treeView" classname="java.lang.String" />     
     
     <table border="0" cellpadding="2" cellspacing="1" width="100%" summary="Properties Table" class="framing-table">
      <tr> 
        <th  class="column-head" scope="rowgroup"> <a name="generalAttributes"></a><bean:message key="tab.topology"/></th>
      </tr>
      
      <tr> 
        <td class="table-text"   headers="header1" nowrap valign="top" > 
         <tiles:insert name="<%=treeView%>" flush="true" />	    
        </TD>
     </TR>
     </TABLE>



