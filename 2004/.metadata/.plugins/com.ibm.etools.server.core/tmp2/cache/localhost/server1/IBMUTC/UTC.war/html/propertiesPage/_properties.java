package org.apache.jsp;

import java.util.Properties;
import java.util.Iterator;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

/* File Declaration Phase */

public class _properties extends com.ibm.ws.webcontainer.jsp.runtime.HttpJspBase {

  /* Class Declaration Phase */

  static {
    /* Static Initializer Phase */
  }

  public _properties( ) {
  }

  private static boolean _jspx_inited = false;

  private static String _jspx_debug_jspSourceLocation = "/html/propertiesPage/properties.jsp";

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

                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(0,0);to=(9,0)]
                                  out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\r\n\r\n<!--\r\n Licensed Material - Property of IBM \r\n (C) Copyright IBM Corp. 2001 - All Rights Reserved. \r\n US Government Users Restricted Rights - Use, duplication or disclosure \r\n restricted by GSA ADP Schedule Contract with IBM Corp. \r\n-->\r\n\r\n");

                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(9,122);to=(10,0)]
                                  out.write("\r\n");

                                // end
                                // ##DEBUG## "/html/propertiesPage/properties.jsp" 11,0-"/html/propertiesPage/properties.jsp" 11,85
                                  com.ibm.etools.utc.view.JNDIInfo jndiInfo = null;
                                  boolean _jspx_specialjndiInfo  = false;
                                   synchronized (session) {
                                    jndiInfo= (com.ibm.etools.utc.view.JNDIInfo)
                                    pageContext.getAttribute("jndiInfo",PageContext.SESSION_SCOPE);
                                    if ( jndiInfo == null ) {
                                      _jspx_specialjndiInfo = true;
                                      try {
                                        jndiInfo = (com.ibm.etools.utc.view.JNDIInfo) java.beans.Beans.instantiate(this.getClass().getClassLoader(), "com.ibm.etools.utc.view.JNDIInfo");
                                      } catch (ClassNotFoundException exc) {
                                         throw new InstantiationException(exc.getMessage());
                                      } catch (Exception exc) {
                                         throw new ServletException (" Cannot create bean of class "+"com.ibm.etools.utc.view.JNDIInfo", exc);
                                      }
                                      pageContext.setAttribute("jndiInfo", jndiInfo, PageContext.SESSION_SCOPE);
                                    }
                                   } 
                                  if(_jspx_specialjndiInfo == true) {
                                // end
                                // ##DEBUG## "/html/propertiesPage/properties.jsp" 11,0-"/html/propertiesPage/properties.jsp" 11,85
                                  }
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(10,85);to=(11,0)]
                                  out.write("\r\n");

                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(11,37);to=(21,9)]
                                  out.write("\r\n\r\n<html>\r\n<head>\r\n  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n  <meta http-equiv=\"Content-Style-Type\" content=\"text/css\">\r\n  <meta http-equiv=\"Pragma\" content=\"no-cache\">\r\n  <meta http-equiv=\"Cache-Control\" content=\"no-cache\">\r\n  <meta http-equiv=\"expires\" content=\"0\">\r\n  <link rel=stylesheet type=\"text/css\" href=\"/UTC/css/dynamic.css\">\r\n  <title>");

                                // end
                                // ##DEBUG## ##TAGLIB## "/html/propertiesPage/properties.jsp" 22,9-"/html/propertiesPage/properties.jsp" 22,50
                                  _jspx_th_utc_resource_0.setPageContext(pageContext);
                                  _jspx_th_utc_resource_0.setParent(null);
                                  _jspx_th_utc_resource_0.setKey("propertiesPageTitle");
                                  _jspxTagObjects.push(_jspx_th_utc_resource_0);
                                    int _jspx_eval_utc_resource_0 = _jspx_th_utc_resource_0.doStartTag();
                                  // end
                                  // ##DEBUG## "/html/propertiesPage/properties.jsp" 22,9-"/html/propertiesPage/properties.jsp" 22,50
                                    if (_jspx_th_utc_resource_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                      return;
                                  ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(21,50);to=(29,42)]
                                  out.write("</title>\r\n</head>\r\n\r\n<body>\r\n\r\n<div class=\"properties\">\r\n<table cellspacing=0 cellpadding=4 border=0 width=100%>\r\n<form action=\"/UTC/jndiProperties\" method=\"post\" enctype=\"multipart/form-data\">\r\n<input type=\"hidden\" name=\"random\" value=\"");

                                // end
                                // ##DEBUG## "/html/propertiesPage/properties.jsp" 30,45-"/html/propertiesPage/properties.jsp" 30,60
                                  out.print( Math.random() );
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(29,62);to=(32,34)]
                                  out.write("\"/>\r\n<input type=\"hidden\" name=\"action\" value=\"set\"/>\r\n<tr valign=\"top\">\r\n  <td nowrap><label for=\"factory\">");

                                // end
                                // ##DEBUG## ##TAGLIB## "/html/propertiesPage/properties.jsp" 33,34-"/html/propertiesPage/properties.jsp" 33,84
                                  /* ------  utc:resource ------ */
                                  com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_1 = new com.ibm.etools.utc.ResourceTag();
                                  _jspx_th_utc_resource_1.setPageContext(pageContext);
                                  _jspx_th_utc_resource_1.setParent(null);
                                  _jspx_th_utc_resource_1.setKey("propertiesPageInitialFactory");
                                  _jspxTagObjects.push(_jspx_th_utc_resource_1);
                                    int _jspx_eval_utc_resource_1 = _jspx_th_utc_resource_1.doStartTag();
                                  // end
                                  // ##DEBUG## "/html/propertiesPage/properties.jsp" 33,34-"/html/propertiesPage/properties.jsp" 33,84
                                    if (_jspx_th_utc_resource_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                      return;
                                  ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(32,84);to=(33,95)]
                                  out.write("</label>&nbsp;<span class=\"info\">(INITIAL_CONTEXT_FACTORY)</span></td>\r\n  <td width=55%><input type=\"text\" id=\"factory\" class=\"textenter\" name=\"initialFactory\" value=\"");

                                // end
                                // ##DEBUG## "/html/propertiesPage/properties.jsp" 34,98-"/html/propertiesPage/properties.jsp" 34,128
                                  out.print( jndiInfo.getInitialFactory() );
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(33,130);to=(37,30)]
                                  out.write("\" size=50/></td>\r\n  <td></td>\r\n</tr>\r\n<tr>\r\n  <td nowrap><label for=\"url\">");

                                // end
                                // ##DEBUG## ##TAGLIB## "/html/propertiesPage/properties.jsp" 38,30-"/html/propertiesPage/properties.jsp" 38,77
                                  /* ------  utc:resource ------ */
                                  com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_2 = new com.ibm.etools.utc.ResourceTag();
                                  _jspx_th_utc_resource_2.setPageContext(pageContext);
                                  _jspx_th_utc_resource_2.setParent(null);
                                  _jspx_th_utc_resource_2.setKey("propertiesPageProviderURL");
                                  _jspxTagObjects.push(_jspx_th_utc_resource_2);
                                    int _jspx_eval_utc_resource_2 = _jspx_th_utc_resource_2.doStartTag();
                                  // end
                                  // ##DEBUG## "/html/propertiesPage/properties.jsp" 38,30-"/html/propertiesPage/properties.jsp" 38,77
                                    if (_jspx_th_utc_resource_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                      return;
                                  ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(37,77);to=(38,78)]
                                  out.write("</label>&nbsp;<span class=\"info\">(PROVIDER_URL)</span></td>\r\n  <td><input type=\"text\" id=\"url\" class=\"textenter\" name=\"providerURL\" value=\"");

                                // end
                                // ##DEBUG## "/html/propertiesPage/properties.jsp" 39,81-"/html/propertiesPage/properties.jsp" 39,108
                                  out.print( jndiInfo.getProviderURL() );
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(38,110);to=(42,31)]
                                  out.write("\" size=50/></td>\r\n  <td></td>\r\n</tr>\r\n<tr>\r\n  <td nowrap><label for=\"user\">");

                                // end
                                // ##DEBUG## ##TAGLIB## "/html/propertiesPage/properties.jsp" 43,31-"/html/propertiesPage/properties.jsp" 43,71
                                  /* ------  utc:resource ------ */
                                  com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_3 = new com.ibm.etools.utc.ResourceTag();
                                  _jspx_th_utc_resource_3.setPageContext(pageContext);
                                  _jspx_th_utc_resource_3.setParent(null);
                                  _jspx_th_utc_resource_3.setKey("propertiesPageUser");
                                  _jspxTagObjects.push(_jspx_th_utc_resource_3);
                                    int _jspx_eval_utc_resource_3 = _jspx_th_utc_resource_3.doStartTag();
                                  // end
                                  // ##DEBUG## "/html/propertiesPage/properties.jsp" 43,31-"/html/propertiesPage/properties.jsp" 43,71
                                    if (_jspx_th_utc_resource_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                      return;
                                  ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(42,71);to=(43,72)]
                                  out.write("</label>&nbsp;<span class=\"info\">(SECURITY_PRINCIPAL)</span></td>\r\n  <td><input type=\"text\" id=\"user\" class=\"textenter\" name=\"user\" value=\"");

                                // end
                                // ##DEBUG## "/html/propertiesPage/properties.jsp" 44,75-"/html/propertiesPage/properties.jsp" 44,95
                                  out.print( jndiInfo.getUser() );
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(43,97);to=(47,35)]
                                  out.write("\" size=30/></td>\r\n  <td></td>\r\n</tr>\r\n<tr>\r\n  <td nowrap><label for=\"password\">");

                                // end
                                // ##DEBUG## ##TAGLIB## "/html/propertiesPage/properties.jsp" 48,35-"/html/propertiesPage/properties.jsp" 48,79
                                  /* ------  utc:resource ------ */
                                  com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_4 = new com.ibm.etools.utc.ResourceTag();
                                  _jspx_th_utc_resource_4.setPageContext(pageContext);
                                  _jspx_th_utc_resource_4.setParent(null);
                                  _jspx_th_utc_resource_4.setKey("propertiesPagePassword");
                                  _jspxTagObjects.push(_jspx_th_utc_resource_4);
                                    int _jspx_eval_utc_resource_4 = _jspx_th_utc_resource_4.doStartTag();
                                  // end
                                  // ##DEBUG## "/html/propertiesPage/properties.jsp" 48,35-"/html/propertiesPage/properties.jsp" 48,79
                                    if (_jspx_th_utc_resource_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                      return;
                                  ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(47,79);to=(48,84)]
                                  out.write("</label>&nbsp;<span class=\"info\">(SECURITY_CREDENTIALS)</span></td>\r\n  <td><input type=\"password\" id=\"password\" class=\"textenter\" name=\"password\" value=\"");

                                // end
                                // ##DEBUG## "/html/propertiesPage/properties.jsp" 49,87-"/html/propertiesPage/properties.jsp" 49,111
                                  out.print( jndiInfo.getPassword() );
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(48,113);to=(49,32)]
                                  out.write("\" size=30/></td>\r\n  <td><input type=submit value=\"");

                                // end
                                // ##DEBUG## ##TAGLIB## "/html/propertiesPage/properties.jsp" 50,32-"/html/propertiesPage/properties.jsp" 50,71
                                  /* ------  utc:resource ------ */
                                  com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_5 = new com.ibm.etools.utc.ResourceTag();
                                  _jspx_th_utc_resource_5.setPageContext(pageContext);
                                  _jspx_th_utc_resource_5.setParent(null);
                                  _jspx_th_utc_resource_5.setKey("propertiesPageSet");
                                  _jspxTagObjects.push(_jspx_th_utc_resource_5);
                                    int _jspx_eval_utc_resource_5 = _jspx_th_utc_resource_5.doStartTag();
                                  // end
                                  // ##DEBUG## "/html/propertiesPage/properties.jsp" 50,32-"/html/propertiesPage/properties.jsp" 50,71
                                    if (_jspx_th_utc_resource_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                      return;
                                  ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(49,71);to=(52,0)]
                                  out.write("\" class=\"button\"/></td>\r\n</tr>\r\n</form>\r\n");

                                // end
                                // ##DEBUG## ##SCRIPTLET## "/html/propertiesPage/properties.jsp" 53,2-"/html/propertiesPage/properties.jsp" 54,25
                                   String message = jndiInfo.getMessage();
                                    if (message != null) { 
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(53,27);to=(55,6)]
                                  out.write("\r\n<tr>\r\n  <td>");

                                // end
                                // ##DEBUG## ##TAGLIB## "/html/propertiesPage/properties.jsp" 56,6-"/html/propertiesPage/properties.jsp" 56,48
                                  /* ------  utc:resource ------ */
                                  com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_6 = new com.ibm.etools.utc.ResourceTag();
                                  _jspx_th_utc_resource_6.setPageContext(pageContext);
                                  _jspx_th_utc_resource_6.setParent(null);
                                  _jspx_th_utc_resource_6.setKey("propertiesPageStatus");
                                  _jspxTagObjects.push(_jspx_th_utc_resource_6);
                                    int _jspx_eval_utc_resource_6 = _jspx_th_utc_resource_6.doStartTag();
                                  // end
                                  // ##DEBUG## "/html/propertiesPage/properties.jsp" 56,6-"/html/propertiesPage/properties.jsp" 56,48
                                    if (_jspx_th_utc_resource_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                      return;
                                  ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(55,48);to=(58,16)]
                                  out.write("</td>\r\n</tr>\r\n<tr>\r\n  <td colspan=3>");

                                // end
                                // ##DEBUG## "/html/propertiesPage/properties.jsp" 59,19-"/html/propertiesPage/properties.jsp" 59,28
                                  out.print( message );
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(58,30);to=(60,0)]
                                  out.write("</td>\r\n</tr>\r\n");

                                // end
                                // ##DEBUG## ##SCRIPTLET## "/html/propertiesPage/properties.jsp" 61,2-"/html/propertiesPage/properties.jsp" 61,5
                                   } 
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(60,7);to=(65,18)]
                                  out.write("\r\n\r\n\r\n<tr><td height=30></td></tr>\r\n<tr>\r\n  <td colspan=\"3\">");

                                // end
                                // ##DEBUG## ##TAGLIB## "/html/propertiesPage/properties.jsp" 66,18-"/html/propertiesPage/properties.jsp" 66,74
                                  /* ------  utc:resource ------ */
                                  com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_7 = new com.ibm.etools.utc.ResourceTag();
                                  _jspx_th_utc_resource_7.setPageContext(pageContext);
                                  _jspx_th_utc_resource_7.setParent(null);
                                  _jspx_th_utc_resource_7.setKey("propertiesPageAdditionalProperties");
                                  _jspxTagObjects.push(_jspx_th_utc_resource_7);
                                    int _jspx_eval_utc_resource_7 = _jspx_th_utc_resource_7.doStartTag();
                                  // end
                                  // ##DEBUG## "/html/propertiesPage/properties.jsp" 66,18-"/html/propertiesPage/properties.jsp" 66,74
                                    if (_jspx_th_utc_resource_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                      return;
                                  ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(65,74);to=(73,44)]
                                  out.write("</td>\r\n</tr>\r\n<tr>\r\n  <td colspan=\"3\" valign=\"middle\" height=\"15\"><img src=\"/UTC/images/line.gif\" width=\"100%\" height=\"2\"></td>\r\n</tr>\r\n\r\n<tr>\r\n  <form action=\"/UTC/jndiProperties\" method=\"post\" enctype=\"multipart/form-data\">\r\n  <input type=\"hidden\" name=\"random\" value=\"");

                                // end
                                // ##DEBUG## "/html/propertiesPage/properties.jsp" 74,47-"/html/propertiesPage/properties.jsp" 74,62
                                  out.print( Math.random() );
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(73,64);to=(75,39)]
                                  out.write("\"/>\r\n  <input type=\"hidden\" name=\"action\" value=\"add\"/>\r\n  <td width=40%><label for=\"nameentry\">");

                                // end
                                // ##DEBUG## ##TAGLIB## "/html/propertiesPage/properties.jsp" 76,39-"/html/propertiesPage/properties.jsp" 76,79
                                  /* ------  utc:resource ------ */
                                  com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_8 = new com.ibm.etools.utc.ResourceTag();
                                  _jspx_th_utc_resource_8.setPageContext(pageContext);
                                  _jspx_th_utc_resource_8.setParent(null);
                                  _jspx_th_utc_resource_8.setKey("propertiesPageName");
                                  _jspxTagObjects.push(_jspx_th_utc_resource_8);
                                    int _jspx_eval_utc_resource_8 = _jspx_th_utc_resource_8.doStartTag();
                                  // end
                                  // ##DEBUG## "/html/propertiesPage/properties.jsp" 76,39-"/html/propertiesPage/properties.jsp" 76,79
                                    if (_jspx_th_utc_resource_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                      return;
                                  ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(75,79);to=(76,40)]
                                  out.write("</label>&nbsp;<input type=\"text\" id=\"nameentry\" name=\"property\" class=\"textenter2\"/></td>\r\n  <td width=40%><label for=\"valueentry\">");

                                // end
                                // ##DEBUG## ##TAGLIB## "/html/propertiesPage/properties.jsp" 77,40-"/html/propertiesPage/properties.jsp" 77,81
                                  /* ------  utc:resource ------ */
                                  com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_9 = new com.ibm.etools.utc.ResourceTag();
                                  _jspx_th_utc_resource_9.setPageContext(pageContext);
                                  _jspx_th_utc_resource_9.setParent(null);
                                  _jspx_th_utc_resource_9.setKey("propertiesPageValue");
                                  _jspxTagObjects.push(_jspx_th_utc_resource_9);
                                    int _jspx_eval_utc_resource_9 = _jspx_th_utc_resource_9.doStartTag();
                                  // end
                                  // ##DEBUG## "/html/propertiesPage/properties.jsp" 77,40-"/html/propertiesPage/properties.jsp" 77,81
                                    if (_jspx_th_utc_resource_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                      return;
                                  ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(76,81);to=(77,32)]
                                  out.write("</label>&nbsp;<input type=\"text\" id=\"valueentry\" name=\"value\" class=\"textenter2\"/></td>\r\n  <td><input type=submit value=\"");

                                // end
                                // ##DEBUG## ##TAGLIB## "/html/propertiesPage/properties.jsp" 78,32-"/html/propertiesPage/properties.jsp" 78,71
                                  /* ------  utc:resource ------ */
                                  com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_10 = new com.ibm.etools.utc.ResourceTag();
                                  _jspx_th_utc_resource_10.setPageContext(pageContext);
                                  _jspx_th_utc_resource_10.setParent(null);
                                  _jspx_th_utc_resource_10.setKey("propertiesPageAdd");
                                  _jspxTagObjects.push(_jspx_th_utc_resource_10);
                                    int _jspx_eval_utc_resource_10 = _jspx_th_utc_resource_10.doStartTag();
                                  // end
                                  // ##DEBUG## "/html/propertiesPage/properties.jsp" 78,32-"/html/propertiesPage/properties.jsp" 78,71
                                    if (_jspx_th_utc_resource_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                      return;
                                  ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(77,71);to=(81,0)]
                                  out.write("\" class=\"button\"/></td>\r\n  </form>\r\n</tr>\r\n\r\n");

                                // end
                                // ##DEBUG## ##SCRIPTLET## "/html/propertiesPage/properties.jsp" 82,2-"/html/propertiesPage/properties.jsp" 86,42
                                   Properties p = jndiInfo.getAdditionalProperties(); 
                                  Iterator iterator = p.keySet().iterator(); 
                                  while (iterator.hasNext()) {
                                    String property = (String) iterator.next();
                                    String value = p.getProperty(property); 
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(85,44);to=(89,44)]
                                  out.write("\r\n\r\n<tr>\r\n  <form action=\"/UTC/jndiProperties\" method=\"post\" enctype=\"multipart/form-data\">\r\n  <input type=\"hidden\" name=\"random\" value=\"");

                                // end
                                // ##DEBUG## "/html/propertiesPage/properties.jsp" 90,47-"/html/propertiesPage/properties.jsp" 90,62
                                  out.print( Math.random() );
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(89,64);to=(91,46)]
                                  out.write("\"/>\r\n  <input type=\"hidden\" name=\"action\" value=\"remove\"/>\r\n  <input type=\"hidden\" name=\"property\" value=\"");

                                // end
                                // ##DEBUG## "/html/propertiesPage/properties.jsp" 92,49-"/html/propertiesPage/properties.jsp" 92,59
                                  out.print( property );
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(91,61);to=(92,6)]
                                  out.write("\"/>\r\n  <td>");

                                // end
                                // ##DEBUG## "/html/propertiesPage/properties.jsp" 93,9-"/html/propertiesPage/properties.jsp" 93,19
                                  out.print( property );
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(92,21);to=(93,6)]
                                  out.write("</td>\r\n  <td>");

                                // end
                                // ##DEBUG## "/html/propertiesPage/properties.jsp" 94,9-"/html/propertiesPage/properties.jsp" 94,16
                                  out.print( value );
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(93,18);to=(94,32)]
                                  out.write("</td>\r\n  <td><input type=submit value=\"");

                                // end
                                // ##DEBUG## ##TAGLIB## "/html/propertiesPage/properties.jsp" 95,32-"/html/propertiesPage/properties.jsp" 95,74
                                  /* ------  utc:resource ------ */
                                  com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_11 = new com.ibm.etools.utc.ResourceTag();
                                  _jspx_th_utc_resource_11.setPageContext(pageContext);
                                  _jspx_th_utc_resource_11.setParent(null);
                                  _jspx_th_utc_resource_11.setKey("propertiesPageRemove");
                                  _jspxTagObjects.push(_jspx_th_utc_resource_11);
                                    int _jspx_eval_utc_resource_11 = _jspx_th_utc_resource_11.doStartTag();
                                  // end
                                  // ##DEBUG## "/html/propertiesPage/properties.jsp" 95,32-"/html/propertiesPage/properties.jsp" 95,74
                                    if (_jspx_th_utc_resource_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                      return;
                                  ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(94,74);to=(97,0)]
                                  out.write("\" class=\"button\"/></td>\r\n  </form>\r\n</tr>\r\n");

                                // end
                                // ##DEBUG## ##SCRIPTLET## "/html/propertiesPage/properties.jsp" 98,2-"/html/propertiesPage/properties.jsp" 98,5
                                   } 
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(97,7);to=(99,0)]
                                  out.write("\r\n\r\n");

                                // end
                                // ##DEBUG## ##SCRIPTLET## "/html/propertiesPage/properties.jsp" 100,2-"/html/propertiesPage/properties.jsp" 101,26
                                   String message2 = jndiInfo.getMessage2();
                                    if (message2 != null) { 
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(100,28);to=(102,6)]
                                  out.write("\r\n<tr>\r\n  <td>");

                                // end
                                // ##DEBUG## ##TAGLIB## "/html/propertiesPage/properties.jsp" 103,6-"/html/propertiesPage/properties.jsp" 103,48
                                  /* ------  utc:resource ------ */
                                  com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_12 = new com.ibm.etools.utc.ResourceTag();
                                  _jspx_th_utc_resource_12.setPageContext(pageContext);
                                  _jspx_th_utc_resource_12.setParent(null);
                                  _jspx_th_utc_resource_12.setKey("propertiesPageStatus");
                                  _jspxTagObjects.push(_jspx_th_utc_resource_12);
                                    int _jspx_eval_utc_resource_12 = _jspx_th_utc_resource_12.doStartTag();
                                  // end
                                  // ##DEBUG## "/html/propertiesPage/properties.jsp" 103,6-"/html/propertiesPage/properties.jsp" 103,48
                                    if (_jspx_th_utc_resource_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                      return;
                                  ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(102,48);to=(105,16)]
                                  out.write("</td>\r\n</tr>\r\n<tr>\r\n  <td colspan=3>");

                                // end
                                // ##DEBUG## "/html/propertiesPage/properties.jsp" 106,19-"/html/propertiesPage/properties.jsp" 106,29
                                  out.print( message2 );
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(105,31);to=(107,0)]
                                  out.write("</td>\r\n</tr>\r\n");

                                // end
                                // ##DEBUG## ##SCRIPTLET## "/html/propertiesPage/properties.jsp" 108,2-"/html/propertiesPage/properties.jsp" 108,5
                                   } 
                                // end
                                // HTML // begin [file="/html/propertiesPage/properties.jsp";from=(107,7);to=(115,0)]
                                  out.write("\r\n\r\n</table>\r\n\r\n</div>\r\n\r\n</body>\r\n</html>\r\n");

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
