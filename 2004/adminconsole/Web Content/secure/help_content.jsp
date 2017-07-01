<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<ibmcommon:detectLocale/>

<html:html locale="true">

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="GENERATOR" content="Mozilla/4.73 [en] (Win98; U) [Netscape]">
    <title><bean:message key="help.main.welcome"/></title>
    <jsp:include page="layouts/browser_detection.jsp" flush="true"/>
  </head>

  <body class="content">
    <table border="0" width="90%" align="left" cellspacing="0" cellpadding="0">
      <tr>
        <td>
          <h1 id="bread-crumb"><img src="/admin/images/about.gif" align="texttop">&nbsp;<bean:message key="help.main.title"/></h1>
          <p class="instruction-text"><bean:message key="help.main.text"/>
          <p class="instruction-text"><bean:message key="help.main.text.index"/>
          <p class="instruction-text"><bean:message key="help.main.more.info"/>
        </td>
      </tr>
      <tr>
        <td class="instruction-text">
          <hr size="1">
            <strong><bean:message key="help.main.notices"/></strong><br>
            <bean:message key="help.main.copyright"/>
        </td>
      </tr>
    </table>
  </body>

</html:html>
