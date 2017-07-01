<%-- IBM Confidential OCO Source Material --%>
<%-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 1997, 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.*,org.apache.struts.action.*,org.apache.struts.util.*"%>
<%@ page import="com.ibm.ws.console.servermanagement.pmiservice.*"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute name="formAction" classname="java.lang.String" />
<tiles:useAttribute name="formName" classname="java.lang.String" />
<tiles:useAttribute name="formType" classname="java.lang.String" />

                    
<% 
    MessageResources messages = (MessageResources) application.getAttribute(Action.MESSAGES_KEY);
    //System.out.println("messages " +  messages);
    Locale locale = request.getLocale();
    String invalidSetting = messages.getMessage(locale,"PMIService.invalidSetting");
    String thevalidSettings = messages.getMessage(locale,"PMIService.thevalidSettings");
    String LevelNone = messages.getMessage(locale,"PMIService.LevelNone");
    String LevelStandard  = messages.getMessage(locale,"PMIService.LevelStandard");
    String LevelCustom   = messages.getMessage(locale,"PMIService.LevelCustom");


    PMIServiceDetailForm form = (PMIServiceDetailForm) session.getAttribute("com.ibm.ws.console.servermanagement.PMIServiceDetailForm"); 
    List modulesList = form.getModulesList();
    List levelsList = form.getLevelsList();
 %>
  
<html:form action="<%=formAction%>" name="<%=formName%>" type="<%=formType%>">  
 
       
<table class="framing-table"  border="0" cellspacing="1" cellcpadding="3" width="100%">

<NOSCRIPT>
            <tr valign="top"> 
                  <th class="column-head-name" nowrap> 
                  <bean:message key="PMIService.modules"/>
                  </th>
                  <th class="column-head-name" colspan="2" nowrap > 
                    <bean:message key="PMIService.monitoringLevel"/>
                  </td>             
                  <th class="column-head-name" nowrap > 
                    <bean:message key="PMIService.specification"/>
                  </th>           
             </tr>
                <tr valign="top"> 
                  <td class="table-text" nowrap  valign="top"> 
                   <html:select property="pmiModule" name="<%=formName%>"  size="5" multiple="true"> 
                  
                     <%
                     Iterator modulesIter = modulesList.iterator();
                     while (modulesIter.hasNext())
                     { 
                        String descr = (String)modulesIter.next();
                        String value = descr;   
                     %>
                     
                         <html:option value="<%=value%>"><%=descr%></html:option>
                     
                    <% } %>
               
               </html:select>
               </td>
                <td class="table-text" nowrap  valign="top"> 
                <html:select property="pmiLevel" name="<%=formName%>" styleId="aselect"> 
                  
                     <%
                     Iterator levelsIter = levelsList.iterator();
                     while (levelsIter.hasNext())
                     { 
                        String descr = (String)levelsIter.next();
                        String value = descr;   
                     %>
                     
                         <html:option value="<%=value%>"><%=descr%></html:option>
                     
                    <% } %>
               
               </html:select>

                  </td>
                  <td class="table-text" nowrap   valign="top"> 
                        <input type="submit" name="runtimeAdd"  value="<bean:message key="servermanagement.button.add"/>" class="buttons" id="other">
                  </td>

                  <td class="table-text" nowrap >
</NOSCRIPT>

<SCRIPT>


document.write('<tr><td valign="top"   class="table-text"><bean:message key="PMIService.specification"/></td><td nowrap class="table-text" colspan="3">');
document.write('<input onkeypress="specLook(0)" onclick="specLook(0)" type="radio" value="" name="pmispec"><%=LevelNone%><br>');     
document.write('<input onkeypress="specLook(1)" onclick="specLook(1)" type="radio" value="beanModule" name="pmispec"><%=LevelStandard%><br>');
document.write('<input  type="radio" value="None" name="pmispec"><%=LevelCustom%>');
if (document.layers) {  
document.write('<blockquote>');
} else {
document.write('<br><br><img src="/admin/images/blank20.gif" alt="" width="12" height="20"/>');
}

</SCRIPT>
                     <html:textarea property="spec"  rows="5" cols="25" onblur="if (!document.layers){specLook(-1)}"/>
 
<%



        String standardSpec1 = "";
        String standardSpec2 = "";
        String noneSpec1 = "";
        String noneSpec2 = "";        
        String current = "";
        int t=0;
        int thelen = modulesList.size();
        for (t=0;t<thelen;t++) {
                current = (String)modulesList.get(t);
                if (t < (thelen-1)) {
                        standardSpec1 = standardSpec1 + current + "=H,"; 
                        noneSpec1 = noneSpec1 + current + "=N,";        
                } 
                else {
                        standardSpec1 = standardSpec1 + current + "=H"; 
                        noneSpec1 = noneSpec1 + current + "=N";        
                }
        } 
%>
                    
<SCRIPT>
if (document.layers) {  
document.write('</blockquote>');
} 

function itsReset(e) {
    if (isIE) {
        if (isDom) {
            elm = e.target.name;
        } else {
            elm = window.event.srcElement.name;
        }
    }  else {
        elm = e.target.name;
    }
    if (elm == "reset") {
        specLook();
        return false;
    }
}

function specLook(state) {
        var currSpec = document.forms["<%=formName%>"].spec.value;

        var currSpec = currSpec.replace(/[\n\r\f]+/g,",");
        //var currSpec = currSpec.replace(/[\t\s]+/g,"");

        
        

        if (currSpec.charAt(0) == ",") {
                currSpec = currSpec.substring(1,currSpec.length);                
        }
        if (currSpec.charAt(currSpec.length-1) == ",") {
                currSpec = currSpec.substring(0,currSpec.length-1);                
        }
        var currentSpec = currSpec.split(",");
        

        /////////////  Changed to make whatever mbean gives 
        /////////////  as modules the standard ones to use
        var stanSpec1 = "";
        var noSpec1 = "";
        for (i=0;i<currentSpec.length;i++) {
                tmpcs = currentSpec[i].split("=");
                stanSpec1 = stanSpec1 + tmpcs[0]+"=H,";
                noSpec1 = noSpec1 + tmpcs[0]+"=N,";
        }
        
        if (stanSpec1.charAt(0) == ",") {
                stanSpec1 = stanSpec1.substring(1,stanSpec1.length);                
        }
        if (stanSpec1.charAt(stanSpec1.length-1) == ",") {
                stanSpec1 = stanSpec1.substring(0,stanSpec1.length-1);                
        }
        var standardSpec1 = stanSpec1.split(",");
        standardSpec1 = standardSpec1.sort();
        
        if (noSpec1.charAt(0) == ",") {
                noSpec1 = noSpec1.substring(1,noSpec1.length);                
        }
        if (noSpec1.charAt(noSpec1.length-1) == ",") {
                noSpec1 = noSpec1.substring(0,noSpec1.length-1);                
        }
        var noneSpec1 = noSpec1.split(",");
        noneSpec1 = noneSpec1.sort();
        

        currentSpec = currentSpec.sort();
        
        
        var noneCount = 0;
        var highCount = 0;
        var customCount = 0;
        for (r=0;r<currentSpec.length;r++) {
                csl = currentSpec[r].split("=");
                csl[1] = csl[1].replace(/[\t\s]+/g,"");
                if (csl[1] == "N") {
                        noneCount = noneCount + 1;
                }
                else if (csl[1] == "H") {
                        highCount = highCount + 1;
                }
                else {
                        if ((csl[1] != "L") && (csl[1] != "M") && (csl[1] != "X")) {
                                if (csl[1] == "") {
                                        return;
                                }
                                else {      
                                        alert(csl[0]+"="+csl[1]+"\n\n<%=invalidSetting%>\n\n<%=thevalidSettings%>");
                                        return;
                                }
                        }
                        else {
                                customCount = customCount + 1;
                        }
                }
                currentSpec[r] = csl[0]+"="+csl[1];
        }
        
        standardSpec1String = standardSpec1.toString();
        //standardSpec1String = standardSpec1String.replace(/[\t\s]+/g,"");
        standardSpec1String = standardSpec1String.replace(/,+/g,"\n");

        noneSpec1String = noneSpec1.toString();
        //noneSpec1String = noneSpec1String.replace(/[\t\s]+/g,"");
        noneSpec1String = noneSpec1String.replace(/,+/g,"\n");

        currentSpecString = currentSpec.toString();
        //currentSpecString = currentSpecString.replace(/[\t\s]+/g,"");
        currentSpecString = currentSpecString.replace(/,+/g,"\n");

        
        if (state < 0) {

                if (currentSpecString == noneSpec1String) {
                        document.forms["<%=formName%>"].pmispec[0].checked = true;
                }
                else if (currentSpecString == standardSpec1String) {
                        document.forms["<%=formName%>"].pmispec[1].checked = true;
                }
                else {
                        document.forms["<%=formName%>"].pmispec[2].checked = true;
                }
                document.forms["<%=formName%>"].spec.value = currentSpecString;                

        }
        else if (state == 0) {
                document.forms["<%=formName%>"].spec.value = noneSpec1String;
        }
        else {
                document.forms["<%=formName%>"].spec.value = standardSpec1String; 
                       
        }


}


specLook(-1);

document.onclick = itsReset;


</SCRIPT>
                  </td>
                  <td valign="top" class="table-text">
                    <bean:message key="PMIService.initialSpecLevel.description"/>
                  </td>
                </tr>
                



               <tr>
              <td class="button-section" colspan="5" >
                <input type="submit" name="runtimeSave" value="<bean:message key="button.ok"/>" class="buttons" id="navigation">
              </td>
              </tr>
        </table>
      
        
  
</html:form>        
