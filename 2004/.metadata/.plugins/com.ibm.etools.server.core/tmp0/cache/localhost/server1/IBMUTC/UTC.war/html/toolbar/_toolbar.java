package org.apache.jsp;

import com.ibm.etools.utc.view.Toolbar;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

/* File Declaration Phase */

public class _toolbar extends com.ibm.ws.webcontainer.jsp.runtime.HttpJspBase {

  /* Class Declaration Phase */

  static {
    /* Static Initializer Phase */
  }

  public _toolbar( ) {
  }

  private static boolean _jspx_inited = false;

  private static String _jspx_debug_jspSourceLocation = "/html/toolbar/toolbar.jsp";

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

            // HTML // begin [file="/html/toolbar/toolbar.jsp";from=(0,0);to=(9,0)]
              out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\r\n\r\n<!--\r\n Licensed Material - Property of IBM \r\n (C) Copyright IBM Corp. 2001 - All Rights Reserved. \r\n US Government Users Restricted Rights - Use, duplication or disclosure \r\n restricted by GSA ADP Schedule Contract with IBM Corp. \r\n-->\r\n\r\n");

            // end
            // HTML // begin [file="/html/toolbar/toolbar.jsp";from=(9,114);to=(10,0)]
              out.write("\r\n");

            // end
            // HTML // begin [file="/html/toolbar/toolbar.jsp";from=(10,37);to=(16,9)]
              out.write("\r\n\r\n<html>\r\n<head>\r\n  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n  <link rel=stylesheet type=\"text/css\" href=\"/UTC/css/dynamic.css\">\r\n  <title>");

            // end
            // ##DEBUG## ##TAGLIB## "/html/toolbar/toolbar.jsp" 17,9-"/html/toolbar/toolbar.jsp" 17,43
              _jspx_th_utc_resource_0.setPageContext(pageContext);
              _jspx_th_utc_resource_0.setParent(null);
              _jspx_th_utc_resource_0.setKey("toolbarTitle");
              _jspxTagObjects.push(_jspx_th_utc_resource_0);
                int _jspx_eval_utc_resource_0 = _jspx_th_utc_resource_0.doStartTag();
              // end
              // ##DEBUG## "/html/toolbar/toolbar.jsp" 17,9-"/html/toolbar/toolbar.jsp" 17,43
                if (_jspx_th_utc_resource_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                  return;
              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
            // end
            // HTML // begin [file="/html/toolbar/toolbar.jsp";from=(16,43);to=(21,0)]
              out.write("</title>\r\n</head>\r\n\r\n<body bgcolor=\"#ECE9D8\">\r\n\r\n");

            // end
            // ##DEBUG## ##SCRIPTLET## "/html/toolbar/toolbar.jsp" 22,2-"/html/toolbar/toolbar.jsp" 26,0
               int pg = -1;
                 try {
                   pg = Integer.parseInt(request.getParameter("page"));
                 } catch (Exception e) { }
            // end
            // HTML // begin [file="/html/toolbar/toolbar.jsp";from=(25,2);to=(30,68)]
              out.write("\r\n\r\n<div>\r\n<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n<tr>\r\n<td valign=\"middle\" align=\"center\" width=\"25\" height=\"25\"><img src=\"");

            // end
            // ##DEBUG## "/html/toolbar/toolbar.jsp" 31,71-"/html/toolbar/toolbar.jsp" 31,93
              out.print( Toolbar.getImage(pg) );
            // end
            // HTML // begin [file="/html/toolbar/toolbar.jsp";from=(30,95);to=(31,68)]
              out.write("\" width=\"16\" height=\"16\" border=\"0\"></td>\r\n<td valign=\"middle\" align=\"left\" height=\"25\" class=\"toolbar\" nowrap>");

            // end
            // ##DEBUG## ##TAGLIB## "/html/toolbar/toolbar.jsp" 32,68-"/html/toolbar/toolbar.jsp" 32,117
              /* ------  utc:resource ------ */
              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_1 = new com.ibm.etools.utc.ResourceTag();
              _jspx_th_utc_resource_1.setPageContext(pageContext);
              _jspx_th_utc_resource_1.setParent(null);
              _jspx_th_utc_resource_1.setKey( Toolbar.getTitle(pg) );
              _jspxTagObjects.push(_jspx_th_utc_resource_1);
                int _jspx_eval_utc_resource_1 = _jspx_th_utc_resource_1.doStartTag();
              // end
              // ##DEBUG## "/html/toolbar/toolbar.jsp" 32,68-"/html/toolbar/toolbar.jsp" 32,117
                if (_jspx_th_utc_resource_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                  return;
              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
            // end
            // HTML // begin [file="/html/toolbar/toolbar.jsp";from=(31,117);to=(34,0)]
              out.write("</td>\r\n<td valign=\"middle\" align=\"center\" width=\"*\" height=\"25\">&nbsp;</td>\r\n\r\n");

            // end
            // ##DEBUG## "/html/toolbar/toolbar.jsp" 35,3-"/html/toolbar/toolbar.jsp" 35,46
              out.print( Toolbar.getExtraToolbarItems(request, pg) );
            // end
            // HTML // begin [file="/html/toolbar/toolbar.jsp";from=(34,48);to=(36,0)]
              out.write("\r\n\r\n");

            // end
            // ##DEBUG## ##SCRIPTLET## "/html/toolbar/toolbar.jsp" 37,2-"/html/toolbar/toolbar.jsp" 38,26
               String helpURL = Toolbar.getHelpURL(pg);
                 if (helpURL != null) { 
            // end
            // HTML // begin [file="/html/toolbar/toolbar.jsp";from=(37,28);to=(39,28)]
              out.write("\r\n  <td valign=\"middle\" align=\"center\" width=\"25\" height=\"25\">\r\n    <a href=\"/UTC/help?name=");

            // end
            // ##DEBUG## "/html/toolbar/toolbar.jsp" 40,31-"/html/toolbar/toolbar.jsp" 40,40
              out.print( helpURL );
            // end
            // HTML // begin [file="/html/toolbar/toolbar.jsp";from=(39,42);to=(39,53)]
              out.write("&returnURL=");

            // end
            // ##DEBUG## "/html/toolbar/toolbar.jsp" 40,56-"/html/toolbar/toolbar.jsp" 40,86
              out.print( Toolbar.getHelpReturnURL(pg) );
            // end
            // HTML // begin [file="/html/toolbar/toolbar.jsp";from=(39,88);to=(39,96)]
              out.write("&random=");

            // end
            // ##DEBUG## "/html/toolbar/toolbar.jsp" 40,99-"/html/toolbar/toolbar.jsp" 40,114
              out.print( Math.random() );
            // end
            // HTML // begin [file="/html/toolbar/toolbar.jsp";from=(39,116);to=(39,204)]
              out.write("\" target=\"main\"><img src=\"/UTC/images/elocal/help_view.gif\" width=\"16\" height=\"16\" alt=\"");

            // end
            // ##DEBUG## ##TAGLIB## "/html/toolbar/toolbar.jsp" 40,204-"/html/toolbar/toolbar.jsp" 40,239
              /* ------  utc:resource ------ */
              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_2 = new com.ibm.etools.utc.ResourceTag();
              _jspx_th_utc_resource_2.setPageContext(pageContext);
              _jspx_th_utc_resource_2.setParent(null);
              _jspx_th_utc_resource_2.setKey("helpPageTitle");
              _jspxTagObjects.push(_jspx_th_utc_resource_2);
                int _jspx_eval_utc_resource_2 = _jspx_th_utc_resource_2.doStartTag();
              // end
              // ##DEBUG## "/html/toolbar/toolbar.jsp" 40,204-"/html/toolbar/toolbar.jsp" 40,239
                if (_jspx_th_utc_resource_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                  return;
              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
            // end
            // HTML // begin [file="/html/toolbar/toolbar.jsp";from=(39,239);to=(40,0)]
              out.write("\" border=\"0\" onMouseOver=\"src='/UTC/images/clocal/help_view.gif'\" onMouseOut=\"src='/UTC/images/elocal/help_view.gif'\"></a></td>\r\n");

            // end
            // ##DEBUG## ##SCRIPTLET## "/html/toolbar/toolbar.jsp" 41,2-"/html/toolbar/toolbar.jsp" 41,5
               } 
            // end
            // HTML // begin [file="/html/toolbar/toolbar.jsp";from=(40,7);to=(48,7)]
              out.write("\r\n\r\n<td valign=\"middle\" align=\"center\" width=\"10\" height=\"25\">&nbsp;</td>\r\n</tr>\r\n\r\n</table>\r\n</div>\r\n</body>\r\n</html>");

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
