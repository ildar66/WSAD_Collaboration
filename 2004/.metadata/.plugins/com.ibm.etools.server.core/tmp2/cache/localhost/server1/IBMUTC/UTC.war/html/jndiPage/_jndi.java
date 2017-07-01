package org.apache.jsp;

import com.ibm.etools.utc.Tree;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

/* File Declaration Phase */

public class _jndi extends com.ibm.ws.webcontainer.jsp.runtime.HttpJspBase {

  /* Class Declaration Phase */

  static {
    /* Static Initializer Phase */
  }

  public _jndi( ) {
  }

  private static boolean _jspx_inited = false;

  private static String _jspx_debug_jspSourceLocation = "/html/jndiPage/jndi.jsp";

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

              // HTML // begin [file="/html/jndiPage/jndi.jsp";from=(0,0);to=(9,0)]
                out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\r\n\r\n<!--\r\n Licensed Material - Property of IBM \r\n (C) Copyright IBM Corp. 2001 - All Rights Reserved. \r\n US Government Users Restricted Rights - Use, duplication or disclosure \r\n restricted by GSA ADP Schedule Contract with IBM Corp. \r\n-->\r\n\r\n");

              // end
              // HTML // begin [file="/html/jndiPage/jndi.jsp";from=(9,106);to=(10,0)]
                out.write("\r\n");

              // end
              // ##DEBUG## "/html/jndiPage/jndi.jsp" 11,0-"/html/jndiPage/jndi.jsp" 11,85  LineMapIndex:1
                com.ibm.etools.utc.view.JNDIView jndiView = null;
                boolean _jspx_specialjndiView  = false;
                 synchronized (session) {
                  jndiView= (com.ibm.etools.utc.view.JNDIView)
                  pageContext.getAttribute("jndiView",PageContext.SESSION_SCOPE);
                  if ( jndiView == null ) {
                    _jspx_specialjndiView = true;
                    try {
                      jndiView = (com.ibm.etools.utc.view.JNDIView) java.beans.Beans.instantiate(this.getClass().getClassLoader(), "com.ibm.etools.utc.view.JNDIView");
                    } catch (ClassNotFoundException exc) {
                       throw new InstantiationException(exc.getMessage());
                    } catch (Exception exc) {
                       throw new ServletException (" Cannot create bean of class "+"com.ibm.etools.utc.view.JNDIView", exc);
                    }
                    pageContext.setAttribute("jndiView", jndiView, PageContext.SESSION_SCOPE);
                  }
                 } 
                if(_jspx_specialjndiView == true) {
              // end
              // ##DEBUG## "/html/jndiPage/jndi.jsp" 11,0-"/html/jndiPage/jndi.jsp" 11,85  LineMapIndex:2
                }
              // end
              // HTML // begin [file="/html/jndiPage/jndi.jsp";from=(10,85);to=(11,0)]
                out.write("\r\n");

              // end
              // HTML // begin [file="/html/jndiPage/jndi.jsp";from=(11,37);to=(21,9)]
                out.write("\r\n\r\n<html>\r\n<head>\r\n  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n  <meta http-equiv=\"Content-Style-Type\" content=\"text/css\">\r\n  <meta http-equiv=\"Pragma\" content=\"no-cache\">\r\n  <meta http-equiv=\"Cache-Control\" content=\"no-cache\">\r\n  <meta http-equiv=\"expires\" content=\"0\">\r\n  <link rel=stylesheet type=\"text/css\" href=\"/UTC/css/dynamic.css\">\r\n  <title>");

              // end
              // ##DEBUG## ##TAGLIB## "/html/jndiPage/jndi.jsp" 22,9-"/html/jndiPage/jndi.jsp" 22,50  LineMapIndex:3
                _jspx_th_utc_resource_0.setPageContext(pageContext);
                _jspx_th_utc_resource_0.setParent(null);
                _jspx_th_utc_resource_0.setKey("jndiLookupPageTitle");
                _jspxTagObjects.push(_jspx_th_utc_resource_0);
                  int _jspx_eval_utc_resource_0 = _jspx_th_utc_resource_0.doStartTag();
                // end
                // ##DEBUG## "/html/jndiPage/jndi.jsp" 22,9-"/html/jndiPage/jndi.jsp" 22,50  LineMapIndex:4
                  if (_jspx_th_utc_resource_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                    return;
                ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
              // end
              // HTML // begin [file="/html/jndiPage/jndi.jsp";from=(21,50);to=(29,42)]
                out.write("</title>\r\n</head>\r\n\r\n<body>\r\n\r\n<div class=\"jndiPage\">\r\n\r\n<form action=\"/UTC/jndiLookup\" method=\"post\" target=\"main\" enctype=\"multipart/form-data\">\r\n<input type=\"hidden\" name=\"random\" value=\"");

              // end
              // ##DEBUG## "/html/jndiPage/jndi.jsp" 30,45-"/html/jndiPage/jndi.jsp" 30,60  LineMapIndex:5
                out.print( Math.random() );
              // end
              // HTML // begin [file="/html/jndiPage/jndi.jsp";from=(29,62);to=(33,41)]
                out.write("\"/>\r\n\r\n<table cellpadding=4 cellspacing=0 border=0 width=100%>\r\n<tr>\r\n  <td width=15% nowrap><label for=\"name\">");

              // end
              // ##DEBUG## ##TAGLIB## "/html/jndiPage/jndi.jsp" 34,41-"/html/jndiPage/jndi.jsp" 34,85  LineMapIndex:6
                /* ------  utc:resource ------ */
                com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_1 = new com.ibm.etools.utc.ResourceTag();
                _jspx_th_utc_resource_1.setPageContext(pageContext);
                _jspx_th_utc_resource_1.setParent(null);
                _jspx_th_utc_resource_1.setKey("jndiLookupPageJNDIName");
                _jspxTagObjects.push(_jspx_th_utc_resource_1);
                  int _jspx_eval_utc_resource_1 = _jspx_th_utc_resource_1.doStartTag();
                // end
                // ##DEBUG## "/html/jndiPage/jndi.jsp" 34,41-"/html/jndiPage/jndi.jsp" 34,85  LineMapIndex:7
                  if (_jspx_th_utc_resource_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                    return;
                ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
              // end
              // HTML // begin [file="/html/jndiPage/jndi.jsp";from=(33,85);to=(35,57)]
                out.write("</label></td>\r\n  <td width=*><input type=\"text\" name=\"name\" id=\"name\" value=\"\" class=\"textenter\" size=50/></td>\r\n  <td width=20%><input type=submit class=\"button\" value=\"");

              // end
              // ##DEBUG## ##TAGLIB## "/html/jndiPage/jndi.jsp" 36,57-"/html/jndiPage/jndi.jsp" 36,99  LineMapIndex:8
                /* ------  utc:resource ------ */
                com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_2 = new com.ibm.etools.utc.ResourceTag();
                _jspx_th_utc_resource_2.setPageContext(pageContext);
                _jspx_th_utc_resource_2.setParent(null);
                _jspx_th_utc_resource_2.setKey("jndiLookupPageLookup");
                _jspxTagObjects.push(_jspx_th_utc_resource_2);
                  int _jspx_eval_utc_resource_2 = _jspx_th_utc_resource_2.doStartTag();
                // end
                // ##DEBUG## "/html/jndiPage/jndi.jsp" 36,57-"/html/jndiPage/jndi.jsp" 36,99  LineMapIndex:9
                  if (_jspx_th_utc_resource_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                    return;
                ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
              // end
              // HTML // begin [file="/html/jndiPage/jndi.jsp";from=(35,99);to=(38,0)]
                out.write("\"/></td>\r\n</tr>\r\n\r\n");

              // end
              // ##DEBUG## ##SCRIPTLET## "/html/jndiPage/jndi.jsp" 39,2-"/html/jndiPage/jndi.jsp" 39,29  LineMapIndex:10
                 if (jndiView.isError()) { 
              // end
              // HTML // begin [file="/html/jndiPage/jndi.jsp";from=(38,31);to=(40,17)]
                out.write("\r\n<tr>\r\n  <td valign=top>");

              // end
              // ##DEBUG## ##TAGLIB## "/html/jndiPage/jndi.jsp" 41,17-"/html/jndiPage/jndi.jsp" 41,58  LineMapIndex:11
                /* ------  utc:resource ------ */
                com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_3 = new com.ibm.etools.utc.ResourceTag();
                _jspx_th_utc_resource_3.setPageContext(pageContext);
                _jspx_th_utc_resource_3.setParent(null);
                _jspx_th_utc_resource_3.setKey("jndiLookupPageError");
                _jspxTagObjects.push(_jspx_th_utc_resource_3);
                  int _jspx_eval_utc_resource_3 = _jspx_th_utc_resource_3.doStartTag();
                // end
                // ##DEBUG## "/html/jndiPage/jndi.jsp" 41,17-"/html/jndiPage/jndi.jsp" 41,58  LineMapIndex:12
                  if (_jspx_th_utc_resource_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                    return;
                ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
              // end
              // HTML // begin [file="/html/jndiPage/jndi.jsp";from=(40,58);to=(41,86)]
                out.write("</td>\r\n  <td colspan=2><img src=\"/UTC/images/error.gif\" width=16 height=16><span class=error>");

              // end
              // ##DEBUG## "/html/jndiPage/jndi.jsp" 42,89-"/html/jndiPage/jndi.jsp" 42,110  LineMapIndex:13
                out.print( jndiView.getError() );
              // end
              // HTML // begin [file="/html/jndiPage/jndi.jsp";from=(41,112);to=(43,0)]
                out.write("</span></td>\r\n</tr>\r\n");

              // end
              // ##DEBUG## ##SCRIPTLET## "/html/jndiPage/jndi.jsp" 44,2-"/html/jndiPage/jndi.jsp" 44,5  LineMapIndex:14
                 } 
              // end
              // HTML // begin [file="/html/jndiPage/jndi.jsp";from=(43,7);to=(48,0)]
                out.write("\r\n\r\n</table>\r\n</form>\r\n\r\n");

              // end
              // ##DEBUG## ##SCRIPTLET## "/html/jndiPage/jndi.jsp" 49,2-"/html/jndiPage/jndi.jsp" 49,54  LineMapIndex:15
                 jndiView.cacheJNDINamespace(request, application); 
              // end
              // HTML // begin [file="/html/jndiPage/jndi.jsp";from=(48,56);to=(50,0)]
                out.write("\r\n\r\n");

              // end
              // ##DEBUG## "/html/jndiPage/jndi.jsp" 51,3-"/html/jndiPage/jndi.jsp" 51,104  LineMapIndex:16
                out.print( Tree.getTableTree(request, "_parent", "/html/jndiPage/jndiPage.jsp", jndiView.getJNDIRoot(), false) );
              // end
              // HTML // begin [file="/html/jndiPage/jndi.jsp";from=(50,106);to=(55,7)]
                out.write("\r\n\r\n</div>\r\n\r\n</body>\r\n</html>");

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
          public static String[][] _jspx_debug_FileMapping = {
          {
          "/html/jndiPage/jndi.jsp", 
          },
          {
          "0", 
          },
          };
          public static String[][] _jspx_debug_LineMapping = {
          {
          "0", 
          "0", 
          "0", 
          "0", 
          "0", 
          "0", 
          "0", 
          "0", 
          "0", 
          "0", 
          "0", 
          "0", 
          "0", 
          "0", 
          "0", 
          "0", 
          "0", 
          },
          {
          "1", 
          "11", 
          "11", 
          "22", 
          "22", 
          "30", 
          "34", 
          "34", 
          "36", 
          "36", 
          "39", 
          "41", 
          "41", 
          "42", 
          "44", 
          "49", 
          "51", 
          },
          };
        }
