package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

/* File Declaration Phase */

public class _propertiesPage extends com.ibm.ws.webcontainer.jsp.runtime.HttpJspBase {

  /* Class Declaration Phase */

  static {
    /* Static Initializer Phase */
  }

  public _propertiesPage( ) {
  }

  private static boolean _jspx_inited = false;

  private static String _jspx_debug_jspSourceLocation = "/html/propertiesPage/propertiesPage.jsp";

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
      /* ------  utc:resource ------ */
      com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_0 = new com.ibm.etools.utc.ResourceTag();

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

        // HTML // begin [file="/html/propertiesPage/propertiesPage.jsp";from=(0,0);to=(11,0)]
          out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\r\n\r\n<!--\r\n Licensed Material - Property of IBM \r\n (C) Copyright IBM Corp. 2001 - All Rights Reserved. \r\n US Government Users Restricted Rights - Use, duplication or disclosure \r\n restricted by GSA ADP Schedule Contract with IBM Corp. \r\n-->\r\n\r\n<html>\r\n\r\n");

        // end
        // HTML // begin [file="/html/propertiesPage/propertiesPage.jsp";from=(11,73);to=(12,0)]
          out.write("\r\n");

        // end
        // HTML // begin [file="/html/propertiesPage/propertiesPage.jsp";from=(12,37);to=(21,9)]
          out.write("\r\n\r\n<head>\r\n  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n  <meta http-equiv=\"Content-Style-Type\" content=\"text/css\">\r\n  <meta http-equiv=\"Pragma\" content=\"no-cache\">\r\n  <meta http-equiv=\"Cache-Control\" content=\"no-cache\">\r\n  <meta http-equiv=\"expires\" content=\"0\">\r\n  <link rel=stylesheet type=\"text/css\" href=\"/UTC/css/windows.css\">\r\n  <title>");

        // end
        // ##DEBUG## ##TAGLIB## "/html/propertiesPage/propertiesPage.jsp" 22,9-"/html/propertiesPage/propertiesPage.jsp" 22,40
          _jspx_th_utc_resource_0.setPageContext(pageContext);
          _jspx_th_utc_resource_0.setParent(null);
          _jspx_th_utc_resource_0.setKey("pageTitle");
          _jspxTagObjects.push(_jspx_th_utc_resource_0);
            int _jspx_eval_utc_resource_0 = _jspx_th_utc_resource_0.doStartTag();
          // end
          // ##DEBUG## "/html/propertiesPage/propertiesPage.jsp" 22,9-"/html/propertiesPage/propertiesPage.jsp" 22,40
            if (_jspx_th_utc_resource_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
              return;
          ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
        // end
        // HTML // begin [file="/html/propertiesPage/propertiesPage.jsp";from=(21,40);to=(30,95)]
          out.write("</title>\r\n</head>\r\n\r\n<body id=\"margin\" bgcolor=\"#ECE9D8\">\r\n\r\n<div id=\"border\">\r\n  <iframe id=\"toolbar\" src=\"/UTC/html/toolbar/toolbar.jsp?page=7\" frameborder=\"0\" scrolling=\"No\" frameborder=\"0\" marginheight=\"1\" marginheight=\"1\"></iframe>\r\n</div>\r\n<div id=\"shadow\">\r\n  <iframe id=\"content\" name=\"formProperties\" src=\"/UTC/html/propertiesPage/properties.jsp?rand=");

        // end
        // ##DEBUG## "/html/propertiesPage/propertiesPage.jsp" 31,98-"/html/propertiesPage/propertiesPage.jsp" 31,113
          out.print( Math.random() );
        // end
        // HTML // begin [file="/html/propertiesPage/propertiesPage.jsp";from=(30,115);to=(34,7)]
          out.write("\" frameborder=\"0\" scrolling=\"auto\" frameborder=\"0\" marginheight=\"2\" marginwidth=\"4\"></iframe>\r\n</div>\r\n\r\n</body>\r\n</html>");

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
