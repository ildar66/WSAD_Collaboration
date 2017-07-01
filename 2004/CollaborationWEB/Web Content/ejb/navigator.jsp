<%@ page contentType = "text/html; charset=windows-1251" %>
<html lang="ru">
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<META HTTP-EQUIV="Expires" CONTENT="0">
<TITLE><B>Task Navigation</B></TITLE>

<style type="text/css">
/* The browser agent is IE */
/* The agent locale is en */
/* The agent OS is NT */
/* The font size multiplier is 1.0 */
/*  LOGIN PAGE */
.login {
	text-align: center;
}
<%=request.getParameter("")%>
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

<script language="JavaScript1.2" type="text/javascript">
/************************************************
*   Stuff from menu_functions.js *
************************************************/

// Global variables

var skin;
var isNav4, isIE, isDom

var foropera = window.navigator.userAgent.toLowerCase();
// alert(foropera);

var itsopera = foropera.indexOf("opera",0) + 1;
var itsgecko = foropera.indexOf("gecko",0) + 1;
var itsmozillacompat = foropera.indexOf("mozilla",0) + 1;
var itsmsie = foropera.indexOf("msie",0) + 1;
        if (itsopera > 0){
                //thebrowser = 3;
                //alert("its opera");
                isNav4 = true
        }
        if (itsmozillacompat > 0){ 
                //alert("its mozilla compatible");
                if (itsgecko > 0) {
                        //thebrowser = 4
                       // alert("its gecko");
                       isNav4 = true
                       isDom = true
                }
                else if (itsmsie > 0) {
                      //  alert("its msie");
                       // thebrowser = 2
                        isIE = true
                }
                else {
                        if (parseInt(navigator.appVersion) < 5) {
                                // alert("its ns4.x")
                                //thebrowser = 1
                                isNav4 = true
                        }
                        else {
                                thebrowser = 2
                                isIE = true
                        }
                }
        } 

function NSResize() {
   top.header.location.reload();
   top.detail.location.reload();
   if (top.HelpNavigation) {
   top.HelpNavigation.location.reload();
   }
   return false;
}

if (isNav4 && !isDom) {
   window.captureEvents(Event.RESIZE)
   window.onresize = NSResize
}

</script>

</head>


<body class="navtree">
<script type="text/javascript" language="JavaScript"
	src="/collaboration/scripts/aptree.js"></script>
<script type="text/javascript" language="JavaScript1.2">

setShowExpanders(true);
setExpandDepth(1);
setKeepState(true);
setShowHealth(false);
setInTable(false);
setTargetFrame("detail");
openFolder = "/collaboration/images/open_folder.gif";
closedFolder = "/collaboration/images/closed_folder.gif";
plusIcon = "/collaboration/images/lplus.gif";
minusIcon = "/collaboration/images/lminus.gif";
blankIcon = "/collaboration/images/blank20.gif";

</script>



<div class='indent1'><b>User ID:</b></div>

<script type="text/javascript" language="JavaScript1.2">
collaboration_domain = addRoot('/collaboration/images/Domain.gif', 'список задач:','');
magementNRI = addItem(collaboration_domain, "/collaboration/images/onepix.gif", "Взаимодействие с NRI", "");
table1 = addItem(magementNRI, "/collaboration/images/onepix.gif", "Таблица dbsAntennaPlaces", "/collaboration/references_0.do?comand=dbsAntennaPlaces&sortBy=name");
table2 = addItem(magementNRI, "/collaboration/images/onepix.gif", "Таблица dbsPositions_0", "/collaboration/references_0.do?comand=dbsPositions&sortBy=name");
table3 = addItem(magementNRI, "/collaboration/images/onepix.gif", "Таблица dbsPositions_1", "/collaboration/references_1.do?comand=dbsPositions&sortBy=name");
table4 = addItem(magementNRI, "/collaboration/images/onepix.gif", "Таблица dbsPositions_Page", "/collaboration/references_page.do?comand=dbsPositions&sortBy=name");
table5 = addItem(magementNRI, "/collaboration/images/onepix.gif", "Таблица dbsPositions", "/collaboration/references.do?comand=dbsPositions&sortBy=name");

table6 = addItem(magementNRI, "/collaboration/images/onepix.gif", "Таблица dbsPositions_genericFromXML(EJB)", "/collaboration/generic.do?comand=GET_DbsPositions&sortBy=name&useFastLane=false");
table7 = addItem(magementNRI, "/collaboration/images/onepix.gif", "Таблица dbsPositions_genericFromXML(DAO)", "/collaboration/generic.do?comand=GET_DbsPositions&sortBy=name&useFastLane=true");

table8 = addItem(magementNRI, "/collaboration/images/onepix.gif", "Таблица dbsAntennaPlaces_genericFromXML(EJB)", "/collaboration/generic.do?comand=GET_DbsAntennaPlaces&sortBy=name&useFastLane=false");
table9 = addItem(magementNRI, "/collaboration/images/onepix.gif", "Таблица dbsAntennaPlaces_genericFromXML(DAO)", "/collaboration/generic.do?comand=GET_DbsAntennaPlaces&sortBy=name&useFastLane=true");

table10 = addItem(magementNRI, "/collaboration/images/onepix.gif", "Таблица dbsApparatplaces_genericFromXML(EJB)", "/collaboration/generic.do?comand=GET_dbsApparatplaces&sortBy=name&useFastLane=false");
table11 = addItem(magementNRI, "/collaboration/images/onepix.gif", "Таблица dbsApparatplaces_genericFromXML(DAO)", "/collaboration/generic.do?comand=GET_dbsApparatplaces&sortBy=name&useFastLane=true");

table12 = addItem(magementNRI, "/collaboration/images/onepix.gif", "Таблица dbsapparattypes_genericFromXML(EJB)", "/collaboration/generic.do?comand=GET_dbsapparattypes&sortBy=name&useFastLane=false");
table13 = addItem(magementNRI, "/collaboration/images/onepix.gif", "Таблица dbsapparattypes_genericFromXML(DAO)", "/collaboration/generic.do?comand=GET_dbsapparattypes&sortBy=name&useFastLane=true");

table14 = addItem(magementNRI, "/collaboration/images/onepix.gif", "Таблица dbsapplacetypes_genericFromXML(EJB)", "/collaboration/generic.do?comand=GET_dbsapplacetypes&sortBy=name&useFastLane=false");
table15 = addItem(magementNRI, "/collaboration/images/onepix.gif", "Таблица dbsapplacetypes_genericFromXML(DAO)", "/collaboration/generic.do?comand=GET_dbsapplacetypes&sortBy=name&useFastLane=true");

table16 = addItem(magementNRI, "/collaboration/images/onepix.gif", "Таблица dbscontainertypes_genericFromXML(EJB)", "/collaboration/generic.do?comand=GET_dbscontainertypes&sortBy=name&useFastLane=false");
table17 = addItem(magementNRI, "/collaboration/images/onepix.gif", "Таблица dbscontainertypes_genericFromXML(DAO)", "/collaboration/generic.do?comand=GET_dbscontainertypes&sortBy=name&useFastLane=true");

table18 = addItem(magementNRI, "/collaboration/images/onepix.gif", "Таблица dbsoporaplaces_genericFromXML(EJB)", "/collaboration/generic.do?comand=GET_dbsoporaplaces&sortBy=name&useFastLane=false");
table19 = addItem(magementNRI, "/collaboration/images/onepix.gif", "Таблица dbsoporaplaces_genericFromXML(DAO)", "/collaboration/generic.do?comand=GET_dbsoporaplaces&sortBy=name&useFastLane=true");

table20 = addItem(magementNRI, "/collaboration/images/onepix.gif", "Таблица dbsoporatypes_genericFromXML(EJB)", "/collaboration/generic.do?comand=GET_dbsoporatypes&sortBy=name&useFastLane=false");
table21 = addItem(magementNRI, "/collaboration/images/onepix.gif", "Таблица dbsoporatypes_genericFromXML(DAO)", "/collaboration/generic.do?comand=GET_dbsoporatypes&sortBy=name&useFastLane=true");

tablesNFS = addItem(magementNRI, "", "Таблицы NFS", "");
table1 = addItem(tablesNFS, "/collaboration/images/onepix.gif", "Таблица 1", "/collaboration/navigatorCmd.do?forwardName=table1.content.main");
table2 = addItem(tablesNFS, "/collaboration/images/onepix.gif", "Таблица 2", "/collaboration/navigatorCmd.do?forwardName=table2.content.main");
table3 = addItem(tablesNFS, "/collaboration/images/onepix.gif", "Таблица 3", "/collaboration/navigatorCmd.do?forwardName=table3.content.main");
table4 = addItem(tablesNFS, "/collaboration/images/onepix.gif", "Таблица 4", "/collaboration/navigatorCmd.do?forwardName=table4.content.main");
table5 = addItem(tablesNFS, "/collaboration/images/onepix.gif", "Таблица 5", "/collaboration/navigatorCmd.do?forwardName=table5.content.main");
tablesMSAccess = addItem(magementNRI, "", "Таблицы MSAccess", "");
table1 = addItem(tablesMSAccess, "/collaboration/images/onepix.gif", "Таблица 1", "/collaboration/navigatorCmd.do?forwardName=table1.content.main");
table2 = addItem(tablesMSAccess, "/collaboration/images/onepix.gif", "Таблица 2", "/collaboration/navigatorCmd.do?forwardName=table2.content.main");
table3 = addItem(tablesMSAccess, "/collaboration/images/onepix.gif", "Таблица 3", "/collaboration/navigatorCmd.do?forwardName=table3.content.main");
table4 = addItem(tablesMSAccess, "/collaboration/images/onepix.gif", "Таблица 4", "/collaboration/navigatorCmd.do?forwardName=table4.content.main");
table5 = addItem(tablesMSAccess, "/collaboration/images/onepix.gif", "Таблица 5", "/collaboration/navigatorCmd.do?forwardName=table5.content.main");
logsNRI = addItem(collaboration_domain, "/collaboration/images/onepix.gif", "Журналы взаимодействия с NRI", "");
logNRI_1 = addItem(logsNRI, "/collaboration/images/onepix.gif", "Журнал NRI №1", "/collaboration/navigatorCmd.do?forwardName=log1.content.main");
logsNFS = addItem(logsNRI, "/collaboration/images/onepix.gif", "Журналы NFS:", "");
logNFS_1 = addItem(logsNFS, "/collaboration/images/onepix.gif", "Журнал NFS №1", "/collaboration/navigatorCmd.do?forwardName=log1.content.main");
logNFS_2 = addItem(logsNFS, "/collaboration/images/onepix.gif", "Журнал NFS №2", "/collaboration/navigatorCmd.do?forwardName=log1.content.main"); 
</script>

<script type="text/javascript" language="JavaScript1.2">
initialize()
</script>

<noscript>
<div class='indent1'><b>список задач:</b></div>
<div class='indent1'>Взаимодействие с NRI</div>
<div class='indent2'><a
	href="/collaboration/references.do?comand=dbsAntennaPlaces&sortBy=name"
	target="detail">Таблица dbsAntennaPlaces</a></div>
<div class='indent2'><a
	href="/collaboration/references.do?comand=dbsPositions&sortBy=name"
	target="detail">Таблица dbsPositions</a></div>
	<div class='indent2'><a
	href="/collaboration/references.do?forwardName=ApplicationServer.content.main"
	target="detail">Таблица 3</a></div>	

<div class='indent1'>Журналы взаимодействия с NRI</div>
<div class='indent2'><a
	href="/collaboration/references.do?forwardName=Security.config.view"
	target="detail">Журнал NRI №1</a></div>

<div class='indent2'>Журналы NFS</div>
<div class='indent3'><a
	href="/collaboration/references.do?forwardName=LTPA.config.view"
	target="detail">Журнал NFS №1</a></div>
<div class='indent3'><a
	href="/collaboration/references.do?forwardName=LocalOSUserRegistry.config.view"
	target="detail">Журнал NFS №2</a></div>
</noscript>

</body>
</html>
