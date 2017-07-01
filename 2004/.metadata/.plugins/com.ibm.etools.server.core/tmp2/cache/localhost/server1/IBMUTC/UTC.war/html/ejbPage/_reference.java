package org.apache.jsp;

import com.ibm.etools.utc.*;
import com.ibm.etools.utc.model.ClassModel;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

/* File Declaration Phase */

public class _reference extends com.ibm.ws.webcontainer.jsp.runtime.HttpJspBase {

  /* Class Declaration Phase */

  static {
    /* Static Initializer Phase */
  }

  public _reference( ) {
  }

  private static boolean _jspx_inited = false;

  private static String _jspx_debug_jspSourceLocation = "/html/ejbPage/reference.jsp";

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

          // HTML // begin [file="/html/ejbPage/reference.jsp";from=(0,0);to=(9,0)]
            out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\r\n\r\n<!--\r\n Licensed Material - Property of IBM \r\n (C) Copyright IBM Corp. 2001 - All Rights Reserved. \r\n US Government Users Restricted Rights - Use, duplication or disclosure \r\n restricted by GSA ADP Schedule Contract with IBM Corp. \r\n-->\r\n\r\n");

          // end
          // HTML // begin [file="/html/ejbPage/reference.jsp";from=(9,139);to=(10,0)]
            out.write("\r\n");

          // end
          // ##DEBUG## "/html/ejbPage/reference.jsp" 11,0-"/html/ejbPage/reference.jsp" 11,95
            com.ibm.etools.utc.view.ReferenceView referenceView = null;
            boolean _jspx_specialreferenceView  = false;
             synchronized (session) {
              referenceView= (com.ibm.etools.utc.view.ReferenceView)
              pageContext.getAttribute("referenceView",PageContext.SESSION_SCOPE);
              if ( referenceView == null ) {
                _jspx_specialreferenceView = true;
                try {
                  referenceView = (com.ibm.etools.utc.view.ReferenceView) java.beans.Beans.instantiate(this.getClass().getClassLoader(), "com.ibm.etools.utc.view.ReferenceView");
                } catch (ClassNotFoundException exc) {
                   throw new InstantiationException(exc.getMessage());
                } catch (Exception exc) {
                   throw new ServletException (" Cannot create bean of class "+"com.ibm.etools.utc.view.ReferenceView", exc);
                }
                pageContext.setAttribute("referenceView", referenceView, PageContext.SESSION_SCOPE);
              }
             } 
            if(_jspx_specialreferenceView == true) {
          // end
          // ##DEBUG## "/html/ejbPage/reference.jsp" 11,0-"/html/ejbPage/reference.jsp" 11,95
            }
          // end
          // HTML // begin [file="/html/ejbPage/reference.jsp";from=(10,95);to=(11,0)]
            out.write("\r\n");

          // end
          // HTML // begin [file="/html/ejbPage/reference.jsp";from=(11,37);to=(21,9)]
            out.write("\r\n\r\n<html>\r\n<head>\r\n  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n  <meta http-equiv=\"Content-Style-Type\" content=\"text/css\">\r\n  <meta http-equiv=\"Pragma\" content=\"no-cache\">\r\n  <meta http-equiv=\"Cache-Control\" content=\"no-cache\">\r\n  <meta http-equiv=\"expires\" content=\"0\">\r\n  <link rel=stylesheet type=\"text/css\" href=\"/UTC/css/dynamic.css\">\r\n  <title>");

          // end
          // ##DEBUG## ##TAGLIB## "/html/ejbPage/reference.jsp" 22,9-"/html/ejbPage/reference.jsp" 22,56
            _jspx_th_utc_resource_0.setPageContext(pageContext);
            _jspx_th_utc_resource_0.setParent(null);
            _jspx_th_utc_resource_0.setKey("ejbPageReferenceViewTitle");
            _jspxTagObjects.push(_jspx_th_utc_resource_0);
              int _jspx_eval_utc_resource_0 = _jspx_th_utc_resource_0.doStartTag();
            // end
            // ##DEBUG## "/html/ejbPage/reference.jsp" 22,9-"/html/ejbPage/reference.jsp" 22,56
              if (_jspx_th_utc_resource_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                return;
            ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
          // end
          // HTML // begin [file="/html/ejbPage/reference.jsp";from=(21,56);to=(26,0)]
            out.write("</title>\r\n</head>\r\n\r\n<body class=\"page\">\r\n\r\n");

          // end
          // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/reference.jsp" 27,2-"/html/ejbPage/reference.jsp" 27,37
             referenceView.cacheTree(request); 
          // end
          // HTML // begin [file="/html/ejbPage/reference.jsp";from=(26,39);to=(27,0)]
            out.write("\r\n");

          // end
          // ##DEBUG## "/html/ejbPage/reference.jsp" 28,3-"/html/ejbPage/reference.jsp" 28,113
            out.print( Tree.getTableTree(request, "_parent", "/html/ejbPage/referencePage.jsp", referenceView.getTreeRoot(), false) );
          // end
          // HTML // begin [file="/html/ejbPage/reference.jsp";from=(27,115);to=(29,0)]
            out.write("\r\n\r\n");

          // end
          // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/reference.jsp" 30,2-"/html/ejbPage/reference.jsp" 30,50
             if (referenceView.getAddedObjects() != null) { 
          // end
          // HTML // begin [file="/html/ejbPage/reference.jsp";from=(29,52);to=(31,55)]
            out.write("\r\n<br>\r\n    <img src=\"/UTC/images/spacer.gif\" width=8 height=5>");

          // end
          // ##DEBUG## ##TAGLIB## "/html/ejbPage/reference.jsp" 32,55-"/html/ejbPage/reference.jsp" 32,106
            /* ------  utc:resource ------ */
            com.ibm.etools.utc.ResourceTag _jspx_th_utc_resource_1 = new com.ibm.etools.utc.ResourceTag();
            _jspx_th_utc_resource_1.setPageContext(pageContext);
            _jspx_th_utc_resource_1.setParent(null);
            _jspx_th_utc_resource_1.setKey("ejbPageReferenceViewLastAdded");
            _jspxTagObjects.push(_jspx_th_utc_resource_1);
              int _jspx_eval_utc_resource_1 = _jspx_th_utc_resource_1.doStartTag();
            // end
            // ##DEBUG## "/html/ejbPage/reference.jsp" 32,55-"/html/ejbPage/reference.jsp" 32,106
              if (_jspx_th_utc_resource_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
                return;
            ((javax.servlet.jsp.tagext.Tag)_jspxTagObjects.pop()).release();
          // end
          // HTML // begin [file="/html/ejbPage/reference.jsp";from=(31,106);to=(32,4)]
            out.write("\r\n    ");

          // end
          // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/reference.jsp" 33,6-"/html/ejbPage/reference.jsp" 40,40
             ClassModel[] models = referenceView.getAddedObjects();
                   int size = models.length;
                   boolean dots = false;
                   if (size > 10) {
                     size = 10;
                     dots = true;
                   }
                   for (int i = 0; i < size; i++) { 
          // end
          // HTML // begin [file="/html/ejbPage/reference.jsp";from=(39,42);to=(40,63)]
            out.write("\r\n       <br><img src=\"/UTC/images/spacer.gif\" width=16 height=5>");

          // end
          // ##DEBUG## "/html/ejbPage/reference.jsp" 41,66-"/html/ejbPage/reference.jsp" 41,116
            out.print( JspUtil.convertStringToHTML(models[i].getName()) );
          // end
          // HTML // begin [file="/html/ejbPage/reference.jsp";from=(40,118);to=(41,4)]
            out.write("\r\n    ");

          // end
          // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/reference.jsp" 42,6-"/html/ejbPage/reference.jsp" 42,9
             } 
          // end
          // HTML // begin [file="/html/ejbPage/reference.jsp";from=(41,11);to=(42,8)]
            out.write("\r\n    <br>");

          // end
          // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/reference.jsp" 43,10-"/html/ejbPage/reference.jsp" 43,23
             if (dots) { 
          // end
          // HTML // begin [file="/html/ejbPage/reference.jsp";from=(42,25);to=(42,80)]
            out.write("<img src=\"/UTC/images/spacer.gif\" width=16 height=5>...");

          // end
          // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/reference.jsp" 43,82-"/html/ejbPage/reference.jsp" 43,85
             } 
          // end
          // HTML // begin [file="/html/ejbPage/reference.jsp";from=(42,87);to=(43,2)]
            out.write("\r\n  ");

          // end
          // ##DEBUG## ##SCRIPTLET## "/html/ejbPage/reference.jsp" 44,4-"/html/ejbPage/reference.jsp" 44,7
             } 
          // end
          // HTML // begin [file="/html/ejbPage/reference.jsp";from=(43,9);to=(46,7)]
            out.write("\r\n\r\n</body>\r\n</html>");

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
