<%-- IBM Confidential OCO Source Material --%>
<%-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 1997, 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="com.ibm.ws.sm.workspace.*"%>
<%@ page import="com.ibm.ws.workspace.query.*"%>
<%@ page import="com.ibm.ws.console.core.*"%>
<%@ page import="com.ibm.websphere.product.*,com.ibm.websphere.product.xml.product.product,java.util.Locale,org.apache.struts.util.MessageResources,org.apache.struts.action.Action"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%
    WASProduct wp = new WASProduct();
    product prod = null;
    if (wp.productPresent(WASProduct.PRODUCTID_EMBEDDED_EXPRESS))
	    prod = wp.getProductById(WASProduct.PRODUCTID_EMBEDDED_EXPRESS);
    else if (wp.productPresent(WASProduct.PRODUCTID_EXPRESS))
	    prod = wp.getProductById(WASProduct.PRODUCTID_EXPRESS);
    else if (wp.productPresent(WASProduct.PRODUCTID_ND)) {

        WorkSpaceQueryUtil util = WorkSpaceQueryUtilFactory.getUtil();
        RepositoryContext cellContext = (RepositoryContext)session.getAttribute(Constants.CURRENTCELLCTXT_KEY);
        try {
            if (util.isStandAloneCell(cellContext)) {
                prod = wp.getProductById(WASProduct.PRODUCTID_BASE);
            }
            else {
                prod = wp.getProductById(WASProduct.PRODUCTID_ND);
            } 
        }
        catch (Exception e) {
            System.out.println("exception in util.isStandAloneCell " + e.toString());
            prod = wp.getProductById(WASProduct.PRODUCTID_BASE);
        }

	} else
	    prod = wp.getProductById(WASProduct.PRODUCTID_BASE);
	String sep = "---------------------------------------";
	String copyright = "(c) Copyright IBM Corporation 1996, 2003.";
	String prodNameVer = new String(prod.getName() + ", " + 	prod.getVersion());
    Locale locale = (Locale)session.getAttribute(Action.LOCALE_KEY);
    MessageResources messages = (MessageResources)application.getAttribute(Action.MESSAGES_KEY);
	String aboutText = prodNameVer + "\n" +
	                   messages.getMessage(locale, "about.build.num") + " " + prod.getBuildInfo().getLevel() + "\n" +
	                   messages.getMessage(locale, "about.build.date") + " " + prod.getBuildInfo().getDate() + "\n" + 
	                   sep + "\n";
	                   
	if (wp.productPresent(WASProduct.PRODUCTID_XD)) {
	    prod = wp.getProductById(WASProduct.PRODUCTID_XD);
		prodNameVer = new String(prod.getName() + ", " + 	prod.getVersion());
		aboutText += prodNameVer + "\n" +
		             messages.getMessage(locale, "about.build.num") + " " + prod.getBuildInfo().getLevel() + "\n" +
	                 messages.getMessage(locale, "about.build.date") + " " + prod.getBuildInfo().getDate() + "\n" + 
	                sep + "\n";
	}
			    
	if (wp.productPresent(WASProduct.PRODUCTID_PME)) {
	    prod = wp.getProductById(WASProduct.PRODUCTID_PME);
		prodNameVer = new String(prod.getName() + ", " + 	prod.getVersion());
		aboutText += prodNameVer + "\n" +
		             messages.getMessage(locale, "about.build.num") + " " + prod.getBuildInfo().getLevel() + "\n" +
	                 messages.getMessage(locale, "about.build.date") + " " + prod.getBuildInfo().getDate() + "\n" + 
	                 sep + "\n";
	}
	aboutText += copyright;
%>
<STYLE>
TEXTAREA { border-style: none; scrollbar-face-color:#CCCCCC; scrollbar-shadow-color:#FFFFFF; scrollbar-highlight-color:#FFFFFF; scrollbar-3dlight-color:#6B7A92; scrollbar-darkshadow-color:#6B7A92; scrollbar-track-color:#E2E2E2; scrollbar-arrow-color:#6B7A92  }
</STYLE>
<form>
<textarea name="abouttext" rows='6' cols='40'>
<%=aboutText%>
</textarea>
</form> 
