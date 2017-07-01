<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>



<%@ page import="java.util.*,org.apache.regexp.*,org.apache.struts.util.MessageResources,org.apache.struts.action.Action"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>

<ibmcommon:detectLocale/>

<%!
public String removeSpaces(String s) {
  StringTokenizer st = new StringTokenizer(s," ",false);
  String t="";
  while (st.hasMoreElements()) t += st.nextElement();
  return t;
  }

%>
 
<% 

        ServletContext servletContext = (ServletContext)pageContext.getServletContext();
        MessageResources messages = (MessageResources)servletContext.getAttribute(Action.MESSAGES_KEY);

        /////////////  Begin strings to be translated ////////////////////
        String groupsTabLabel = messages.getMessage(request.getLocale(),"trace.tree.groupsTabLabel");
        String componentsTabLabel = messages.getMessage(request.getLocale(),"trace.tree.componentsTabLabel");
        String pleaseWaitLabel = messages.getMessage(request.getLocale(),"trace.tree.pleaseWaitLabel");
        String traceSpecDirections = messages.getMessage(request.getLocale(),"trace.tree.traceSpecDirections");
        String applyButton = messages.getMessage(request.getLocale(),"trace.tree.applyButton");
        String closeButton = messages.getMessage(request.getLocale(),"trace.tree.closeButton");
        String allComponents = messages.getMessage(request.getLocale(),"trace.tree.allComponents");
        String allGroups = messages.getMessage(request.getLocale(),"trace.tree.allGroups");
        
        
        String allDisabled = messages.getMessage(request.getLocale(),"all.disabled");
        String entryExit = messages.getMessage(request.getLocale(),"entry.exit");
        String event = messages.getMessage(request.getLocale(),"event");
        String debug = messages.getMessage(request.getLocale(),"debug");
        String entryExitEvent = messages.getMessage(request.getLocale(),"entry.exit.event");
        String entryExitDebug = messages.getMessage(request.getLocale(),"entry.exit.debug");
        String eventDebug = messages.getMessage(request.getLocale(),"event.debug");
        String allEnabled = messages.getMessage(request.getLocale(),"all.enabled");
        
        ////////////   End strings to be translated //////////////////////


        RE comma = new RE(",");
        List traceOptionValuesMap = (List)session.getAttribute("traceOptionValuesMap");
        
        HashMap traceGroupsMap = (HashMap)session.getAttribute("traceGroupsMap");
        
        String treeType = "groups";
        if (request.getParameter("view") != null) { 
                treeType = request.getParameter("view");
        }
        
        String allStringOn = new String();
        String allStringOff = new String();
        
        if (treeType.equals("components")) { 
                allStringOn = allComponents;
                allStringOff = allGroups;
        } else {
                allStringOn = allGroups;
                allStringOff = allComponents;
        }
        
        String groupsList = new String();
        List NewGroupsList = new ArrayList();
        List SortedGroupsList = new ArrayList();
        List SortedCompsList = new ArrayList();
        
        for (Iterator i1 = traceGroupsMap.keySet().iterator(); i1.hasNext();)  {
                String value1 = (String) i1.next(); 
                String descr1 = (String) traceGroupsMap.get(value1);
                NewGroupsList.add(descr1+","+value1);
        }
        
        Comparator ascending =     new Comparator() {
            public int compare(Object o1, Object o2)
            {
                String s1 = (String)o1;
                String s2 = (String)o2;
                return s1.compareTo(s2);
            }};
        
        Collections.sort(NewGroupsList, ascending);

        int found = 0;
        int r=0;
        for (int y=0;y<NewGroupsList.size();y++) {
                String tmpgrp1 = NewGroupsList.get(y).toString();
                String[] tmpgrp2 = comma.split(tmpgrp1);
                for (r=0;r<SortedGroupsList.size();r++) {
                        if (tmpgrp2[0].equals(SortedGroupsList.get(r).toString())) {
                                found = 1; 
                                break;       
                        }
                }
                if (found == 0) {
                        SortedGroupsList.add(tmpgrp2[0]);
                        SortedCompsList.add(tmpgrp2[1]);        
                } else {
                        String tmpcomps =  SortedCompsList.get(r).toString() + "," + tmpgrp2[1];
                        SortedCompsList.set(r,tmpcomps);
                        found = 0;
                }
        }
        
        



%>

<html>
<head>

<jsp:include page = "/secure/layouts/browser_detection.jsp" flush="true"/>

<STYLE>
SELECT { font-size: 80% }
TD { font-size: 75%; background-color: #FFFFFF }
A { text-decoration: none; }
A:hover { text-decoration:underline }
.table-text A { color: #000000 }
.topology-view {  padding: 0px 0px 0px 0px;  font-family: Arial, Helvetica, sans-serif; font-size: 70%; background-color: #E2E2E2}
</STYLE>


</head>
<body class="topology-view" leftmargin="3" topmargin="2" marginwidth="0" marginheight="0">
<% if (treeType.equals("groups")) {  %>

    <table border="0" cellpadding="0" cellspacing="0"  width="100%" >
      <tr valign="top"> 
        <td class="blank-tab" width="1%" nowrap height="19">
          <img src="/admin/images/onepix.gif" width="4" height="27" align="absmiddle" alt=""> 
        </td>
        <td class="tabs-on" width="1%" nowrap height="19">
          <%=groupsTabLabel%>
        </td>  
        <td class="blank-tab" width="1%" nowrap height="19">
          <img src="/admin/images/onepix.gif" width="2" height="27" align="absmiddle" alt=""> 
        </td>
        <td class="tabs-off" width="1%" nowrap height="19">
          <a class="tabs-item" href="javascript:changeTabs('components')"><%=componentsTabLabel%></a>
        </td> 
        <td class="blank-tab" width="99%" nowrap height="19">
          <img src="/admin/images/onepix.gif" width="1" height="27" align="absmiddle">
        </td>
      </tr>
    </table>
    
<% } else { %>

    <table border="0" cellpadding="0" cellspacing="0"  width="100%" >
      <tr valign="top"> 
        <td class="blank-tab" width="1%" nowrap height="19">
          <img src="/admin/images/onepix.gif" width="4" height="27" align="absmiddle" alt=""> 
        </td>
        <td class="tabs-off" width="1%" nowrap height="19">
          <a class="tabs-item" href="javascript:changeTabs('groups')"><%=groupsTabLabel%></a>
        </td>  
        <td class="blank-tab" width="1%" nowrap height="19">
          <img src="/admin/images/onepix.gif" width="2" height="27" align="absmiddle" alt=""> 
        </td>
        <td class="tabs-on" width="1%" nowrap height="19">
          <%=componentsTabLabel%>
        </td> 
        <td class="blank-tab" width="99%" nowrap height="19">
          <img src="/admin/images/onepix.gif" width="1" height="27" align="absmiddle">
        </td>
      </tr>
    </table>

<% } %>



<div id='wait' style="position:absolute;width:120;visibility:visible;top:200;left:250;border: 0px black solid;padding:0px 0px 0px 0px;background-color:#999999;">
<table width='120' border=0 cellpadding=3 cellspacing=1 >
<tr><td class='table-text'><NOBR><img src='/admin/images/appInstall_animated.gif' align='texttop'><%=pleaseWaitLabel%></NOBR></td></tr>
</table>
</div>

<table border="0" cellpadding="5" cellspacing="0"  width="100%" >
<tr valign="top"> 
<td class="tray-text">
<%=traceSpecDirections%>
<hr size=1>
<form name='temp'>
<textarea name='tmpspec' rows='3' wrap='virtual'></textarea><br>
<input type='button' class='buttons' id='other' value='<%=applyButton%>' onclick='publishSpec()'>
<input type='button' class='buttons' id='other' value='<%=closeButton%>' onclick='self.close()'>
</form>
<hr size=1>
</td>
</tr>
</table>

<script>

function changeTabs(view) {

        opener.tempTraceSpec = document.temp.tmpspec.value;
        document.location = "trace_tree.jsp?view="+view;
        
}

</script>




<%

int itemCount = 0;
RE removeSpace = new RE("\\s");
String valuex = ""; 
String descrx = "";
int q = 0;

String ParentString = "";
String parent = "root";
String paren1 = "root";
String paren2 = "root";
String paren3 = "root";
String paren4 = "";
RE splitter = new RE("_");
String descr2 = "";
int newItems = 0;

RE psplit = new RE("~");


out.println("<div style='display:block' id='Item0' class='indent1'>");
out.println("<a href='#' name='treeitem' onclick=\"changeDIcon('0');return false\"><img src='/admin/images/trace_0.gif' name='Dicon0' align='texttop'  border='0' alt='"+allDisabled+"'>");
out.println(" * ("+allStringOn+")</a></div>");


if (treeType.equals("components")) {


        for (int k=1; k < traceOptionValuesMap.size(); k++) { 
                     String descr = (String)traceOptionValuesMap.get(k);
                     String thisparen = "";
                     int parlen = 0;
                     descr2 = descr;
                     descr = descr.replace('.','_');
                     descr = removeSpaces(descr); 
                     String thisparen1 = "";
                     String thisparen2 = "";
                     String thisparen3 = "";
                     String thisparen4 = "";
                     
                     if ((descr.indexOf("_") > -1) && (descr.indexOf("*") == -1)) {
                     
                        /////////  How many dots in this component //////////////////
                        String[] theparents = splitter.split(descr);
                        
                        parlen = theparents.length;
                        
                        if (parlen == 2) {
                                thisparen1 = theparents[0];
                                thisparen2 = theparents[1];
                        } 
                        if (parlen == 3) {
                                thisparen1 = theparents[0];
                                thisparen2 = theparents[1];
                                thisparen3 = theparents[2];                    
                        }
                        if (parlen > 3) {
                                thisparen1 = theparents[0];
                                thisparen2 = theparents[1];
                                thisparen3 = theparents[2];
                                thisparen4 = theparents[3];
                        }
                        
                        
                        if (parlen > 3) {
                                ///////////  If this current item has the same parent as the current parent  //////////////////
                                if (thisparen3.equals(paren3)) {
                                
                                        if (parlen == 4) { paren3 = thisparen3; }
                                        
                                        if ((parlen > 5) || (parlen == 5)) {
                                                thisparen4 = theparents[3];
                                                if (thisparen4.equals(paren4)) {
                                                       out.println("<div style='display:none' id='Item"+(k+newItems)+"' class='indent4'>");
                                                       out.println("<a href='#' name='treeitem' onclick=\"changeDIcon('"+(k+newItems)+"');return false\"><img src='/admin/images/trace_0.gif' name='Dicon"+(k+newItems)+"' align='texttop'  border='0' alt='"+allDisabled+"'>");
                                                       out.println(descr2+"</a></div>");
                                                       paren4 = thisparen4;
                                                }
                                                else {
                                                       paren4 = thisparen4;
                                                       out.println("<div style='display:none' id='Item"+(k+newItems)+"' class='indent3kids'><a href='#' onclick=\"expandCompressNode('"+(k+newItems)+"');return false\"><img src='/admin/images/lplus.gif' align='texttop' border='0' name='PM"+(k+newItems)+"'></a>");
                                                       out.println("<a href='#' name='treeitem' onclick=\"changeDIcon('"+(k+newItems)+"');return false\"><img src='/admin/images/trace_0.gif' name='Dicon"+(k+newItems)+"' align='texttop'  border='0' alt='"+allDisabled+"'>");
                                                       out.println(thisparen1+"."+thisparen2+"."+thisparen3+"."+thisparen4+".*</a></div>");                                                       
                                                       newItems = newItems + 1;
                                                       out.println("<div style='display:none' id='Item"+(k+newItems)+"' class='indent4'>");
                                                       out.println("<a href='#' name='treeitem' onclick=\"changeDIcon('"+(k+newItems)+"');return false\"><img src='/admin/images/trace_0.gif' name='Dicon"+(k+newItems)+"' align='texttop'  border='0' alt='"+allDisabled+"'>");
                                                       out.println(descr2+"</a></div>");
                                                }
                                        }
                                        else {
                                               out.println("<div style='display:none' id='Item"+(k+newItems)+"' class='indent3'>");
                                               out.println("<a href='#' name='treeitem' onclick=\"changeDIcon('"+(k+newItems)+"');return false\"><img src='/admin/images/trace_0.gif' name='Dicon"+(k+newItems)+"' align='texttop'  border='0' alt='"+allDisabled+"'>");
                                               out.println(descr2+"</a></div>");
                                        }
                
                                        
                                } else {
                                
                
                                       out.println("<div style='display:block' id='Item"+(k+newItems)+"' class='indent2kids'><a href='#' onclick=\"expandCompressNode('"+(k+newItems)+"');return false\"><img src='/admin/images/lplus.gif' align='texttop' border='0' name='PM"+(k+newItems)+"'></a>");
                                       out.println("<a href='#' name='treeitem' onclick=\"changeDIcon('"+(k+newItems)+"');return false\"><img src='/admin/images/trace_0.gif' name='Dicon"+(k+newItems)+"' align='texttop'  border='0' alt='"+allDisabled+"'>");
                                       out.println(thisparen1+"."+thisparen2+"."+thisparen3+".*</a></div>");
                                       newItems = newItems + 1;
                                       paren3 = thisparen3;
                                       if ((parlen > 5) || (parlen == 5)) {
                                                thisparen4 = theparents[3];
                                                if (thisparen4.equals(paren4)) {
                                                       out.println("<div style='display:none' id='Item"+(k+newItems)+"' class='indent4'>");
                                                       out.println("<a href='#' name='treeitem' onclick=\"changeDIcon('"+(k+newItems)+"');return false\"><img src='/admin/images/trace_0.gif' name='Dicon"+(k+newItems)+"' align='texttop'  border='0' alt='"+allDisabled+"'>");
                                                       out.println(descr2+"</a></div>");
                                                       paren4 = thisparen4;
                                                }
                                                else {
                                                       paren4 = thisparen4;
                                                       out.println("<div style='display:none' id='Item"+(k+newItems)+"' class='indent3kids'><a href='#' onclick=\"expandCompressNode('"+(k+newItems)+"');return false\"><img src='/admin/images/lplus.gif' align='texttop' border='0' name='PM"+(k+newItems)+"'></a>");
                                                       out.println("<a href='#' name='treeitem' onclick=\"changeDIcon('"+(k+newItems)+"');return false\"><img src='/admin/images/trace_0.gif' name='Dicon"+(k+newItems)+"' align='texttop'  border='0' alt='"+allDisabled+"'>");
                                                       out.println(thisparen1+"."+thisparen2+"."+thisparen3+"."+thisparen4+".*</a></div>");
                                                       newItems = newItems + 1;
                                                       out.println("<div style='display:none' id='Item"+(k+newItems)+"' class='indent4'>");
                                                       out.println("<a href='#' name='treeitem' onclick=\"changeDIcon('"+(k+newItems)+"');return false\"><img src='/admin/images/trace_0.gif' name='Dicon"+(k+newItems)+"' align='texttop'  border='0' alt='"+allDisabled+"'>");
                                                       out.println(descr2+"</a></div>");
                                                }
                                       }
                                       else {
                                               out.println("<div style='display:none' id='Item"+(k+newItems)+"' class='indent3'>");
                                               out.println("<a href='#' name='treeitem' onclick=\"changeDIcon('"+(k+newItems)+"');return false\"><img src='/admin/images/trace_0.gif' name='Dicon"+(k+newItems)+"' align='texttop'  border='0' alt='"+allDisabled+"'>");
                                               out.println(descr2+"</a></div>");
                                       }
                                
                                
                                
                                
                                }
                         }
                                
                         ////////////  There's only 2 or 3 items in this component //////////////////// 
                         if (parlen <= 3) {
                                               out.println("<div style='display:none' id='Item"+(k+newItems)+"' class='indent2'>");
                                               out.println("<a href='#' name='treeitem' onclick=\"changeDIcon('"+(k+newItems)+"');return false\"><img src='/admin/images/trace_0.gif' name='Dicon"+(k+newItems)+"' align='texttop'  border='0' alt='"+allDisabled+"'>");
                                               out.println(descr2+"</a></div>");

                         }
                          
                     }
                     ///////////  These are the single word components, not including the root //////////////////
                     else {
                     
                        if (descr.indexOf("*") == -1) {
                               out.println("<div style='display:block' id='Item"+(k+newItems)+"' class='indent2'>");
                               out.println("<a href='#' name='treeitem' onclick=\"changeDIcon('"+(k+newItems)+"');return false\"><img src='/admin/images/trace_0.gif' name='Dicon"+(k+newItems)+"' align='texttop'  border='0' alt='"+allDisabled+"'>");
                               out.println(descr2+"</a></div>");
                        }
                     }   
        
        
         }
 
} else {
        
        itemCount = 0;
        for (q=0;q<SortedGroupsList.size();q++) {
        
                descrx = (String)SortedGroupsList.get(q);
                String[] tmpvalue = comma.split(SortedCompsList.get(q).toString());
                
                itemCount = itemCount+1;
                String tmpdescrx = removeSpace.subst(descrx,"_");
                tmpdescrx = tmpdescrx.replace('.','_');
                     
                out.println("<div style='display:none' id='Item"+(itemCount)+"' class='indent2kids'><a href='#' onclick=\"expandCompressNode('"+(itemCount)+"');return false\"><img src='/admin/images/lplus.gif' align='texttop' border='0' name='PM"+(itemCount)+"'></a>");
                out.println("<a href='#' name='treeitem' onclick=\"changeDIcon('"+(itemCount)+"');return false\"><img src='/admin/images/trace_0.gif' name='Dicon"+(itemCount)+"' align='texttop'  border='0' alt='"+allDisabled+"'>");
                out.println(descrx+"</a></div>");

                for (int w=0;w<tmpvalue.length;w++) {
                       itemCount = itemCount+1;
                       String tmpvaluex = tmpvalue[w].replace('.','_');
                       out.println("<div style='display:none' id='Item"+(itemCount)+"' class='indent3'>");
                       out.println("<a href='#' name='treeitem' onclick=\"changeDIcon('"+(itemCount)+"');return false\"><img src='/admin/images/trace_0.gif' name='Dicon"+(itemCount)+"' align='texttop'  border='0' alt='"+allDisabled+"'>");
                       out.println(tmpvalue[w]+"</a></div>");
                } 
                
        }




}


%>




<script type="text/javascript" language="JavaScript1.2" src="/admin/scripts/aptree_trace.js"></script>

<script type="text/javascript" language="JavaScript1.2">




setShowExpanders(true);
setExpandDepth(1);
setKeepState(false);
setShowHealth(false);
plusIcon = "<%=request.getContextPath()%>/images/lplus.gif";
minusIcon = "<%=request.getContextPath()%>/images/lminus.gif";

setImagePath("<%=request.getContextPath()%>/images/");

setInTable(false);

setTargetFrame("detail");

</script>

<script>

//mainRoot = addRoot(" ");
root = addRoot("0", "* (<%=allStringOn%>)");




      

<%
ParentString = "";
parent = "root";
paren1 = "root";
paren2 = "root";
paren3 = "root";
paren4 = "";

descr2 = "";
newItems = 0;



if (treeType.equals("components")) {

        for (int n=1; n < traceOptionValuesMap.size(); n++) { 
                     String descr = (String)traceOptionValuesMap.get(n);
                     String thisparen = "";
                     int parlen = 0;
                     descr2 = descr;
                     descr = descr.replace('.','_');
                     descr = removeSpaces(descr); 
                     String thisparen1 = "";
                     String thisparen2 = "";
                     String thisparen3 = "";
                     String thisparen4 = "";
                     
                     if ((descr.indexOf("_") > -1) && (descr.indexOf("*") == -1)) {
                     
                        /////////  How many dots in this component //////////////////
                        String[] theparents = splitter.split(descr);
                        
                        parlen = theparents.length;
                        
                        if (parlen == 2) {
                                thisparen1 = theparents[0];
                                thisparen2 = theparents[1];
                        } 
                        if (parlen == 3) {
                                thisparen1 = theparents[0];
                                thisparen2 = theparents[1];
                                thisparen3 = theparents[2];                    
                        }
                        if (parlen > 3) {
                                thisparen1 = theparents[0];
                                thisparen2 = theparents[1];
                                thisparen3 = theparents[2];
                                thisparen4 = theparents[3];
                        }

                        
                        if (parlen > 3) {
                                if (thisparen3.equals(paren3)) {
                                        if (parlen == 4) { paren3 = thisparen3; }
                                        if ((parlen > 5) || (parlen == 5)) {
                                                thisparen4 = theparents[3];
                                                if (thisparen4.equals(paren4)) {
                                                       out.println(descr+" = addItem("+paren4+",'"+(n+newItems)+"','"+descr2+"');");
                                                       paren4 = thisparen4;
                                                }
                                                else {
                                                       paren4 = thisparen4;
                                                       
                                                       String ns1 = removeSpaces(thisparen4); 

                                                       out.println(ns1+" = addItem("+thisparen3+",'"+(n+newItems)+"','"+thisparen1+"."+thisparen2+"."+thisparen3+"."+thisparen4+".*');");
                                                       //out.println(thisparen4+" = addItem("+thisparen3+",'"+(n+newItems)+"','"+thisparen1+"."+thisparen2+"."+thisparen3+"."+thisparen4+".*');");
                                                       newItems = newItems + 1;
                                                       out.println(descr+" = addItem("+paren4+",'"+(n+newItems)+"','"+descr2+"');");
                                                }
                                        } else {
                                                out.println(descr+" = addItem("+paren3+",'"+(n+newItems)+"','"+descr2+"');");
                                        }
                
                
                                        
                                } else {
                                
                                       String ns2 = removeSpaces(thisparen3); 

                                       out.println(ns2+" = addItem(root,'"+(n+newItems)+"','"+thisparen1+"."+thisparen2+"."+thisparen3+".*');");
                                       //out.println(thisparen3+" = addItem(root,'"+(n+newItems)+"','"+thisparen1+"."+thisparen2+"."+thisparen3+".*');");
                                       newItems = newItems + 1;
                                       paren3 = thisparen3;
                                        if ((parlen > 5) || (parlen == 5)) {
                                                thisparen4 = theparents[3];
                                                if (thisparen4.equals(paren4)) {
                                                       out.println(descr+" = addItem("+paren4+",'"+(n+newItems)+"','"+descr2+"');");
                                                       paren4 = thisparen4;
                                                }
                                                else {
                                                       paren4 = thisparen4;

                                                       String ns3 = removeSpaces(thisparen4); 

                                                       out.println(ns3+" = addItem("+thisparen3+",'"+(n+newItems)+"','"+thisparen1+"."+thisparen2+"."+thisparen3+"."+thisparen4+".*');");
                                                       //out.println(thisparen4+" = addItem("+thisparen3+",'"+(n+newItems)+"','"+thisparen1+"."+thisparen2+"."+thisparen3+"."+thisparen4+".*');");
                                                       newItems = newItems + 1;
                                                       out.println(descr+" = addItem("+paren4+",'"+(n+newItems)+"','"+descr2+"');");
                                                }
                                        }
                                        else {
                                                out.println(descr+" = addItem("+paren3+",'"+(n+newItems)+"','"+descr2+"');");
                                        }
                                
                                
                                
                                
                                }
                                
                          }
                          

                                
                         ////////////  There's only 2 or 3 items in this component //////////////////// 
                         if (parlen <= 3) {
                                                 
                                out.println(descr+" = addItem(root,'"+(n+newItems)+"','"+descr2+"');");

                         }


                     }
                     else {
                        if (descr.indexOf("*") == -1) {
                                out.println(descr+" = addItem(root,'"+(n+newItems)+"','"+descr2+"');");
                        }
                     }   
                                 
        
         }
 
} else {
        
        itemCount = 0;
        
        for (q=0;q<SortedGroupsList.size();q++) {
                descrx = (String)SortedGroupsList.get(q);
                String[] tmpvalue = comma.split(SortedCompsList.get(q).toString());
                
                itemCount = itemCount+1;
                String tmpdescrx = removeSpace.subst(descrx,"_");
                tmpdescrx = tmpdescrx.replace('.','_');
                tmpdescrx = removeSpaces(tmpdescrx); 
                
                out.println(tmpdescrx+" = addItem(root,'"+itemCount+"','"+descrx+"');");

                for (int w=0;w<tmpvalue.length;w++) {
                       itemCount = itemCount+1;
                       String tmpvaluex = tmpvalue[w].replace('.','_');
                       tmpvaluex = removeSpaces(tmpvaluex);
                       out.println(tmpvaluex+" = addItem("+tmpdescrx+",'"+itemCount+"','"+tmpvalue[w]+"');");
                } 
                
        }



}
%>
          

</SCRIPT>


<script>

        document.write("<div id='progress' onclick=\"hideMenu();return false;\" style=\"position:absolute;width:200;visibility:hidden;top:0;left:0;border: 0px black solid;padding:0px 0px 0px 0px;background-color:#999999;\">");
        document.write("<table width='200' border=0 cellpadding=3 cellspacing=1 >");
        document.write("<tr><td class='table-text'><a href='#' onclick=\"changeLevel('0');return false;\"><img src='/admin/images/trace_0.gif' name='Ticon0' align='texttop'  border='0' alt='<%=allDisabled%>'> <%=allDisabled%></a></td></tr>");
        document.write("<tr><td class='table-text'><a href='#' onclick=\"changeLevel('1');return false;\"><img src='/admin/images/trace_1.gif' name='Ticon1' align='texttop'  border='0' alt='<%=entryExit%>'> <%=entryExit%> </a></td></tr>");        
        document.write("<tr><td class='table-text'><a href='#' onclick=\"changeLevel('2');return false;\"><img src='/admin/images/trace_2.gif' name='Ticon2' align='texttop'  border='0' alt='<%=event%>'> <%=event%> </a></td></tr>");
        document.write("<tr><td class='table-text'><a href='#' onclick=\"changeLevel('3');return false;\"><img src='/admin/images/trace_3.gif' name='Ticon3' align='texttop'  border='0' alt='<%=debug%>'> <%=debug%></a></td></tr>");
        document.write("<tr><td class='table-text'><a href='#' onclick=\"changeLevel('4');return false;\"><img src='/admin/images/trace_4.gif' name='Ticon4' align='texttop'  border='0' alt='<%=entryExitEvent%>'> <%=entryExitEvent%></a></td></tr>");
        document.write("<tr><td class='table-text'><a href='#' onclick=\"changeLevel('5');return false;\"><img src='/admin/images/trace_5.gif' name='Ticon5' align='texttop'  border='0' alt='<%=entryExitDebug%>'> <%=entryExitDebug%></a></td></tr>");
        document.write("<tr><td class='table-text'><a href='#' onclick=\"changeLevel('6');return false;\"><img src='/admin/images/trace_6.gif' name='Ticon6' align='texttop'  border='0' alt='<%=eventDebug%>'> <%=eventDebug%></a></td></tr>");
        document.write("<tr><td class='table-text'><a href='#' onclick=\"changeLevel('7');return false;\"><img src='/admin/images/trace_7.gif' name='Ticon7' align='texttop'  border='0' alt='<%=allEnabled%>'> <%=allEnabled%></a></td></tr>");
        document.write("</table>");
        document.write("</div>");

        



initialize();


document.onclick = wheresTheClick;


</script>




</body>
</html>
