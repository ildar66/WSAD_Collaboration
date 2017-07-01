package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

/* File Declaration Phase */

public class _help extends com.ibm.ws.webcontainer.jsp.runtime.HttpJspBase {

  /* Class Declaration Phase */

  static {
    /* Static Initializer Phase */
  }

  public _help( ) {
  }

  private static boolean _jspx_inited = false;

  private static String _jspx_debug_jspSourceLocation = "/html/helpPage/help.jsp";

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

                                // HTML // begin [file="/html/helpPage/help.jsp";from=(0,0);to=(9,0)]
                                  out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\r\n\r\n<!--\r\n Licensed Material - Property of IBM \r\n (C) Copyright IBM Corp. 2001 - All Rights Reserved. \r\n US Government Users Restricted Rights - Use, duplication or disclosure \r\n restricted by GSA ADP Schedule Contract with IBM Corp. \r\n-->\r\n\r\n");

                                // end
                                // HTML // begin [file="/html/helpPage/help.jsp";from=(9,73);to=(10,0)]
                                  out.write("\r\n");

                                // end
                                // HTML // begin [file="/html/helpPage/help.jsp";from=(10,37);to=(20,9)]
                                  out.write("\r\n\r\n<html>\r\n<head>\r\n  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n  <meta http-equiv=\"Content-Style-Type\" content=\"text/css\">\r\n  <meta http-equiv=\"Pragma\" content=\"no-cache\">\r\n  <meta http-equiv=\"Cache-Control\" content=\"no-cache\">\r\n  <meta http-equiv=\"expires\" content=\"0\">\r\n  <link rel=stylesheet type=\"text/css\" href=\"/UTC/css/dynamic.css\">\r\n  <title>");

                                // end
                                // ##DEBUG## ##TAGLIB## "/html/helpPage/help.jsp" 21,9-"/html/helpPage/help.jsp" 21,44
                                  _jspx_th_utc_resource_0.setPageContext(pageContext);
                                  _jspx_th_utc_resource_0.setParent(null);
                                  _jspx_th_utc_resource_0.setKey("helpPageTitle");
                                  _jspxTagObjects.push(_jspx_th_utc_resource_0);
                                    int _jspx_eval_utc_resource_0 = _jspx_th_utc_resource_0.doStartTag();
                                  // end
                                  // ##DEBUG## "/html/helpPage/help.jsp" 21,9-"/html/helpPage/help.jsp" 21,44
                                    if (_jspx_th_utc_resource_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                      return;
                                  ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                // end
                                // HTML // begin [file="/html/helpPage/help.jsp";from=(20,44);to=(30,23)]
                                  out.write("</title>\r\n</head>\r\n\r\n<body>\r\n\r\n<div class=\"help\">\r\n<table cellpadding=3 cellspacing=3 border=0>\r\n\r\n<tr valign=\"top\">\r\n  <td rowspan=3><a name=\"#homePage\"></a><img src=\"/UTC/images/lpage/page_home.gif\" width=32 height=32/></td>\r\n  <td><div class=title>");

                                // end
                                // ##DEBUG## ##TAGLIB## "/html/helpPage/help.jsp" 31,23-"/html/helpPage/help.jsp" 31,58
                                  /* ------  utc:resource ------ */
                                  com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_1 = new com.ibm.etools.utc.ResourceTag();
                                  _jspx_th_utc_resource_1.setPageContext(pageContext);
                                  _jspx_th_utc_resource_1.setParent(null);
                                  _jspx_th_utc_resource_1.setKey("homePageTitle");
                                  _jspxTagObjects.push(_jspx_th_utc_resource_1);
                                    int _jspx_eval_utc_resource_1 = _jspx_th_utc_resource_1.doStartTag();
                                  // end
                                  // ##DEBUG## "/html/helpPage/help.jsp" 31,23-"/html/helpPage/help.jsp" 31,58
                                    if (_jspx_th_utc_resource_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                      return;
                                  ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                // end
                                // HTML // begin [file="/html/helpPage/help.jsp";from=(30,58);to=(32,10)]
                                  out.write("</div></td></tr>\r\n  <tr><td bgcolor=black height=1></td></tr>\r\n  <tr><td>");

                                // end
                                // ##DEBUG## ##TAGLIB## "/html/helpPage/help.jsp" 33,10-"/html/helpPage/help.jsp" 33,44
                                  /* ------  utc:resource ------ */
                                  com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_2 = new com.ibm.etools.utc.ResourceTag();
                                  _jspx_th_utc_resource_2.setPageContext(pageContext);
                                  _jspx_th_utc_resource_2.setParent(null);
                                  _jspx_th_utc_resource_2.setKey("homePageHelp");
                                  _jspxTagObjects.push(_jspx_th_utc_resource_2);
                                    int _jspx_eval_utc_resource_2 = _jspx_th_utc_resource_2.doStartTag();
                                  // end
                                  // ##DEBUG## "/html/helpPage/help.jsp" 33,10-"/html/helpPage/help.jsp" 33,44
                                    if (_jspx_th_utc_resource_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                      return;
                                  ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                // end
                                // HTML // begin [file="/html/helpPage/help.jsp";from=(32,44);to=(38,23)]
                                  out.write("</td></tr>\r\n\r\n<tr height=15><td></td></tr>\r\n\r\n<tr valign=\"top\">\r\n  <td rowspan=3><a name=\"#jndiPage\"></a><img src=\"/UTC/images/lpage/page_jndi.gif\" width=32 height=32/></td>\r\n  <td><div class=title>");

                                // end
                                // ##DEBUG## ##TAGLIB## "/html/helpPage/help.jsp" 39,23-"/html/helpPage/help.jsp" 39,64
                                  /* ------  utc:resource ------ */
                                  com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_3 = new com.ibm.etools.utc.ResourceTag();
                                  _jspx_th_utc_resource_3.setPageContext(pageContext);
                                  _jspx_th_utc_resource_3.setParent(null);
                                  _jspx_th_utc_resource_3.setKey("jndiLookupPageTitle");
                                  _jspxTagObjects.push(_jspx_th_utc_resource_3);
                                    int _jspx_eval_utc_resource_3 = _jspx_th_utc_resource_3.doStartTag();
                                  // end
                                  // ##DEBUG## "/html/helpPage/help.jsp" 39,23-"/html/helpPage/help.jsp" 39,64
                                    if (_jspx_th_utc_resource_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                      return;
                                  ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                // end
                                // HTML // begin [file="/html/helpPage/help.jsp";from=(38,64);to=(40,10)]
                                  out.write("</div></td></tr>\r\n  <tr><td bgcolor=black height=1></td></tr>\r\n  <tr><td>");

                                // end
                                // ##DEBUG## ##TAGLIB## "/html/helpPage/help.jsp" 41,10-"/html/helpPage/help.jsp" 41,50
                                  /* ------  utc:resource ------ */
                                  com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_4 = new com.ibm.etools.utc.ResourceTag();
                                  _jspx_th_utc_resource_4.setPageContext(pageContext);
                                  _jspx_th_utc_resource_4.setParent(null);
                                  _jspx_th_utc_resource_4.setKey("jndiLookupPageHelp");
                                  _jspxTagObjects.push(_jspx_th_utc_resource_4);
                                    int _jspx_eval_utc_resource_4 = _jspx_th_utc_resource_4.doStartTag();
                                  // end
                                  // ##DEBUG## "/html/helpPage/help.jsp" 41,10-"/html/helpPage/help.jsp" 41,50
                                    if (_jspx_th_utc_resource_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                      return;
                                  ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                // end
                                // HTML // begin [file="/html/helpPage/help.jsp";from=(40,50);to=(46,23)]
                                  out.write("</td></tr>\r\n\r\n<tr height=15><td></td></tr>\r\n\r\n<tr valign=\"top\">\r\n  <td rowspan=3><a name=\"#ejbPage\"></a><img src=\"/UTC/images/lpage/page_ejb.gif\" width=32 height=32/></td>\r\n  <td><div class=title>");

                                // end
                                // ##DEBUG## ##TAGLIB## "/html/helpPage/help.jsp" 47,23-"/html/helpPage/help.jsp" 47,57
                                  /* ------  utc:resource ------ */
                                  com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_5 = new com.ibm.etools.utc.ResourceTag();
                                  _jspx_th_utc_resource_5.setPageContext(pageContext);
                                  _jspx_th_utc_resource_5.setParent(null);
                                  _jspx_th_utc_resource_5.setKey("ejbPageTitle");
                                  _jspxTagObjects.push(_jspx_th_utc_resource_5);
                                    int _jspx_eval_utc_resource_5 = _jspx_th_utc_resource_5.doStartTag();
                                  // end
                                  // ##DEBUG## "/html/helpPage/help.jsp" 47,23-"/html/helpPage/help.jsp" 47,57
                                    if (_jspx_th_utc_resource_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                      return;
                                  ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                // end
                                // HTML // begin [file="/html/helpPage/help.jsp";from=(46,57);to=(48,10)]
                                  out.write("</div></td></tr>\r\n  <tr><td bgcolor=black height=1></td></tr>\r\n  <tr><td>");

                                // end
                                // ##DEBUG## ##TAGLIB## "/html/helpPage/help.jsp" 49,10-"/html/helpPage/help.jsp" 49,43
                                  /* ------  utc:resource ------ */
                                  com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_6 = new com.ibm.etools.utc.ResourceTag();
                                  _jspx_th_utc_resource_6.setPageContext(pageContext);
                                  _jspx_th_utc_resource_6.setParent(null);
                                  _jspx_th_utc_resource_6.setKey("ejbPageHelp");
                                  _jspxTagObjects.push(_jspx_th_utc_resource_6);
                                    int _jspx_eval_utc_resource_6 = _jspx_th_utc_resource_6.doStartTag();
                                  // end
                                  // ##DEBUG## "/html/helpPage/help.jsp" 49,10-"/html/helpPage/help.jsp" 49,43
                                    if (_jspx_th_utc_resource_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                      return;
                                  ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                // end
                                // HTML // begin [file="/html/helpPage/help.jsp";from=(48,43);to=(51,2)]
                                  out.write("</p>\r\n\r\n  <a name=\"#ejbPageReferences\"></a><div class=title2><img src=\"/UTC/images/view/view_ejb_references.gif\" width=16 height=16/>\r\n  ");

                                // end
                                // ##DEBUG## ##TAGLIB## "/html/helpPage/help.jsp" 52,2-"/html/helpPage/help.jsp" 52,49
                                  /* ------  utc:resource ------ */
                                  com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_7 = new com.ibm.etools.utc.ResourceTag();
                                  _jspx_th_utc_resource_7.setPageContext(pageContext);
                                  _jspx_th_utc_resource_7.setParent(null);
                                  _jspx_th_utc_resource_7.setKey("ejbPageReferenceViewTitle");
                                  _jspxTagObjects.push(_jspx_th_utc_resource_7);
                                    int _jspx_eval_utc_resource_7 = _jspx_th_utc_resource_7.doStartTag();
                                  // end
                                  // ##DEBUG## "/html/helpPage/help.jsp" 52,2-"/html/helpPage/help.jsp" 52,49
                                    if (_jspx_th_utc_resource_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                      return;
                                  ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                // end
                                // HTML // begin [file="/html/helpPage/help.jsp";from=(51,49);to=(52,2)]
                                  out.write("</div><br>\r\n  ");

                                // end
                                // ##DEBUG## ##TAGLIB## "/html/helpPage/help.jsp" 53,2-"/html/helpPage/help.jsp" 53,49
                                  /* ------  utc:resource ------ */
                                  com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_8 = new com.ibm.etools.utc.ResourceTag();
                                  _jspx_th_utc_resource_8.setPageContext(pageContext);
                                  _jspx_th_utc_resource_8.setParent(null);
                                  _jspx_th_utc_resource_8.setKey("ejbPageReferencesViewHelp");
                                  _jspxTagObjects.push(_jspx_th_utc_resource_8);
                                    int _jspx_eval_utc_resource_8 = _jspx_th_utc_resource_8.doStartTag();
                                  // end
                                  // ##DEBUG## "/html/helpPage/help.jsp" 53,2-"/html/helpPage/help.jsp" 53,49
                                    if (_jspx_th_utc_resource_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                      return;
                                  ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                // end
                                // HTML // begin [file="/html/helpPage/help.jsp";from=(52,49);to=(55,2)]
                                  out.write("</p>\r\n\r\n  <a name=\"#ejbPageParameters\"></a><div class=title2><img src=\"/UTC/images/view/view_ejb_parameters.gif\" width=16 height=16/>\r\n  ");

                                // end
                                // ##DEBUG## ##TAGLIB## "/html/helpPage/help.jsp" 56,2-"/html/helpPage/help.jsp" 56,49
                                  /* ------  utc:resource ------ */
                                  com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_9 = new com.ibm.etools.utc.ResourceTag();
                                  _jspx_th_utc_resource_9.setPageContext(pageContext);
                                  _jspx_th_utc_resource_9.setParent(null);
                                  _jspx_th_utc_resource_9.setKey("ejbPageParameterViewTitle");
                                  _jspxTagObjects.push(_jspx_th_utc_resource_9);
                                    int _jspx_eval_utc_resource_9 = _jspx_th_utc_resource_9.doStartTag();
                                  // end
                                  // ##DEBUG## "/html/helpPage/help.jsp" 56,2-"/html/helpPage/help.jsp" 56,49
                                    if (_jspx_th_utc_resource_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                      return;
                                  ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                // end
                                // HTML // begin [file="/html/helpPage/help.jsp";from=(55,49);to=(56,2)]
                                  out.write("</div><br>\r\n  ");

                                // end
                                // ##DEBUG## ##TAGLIB## "/html/helpPage/help.jsp" 57,2-"/html/helpPage/help.jsp" 57,49
                                  /* ------  utc:resource ------ */
                                  com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_10 = new com.ibm.etools.utc.ResourceTag();
                                  _jspx_th_utc_resource_10.setPageContext(pageContext);
                                  _jspx_th_utc_resource_10.setParent(null);
                                  _jspx_th_utc_resource_10.setKey("ejbPageParametersViewHelp");
                                  _jspxTagObjects.push(_jspx_th_utc_resource_10);
                                    int _jspx_eval_utc_resource_10 = _jspx_th_utc_resource_10.doStartTag();
                                  // end
                                  // ##DEBUG## "/html/helpPage/help.jsp" 57,2-"/html/helpPage/help.jsp" 57,49
                                    if (_jspx_th_utc_resource_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                      return;
                                  ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                // end
                                // HTML // begin [file="/html/helpPage/help.jsp";from=(56,49);to=(63,23)]
                                  out.write("</p>\r\n</td></tr>\r\n\r\n<tr height=15><td></td></tr>\r\n\r\n<tr valign=\"top\">\r\n  <td rowspan=3><a name=\"propertiesPage\"></a><img src=\"/UTC/images/lpage/page_properties.gif\" width=32 height=32/></td>\r\n  <td><div class=title>");

                                // end
                                // ##DEBUG## ##TAGLIB## "/html/helpPage/help.jsp" 64,23-"/html/helpPage/help.jsp" 64,64
                                  /* ------  utc:resource ------ */
                                  com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_11 = new com.ibm.etools.utc.ResourceTag();
                                  _jspx_th_utc_resource_11.setPageContext(pageContext);
                                  _jspx_th_utc_resource_11.setParent(null);
                                  _jspx_th_utc_resource_11.setKey("propertiesPageTitle");
                                  _jspxTagObjects.push(_jspx_th_utc_resource_11);
                                    int _jspx_eval_utc_resource_11 = _jspx_th_utc_resource_11.doStartTag();
                                  // end
                                  // ##DEBUG## "/html/helpPage/help.jsp" 64,23-"/html/helpPage/help.jsp" 64,64
                                    if (_jspx_th_utc_resource_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                      return;
                                  ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                // end
                                // HTML // begin [file="/html/helpPage/help.jsp";from=(63,64);to=(65,10)]
                                  out.write("</div></td></tr>\r\n  <tr><td bgcolor=black height=1></td></tr>\r\n  <tr><td>");

                                // end
                                // ##DEBUG## ##TAGLIB## "/html/helpPage/help.jsp" 66,10-"/html/helpPage/help.jsp" 66,50
                                  /* ------  utc:resource ------ */
                                  com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_12 = new com.ibm.etools.utc.ResourceTag();
                                  _jspx_th_utc_resource_12.setPageContext(pageContext);
                                  _jspx_th_utc_resource_12.setParent(null);
                                  _jspx_th_utc_resource_12.setKey("propertiesPageHelp");
                                  _jspxTagObjects.push(_jspx_th_utc_resource_12);
                                    int _jspx_eval_utc_resource_12 = _jspx_th_utc_resource_12.doStartTag();
                                  // end
                                  // ##DEBUG## "/html/helpPage/help.jsp" 66,10-"/html/helpPage/help.jsp" 66,50
                                    if (_jspx_th_utc_resource_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                      return;
                                  ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                // end
                                // HTML // begin [file="/html/helpPage/help.jsp";from=(65,50);to=(73,7)]
                                  out.write("</td></tr></table>\r\n\r\n</div>\r\n<font style=\"font-size: 12pt; font-weight: bold; color: white;\">IBM EJB Test Client<br>\r\n<div style=\"font-size: 7pt;\"><i>by</i></div>\r\n<div style=\"font-size: 11pt;\">Tim deBoer &amp;<br>Sheldon Wosnick</div></font>\r\n\r\n</body>\r\n</html>");

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
