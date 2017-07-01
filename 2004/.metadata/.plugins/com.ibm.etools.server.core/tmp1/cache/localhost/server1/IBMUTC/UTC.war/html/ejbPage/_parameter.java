package org.apache.jsp;

import java.util.List;
import com.ibm.etools.utc.Tree;
import com.ibm.etools.utc.Resource;
import com.ibm.etools.utc.view.*;
import com.ibm.etools.utc.model.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

/* File Declaration Phase */

public class _parameter extends com.ibm.ws.webcontainer.jsp.runtime.HttpJspBase {

  /* Class Declaration Phase */

  static {
    /* Static Initializer Phase */
  }

  public _parameter( ) {
  }

  private static boolean _jspx_inited = false;

  private static String _jspx_debug_jspSourceLocation = "/html/ejbPage/parameter.jsp";

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

                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(0,0);to=(9,0)]
                                                                                              out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\r\n\r\n<!--\r\n Licensed Material - Property of IBM \r\n (C) Copyright IBM Corp. 2001 - All Rights Reserved. \r\n US Government Users Restricted Rights - Use, duplication or disclosure \r\n restricted by GSA ADP Schedule Contract with IBM Corp. \r\n-->\r\n\r\n");

                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(9,202);to=(10,0)]
                                                                                              out.write("\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 11,0-"/html/ejbPage/parameter.jsp" 11,95
                                                                                              com.ibm.etools.utc.view.ParameterView parameterView = null;
                                                                                              boolean _jspx_specialparameterView  = false;
                                                                                               synchronized (session) {
                                                                                              parameterView= (com.ibm.etools.utc.view.ParameterView)
                                                                                              pageContext.getAttribute("parameterView",PageContext.SESSION_SCOPE);
                                                                                              if ( parameterView == null ) {
                                                                                              _jspx_specialparameterView = true;
                                                                                              try {
                                                                                              parameterView = (com.ibm.etools.utc.view.ParameterView) java.beans.Beans.instantiate(this.getClass().getClassLoader(), "com.ibm.etools.utc.view.ParameterView");
                                                                                              } catch (ClassNotFoundException exc) {
                                                                                               throw new InstantiationException(exc.getMessage());
                                                                                              } catch (Exception exc) {
                                                                                               throw new ServletException (" Cannot create bean of class "+"com.ibm.etools.utc.view.ParameterView", exc);
                                                                                              }
                                                                                              pageContext.setAttribute("parameterView", parameterView, PageContext.SESSION_SCOPE);
                                                                                              }
                                                                                               } 
                                                                                              if(_jspx_specialparameterView == true) {
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 11,0-"/html/ejbPage/parameter.jsp" 11,95
                                                                                              }
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(10,95);to=(11,0)]
                                                                                              out.write("\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 12,0-"/html/ejbPage/parameter.jsp" 12,93
                                                                                              com.ibm.etools.utc.ClassModelManager modelManager = null;
                                                                                              boolean _jspx_specialmodelManager  = false;
                                                                                               synchronized (session) {
                                                                                              modelManager= (com.ibm.etools.utc.ClassModelManager)
                                                                                              pageContext.getAttribute("modelManager",PageContext.SESSION_SCOPE);
                                                                                              if ( modelManager == null ) {
                                                                                              _jspx_specialmodelManager = true;
                                                                                              try {
                                                                                              modelManager = (com.ibm.etools.utc.ClassModelManager) java.beans.Beans.instantiate(this.getClass().getClassLoader(), "com.ibm.etools.utc.ClassModelManager");
                                                                                              } catch (ClassNotFoundException exc) {
                                                                                               throw new InstantiationException(exc.getMessage());
                                                                                              } catch (Exception exc) {
                                                                                               throw new ServletException (" Cannot create bean of class "+"com.ibm.etools.utc.ClassModelManager", exc);
                                                                                              }
                                                                                              pageContext.setAttribute("modelManager", modelManager, PageContext.SESSION_SCOPE);
                                                                                              }
                                                                                               } 
                                                                                              if(_jspx_specialmodelManager == true) {
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 12,0-"/html/ejbPage/parameter.jsp" 12,93
                                                                                              }
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(11,93);to=(12,0)]
                                                                                              out.write("\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 13,0-"/html/ejbPage/parameter.jsp" 13,89
                                                                                              com.ibm.etools.utc.view.ResultView resultView = null;
                                                                                              boolean _jspx_specialresultView  = false;
                                                                                               synchronized (session) {
                                                                                              resultView= (com.ibm.etools.utc.view.ResultView)
                                                                                              pageContext.getAttribute("resultView",PageContext.SESSION_SCOPE);
                                                                                              if ( resultView == null ) {
                                                                                              _jspx_specialresultView = true;
                                                                                              try {
                                                                                              resultView = (com.ibm.etools.utc.view.ResultView) java.beans.Beans.instantiate(this.getClass().getClassLoader(), "com.ibm.etools.utc.view.ResultView");
                                                                                              } catch (ClassNotFoundException exc) {
                                                                                               throw new InstantiationException(exc.getMessage());
                                                                                              } catch (Exception exc) {
                                                                                               throw new ServletException (" Cannot create bean of class "+"com.ibm.etools.utc.view.ResultView", exc);
                                                                                              }
                                                                                              pageContext.setAttribute("resultView", resultView, PageContext.SESSION_SCOPE);
                                                                                              }
                                                                                               } 
                                                                                              if(_jspx_specialresultView == true) {
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 13,0-"/html/ejbPage/parameter.jsp" 13,89
                                                                                              }
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(12,89);to=(13,0)]
                                                                                              out.write("\r\n");

                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(13,37);to=(23,9)]
                                                                                              out.write("\r\n\r\n<html>\r\n<head>\r\n  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n  <meta http-equiv=\"Content-Style-Type\" content=\"text/css\">\r\n  <meta http-equiv=\"Pragma\" content=\"no-cache\">\r\n  <meta http-equiv=\"Cache-Control\" content=\"no-cache\">\r\n  <meta http-equiv=\"expires\" content=\"0\">\r\n  <link rel=stylesheet type=\"text/css\" href=\"/UTC/css/dynamic.css\">\r\n  <title>");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 24,9-"/html/ejbPage/parameter.jsp" 24,56
                                                                                              _jspx_th_utc_resource_0.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_0.setParent(null);
                                                                                              _jspx_th_utc_resource_0.setKey("ejbPageParameterViewTitle");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_0);
                                                                                              int _jspx_eval_utc_resource_0 = _jspx_th_utc_resource_0.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 24,9-"/html/ejbPage/parameter.jsp" 24,56
                                                                                              if (_jspx_th_utc_resource_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(23,56);to=(26,0)]
                                                                                              out.write("</title>\r\n</head>\r\n\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 27,2-"/html/ejbPage/parameter.jsp" 28,50
                                                                                               parameterView.loadManagerObjects(modelManager);
                                                                                                parameterView.loadManagerClasses(modelManager); 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(27,52);to=(29,0)]
                                                                                              out.write("\r\n\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 30,2-"/html/ejbPage/parameter.jsp" 30,42
                                                                                               if (parameterView.isLoadClassView()) { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(29,44);to=(33,42)]
                                                                                              out.write("\r\n<!-- *************** Load class *************** -->\r\n<body class=\"margin\">\r\n<form name=\"newClassForm\" action=\"/UTC/toolbox\" method=\"post\" target=\"formParameters\" enctype=\"multipart/form-data\">\r\n<input type=\"hidden\" name=\"random\" value=\"");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 34,45-"/html/ejbPage/parameter.jsp" 34,60
                                                                                              out.print( Math.random() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(33,62);to=(37,18)]
                                                                                              out.write("\"/>\r\n<input type=\"hidden\" name=\"utility\" value=\"2\"/>\r\n<table cellpadding=3 cellspacing=0 border=0 bgcolor=white width=100%>\r\n<tr>\r\n  <td colspan=\"3\">");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 38,18-"/html/ejbPage/parameter.jsp" 38,60
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_1 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_1.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_1.setParent(null);
                                                                                              _jspx_th_utc_resource_1.setKey("infoToolboxLoadClass");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_1);
                                                                                              int _jspx_eval_utc_resource_1 = _jspx_th_utc_resource_1.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 38,18-"/html/ejbPage/parameter.jsp" 38,60
                                                                                              if (_jspx_th_utc_resource_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(37,60);to=(43,34)]
                                                                                              out.write("</td>\r\n</tr>\r\n<tr>\r\n  <td colspan=\"3\" valign=\"middle\" height=\"15\"><img src=\"/UTC/images/line.gif\" width=\"100%\" height=\"2\"></td>\r\n</tr>\r\n<tr>\r\n  <td width=20%><label for=\"text\">");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 44,34-"/html/ejbPage/parameter.jsp" 44,85
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_2 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_2.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_2.setParent(null);
                                                                                              _jspx_th_utc_resource_2.setKey("ejbPageParameterViewLoadClass");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_2);
                                                                                              int _jspx_eval_utc_resource_2 = _jspx_th_utc_resource_2.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 44,34-"/html/ejbPage/parameter.jsp" 44,85
                                                                                              if (_jspx_th_utc_resource_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(43,85);to=(45,42)]
                                                                                              out.write("</label></td>\r\n  <td width=*><input type=\"text\" id=\"text\" class=\"textenter\" name=\"class\" size=25/></td>\r\n  <td width=15%><input type=submit value=\"");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 46,42-"/html/ejbPage/parameter.jsp" 46,88
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_3 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_3.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_3.setParent(null);
                                                                                              _jspx_th_utc_resource_3.setKey("ejbPageParameterViewLoad");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_3);
                                                                                              int _jspx_eval_utc_resource_3 = _jspx_th_utc_resource_3.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 46,42-"/html/ejbPage/parameter.jsp" 46,88
                                                                                              if (_jspx_th_utc_resource_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(45,88);to=(51,0)]
                                                                                              out.write("\" class=\"button\"/></td>\r\n</tr>\r\n\r\n</table>\r\n</form>\r\n\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 52,2-"/html/ejbPage/parameter.jsp" 52,49
                                                                                               } else if (parameterView.isClassCastView()) { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(51,51);to=(55,42)]
                                                                                              out.write("\r\n<!-- *************** Class Cast *************** -->\r\n<body class=\"margin\">\r\n<form name=\"newClassForm\" action=\"/UTC/toolbox\" method=\"post\" target=\"formParameters\" enctype=\"multipart/form-data\">\r\n<input type=\"hidden\" name=\"random\" value=\"");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 56,45-"/html/ejbPage/parameter.jsp" 56,60
                                                                                              out.print( Math.random() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(55,62);to=(59,18)]
                                                                                              out.write("\"/>\r\n<input type=\"hidden\" name=\"utility\" value=\"0\"/>\r\n<table cellpadding=3 cellspacing=0 border=0 bgcolor=white width=100%>\r\n<tr>\r\n  <td colspan=\"3\">");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 60,18-"/html/ejbPage/parameter.jsp" 60,60
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_4 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_4.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_4.setParent(null);
                                                                                              _jspx_th_utc_resource_4.setKey("infoToolboxCastClass");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_4);
                                                                                              int _jspx_eval_utc_resource_4 = _jspx_th_utc_resource_4.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 60,18-"/html/ejbPage/parameter.jsp" 60,60
                                                                                              if (_jspx_th_utc_resource_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(59,60);to=(65,36)]
                                                                                              out.write("</td>\r\n</tr>\r\n<tr>\r\n  <td colspan=\"3\" valign=\"middle\" height=\"15\"><img src=\"/UTC/images/line.gif\" width=\"100%\" height=\"2\"></td>\r\n</tr>\r\n<tr>\r\n  <td width=20%><label for=\"object\">");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 66,36-"/html/ejbPage/parameter.jsp" 66,84
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_5 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_5.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_5.setParent(null);
                                                                                              _jspx_th_utc_resource_5.setKey("ejbPageParameterViewObject");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_5);
                                                                                              int _jspx_eval_utc_resource_5 = _jspx_th_utc_resource_5.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 66,36-"/html/ejbPage/parameter.jsp" 66,84
                                                                                              if (_jspx_th_utc_resource_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(65,84);to=(68,0)]
                                                                                              out.write("</label></td>\r\n  <td>\r\n<select name=\"object\" class=\"selectlist\" id=\"object\">\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 69,2-"/html/ejbPage/parameter.jsp" 72,56
                                                                                               List castObjects = parameterView.getManagerObjects();
                                                                                                int size = castObjects.size();
                                                                                                for (int j = 0; j < size; j++) {
                                                                                                ObjectModel model = (ObjectModel) castObjects.get(j); 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(71,58);to=(72,17)]
                                                                                              out.write("\r\n  <option value=\"");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 73,20-"/html/ejbPage/parameter.jsp" 73,23
                                                                                              out.print( j );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(72,25);to=(72,27)]
                                                                                              out.write("\">");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 73,30-"/html/ejbPage/parameter.jsp" 73,47
                                                                                              out.print( model.getName() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(72,49);to=(73,0)]
                                                                                              out.write("</option>\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 74,2-"/html/ejbPage/parameter.jsp" 74,5
                                                                                               } 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(73,7);to=(79,25)]
                                                                                              out.write("\r\n</select>\r\n  </td>\r\n  <td></td>\r\n</tr>\r\n<tr>\r\n  <td><label for=\"class\">");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 80,25-"/html/ejbPage/parameter.jsp" 80,75
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_6 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_6.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_6.setParent(null);
                                                                                              _jspx_th_utc_resource_6.setKey("ejbPageParameterViewCastType");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_6);
                                                                                              int _jspx_eval_utc_resource_6 = _jspx_th_utc_resource_6.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 80,25-"/html/ejbPage/parameter.jsp" 80,75
                                                                                              if (_jspx_th_utc_resource_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(79,75);to=(83,0)]
                                                                                              out.write("</label></td>\r\n  <td>\r\n\r\n<select name=\"class\" class=\"selectlist\" id=\"class\">\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 84,2-"/html/ejbPage/parameter.jsp" 87,54
                                                                                               List castClasses = parameterView.getManagerClasses();
                                                                                                size = castClasses.size();
                                                                                                for (int j = 0; j < size; j++) {
                                                                                                ClassModel model = (ClassModel) castClasses.get(j); 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(86,56);to=(87,17)]
                                                                                              out.write("\r\n  <option value=\"");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 88,20-"/html/ejbPage/parameter.jsp" 88,23
                                                                                              out.print( j );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(87,25);to=(87,27)]
                                                                                              out.write("\">");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 88,30-"/html/ejbPage/parameter.jsp" 88,47
                                                                                              out.print( model.getName() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(87,49);to=(88,2)]
                                                                                              out.write("</option>\r\n  ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 89,4-"/html/ejbPage/parameter.jsp" 89,7
                                                                                               } 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(88,9);to=(91,42)]
                                                                                              out.write("\r\n</select>\r\n  </td>\r\n  <td width=15%><input type=submit value=\"");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 92,42-"/html/ejbPage/parameter.jsp" 92,88
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_7 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_7.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_7.setParent(null);
                                                                                              _jspx_th_utc_resource_7.setKey("ejbPageParameterViewCast");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_7);
                                                                                              int _jspx_eval_utc_resource_7 = _jspx_th_utc_resource_7.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 92,42-"/html/ejbPage/parameter.jsp" 92,88
                                                                                              if (_jspx_th_utc_resource_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(91,88);to=(97,0)]
                                                                                              out.write("\" class=\"button\"/></td>\r\n</tr>\r\n\r\n</table>\r\n</form>\r\n\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 98,2-"/html/ejbPage/parameter.jsp" 98,45
                                                                                               } else if (parameterView.isArrayView()) { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(97,47);to=(101,42)]
                                                                                              out.write("\r\n<!-- *************** Array Creation *************** -->\r\n<body class=\"margin\">\r\n<form name=\"createArrayForm\" action=\"/UTC/toolbox\" method=\"post\" target=\"formParameters\" enctype=\"multipart/form-data\">\r\n<input type=\"hidden\" name=\"random\" value=\"");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 102,45-"/html/ejbPage/parameter.jsp" 102,60
                                                                                              out.print( Math.random() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(101,62);to=(105,18)]
                                                                                              out.write("\"/>\r\n<input type=\"hidden\" name=\"utility\" value=\"1\"/>\r\n<table cellpadding=3 cellspacing=0 border=0 bgcolor=white width=100%>\r\n<tr>\r\n  <td colspan=\"3\">");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 106,18-"/html/ejbPage/parameter.jsp" 106,62
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_8 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_8.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_8.setParent(null);
                                                                                              _jspx_th_utc_resource_8.setKey("infoToolboxCreateArray");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_8);
                                                                                              int _jspx_eval_utc_resource_8 = _jspx_th_utc_resource_8.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 106,18-"/html/ejbPage/parameter.jsp" 106,62
                                                                                              if (_jspx_th_utc_resource_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(105,62);to=(111,35)]
                                                                                              out.write("</td>\r\n</tr>\r\n<tr>\r\n  <td colspan=\"3\" valign=\"middle\" height=\"15\"><img src=\"/UTC/images/line.gif\" width=\"100%\" height=\"2\"></td>\r\n</tr>\r\n<tr>\r\n  <td width=20%><label for=\"class\">");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 112,35-"/html/ejbPage/parameter.jsp" 112,86
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_9 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_9.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_9.setParent(null);
                                                                                              _jspx_th_utc_resource_9.setKey("ejbPageParameterViewArrayType");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_9);
                                                                                              int _jspx_eval_utc_resource_9 = _jspx_th_utc_resource_9.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 112,35-"/html/ejbPage/parameter.jsp" 112,86
                                                                                              if (_jspx_th_utc_resource_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(111,86);to=(114,0)]
                                                                                              out.write("</label></td>\r\n  <td>\r\n<select name=\"class\" class=\"selectlist\" id=\"class\">\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 115,2-"/html/ejbPage/parameter.jsp" 118,54
                                                                                               List castClasses = parameterView.getManagerClasses();
                                                                                                int size = castClasses.size();
                                                                                                for (int j = 0; j < size; j++) {
                                                                                                ClassModel model = (ClassModel) castClasses.get(j); 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(117,56);to=(118,17)]
                                                                                              out.write("\r\n  <option value=\"");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 119,20-"/html/ejbPage/parameter.jsp" 119,23
                                                                                              out.print( j );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(118,25);to=(118,27)]
                                                                                              out.write("\">");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 119,30-"/html/ejbPage/parameter.jsp" 119,47
                                                                                              out.print( model.getName() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(118,49);to=(119,2)]
                                                                                              out.write("</option>\r\n  ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 120,4-"/html/ejbPage/parameter.jsp" 120,7
                                                                                               } 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(119,9);to=(126,24)]
                                                                                              out.write("\r\n</select>\r\n\r\n  </td>\r\n  <td width=15%></td>\r\n</tr>\r\n<tr>\r\n  <td><label for=\"size\">");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 127,24-"/html/ejbPage/parameter.jsp" 127,75
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_10 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_10.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_10.setParent(null);
                                                                                              _jspx_th_utc_resource_10.setKey("ejbPageParameterViewArraySize");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_10);
                                                                                              int _jspx_eval_utc_resource_10 = _jspx_th_utc_resource_10.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 127,24-"/html/ejbPage/parameter.jsp" 127,75
                                                                                              if (_jspx_th_utc_resource_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(126,75);to=(128,32)]
                                                                                              out.write("</label></td>\r\n  <td><input type=\"text\" id=\"size\" class=\"textenter\" name=\"size\" value=\"1\" size=5/></td>\r\n  <td><input type=submit value=\"");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 129,32-"/html/ejbPage/parameter.jsp" 129,85
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_11 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_11.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_11.setParent(null);
                                                                                              _jspx_th_utc_resource_11.setKey("ejbPageParameterViewArrayCreate");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_11);
                                                                                              int _jspx_eval_utc_resource_11 = _jspx_th_utc_resource_11.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 129,32-"/html/ejbPage/parameter.jsp" 129,85
                                                                                              if (_jspx_th_utc_resource_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(128,85);to=(134,0)]
                                                                                              out.write("\" class=\"button\"/></td>\r\n</tr>\r\n\r\n</table>\r\n</form>\r\n\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 135,2-"/html/ejbPage/parameter.jsp" 135,57
                                                                                               } else if (parameterView.isCollectionToArrayView()) { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(134,59);to=(136,0)]
                                                                                              out.write("\r\n<!-- *************** Collection To Array *************** -->\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 137,2-"/html/ejbPage/parameter.jsp" 137,55
                                                                                               parameterView.loadManagerCollections(modelManager); 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(136,57);to=(139,42)]
                                                                                              out.write("\r\n<body class=\"margin\">\r\n<form name=\"collectionToArrayForm\" action=\"/UTC/toolbox\" method=\"post\" target=\"formParameters\" enctype=\"multipart/form-data\">\r\n<input type=\"hidden\" name=\"random\" value=\"");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 140,45-"/html/ejbPage/parameter.jsp" 140,60
                                                                                              out.print( Math.random() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(139,62);to=(149,40)]
                                                                                              out.write("\"/>\r\n<input type=\"hidden\" name=\"utility\" value=\"3\"/>\r\n<table cellpadding=3 cellspacing=0 border=0 bgcolor=white width=100%>\r\n<tr>\r\n  <td colspan=\"2\">Collection -&gt; Object[]</td>\r\n</tr>\r\n<tr>\r\n  <td colspan=\"3\" valign=\"middle\" height=\"15\"><img src=\"/UTC/images/line.gif\" width=\"100%\" height=\"2\"></td>\r\n</tr>\r\n<tr>\r\n  <td width=20%><label for=\"collection\">");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 150,40-"/html/ejbPage/parameter.jsp" 150,92
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_12 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_12.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_12.setParent(null);
                                                                                              _jspx_th_utc_resource_12.setKey("ejbPageParameterViewCollection");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_12);
                                                                                              int _jspx_eval_utc_resource_12 = _jspx_th_utc_resource_12.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 150,40-"/html/ejbPage/parameter.jsp" 150,92
                                                                                              if (_jspx_th_utc_resource_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(149,92);to=(152,0)]
                                                                                              out.write("</label></td>\r\n  <td>\r\n<select name=\"collection\" class=\"selectlist\" id=\"collection\">\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 153,2-"/html/ejbPage/parameter.jsp" 156,54
                                                                                               List collections = parameterView.getManagerCollections();
                                                                                                int size = collections.size();
                                                                                                for (int j = 0; j < size; j++) {
                                                                                                ClassModel model = (ClassModel) collections.get(j); 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(155,56);to=(156,17)]
                                                                                              out.write("\r\n  <option value=\"");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 157,20-"/html/ejbPage/parameter.jsp" 157,23
                                                                                              out.print( j );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(156,25);to=(156,27)]
                                                                                              out.write("\">");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 157,30-"/html/ejbPage/parameter.jsp" 157,47
                                                                                              out.print( model.getName() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(156,49);to=(157,2)]
                                                                                              out.write("</option>\r\n  ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 158,4-"/html/ejbPage/parameter.jsp" 158,7
                                                                                               } 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(157,9);to=(160,42)]
                                                                                              out.write("\r\n</select>\r\n  </td>\r\n  <td width=15%><input type=submit value=\"");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 161,42-"/html/ejbPage/parameter.jsp" 161,91
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_13 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_13.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_13.setParent(null);
                                                                                              _jspx_th_utc_resource_13.setKey("ejbPageParameterViewConvert");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_13);
                                                                                              int _jspx_eval_utc_resource_13 = _jspx_th_utc_resource_13.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 161,42-"/html/ejbPage/parameter.jsp" 161,91
                                                                                              if (_jspx_th_utc_resource_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(160,91);to=(166,0)]
                                                                                              out.write("\" class=\"button\"/></td>\r\n</tr>\r\n\r\n</table>\r\n</form>\r\n\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 167,2-"/html/ejbPage/parameter.jsp" 167,51
                                                                                               } else if (parameterView.isArrayToListView()) { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(166,53);to=(168,0)]
                                                                                              out.write("\r\n<!-- *************** Array To List *************** -->\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 169,2-"/html/ejbPage/parameter.jsp" 169,50
                                                                                               parameterView.loadManagerArrays(modelManager); 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(168,52);to=(171,42)]
                                                                                              out.write("\r\n<body class=\"margin\">\r\n<form name=\"arrayToListForm\" action=\"/UTC/toolbox\" method=\"post\" target=\"formParameters\" enctype=\"multipart/form-data\">\r\n<input type=\"hidden\" name=\"random\" value=\"");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 172,45-"/html/ejbPage/parameter.jsp" 172,60
                                                                                              out.print( Math.random() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(171,62);to=(181,35)]
                                                                                              out.write("\"/>\r\n<input type=\"hidden\" name=\"utility\" value=\"4\"/>\r\n<table cellpadding=3 cellspacing=0 border=0 bgcolor=white width=100%>\r\n<tr>\r\n  <td colspan=\"3\">Object[] -&gt; List</td>\r\n</tr>\r\n<tr>\r\n  <td colspan=\"3\" valign=\"middle\" height=\"15\"><img src=\"/UTC/images/line.gif\" width=\"100%\" height=\"2\"></td>\r\n</tr>\r\n<tr>\r\n  <td width=20%><label for=\"array\">");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 182,35-"/html/ejbPage/parameter.jsp" 182,82
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_14 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_14.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_14.setParent(null);
                                                                                              _jspx_th_utc_resource_14.setKey("ejbPageParameterViewArray");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_14);
                                                                                              int _jspx_eval_utc_resource_14 = _jspx_th_utc_resource_14.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 182,35-"/html/ejbPage/parameter.jsp" 182,82
                                                                                              if (_jspx_th_utc_resource_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(181,82);to=(184,0)]
                                                                                              out.write("</label></td>\r\n  <td>\r\n<select name=\"array\" class=\"selectlist\" id=\"array\">\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 185,2-"/html/ejbPage/parameter.jsp" 188,49
                                                                                               List arrays = parameterView.getManagerArrays();
                                                                                                int size = arrays.size();
                                                                                                for (int j = 0; j < size; j++) {
                                                                                                ClassModel model = (ClassModel) arrays.get(j); 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(187,51);to=(188,17)]
                                                                                              out.write("\r\n  <option value=\"");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 189,20-"/html/ejbPage/parameter.jsp" 189,23
                                                                                              out.print( j );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(188,25);to=(188,27)]
                                                                                              out.write("\">");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 189,30-"/html/ejbPage/parameter.jsp" 189,47
                                                                                              out.print( model.getName() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(188,49);to=(189,2)]
                                                                                              out.write("</option>\r\n  ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 190,4-"/html/ejbPage/parameter.jsp" 190,7
                                                                                               } 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(189,9);to=(192,42)]
                                                                                              out.write("\r\n</select>\r\n  </td>\r\n  <td width=15%><input type=submit value=\"");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 193,42-"/html/ejbPage/parameter.jsp" 193,91
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_15 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_15.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_15.setParent(null);
                                                                                              _jspx_th_utc_resource_15.setKey("ejbPageParameterViewConvert");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_15);
                                                                                              int _jspx_eval_utc_resource_15 = _jspx_th_utc_resource_15.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 193,42-"/html/ejbPage/parameter.jsp" 193,91
                                                                                              if (_jspx_th_utc_resource_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(192,91);to=(198,0)]
                                                                                              out.write("\" class=\"button\"/></td>\r\n</tr>\r\n\r\n</table>\r\n</form>\r\n\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 199,2-"/html/ejbPage/parameter.jsp" 199,55
                                                                                               } else if (parameterView.isSetArrayElementView()) { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(198,57);to=(200,0)]
                                                                                              out.write("\r\n<!-- *************** Set Array Element *************** -->\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 201,2-"/html/ejbPage/parameter.jsp" 201,50
                                                                                               parameterView.loadManagerArrays(modelManager); 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(200,52);to=(201,0)]
                                                                                              out.write("\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 202,2-"/html/ejbPage/parameter.jsp" 202,51
                                                                                               parameterView.loadManagerObjects(modelManager); 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(201,53);to=(204,42)]
                                                                                              out.write("\r\n<body class=\"margin\">\r\n<form name=\"arrayElementForm\" action=\"/UTC/toolbox\" method=\"post\" target=\"formParameters\" enctype=\"multipart/form-data\">\r\n<input type=\"hidden\" name=\"random\" value=\"");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 205,45-"/html/ejbPage/parameter.jsp" 205,60
                                                                                              out.print( Math.random() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(204,62);to=(208,18)]
                                                                                              out.write("\"/>\r\n<input type=\"hidden\" name=\"utility\" value=\"5\"/>\r\n<table cellpadding=3 cellspacing=0 border=0 bgcolor=white width=100%>\r\n<tr>\r\n  <td colspan=\"3\">");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 209,18-"/html/ejbPage/parameter.jsp" 209,66
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_16 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_16.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_16.setParent(null);
                                                                                              _jspx_th_utc_resource_16.setKey("infoToolboxSetArrayElement");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_16);
                                                                                              int _jspx_eval_utc_resource_16 = _jspx_th_utc_resource_16.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 209,18-"/html/ejbPage/parameter.jsp" 209,66
                                                                                              if (_jspx_th_utc_resource_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(208,66);to=(214,35)]
                                                                                              out.write("</td>\r\n</tr>\r\n<tr>\r\n  <td colspan=\"3\" valign=\"middle\" height=\"15\"><img src=\"/UTC/images/line.gif\" width=\"100%\" height=\"2\"></td>\r\n</tr>\r\n<tr>\r\n  <td width=20%><label for=\"array\">");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 215,35-"/html/ejbPage/parameter.jsp" 215,82
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_17 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_17.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_17.setParent(null);
                                                                                              _jspx_th_utc_resource_17.setKey("ejbPageParameterViewArray");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_17);
                                                                                              int _jspx_eval_utc_resource_17 = _jspx_th_utc_resource_17.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 215,35-"/html/ejbPage/parameter.jsp" 215,82
                                                                                              if (_jspx_th_utc_resource_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(214,82);to=(217,0)]
                                                                                              out.write("</label></td>\r\n  <td>\r\n<select name=\"array\" class=\"selectlist\" id=\"array\">\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 218,2-"/html/ejbPage/parameter.jsp" 221,49
                                                                                               List arrays = parameterView.getManagerArrays();
                                                                                                int size = arrays.size();
                                                                                                for (int j = 0; j < size; j++) {
                                                                                                ClassModel model = (ClassModel) arrays.get(j); 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(220,51);to=(221,17)]
                                                                                              out.write("\r\n  <option value=\"");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 222,20-"/html/ejbPage/parameter.jsp" 222,23
                                                                                              out.print( j );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(221,25);to=(221,27)]
                                                                                              out.write("\">");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 222,30-"/html/ejbPage/parameter.jsp" 222,47
                                                                                              out.print( model.getName() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(221,49);to=(222,2)]
                                                                                              out.write("</option>\r\n  ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 223,4-"/html/ejbPage/parameter.jsp" 223,7
                                                                                               } 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(222,9);to=(228,27)]
                                                                                              out.write("\r\n</select>\r\n  </td>\r\n  <td width=15%></td>\r\n</tr>\r\n<tr>\r\n  <td><label for=\"element\">");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 229,27-"/html/ejbPage/parameter.jsp" 229,76
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_18 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_18.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_18.setParent(null);
                                                                                              _jspx_th_utc_resource_18.setKey("ejbPageParameterViewElement");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_18);
                                                                                              int _jspx_eval_utc_resource_18 = _jspx_th_utc_resource_18.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 229,27-"/html/ejbPage/parameter.jsp" 229,76
                                                                                              if (_jspx_th_utc_resource_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(228,76);to=(233,26)]
                                                                                              out.write("</label></td>\r\n  <td><input type=\"text\" class=\"textenter\" id=\"element\" name=\"element\" value=\"0\" size=5/></td>\r\n  <td></td>\r\n</tr>\r\n<tr>\r\n  <td><label for=\"object\">");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 234,26-"/html/ejbPage/parameter.jsp" 234,74
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_19 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_19.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_19.setParent(null);
                                                                                              _jspx_th_utc_resource_19.setKey("ejbPageParameterViewObject");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_19);
                                                                                              int _jspx_eval_utc_resource_19 = _jspx_th_utc_resource_19.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 234,26-"/html/ejbPage/parameter.jsp" 234,74
                                                                                              if (_jspx_th_utc_resource_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(233,74);to=(236,0)]
                                                                                              out.write("</label></td>\r\n  <td>\r\n<select name=\"object\" class=\"selectlist\" id=\"object\">\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 237,2-"/html/ejbPage/parameter.jsp" 240,50
                                                                                               List objects = parameterView.getManagerObjects();
                                                                                                size = objects.size();
                                                                                                for (int j = 0; j < size; j++) {
                                                                                                ClassModel model = (ClassModel) objects.get(j); 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(239,52);to=(240,17)]
                                                                                              out.write("\r\n  <option value=\"");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 241,20-"/html/ejbPage/parameter.jsp" 241,23
                                                                                              out.print( j );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(240,25);to=(240,27)]
                                                                                              out.write("\">");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 241,30-"/html/ejbPage/parameter.jsp" 241,47
                                                                                              out.print( model.getName() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(240,49);to=(241,2)]
                                                                                              out.write("</option>\r\n  ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 242,4-"/html/ejbPage/parameter.jsp" 242,7
                                                                                               } 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(241,9);to=(244,32)]
                                                                                              out.write("\r\n</select>\r\n  </td>\r\n  <td><input type=submit value=\"");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 245,32-"/html/ejbPage/parameter.jsp" 245,77
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_20 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_20.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_20.setParent(null);
                                                                                              _jspx_th_utc_resource_20.setKey("ejbPageParameterViewSet");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_20);
                                                                                              int _jspx_eval_utc_resource_20 = _jspx_th_utc_resource_20.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 245,32-"/html/ejbPage/parameter.jsp" 245,77
                                                                                              if (_jspx_th_utc_resource_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(244,77);to=(250,0)]
                                                                                              out.write("\" class=\"button\"/></td>\r\n</tr>\r\n\r\n</table>\r\n</form>\r\n\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 251,2-"/html/ejbPage/parameter.jsp" 251,49
                                                                                               } else if (parameterView.isInspectorView()) { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(250,51);to=(252,0)]
                                                                                              out.write("\r\n<!-- *************** Field Inspector *************** -->\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 253,2-"/html/ejbPage/parameter.jsp" 253,51
                                                                                               parameterView.cacheInspectorTree(modelManager); 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(252,53);to=(254,0)]
                                                                                              out.write("\r\n<body class=\"margin\">\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 255,3-"/html/ejbPage/parameter.jsp" 255,115
                                                                                              out.print( Tree.getTableTree(request, "_self", "/html/ejbPage/parameter.jsp", parameterView.getInspectorTreeRoot(), true) );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(254,117);to=(256,0)]
                                                                                              out.write("\r\n\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 257,2-"/html/ejbPage/parameter.jsp" 257,50
                                                                                               } else if (parameterView.isJNDIObjectView()) { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(256,52);to=(259,0)]
                                                                                              out.write("\r\n<!-- *************** JNDI Object view *************** -->\r\n<body class=\"margin\">\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 260,2-"/html/ejbPage/parameter.jsp" 260,47
                                                                                               if (parameterView.isTransactionCurrent()) { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(259,49);to=(261,0)]
                                                                                              out.write("\r\n\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 262,2-"/html/ejbPage/parameter.jsp" 262,83
                                                                                               TransactionObject transaction = (TransactionObject) parameterView.getCurrent(); 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(261,85);to=(264,3)]
                                                                                              out.write("\r\n\r\n<br>\r\n<b>");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 265,3-"/html/ejbPage/parameter.jsp" 265,57
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_21 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_21.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_21.setParent(null);
                                                                                              _jspx_th_utc_resource_21.setKey("jndiObjectsPageTransactionStatus");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_21);
                                                                                              int _jspx_eval_utc_resource_21 = _jspx_th_utc_resource_21.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 265,3-"/html/ejbPage/parameter.jsp" 265,57
                                                                                              if (_jspx_th_utc_resource_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(264,57);to=(264,62)]
                                                                                              out.write("</b> ");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 265,65-"/html/ejbPage/parameter.jsp" 265,90
                                                                                              out.print( transaction.getStatus() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(264,92);to=(269,0)]
                                                                                              out.write("\r\n<p>\r\n\r\n<table cellpadding=3 cellspacing=0 border=0>\r\n<tr>\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 270,2-"/html/ejbPage/parameter.jsp" 270,40
                                                                                               if (transaction.showBeginAction()) { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(269,42);to=(272,28)]
                                                                                              out.write("\r\n<form action=\"/UTC/userTransaction\" method=\"get\">\r\n  <td><input type=hidden name=\"action\" value=\"begin\">\r\n  <input type=submit value=\"");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 273,28-"/html/ejbPage/parameter.jsp" 273,81
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_22 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_22.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_22.setParent(null);
                                                                                              _jspx_th_utc_resource_22.setKey("jndiObjectsPageTransactionBegin");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_22);
                                                                                              int _jspx_eval_utc_resource_22 = _jspx_th_utc_resource_22.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 273,28-"/html/ejbPage/parameter.jsp" 273,81
                                                                                              if (_jspx_th_utc_resource_22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(272,81);to=(275,0)]
                                                                                              out.write("\" id=\"begin\" class=\"button\"></td>\r\n</form>\r\n\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 276,2-"/html/ejbPage/parameter.jsp" 276,5
                                                                                               } 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(275,7);to=(277,0)]
                                                                                              out.write("\r\n\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 278,2-"/html/ejbPage/parameter.jsp" 278,41
                                                                                               if (transaction.showStateActions()) { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(277,43);to=(280,28)]
                                                                                              out.write("\r\n<form action=\"/UTC/userTransaction\" method=\"get\">\r\n  <td><input type=hidden name=\"action\" value=\"commit\">\r\n  <input type=submit value=\"");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 281,28-"/html/ejbPage/parameter.jsp" 281,82
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_23 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_23.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_23.setParent(null);
                                                                                              _jspx_th_utc_resource_23.setKey("jndiObjectsPageTransactionCommit");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_23);
                                                                                              int _jspx_eval_utc_resource_23 = _jspx_th_utc_resource_23.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 281,28-"/html/ejbPage/parameter.jsp" 281,82
                                                                                              if (_jspx_th_utc_resource_23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(280,82);to=(285,28)]
                                                                                              out.write("\" id=\"commit\" class=\"button\"></td>\r\n</form>\r\n<p>\r\n<form action=\"/UTC/userTransaction\" method=\"get\">\r\n  <td><input type=hidden name=\"action\" value=\"rollback\">\r\n  <input type=submit value=\"");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 286,28-"/html/ejbPage/parameter.jsp" 286,84
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_24 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_24.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_24.setParent(null);
                                                                                              _jspx_th_utc_resource_24.setKey("jndiObjectsPageTransactionRollback");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_24);
                                                                                              int _jspx_eval_utc_resource_24 = _jspx_th_utc_resource_24.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 286,28-"/html/ejbPage/parameter.jsp" 286,84
                                                                                              if (_jspx_th_utc_resource_24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(285,84);to=(290,28)]
                                                                                              out.write("\" id=\"rollback\" class=\"button\"></td>\r\n</form>\r\n<p>\r\n<form action=\"/UTC/userTransaction\" method=\"get\">\r\n  <td><input type=hidden name=\"action\" value=\"rollbackOnly\">\r\n  <input type=submit value=\"");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 291,28-"/html/ejbPage/parameter.jsp" 291,88
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_25 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_25.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_25.setParent(null);
                                                                                              _jspx_th_utc_resource_25.setKey("jndiObjectsPageTransactionRollbackOnly");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_25);
                                                                                              int _jspx_eval_utc_resource_25 = _jspx_th_utc_resource_25.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 291,28-"/html/ejbPage/parameter.jsp" 291,88
                                                                                              if (_jspx_th_utc_resource_25.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(290,88);to=(293,0)]
                                                                                              out.write("\" id=\"rollbackonly\" class=\"button\"></td>\r\n</form>\r\n\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 294,2-"/html/ejbPage/parameter.jsp" 294,5
                                                                                               } 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(293,7);to=(297,0)]
                                                                                              out.write("\r\n</tr>\r\n</table>\r\n\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 298,2-"/html/ejbPage/parameter.jsp" 298,53
                                                                                               } else if (parameterView.isDataSourceCurrent()) { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(297,55);to=(299,0)]
                                                                                              out.write("\r\n\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 300,2-"/html/ejbPage/parameter.jsp" 300,80
                                                                                               DataSourceObject dataSource = (DataSourceObject) parameterView.getCurrent(); 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(299,82);to=(304,6)]
                                                                                              out.write("\r\n\r\n<div class=\"datasource\">\r\n<table cellpadding=3 cellspacing=0 border=0>\r\n<tr>\r\n  <td>");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 305,6-"/html/ejbPage/parameter.jsp" 305,59
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_26 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_26.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_26.setParent(null);
                                                                                              _jspx_th_utc_resource_26.setKey("jndiObjectsPageDataSourceDriver");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_26);
                                                                                              int _jspx_eval_utc_resource_26 = _jspx_th_utc_resource_26.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 305,6-"/html/ejbPage/parameter.jsp" 305,59
                                                                                              if (_jspx_th_utc_resource_26.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(304,59);to=(305,6)]
                                                                                              out.write("</td>\r\n  <td>");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 306,9-"/html/ejbPage/parameter.jsp" 306,46
                                                                                              out.print( dataSource.getDatabaseProductName() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(305,48);to=(305,50)]
                                                                                              out.write(", ");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 306,53-"/html/ejbPage/parameter.jsp" 306,93
                                                                                              out.print( dataSource.getDatabaseProductVersion() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(305,95);to=(308,6)]
                                                                                              out.write("</td>\r\n</tr>\r\n<tr class=\"alternating\">\r\n  <td>");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 309,6-"/html/ejbPage/parameter.jsp" 309,59
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_27 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_27.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_27.setParent(null);
                                                                                              _jspx_th_utc_resource_27.setKey("jndiObjectsPageDataSourceDriver");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_27);
                                                                                              int _jspx_eval_utc_resource_27 = _jspx_th_utc_resource_27.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 309,6-"/html/ejbPage/parameter.jsp" 309,59
                                                                                              if (_jspx_th_utc_resource_27.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(308,59);to=(309,6)]
                                                                                              out.write("</td>\r\n  <td>");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 310,9-"/html/ejbPage/parameter.jsp" 310,37
                                                                                              out.print( dataSource.getDriverName() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(309,39);to=(309,41)]
                                                                                              out.write(", ");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 310,44-"/html/ejbPage/parameter.jsp" 310,75
                                                                                              out.print( dataSource.getDriverVersion() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(309,77);to=(312,6)]
                                                                                              out.write("</td>\r\n</tr>\r\n<tr>\r\n  <td>");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 313,6-"/html/ejbPage/parameter.jsp" 313,57
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_28 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_28.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_28.setParent(null);
                                                                                              _jspx_th_utc_resource_28.setKey("jndiObjectsPageDataSourceUser");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_28);
                                                                                              int _jspx_eval_utc_resource_28 = _jspx_th_utc_resource_28.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 313,6-"/html/ejbPage/parameter.jsp" 313,57
                                                                                              if (_jspx_th_utc_resource_28.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(312,57);to=(313,6)]
                                                                                              out.write("</td>\r\n  <td>");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 314,9-"/html/ejbPage/parameter.jsp" 314,35
                                                                                              out.print( dataSource.getUserName() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(313,37);to=(316,6)]
                                                                                              out.write("</td>\r\n</tr>\r\n<tr class=\"alternating\">\r\n  <td>");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 317,6-"/html/ejbPage/parameter.jsp" 317,67
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_29 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_29.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_29.setParent(null);
                                                                                              _jspx_th_utc_resource_29.setKey("jndiObjectsPageDataSourceMaxConnections");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_29);
                                                                                              int _jspx_eval_utc_resource_29 = _jspx_th_utc_resource_29.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 317,6-"/html/ejbPage/parameter.jsp" 317,67
                                                                                              if (_jspx_th_utc_resource_29.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(316,67);to=(317,6)]
                                                                                              out.write("</td>\r\n  <td>");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 318,9-"/html/ejbPage/parameter.jsp" 318,41
                                                                                              out.print( dataSource.getMaxConnections() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(317,43);to=(320,6)]
                                                                                              out.write("</td>\r\n</tr>\r\n<tr>\r\n  <td>");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 321,6-"/html/ejbPage/parameter.jsp" 321,56
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_30 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_30.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_30.setParent(null);
                                                                                              _jspx_th_utc_resource_30.setKey("jndiObjectsPageDataSourceURL");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_30);
                                                                                              int _jspx_eval_utc_resource_30 = _jspx_th_utc_resource_30.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 321,6-"/html/ejbPage/parameter.jsp" 321,56
                                                                                              if (_jspx_th_utc_resource_30.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(320,56);to=(321,6)]
                                                                                              out.write("</td>\r\n  <td>");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 322,9-"/html/ejbPage/parameter.jsp" 322,30
                                                                                              out.print( dataSource.getURL() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(321,32);to=(326,0)]
                                                                                              out.write("</td>\r\n</tr>\r\n</table>\r\n</div>\r\n\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 327,2-"/html/ejbPage/parameter.jsp" 327,12
                                                                                               } else { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(326,14);to=(327,2)]
                                                                                              out.write("\r\n  ");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 328,2-"/html/ejbPage/parameter.jsp" 328,46
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_31 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_31.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_31.setParent(null);
                                                                                              _jspx_th_utc_resource_31.setKey("jndiObjectsPageInvalid");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_31);
                                                                                              int _jspx_eval_utc_resource_31 = _jspx_th_utc_resource_31.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 328,2-"/html/ejbPage/parameter.jsp" 328,46
                                                                                              if (_jspx_th_utc_resource_31.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(327,46);to=(328,0)]
                                                                                              out.write("\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 329,2-"/html/ejbPage/parameter.jsp" 329,5
                                                                                               } 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(328,7);to=(330,0)]
                                                                                              out.write("\r\n\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 331,2-"/html/ejbPage/parameter.jsp" 331,49
                                                                                               } else if (parameterView.isHierarchyView()) { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(330,51);to=(334,0)]
                                                                                              out.write("\r\n\r\n<!-- *************** Hierarchy/Method Visibility *************** -->\r\n<body class=\"margin\">\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 335,0-"/html/ejbPage/parameter.jsp" 335,48
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_32 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_32.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_32.setParent(null);
                                                                                              _jspx_th_utc_resource_32.setKey("ejbPageParameterVisibility");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_32);
                                                                                              int _jspx_eval_utc_resource_32 = _jspx_th_utc_resource_32.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 335,0-"/html/ejbPage/parameter.jsp" 335,48
                                                                                              if (_jspx_th_utc_resource_32.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(334,48);to=(334,49)]
                                                                                              out.write(" ");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 335,52-"/html/ejbPage/parameter.jsp" 335,95
                                                                                              out.print( parameterView.getHierarchyClassFullName() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(334,97);to=(337,42)]
                                                                                              out.write("<br>\r\n\r\n<form action=\"/UTC/setHierarchy\" target=\"formReferences\" method=\"post\" enctype=\"multipart/form-data\">\r\n<input type=\"hidden\" name=\"random\" value=\"");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 338,45-"/html/ejbPage/parameter.jsp" 338,60
                                                                                              out.print( Math.random() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(337,62);to=(343,18)]
                                                                                              out.write("\"/>\r\n\r\n<div class=\"hierarchy\">\r\n<table cellpadding=3 cellspacing=0 border=0 width=100%>\r\n\r\n<tr>\r\n  <td colspan=\"2\">");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 344,18-"/html/ejbPage/parameter.jsp" 344,78
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_33 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_33.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_33.setParent(null);
                                                                                              _jspx_th_utc_resource_33.setKey("ejbPageParameterVisibilitySuperclasses");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_33);
                                                                                              int _jspx_eval_utc_resource_33 = _jspx_th_utc_resource_33.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 344,18-"/html/ejbPage/parameter.jsp" 344,78
                                                                                              if (_jspx_th_utc_resource_33.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(343,78);to=(348,0)]
                                                                                              out.write("</td>\r\n</tr>\r\n<tr>\r\n  <td colspan=\"2\" valign=\"middle\" height=\"15\"><img src=\"/UTC/images/line.gif\" width=\"100%\" height=\"2\"></td>\r\n</tr>\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 349,3-"/html/ejbPage/parameter.jsp" 349,47
                                                                                              out.print( parameterView.getHierarchySuperclassForm() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(348,49);to=(353,18)]
                                                                                              out.write("\r\n\r\n<tr><td height=20></td></tr>\r\n\r\n<tr>\r\n  <td colspan=\"2\">");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 354,18-"/html/ejbPage/parameter.jsp" 354,81
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_34 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_34.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_34.setParent(null);
                                                                                              _jspx_th_utc_resource_34.setKey("ejbPageParameterVisibilitySuperinterfaces");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_34);
                                                                                              int _jspx_eval_utc_resource_34 = _jspx_th_utc_resource_34.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 354,18-"/html/ejbPage/parameter.jsp" 354,81
                                                                                              if (_jspx_th_utc_resource_34.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(353,81);to=(358,0)]
                                                                                              out.write("</td>\r\n</tr>\r\n<tr>\r\n  <td colspan=\"2\" valign=\"middle\" height=\"15\"><img src=\"/UTC/images/line.gif\" width=\"100%\" height=\"2\"></td>\r\n</tr>\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 359,3-"/html/ejbPage/parameter.jsp" 359,46
                                                                                              out.print( parameterView.getHierarchyInterfaceForm() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(358,48);to=(364,26)]
                                                                                              out.write("\r\n\r\n</table>\r\n</div>\r\n<br>\r\n\r\n<input type=submit value=\"");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 365,26-"/html/ejbPage/parameter.jsp" 365,77
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_35 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_35.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_35.setParent(null);
                                                                                              _jspx_th_utc_resource_35.setKey("ejbPageParameterVisibilitySet");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_35);
                                                                                              int _jspx_eval_utc_resource_35 = _jspx_th_utc_resource_35.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 365,26-"/html/ejbPage/parameter.jsp" 365,77
                                                                                              if (_jspx_th_utc_resource_35.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(364,77);to=(368,0)]
                                                                                              out.write("\" class=\"button\"/>\r\n\r\n</form>\r\n\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 369,2-"/html/ejbPage/parameter.jsp" 369,12
                                                                                               } else { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(368,14);to=(372,0)]
                                                                                              out.write("\r\n<!-- *************** Constructor/Method Invocation *************** -->\r\n<body class=\"margin\">\r\n<div class=\"parameterPage\">\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 373,2-"/html/ejbPage/parameter.jsp" 375,0
                                                                                               if (parameterView.isValid()) { 
                                                                                              String form = parameterView.getHTMLForm(modelManager);
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(374,2);to=(375,0)]
                                                                                              out.write("\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 376,3-"/html/ejbPage/parameter.jsp" 376,7
                                                                                              out.print(form);
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(375,9);to=(377,0)]
                                                                                              out.write("\r\n\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 378,2-"/html/ejbPage/parameter.jsp" 378,5
                                                                                               } 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(377,7);to=(379,0)]
                                                                                              out.write("\r\n\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 380,2-"/html/ejbPage/parameter.jsp" 380,5
                                                                                               } 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(379,7);to=(387,2)]
                                                                                              out.write("\r\n\r\n <!-- *************** Results Section *************** -->\r\n\r\n<a name=\"result\"/>\r\n<table cellpadding=1 cellspacing=0 width=100% border=0>\r\n  <tr><td height=30></td></tr>\r\n  <tr><td>\r\n  ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 388,4-"/html/ejbPage/parameter.jsp" 388,36
                                                                                               if (resultView.isExpanded()) { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(387,38);to=(388,46)]
                                                                                              out.write("\r\n    <a href=\"/UTC/results?action=close&random=");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 389,49-"/html/ejbPage/parameter.jsp" 389,64
                                                                                              out.print( Math.random() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(388,66);to=(389,2)]
                                                                                              out.write("\"><img src=\"/UTC/images/twist_open.gif\" width=\"11\" height=\"10\" border=0></a>\r\n  ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 390,4-"/html/ejbPage/parameter.jsp" 390,14
                                                                                               } else { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(389,16);to=(390,45)]
                                                                                              out.write("\r\n    <a href=\"/UTC/results?action=open&random=");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 391,48-"/html/ejbPage/parameter.jsp" 391,63
                                                                                              out.print( Math.random() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(390,65);to=(391,2)]
                                                                                              out.write("\"><img src=\"/UTC/images/twist_close.gif\" width=\"11\" height=\"10\" border=0></a>\r\n  ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 392,4-"/html/ejbPage/parameter.jsp" 392,7
                                                                                               } 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(391,9);to=(392,2)]
                                                                                              out.write("\r\n  ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 393,4-"/html/ejbPage/parameter.jsp" 393,37
                                                                                               if (!resultView.showSource()) { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(392,39);to=(393,4)]
                                                                                              out.write("\r\n    ");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 394,4-"/html/ejbPage/parameter.jsp" 394,53
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_36 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_36.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_36.setParent(null);
                                                                                              _jspx_th_utc_resource_36.setKey("ejbPageParameterViewResults");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_36);
                                                                                              int _jspx_eval_utc_resource_36 = _jspx_th_utc_resource_36.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 394,4-"/html/ejbPage/parameter.jsp" 394,53
                                                                                              if (_jspx_th_utc_resource_36.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(393,53);to=(394,2)]
                                                                                              out.write("</td></tr>\r\n  ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 395,4-"/html/ejbPage/parameter.jsp" 395,14
                                                                                               } else { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(394,16);to=(395,4)]
                                                                                              out.write("\r\n    ");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 396,4-"/html/ejbPage/parameter.jsp" 396,52
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_37 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_37.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_37.setParent(null);
                                                                                              _jspx_th_utc_resource_37.setKey("ejbPageParameterViewSource");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_37);
                                                                                              int _jspx_eval_utc_resource_37 = _jspx_th_utc_resource_37.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 396,4-"/html/ejbPage/parameter.jsp" 396,52
                                                                                              if (_jspx_th_utc_resource_37.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(395,52);to=(395,63)]
                                                                                              out.write(" <img src=\"");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 396,66-"/html/ejbPage/parameter.jsp" 396,95
                                                                                              out.print( resultView.getSourceImage() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(395,97);to=(395,119)]
                                                                                              out.write("\" width=16 height=16/>");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 396,122-"/html/ejbPage/parameter.jsp" 396,146
                                                                                              out.print( resultView.getSource() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(395,148);to=(396,2)]
                                                                                              out.write("\r\n  ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 397,4-"/html/ejbPage/parameter.jsp" 397,7
                                                                                               } 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(396,9);to=(402,2)]
                                                                                              out.write("\r\n  \r\n  <tr>\r\n    <td colspan=\"3\" valign=\"middle\" height=\"15\"><img src=\"/UTC/images/line.gif\" width=\"100%\" height=\"2\"></td>\r\n  </tr>\r\n  \r\n  ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 403,4-"/html/ejbPage/parameter.jsp" 403,36
                                                                                               if (resultView.isExpanded()) { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(402,38);to=(408,0)]
                                                                                              out.write("\r\n\r\n  <tr>\r\n  <td colspan=\"3\">\r\n\r\n<!-- Result Page --->\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 409,2-"/html/ejbPage/parameter.jsp" 409,31
                                                                                               if (resultView.isValid()) { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(408,33);to=(410,2)]
                                                                                              out.write("\r\n\r\n  ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 411,4-"/html/ejbPage/parameter.jsp" 411,39
                                                                                               if (resultView.isErrorResult()) { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(410,41);to=(411,20)]
                                                                                              out.write("\r\n    <font color=red>");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 412,23-"/html/ejbPage/parameter.jsp" 412,46
                                                                                              out.print( resultView.getError() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(411,48);to=(412,2)]
                                                                                              out.write("</font>\r\n  ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 413,4-"/html/ejbPage/parameter.jsp" 413,45
                                                                                               } else if (resultView.isVoidResult()) { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(412,47);to=(413,4)]
                                                                                              out.write("\r\n    ");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 414,4-"/html/ejbPage/parameter.jsp" 414,47
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_38 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_38.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_38.setParent(null);
                                                                                              _jspx_th_utc_resource_38.setKey("ejbPageResultViewVoid");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_38);
                                                                                              int _jspx_eval_utc_resource_38 = _jspx_th_utc_resource_38.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 414,4-"/html/ejbPage/parameter.jsp" 414,47
                                                                                              if (_jspx_th_utc_resource_38.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(413,47);to=(414,2)]
                                                                                              out.write("\r\n  ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 415,4-"/html/ejbPage/parameter.jsp" 415,45
                                                                                               } else if (resultView.isNullResult()) { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(414,47);to=(415,4)]
                                                                                              out.write("\r\n    ");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 416,4-"/html/ejbPage/parameter.jsp" 416,47
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_39 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_39.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_39.setParent(null);
                                                                                              _jspx_th_utc_resource_39.setKey("ejbPageResultViewNull");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_39);
                                                                                              int _jspx_eval_utc_resource_39 = _jspx_th_utc_resource_39.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 416,4-"/html/ejbPage/parameter.jsp" 416,47
                                                                                              if (_jspx_th_utc_resource_39.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(415,47);to=(416,2)]
                                                                                              out.write("\r\n  ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 417,4-"/html/ejbPage/parameter.jsp" 417,50
                                                                                               } else if (resultView.isExceptionResult()) { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(416,52);to=(418,4)]
                                                                                              out.write("\r\n    <img src=\"/UTC/images/error.gif\" width=16 height=16 align=\"top\"/>\r\n    ");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 419,7-"/html/ejbPage/parameter.jsp" 419,41
                                                                                              out.print( resultView.getExceptionMessage() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(418,43);to=(419,2)]
                                                                                              out.write("\r\n  ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 420,4-"/html/ejbPage/parameter.jsp" 420,14
                                                                                               } else { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(419,16);to=(420,4)]
                                                                                              out.write("\r\n    ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 421,6-"/html/ejbPage/parameter.jsp" 421,59
                                                                                               if (resultView.getModel() instanceof ObjectModel) { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(420,61);to=(421,78)]
                                                                                              out.write("\r\n      <img src=\"/UTC/images/object.gif\" width=16 height=16 align=\"top\"/>&nbsp;");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 422,81-"/html/ejbPage/parameter.jsp" 422,110
                                                                                              out.print( resultView.getObjectValue() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(421,112);to=(421,119)]
                                                                                              out.write("&nbsp;(");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 422,122-"/html/ejbPage/parameter.jsp" 422,153
                                                                                              out.print( resultView.getModelFullName() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(421,155);to=(422,4)]
                                                                                              out.write(")\r\n    ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 423,6-"/html/ejbPage/parameter.jsp" 423,16
                                                                                               } else { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(422,18);to=(423,77)]
                                                                                              out.write("\r\n      <img src=\"/UTC/images/class.gif\" width=16 height=16 align=\"top\"/>&nbsp;");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 424,80-"/html/ejbPage/parameter.jsp" 424,111
                                                                                              out.print( resultView.getModelFullName() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(423,113);to=(424,4)]
                                                                                              out.write("\r\n    ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 425,6-"/html/ejbPage/parameter.jsp" 425,9
                                                                                               } 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(424,11);to=(425,2)]
                                                                                              out.write("\r\n  ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 426,4-"/html/ejbPage/parameter.jsp" 426,7
                                                                                               } 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(425,9);to=(427,2)]
                                                                                              out.write("\r\n\r\n  ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 428,4-"/html/ejbPage/parameter.jsp" 428,74
                                                                                               if (resultView.isExceptionResult() || resultView.isObjectResult()) { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(427,76);to=(428,4)]
                                                                                              out.write("\r\n    ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 429,6-"/html/ejbPage/parameter.jsp" 429,59
                                                                                               if (modelManager.contains(resultView.getModel())) { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(428,61);to=(429,6)]
                                                                                              out.write("\r\n      ");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 430,6-"/html/ejbPage/parameter.jsp" 430,50
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_40 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_40.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_40.setParent(null);
                                                                                              _jspx_th_utc_resource_40.setKey("ejbPageResultViewInUse");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_40);
                                                                                              int _jspx_eval_utc_resource_40 = _jspx_th_utc_resource_40.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 430,6-"/html/ejbPage/parameter.jsp" 430,50
                                                                                              if (_jspx_th_utc_resource_40.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(429,50);to=(430,4)]
                                                                                              out.write("\r\n    ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 431,6-"/html/ejbPage/parameter.jsp" 431,16
                                                                                               } else { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(430,18);to=(432,48)]
                                                                                              out.write("\r\n      <form name=\"work\" action=\"/UTC/addObject\" method=\"get\" target=\"formReferences\">\r\n      <input type=\"hidden\" name=\"random\" value=\"");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 433,51-"/html/ejbPage/parameter.jsp" 433,66
                                                                                              out.print( Math.random() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(432,68);to=(433,32)]
                                                                                              out.write("\"/>\r\n      <input type=submit value=\"");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 434,32-"/html/ejbPage/parameter.jsp" 434,74
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_41 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_41.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_41.setParent(null);
                                                                                              _jspx_th_utc_resource_41.setKey("ejbPageResultViewUse");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_41);
                                                                                              int _jspx_eval_utc_resource_41 = _jspx_th_utc_resource_41.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 434,32-"/html/ejbPage/parameter.jsp" 434,74
                                                                                              if (_jspx_th_utc_resource_41.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(433,74);to=(435,4)]
                                                                                              out.write("\" class=\"button\"/>\r\n      </form>\r\n    ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 436,6-"/html/ejbPage/parameter.jsp" 436,9
                                                                                               } 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(435,11);to=(437,4)]
                                                                                              out.write("\r\n    \r\n    ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 438,6-"/html/ejbPage/parameter.jsp" 438,40
                                                                                               if (resultView.isCollection()) { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(437,42);to=(439,48)]
                                                                                              out.write("\r\n      <form name=\"workAll\" action=\"/UTC/addAllObjects\" method=\"get\" target=\"formReferences\">\r\n      <input type=\"hidden\" name=\"random\" value=\"");

                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 440,51-"/html/ejbPage/parameter.jsp" 440,66
                                                                                              out.print( Math.random() );
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(439,68);to=(440,32)]
                                                                                              out.write("\"/>\r\n      <input type=submit value=\"");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 441,32-"/html/ejbPage/parameter.jsp" 441,77
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_42 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_42.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_42.setParent(null);
                                                                                              _jspx_th_utc_resource_42.setKey("ejbPageResultViewUseAll");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_42);
                                                                                              int _jspx_eval_utc_resource_42 = _jspx_th_utc_resource_42.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 441,32-"/html/ejbPage/parameter.jsp" 441,77
                                                                                              if (_jspx_th_utc_resource_42.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(440,77);to=(442,4)]
                                                                                              out.write("\" class=\"longbutton\"/>\r\n      </form>\r\n    ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 443,6-"/html/ejbPage/parameter.jsp" 443,9
                                                                                               } 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(442,11);to=(443,2)]
                                                                                              out.write("\r\n  ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 444,4-"/html/ejbPage/parameter.jsp" 444,7
                                                                                               } 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(443,9);to=(445,2)]
                                                                                              out.write("\r\n\r\n  ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 446,4-"/html/ejbPage/parameter.jsp" 446,43
                                                                                               if (resultView.isExceptionResult()) { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(445,45);to=(446,27)]
                                                                                              out.write("\r\n    <pre class=\"exception\">");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 447,29-"/html/ejbPage/parameter.jsp" 447,62
                                                                                               resultView.printException(out); 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(446,64);to=(447,2)]
                                                                                              out.write("</pre>\r\n  ");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 448,4-"/html/ejbPage/parameter.jsp" 448,7
                                                                                               } 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(447,9);to=(449,0)]
                                                                                              out.write("\r\n\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 450,2-"/html/ejbPage/parameter.jsp" 450,12
                                                                                               } else { 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(449,14);to=(450,2)]
                                                                                              out.write("\r\n  ");

                                                                                              // end
                                                                                              // ##DEBUG## ##TAGLIB## "/html/ejbPage/parameter.jsp" 451,2-"/html/ejbPage/parameter.jsp" 451,48
                                                                                              /* ------  utc:resource ------ */
                                                                                              com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_43 = new com.ibm.etools.utc.ResourceTag();
                                                                                              _jspx_th_utc_resource_43.setPageContext(pageContext);
                                                                                              _jspx_th_utc_resource_43.setParent(null);
                                                                                              _jspx_th_utc_resource_43.setKey("ejbPageResultViewInvalid");
                                                                                              _jspxTagObjects.push(_jspx_th_utc_resource_43);
                                                                                              int _jspx_eval_utc_resource_43 = _jspx_th_utc_resource_43.doStartTag();
                                                                                              // end
                                                                                              // ##DEBUG## "/html/ejbPage/parameter.jsp" 451,2-"/html/ejbPage/parameter.jsp" 451,48
                                                                                              if (_jspx_th_utc_resource_43.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                                                                                              return;
                                                                                              ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(450,48);to=(451,0)]
                                                                                              out.write("\r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 452,2-"/html/ejbPage/parameter.jsp" 452,5
                                                                                               } 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(451,7);to=(456,0)]
                                                                                              out.write("\r\n\r\n  </td>\r\n </tr>\r\n \r\n");

                                                                                              // end
                                                                                              // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/parameter.jsp" 457,2-"/html/ejbPage/parameter.jsp" 457,5
                                                                                               } 
                                                                                              // end
                                                                                              // HTML // begin [file="/html/ejbPage/parameter.jsp";from=(456,7);to=(460,7)]
                                                                                              out.write("\r\n</table>\r\n\r\n</body>\r\n</html>");

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
