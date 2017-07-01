<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" %>
<%@ page import="javax.servlet.ServletException,org.apache.struts.util.MessageResources,org.apache.struts.action.Action,java.util.*"%>  
<% if (true) {
    MessageResources messages = (org.apache.struts.util.MessageResources) application.getAttribute(Action.MESSAGES_KEY);
    Locale locale = request.getLocale();
    String message = messages.getMessage(locale, "error.RefOject.notFound");
    if (message == null) {
        message = "Error occured:unable to retrieve RefObject";
    }
    throw new ServletException(message);
  }  %>
