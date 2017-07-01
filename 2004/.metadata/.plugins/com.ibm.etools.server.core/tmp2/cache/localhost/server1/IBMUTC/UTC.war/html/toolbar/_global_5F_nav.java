package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

/* File Declaration Phase */

public class _global_5F_nav extends com.ibm.ws.webcontainer.jsp.runtime.HttpJspBase {

  /* Class Declaration Phase */

  static {
    /* Static Initializer Phase */
  }

  public _global_5F_nav( ) {
  }

  private static boolean _jspx_inited = false;

  private static String _jspx_debug_jspSourceLocation = "/html/toolbar/global_nav.jsp";

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

                    // HTML // begin [file="/html/toolbar/global_nav.jsp";from=(0,0);to=(11,0)]
                      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\r\n\r\n<!--\r\n Licensed Material - Property of IBM \r\n (C) Copyright IBM Corp. 2001 - All Rights Reserved. \r\n US Government Users Restricted Rights - Use, duplication or disclosure \r\n restricted by GSA ADP Schedule Contract with IBM Corp. \r\n-->\r\n\r\n<html>\r\n\r\n");

                    // end
                    // HTML // begin [file="/html/toolbar/global_nav.jsp";from=(11,73);to=(12,0)]
                      out.write("\r\n");

                    // end
                    // HTML // begin [file="/html/toolbar/global_nav.jsp";from=(12,37);to=(21,9)]
                      out.write("\r\n\r\n<head>\r\n  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n  <meta http-equiv=\"Content-Style-Type\" content=\"text/css\">\r\n  <meta http-equiv=\"Pragma\" content=\"no-cache\">\r\n  <meta http-equiv=\"Cache-Control\" content=\"no-cache\">\r\n  <meta http-equiv=\"expires\" content=\"0\">\r\n  <link rel=stylesheet type=\"text/css\" href=\"/UTC/css/dynamic.css\">\r\n  <title>");

                    // end
                    // ##DEBUG## ##TAGLIB## "/html/toolbar/global_nav.jsp" 22,9-"/html/toolbar/global_nav.jsp" 22,39
                      _jspx_th_utc_resource_0.setPageContext(pageContext);
                      _jspx_th_utc_resource_0.setParent(null);
                      _jspx_th_utc_resource_0.setKey("utcTitle");
                      _jspxTagObjects.push(_jspx_th_utc_resource_0);
                        int _jspx_eval_utc_resource_0 = _jspx_th_utc_resource_0.doStartTag();
                      // end
                      // ##DEBUG## "/html/toolbar/global_nav.jsp" 22,9-"/html/toolbar/global_nav.jsp" 22,39
                        if (_jspx_th_utc_resource_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                          return;
                      ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                    // end
                    // HTML // begin [file="/html/toolbar/global_nav.jsp";from=(21,39);to=(31,61)]
                      out.write("</title>\r\n</head>\r\n\r\n<body id=\"globalnavmargin\">\r\n<!-- Global Navigation -->\r\n\r\n<div id=\"globalnavborder\">\r\n<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\r\n<tr>\r\n<td valign=\"middle\" align=\"center\" width=\"25\" height=\"16\"><img src=\"/UTC/images/testClient.gif\" width=\"16\" height=\"16\" border=\"0\"></td>\r\n<td valign=\"middle\" align=\"left\" height=\"22\" class=\"toolbar\">");

                    // end
                    // ##DEBUG## ##TAGLIB## "/html/toolbar/global_nav.jsp" 32,61-"/html/toolbar/global_nav.jsp" 32,91
                      /* ------  utc:resource ------ */
                      com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_1 = new com.ibm.etools.utc.ResourceTag();
                      _jspx_th_utc_resource_1.setPageContext(pageContext);
                      _jspx_th_utc_resource_1.setParent(null);
                      _jspx_th_utc_resource_1.setKey("utcTitle");
                      _jspxTagObjects.push(_jspx_th_utc_resource_1);
                        int _jspx_eval_utc_resource_1 = _jspx_th_utc_resource_1.doStartTag();
                      // end
                      // ##DEBUG## "/html/toolbar/global_nav.jsp" 32,61-"/html/toolbar/global_nav.jsp" 32,91
                        if (_jspx_th_utc_resource_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                          return;
                      ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                    // end
                    // HTML // begin [file="/html/toolbar/global_nav.jsp";from=(31,91);to=(34,141)]
                      out.write("</td>\r\n<td valign=\"middle\" align=\"center\" width=\"*\" height=\"23\">&nbsp;</td>\r\n<td valign=\"middle\" align=\"center\" width=\"25\" height=\"16\">\r\n  <a href=\"/UTC/html/homePage/homeFrame.jsp\" target=\"main\"><img src=\"/UTC/images/epage/page_home.gif\" width=\"16\" height=\"16\" border=\"0\" alt=\"");

                    // end
                    // ##DEBUG## ##TAGLIB## "/html/toolbar/global_nav.jsp" 35,141-"/html/toolbar/global_nav.jsp" 35,176
                      /* ------  utc:resource ------ */
                      com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_2 = new com.ibm.etools.utc.ResourceTag();
                      _jspx_th_utc_resource_2.setPageContext(pageContext);
                      _jspx_th_utc_resource_2.setParent(null);
                      _jspx_th_utc_resource_2.setKey("homePageTitle");
                      _jspxTagObjects.push(_jspx_th_utc_resource_2);
                        int _jspx_eval_utc_resource_2 = _jspx_th_utc_resource_2.doStartTag();
                      // end
                      // ##DEBUG## "/html/toolbar/global_nav.jsp" 35,141-"/html/toolbar/global_nav.jsp" 35,176
                        if (_jspx_th_utc_resource_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                          return;
                      ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                    // end
                    // HTML // begin [file="/html/toolbar/global_nav.jsp";from=(34,176);to=(36,141)]
                      out.write("\" onMouseOver=\"src='/UTC/images/cpage/page_home.gif'\" onMouseOut=\"src='/UTC/images/epage/page_home.gif'\"></a></td>\r\n<td valign=\"middle\" align=\"center\" width=\"25\" height=\"16\">\r\n  <a href=\"/UTC/html/jndiPage/jndiFrame.jsp\" target=\"main\"><img src=\"/UTC/images/epage/page_jndi.gif\" width=\"16\" height=\"16\" border=\"0\" alt=\"");

                    // end
                    // ##DEBUG## ##TAGLIB## "/html/toolbar/global_nav.jsp" 37,141-"/html/toolbar/global_nav.jsp" 37,182
                      /* ------  utc:resource ------ */
                      com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_3 = new com.ibm.etools.utc.ResourceTag();
                      _jspx_th_utc_resource_3.setPageContext(pageContext);
                      _jspx_th_utc_resource_3.setParent(null);
                      _jspx_th_utc_resource_3.setKey("jndiLookupPageTitle");
                      _jspxTagObjects.push(_jspx_th_utc_resource_3);
                        int _jspx_eval_utc_resource_3 = _jspx_th_utc_resource_3.doStartTag();
                      // end
                      // ##DEBUG## "/html/toolbar/global_nav.jsp" 37,141-"/html/toolbar/global_nav.jsp" 37,182
                        if (_jspx_th_utc_resource_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                          return;
                      ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                    // end
                    // HTML // begin [file="/html/toolbar/global_nav.jsp";from=(36,182);to=(38,138)]
                      out.write("\" onMouseOver=\"src='/UTC/images/cpage/page_jndi.gif'\" onMouseOut=\"src='/UTC/images/epage/page_jndi.gif'\"></a></td>\r\n<td valign=\"middle\" align=\"center\" width=\"25\" height=\"16\">\r\n  <a href=\"/UTC/html/ejbPage/ejbFrame.jsp\" target=\"main\"><img src=\"/UTC/images/epage/page_ejb.gif\" width=\"16\" height=\"16\" border=\"0\" alt=\"");

                    // end
                    // ##DEBUG## ##TAGLIB## "/html/toolbar/global_nav.jsp" 39,138-"/html/toolbar/global_nav.jsp" 39,172
                      /* ------  utc:resource ------ */
                      com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_4 = new com.ibm.etools.utc.ResourceTag();
                      _jspx_th_utc_resource_4.setPageContext(pageContext);
                      _jspx_th_utc_resource_4.setParent(null);
                      _jspx_th_utc_resource_4.setKey("ejbPageTitle");
                      _jspxTagObjects.push(_jspx_th_utc_resource_4);
                        int _jspx_eval_utc_resource_4 = _jspx_th_utc_resource_4.doStartTag();
                      // end
                      // ##DEBUG## "/html/toolbar/global_nav.jsp" 39,138-"/html/toolbar/global_nav.jsp" 39,172
                        if (_jspx_th_utc_resource_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                          return;
                      ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                    // end
                    // HTML // begin [file="/html/toolbar/global_nav.jsp";from=(38,172);to=(40,148)]
                      out.write("\" onMouseOver=\"src='/UTC/images/cpage/page_ejb.gif'\" onMouseOut=\"src='/UTC/images/epage/page_ejb.gif'\"></a></td>\r\n<td valign=\"middle\" align=\"center\" width=\"25\" height=\"16\">\r\n  <a href=\"/UTC/html/propertiesPage/propertiesFrame.jsp\" target=\"main\"><img src=\"/UTC/images/epage/page_properties.gif\" width=\"16\" height=\"16\" alt=\"");

                    // end
                    // ##DEBUG## ##TAGLIB## "/html/toolbar/global_nav.jsp" 41,148-"/html/toolbar/global_nav.jsp" 41,189
                      /* ------  utc:resource ------ */
                      com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_5 = new com.ibm.etools.utc.ResourceTag();
                      _jspx_th_utc_resource_5.setPageContext(pageContext);
                      _jspx_th_utc_resource_5.setParent(null);
                      _jspx_th_utc_resource_5.setKey("propertiesPageTitle");
                      _jspxTagObjects.push(_jspx_th_utc_resource_5);
                        int _jspx_eval_utc_resource_5 = _jspx_th_utc_resource_5.doStartTag();
                      // end
                      // ##DEBUG## "/html/toolbar/global_nav.jsp" 41,148-"/html/toolbar/global_nav.jsp" 41,189
                        if (_jspx_th_utc_resource_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                          return;
                      ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                    // end
                    // HTML // begin [file="/html/toolbar/global_nav.jsp";from=(40,189);to=(42,130)]
                      out.write("\" border=\"0\" onMouseOver=\"src='/UTC/images/cpage/page_properties.gif'\" onMouseOut=\"src='/UTC/images/epage/page_properties.gif'\"></a></td>\r\n<td valign=\"middle\" align=\"center\" width=\"25\" height=\"16\">\r\n  <a href=\"/UTC/html/helpPage/helpFrame.jsp\" target=\"main\"><img src=\"/UTC/images/epage/page_help.gif\" width=\"16\" height=\"16\" alt=\"");

                    // end
                    // ##DEBUG## ##TAGLIB## "/html/toolbar/global_nav.jsp" 43,130-"/html/toolbar/global_nav.jsp" 43,165
                      /* ------  utc:resource ------ */
                      com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_6 = new com.ibm.etools.utc.ResourceTag();
                      _jspx_th_utc_resource_6.setPageContext(pageContext);
                      _jspx_th_utc_resource_6.setParent(null);
                      _jspx_th_utc_resource_6.setKey("helpPageTitle");
                      _jspxTagObjects.push(_jspx_th_utc_resource_6);
                        int _jspx_eval_utc_resource_6 = _jspx_th_utc_resource_6.doStartTag();
                      // end
                      // ##DEBUG## "/html/toolbar/global_nav.jsp" 43,130-"/html/toolbar/global_nav.jsp" 43,165
                        if (_jspx_th_utc_resource_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                          return;
                      ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                    // end
                    // HTML // begin [file="/html/toolbar/global_nav.jsp";from=(42,165);to=(49,7)]
                      out.write("\" border=\"0\" onMouseOver=\"src='/UTC/images/cpage/page_help.gif'\" onMouseOut=\"src='/UTC/images/epage/page_help.gif'\"></a></td>\r\n<td valign=\"middle\" align=\"center\" width=\"10\" height=\"23\"></td>\r\n</tr>\r\n</table>\r\n</div>\r\n\r\n</body>\r\n</html>");

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
