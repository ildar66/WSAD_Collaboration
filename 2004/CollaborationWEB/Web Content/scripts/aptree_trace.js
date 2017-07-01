//////////////////////////////////////////////
// This file contains JavaScript functions 
// used to create the tree used for setting 
// trace specificaitons for Diagnostic Trace 
// using the Modify button on the Console
////////////////////////////////////////////// 


//the node to the root of the tree
var tree_root = null;  

//browser type
var browser = browserDetection();             

//index of all the nodes in the tree -- promotes efficient lookup
var nodeIndex = null;        

//the currently selected item from the tree
var selected = null;         

//the id of the currently selected item in the tree (corresponds to the id of the node)
var selectedId = 0;

//depth to which we should initially expand
//expand the entire tree by default
var expandDepth = -1;        

//the path to the image file to display
var imagePath = "";

//the icon shown when a node is collapsed. Set inside the JSP
var plusIcon = "";

//the icon shown when a node is expanded. Set inside the JSP. 
var minusIcon = "";

//frame in which to display the tree. Set inside the JSP. 
var targetFrame = "";

//keep state. Set inside the JSP. 
var keepState = "";

//boolean value. Set inside the JSP. 
var showExpanders = "";

//boolean value that indiacates whether the user has selected that all 
//nodes are enabled to a certain trace specification 
var allEnabled = false; 

//if the tree has a form element above, ns4 needs to adjust the tree up 30 px or so 
var ns4InFormAdjustment = -120;

//the trace level for the currently selected item 
//represent the path to corresponding image to be displayed
var currentTraceLevel = "";

//the trace level for the currently selected item 
//textual representation of the trace level 
var currentTraceSpec = "";

//the string holding the value of all current selections that
//are in the textbox in opener window. Populating from this 
//variable is more efficient that printing the selections array
//when changing tabs or entering the panel with the tree from the main Console page
var traceSpec = "";

//string used to print items in the selections array
var concatSelections = ""; 

//an array holding all current selections. This is different from 
//traceSpec becuase it contains even recent selections, 
//not just those in the opener window. So selections holds all values at 
//any given point, irrespective of whether the user has changed tabs or
//enters the selection panel with the tree from the main Console page
var selections = new Array(); 

//array used to parse tempTrace -- used to repopulate the text box when 
//the user enters the panel with the tree from the main Console page or when 
//they change tabs
var indvTrace;

//the trace levels the user can select
var levels = new Array("all=disabled","entryExit=enabled","event=enabled","debug=enabled","entryExit=enabled,event=enabled","entryExit=enabled,debug=enabled","event=enabled,debug=enabled","all=enabled");

//variable used to create the display
var menuLeft = "0";

//variable used to create the display
var menuTop = "";


//this section of code is executed each time the script is invoked. 
//on the Console, the script is invoked when changing tabs or when 
//opening the panel containing the tree from the main Console page. 

//opener.document.forms[0].selectedComponents -- the trace specification
//text area on the main Console page from the Configuration tab

//opener.document.forms[0].selectedComponentsRuntime -- the trace specification 
//text area on the main Console page from the Runtime tab

//tempTraceSpec the common variable that "binds" the text area in the main 
//Console page to the text area in the panel with the tree. This way, regardless
//of whether the "opener" is another tab or the main Console page, the traceSpec
//variable is correctly populated without doing an "apply" before changing views

if (opener.document.forms[0].selectedComponents == null) {
    if (opener.document.forms[0].selectedComponentsRuntime == null) {
        alert("The Trace Service page is not available");
    } else {
        traceSpec =  opener.tempTraceSpec;
        populateSelections();
    }
} else {
    traceSpec =  opener.tempTraceSpec;
    populateSelections();
    
}

//when changing tabs, we need to make sure that the allEnabled global 
//variable reflects the correct value

for (m=0; m < selections.length; m++) {
    substringArray = selections[m].split("%"); 
    if (substringArray[0] = "*") {
        allEnabled = true; 
    }
}



/////////////////////////////////////////////////////////////
// Define a tree node
//
// Inputs:
//  parent - the parent node
//  id - the id assigned to the node
//  content - the label associated with the node
////////////////////////////////////////////////////////////

function TreeNode(parent, id, content)
{
    // Class Variables
    this.content = content;
    this.parent = parent;         // Parent TreeNode
    this.children = new Array;    // Children of the current TreeNode
    this.childCount = 0;          // Number of children of this TreeNode
    this.id = id;                 // TreeNode Id number
    this.expanded = false;        // Are the children visible
    this.visible = false;         // Is the node itself visible
    this.button = null;           // Browser image object to the PM button
    this.trace = 0;               // The initial trace level for the node (corresponds
                                  // to "all disabled"



    // Set the node icon to the appropriate browser image object
    if (browser == 1) {
        
        this.layer = document.layers["Item"+this.id];
        this.Dimg = this.layer.document.images['Dicon'+this.id];
        this.button = this.layer.document.images['PM'+this.id];
        this.layout = JSTree_Layout;
        if (this.id != 0) {
            document.layers["Item"+this.id].visibility = "hide";
        } else {
            document.layers["Item"+this.id].visibility = "show";
        }
    } else if (browser == 3) {
        this.Dimg = document.getElementsByName('Dicon'+this.id);
    } else {
        this.Dimg = document.all['Dicon'+this.id];
    }

}


/////////////////////////////////////////////////////////////
// Display the inital state of the tree.
//
// Inputs:
//  None.
// Output:
//  Tree is displayed in the browser.
// Notes:
//  The tree has already been built by the setTreeRoot and
//  addTreeNode methods.
/////////////////////////////////////////////////////////////

function initialize()   
{
    
    var node = tree_root;

    // setup the node index for faster access to tree members
    nodeIndex = new Array();
    createNodeIndex(node);
    
    // set the document node to the tree root
    document.node = tree_root;
    
    expandChildren(root,1);
    
    reviewSpec();

    //document.temp.tmpspec.value -- the value contained within the textarea
    //of the panel containing the tree

    document.temp.tmpspec.value = traceSpec;    

    window.status = "Loaded "+nodeIndex.length+" items";
    
    //display the WebSphere "wait" icon

    if (browser == 4) {
        document.all["wait"].style.display = "none";
    } else if (browser == (1 || 3)) {
        document.layers["wait"].visibility="hide";
        document.layers["Item0"].visibility="show";
    } else {
        document.all["wait"].style.display = "none";
    }
    
}

///////////////////////////////////////////////////////////////
// Initialized the selections array to whatever is in traceSpec
// (done when the script is invoked)
//
// Inputs:
//  None.
// Output:
//  The initialies selections array
//////////////////////////////////////////////////////////////

function populateSelections() {

    var stripTrace = traceSpec.replace(/\s/g,"");
    var stripeq = stripTrace.replace(/=/g,"|");
    var stripcomma = stripeq.replace(/,/g,"|");
    
    //tempTrace is an array with each element of the form node|trace_level
    var tempTrace = stripcomma.split(":");

    var nodeName = ""; 
    var traceLevel = ""; 
    
    for (v=0; v<tempTrace.length; v++) {
        nodeName = ""; 
        traceLevel = ""; 
        
        //indvTrace is an array where the first element is the node
        //and every other subsequent element is part of the trace_level string
        indvTrace = tempTrace[v].split("|"); 

        nodeName = indvTrace[0];
        
        // 1 and 2 is the first spec, 3 and 4 is the second spec, 5 and 6 is the third spec
        var tmpsp = indvTrace[1] + "=" + indvTrace[2];
        
        if ((indvTrace[3]) && (indvTrace[4] != "disabled")) {
            tmpsp = tmpsp + ","+indvTrace[3] + "=" + indvTrace[4];
        }
        if ((indvTrace[5]) && (indvTrace[6] != "disabled")) {
            tmpsp = tmpsp + ","+indvTrace[5] + "=" + indvTrace[6];
        }

        traceLevel = tmpsp; 

        //inside the selections array each element is of the form nodename%trace_level
        selections[selections.length] = nodeName + "%" + traceLevel;
        
        
    }
    
}


/////////////////////////////////////////////////////////////
// Review current selections and expand selected nodes with 
// the appropriate image corresponding to the trace level
//
// Inputs:
//  None.
// Output:
//  Tree with selections expanded
/////////////////////////////////////////////////////////////

function reviewSpec()
{

    var stripTrace = traceSpec.replace(/\s/g,"");
    var stripeq = stripTrace.replace(/=/g,"|");
    var stripcomma = stripeq.replace(/,/g,"|");


    //tempTrace is an array with each element of the form node|trace_level
    var tempTrace = stripcomma.split(":");

    var nodeName = ""; 
    var traceLevel = ""; 
    
//    alert("reviewSpec -- stripTrace is "+ stripTrace); 
//    alert("reviewSpec -- traceSpec is " + traceSpec); 
//    alert("reviewSpec -- stripeq is " + stripeq); 
//    alert("reviewSpec -- stripcomma is " + stripcomma); 
//    alert("reviewSpec -- tempTrace is " + tempTrace); 

//    alert("reviewSpec -- tempTrace.length is " + tempTrace.length); 

    for (v=0; v<tempTrace.length; v++) {
        
        //indvTrace is an array where the first element is the node
        //and every other subsequent element is part of the trace_level string
        indvTrace = tempTrace[v].split("|");
        
        nodeName = indvTrace[0];
        
        // 1 and 2 is the first spec, 3 and 4 is the second spec, 5 and 6 is the third spec
        var tmpsp = indvTrace[1] + "=" + indvTrace[2];
        
        if ((indvTrace[3]) && (indvTrace[4] != "disabled")) {
            tmpsp = tmpsp + ","+indvTrace[3] + "=" + indvTrace[4];
        }
        if ((indvTrace[5]) && (indvTrace[6] != "disabled")) {
            tmpsp = tmpsp + ","+indvTrace[5] + "=" + indvTrace[6];
        }

        //the the trace level to the appropriate level in the levels array. 
        //if not in the levels array

        for (g=0;g<levels.length;g++) {
            if (tmpsp == levels[g]) {
                //alert("incoming matched "+levels[g]);                                
                traceLevel = levels[g];                                
                break;                
            }
        }
        
        //go through every node in the tree which is represented by nodeIndex. 
        //if the content of the tree node matching the nodeName of one that has
        //been selected, we expand that node by invoking the openTree method
        //and do the appropriate image coloring by invoking changeLevel

        for (i = 0; i < nodeIndex.length; i++) {
            var node = nodeIndex[i];
            //if the node is the first element the content is everything is selected (or "*")
            if (i == 0) {
                node.content = "*";
            }
            if (node.content == nodeName) {
                //alert("reviewSpec -- found: "+node.content+", with this trace - "+tmpsp+", which matches - "+levels[g]);
                selectedId = node.id;
                //alert("reviewSpec -- selectedId is " + selectedId); 
                openTree(node);
                changeLevel(g);
                break;  

            }

        }        
    }
}

/////////////////////////////////////////////////////////////
// Create an index of all the nodes in the tree to speedup
// lookup work.
//
// Inputs:
//  node -- the root of the tree.
// Output:
//  The nodeIndex is created.
/////////////////////////////////////////////////////////////
function createNodeIndex(node)
{

    nodeIndex[node.id] = node;

    var i;
    for (i = 0; i < node.childCount; i++) {
        createNodeIndex(node.children[i]);        
    }
    
}


/////////////////////////////////////////////////////////////
// Add an item to the tree
// Inputs:
//  parent - parent node
//  id - the id of the node
//  content - the label of the node
// Output:
//   The tree node that has been created.
/////////////////////////////////////////////////////////////

function addItem(parent,id,content)
{
    var selectable = true;
    
    var tempNode = new TreeNode(parent,id,content);      
    parent.children[parent.childCount++] = tempNode; 
    
    //alert("Added "+tempNode.content);

    return tempNode;
}


/////////////////////////////////////////////////////////////
// determine browser type
//
// Inputs:
//  none        
// Output:
//  set browser type
// Notes: 
//  Browser Types:
//      0 - Not supported
//      1 - Netscape Communicator 4.x
//      2 - IE
//      3 - Opera
//      4 - NS6
/////////////////////////////////////////////////////////////

function browserDetection()  {
    
    var thebrowser;

    var foropera = window.navigator.userAgent.toLowerCase();
    var itsopera = foropera.indexOf("opera",0) + 1;
    var itsgecko = foropera.indexOf("gecko",0) + 1;
    var itsmozillacompat = foropera.indexOf("mozilla",0) + 1;
    var itsmsie = foropera.indexOf("msie",0) + 1;



    if (itsopera > 0) {
        thebrowser = 3;
        //alert("its opera");
        document.all = document.getElementsByTagName("*")

    }

    if (itsmozillacompat > 0) {
        //alert("its mozilla compatible");
        if (itsgecko > 0) {
            thebrowser = 4
            //alert("its gecko");
            document.all = document.getElementsByTagName("*")                           
        } else if (itsmsie > 0) {
            //alert("its msie");
            thebrowser = 2
        } else {
            //alert("its ns4.x")
            thebrowser = 1
        }
    }

    return thebrowser;

}


/////////////////////////////////////////////////////////////
// Set the root of the tree to be constructed
// Inputs:
//  id - the id of the root
//  content - the content of the root
// Output:
//  The root tree node that has been created.
/////////////////////////////////////////////////////////////

function addRoot(id,content)
{

    selectable = false;

    // create a new node and set that as the root
    
    var tempNode = new TreeNode("",id,content);

    tree_root = tempNode;
    return tempNode;
    
}


/////////////////////////////////////////////////////////////
// Expand the chlidren of the node by making the chlidren
// nodes visable.
//
// Inputs:
//  node - the node to be expanded
//  depth - the absolute depth to which to expand (optional)
//           null:  expand leaves if previously expanded
//           < 0 :  forcefully expand entire tree
//           > 0 :  forcefully expand tree n levels from root
// Output:
//  None.
/////////////////////////////////////////////////////////////

function expandChildren(node,depth)
{
    var i;
    if ( depth ) {
        depth--;
    }

    depth = 0;        
    node.expanded = true;
    
    
    
    for (i = 0; i < node.childCount; i++) {
        
        var cnode = node.children[i];

        // show the layers of this node 
        
        if (browser == 1) {
            
            document.layers['Item'+cnode.id].visibility="show";
        } else if (browser == 3) {
            document.getElementById("Item"+cnode.id).style.visibility = "VISIBLE"; 
            
        } else {
            document.all["Item"+cnode.id].style.display = "block";
            
        }
        cnode.visible = true;

        // open up any previouly open children
        if ( depth == null && cnode.expanded )
            expandChildren(cnode);
        else {
            if (depth != null && depth != 0 )
                expandChildren(cnode,depth);
        }

    }
    
}

/////////////////////////////////////////////////////////////
// Event Handler for the Plus-Minus buttons.  This procedure
// will either expand a node or compress a node depending on
// the current state of node.
//
// Inputs:
//  id - the id number of the node of which created the event
// Output:
//  None.
/////////////////////////////////////////////////////////////

function expandCompressNode(id)
{
    var node = nodeIndex[id];
    
    
    if (browser == 1) {
        node.layer = document.layers["Item"+node.id];
        node.button = node.layer.document.images['PM'+node.id];
    } else if (browser == 3) {
        node.button = document.getElementsByName('PM'+node.id);
    } else {
        node.button = document.all['PM'+node.id];
    }

    
    if (!node.expanded) {
        
        node.button.src = imagePath + "lminus.gif";
        
        node.expanded = true;
        
        expandChildren(node);
    }

    else {
        
        node.button.src = imagePath + "lplus.gif";
        
        node.expanded = false;
        
        compressChildren(node);
    }
    
    if (browser == 1) {
        JSTree_Layout();
    }

}


/////////////////////////////////////////////////////////////
// Compress the chlidren of the node by hiding its chlidren
// nodes.
//
// Inputs:
//  node - the node to be compressed
// Output:
//   None.
/////////////////////////////////////////////////////////////

function compressChildren(node)
{
    var i;

    for (i = 0; i < node.childCount; i++) {
        var cnode = node.children[i];

        if (browser == 1) {
            document.layers['Item'+cnode.id].visibility="hide";
        } else if (browser == 3) {
            document.getElementById("Item"+cnode.id).style.visibility = "HIDDEN";
            
        } else {
            document.all["Item"+cnode.id].style.display = "none";
            
        }
        
        cnode.visible = false;

        if (cnode.childCount > 0) {
            
            compressChildren(cnode);
        }
    }
}

/////////////////////////////////////////////////////////////
// Layout the layers of the tree one after another.
//
// Inputs:
//  None.  Uses document.layers to do the layout.
// Output:
//  A Laid out tree.
// Note:
//  This function is only needed for Netscape.
/////////////////////////////////////////////////////////////
function JSTree_Layout()
{
    var docLayers;
    
    //extract the layers of the document
    docLayers = document.layers;

    //initalize position and loop variables
    var i = 0;
    var posY = docLayers[i].pageY + docLayers[i].document.height + ns4InFormAdjustment;
    for (i = 1; i < docLayers.length; i++) {
        //don't layout a hidden layer
        if (docLayers[i].visibility != "hide") {
            //set the location of the layer
            docLayers[i].moveTo(docLayers[i].x, posY);
            posY += docLayers[i].document.height;
        }
    }
}

/////////////////////////////////////////////////////////////
// Change the trace level for a node and display appropriate
// image
//
// Inputs:
//  lvl - the trace level to change to  
// Output:
//  tree reflecting the selections
/////////////////////////////////////////////////////////////

function changeLevel(lvl) { 

    if (allEnabled == true) {
        clearTree();
    }

    var thispar;

    if (browser == 4) {
        document.getElementById("progress").style.visibility = "hidden";
    } else if (browser == (1 || 3)) {
        document.layers["progress"].visibility="hide";
    } else {
        document.all["progress"].style.visibility = "hidden";
    }
    
    selected = nodeIndex[selectedId];
    
    //if the id of the selected item is 0 that means it is the root of the tree
    //so the content is that everything is selected (or "*")

    if (selected.id == "0") {
        selected.content = "*"
    }

    //if the id of the selected item is 0 that means it is the root and that 
    //all children are enabled for a particular trace. The boolean value allEnabled 
    //is then set to true

    if (selected.id == "0") {
        allEnabled = true; 
    } else {
        allEnabled = false; 
    }


    if (lvl == '1' || lvl == 1) {
        selected.Dimg.src = imagePath+"trace_1.gif";
        selected.Dimg.alt = "debug";
        selected.trace = lvl; 
        
    } else if (lvl == '2' || lvl == 2) {
        selected.Dimg.src = imagePath+"trace_2.gif";
        selected.Dimg.alt = "debug + entry/exit";
        selected.trace = lvl;

    } else if (lvl == '3' || lvl == 3) {
        selected.Dimg.src = imagePath+"trace_3.gif";
        selected.Dimg.alt = "debug + entry/exit + event";
        selected.trace = lvl;
    } else if (lvl == '4' || lvl == 4) {
        selected.Dimg.src = imagePath+"trace_4.gif";
        selected.Dimg.alt = "entry/exit + event";
        selected.trace = lvl;
    } else if (lvl == '5' || lvl == 5) {
        selected.Dimg.src = imagePath+"trace_5.gif";
        selected.Dimg.alt = "debug + event";
        selected.trace = lvl;
    } else if (lvl == '6' || lvl == 6) {
        selected.Dimg.src = imagePath+"trace_6.gif";
        selected.Dimg.alt = "entry/exit";
        selected.trace = lvl;
    } else if (lvl == '7' || lvl == 7) {
        selected.Dimg.src = imagePath+"trace_7.gif";
        selected.Dimg.alt = "event";        
        selected.trace = lvl;
    } else {
        selected.Dimg.src = imagePath+"trace_0.gif";
        selected.Dimg.alt = "all disabled";
        selected.trace = lvl;
    }

    
    currentTraceLevel = selected.Dimg.src;
    currentTraceSpec = selected.content+levels[selected.trace];
    
    if (selected.id != "0") {
            
        for (j = 0; j < selected.childCount; j++) {
            
            var cnode = selected.children[j]
                        
            if (cnode.childCount > 0) {
                cnode.Dimg.src = currentTraceLevel;
                cnode.trace = selected.trace;
                changeDIconChild(cnode.id);
                
            } else {
                cnode.Dimg.src = currentTraceLevel;
                cnode.trace = selected.trace;
            }
        }
              
    } else {
        
        for (i = 1; i < nodeIndex.length; i++) {
            var anode = nodeIndex[i];
            anode.Dimg.src = currentTraceLevel;
            anode.trace = selected.trace;
            
        }   
        
    }

    //we store the current selection in the selections array
    //and reprint (update) the contents of the text area in the 
    //panel containing the tree. 

    storeSelection(selected.content, levels[selected.trace]); 
    printSelection(); 

}

/////////////////////////////////////////////////////////////
// Clear the trace level for all nodes in the tree. Set every
// node's trace level and appropriate image to "all disabled"
//
// Inputs:
//  None
// Output:
//  None
/////////////////////////////////////////////////////////////

function clearTree() {

    clearImage = imagePath+"trace_0.gif";
    traceLevel = 0; 

    for (i = 0; i < nodeIndex.length; i++) {
        var anode = nodeIndex[i];
        anode.Dimg.src = clearImage;
        anode.trace = traceLevel;           
    }
}

/////////////////////////////////////////////////////////////
// Add a selection to the slections array
//
// Inputs:
//  content - the node name
//  level - the trace level text
// Output:
//  updated selections array reflecting current selections
// Notes: 
//  the format of an element in the selections array is 
//  content%level
/////////////////////////////////////////////////////////////

function storeSelection(content, level) {

    //If the content is "every node is selected" (or "*")
    //clear our the selections array by replacing all existing
    //values with blank%blank. This will ensure that all existing
    //selectionsi in the textarea of the panel continaing the tree
    //will be wiped out on printing and replaced with "*=all=<level>

    //If the content is not "every node is selected" then if our selections
    //array currently has an element with the content set to "*" we clear that 
    //out with a "blank%blank. So it will not be continued to be printed even though
    //child selections have overriden what was once selected at the root.

    if (content == "*") {
        for (m=0; m < selections.length; m++) {
            selections[m] = "blank%blank"; 
        }
    } else if (content != "*") {
        for (n=0; n < selections.length; n++) {
            selectionContent = selections[n].split("%"); 
            if (selectionContent[0] == "*") {
                selections[n] = "blank%blank";             
            }
        }
    }

    //madeChange is a boolean variable that reflects if an existing element's
    //trace specification was just changed

    var madeChange = false; 


    if (selections.length == 0) {
        selections[0] = content + "%" + level;
    } else {

        for (i = 0; i < selections.length; i++) {

            //substringArray is an array representing one element of selections
            //the first element in substringArray in the node (content) and the second
            //element is the trace level

            substringArray = selections[i].split("%");  
            
            if (substringArray[0] == content) {
                selections[i] = substringArray[0]+"%"+level;  
                madeChange = true;               
            }

        }

        //if an exisiting element wasn't just modified with a new trace level, add
        //onto the selections array

        if (madeChange == false) {
            selections[selections.length] = content + "%" + level;
        }

    }

    madeChange = false; 
}


/////////////////////////////////////////////////////////////
// Prints the contents of the selections array into the 
// textarea of the panel containing the tree
//
// Inputs:
//  None
// Output:
//  None
/////////////////////////////////////////////////////////////

function printSelection() {

    concatSelections = ""; 

    if (selections.length == 1) {
        substringArray = selections[0].split("%"); 
        document.temp.tmpspec.value = substringArray[0]+"="+substringArray[1];
    } else {
        
        for (i = 0; i < selections.length; i++) {  

            //substringArray is an array representing one element of selections
            //the first element in substringArray in the node (content) and the second
            //element is the trace level

            substringArray = selections[i].split("%");
            
            //temp contains the selection with an equals sign in place of the

            var temp = substringArray[0]+"="+substringArray[1]; 

            if (temp != "blank=blank") {
                if (concatSelections == "") {
                    concatSelections = temp;     
                } else {
                    concatSelections = concatSelections +  ": " + temp;
                }             
            }
        }
        document.temp.tmpspec.value = concatSelections;
    }

}

//////////////////////////////////////////////////////////////
// Prints the contents of the textarea in the panel containing 
// the tree into the textarea of the main Console page
//
// Inputs:
//  None
// Output:
//  The textarea of the main Console page is updated
//////////////////////////////////////////////////////////////

function publishSpec() {
    if (opener.document.forms[0].selectedComponents == null) {
        if (opener.document.forms[0].selectedComponentsRuntime == null) {
            alert("The console Trace Service page is not available, please navigate to that page in the console clicking Apply");
        } else {
            
            if (document.temp.tmpspec.value != "") {
                opener.document.forms[0].selectedComponentsRuntime.value = document.temp.tmpspec.value;
                opener.tempTraceSpec = document.temp.tmpspec.value;
            } else {
                opener.document.forms[0].selectedComponentsRuntime.value = "*=all=disabled";
                opener.tempTraceSpec = "*=all=disabled";
            } 
            
        }
    } else {
        
        if (document.temp.tmpspec.value != "") {
            opener.document.forms[0].selectedComponents.value = document.temp.tmpspec.value;
            opener.tempTraceSpec = document.temp.tmpspec.value;
        } else {
            opener.document.forms[0].selectedComponents.value = "*=all=disabled";
            opener.tempTraceSpec = "*=all=disabled";
        }
    }       
}

//////////////////////////////////////////////////////////////
// Expands a node
//
// Inputs:
//  anode - the node to expand
// Output: 
//  None
//////////////////////////////////////////////////////////////

function openTree(anode) {

    node = anode;
    
    if (!node.expanded) {
        
        if (browser == 1) {
            node.layer = document.layers["Item"+node.id];
            node.button = node.layer.document.images['PM'+node.id];
        } else if (browser == 3) {
            node.button = document.getElementsByName('PM'+node.id);
        } else {
            node.button = document.all['PM'+node.id];
        }
        
        if (node.button) {
            node.button.src = minusIcon;
        }

        node.expanded = true;
        
        expandChildren(node);
        
        openTree(node.parent);

    }

}


/**********************************************************************
*  Functions that set global variables
***********************************************************************/

//set the url to abort to if browser dosen't support dhtml

function setAbort(url)
{
    abort = url;
}

//set hte image path

function setImagePath(ip)
{
    imagePath = ip;
}

//set the depth to expand to 

function setExpandDepth(iDepth)
{
    if ( iDepth != null && iDepth >= 0 ) {
        expandDepth = iDepth;
    }
}

//set showExpanders boolean variable

function setShowExpanders(sExpand)
{
    showExpanders = sExpand;
    
}

//set the keepState boolean variable

function setKeepState(ks)
{
    keepState = ks;
}

//set the showHealth global variable

function setShowHealth(sh)
{
    showHealth = sh;
}

//set the inTable global variable

function setInTable(it)
{
    inTable = it;
}

//set the target frame to display the tree

function setTargetFrame(tf)
{
    targetFrame = tf;
}



/**********************************************************************
*  Helper and Utility functions *
***********************************************************************/


// browsers parse (and unescape) the href in the processing
// of an onclick.  By the time str gets to our callback,
// it is escaped again.  This ensures our callback gets a
// string with escaped backslashes.

function ebs(str)
{
    if (!str || str == null)
        return("");

    return(str.replace(/\\/g, "\\\\"));
}

// set the location of the target frame

function clk(targetFrame, sPath)
{
    targetFrame.location= sPath;
}


// change the id of the currently selected item

function changeDIcon(id)
{

    var forlevel = 0;
    var node = nodeIndex[id];
    selectedId = id;
    
}


//change the id and properties of child nodes

function changeDIconChild(id)
{
    var node = nodeIndex[id];
    
    for (k = 0; k < node.childCount; k++) {
        
        var cnode = node.children[k]
                    
        if (cnode.childCount > 0) {
            changeDIconChild(cnode.id);
            
        } else {
            cnode.Dimg.src = currentTraceLevel;
            cnode.trace = node.trace;
        }
    }



}

//function used for the display

function moveMenu() {

    eY = "";
    
    
    if (browser == 4) {
        document.getElementById("progress").style.top = menuTop;
        document.getElementById("progress").style.left = menuLeft;
        document.getElementById("progress").style.visibility = "visible";
        
    } else if (browser == (1 || 3)) {
        document.layers["progress"].visibility="show";
        document.layers["progress"].top = menuTop;
        document.layers["progress"].left = menuLeft;
    } else {
        document.all["progress"].style.pixelTop = menuTop;
        document.all["progress"].style.pixelLeft = menuLeft;
        document.all["progress"].style.visibility = "visible";
    }

}

//function that creates a state array

function stateArrayPush() {
    this[this.length] = arguments[i];
    return this.length;
}

//function that splices a state array

function stateArraySplice(startat,len,theArray){
    var deletedItems = new Array();
    var remainingItems = new Array();
    deletedItems = theArray.slice(startat,startat+len);
    remainingItems = theArray.slice(startat+len);
    theArray.length = startat;
    for (var i=0;i<remainingItems.length;i++) {
        theArray[theArray.length] = remainingItems[i];
    }
    return deletedItems;
}

//function used for the display

function hideMenu() {

    if (browser == 4) {
        document.getElementById("progress").style.visibility = "hidden";
    } else if (browser == 1 || browser == 3) {
        document.layers["progress"].visibility="hide";
    } else {
        document.all["progress"].style.visibility = "hidden";
    }
    

}

//function used for the display

function wheresTheClick(e) {

    var what = "";
    
    if (browser == 4) {
        menuLeft = e.clientX;
        menuTop = e.clientY;
        whatpar = e.target.parentNode;
        what = whatpar.name
        if (what == "treeitem") {
            moveMenu(selected);
        } else {
            hideMenu();
        }
    }
    if ((browser == 1) || (browser == 3)) {
        menuLeft = e.pageX;
        menuTop = e.pageY;
        what = e.target.href;
        if (e.target.href) {
            if (what.indexOf("changeDIcon") > -1) {
                moveMenu();
            } else {
                document.layers["progress"].visibility="hide";
            }
            
        } else {
            document.layers["progress"].visibility="hide";
        }
        routeEvent(e);
        
        
    }
    if (browser == 2) {
        e = event; 
        menuLeft = e.clientX;
        menuTop = e.srcElement.offsetTop;
        what = e.srcElement.name;
        if (what == "treeitem") {
            moveMenu(selected);
        } else {
            hideMenu();
        }
    }

}

