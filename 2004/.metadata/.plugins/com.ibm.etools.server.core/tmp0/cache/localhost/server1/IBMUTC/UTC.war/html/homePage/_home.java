package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

/* File Declaration Phase */

public class _home extends com.ibm.ws.webcontainer.jsp.runtime.HttpJspBase {

  /* Class Declaration Phase */

  static {
    /* Static Initializer Phase */
  }

  public _home( ) {
  }

  private static boolean _jspx_inited = false;

  private static String _jspx_debug_jspSourceLocation = "/html/homePage/home.jsp";

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

                        // HTML // begin [file="/html/homePage/home.jsp";from=(0,0);to=(9,0)]
                          out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\r\n\r\n<!--\r\n Licensed Material - Property of IBM \r\n (C) Copyright IBM Corp. 2001 - All Rights Reserved. \r\n US Government Users Restricted Rights - Use, duplication or disclosure \r\n restricted by GSA ADP Schedule Contract with IBM Corp. \r\n-->\r\n\r\n");

                        // end
                        // HTML // begin [file="/html/homePage/home.jsp";from=(9,73);to=(10,0)]
                          out.write("\r\n");

                        // end
                        // HTML // begin [file="/html/homePage/home.jsp";from=(10,37);to=(20,9)]
                          out.write("\r\n\r\n<html>\r\n<head>\r\n  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n  <meta http-equiv=\"Content-Style-Type\" content=\"text/css\">\r\n  <meta http-equiv=\"Pragma\" content=\"no-cache\">\r\n  <meta http-equiv=\"Cache-Control\" content=\"no-cache\">\r\n  <meta http-equiv=\"expires\" content=\"0\">\r\n  <link rel=stylesheet type=\"text/css\" href=\"/UTC/css/dynamic.css\">\r\n  <title>");

                        // end
                        // ##DEBUG## ##TAGLIB## "/html/homePage/home.jsp" 21,9-"/html/homePage/home.jsp" 21,44
                          _jspx_th_utc_resource_0.setPageContext(pageContext);
                          _jspx_th_utc_resource_0.setParent(null);
                          _jspx_th_utc_resource_0.setKey("homePageTitle");
                          _jspxTagObjects.push(_jspx_th_utc_resource_0);
                            int _jspx_eval_utc_resource_0 = _jspx_th_utc_resource_0.doStartTag();
                          // end
                          // ##DEBUG## "/html/homePage/home.jsp" 21,9-"/html/homePage/home.jsp" 21,44
                            if (_jspx_th_utc_resource_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                              return;
                          ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                        // end
                        // HTML // begin [file="/html/homePage/home.jsp";from=(20,44);to=(28,153)]
                          out.write("</title>\r\n</head>\r\n\r\n<body>\r\n\r\n<div class=home>\r\n<table cellpadding=4 cellspacing=1 border=0 width=100%>\r\n<tr>\r\n<td class=home><a href=\"/UTC/html/jndiPage/jndiFrame.jsp\" target=\"main\"><img src=\"/UTC/images/lpage/page_jndi.gif\" width=\"32\" height=\"32\" border=\"0\"><br>");

                        // end
                        // ##DEBUG## ##TAGLIB## "/html/homePage/home.jsp" 29,153-"/html/homePage/home.jsp" 29,194
                          /* ------  utc:resource ------ */
                          com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_1 = new com.ibm.etools.utc.ResourceTag();
                          _jspx_th_utc_resource_1.setPageContext(pageContext);
                          _jspx_th_utc_resource_1.setParent(null);
                          _jspx_th_utc_resource_1.setKey("jndiLookupPageTitle");
                          _jspxTagObjects.push(_jspx_th_utc_resource_1);
                            int _jspx_eval_utc_resource_1 = _jspx_th_utc_resource_1.doStartTag();
                          // end
                          // ##DEBUG## "/html/homePage/home.jsp" 29,153-"/html/homePage/home.jsp" 29,194
                            if (_jspx_th_utc_resource_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                              return;
                          ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                        // end
                        // HTML // begin [file="/html/homePage/home.jsp";from=(28,194);to=(29,19)]
                          out.write("</a></td>\r\n<td class=homeinfo>");

                        // end
                        // ##DEBUG## ##TAGLIB## "/html/homePage/home.jsp" 30,19-"/html/homePage/home.jsp" 30,66
                          /* ------  utc:resource ------ */
                          com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_2 = new com.ibm.etools.utc.ResourceTag();
                          _jspx_th_utc_resource_2.setPageContext(pageContext);
                          _jspx_th_utc_resource_2.setParent(null);
                          _jspx_th_utc_resource_2.setKey("jndiLookupPageDescription");
                          _jspxTagObjects.push(_jspx_th_utc_resource_2);
                            int _jspx_eval_utc_resource_2 = _jspx_th_utc_resource_2.doStartTag();
                          // end
                          // ##DEBUG## "/html/homePage/home.jsp" 30,19-"/html/homePage/home.jsp" 30,66
                            if (_jspx_th_utc_resource_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                              return;
                          ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                        // end
                        // HTML // begin [file="/html/homePage/home.jsp";from=(29,66);to=(31,150)]
                          out.write("</td>\r\n<td width=10></td>\r\n<td class=home><a href=\"/UTC/html/ejbPage/ejbFrame.jsp\" target=\"main\"><img src=\"/UTC/images/lpage/page_ejb.gif\" width=\"32\" height=\"32\" border=\"0\"><br>");

                        // end
                        // ##DEBUG## ##TAGLIB## "/html/homePage/home.jsp" 32,150-"/html/homePage/home.jsp" 32,184
                          /* ------  utc:resource ------ */
                          com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_3 = new com.ibm.etools.utc.ResourceTag();
                          _jspx_th_utc_resource_3.setPageContext(pageContext);
                          _jspx_th_utc_resource_3.setParent(null);
                          _jspx_th_utc_resource_3.setKey("ejbPageTitle");
                          _jspxTagObjects.push(_jspx_th_utc_resource_3);
                            int _jspx_eval_utc_resource_3 = _jspx_th_utc_resource_3.doStartTag();
                          // end
                          // ##DEBUG## "/html/homePage/home.jsp" 32,150-"/html/homePage/home.jsp" 32,184
                            if (_jspx_th_utc_resource_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                              return;
                          ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                        // end
                        // HTML // begin [file="/html/homePage/home.jsp";from=(31,184);to=(32,19)]
                          out.write("</a></td>\r\n<td class=homeinfo>");

                        // end
                        // ##DEBUG## ##TAGLIB## "/html/homePage/home.jsp" 33,19-"/html/homePage/home.jsp" 33,59
                          /* ------  utc:resource ------ */
                          com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_4 = new com.ibm.etools.utc.ResourceTag();
                          _jspx_th_utc_resource_4.setPageContext(pageContext);
                          _jspx_th_utc_resource_4.setParent(null);
                          _jspx_th_utc_resource_4.setKey("ejbPageDescription");
                          _jspxTagObjects.push(_jspx_th_utc_resource_4);
                            int _jspx_eval_utc_resource_4 = _jspx_th_utc_resource_4.doStartTag();
                          // end
                          // ##DEBUG## "/html/homePage/home.jsp" 33,19-"/html/homePage/home.jsp" 33,59
                            if (_jspx_th_utc_resource_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                              return;
                          ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                        // end
                        // HTML // begin [file="/html/homePage/home.jsp";from=(32,59);to=(36,171)]
                          out.write("</td>\r\n</tr>\r\n<tr height=15><td></td></tr>\r\n<tr>\r\n<td class=home><a href=\"/UTC/html/propertiesPage/propertiesFrame.jsp\" target=\"main\"><img src=\"/UTC/images/lpage/page_properties.gif\" width=\"32\" height=\"32\" border=\"0\"><br>");

                        // end
                        // ##DEBUG## ##TAGLIB## "/html/homePage/home.jsp" 37,171-"/html/homePage/home.jsp" 37,212
                          /* ------  utc:resource ------ */
                          com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_5 = new com.ibm.etools.utc.ResourceTag();
                          _jspx_th_utc_resource_5.setPageContext(pageContext);
                          _jspx_th_utc_resource_5.setParent(null);
                          _jspx_th_utc_resource_5.setKey("propertiesPageTitle");
                          _jspxTagObjects.push(_jspx_th_utc_resource_5);
                            int _jspx_eval_utc_resource_5 = _jspx_th_utc_resource_5.doStartTag();
                          // end
                          // ##DEBUG## "/html/homePage/home.jsp" 37,171-"/html/homePage/home.jsp" 37,212
                            if (_jspx_th_utc_resource_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                              return;
                          ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                        // end
                        // HTML // begin [file="/html/homePage/home.jsp";from=(36,212);to=(37,19)]
                          out.write("</a></td>\r\n<td class=homeinfo>");

                        // end
                        // ##DEBUG## ##TAGLIB## "/html/homePage/home.jsp" 38,19-"/html/homePage/home.jsp" 38,66
                          /* ------  utc:resource ------ */
                          com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_6 = new com.ibm.etools.utc.ResourceTag();
                          _jspx_th_utc_resource_6.setPageContext(pageContext);
                          _jspx_th_utc_resource_6.setParent(null);
                          _jspx_th_utc_resource_6.setKey("propertiesPageDescription");
                          _jspxTagObjects.push(_jspx_th_utc_resource_6);
                            int _jspx_eval_utc_resource_6 = _jspx_th_utc_resource_6.doStartTag();
                          // end
                          // ##DEBUG## "/html/homePage/home.jsp" 38,19-"/html/homePage/home.jsp" 38,66
                            if (_jspx_th_utc_resource_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                              return;
                          ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                        // end
                        // HTML // begin [file="/html/homePage/home.jsp";from=(37,66);to=(39,153)]
                          out.write("</td>\r\n<td></td>\r\n<td class=home><a href=\"/UTC/html/helpPage/helpFrame.jsp\" target=\"main\"><img src=\"/UTC/images/lpage/page_help.gif\" width=\"32\" height=\"32\" border=\"0\"><br>");

                        // end
                        // ##DEBUG## ##TAGLIB## "/html/homePage/home.jsp" 40,153-"/html/homePage/home.jsp" 40,188
                          /* ------  utc:resource ------ */
                          com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_7 = new com.ibm.etools.utc.ResourceTag();
                          _jspx_th_utc_resource_7.setPageContext(pageContext);
                          _jspx_th_utc_resource_7.setParent(null);
                          _jspx_th_utc_resource_7.setKey("helpPageTitle");
                          _jspxTagObjects.push(_jspx_th_utc_resource_7);
                            int _jspx_eval_utc_resource_7 = _jspx_th_utc_resource_7.doStartTag();
                          // end
                          // ##DEBUG## "/html/homePage/home.jsp" 40,153-"/html/homePage/home.jsp" 40,188
                            if (_jspx_th_utc_resource_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                              return;
                          ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                        // end
                        // HTML // begin [file="/html/homePage/home.jsp";from=(39,188);to=(40,19)]
                          out.write("</a></td>\r\n<td class=homeinfo>");

                        // end
                        // ##DEBUG## ##TAGLIB## "/html/homePage/home.jsp" 41,19-"/html/homePage/home.jsp" 41,60
                          /* ------  utc:resource ------ */
                          com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_8 = new com.ibm.etools.utc.ResourceTag();
                          _jspx_th_utc_resource_8.setPageContext(pageContext);
                          _jspx_th_utc_resource_8.setParent(null);
                          _jspx_th_utc_resource_8.setKey("helpPageDescription");
                          _jspxTagObjects.push(_jspx_th_utc_resource_8);
                            int _jspx_eval_utc_resource_8 = _jspx_th_utc_resource_8.doStartTag();
                          // end
                          // ##DEBUG## "/html/homePage/home.jsp" 41,19-"/html/homePage/home.jsp" 41,60
                            if (_jspx_th_utc_resource_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                              return;
                          ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                        // end
                        // HTML // begin [file="/html/homePage/home.jsp";from=(40,60);to=(45,7)]
                          out.write("</td>\r\n</tr>\r\n</table>\r\n</div>\r\n</body>\r\n</html>");

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
