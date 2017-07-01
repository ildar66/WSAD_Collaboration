<%-- IBM Confidential OCO Source Material --%>
<%-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 1997, 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" import="org.apache.struts.util.MessageResources,org.apache.struts.action.Action,org.apache.regexp.*,com.ibm.ws.console.core.ConfigFileHelper,java.util.StringTokenizer"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>

<tiles:useAttribute name="descTitle" classname="java.lang.String" />
<tiles:useAttribute name="descImage" classname="java.lang.String" />
<tiles:useAttribute name="descText" classname="java.lang.String" />

<% 
        String thecontext = "";
        String theref = "";
        String theresource = "";
        if (session.getAttribute("thecontext") != null) {
                thecontext = (String)session.getAttribute("thecontext"); 
        }
        if (session.getAttribute("theref") != null) {
                theref = (String)session.getAttribute("theref"); 
        } 
        if (session.getAttribute("theresource") != null) {
                theresource = (String)session.getAttribute("theresource"); 
        }
%>

<a name="important"></a> 
<ibmcommon:errors/>


<a name="title"></a>

<%
   String formType = (String)session.getAttribute (com.ibm.ws.console.core.Constants.CURRENT_FORMTYPE);

   String fieldLevelHelpTopic = null;
   String DETAILFORM = "DetailForm";
   String COLLECTIONFORM = "CollectionForm";
   String FORM = "Form";
   String objectType = "";
   if (formType != null && formType.length() > 0)
   {
		int index = formType.lastIndexOf ('.');
		if (index > 0)
		{
			String fType = formType.substring (index+1);
			if (fType.endsWith (DETAILFORM))
			{
				objectType = fType.substring (0, fType.length()-DETAILFORM.length());
				fieldLevelHelpTopic = objectType + ".detail";
			}
			else if (fType.endsWith (COLLECTIONFORM))
			{
				objectType = fType.substring (0, fType.length()-COLLECTIONFORM.length());
				fieldLevelHelpTopic = objectType + ".collection";
			}
         else if (fType.endsWith (FORM))
         {
            objectType = fType.substring (0, fType.length() - FORM.length());
            fieldLevelHelpTopic = objectType;
         }
			else
			{
				fieldLevelHelpTopic = fType;
			}
		}
      else
      {
         fieldLevelHelpTopic = formType;
      }
   }
	else
		//fieldLevelHelpTopic = null;
        fieldLevelHelpTopic = "";
%>



        <%  
        
        ////////////////////////////////// Try to construct a good URL ///////////////////////////////////////////////////////

        ServletContext servletContext = (ServletContext)pageContext.getServletContext();
        MessageResources messages = (MessageResources)servletContext.getAttribute(Action.MESSAGES_KEY);
        String message = messages.getMessage(request.getLocale(),descTitle);
        String backLink = messages.getMessage(request.getLocale(),"button.back");        

        String altprior = "";
        String priorpage = "";
        String dbcsprior = "";
        String reconpage = "";
        String newbasestring = "";
        String newqstring = "";
        if (request.getHeader("Referer") == null){
                priorpage = "badURL";
        } 
        else {
                priorpage = request.getHeader("Referer");
        }
        
        RE splitter1 = new RE("\\?");
        RE splitter2 = new RE("&");
        int firstQ = priorpage.indexOf("?");
        
        if (firstQ > -1) {
        	newqstring = priorpage.substring(firstQ+1);
        	newbasestring = priorpage.substring(0,firstQ);
        }
        
        if (priorpage.indexOf("contextId") > -1) {
        
                //String[] constructURL1 = splitter1.split(priorpage);
                String[] constructURL2 = splitter2.split(newqstring);
                int constructURLlen = constructURL2.length;
                
                for (int z=0;z<constructURLlen;z++) {
                        if (constructURL2[z].indexOf("contextId") > -1) {
                                reconpage = "contextId="+thecontext;
                                if (thecontext != null) {
	                                if (z == 0) {
	                                        dbcsprior = reconpage;
	                                } else {
	                                        dbcsprior = dbcsprior + "&" + reconpage;
	                                }
                                } else {
	                                if (z == 0) {
	                                        dbcsprior = constructURL2[z];
	                                } else {
	                                        dbcsprior = dbcsprior + "&" + constructURL2[z];                                
	                                }                                                          
                        	}        				                               
                        } else if (constructURL2[z].indexOf("refId") > -1) {
                                reconpage = "refId="+theref;
                                if (theref != null) {
	                                if (z == 0) {
	                                        dbcsprior = reconpage;
	                                } else {
	                                        dbcsprior = dbcsprior + "&" + reconpage;
	                                }
                                } else {
	                                if (z == 0) {
	                                        dbcsprior = constructURL2[z];
	                                } else {
	                                        dbcsprior = dbcsprior + "&" + constructURL2[z];                                
	                                }                                                          
                        	}        				                               
       				                               
                                
                        } else if (constructURL2[z].indexOf("perspective") > -1) {
                                reconpage = "perspective="+request.getParameter("perspective");
                                if (request.getParameter("perspective") != null) {
	                                if (z == 0) {
	                                        dbcsprior = reconpage;
	                                } else {
	                                        dbcsprior = dbcsprior + "&" + reconpage;                                
	                                } 
                                } else {
	                                if (z == 0) {
	                                        dbcsprior = constructURL2[z];
	                                } else {
	                                        dbcsprior = dbcsprior + "&" + constructURL2[z];                                
	                                }                                                          
                        	}        				                               
                                                         
                        } else if (constructURL2[z].indexOf("resourceUri") > -1) {
                                reconpage = "resourceUri="+theresource;
                                if (request.getParameter("resourceUri") != null) {
	                                if (z == 0) {
	                                        dbcsprior = reconpage;
	                                } else {
	                                        dbcsprior = dbcsprior + "&" + reconpage;                                
	                                } 
                                } else {
	                                if (z == 0) {
	                                        dbcsprior = constructURL2[z];
	                                } else {
	                                        dbcsprior = dbcsprior + "&" + constructURL2[z];                                
	                                }                                                          
                        	}        				                               
                                                         
                         } else  {
                                if (z == 0) {
                                        dbcsprior = constructURL2[z];
                                } else {
                                        dbcsprior = dbcsprior + "&" + constructURL2[z];                                
                                }                          
                        }                                           
                        
                }
                               
                dbcsprior = newbasestring + "?" + dbcsprior;
                priorpage = dbcsprior;
                
        }


        String qstring = request.getQueryString();
        String reqMethod = request.getMethod();
        String urlString = request.getRequestURI();
        String resourceUri,contextId,perspective,refId;

        if (qstring != null) {
        
                resourceUri = "&resourceUri="+request.getParameter("resourceUri");
                contextId = "&contextId="+thecontext;
                perspective = "&perspective="+request.getParameter("perspective");
                refId = "&refId="+theref;

                urlString = request.getRequestURI() + "?" + request.getQueryString(); 
                
        
                if (priorpage.indexOf(".do?")>-1) {
                        altprior = request.getRequestURI() + "?EditAction=true"+ refId + contextId + resourceUri + perspective;
                } else {
                        altprior = priorpage + "?EditAction=true"+ refId + contextId + resourceUri + perspective;
                }
      
        } 

        //////////////////////////////////////////////////////////////////////////////////////////////////////



        %>

        <%
        
        
        //////////////////  Paqes to use for zeroing out breadcrumb ///////////////////
        int isnavigator = priorpage.indexOf("/admin/secure/navigator.jsp");        
        int isbanner = priorpage.indexOf("/admin/secure/banner.jsp");
        int islogon = priorpage.indexOf("logon.do");      
        if (islogon == -1)
            islogon = priorpage.indexOf("securelogon.do");      
        if (islogon == -1) {
            if (priorpage.endsWith("/admin/"))
                islogon = 1;
        }        
        int islogoff = priorpage.indexOf("logoff.do");
        int ismessages = priorpage.indexOf("status.jsp");
        if (ismessages == -1) {
                ismessages = priorpage.indexOf("statusTray.do");
        }
        
        int ismessagedetail = priorpage.indexOf("events.content.main");
        if (ismessagedetail < 0) {
                ismessagedetail = priorpage.indexOf("configproblems.content.main") + urlString.indexOf("resourceUri=info") + urlString.indexOf("resourceUri=warning") + urlString.indexOf("resourceUri=error") ;
        }
        
        int isreturnfrommessage = priorpage.indexOf("eventCollection.do");
        if (isreturnfrommessage < 0) {
                isreturnfrommessage = priorpage.indexOf("configProbCollection.do");
        }
        int isHome = urlString.indexOf("/secure/content.jsp");
        //////////////////////////////////////////////////////////////////////////////
        

        
        //////////////////  Pages to pop breadcrumb to same page /////////////////////////
        int isFilterToggle = urlString.indexOf("quickSearchState=");
        int isPrefsToggle =  urlString.indexOf("show=");
        int isScopeToggle =  urlString.indexOf("scopeShow=");
        //////////////////////////////////////////////////////////////////////////////////
        

        
        ////////////////// Node and Server browsing pages ////////////////////////////////
        int isServerBrowse = urlString.indexOf("browseServersAction=");
        int isNodeBrowse =  urlString.indexOf("browseNodesAction=");
        //////////////////////////////////////////////////////////////////////////////////
        

       
        //out.println("<P>Desc Priorpage: "+priorpage+"</P>");
        //out.println("<P>Altprior: "+altprior+"</P>");
        //out.println("Early priorpage:" + session.getAttribute("goodpriorpage"));



        int counter1;
        int counter2;
        int counter3;
        int counter4;
        int tmpcount;
        String backbutton;        
        int newlen = 0;
        String status = "push";
        int bclen = 15;
        String[] bclinksT = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" };
        String[] bcnamesT = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" };         
        String[] bcpageidT = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" }; 
       
        /////////// If the referer is navigator||banner||logon, then zero out arrays ////////////////////
        if ((isnavigator > -1) || (isbanner > -1) || (islogon > -1) || (islogoff > -1)) {
                session.setAttribute("bcnames", bcnamesT);
                session.setAttribute("bclinks", bclinksT);                
                session.setAttribute("bcpageid", bcpageidT);

                session.setAttribute("thecontext","");
                session.setAttribute("theref","");
                session.setAttribute("theresource","");

        }
        /////////////////////////////////////////////////////////////////////////////////////////////////
        

        if (session.getAttribute("bclinks") != null) {
	        bclinksT = (String[])session.getAttribute("bclinks");
        }
        if (session.getAttribute("bcnames") != null)  {           
                bcnamesT = (String[])session.getAttribute("bcnames");  
        }
        if (session.getAttribute("bcpageid") != null)  {           
                bcpageidT = (String[])session.getAttribute("bcpageid");  
        }

        

        //////////////// If its Prob Determination stuff, use the server name for page title
        int probdetSit = urlString.indexOf("&contextId");
        if ((bcnamesT[0].equals(message)) && (probdetSit > -1)) {
               String contxt = request.getParameter("contextId");
               int svr = contxt.indexOf("servers:");
               if (svr != -1)
               {
                   String servers = contxt.substring(svr);
                   StringTokenizer tok = new StringTokenizer(servers, ":");
                   String s1 = tok.nextToken();
                   String s2 = tok.nextToken();
    
                   message = s2;
                   fieldLevelHelpTopic = "";
               }
        }        
        ////////////////////////////////////////////////////////////////////////////////////////
        

        int oldlen = 0;
        

        /////////////  Find the first empty spot in the array //////////////////
        for (counter1=0; counter1<bclen; counter1++) {
                if (bcnamesT[counter1].equals("")) {
                        oldlen = counter1;
                        break;
                }
        }
        

    // Determine Status
        if ((isFilterToggle > 0) || (isPrefsToggle > 0) || (isScopeToggle > 0)) {
            if ((isServerBrowse < 0) && (isNodeBrowse < 0)) {
                status = "pop";
                if (oldlen > 0) {
                    newlen = oldlen - 1;
                } else {
                    newlen = oldlen;
                }
            }

        // Examine breadcrumb to determine if need to Pop
        } else {
            
            for (counter1=0; counter1<oldlen; counter1++) {
    
                //if ((fieldLevelHelpTopic.equals(bcpageidT[counter1])) && (!fieldLevelHelpTopic.equals("")))  { 
                    //|| (patternString.equals(bcnamesT[counter1]))) {

                //        newlen = counter1;
                //        for (counter2=newlen; counter2<bclen; counter2++) {
                //                bcnamesT[counter2] = "";
                //                bcpageidT[counter2] = "";
                //                if (newlen != counter2) {
                //                        bclinksT[counter2] = "";
                //               }
                //        }

                //        status = "pop";
                //        out.println("popped at "+counter1+", Name is "+message);
                //        break;
                       // }
                //} else {
                  if (message.equals(bcnamesT[counter1])) {
                        newlen = counter1;
                        for (counter2=newlen; counter2<bclen; counter2++) {
                                bcnamesT[counter2] = "";
                                bcpageidT[counter2] = "";
                                if (newlen != counter2) {
                                        bclinksT[counter2] = "";                                        
                                }
                        } 

                        status = "pop";
                        //out.println("popped at "+counter1+", Name is "+patternString);
                        break;
                  }

                //}
    
            }

        }
                
        if (status.equals("pop")) {
                ////////// Popped back 1 or more pages ////////////////////
                //if (oldlen-newlen > 1) {
                //}
                ///////// Popped to the same page /////////////////////////
                if (oldlen-newlen == 1) {
                        
                        //out.println("Same pop from "+oldlen+" to "+newlen+"<br>"+priorpage+"<br>"+altprior);   
                        
                        if (((bclinksT[newlen].indexOf(".do?EditAction=true") < 0) && (bclinksT[newlen].indexOf(".do?forwardName=") < 0)) && (bclinksT[newlen].indexOf(".do?action=") < 0) && (bclinksT[newlen].indexOf(".do?perspective=") < 0) && (bclinksT[newlen].indexOf("scopeShow=") < 0) && (bclinksT[newlen].indexOf("quickSearchState=") < 0) && (bclinksT[newlen].indexOf("show=") < 0)) {
                                if (((priorpage.indexOf(".do?EditAction=true") > -1) || (priorpage.indexOf(".do?forwardName=") > -1)) || (priorpage.indexOf(".do?action=") > -1) || (priorpage.indexOf(".do?perspective=") > -1) || (priorpage.indexOf("scopeShow=") > -1) || (priorpage.indexOf("quickSearchState=") > -1) || (priorpage.indexOf("show=") > -1)) {
                                        bclinksT[newlen] = priorpage;
                                } else if (((altprior.indexOf(".do?EditAction=true") > -1) || (altprior.indexOf(".do?forwardName=") > -1)) || (altprior.indexOf(".do?action=") > -1) || (altprior.indexOf(".do?perspective=") > -1) || (altprior.indexOf("scopeShow=") > -1) || (altprior.indexOf("quickSearchState=") > -1) || (altprior.indexOf("show=") > -1)) {
                                        bclinksT[newlen] = altprior;
                                } else if (altprior.indexOf(".do?EditAction=false") > -1) {
                                        bclinksT[newlen] = altprior;
                                } else {
                                        bclinksT[newlen] = "badURL";
                                }
                        } else {
                                //out.println("fell out back");
                        }                      
        
                } 
                
        } else { 
        
                newlen = oldlen;
                //////// Pushed a page onto the stack ////////////////////
                
                //out.println("pushed to "+newlen+"<br>Priorpage: "+priorpage+"<br>"+altprior);
                
                if (newlen >=1) {
        
                        if (((bclinksT[newlen-1].indexOf(".do?EditAction=true") < 0) && (bclinksT[newlen-1].indexOf(".do?forwardName=") < 0)) && (bclinksT[newlen-1].indexOf(".do?action=") < 0) && (bclinksT[newlen-1].indexOf(".do?perspective=") < 0) && (bclinksT[newlen-1].indexOf("scopeShow=") < 0) && (bclinksT[newlen-1].indexOf("quickSearchState=") < 0) && (bclinksT[newlen-1].indexOf("show=") < 0)) {
                                if (((priorpage.indexOf(".do?EditAction=true") > -1) || (priorpage.indexOf(".do?forwardName=") > -1)) || (priorpage.indexOf(".do?action=") > -1) || (priorpage.indexOf(".do?perspective=") > -1) || (priorpage.indexOf("scopeShow=") > -1) || (priorpage.indexOf("quickSearchState=") > -1) || (priorpage.indexOf("show=") > -1)) {
                                        bclinksT[newlen-1] = priorpage;
                                        //out.println("branch1 pushed to "+newlen+"<br>Priorpage: "+priorpage+"<br>"+altprior);
                                } else if (((altprior.indexOf(".do?EditAction=true") > -1) || (altprior.indexOf(".do?forwardName=") > -1)) || (altprior.indexOf(".do?action=") > -1) || (altprior.indexOf(".do?perspective=") > -1) || (altprior.indexOf("scopeShow=") > -1) || (altprior.indexOf("quickSearchState=") > -1) || (altprior.indexOf("show=") > -1)) {
                                        bclinksT[newlen-1] = altprior;
                                        //out.println("branch2 pushed to "+newlen+"<br>Priorpage: "+priorpage+"<br>"+altprior);
                                } else if (altprior.indexOf(".do?EditAction=false") > -1) {
                                        bclinksT[newlen-1] = altprior;
                                        //out.println("branch3 pushed to "+newlen+"<br>Priorpage: "+priorpage+"<br>"+altprior);
                                } else {
                                        bclinksT[newlen-1] = "badURL";
                                        //out.println("branch4 pushed to "+newlen+"<br>Priorpage: "+priorpage+"<br>"+altprior);
                                }
                        } else {
                                
                                if ((bclinksT[newlen-1].indexOf("refId=null") > 0) && (bclinksT[newlen-1].indexOf("resourceUri=null") > 0)) {
                                   if (((altprior.indexOf(".do?EditAction=true") > -1) || (altprior.indexOf(".do?forwardName=") > -1)) || (altprior.indexOf(".do?action=") > -1) || (altprior.indexOf(".do?perspective=") > -1) || (altprior.indexOf("scopeShow=") > -1) || (altprior.indexOf("quickSearchState=") > -1) || (altprior.indexOf("show=") > -1)) {
                                           bclinksT[newlen-1] = altprior;
                                           //out.println("branch5 pushed to "+newlen+"<br>Priorpage: "+priorpage+"<br>"+altprior);
                                   } 
                                }
                        }
                } else {
                        if (((bclinksT[newlen].indexOf(".do?EditAction=true") < 0) && (bclinksT[newlen].indexOf(".do?forwardName=") < 0)) && (bclinksT[newlen].indexOf(".do?action=") < 0) && (bclinksT[newlen].indexOf(".do?perspective=") < 0) && (bclinksT[newlen].indexOf("scopeShow=") < 0) && (bclinksT[newlen].indexOf("quickSearchState=") < 0) && (bclinksT[newlen].indexOf("show=") < 0)) {
                                if (((priorpage.indexOf(".do?EditAction=true") > -1) || (priorpage.indexOf(".do?forwardName=") > -1)) || (priorpage.indexOf(".do?action=") > -1) || (priorpage.indexOf(".do?perspective=") > -1) || (priorpage.indexOf("scopeShow=") > -1) || (priorpage.indexOf("quickSearchState=") > -1) || (priorpage.indexOf("show=") > -1)) {
                                        bclinksT[newlen] = priorpage;
                                } else if (((altprior.indexOf(".do?EditAction=true") > -1) || (altprior.indexOf(".do?forwardName=") > -1)) || (altprior.indexOf(".do?action=") > -1) || (altprior.indexOf(".do?perspective=") > -1) || (altprior.indexOf("scopeShow=") > -1) || (altprior.indexOf("quickSearchState=") > -1) || (altprior.indexOf("show=") > -1)) {
                                        bclinksT[newlen] = altprior;
                                } else if (altprior.indexOf(".do?EditAction=false") > -1) {
                                        bclinksT[newlen] = altprior;
                                } else {
                                        bclinksT[newlen] = "badURL";
                                }
                        }
                }                  

                ///////  Now do the back button check /////////////////////
                for (counter1=0; counter1<oldlen; counter1++) {
                        
                       backbutton = bclinksT[counter1];
                       tmpcount = counter1 + 1;
                                                                        
                        for (counter3=tmpcount; counter3<bclen; counter3++) {
                                //out.println("<p>comparing "+counter3+" to "+counter1);
                                if (bclinksT[counter3].equals(backbutton)) {
                        
                                        for (counter4=tmpcount; counter4<bclen; counter4++) {
                                                bcnamesT[counter4] = "";
                                                bcpageidT[counter4] = "";

                                                if (tmpcount != counter4) {
                                                        bclinksT[counter4] = "";                                                        
                                                                                                        
                                                }
                                        }
                                        status = "pop"; 
                                        //out.println("Back button popped at "+tmpcount);
                                        newlen = tmpcount;
                                        break;                                                 
                                
                                }
                        }
                        if (status.equals("pop")) {
                                break;
                        }
                                
                } 
        
        
        
        }         
        




       if (newlen >= 1) {
       
                if ((ismessagedetail < 0) && (ismessages < 0) && (isreturnfrommessage < 0)) {
                        out.println("<H3 id='bread-crumb'>");
                        for (counter1=0; counter1<newlen; counter1++) {
                                if (bclinksT[counter1].indexOf("badURL") < 0) {
                                        out.println("<a href='" + bclinksT[counter1] + "'>" + bcnamesT[counter1] + "</a> > ");
                                } else {
                                        out.println(bcnamesT[counter1] + " > ");
                                }
                        }
                        out.println("</H3>");
                }                
        
        }
                
        
        
        if ((ismessagedetail > -1) || (ismessages > -1) || (isreturnfrommessage > -1)) {
                session.setAttribute("bcnames", bcnamesT);
                session.setAttribute("bclinks", bclinksT);                
                session.setAttribute("bcpageid", bcpageidT);
        
        }
        
        else {
                if (isHome < 0) {
                        bcnamesT[newlen] = message;
                        session.setAttribute("bcnames", bcnamesT);
                        session.setAttribute("bclinks", bclinksT);
                        bcpageidT[newlen] = fieldLevelHelpTopic;
                        session.setAttribute("bcpageid", bcpageidT);

                        
                }
        }
        %>
        

<%

        if ((ismessagedetail > -1) && (isFilterToggle < 0) && (isPrefsToggle < 0) && (isScopeToggle < 0)) {
                out.println("<H3 id='bread-crumb'>");                           
                out.println("< <a href='" + priorpage + "'>"+backLink+"</a>");
                out.println("</H3>");
                
                
        }


%>
        

<H1 id="bread-crumb"> 
<% if (probdetSit > -1) { %>
<%=message%>
<% } else { %>
<bean:message key='<%=descTitle%>'/>
<% } %>
</H1>
   
<%
	if (fieldLevelHelpTopic == null)
	{
%>

<p class="instruction-text"><bean:message key='<%=descText%>'/></p>

<%
	}
	else
	{
%>

   <p class="instruction-text">
      <bean:message key='<%=descText%>'/>
      <ibmcommon:info image="help.additional.information.image" topic="<%=fieldLevelHelpTopic%>"/>
   </p>

<%
	}
%>
        
       

<%

        if (session.getAttribute("traceOptionValuesMap") != null) {
                session.removeAttribute("traceOptionValuesMap");
                session.removeAttribute("traceGroupsMap");
        }

%>


<a name="main"></a> 
