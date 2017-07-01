package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

/* File Declaration Phase */

public class _helpPage extends com.ibm.ws.webcontainer.jsp.runtime.HttpJspBase {

  /* Class Declaration Phase */

  static {
    /* Static Initializer Phase */
  }

  public _helpPage( ) {
  }

  private static boolean _jspx_inited = false;

  private static String _jspx_debug_jspSourceLocation = "/html/helpPage/helpPage.jsp";

  public final void _jspx_init() throws org.apache.jasper.runtime.JspException {
    /* Init Method Phase */
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse  response)
      throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    String  _value = null;
    java.util.Stack _jspxTagObjects = new java.util.Stack();
    /* Service Declaration Phase */

    try {

      if (_jspx_inited == false) {
        synchronized (this) {
          if (_jspx_inited == false) {
            _jspx_init();
            _jspx_inited = true;
          }
        }
      }

      _jspxFactory = JspFactory.getDefaultFactory();
      response.setContentType("text/html; charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			"/error.jsp", true, 8192, true);

      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();

      /* Service Method Phase */

      // HTML // begin [file="/html/helpPage/helpPage.jsp";from=(0,0);to=(11,0)]
        out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\r\n\r\n<!--\r\n Licensed Material - Property of IBM \r\n (C) Copyright IBM Corp. 2001 - All Rights Reserved. \r\n US Government Users Restricted Rights - Use, duplication or disclosure \r\n restricted by GSA ADP Schedule Contract with IBM Corp. \r\n-->\r\n\r\n<html>\r\n\r\n");

      // end
      // HTML // begin [file="/html/helpPage/helpPage.jsp";from=(11,73);to=(12,0)]
        out.write("\r\n");

      // end
      // ##DEBUG## "/html/helpPage/helpPage.jsp" 13,0-"/html/helpPage/helpPage.jsp" 13,85
        com.ibm.etools.utc.view.HelpView helpView = null;
        boolean _jspx_specialhelpView  = false;
         synchronized (session) {
          helpView= (com.ibm.etools.utc.view.HelpView)
          pageContext.getAttribute("helpView",PageContext.SESSION_SCOPE);
          if ( helpView == null ) {
            _jspx_specialhelpView = true;
            try {
              helpView = (com.ibm.etools.utc.view.HelpView) java.beans.Beans.instantiate(this.getClass().getClassLoader(), "com.ibm.etools.utc.view.HelpView");
            } catch (ClassNotFoundException exc) {
               throw new InstantiationException(exc.getMessage());
            } catch (Exception exc) {
               throw new ServletException (" Cannot create bean of class "+"com.ibm.etools.utc.view.HelpView", exc);
            }
            pageContext.setAttribute("helpView", helpView, PageContext.SESSION_SCOPE);
          }
         } 
        if(_jspx_specialhelpView == true) {
      // end
      // ##DEBUG## "/html/helpPage/helpPage.jsp" 13,0-"/html/helpPage/helpPage.jsp" 13,85
        }
      // end
      // HTML // begin [file="/html/helpPage/helpPage.jsp";from=(12,85);to=(27,75)]
        out.write("\r\n\r\n<head>\r\n  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n  <meta http-equiv=\"Content-Style-Type\" content=\"text/css\">\r\n  <meta http-equiv=\"Pragma\" content=\"no-cache\">\r\n  <meta http-equiv=\"Cache-Control\" content=\"no-cache\">\r\n  <meta http-equiv=\"expires\" content=\"0\">\r\n  <link rel=stylesheet type=\"text/css\" href=\"/UTC/css/windows.css\">\r\n  <title><utc:resource key=\"pageTitle\"/></title>\r\n</head>\r\n\r\n<body id=\"margin\" bgcolor=\"#ECE9D8\">\r\n\r\n<div id=\"border\">\r\n  <iframe id=\"toolbar\" src=\"/UTC/html/toolbar/toolbar.jsp?page=9&returnURL=");

      // end
      // ##DEBUG## "/html/helpPage/helpPage.jsp" 28,78-"/html/helpPage/helpPage.jsp" 28,103
        out.print( helpView.getReturnURL() );
      // end
      // HTML // begin [file="/html/helpPage/helpPage.jsp";from=(27,105);to=(30,72)]
        out.write("\" frameborder=\"0\" scrolling=\"No\" marginheight=\"1\" marginwidth=\"1\"></iframe>\r\n</div>\r\n<div id=\"shadow\">\r\n  <iframe id=\"content\" name=\"formHelp\" src=\"/UTC/html/helpPage/help.jsp#");

      // end
      // ##DEBUG## "/html/helpPage/helpPage.jsp" 31,75-"/html/helpPage/helpPage.jsp" 31,95
        out.print( helpView.getName() );
      // end
      // HTML // begin [file="/html/helpPage/helpPage.jsp";from=(30,97);to=(34,7)]
        out.write("\" frameborder=\"0\" scrolling=\"auto\" marginheight=\"2\" marginwidth=\"4\"></iframe>\r\n</div>\r\n\r\n</body>\r\n</html>");

      // end

    } catch (Throwable t) {
      if (out != null && out.getBufferSize() != 0)
        out.clearBuffer();
      if (pageContext != null) pageContext.handlePageException(t);
    } finally {
      while (_jspxTagObjects.empty() == false){
        ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
      }
      if (_jspxFactory != null) _jspxFactory.releasePageContext(pageContext);

      /* Service Finally Phase */
    }
  }
}
