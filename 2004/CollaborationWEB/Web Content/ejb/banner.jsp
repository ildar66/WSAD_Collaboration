<%@ page language="java" %>
<%@ page contentType = "text/html; charset=windows-1251" %>
<%--@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" --%>
<%--@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%--@ page import="com.ibm.ws.console.core.bean.*" --%>
<%--@ page import="com.ibm.ws.console.core.*" --%>

<%--<ibmcommon:detectLocale/>--%>

<%
   //obtain show/hide banner preference from preference.xml for this user
   //the banner property is used to show whether secure/tiles/banner.jsp will
   //be visible (see reference to this file below)
   
   //UserPreferenceBean uBean = (UserPreferenceBean) session.getAttribute(Constants.USER_PREFS);
   //String banner = uBean.getProperty("System/workspace#bannerShow", "true");
   String banner = "true";
%>

<html:html locale="true">
<HEAD>
<script language="JavaScript"
	src="<%=request.getContextPath()%>/scripts/menu_functions.js"></script>

<STYLE TYPE="text/css">
a {
	text-decoration: none
}

a:hover {
	text-decoration: underline
}
</STYLE>
<style type="text/css">
/* The browser agent is IE */
/* The agent locale is en */
/* The agent OS is NT */
/* The font size multiplier is 1.0 */
/*  LOGIN PAGE */
.login {
	text-align: center;
}

.login-button-section {
	padding-left: 0px;
	font-family: Arial, Helvetica, sans-serif;
	font-weight: normal;
	color: #000000;
	background-color: #CCCCCC;
	background-image: none;
}

/* BANNER PAGE */
.header {
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	margin-left: 0px;
	font-family: Arial, Helvetica, sans-serif;
	border-bottom: 1px solid black;
}

.top-navigation {
	color: #000000;
	font-size: 70.0%;
	background-color: #ADB0EC;
	font-family: Arial, Helvetica, sans-serif;
	padding-left: 10px;
	padding-right: 5px;
}

.top-nav-item {
	color: #000000;
	font-family: Arial, Helvetica, sans-serif;
	font-weight: bold;
	text-decoration: none
}

a.top-nav-item {
	color: #000000;
	font-family: Arial, Helvetica, sans-serif;
	font-weight: bold;
}

a:active.top-nav-item {
	color: #000000;
	font-family: sans-serif;
}

a:hover.top-nav-item {
	text-decoration: underline
}

/* ACCESSIBILITY */
.accessibility-jumps-bottom {
	font-size: 50%;
	color: #FFFFFF;
	margin-top: 20px;
	margin-bottom: 0px;
}

.accessibility-jumps-top {
	font-size: 50%;
	color: #FFFFFF;
	margin-top: -20px;
	margin-bottom: 0px;
}

.accessibility-jumps-bottom a {
	color: #FFFFFF;
}

.accessibility-jumps-top a {
	color: #FFFFFF;
}

/* TABS */
.tabs-on {
	color: #000000;
	font-size: 72.0%;
	background-color: #E2E2E2;
	font-family: Arial, Helvetica, sans-serif;
	font-weight: bold;
	border-left: 1px solid #000000;
	border-top: 1px solid #000000;
	border-right: 1px solid #000000;
	padding-left: 8px;
	padding-right: 8px;
	padding-top: 5px;
	padding-bottom: 5px;
	text-align: center
}

.tabs-off {
	color: #000000;
	font-size: 70.0%;
	background-color: #ADACAF;
	font-family: Arial, Helvetica, sans-serif;
	font-weight: bold;
	border-left: 1px solid #000000;
	border-top: 1px solid #000000;
	border-right: 1px solid #000000;
	border-bottom: 1px solid #000000;
	padding-left: 8px;
	padding-right: 8px;
	padding-top: 5px;
	padding-bottom: 5px;
	text-align: center
}

.tabs-item {
	color: #000000;
	background-color: #ADACAF;
	font-family: Arial, Helvetica, sans-serif;
	font-weight: bold
}

.blank-tab {
	background-color: #FFFFFF;
	border-bottom: 1px solid #000000;
}

a:active.tabs-item {
	color: #000000
}

/* FORMS */
FIELDSET {
	border: 0px
}

INPUT {
	font-family: sans-serif;
	font-size: 95.0%
}

SELECT {
	font-family: sans-serif;
	font-size: 95.0%
}

TEXTAREA {
	font-size: 95.0%;
	font-family: sans-serif;
	width: 95%;
}

UL {
	margin-bottom: 0px;
	margin-top: 0px;
	margin-left: 2em;
	list-style-type: square
}

FORM {
	margin-bottom: 0px;
	margin-top: 0px;
	padding-top: 0px;
	padding-bottom: 0px;
}

/* BUTTONS */
.buttons {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 70.0%;
	margin: 1px 2px 1px 2px;
	border-top: 1px outset #B1B1B1;
	border-right: 1px outset #000000;
	border-bottom: 1px outset #000000;
	border-left: 1px outset #B1B1B1;
	background-color: #E2E2E2;
}

.buttons#functions {
	font-weight: normal;
	font-family: Verdana, sans-serif;
	font-size: 70%;
	margin: 1px 1px 1px 1px;
	border-top: 1px outset #B1B1B1;
	border-right: 1px outset #000000;
	border-bottom: 1px outset #000000;
	border-left: 1px outset #B1B1B1;
	background-color: #CCCCCC;
}

.function-button-section {
	padding-lef: 8px;
	font-family: Arial, Helvetica, sans-serif;
	text-align: left;
	color: #000000;
	background-color: #E2E2E2;
	font-weight: normal;
}

.wizard-button-section.buttons#navigation {
	font-family: Verdana, sans-serif;
	font-size: 70%;
	border-top: 1px outset #B1B1B1;
	border-right: 1px outset #000000;
	border-bottom: 1px outset #000000;
	border-left: 1px outset #B1B1B1;
	background-color: #CCCCCC;
	margin-top: 2px;
}

.buttons#navigation {
	font-family: Verdana, sans-serif;
	font-size: 70.0%;
	border-top: 1px outset #B1B1B1;
	border-right: 1px outset #000000;
	border-bottom: 1px outset #000000;
	border-left: 1px outset #B1B1B1;
	background-color: #E2E2E2;
	margin-top: 2px;
}

.buttons#other {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 95.0%;
	margin: 2px 2px 0px 2px;
	border-top: 1px outset #B1B1B1;
	border-right: 1px outset #000000;
	border-bottom: 1px outset #000000;
	border-left: 1px outset #B1B1B1;
	background-color: #E2E2E2;
}

.buttons#steps {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 95.0%;
	margin: 2px 2px -2px 0px;
	border: 0px solid black;
	text-decoration: underline;
	color: #0000FF;
	background-color: #FFFFFF;
}

.buttons#paging {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 95.0%;
	margin: 2px 2px -2px 2px;
	border: 0px outset black;
	text-decoration: underline;
	color: #0000FF;
	background-color: #E2E2E2;
}

.button-section {
	padding-left: 0px;
	font-family: Arial, Helvetica, sans-serif;
	text-align: left;
	font-weight: normal;
	color: #000000;
	background-color: #CCCCCC;
	background-image: none;
}

/*  SYSTEM STATUS AREA  */
.system-tray {
	background-color: #FFFFFF;
	font-family: Arial, Helvetica, sans-serif;
	border-top: 1px solid #000000;
	margin-top: 8px;
	margin-left: 5px;
	margin-right: 8px;
	scrollbar-face-color: #CCCCCC;
	scrollbar-shadow-color: #FFFFFF;
	scrollbar-highlight-color: #FFFFFF;
	scrollbar-3dlight-color: #6B7A92;
	scrollbar-darkshadow-color: #6B7A92;
	scrollbar-track-color: #E2E2E2;
	scrollbar-arrow-color: #6B7A92
}

.tray-text {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 70.0%;
	background-color: #E2E2E2;
	color: #000000;
}

.was-message-item {
	color: #000000;
	font-family: Arial, Helvetica, sans-serif;
	font-weight: bold;
	font-size: 70.0%;
	background-color: #E2E2E2;
}

.was-message-item a {
	font-weight: normal
}

/* TABLES */
.table-text {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 70.0%;
	background-color: #F7F7F7;
	background-image: none;
}

.table-text#running {
	color: green;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 70.0%;
	background-color: #F7F7F7;
	background-image: none;
}

.table-text#stopped {
	color: black;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 70.0%;
	background-color: #F7F7F7;
	background-image: none;
}

.table-text#problems {
	color: red;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 70.0%;
	background-color: #F7F7F7;
	background-image: none;
}

.table-text#unknown {
	color: orange;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 70.0%;
	background-color: #F7F7F7;
	background-image: none;
}

.column-head {
	padding-left: .35em;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 77.0%;
	font-weight: bold;
	text-align: left;
	color: #FFFFFF;
	background-color: #8691C7;
	background-image: none;
}

.column-head-name {
	padding-left: .35em;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 70.0%;
	font-weight: bold;
	text-align: left;
	color: #000000;
	background-color: #BBCEDB;
	background-image: none;
}

.column-head-prefs {
	padding-left: .35em;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 70.0%;
	font-weight: bold;
	text-align: left;
	color: #FFFFFF;
	background-color: #6B7A92;
	background-image: none;
}

.framing-table {
	background-color: #767776;
	background-image: none;
}

.noframe-framing-table {
	background-color: #767776;
	background-image: none;
	border-right: 1px solid #000000;
	border-bottom: 1px solid #000000;
	border-top: 1px solid #000000;
	border-left: 1px solid #000000;
}

.layout-manager {
	background-color: #E2E2E2;
	border-right: 1px solid #000000;
	border-bottom: 1px solid #000000;
	border-left: 1px solid #000000;
}

.layout-manager#notabs {
	background-color: #E2E2E2;
	border-right: 1px solid #000000;
	border-bottom: 1px solid #000000;
	border-left: 1px solid #000000;
	border-top: 1px solid #000000;
}

.highlighted {
	background-color: #FFFFCC
}

.not-highlighted {
	background-color: #FFFFFF
}

.description-text {
	padding-left: 5px;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 70.0%;
	background-color: #FFFFFF
}

.instruction-text {
	padding-left: 5px;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 70.0%;
	background-color: #FFFFFF
}

.information {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 70.0%;
	color: #993300;
}

.paging-text {
	font-size: 71.0%
}

.runtime-checkbox {
	font-size: 70.0%;
	font-family: Arial, Helvetica, sans-serif;
	text-align: left;
	font-weight: normal;
	color: #000000;
	background-color: #CCCCCC;
}

.find-filter {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 71.0%;
	color: #000000;
	background-color: #E2E2E2;
	margin-left: 3px;
}

.find-filter-expanded {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 71.0%;
	color: #000000;
	background-color: #E2E2E2;
	padding-left: 20px;
	padding-bottom: 0px
}

.find-filter-text {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 71.0%;
	color: #000000;
	background-color: #E2E2E2;
}

.collection-total {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 71.0%;
	color: #000000;
	background-color: #E2E2E2;
}

.collection-total-expanded {
	padding-left: 40px;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 71.0%;
	color: #000000;
	background-color: #E2E2E2;
}

.complex-property {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 70.0%;
	padding-left: 30px;
	background-color: #F7F7F7;
}

/*  BODY STYLES */
a {
	color: #0000FF
}

a:active {
	color: #666666
}

#plusminus {
	text-decoration: none;
	color: #000000;
}

.content {
	background-color: #FFFFFF;
	font-family: Arial, Helvetica, sans-serif;
	scrollbar-face-color: #CCCCCC;
	scrollbar-shadow-color: #FFFFFF;
	scrollbar-highlight-color: #FFFFFF;
	scrollbar-3dlight-color: #6B7A92;
	scrollbar-darkshadow-color: #6B7A92;
	scrollbar-track-color: #E2E2E2;
	scrollbar-arrow-color: #6B7A92
}

.topology-view {
	padding-top: 5px;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 70.0%;
	background-color: #FFFFFF
}

.topology-view H1#bread-crumb {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 130%;
	background-color: #FFFFFF;
	letter-spacing: -.03em;
	margin-top: .75em;
	margin-bottom: -.25em;
}

.topology-view p.instruction-text {
	padding-left: .5em;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 100%;
	background-color: #FFFFFF
}

.navtree {
	background-color: #FFFFFF;
	font-family: Arial, Helvetica, sans-serif;
	border-right: 1px solid black;
	margin-top: 5px;
	scrollbar-face-color: #CCCCCC;
	scrollbar-shadow-color: #FFFFFF;
	scrollbar-highlight-color: #FFFFFF;
	scrollbar-3dlight-color: #6B7A92;
	scrollbar-darkshadow-color: #6B7A92;
	scrollbar-track-color: #E2E2E2;
	scrollbar-arrow-color: #6B7A92
}

.navtree div {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 70.0%;
}

.Item0 {
	margin-top: 7px;
	margin-bottom: 2px;
	font-weight: bold;
}

.Item0 a {
	margin-top: 7px;
	margin-bottom: 2px;
	font-weight: normal;
}

.navtree-items {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 70.0%
}

.task-item {
	font-size: 110%;
	color: #000000;
}

/* WIZARDS */
th.wizard-step {
	font-weight: bold;
	background-color: #E2E2E2;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 71.0%;
	text-align: left;
	color: #000000;
}

.wizard-step {
	background-color: #FFFFFF;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 71.0%;
	text-align: left;
	color: #000000;
}

.wizard-step-text {
	background-color: #E2E2E2;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 71.0%;
	text-align: left;
	color: #000000;
}

.wizard-button-section {
	font-family: Arial, Helvetica, sans-serif;
	text-align: left;
	font-weight: normal;
	color: #000000;
	background-color: #E2E2E2;
}

.wizard-step-expanded {
	background-color: #E2E2E2;
	padding-left: 20px;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 71.0%;
	text-align: left;
	color: #000000;
}

.wizard-table {
	background-color: #E2E2E2;
	border-top: 1px solid #000000;
	border-right: 1px solid #000000;
	border-bottom: 1px solid #000000;
	border-left: 1px solid #000000;
}

/*  TITLES  */
H1 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 95.0%;;
	margin-left: 5px
}

H1#bread-crumb {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 95.0%;
	margin-top: .75em;
	margin-bottom: -.25em;
}

H3#bread-crumb {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 70.0%;
	margin-top: .5em;
	margin-bottom: -.5em
}

/* HELP PAGES */
.help {
	margin: 0px;
	background-color: #FFFFFF;
	font-family: Arial, Helvetica, sans-serif
}

.index-link {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 75.0%;
	text-align: right;
	padding-top: 10px;
	padding-right: 10px;
}

/* TREES */
#Item0 {
	font-weight: bold;
	padding-top: 5px
}

.indent1 {
	padding-left: 0px;
	font-family: Arial, Helvetica, sans-serif;
	margin-top: 5px;
	margin-bottom: 5px
}

.indent2 {
	padding-left: 19px;
	font-family: Arial, Helvetica, sans-serif;
	margin-top: 5px;
	margin-bottom: 5px
}

.indent3 {
	padding-left: 38px;
	font-family: Arial, Helvetica, sans-serif;
	margin-top: 5px;
	margin-bottom: 5px
}

.indent4 {
	padding-left: 57px;
	font-family: Arial, Helvetica, sans-serif;
	margin-top: 5px;
	margin-bottom: 5px
}

.indent5 {
	padding-left: 76px;
	font-family: Arial, Helvetica, sans-serif;
	margin-top: 5px;
	margin-bottom: 5px
}

.indent6 {
	padding-left: 95px;
	font-family: Arial, Helvetica, sans-serif;
	margin-top: 5px;
	margin-bottom: 5px
}

.indent7 {
	padding-left: 114px;
	font-family: Arial, Helvetica, sans-serif;
	margin-top: 5px;
	margin-bottom: 5px
}

.indent8 {
	padding-left: 133px;
	font-family: Arial, Helvetica, sans-serif;
	margin-top: 5px;
	margin-bottom: 5px
}

.indent9 {
	padding-left: 152px;
	font-family: Arial, Helvetica, sans-serif;
	margin-top: 5px;
	margin-bottom: 5px
}

.indent10 {
	padding-left: 171px;
	font-family: Arial, Helvetica, sans-serif;
	margin-top: 5px;
	margin-bottom: 5px
}

.indent1kids {
	padding-left: -19px;
	font-family: Arial, Helvetica, sans-serif;
	margin-top: 10px;
	margin-bottom: 4px;
	font-weight: bold
}

.indent2kids {
	padding-left: 0px;
	font-family: Arial, Helvetica, sans-serif;
	margin-top: 5px;
	margin-bottom: 5px
}

.indent3kids {
	padding-left: 19px;
	font-family: Arial, Helvetica, sans-serif;
	margin-top: 5px;
	margin-bottom: 5px
}

.indent4kids {
	padding-left: 38px;
	font-family: Arial, Helvetica, sans-serif;
	margin-top: 5px;
	margin-bottom: 5px
}

.indent5kids {
	padding-left: 57px;
	font-family: Arial, Helvetica, sans-serif;
	margin-top: 5px;
	margin-bottom: 5px
}

.indent6kids {
	padding-left: 76px;
	font-family: Arial, Helvetica, sans-serif;
	margin-top: 5px;
	margin-bottom: 5px
}

.indent7kids {
	padding-left: 95px;
	font-family: Arial, Helvetica, sans-serif;
	margin-top: 5px;
	margin-bottom: 5px
}

.indent8kids {
	padding-left: 114px;
	font-family: Arial, Helvetica, sans-serif;
	margin-top: 5px;
	margin-bottom: 5px
}

.indent9kids {
	padding-left: 133px;
	font-family: Arial, Helvetica, sans-serif;
	margin-top: 5px;
	margin-bottom: 5px
}

.indent10kids {
	padding-left: 152px;
	font-family: Arial, Helvetica, sans-serif;
	margin-top: 5px;
	margin-bottom: 5px
}

/* VALIDATION ERRORS */
.validation-error {
	color: #CC0000;
	font-family: Arial, Helvetica, sans-serif;
}

.validation-warn-info {
	color: #000000;
	font-family: Arial, Helvetica, sans-serif;
}

.validation-header {
	color: #FFFFFF;
	background-color: #6B7A92;
	font-family: Arial, Helvetica, sans-serif;
	font-weight: bold;
	font-size: 75.0%
}

/* HOMEPAGE STYLES */
.nolines {
	font-size: 75.0%;
	borders: 0px solid #CCCCFF;
}

.linesmost {
	font-size: 75.0%;
	border-left: 0px;
	border-bottom: 0px;
	border-top: 1px solid #CCCCFF;
	border-right: 1px solid #CCCCFF;
	background-color: #FFFFFF;
	padding-bottom: 12px
}

.purpletext {
	font-family: sans-serif;
	color: #9933CC;
	font-size: 104.0%;
}

.purplebold {
	font-weight: bold;
	color: #9933CC;
	font-size: 115%;
	font-family: Helvetica, sans-serif;
}

.graytext {
	font-family: sans-serif;
	color: #666666;
	font-size: 104.0%;
}

.graybold {
	font-weight: bold;
	color: #363636;
	font-size: 107.0%;
	font-family: Helvetica, sans-serif;
}

.desctextabout {
	font-family: sans-serif;
	color: #363636;
	font-size: 101%;
	padding-bottom: 5px;
	line-height: 160%
}

.desctexthead {
	font-weight: 600;
	font-family: sans-serif;
	color: #333333;
	font-size: 104.0%;
	padding-bottom: 5px;
	line-height: 155%
}

.desctext {
	font-family: sans-serif;
	color: #666666;
	font-size: 100.0%;
	line-height: 130%
}

.bluebold {
	font-weight: bold;
	color: #330066;
	font-size: 107.0%;
	font-family: Helvetica, sans-serif;
}

a.purpletext {
	text-decoration: underline #000000
}

a.purplebold {
	text-decoration: underline #000000
}

a.bluebold {
	text-decoration: underline #000000
}

a.graybold {
	text-decoration: underline #000000
}
</style>


<STYLE TYPE="text/css">
a {
	text-decoration: none
}

a:hover {
	text-decoration: underline
}
</STYLE>

</HEAD>

<body class="header" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0">
<%if (banner.equals("true")) { %>
<jsp:include page="tiles/banner.jsp" flush="true" /> 
<%}%>

<table class="noframe-framing-table" border="0" cellpadding="3"
	cellspacing="0" width="100%">
	<tr valign="top">
		<td class="top-navigation">
			<a class="top-nav-item" href="content.jsp" target="detail"> Home </a>&nbsp;&nbsp; | &nbsp;&nbsp; 
			<a class="top-nav-item"	href="/collaboration/menuBarCmd.do?forwardName=console.preferences.main" target="detail"> Preferences </a>&nbsp;&nbsp; | &nbsp;&nbsp;
			<a class="top-nav-item" href="/collaboration/logoff.do" target="_top"> Logout </a>&nbsp;&nbsp; | &nbsp;&nbsp; 
			<a class="top-nav-item"	href="/collaboration/help/help_console.jsp" target="collaboration_help"> Help </a>&nbsp;&nbsp; | &nbsp;&nbsp;
		</td>
<%--
		<td class="top-navigation" align=right>
		<form name="descriptionsForm" action="/collaboration/menuBarCmd.do"
			method="post"><input type="image" name="descriptionsON"
			src="/collaboration/images/descriptionsON.gif"
			alt="Hide Field and Page Descriptions" align="right" border="0"
			onclick="top.detail.location.reload(true)">&nbsp;&nbsp;</form>
		</td>
--%>
	</tr>
</table>

</body>
</html:html>
