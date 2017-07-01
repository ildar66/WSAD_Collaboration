<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

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
