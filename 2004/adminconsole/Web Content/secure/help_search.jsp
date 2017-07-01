<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@page language="java" import="com.ibm.ws.console.core.help.*,java.io.*,java.util.*,org.apache.regexp.*,org.apache.struts.util.MessageResources,org.apache.struts.action.Action"%>

<ibmcommon:detectLocale/>

<html:html locale="true">

<jsp:include page="layouts/browser_detection.jsp" flush="true"/>


<STYLE TYPE="text/css">
.help-docset {  padding-left: 5px; padding-right: 5px;   font-family: sans-serif; }
a {  text-decoration: none}
a:hover {  text-decoration: underline}
div.navtree {  font-size: 70%; font-family: sans-serif; }
layer.navtree { font-size: 70%; font-family: sans-serif; }
.helptree {   font-size: 70%; font-family: sans-serif; border-right: 1px solid #000000; background-color: #E2E2E2 }
FORM { padding-left: 10px }
div { padding-left: 3px }
p  { padding-left: 3px }
</STYLE>


  <BODY CLASS="helptree">    

  <table border="0" cellpadding="0" cellspacing="0"  width="108%" >
    <tr valign="top"> 
      <td class="blank-tab" width="1%" nowrap height="19">
        <img src="/admin/images/onepix.gif" width="2" height="27" align="absmiddle" alt=""> 
      </td>
	   <td class="tabs-off" width="1%" nowrap height="19">
        <a class="tabs-item" href="/admin/secure/help_navigation.jsp"><bean:message key="help.navigation.index"/></a>
      </td>  
      <td class="blank-tab" width="1%" nowrap height="19">
        <img src="/admin/images/onepix.gif" width="2" height="27" align="absmiddle" alt=""> 
      </td>
	   <td class="tabs-on" width="1%" nowrap height="19">
        <bean:message key="help.navigation.search"/>
      </td> 
      <td class="blank-tab" width="1%" nowrap height="19">
        <img src="/admin/images/onepix.gif" width="2" height="27" align="absmiddle" alt=""> 
      </td>
      <td class="blank-tab" width="99%" nowrap height="19">
        <img src="/admin/images/onepix.gif" width="1" height="27" align="absmiddle">
      </td>
    </tr>
  </table>


<html:form action="helpSearch.do" name="helpSearchForm" type="com.ibm.ws.console.core.help.HelpSearchForm">

<jsp:useBean id="helpSearchForm" scope="session" class="com.ibm.ws.console.core.help.HelpSearchForm"/>

<%
    List results = null;
    int totalCount = 0;
    if (request.getParameter ("searchTerm") != null)
    {
        String tempSrchTerm = request.getParameter ("searchTerm");
        if (tempSrchTerm != null && !tempSrchTerm.trim().equals(""))
        {
            helpSearchForm.setSearchTerm (tempSrchTerm);
		      Locale locale = (Locale)session.getAttribute(Action.LOCALE_KEY);
	         MessageResources messages = (MessageResources)application.getAttribute(Action.MESSAGES_KEY);
            SearchHelper searchHelper = new SearchHelper (locale, messages);
            searchHelper.searchHelpFiles (tempSrchTerm.toLowerCase(), getServletContext());
            results = searchHelper.getResultsList();
            totalCount = searchHelper.getTotalCount();
            request.setAttribute ("HelpSearchResults", results);
            request.setAttribute ("HelpSearchTotalCount", new Integer (totalCount));
        }
    }
    else
    {
        results = (List) request.getAttribute ("HelpSearchResults");
        Integer totalCountInteger = (Integer) request.getAttribute ("HelpSearchTotalCount");
        totalCount = (totalCountInteger == null ? 0 : totalCountInteger.intValue());
        helpSearchForm.setSearchTerm ("");
    }
%>      

  <p><b><bean:message key="help.navigation.search.terms"/></b><br>
  <nobr><html:text name="helpSearchForm" property="searchTerm" size="15"/>
    <html:submit property="searchAction"><bean:message key="help.navigation.search"/></html:submit>
  </nobr>
  <br><bean:message key="help.navigation.search.wildcards"/>


<%
    if (results == null) {}
    else if (results.size() == 0)
    {
%>

  <p><bean:message key="help.navigation.search.noresults"/></p>

<% 
    }
    else
    {
%>

  <p><b><bean:message key="help.navigation.search.results"/></b><hr size="1">

<%
        if (totalCount < 200)
        {
%>

  <div><bean:message key="help.navigation.search.totalfiles"/>&nbsp;<%=totalCount%></div></p>

<%
        }
        else
        {
%>

  <p><bean:message key="help.navigation.search.toomanymatches"/></p>

<%
        }
%>

  <logic:iterate id="result" name="HelpSearchResults"> 

<%

  String resultString = (String) result;

%>

  <%=resultString%>
  <p>
  </logic:iterate>

<%
    }
%>

<%--  // remove this line to uncomment Michael's search
<% 
// Start Michael Etgen's original code.  This section can be uncommented to run Michael's search and display his results.
out.println ("<br><br>");

/////// Translatable Text ////////////////
String numMatchesString = "Number of matches in each document:";
String tooManyMatchesString = "There were more than 200 files with matches.  Please try a more specific search term.";
String resultsString = "Results";
String noResultsString = "No results found";
String totalMatchesString = "Total files with matches:";
//////////////////////////////////////////

String srchterm = request.getParameter("searchTerm");
String theterm = "";
if (srchterm != null) {
        theterm = srchterm.toLowerCase();
}
 
RE regexp1 = new RE("[?|*|%]");
String thetermRE = regexp1.subst(theterm, "(.?)"); 
RE regexp2 = new RE("\\s");
thetermRE = regexp2.subst(thetermRE, "(.?)"); 

String thepage = request.getRequestURI();

%>
<p ><b>Search term(s): </b><br>
<nobr><input type="text" name="searchTerm" size="15" value="<%=theterm%>" />
<input name="go" type="submit" class="buttons" id="other" value="Search"/></nobr>
<br>(Valid wildcards: *,%,?)
<%
            if (!theterm.equals(""))
            {
                ServletContext context = getServletContext(); 
                List resultsList = new ArrayList();
                List finalList = new ArrayList();
                int foundone = 0;
                int getnext = 0;
                int starttabletag = -1;
                String thedir = "secure";
                RE newtermRE = new RE(thetermRE); 
                String thefile = new String();
                int ishtml = -1;
                int inHere = -1;
                String theTitle = "";
                int thecount = 0;
                int startTitle = -1;
                int endTitle = -1;
                String file = "";
                String r, s, news, linkfile = new String();
                int totalcount = 0;

                File dirx = new File(context.getRealPath("images/"));
                File[] numx=dirx.listFiles();
                int NumberOfFilesx=numx.length;
                String newdirx = numx[0].toString();
                int imagedir = newdirx.indexOf("\\images");
                String installpath = newdirx.substring(0,imagedir);

                File diry = new File(installpath);
                File[] numy=diry.listFiles();
                int NumberOfFilesy=numy.length;
                for (int g=0;g<NumberOfFilesy;g++)
                {
                    String newdiry = numy[g].toString();
                    int iscom = newdiry.indexOf("com.ibm");
                    if (iscom > -1)
                    {
                        //out.println(newdiry+"<br>");
                        File dira = new File(newdiry);
                        File[] numa=dira.listFiles();
                        int NumberOfFilesa=numa.length;
                        for (int t=0;t<NumberOfFilesa;t++)
                        {
                            if (totalcount > 200)
                            {
                                break;
                            }
                            String newdira = numa[t].toString();
                            //out.print(thedir+newdira+"<br>");
                            if (numa[t].isDirectory())
                            {
                                File dirb = new File(newdira);
                                File[] numb=dirb.listFiles();
                                int NumberOfFilesb=numb.length;
                                for (int u=0;u<NumberOfFilesb;u++)
                                {
                                    if (totalcount > 200)
                                    {
                                        break;
                                    }
                                    String newdirb = numb[u].toString();
                                    //out.print("<pre>     "+newdirb+"</pre><br>");
                                    if (numb[u].isDirectory())
                                    {
                                        File dirc = new File(newdirb);
                                        File[] numc=dirc.listFiles();
                                        int NumberOfFilesc=numc.length;
                                        for (int v=0;v<NumberOfFilesc;v++)
                                        {
                                            if (totalcount > 200)
                                            {
                                                break;
                                            }
                                            String newdirc = numc[v].toString();
                                            //out.print("<pre>             "+newdirc+"</pre><br>");
                                            thefile = newdirc;
                                            ishtml = thefile.indexOf(".html");
                                            if (ishtml > -1)
                                            {
                                                inHere = -1;
                                                theTitle = "";
                                                thecount = 0;
                                                startTitle = -1;
                                                endTitle = -1;
                                                file = thefile;
                                                s = "";
                                                try
                                                {
                                                    FileInputStream in = new FileInputStream(file);
                                                    //DataInputStream dis = new DataInputStream(in);
                                                    byte[] buf = new byte[in.available()];
                                                    in.read(buf);
                                                    in.close();
                                                    s = new String(buf);

                                                    startTitle = s.indexOf("<title>");
                                                    endTitle = s.indexOf("</title>");
                                                    if (startTitle > -1)
                                                    {
                                                        theTitle = s.substring(startTitle+7,endTitle); 
                                                    }
                                                    s = s.toLowerCase();
                                                    String[] matchresult = newtermRE.split(s);
                                                    thecount = matchresult.length - 1;

                                                    if (thecount > 0)
                                                    {
                                                        foundone = 1;
                                                        totalcount += 1;
                                                        int lf1 = thefile.indexOf("\\adminconsole.war\\");
                                                        linkfile = thefile.substring(lf1+17);
                                                        linkfile = linkfile.replace('\\','/');
                                                        r = "<div><a href='/admin"+linkfile+"' target='HelpDetail'><img src='/admin/images/file.gif' border='0' align='texttop'> "+theTitle+"</a></div>";
                                                        int nowsize = resultsList.size();
                                                        String isFound = "nope";
                                                        for ( int j = 0; j < nowsize; j++ )
                                                        {
                                                            String tmpr = (String)resultsList.get(j);

                                                            int tmpcstart = tmpr.indexOf("<b>") + 3;
                                                            int tmpcstop = tmpr.indexOf("</b>");
                                                            String tmpr1 = tmpr.substring(tmpcstart,tmpcstop);
                                                            int tc = Integer.parseInt(tmpr1);
                                                            if (tc == thecount)
                                                            {
                                                                resultsList.set(j,tmpr+r);
                                                                isFound = "yup";
                                                                break;
                                                            }
                                                        }
                                                        if (isFound.equals("nope"))
                                                        {
                                                            r = "<div>"+numMatchesString+" <b>"+thecount+"</b></div>" + r;
                                                            resultsList.add(r);
                                                        }
                                                    }
                                                }
                                                catch (Exception e)
                                                {
                                                    //out.print("File read: " +e);
                                                }
                                            }
                                        }
                                    }
                                    else
                                    {
                                        thefile = newdirb;
                                        ishtml = thefile.indexOf(".html");
                                        if (ishtml > -1)
                                        {
                                            inHere = -1;
                                            theTitle = "";
                                            thecount = 0;
                                            startTitle = -1;
                                            endTitle = -1;
                                            file = thefile;
                                            s = "";
                                            try
                                            {
                                                FileInputStream in = new FileInputStream(file);
                                                //DataInputStream dis = new DataInputStream(in);
                                                byte[] buf = new byte[in.available()];
                                                in.read(buf);
                                                in.close();
                                                s = new String(buf);

                                                startTitle = s.indexOf("<title>");
                                                endTitle = s.indexOf("</title>");
                                                if (startTitle > -1)
                                                {
                                                    theTitle = s.substring(startTitle+7,endTitle); 
                                                }
                                                s = s.toLowerCase();
                                                String[] matchresult = newtermRE.split(s);
                                                thecount = matchresult.length - 1;
                                                if (thecount > 0)
                                                {
                                                    foundone = 1;

                                                    totalcount += 1;
                                                    int lf1 = thefile.indexOf("\\adminconsole.war\\");

                                                    linkfile = thefile.substring(lf1+17);
                                                    linkfile = linkfile.replace('\\','/');

                                                    r = "<div><a href='/admin"+linkfile+"' target='HelpDetail'><img src='/admin/images/file.gif' border='0' align='texttop'> "+theTitle+"</a></div>";

                                                    int nowsize = resultsList.size();
                                                    String isFound = "nope";
                                                    for ( int j = 0; j < nowsize; j++ )
                                                    {
                                                        String tmpr = (String)resultsList.get(j);

                                                        int tmpcstart = tmpr.indexOf("<b>") + 3;
                                                        int tmpcstop = tmpr.indexOf("</b>");
                                                        String tmpr1 = tmpr.substring(tmpcstart,tmpcstop);
                                                        int tc = Integer.parseInt(tmpr1);
                                                        if (tc == thecount)
                                                        {
                                                            resultsList.set(j,tmpr+r);
                                                            isFound = "yup";
                                                            break;
                                                        }
                                                    }
                                                    if (isFound.equals("nope"))
                                                    {
                                                        r = "<div>"+numMatchesString+" <b>"+thecount+"</b></div>" + r;
                                                        resultsList.add(r);
                                                    }
                                                }
                                            }
                                            catch (Exception e)
                                            {
                                                //out.print("File read: " +e);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (foundone == 0)
                {
                    out.print("<p>"+noResultsString+"</p>");        
                }
                else
                {
                    out.print("<p><b>"+resultsString+"</b><hr size='1'>");
                    if (totalcount < 200)
                    {
                        out.print("<div>"+totalMatchesString+totalcount+"</div></p>");
                    }
                    else
                    {
                        out.print("<p>"+tooManyMatchesString+"</p>");
                    }
                    String swap = "nope";
                    int currsize = resultsList.size();
                    for ( int k = 0; k < currsize; k++ )
                    {
                        swap = "nope";
                        //out.print("<br>k is "+k);
                        for ( int m = 0; m < (currsize-k-1); m++ )
                        {

                            String tmpr1 = (String)resultsList.get(m);
                            int tmpcstart1 = tmpr1.indexOf("<b>") + 3;
                            int tmpcstop1 = tmpr1.indexOf("</b>");
                            String tmpr1a = tmpr1.substring(tmpcstart1,tmpcstop1);
                            int tc1 = Integer.parseInt(tmpr1a);
                            String tmpr2 = (String)resultsList.get(m+1);
                            int tmpcstart2 = tmpr2.indexOf("<b>") + 3;
                            int tmpcstop2 = tmpr2.indexOf("</b>");
                            String tmpr2a = tmpr2.substring(tmpcstart2,tmpcstop2);
                            int tc2 = Integer.parseInt(tmpr2a);
                            //out.print("<br>m is "+m);
                            //out.print("<br>comparing "+tc1+" and "+tc2);
                            if (tc1 < tc2)
                            {
                                resultsList.set(m,tmpr2);
                                resultsList.set(m+1,tmpr1);
                                swap = "yup";
                                //out.print("<br>at "+currsize+" swapping "+tc1+" and "+tc2);
                            }
                        }
                        if (swap.equals("nope"))
                        {
                            break;
                        }
                    }
                    for ( int i = 0; i < resultsList.size(); i++ )
                    {
                        out.println( "   " +resultsList.get( i ) );
                        out.println("<p>");
                    }
                }  //end array has something in it
            }  //end if theterm has something in it
    // End Michael's original search code
%>
// remove this line and the next to uncomment Michael's search   
--%>

</html:form>

  </body>
</html:html>


