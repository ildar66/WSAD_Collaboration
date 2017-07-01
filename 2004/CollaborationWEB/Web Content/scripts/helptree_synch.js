/***************************
IBM Confidential OCO Source Material 
5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 
The source code for this program is not published or otherwise divested 
of its trade secrets, irrespective of what has been deposited with the 
U.S. Copyright Office.  
***************************/

function selectCheck() {
   
   var currentDoc = top.HelpDetail.location.href;
   //alert(top.nodeIndex[3].link);
   
   var itsThere = "no";
   
   var nodes = top.nodeIndex;
   
   for (var a=0;a<nodes.length;a++) {
        if (nodes[a].link.indexOf(".html") > -1) {
                if (currentDoc.indexOf(nodes[a].link) > -1) {
                        //alert("found "+nodes[a].content+" - "+nodes[a].link);
                        itsThere = "yes";
                        break;
                }
        }
   }
   

   if (itsThere == "yes") {
   
           var currnode = nodes[a];
           var priornode = nodes[top.prioron];
           top.prioron = top.currenton;
           top.currenton = a;
           //alert("current: "+top.currenton+", prior: "+top.prioron);
          
           var lev = currnode.level - 1;
           var tmpnode = currnode;
           
        
           //alert("level is " + lev);
           
        
           for (var x=1; x < lev; x++) {
           
                 parennode = nodes[tmpnode.parent.id];
                
                 //alert("Parent: "+parennode.content);
                
                 if (browser == 1) {
                    parennode.layer = document.layers["Item"+parennode.id];
                    parennode.button = parennode.layer.document.images['PM'+parennode.id];
                 }
                 else if (browser == 3) {
                    parennode.button = document.getElementsByName('PM'+parennode.id);
                 }               
                 else {
                    parennode.button = document.all['PM'+parennode.id];
                 }
        
                
                if (parennode.button) {
                        parennode.button.src = imagePath + "/admin/images/lminus.gif";
                }
        
                parennode.expanded = true;
        
                expandChildren(parennode);
                
                if (browser == 1 || browser ==3) {
                parennode.layout();
                }
                
                tmpnode = parennode;
           }
           
        
           
                var prior = "Item"+top.prioron;
                var now = "Item"+top.currenton;
                
        
                
                if (browser == 2) {
                        if (top.prioron != "") {
                                document.all[prior].style.backgroundColor="#E2E2E2";
                        }
                        
                        document.all[now].style.backgroundColor="#CCCCCC";
                        thisItem = document.all[now].offsetTop;
                                                        
                        thisWin = document.body.clientHeight;
                        thisWinscroll = document.body.scrollTop;
                        //alert("Item: "+thisItem);  
                        //alert("Window: "+thisWin);
                        //alert("Scroll: "+document.body.scrollTop);
                        visibleWin = thisWinscroll + thisWin;
                        if (thisItem > visibleWin) {
                                //alert("scroll down");
                                //diff = (thisItem - visibleWin) + (thisWin/2)
                                vis = thisItem - 100;
                                window.scrollTo(0,vis);
                        }
                        if (thisItem < thisWinscroll) {
                                //alert("scroll up");
                                //diff = thisItem - thisWinscroll;
                                vis = thisItem - 100;
                                window.scrollTo(0,vis);
                        }
                        //diff = (thisItem - (thisWin+thisWinscroll)) + (thisWin/2);
                        //alert(diff);
                        //window.scroll(0,diff);
                }
                
                else if (browser == 4) {
                        //alert("inDOm");
                        if (top.prioron != "") {
                                document.all[prior].style.backgroundColor="#E2E2E2";
                        }
                        
                        document.all[now].style.backgroundColor="#CCCCCC";
                        
                        thisItem = document.all[now].offsetTop;
                                                        
                        //thisWin = document.body.clientHeight;
                        //thisWinscroll = document.body.scrollTop;
                         //alert("Item: "+thisItem);  
                        // alert("Window: "+window.innerHeight);
                        //alert("Scroll: "+document.body.scrollTop);
                        //visibleWin = thisWinscroll + thisWin;
                        //if (thisItem > visibleWin) {
                                //alert("scroll down");
                                //diff = (thisItem - visibleWin) + (thisWin/2)
                        //        vis = thisItem - 100;
                        //        window.scrollTo(0,vis);
                        //}
                        //if (thisItem < thisWinscroll) {
                        //          alert("scroll up");
                        //          diff = thisItem - thisWinscroll;
                        //        vis = thisItem - 100;
                        //        window.scrollTo(0,vis);
                        //}
                        //diff = (thisItem - (thisWin+thisWinscroll)) + (thisWin/2);
                        //alert(diff);
                        window.scrollTo(0,thisItem-25);
                }
                
                else {
                        if (top.prioron != "") {
                        //alert("in ns");
                        document.layers[prior].bgColor=null;
                        }
                        
                        document.layers[now].bgColor="#CCCCCC";
                        thisItem = document.layers[now].top;
                        thisWin =  window.innerHeight;
                        thisWinscroll = window.pageYOffset;
                        visibleWin = thisWinscroll + thisWin;
                        if (thisItem > visibleWin) {
                                //alert("scroll down");
                                //diff = (thisItem - visibleWin) + (thisWin/2)
                                vis = thisItem -100;
                                window.scrollTo(0,vis);
                        }
                        if (thisItem < thisWinscroll) {
                                //alert("scroll up");
                                //diff = thisItem - thisWinscroll;
                                vis = thisItem - 200;
                                window.scrollTo(0,vis);
                        }
                        //alert("thisItem = "+thisItem+" srollbottom = "+pageYOffset);
                        //alert(window.innerHeight); 
                        //alert(window.pageYOffset);              
                }  
                
           
           
                if (browser == 1 || browser == 3) {
                
                        // extract the layers of the document
                        var docLayers = document.layers;
                
                        // initalize position and loop variables
                        var i = 0;
                        var posY = docLayers[i].pageY + docLayers[i].document.height - ns4InFormAdjustment;
                
                        for (i = 1; i < docLayers.length; i++)
                        {
                        // don't layout a hidden layer
                                if (docLayers[i].visibility != "hide") {
                         // set the location of the layer
                                        docLayers[i].moveTo(docLayers[i].x, posY);
                                        posY += docLayers[i].document.height;
                                }
                        }
                
                
                }
           
                
                return false;
        }       

}
