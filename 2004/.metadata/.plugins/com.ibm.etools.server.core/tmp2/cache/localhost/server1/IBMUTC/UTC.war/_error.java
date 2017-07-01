package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

/* File Declaration Phase */

public class _error extends com.ibm.ws.webcontainer.jsp.runtime.HttpJspBase {

  /* Class Declaration Phase */

  static {
    /* Static Initializer Phase */
  }

  public _error( ) {
  }

  private static boolean _jspx_inited = false;

  private static String _jspx_debug_jspSourceLocation = "/error.jsp";

  public final void _jspx_init() throws org.apache.jasper.runtime.JspException {
    /* Init Method Phase */
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse  response)
      throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    PageContext pageContext = null;
    HttpSession session = null;
    Throwable exception = (Throwable) request.getAttribute("javax.servlet.jsp.jspException");
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
          			"", true, 8192, true);

          application = pageContext.getServletContext();
          config = pageContext.getServletConfig();
          session = pageContext.getSession();
          out = pageContext.getOut();

          /* Service Method Phase */

          // HTML // begin [file="/error.jsp";from=(0,0);to=(9,0)]
            out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\r\n\r\n<!--\r\n Licensed Material - Property of IBM \r\n (C) Copyright IBM Corp. 2001 - All Rights Reserved. \r\n US Government Users Restricted Rights - Use, duplication or disclosure \r\n restricted by GSA ADP Schedule Contract with IBM Corp. \r\n-->\r\n\r\n");

          // end
          // HTML // begin [file="/error.jsp";from=(9,69);to=(10,0)]
            out.write("\r\n");

          // end
          // HTML // begin [file="/error.jsp";from=(10,37);to=(20,9)]
            out.write("\r\n\r\n<html>\r\n<head>\r\n  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n  <meta http-equiv=\"Content-Style-Type\" content=\"text/css\">\r\n  <meta http-equiv=\"Pragma\" content=\"no-cache\">\r\n  <meta http-equiv=\"Cache-Control\" content=\"no-cache\">\r\n  <meta http-equiv=\"expires\" content=\"0\">\r\n  <link rel=stylesheet type=\"text/css\" href=\"/UTC/css/dynamic.css\">\r\n  <title>");

          // end
          // ##DEBUG## ##TAGLIB## "/error.jsp" 21,9-"/error.jsp" 21,40
            _jspx_th_utc_resource_0.setPageContext(pageContext);
            _jspx_th_utc_resource_0.setParent(null);
            _jspx_th_utc_resource_0.setKey("errorPage");
            _jspxTagObjects.push(_jspx_th_utc_resource_0);
              int _jspx_eval_utc_resource_0 = _jspx_th_utc_resource_0.doStartTag();
            // end
            // ##DEBUG## "/error.jsp" 21,9-"/error.jsp" 21,40
              if (_jspx_th_utc_resource_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                return;
            ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
          // end
          // HTML // begin [file="/error.jsp";from=(20,40);to=(25,0)]
            out.write("</title>\r\n</head>\r\n\r\n<body>\r\n\r\n");

          // end
          // ##DEBUG## ##TAGLIB## "/error.jsp" 26,0-"/error.jsp" 26,34
            /* ------  utc:resource ------ */
            com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_1 = new com.ibm.etools.utc.ResourceTag();
            _jspx_th_utc_resource_1.setPageContext(pageContext);
            _jspx_th_utc_resource_1.setParent(null);
            _jspx_th_utc_resource_1.setKey("errorMessage");
            _jspxTagObjects.push(_jspx_th_utc_resource_1);
              int _jspx_eval_utc_resource_1 = _jspx_th_utc_resource_1.doStartTag();
            // end
            // ##DEBUG## "/error.jsp" 26,0-"/error.jsp" 26,34
              if (_jspx_th_utc_resource_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                return;
            ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
          // end
          // HTML // begin [file="/error.jsp";from=(25,34);to=(26,0)]
            out.write("\r\n");

          // end
          // ##DEBUG## "/error.jsp" 27,3-"/error.jsp" 27,27
            out.print( exception.getMessage() );
          // end
          // HTML // begin [file="/error.jsp";from=(26,29);to=(27,5)]
            out.write("<br>\r\n<pre>");

          // end
          // ##DEBUG## ##SCRIPTLET## "/error.jsp" 28,7-"/error.jsp" 28,37
             exception.printStackTrace(); 
          // end
          // HTML // begin [file="/error.jsp";from=(27,39);to=(30,7)]
            out.write("</pre><p>\r\n\r\n</body>\r\n</html>");

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
