<%-- IBM Confidential OCO Source Material --%>
<%-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 1997, 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>


<%@ page import="java.util.*, org.apache.regexp.*"%>

<%
   
String[] langarray = { "de","en","es","fr","it","ja","ko","pt","zh-cn", "zh-tw" }; 
  
double multiplier = 0.7; 
String browserPlatform = "";
double browserPlatformM = 1.0;
String browserJava = "";
double browserJavaM = 1.0;
String browserLocale = "";
double browserLocaleM = 1.0;
String theagent = request.getHeader("USER-AGENT");
theagent = theagent.toLowerCase();
String thelocale = "";
if (request.getHeader("ACCEPT-LANGUAGE")==null) {
        thelocale = "en";
} else {
        thelocale = request.getHeader("ACCEPT-LANGUAGE").toLowerCase();
}


RE splitter = new RE(",");
String[] theirlangs = splitter.split(thelocale);
int theirlen = theirlangs.length;
int ourlangs = langarray.length;
String currlang = "";

for (int y=0; y<theirlen;y++) {

        currlang = theirlangs[y];
        
        int weight = currlang.indexOf(";q=");
        if (weight > -1) {
                currlang = currlang.substring(0,weight).trim();
        }

        int country = currlang.indexOf("-");
        if ((country > -1) && (currlang.indexOf("zh") < 0)) {
                currlang = currlang.substring(0,country).trim();
        }                

        for (int z=0; z<ourlangs;z++) {
                String tempourlang = langarray[z];
                if (currlang.equals(tempourlang)) {
                        browserLocale = tempourlang;
                        break;
                                                        
                        
                }
        }
        if (!browserLocale.equals("")) {
                break;
        }

               
}

%>


<%

if (null != theagent && -1 != theagent.indexOf("windows")) {
        browserPlatform = "NT";
        browserPlatformM = 1.0;

}
else if (null != theagent && -1 != theagent.indexOf("aix")) {
        browserPlatform = "AIX";        
        browserPlatformM = 1.2;
}
else if (null != theagent && -1 != theagent.indexOf("sunos")) {
        browserPlatform = "SOLARIS";        
        browserPlatformM = 1.3;
}
else if (null != theagent && -1 != theagent.indexOf("linux")) {
        browserPlatform = "LINUX";        
        browserPlatformM = 1.2;
}
else if (null != theagent && -1 != theagent.indexOf("hp_ux")) {
        browserPlatform = "HP_UX";        
        browserPlatformM = 1.2;
}
else {
        browserPlatform = "NT";        
        browserPlatformM = 1.0;
}
%>

<%

if (null != theagent && -1 != theagent.indexOf("msie")) {
        browserJava = "IE";        
        browserJavaM = 1.0;
}
else if (null != theagent && -1 != theagent.indexOf("gecko")) {
        browserJava = "GECKO";        
        browserJavaM = 1.0;
}
else if (null != theagent && -1 != theagent.indexOf("opera")) {
        browserJava = "OPERA";        
        browserJavaM = 1.0;
}
else {
        browserJava = "NETSCAPE";        
        browserJavaM = 1.0;
}


%>




<%

if (browserLocale.equals("zh-cn")) {
        browserLocale = "zh";        
        browserLocaleM = 1.2;
}
else if (browserLocale.equals("zh-tw")) {
        browserLocale = "zh_TW";        
        browserLocaleM = 1.2;
}
else if (browserLocale.equals("fr")) {
        browserLocale = "fr";        
        browserLocaleM = 1.0;
}
else if (browserLocale.equals("de")) {
        browserLocale = "de";
        browserLocaleM = 1.0;
}
else if (browserLocale.equals("en")) {
        browserLocale = "en";
        browserLocaleM = 1.0;
}
else if (browserLocale.equals("it")) {
        browserLocale = "it";
        browserLocaleM = 1.0;
}
else if (browserLocale.equals("ja")) {
        browserLocale = "ja";
        browserLocaleM = 1.1;
}
else if (browserLocale.equals("ko")) {
        browserLocale = "ko";
        browserLocaleM = 1.1;
}
else if (browserLocale.equals("pt")) {
        browserLocale = "pt";
        browserLocaleM = 1.0;
}
else if (browserLocale.equals("es")) {
        browserLocale = "es";
        browserLocaleM = 1.0;
}
else {
        browserLocale = "en";
        browserLocaleM = 1.0;
}


%>


<%
        multiplier = browserLocaleM * browserJavaM * browserPlatformM;
%>


<style type="text/css">

<%
        out.println("/* The browser agent is "+browserJava+" */");
        out.println("/* The agent locale is "+browserLocale+" */");
        out.println("/* The agent OS is "+browserPlatform+" */");
        
        out.println("/* The font size multiplier is "+multiplier+" */");
        

%>

/*  LOGIN PAGE */

<% if (browserJava.equals("NETSCAPE")) { %>
.login {  text-align: center; }
<% } else if (browserJava.equals("GECKO")) { %>
.login {  padding-left: 30%; }
<% } else { %>
.login {  text-align: center; }
<% } %>
.login-button-section {  padding-left: 0px; font-family: Arial,Helvetica, sans-serif;  font-weight:normal; color: #000000; background-color:#CCCCCC; background-image: none;  } 


/* BANNER PAGE */

.header {  margin-top: 0px; margin-right: 0px; margin-bottom: 0px; margin-left: 0px ; font-family: Arial, Helvetica, sans-serif; border-bottom: 1px solid black; }
.top-navigation { color: #000000; font-size:<%=(70*multiplier)%>%; background-color:#ADB0EC; font-family: Arial,Helvetica, sans-serif; padding-left: 10px; padding-right: 5px;}  
.top-nav-item  { color: #000000; font-family: Arial,Helvetica,sans-serif; font-weight:bold; text-decoration: none  } 
a.top-nav-item  { color: #000000; font-family: Arial,Helvetica,sans-serif; font-weight:bold;  } 
a:active.top-nav-item  { color: #000000; font-family: sans-serif; }  
a:hover.top-nav-item {  text-decoration: underline}
<% if (browserJava.equals("GECKO")) {  %>
.header {  margin-top: 0px; margin-right: 0px; margin-bottom: 0px; margin-left: 0px ; font-family: Arial, Helvetica, sans-serif; border-bottom: 0px solid black; }
<% } %>





/* ACCESSIBILITY */

<% if (browserJava.equals("NETSCAPE")) { %>
.accessibility-jumps-top { font-size:25%; color: #FFFFFF; margin-top: -10px; padding-top: -10px; }
.accessibility-jumps-bottom { font-size:25%; color: #FFFFFF; margin-top: 30px; padding-top: 30px; }
<% } else { %>
.accessibility-jumps-bottom { font-size:50%; color: #FFFFFF; margin-top: 20px; margin-bottom: 0px; }
.accessibility-jumps-top { font-size:50%; color: #FFFFFF; margin-top: -20px; margin-bottom: 0px; }
<% } %>
.accessibility-jumps-bottom a  { color: #FFFFFF;}
.accessibility-jumps-top a  { color: #FFFFFF;}



/* TABS */

.tabs-on { color: #000000; font-size:<%=(72*multiplier)%>%; background-color:#E2E2E2; font-family: Arial,Helvetica, sans-serif; font-weight:bold;  border-left: 1px solid #000000; border-top: 1px solid #000000; border-right: 1px solid #000000;  padding-left: 8px; padding-right: 8px; padding-top: 5px; padding-bottom: 5px; text-align: center }
.tabs-off { color: #000000; font-size:<%=(70*multiplier)%>%; background-color:#ADACAF; font-family: Arial,Helvetica, sans-serif; font-weight:bold; border-left: 1px solid #000000; border-top: 1px solid #000000; border-right: 1px solid #000000; border-bottom: 1px solid #000000; padding-left: 8px; padding-right: 8px; padding-top: 5px; padding-bottom: 5px; text-align: center    } 
.tabs-item { color: #000000;  background-color:#ADACAF; font-family: Arial,Helvetica, sans-serif; font-weight:bold  }
.blank-tab { background-color:#FFFFFF;  border-bottom: 1px solid #000000;  }
a:active.tabs-item  { color: #000000 }



/* FORMS */

FIELDSET { border: 0px }
INPUT { font-family: sans-serif; font-size:<%=(95*multiplier)%>% }
SELECT {  font-family: sans-serif; font-size:<%=(95*multiplier)%>% }
TEXTAREA { font-size:<%=(95*multiplier)%>%; font-family: sans-serif; width: 95%; }
UL { margin-bottom: 0px; margin-top: 0px; margin-left: 2em; list-style-type: square} 
FORM { margin-bottom:0px; margin-top: 0px; padding-top: 0px; padding-bottom: 0px; }
<% if (browserJava.equals("NETSCAPE")) { %>
.nopad { margin-bottom: 0px; margin-top: -.5em; padding-bottom: 0px; padding-top: -.5em }
<% } %>


/* BUTTONS */

<% if (browserJava.equals("NETSCAPE")) { %>
.buttons {  font-family: Arial,Helvetica, sans-serif; font-size:<%=(70*multiplier)%>%; font-weight:normal; }
.buttons#functions  {  font-family: Arial,Helvetica, sans-serif; font-size:  70%;  font-weight:normal; }
.buttons#navigation   {  font-family: Arial,Helvetica, sans-serif; font-size:  70%;  font-weight:normal; }                                                                           
.buttons#other {  font-family: Arial,Helvetica, sans-serif; font-size:<%=(70*multiplier)%>%; font-weight:normal;   }
.buttons#steps {  font-family: Arial,Helvetica, sans-serif; font-size:<%=(95*multiplier)%>%; padding-left: -1em; margin: 2px 5px 2px 0px; border: 0px solid black; background-color:#FFFFFF;}
.buttons#paging {  font-family: Arial,Helvetica, sans-serif; font-size:<%=(95*multiplier)%>%;  font-weight:normal; }
.function-button-section {  font-family: Arial,Helvetica, sans-serif;  text-align: left;  color: #000000; background-color: #E2E2E2; font-weight: normal; } 
.button-section {  font-family: Arial,Helvetica, sans-serif;  text-align: left; font-weight:normal; color: #000000; background-color:#CCCCCC;} 
<% } else { %>
.buttons  {  font-family: Arial,Helvetica, sans-serif; font-size:<%=(70*multiplier)%>%; margin: 1px 2px 1px 2px; border-top: 1px outset #B1B1B1; border-right: 1px outset #000000; border-bottom: 1px outset #000000; border-left: 1px outset #B1B1B1; background-color:#E2E2E2; }
.buttons#functions  {  font-weight:normal; font-family: Verdana, sans-serif; font-size:  70%; margin: 1px 1px 1px 1px; border-top: 1px outset #B1B1B1; border-right: 1px outset #000000; border-bottom: 1px outset #000000; border-left: 1px outset #B1B1B1; background-color:#CCCCCC;   }
.function-button-section { padding-lef: 8px; font-family: Arial,Helvetica, sans-serif;  text-align: left;  color: #000000; background-color: #E2E2E2; font-weight: normal; } 
.wizard-button-section .buttons#navigation   {  font-family: Verdana, sans-serif; font-size:  70%;  border-top: 1px outset #B1B1B1; border-right: 1px outset #000000; border-bottom: 1px outset #000000; border-left: 1px outset #B1B1B1; background-color:#CCCCCC; margin-top:2px;}
.buttons#navigation   {  font-family: Verdana, sans-serif; font-size:<%=(70*multiplier)%>%;  border-top: 1px outset #B1B1B1; border-right: 1px outset #000000; border-bottom: 1px outset #000000; border-left: 1px outset #B1B1B1; background-color:#E2E2E2; margin-top:2px;}
.buttons#other {  font-family: Arial,Helvetica, sans-serif; font-size:<%=(95*multiplier)%>%; margin: 2px 2px 0px 2px; border-top: 1px outset #B1B1B1; border-right: 1px outset #000000; border-bottom: 1px outset #000000; border-left: 1px outset #B1B1B1; background-color:#E2E2E2; }
.buttons#steps {  font-family: Arial,Helvetica, sans-serif; font-size:<%=(95*multiplier)%>%;  margin: 2px 2px -2px 0px; border: 0px solid black; text-decoration:underline; color:#0000FF; background-color:#FFFFFF;}
.buttons#paging {  font-family: Arial,Helvetica, sans-serif; font-size:<%=(95*multiplier)%>%; margin: 2px 2px -2px 2px; border: 0px outset black; text-decoration:underline; color:#0000FF; background-color: #E2E2E2;}
.button-section {  padding-left: 0px; font-family: Arial,Helvetica, sans-serif;  text-align: left; font-weight:normal; color: #000000; background-color:#CCCCCC; background-image: none; } 
<% } %>



/*  SYSTEM STATUS AREA  */

<% if (browserJava.equals("NETSCAPE")) { %>
.system-tray { background-color: #FFFFFF; font-family: Arial,Helvetica, sans-serif; margin-top: 5px; margin-left: 5px; margin-right: 5px; }
<% } else if (browserJava.equals("GECKO")) { %>
.system-tray { background-color: #FFFFFF; font-family: Arial,Helvetica, sans-serif;  margin-top: 8px; margin-left: 5px; margin-right: 8px; scrollbar-face-color:#CCCCCC; scrollbar-shadow-color:#FFFFFF; scrollbar-highlight-color:#FFFFFF; scrollbar-3dlight-color:#6B7A92; scrollbar-darkshadow-color:#6B7A92; scrollbar-track-color:#E2E2E2; scrollbar-arrow-color:#6B7A92 }
<% } else { %>
.system-tray { background-color: #FFFFFF; font-family: Arial,Helvetica, sans-serif; border-top: 1px solid #000000; margin-top: 8px; margin-left: 5px; margin-right: 8px; scrollbar-face-color:#CCCCCC; scrollbar-shadow-color:#FFFFFF; scrollbar-highlight-color:#FFFFFF; scrollbar-3dlight-color:#6B7A92; scrollbar-darkshadow-color:#6B7A92; scrollbar-track-color:#E2E2E2; scrollbar-arrow-color:#6B7A92 }
<% } %>
.tray-text {   font-family: Arial,Helvetica, sans-serif; font-size:<%=(70*multiplier)%>%; background-color:#E2E2E2; color: #000000;  }
.was-message-item  { color: #000000; font-family: Arial,Helvetica,sans-serif; font-weight:bold; font-size:<%=(70*multiplier)%>%; background-color:#E2E2E2; } 
.was-message-item a { font-weight: normal }





/* TABLES */

.table-text {   font-family: Arial,Helvetica, sans-serif; font-size:<%=(70*multiplier)%>%; background-color: #F7F7F7; background-image: none; }
.table-text#running { color: green; font-family: Arial,Helvetica, sans-serif; font-size:<%=(70*multiplier)%>%; background-color: #F7F7F7; background-image: none; }
.table-text#stopped { color: black; font-family: Arial,Helvetica, sans-serif; font-size:<%=(70*multiplier)%>%; background-color: #F7F7F7; background-image: none; }
.table-text#problems { color: red; font-family: Arial,Helvetica, sans-serif; font-size:<%=(70*multiplier)%>%; background-color: #F7F7F7; background-image: none; }
.table-text#unknown { color: orange; font-family: Arial,Helvetica, sans-serif; font-size:<%=(70*multiplier)%>%; background-color: #F7F7F7; background-image: none; }
.column-head {  padding-left: .35em; font-family: Arial,Helvetica, sans-serif; font-size: <%=(77*multiplier)%>%; font-weight:bold; text-align: left; color: #FFFFFF; background-color: #8691C7; background-image: none; }  
.column-head-name {  padding-left: .35em; font-family: Arial,Helvetica, sans-serif; font-size:<%=(70*multiplier)%>%;font-weight:bold; text-align: left; color: #000000; background-color: #BBCEDB; background-image: none; }   
.column-head-prefs {  padding-left: .35em; font-family: Arial,Helvetica, sans-serif; font-size:<%=(70*multiplier)%>%; font-weight:bold; text-align: left; color: #FFFFFF; background-color:#6B7A92; background-image: none; }  

.framing-table { background-color: #767776; background-image: none; }
.noframe-framing-table  { background-color: #767776; background-image: none; border-right: 1px solid #000000; border-bottom: 1px solid #000000; border-top: 1px solid #000000; border-left: 1px solid #000000; }

.layout-manager {  background-color:#E2E2E2; border-right: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000;}
.layout-manager#notabs {  background-color:#E2E2E2; border-right: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000; border-top: 1px solid #000000;}
.highlighted { background-color: #FFFFCC  }
.not-highlighted { background-color: #FFFFFF  }
.description-text { padding-left: 5px; font-family: Arial,Helvetica, sans-serif; font-size:<%=(70*multiplier)%>%; background-color: #FFFFFF}
.instruction-text { padding-left: 5px; font-family: Arial, Helvetica, sans-serif; font-size:<%=(70*multiplier)%>%; background-color: #FFFFFF}
.information {  font-family: Arial, Helvetica, sans-serif; font-size:<%=(70*multiplier)%>%; color:#993300;}
.paging-text { font-size: <%=(71*multiplier)%>%}
.runtime-checkbox { font-size:<%=(70*multiplier)%>%; font-family: Arial,Helvetica, sans-serif;  text-align: left; font-weight:normal; color: #000000; background-color:#CCCCCC; }

<% if (browserJava.equals("NETSCAPE")) { %>
.find-filter {  font-family: Arial,Helvetica, sans-serif; font-size:<%=(70*multiplier)%>%; color: #000000; background-color: #E2E2E2;  }
.find-filter-expanded {   font-family: Arial,Helvetica, sans-serif; font-size:<%=(70*multiplier)%>%; color: #000000; background-color: #E2E2E2; text-indent: 9px }
.find-filter-expanded INPUT { font-size: <%=(85*multiplier)%>%; }
.find-filter-expanded SELECT { font-size: <%=(70*multiplier)%>%; }
.find-filter-text {   font-family: Arial,Helvetica, sans-serif; font-size: <%=(65*multiplier)%>%; color: #000000; background-color: #E2E2E2; }
.collection-total {   font-family: Arial,Helvetica, sans-serif; font-size: <%=(65*multiplier)%>%; color: #000000; background-color: #E2E2E2;  }
.collection-total-expanded {   padding-left: 10px; font-family: Arial,Helvetica, sans-serif; font-size: <%=(65*multiplier)%>%; color: #000000; background-color: #E2E2E2;  }
.complex-property { font-family: Arial,Helvetica, sans-serif; font-size:<%=(70*multiplier)%>%; text-indent: 15px; background-color: #F7F7F7; }
.no-framing-table TD.table-text {   font-family: Arial,Helvetica, sans-serif; font-size:<%=(70*multiplier)%>%; background-color: #E2E2E2; background-image: none; }
<% } else { %>
.find-filter {  font-family: Arial,Helvetica, sans-serif; font-size: <%=(71*multiplier)%>%; color: #000000; background-color: #E2E2E2;  margin-left: 3px;  }
.find-filter-expanded {   font-family: Arial,Helvetica, sans-serif; font-size: <%=(71*multiplier)%>%; color: #000000; background-color: #E2E2E2;  padding-left: 20px; padding-bottom: 0px }
.find-filter-text {   font-family: Arial,Helvetica, sans-serif; font-size: <%=(71*multiplier)%>%; color: #000000; background-color: #E2E2E2; }
.collection-total {   font-family: Arial,Helvetica, sans-serif; font-size: <%=(71*multiplier)%>%; color: #000000; background-color: #E2E2E2;  }
.collection-total-expanded {   padding-left: 40px; font-family: Arial,Helvetica, sans-serif; font-size: <%=(71*multiplier)%>%; color: #000000; background-color: #E2E2E2;  }
.complex-property { font-family: Arial,Helvetica, sans-serif; font-size:<%=(70*multiplier)%>%; padding-left: 30px; background-color: #F7F7F7; }
<% } %>


/*  BODY STYLES */

a {color:#0000FF }
a:active {color:#666666 }
#plusminus {text-decoration: none; color: #000000; }
<% if (browserJava.equals("NETSCAPE")) { %>
.content { font-family: Arial, Helvetica, sans-serif; background-color: #FFFFFF  }
.topology-view {   font-family: Arial, Helvetica, sans-serif; font-size:<%=(70*multiplier)%>%; background-color: #FFFFFF  }
.topology-view H1#bread-crumb{  font-family: Arial, Helvetica, sans-serif; font-size: 150%; background-color: #FFFFFF ; }
.topology-view p.instruction-text{  padding-left:.5em; font-family: Arial, Helvetica, sans-serif; font-size: <%=(85*multiplier)%>%; background-color: #FFFFFF}
.navtree-items {  font-family:  Verdana, Arial,Helvetica, sans-serif; font-size: <%=(60*multiplier)%>%}
.navtree {  margin-right: 10px; margin-top: 5px; background-color: #FFFFFF ; font-family: Arial, Helvetica, sans-serif;  font-size:<%=(70*multiplier)%>%; }
.task-item {font-size: 100%; color: #000000;}
.Item0 { font-weight: bold }
.Item0 a { font-weight: normal }
layer#Item0 a { font-weight: bold }
layer a { text-decoration: none }
<% } else { %>
.content {   background-color: #FFFFFF ; font-family: Arial, Helvetica, sans-serif; scrollbar-face-color:#CCCCCC; scrollbar-shadow-color:#FFFFFF; scrollbar-highlight-color:#FFFFFF; scrollbar-3dlight-color:#6B7A92; scrollbar-darkshadow-color:#6B7A92; scrollbar-track-color:#E2E2E2; scrollbar-arrow-color:#6B7A92 }
.topology-view {  padding-top: 5px;  font-family: Arial, Helvetica, sans-serif; font-size:<%=(70*multiplier)%>%; background-color: #FFFFFF}
.topology-view H1#bread-crumb{  font-family: Arial, Helvetica, sans-serif; font-size: 130%; background-color: #FFFFFF ; letter-spacing: -.03em;  margin-top: .75em; margin-bottom: -.25em;}
.topology-view p.instruction-text{  padding-left:.5em; font-family: Arial, Helvetica, sans-serif; font-size: 100%; background-color: #FFFFFF}
.navtree {  background-color: #FFFFFF ; font-family: Arial, Helvetica, sans-serif; border-right: 1px solid black; margin-top: 5px; scrollbar-face-color:#CCCCCC; scrollbar-shadow-color:#FFFFFF; scrollbar-highlight-color:#FFFFFF; scrollbar-3dlight-color:#6B7A92; scrollbar-darkshadow-color:#6B7A92; scrollbar-track-color:#E2E2E2; scrollbar-arrow-color:#6B7A92 }
.navtree div {  font-family: Arial, Helvetica, sans-serif; font-size:<%=(70*multiplier)%>%; }
.Item0  { margin-top: 7px; margin-bottom: 2px; font-weight: bold; }
.Item0 a { margin-top: 7px; margin-bottom: 2px; font-weight: normal; }
.navtree-items {  font-family:  Arial,Helvetica, sans-serif; font-size:<%=(70*multiplier)%>% }
.task-item {font-size: 110%; color: #000000;}
<% } %>
<% if (browserJava.equals("GECKO")) {  %>
body.navtree  {  background-color: #FFFFFF; font-family: Arial,Helvetica,sans-serif; border-right: 0px solid black;  margin-left: 5px; margin-right: 0px; margin-top: 5px }
<% } %>




/* WIZARDS */

<% if (browserJava.equals("NETSCAPE")) { %>
.wizard-table {  background-color:#E2E2E2; }
.wizard-button-section {  font-family: Arial,Helvetica, sans-serif;  text-align: left; font-weight:normal; color: #000000; background-color:#E2E2E2;  } 
.wizard-step-expanded {  background-color:#E2E2E2; text-indent: 15px; font-family: Arial,Helvetica, sans-serif;  font-size: <%=(71*multiplier)%>%; text-align: left;  color: #000000; }
th.wizard-step  { font-weight:bold; background-color:#E2E2E2; font-family: Arial,Helvetica, sans-serif;  font-size: <%=(71*multiplier)%>%; text-align: left;  color: #000000; }
.wizard-step  {  background-color:#FFFFFF; font-family: Arial,Helvetica, sans-serif;  font-size: <%=(71*multiplier)%>%; text-align: left;  color: #000000;}
.wizard-step-text { background-color:#E2E2E2; font-family: Arial,Helvetica, sans-serif;  font-size: <%=(71*multiplier)%>%; text-align: left;  color: #000000;  }
<% } else { %>
th.wizard-step  { font-weight:bold; background-color:#E2E2E2; font-family: Arial,Helvetica, sans-serif;  font-size: <%=(71*multiplier)%>%; text-align: left;  color: #000000; }
.wizard-step  {  background-color:#FFFFFF; font-family: Arial,Helvetica, sans-serif;  font-size: <%=(71*multiplier)%>%; text-align: left;  color: #000000;}
.wizard-step-text { background-color:#E2E2E2; font-family: Arial,Helvetica, sans-serif;  font-size: <%=(71*multiplier)%>%; text-align: left;  color: #000000;  }
.wizard-button-section {  font-family: Arial,Helvetica, sans-serif;  text-align: left; font-weight:normal; color: #000000; background-color:#E2E2E2;  } 
.wizard-step-expanded {    background-color:#E2E2E2; padding-left: 20px; font-family: Arial,Helvetica, sans-serif;  font-size: <%=(71*multiplier)%>%; text-align: left;  color: #000000;  }
.wizard-table {  background-color:#E2E2E2; border-top: 1px solid #000000; border-right: 1px solid #000000; border-bottom: 1px solid #000000; border-left: 1px solid #000000;}
<% } %>





/*  TITLES  */

<% if (browserJava.equals("NETSCAPE")) { %>
H1 {  font-family: Arial,Helvetica, sans-serif; font-size: <%=(100*multiplier)%>%; padding-top: -5px; margin-top: -5px  }
H3 {  font-family: Arial,Helvetica, sans-serif; font-size: <%=(70*multiplier)%>%; margin-left: -3px; margin-top: -4px; padding-top: -4px }
H1#bread-crumb {  font-family: Arial,Helvetica, sans-serif; font-size: <%=(100*multiplier)%>%; padding-top: -4px; margin-top: -4px  }
H3#bread-crumb {  font-family: Arial,Helvetica, sans-serif; font-size: <%=(70*multiplier)%>%; margin-left: -3px; margin-top: -4px; padding-top: -4px }
<% } else { %>
H1 { font-family: Arial,Helvetica, sans-serif; font-size:<%=(95*multiplier)%>%; ; margin-left: 5px }
H1#bread-crumb { font-family: Arial,Helvetica, sans-serif; font-size:<%=(95*multiplier)%>%;   margin-top: .75em; margin-bottom: -.25em; }
H3#bread-crumb { font-family: Arial,Helvetica, sans-serif; font-size:<%=(70*multiplier)%>%; margin-top: .5em;  margin-bottom: -.5em }
<% } %>






/* HELP PAGES */

.help {   margin: 0px; background-color: #FFFFFF ; font-family: Arial, Helvetica, sans-serif }
.index-link { font-family: Arial, Helvetica, sans-serif; font-size: <%=(75*multiplier)%>%; text-align: right; padding-top: 10px; padding-right: 10px; }


/* TREES */

#Item0 { font-weight: bold; padding-top: 5px}

.indent1 {   padding-left:0px; font-family: Arial,Helvetica, sans-serif; margin-top: 5px; margin-bottom: 5px  } 
.indent2 {   padding-left:19px; font-family: Arial,Helvetica, sans-serif; margin-top: 5px; margin-bottom: 5px  }
.indent3 {   padding-left:38px; font-family: Arial,Helvetica, sans-serif; margin-top: 5px; margin-bottom: 5px   }
.indent4 {   padding-left:57px; font-family: Arial,Helvetica, sans-serif; margin-top: 5px; margin-bottom: 5px  }
.indent5 {   padding-left:76px; font-family: Arial,Helvetica, sans-serif; margin-top: 5px; margin-bottom: 5px   }
.indent6 {   padding-left:95px; font-family: Arial,Helvetica, sans-serif; margin-top: 5px; margin-bottom: 5px   }
.indent7 {   padding-left:114px; font-family: Arial,Helvetica, sans-serif; margin-top: 5px; margin-bottom: 5px   }
.indent8 {   padding-left:133px; font-family: Arial,Helvetica, sans-serif; margin-top: 5px; margin-bottom: 5px   }
.indent9 {   padding-left:152px; font-family: Arial,Helvetica, sans-serif; margin-top: 5px; margin-bottom: 5px   }
.indent10 {   padding-left:171px; font-family: Arial,Helvetica, sans-serif; margin-top: 5px; margin-bottom: 5px   }
.indent1kids {   padding-left:-19px; font-family: Arial,Helvetica, sans-serif; margin-top: 10px; margin-bottom: 4px; font-weight: bold  } 
.indent2kids {   padding-left:0px; font-family: Arial,Helvetica, sans-serif; margin-top: 5px; margin-bottom: 5px  }
.indent3kids {   padding-left:19px; font-family: Arial,Helvetica, sans-serif; margin-top: 5px; margin-bottom: 5px   }
.indent4kids {   padding-left:38px; font-family: Arial,Helvetica, sans-serif; margin-top: 5px; margin-bottom: 5px  }
.indent5kids {   padding-left:57px; font-family: Arial,Helvetica, sans-serif; margin-top: 5px; margin-bottom: 5px   }
.indent6kids {   padding-left:76px; font-family: Arial,Helvetica, sans-serif; margin-top: 5px; margin-bottom: 5px   }
.indent7kids {   padding-left:95px; font-family: Arial,Helvetica, sans-serif; margin-top: 5px; margin-bottom: 5px   }
.indent8kids {   padding-left:114px; font-family: Arial,Helvetica, sans-serif; margin-top: 5px; margin-bottom: 5px   }
.indent9kids {   padding-left:133px; font-family: Arial,Helvetica, sans-serif; margin-top: 5px; margin-bottom: 5px   }
.indent10kids {   padding-left:152px; font-family: Arial,Helvetica, sans-serif; margin-top: 5px; margin-bottom: 5px   }







/* VALIDATION ERRORS */

.validation-error {color: #CC0000; font-family: Arial,Helvetica, sans-serif;  }
.validation-warn-info {color: #000000; font-family: Arial,Helvetica, sans-serif;  }
.validation-header { color: #FFFFFF; background-color:#6B7A92; font-family: Arial,Helvetica, sans-serif;font-weight:bold; font-size: <%=(75*multiplier)%>% }
<% if (browserJava.equals("NETSCAPE")) { %>
.red-text {color: #CC0000; font-weight: bold; }
.required {color: #CC0000; font-weight: bold;  font-size: 100%; }
.error {color: #CC0000; font-weight: bold;  font-size: 100%;}
<% } %>


/* HOMEPAGE STYLES */


<% if (browserJava.equals("NETSCAPE")) { %>
.nolines {  font-size: <%=(75*multiplier)%>%; borders: 0px solid #CCCCFF;  }
.linesmost { font-size: <%=(75*multiplier)%>%;   border-left: 0px; border-bottom: 0px; border-top: 1px solid #CCCCFF; border-right: 1px solid #CCCCFF;  background-color: #FFFFFF; padding-bottom: 12px}
.purpletext { font-family: sans-serif; color: #330066; font-size: 90%;}
.purplebold { font-weight:bold; color: #330066; font-size: 90%; font-family: Helvetica,sans-serif;}
.graytext { font-family: sans-serif; color: #666666; font-size: 90%;}
.graybold { font-weight:bold; color: #330066; font-size: 90%; font-family: Helvetica,sans-serif;}
.desctextabout { font-family: sans-serif; color: #330066; font-size: <%=(75*multiplier)%>%; padding-bottom: 5px; }
.desctexthead { font-weight: 600; font-family: sans-serif; color: #330066; font-size: <%=(75*multiplier)%>%; padding-bottom: 5px; }
.desctext { font-family: sans-serif; color: #666666; font-size: <%=(75*multiplier)%>%; }
.bluebold { font-weight:bold; color: #330066; font-size: 90%; font-family: Helvetica,sans-serif;}
a .purpletext { text-decoration: underline #000000}
a .purplebold { text-decoration: underline  #000000}
a .bluebold  {  text-decoration: underline  #000000}
a .graybold  {  text-decoration: underline  #000000}
<% } else { %>
.nolines {  font-size: <%=(75*multiplier)%>%; borders: 0px solid #CCCCFF;  }
.linesmost { font-size: <%=(75*multiplier)%>%;   border-left: 0px; border-bottom: 0px; border-top: 1px solid #CCCCFF; border-right: 1px solid #CCCCFF;  background-color: #FFFFFF; padding-bottom: 12px}
.purpletext { font-family: sans-serif; color: #9933CC; font-size: <%=(104*multiplier)%>%;}
.purplebold { font-weight: bold; color: #9933CC; font-size: 115%; font-family: Helvetica,sans-serif;}
.graytext { font-family: sans-serif; color: #666666; font-size: <%=(104*multiplier)%>%;}
.graybold { font-weight: bold; color: #363636; font-size: <%=(107*multiplier)%>%; font-family: Helvetica,sans-serif;}
.desctextabout { font-family: sans-serif; color: #363636; font-size: 101%; padding-bottom: 5px; line-height: 160%}
.desctexthead { font-weight: 600; font-family: sans-serif; color: #333333; font-size: <%=(104*multiplier)%>%; padding-bottom: 5px; line-height: 155%}
.desctext { font-family: sans-serif; color: #666666; font-size: <%=(100*multiplier)%>%; line-height: 130%}
.bluebold { font-weight: bold; color: #330066; font-size: <%=(107*multiplier)%>%; font-family: Helvetica,sans-serif;}
a .purpletext { text-decoration: underline #000000}
a .purplebold { text-decoration: underline  #000000}
a .bluebold  {  text-decoration: underline  #000000}
a .graybold  {  text-decoration: underline  #000000}
<% } %>
</style>

