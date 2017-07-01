<?xml version='1.0'?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"> 

<!-- IBM Confidential OCO Source Material -->
<!-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 1997, 2003 -->
<!-- The source code for this program is not published or otherwise divested -->
<!-- of its trade secrets, irrespective of what has been deposited with the -->
<!-- U.S. Copyright Office. -->

   <xsl:template match="/">
    <HTML>
        <HEAD>
        <title>Deployment Descriptor View</title>
        
        
        <STYLE>
        
        
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
        .indent1kids {   padding-left:-19px; font-family: Arial,Helvetica, sans-serif; margin-top: 10px; margin-bottom: 10px; font-weight: bold; font-size: 120% } 
        .indent2kids {   padding-left:0px; font-family: Arial,Helvetica, sans-serif; margin-top: 5px; margin-bottom: 5px  }
        .indent3kids {   padding-left:19px; font-family: Arial,Helvetica, sans-serif; margin-top: 5px; margin-bottom: 5px   }
        .indent4kids {   padding-left:38px; font-family: Arial,Helvetica, sans-serif; margin-top: 5px; margin-bottom: 5px  }
        .indent5kids {   padding-left:57px; font-family: Arial,Helvetica, sans-serif; margin-top: 5px; margin-bottom: 5px   }
        .indent6kids {   padding-left:76px; font-family: Arial,Helvetica, sans-serif; margin-top: 5px; margin-bottom: 5px   }
        .indent7kids {   padding-left:95px; font-family: Arial,Helvetica, sans-serif; margin-top: 5px; margin-bottom: 5px   }
        .indent8kids {   padding-left:114px; font-family: Arial,Helvetica, sans-serif; margin-top: 5px; margin-bottom: 5px   }
        .indent9kids {   padding-left:133px; font-family: Arial,Helvetica, sans-serif; margin-top: 5px; margin-bottom: 5px   }
        .indent10kids {   padding-left:152px; font-family: Arial,Helvetica, sans-serif; margin-top: 5px; margin-bottom: 5px   }

        H3 { margin-left: 5px; font-size: 115% }
        BODY {  color: #000000; font-size: 75%}
        A { text-decoration: none; color:#000000 }
        #Item0 { margin-bottom: 10px }


        .goback { margin-left: 0px; font-size: 100%; font-weight:bold; color: #0000FF; font-family: Arial,Helvetica, sans-serif; }

        </STYLE>
        
           <xsl:text disable-output-escaping="yes">
           <![CDATA[
           <SCRIPT LANGUAGE="JavaScript">
           if (document.layers) {
                document.write("<STYLE>");
                document.write("LAYER { color: #000000; font-size: 70% }");
                document.write("</STYLE>");
           }
           </SCRIPT>
           ]]>
           </xsl:text>


        </HEAD>
        <BODY>
        

           <xsl:text disable-output-escaping="yes">
           <![CDATA[
           <SCRIPT LANGUAGE="JavaScript">
                document.write("<p> < <a class='goback' href='javascript:history.back()'>Back</a></p>");
           </SCRIPT>
           ]]>
           </xsl:text>

        
   <xsl:text disable-output-escaping="yes">
   <![CDATA[
   <SCRIPT LANGUAGE="JavaScript" src="scripts/aptree.js"></SCRIPT>
   ]]>
   </xsl:text>

        
                <xsl:apply-templates select="web-app"/>

        
         
      </BODY>
    </HTML>
   </xsl:template>


<xsl:template match="web-app">


    <xsl:variable name="maxCount">1000</xsl:variable>
    <xsl:choose>
        <xsl:when test="count(//*)>$maxCount">

            <!--  This is the large item number path -->
            <H3>web.xml<xsl:if test="display-name"><xsl:text> for "</xsl:text><xsl:value-of select="display-name"/> <xsl:text>"</xsl:text> </xsl:if></H3>
            <div class="indent2"><form><textarea cols="70" rows="35" WRAP="off">                                                                                         
                    <xsl:copy-of select="/node()"/>
            </textarea></form></div>
        </xsl:when>

        <!--  This is the small item number path -->
        <xsl:otherwise>

                <NOSCRIPT>
                <H3>web.xml<xsl:if test="display-name"><xsl:text> for "</xsl:text><xsl:value-of select="display-name"/> <xsl:text>"</xsl:text> </xsl:if></H3>
                <div class="indent2"><form><textarea cols="50" rows="35" WRAP="off">                                                                                         
                        <xsl:copy-of select="/node()"/>
                </textarea></form></div>
                </NOSCRIPT>


        <SCRIPT>
        <xsl:text><![CDATA[
        setImagePath("images/");
        setShowExpanders(true);
        setExpandDepth(1);
        setKeepState(false);
        setShowHealth(false);
        setInTable(false);
        setTargetFrame("detail");
        openFolder = "open_folder.gif";
        closedFolder = "closed_folder.gif";
        plusIcon = "lplus.gif";
        minusIcon = "lminus.gif";
        ]]></xsl:text>
        </SCRIPT>
   


<SCRIPT>
<xsl:text><![CDATA[domain = addRoot("images/onepix.gif","<b>Web.xml for \"]]></xsl:text><xsl:if test="display-name"><xsl:value-of select="display-name"/></xsl:if><xsl:text><![CDATA[\"</b>","");]]></xsl:text>
<xsl:if test="description"><xsl:text><![CDATA[item1 = addItem(domain,"images/onepix.gif","Description: ]]></xsl:text><xsl:call-template name='illegalChar-replace'><xsl:with-param name='text'><xsl:value-of select='description' disable-output-escaping='yes'/></xsl:with-param></xsl:call-template><xsl:text><![CDATA[","");]]></xsl:text></xsl:if>

</SCRIPT> 


        

        <xsl:if test="context-param">
        <SCRIPT>
        <xsl:text><![CDATA[contextparam = addItem(domain,"images/onepix.gif","Context Parameters","");]]></xsl:text>
        </SCRIPT>
        <xsl:apply-templates select="context-param"><xsl:sort select="param-name"/></xsl:apply-templates>
        </xsl:if>
        
        <xsl:if test="filter">
        <SCRIPT>
        <xsl:text><![CDATA[filter = addItem(domain,"images/onepix.gif","Filters","");]]></xsl:text>
        </SCRIPT>
        <xsl:apply-templates select="filter"><xsl:sort select="filter-name"/></xsl:apply-templates>                       
        </xsl:if>

        <xsl:if test="filter-mapping">
        <SCRIPT>
        <xsl:text><![CDATA[filtermap = addItem(domain,"images/onepix.gif","Filter Mappings","");]]></xsl:text>
        </SCRIPT>
        <xsl:apply-templates select="filter-mapping"><xsl:sort select="filter-name"/></xsl:apply-templates>
        </xsl:if>

 
        
        <xsl:if test="listener">
        <SCRIPT>
        <xsl:text><![CDATA[listener = addItem(domain,"images/onepix.gif","Listeners","");]]></xsl:text>
        </SCRIPT>

        <xsl:apply-templates select="listener"><xsl:sort select="listener-class"/></xsl:apply-templates>
        </xsl:if>
     

        <xsl:if test="servlet">
        <SCRIPT>
        <xsl:text><![CDATA[servlet = addItem(domain,"images/onepix.gif","Servlets and JSPs","");]]></xsl:text>
        </SCRIPT>
        <xsl:apply-templates select="servlet"><xsl:sort select="servlet-name"/></xsl:apply-templates>
        </xsl:if>
               
                


        <xsl:if test="servlet-mapping">
        <SCRIPT>
        <xsl:text><![CDATA[servletmap = addItem(domain,"images/onepix.gif","Servlet Mappings","");]]></xsl:text>
        </SCRIPT>
        <xsl:apply-templates select="servlet-mapping"><xsl:sort select="servlet-name"/></xsl:apply-templates>
        </xsl:if>

    
        <xsl:if test="session-config">
        <SCRIPT>
        <xsl:text><![CDATA[sessionconfig = addItem(domain,"images/onepix.gif","Session Configuration","");]]></xsl:text>
        </SCRIPT>
        <xsl:apply-templates select="session-config"/>
        </xsl:if>
    
    
        <xsl:if test="mime-mapping">
        <SCRIPT>
        <xsl:text><![CDATA[mimemap = addItem(domain,"images/onepix.gif","Mime Mapping","");]]></xsl:text>
        </SCRIPT>
        <xsl:apply-templates select="mime-mapping"><xsl:sort select="mime-type"/></xsl:apply-templates>
        </xsl:if>

    
 

    
        <xsl:if test="welcome-file-list">
        <SCRIPT>
        <xsl:text><![CDATA[welcomefile = addItem(domain,"images/onepix.gif","Welcome File List","");]]></xsl:text>
        </SCRIPT>
        <xsl:apply-templates select="welcome-file-list"><xsl:sort select="welcome-file"/></xsl:apply-templates>
        </xsl:if>


   
    
        <xsl:if test="error-page">
        <SCRIPT>
        <xsl:text><![CDATA[errorpage = addItem(domain,"images/onepix.gif","Error Pages","");]]></xsl:text>
        </SCRIPT>
        <xsl:apply-templates select="error-page"><xsl:sort select="location"/></xsl:apply-templates>
        </xsl:if>




    <xsl:if test="taglib">
        <SCRIPT>
        <xsl:text><![CDATA[taglib = addItem(domain,"images/onepix.gif","Tag Libraries","");]]></xsl:text>
        </SCRIPT>
        <xsl:apply-templates select="taglib"><xsl:sort select="taglib-uri"/></xsl:apply-templates>
    </xsl:if>
    
 

 
    <xsl:if test="security-constraint">
    
        <SCRIPT>
        <xsl:text><![CDATA[securityconstraint = addItem(domain,"images/onepix.gif","Security Constraints","");]]></xsl:text>
        </SCRIPT>

        <xsl:apply-templates select="security-constraint"/>

    </xsl:if>
    
 
    <xsl:if test="login-config">
    
        <SCRIPT>
        <xsl:text><![CDATA[loginconfig = addItem(domain,"images/onepix.gif","Login Configuration","");]]></xsl:text>
        </SCRIPT>
        <xsl:apply-templates select="login-config"/>

    </xsl:if>

                         
  
  
  
  
        <xsl:if test="env-entry">
        <SCRIPT>
        <xsl:text><![CDATA[enventry = addItem(domain,"images/onepix.gif","Environment Entries","");]]></xsl:text>
        </SCRIPT>
        <xsl:apply-templates select="env-entry"><xsl:sort select="env-entry-name"/></xsl:apply-templates>
        </xsl:if>
  
  

        <xsl:if test="security-role">                  
        <SCRIPT>        
        <xsl:text><![CDATA[securityrole = addItem(domain,"images/onepix.gif","Security Roles","");]]></xsl:text>    
        </SCRIPT> 
        <xsl:apply-templates select="security-role"><xsl:sort select="role-name"/></xsl:apply-templates>
        </xsl:if> 
  
        <xsl:if test="resource-ref">
        <SCRIPT>
        <xsl:text><![CDATA[resourceref = addItem(domain,"images/onepix.gif","Resource References","");]]></xsl:text>
        </SCRIPT>
       
        <xsl:apply-templates select="resource-ref"><xsl:sort select="res-ref-name"/></xsl:apply-templates>
        </xsl:if>                       
  

        <xsl:if test="ejb-ref">
        <SCRIPT>
        <xsl:text><![CDATA[ejbref = addItem(domain,"images/onepix.gif","EJB references","");]]></xsl:text>
        </SCRIPT>

        <xsl:apply-templates select="ejb-ref"><xsl:sort select="ejb-ref-name"/></xsl:apply-templates>
        </xsl:if>
                   


 


        <SCRIPT>
        <xsl:text><![CDATA[
        
                setShowExpanders(true);
                setExpandDepth(1);
        
                initialize();       
        ]]></xsl:text>
        </SCRIPT>

           
        </xsl:otherwise>
    </xsl:choose>
</xsl:template>  








  
<xsl:template match="context-param">

        <SCRIPT>
        <xsl:text><![CDATA[contextparamPN = addItem(contextparam,"images/onepix.gif","Context parameter name: ]]></xsl:text><b><xsl:value-of select="param-name"/></b><xsl:text><![CDATA[","");]]></xsl:text>
        <xsl:text><![CDATA[contextparamPV = addItem(contextparamPN,"images/onepix.gif","Value: ]]></xsl:text><b><xsl:call-template name='illegalChar-replace'><xsl:with-param name='text'><xsl:value-of select="param-value" disable-output-escaping='yes'/></xsl:with-param></xsl:call-template></b><xsl:text><![CDATA[","");]]></xsl:text>
        <xsl:if test="description"><xsl:text><![CDATA[contextparamV = addItem(contextparamPN,"images/onepix.gif","Description: ]]></xsl:text><b><xsl:call-template name='illegalChar-replace'><xsl:with-param name='text'><xsl:value-of select='description' disable-output-escaping='yes'/></xsl:with-param></xsl:call-template></b><xsl:text><![CDATA[","");]]></xsl:text></xsl:if>
        </SCRIPT>


</xsl:template>


<xsl:template match="servlet">

        <SCRIPT>
        <xsl:if test="servlet-class">
                <xsl:text><![CDATA[servletN = addItem(servlet,"images/onepix.gif","Servlet name: ]]></xsl:text><b><xsl:value-of select="servlet-name"/></b><xsl:text><![CDATA[","");]]></xsl:text>
                <xsl:text><![CDATA[servletC = addItem(servletN,"images/onepix.gif","Class: ]]></xsl:text><b><xsl:value-of select="servlet-class"/></b><xsl:text><![CDATA[","");]]></xsl:text>
        </xsl:if>
        <xsl:if test="jsp-file">
                <xsl:text><![CDATA[servletN = addItem(servlet,"images/onepix.gif","JSP name: ]]></xsl:text><b><xsl:value-of select="servlet-name"/></b><xsl:text><![CDATA[","");]]></xsl:text>     
                <xsl:text><![CDATA[servletJ = addItem(servletN,"images/onepix.gif","JSP file: ]]></xsl:text><b><xsl:value-of select="jsp-file"/></b><xsl:text><![CDATA[","");]]></xsl:text>
        </xsl:if>
        <xsl:if test="display-name"><xsl:text><![CDATA[servletDN = addItem(servletN,"images/onepix.gif","Display name: ]]></xsl:text><b><xsl:value-of select='display-name' disable-output-escaping='yes'/></b><xsl:text><![CDATA[","");]]></xsl:text></xsl:if>                           
        <xsl:if test="description"><xsl:text><![CDATA[servletD = addItem(servletN,"images/onepix.gif","Description: ]]></xsl:text><b><xsl:call-template name='illegalChar-replace'><xsl:with-param name='text'><xsl:value-of select='description' disable-output-escaping='yes'/></xsl:with-param></xsl:call-template></b><xsl:text><![CDATA[","");]]></xsl:text></xsl:if>
        <xsl:if test="load-on-startup"><xsl:text><![CDATA[servletLOS = addItem(servletN,"images/onepix.gif","Load-on-startup: ]]></xsl:text><b><xsl:value-of select="load-on-startup"/></b><xsl:text><![CDATA[","");]]></xsl:text></xsl:if>
        
        </SCRIPT>
             
        

        <xsl:if test="init-param">
        <SCRIPT>
        <xsl:text><![CDATA[initparam = addItem(servletN,"images/onepix.gif","Init Parameters","");]]></xsl:text>
        </SCRIPT>
        <xsl:apply-templates select="init-param"><xsl:sort select="param-name"/></xsl:apply-templates>
        </xsl:if>

        <xsl:if test="run-as">
        <SCRIPT>
        <xsl:text><![CDATA[runas = addItem(servletN,"images/onepix.gif","Run as","");]]></xsl:text>
        </SCRIPT>
       
                <xsl:apply-templates select="run-as"><xsl:sort select="role-name"/></xsl:apply-templates>
        </xsl:if>
        
        <xsl:if test="security-role-ref">
        <SCRIPT>
        <xsl:text><![CDATA[securityroleref = addItem(servletN,"images/onepix.gif","Security Role Reference","");]]></xsl:text>
        </SCRIPT>
        <xsl:apply-templates select="security-role-ref"><xsl:sort select="role-name"/></xsl:apply-templates>
        </xsl:if>

      
    
  </xsl:template>
  



<xsl:template match="filter">

        <SCRIPT>
        <xsl:text><![CDATA[item]]></xsl:text><xsl:value-of select="filter-name"/><xsl:text><![CDATA[ = addItem(filter,"images/onepix.gif","Filter name: ]]></xsl:text><b><xsl:value-of select="filter-name"/></b><xsl:text><![CDATA[","");]]></xsl:text>
        <xsl:if test="display-name"><xsl:text><![CDATA[filterDN = addItem(item]]></xsl:text><xsl:value-of select="filter-name"/><xsl:text><![CDATA[,"images/onepix.gif","Display name: ]]></xsl:text><b><xsl:value-of select='display-name'/></b><xsl:text><![CDATA[","");]]></xsl:text></xsl:if>        
        <xsl:text><![CDATA[filterC = addItem(item]]></xsl:text><xsl:value-of select="filter-name"/><xsl:text><![CDATA[,"images/onepix.gif","Filter class: ]]></xsl:text><b><xsl:value-of select="filter-class"/></b><xsl:text><![CDATA[","");]]></xsl:text>        
        <xsl:if test="description"><xsl:text><![CDATA[filterD = addItem(item]]></xsl:text><xsl:value-of select="filter-name"/><xsl:text><![CDATA[,"images/onepix.gif","Description: ]]></xsl:text><b><xsl:call-template name='illegalChar-replace'><xsl:with-param name='text'><xsl:value-of select='description' disable-output-escaping='yes'/></xsl:with-param></xsl:call-template></b><xsl:text><![CDATA[","");]]></xsl:text></xsl:if>
        </SCRIPT>
        

                     
    
        
        <xsl:if test="init-param">
        <SCRIPT>
        <xsl:text><![CDATA[initparam = addItem(item]]></xsl:text><xsl:value-of select="filter-name"/><xsl:text><![CDATA[,"images/onepix.gif","Init Parameters","");]]></xsl:text>
        </SCRIPT>

        <xsl:apply-templates select="init-param"><xsl:sort select="param-name"/></xsl:apply-templates>
        </xsl:if>


      
    
  </xsl:template>
  



<xsl:template match="filter-mapping">


                                                                                                 
        <SCRIPT> 
               <xsl:if test="filter-name"><xsl:text><![CDATA[filtermapN = addItem(filtermap,"images/onepix.gif","Filter name: ]]></xsl:text><b><xsl:value-of select="filter-name"/></b><xsl:text><![CDATA[","");]]></xsl:text></xsl:if>                       
               <xsl:if test="url-pattern"><xsl:text><![CDATA[filtermapUP = addItem(filtermapN,"images/onepix.gif","Url pattern: ]]></xsl:text><b><xsl:call-template name='illegalChar-replace'><xsl:with-param name='text'><xsl:value-of select="url-pattern" disable-output-escaping='yes'/></xsl:with-param></xsl:call-template></b><xsl:text><![CDATA[","");]]></xsl:text></xsl:if>
               <xsl:if test="servlet-name"><xsl:text><![CDATA[filtermapSN = addItem(filtermapN,"images/onepix.gif","Servlet name: ]]></xsl:text><b><xsl:value-of select="servlet-name"/></b><xsl:text><![CDATA[","");]]></xsl:text></xsl:if>

        </SCRIPT> 
                
        
                      
                       

</xsl:template>

<xsl:template match="init-param">
      	
        <SCRIPT>
        <xsl:text><![CDATA[initparamPN = addItem(initparam,"images/onepix.gif","Param name: ]]></xsl:text><b><xsl:value-of select="param-name"/></b><xsl:text><![CDATA[","");]]></xsl:text>
        <xsl:text><![CDATA[initparamPV = addItem(initparamPN,"images/onepix.gif","Value: ]]></xsl:text><b><xsl:call-template name='illegalChar-replace'><xsl:with-param name='text'><xsl:value-of select="param-value" disable-output-escaping='yes'/></xsl:with-param></xsl:call-template></b><xsl:text><![CDATA[","");]]></xsl:text>
        <xsl:if test="description"><xsl:text><![CDATA[initparamD = addItem(initparamPN,"images/onepix.gif","Description: ]]></xsl:text><b><xsl:call-template name='illegalChar-replace'><xsl:with-param name='text'><xsl:value-of select='description' disable-output-escaping='yes'/></xsl:with-param></xsl:call-template></b><xsl:text><![CDATA[","");]]></xsl:text></xsl:if>
                
        </SCRIPT>

                                      
  
</xsl:template>


<xsl:template match="listener">
      	
        <SCRIPT>
        <xsl:text><![CDATA[listenerC = addItem(listener,"images/onepix.gif","Listener class: ]]></xsl:text><b><xsl:value-of select="listener-class"/></b><xsl:text><![CDATA[","");]]></xsl:text>
               
        </SCRIPT>
                                      
  
</xsl:template>



<xsl:template match="run-as">
      	
        <SCRIPT>
        <xsl:text><![CDATA[runasRN = addItem(runas,"images/onepix.gif","Role name: ]]></xsl:text><b><xsl:value-of select="role-name"/></b><xsl:text><![CDATA[","");]]></xsl:text>
        <xsl:if test="description"><xsl:text><![CDATA[runasD = addItem(runasRN,"images/onepix.gif","Description: ]]></xsl:text><b><xsl:call-template name='illegalChar-replace'><xsl:with-param name='text'><xsl:value-of select='description' disable-output-escaping='yes'/></xsl:with-param></xsl:call-template></b><xsl:text><![CDATA[","");]]></xsl:text></xsl:if>
                
        </SCRIPT>

                                      
  
</xsl:template>

<xsl:template match="security-role-ref">
        <SCRIPT>
        <xsl:text><![CDATA[securityrolerefRN = addItem(securityroleref,"images/onepix.gif","Role name: ]]></xsl:text><b><xsl:value-of select="role-name"/></b><xsl:text><![CDATA[","");]]></xsl:text>
        <xsl:text><![CDATA[securityrolerefRL = addItem(securityrolerefRN,"images/onepix.gif","Role link: ]]></xsl:text><b><xsl:call-template name='illegalChar-replace'><xsl:with-param name='text'><xsl:value-of select="role-link" disable-output-escaping='yes'/></xsl:with-param></xsl:call-template></b><xsl:text><![CDATA[","");]]></xsl:text>        
        <xsl:if test="description"><xsl:text><![CDATA[securityrolerefD = addItem(securityrolerefRN,"images/onepix.gif","Description: ]]></xsl:text><b><xsl:call-template name='illegalChar-replace'><xsl:with-param name='text'><xsl:value-of select='description' disable-output-escaping='yes'/></xsl:with-param></xsl:call-template></b><xsl:text><![CDATA[","");]]></xsl:text></xsl:if>
        
        </SCRIPT>
         

</xsl:template>




<xsl:template match="servlet-mapping">
  
          

        <SCRIPT>
        <xsl:text><![CDATA[servletmapSN = addItem(servletmap,"images/onepix.gif","Servlet name: ]]></xsl:text><b><xsl:value-of select="servlet-name"/></b><xsl:text><![CDATA[","");]]></xsl:text>
        <xsl:text><![CDATA[servletmapUP = addItem(servletmapSN,"images/onepix.gif","Url pattern: ]]></xsl:text><b><xsl:call-template name='illegalChar-replace'><xsl:with-param name='text'><xsl:value-of select="url-pattern" disable-output-escaping='yes'/></xsl:with-param></xsl:call-template></b><xsl:text><![CDATA[","");]]></xsl:text>
 
        </SCRIPT>
                 
     
        
   
</xsl:template>



<xsl:template match="session-config">

          
        <SCRIPT>
        <xsl:text><![CDATA[sessionconfigST = addItem(sessionconfig,"images/onepix.gif","Session timeout: ]]></xsl:text><b><xsl:value-of select="session-timeout"/></b><xsl:text><![CDATA[","");]]></xsl:text>
        </SCRIPT>
                     
   
</xsl:template>


<xsl:template match="mime-mapping">
     
          


        <SCRIPT>
        <xsl:text><![CDATA[mimemapE = addItem(mimemap,"images/onepix.gif","Extension: ]]></xsl:text><b><xsl:value-of select="extension"/></b><xsl:text><![CDATA[, Mime Type: ]]></xsl:text><b><xsl:value-of select="mime-type"/></b><xsl:text><![CDATA[","");]]></xsl:text>
        </SCRIPT>
                                 
</xsl:template>


<xsl:template match="welcome-file-list">
          

          
        <SCRIPT>
        <xsl:text><![CDATA[welcomefileN = addItem(welcomefile,"images/onepix.gif","Welcome file: ]]></xsl:text><b><xsl:call-template name='illegalChar-replace'><xsl:with-param name='text'><xsl:value-of select="welcome-file" disable-output-escaping='yes'/></xsl:with-param></xsl:call-template></b><xsl:text><![CDATA[","");]]></xsl:text>
        </SCRIPT>
         


</xsl:template>

<xsl:template match="error-page">
            
          

        <SCRIPT>
        <xsl:text><![CDATA[errorpageL = addItem(errorpage,"images/onepix.gif","Location: ]]></xsl:text><b><xsl:call-template name='illegalChar-replace'><xsl:with-param name='text'><xsl:value-of select="location" disable-output-escaping='yes'/></xsl:with-param></xsl:call-template></b><xsl:text><![CDATA[","");]]></xsl:text>
        <xsl:if test="error-code"><xsl:text><![CDATA[errorpageEC = addItem(errorpageL,"images/onepix.gif","Error code: ]]></xsl:text><b><xsl:value-of select="error-code"/></b><xsl:text><![CDATA[","");]]></xsl:text></xsl:if>
        <xsl:if test="exception-type"><xsl:text><![CDATA[errorpageET = addItem(errorpageL,"images/onepix.gif","Exception type: ]]></xsl:text><b><xsl:value-of select="exception-type"/></b><xsl:text><![CDATA[","");]]></xsl:text></xsl:if>
 
        </SCRIPT>
           
        
   

</xsl:template>


<xsl:template match="taglib">

          
        <SCRIPT>
        <xsl:text><![CDATA[taglibU = addItem(taglib,"images/onepix.gif","Taglib uri: ]]></xsl:text><b><xsl:call-template name='illegalChar-replace'><xsl:with-param name='text'><xsl:value-of select="taglib-uri" disable-output-escaping='yes'/></xsl:with-param></xsl:call-template></b><xsl:text><![CDATA[","");]]></xsl:text>
        <xsl:text><![CDATA[taglibL = addItem(taglibU,"images/onepix.gif","Taglib location: ]]></xsl:text><b><xsl:call-template name='illegalChar-replace'><xsl:with-param name='text'><xsl:value-of select="taglib-location" disable-output-escaping='yes'/></xsl:with-param></xsl:call-template></b><xsl:text><![CDATA[","");]]></xsl:text>
 
        </SCRIPT>
       
             
</xsl:template>



<xsl:template match="resource-ref">
        <SCRIPT>
        <xsl:text><![CDATA[resourcerefN = addItem(resourceref,"images/onepix.gif","Resource ref name: ]]></xsl:text><b><xsl:call-template name='illegalChar-replace'><xsl:with-param name='text'><xsl:value-of select="res-ref-name" disable-output-escaping='yes'/></xsl:with-param></xsl:call-template></b><xsl:text><![CDATA[","");]]></xsl:text>
        <xsl:text><![CDATA[resourcerefT = addItem(resourcerefN,"images/onepix.gif","Resource type: ]]></xsl:text><b><xsl:value-of select="res-type"/></b><xsl:text><![CDATA[","");]]></xsl:text>
        <xsl:text><![CDATA[resourcerefA = addItem(resourcerefN,"images/onepix.gif","Resource auth: ]]></xsl:text><b><xsl:value-of select="res-auth"/></b><xsl:text><![CDATA[","");]]></xsl:text>
        <xsl:if test="description"><xsl:text><![CDATA[resourcerefD = addItem(resourcerefN,"images/onepix.gif","Description: ]]></xsl:text><b><xsl:call-template name='illegalChar-replace'><xsl:with-param name='text'><xsl:value-of select='description' disable-output-escaping='yes'/></xsl:with-param></xsl:call-template></b><xsl:text><![CDATA[",""); ]]></xsl:text></xsl:if>

        </SCRIPT>
        </xsl:template>


<xsl:template match="security-constraint">           
        

        <SCRIPT>
        <xsl:text><![CDATA[securityconstraintWRC = addItem(securityconstraint,"images/onepix.gif","Web Resource Collection: ]]></xsl:text><b><xsl:value-of select="web-resource-collection/web-resource-name"/></b><xsl:text><![CDATA[","");]]></xsl:text>
        </SCRIPT>
        <xsl:apply-templates select="web-resource-collection"/>

        <xsl:if test="auth-constraint">
        <SCRIPT>
        <xsl:text><![CDATA[authconstraint = addItem(securityconstraint,"images/onepix.gif","Authorization constraint","");]]></xsl:text>
        </SCRIPT>
        <xsl:apply-templates select="auth-constraint"><xsl:sort select="role-name"/></xsl:apply-templates>
        </xsl:if>
        
        <xsl:if test="user-data-constraint">
        <SCRIPT>
        <xsl:text><![CDATA[userdataconstraint = addItem(securityconstraint,"images/onepix.gif","User data constraint","");]]></xsl:text>
        </SCRIPT>
        <xsl:apply-templates select="user-data-constraint"/>
        </xsl:if>


                             
</xsl:template>

<xsl:template match="web-resource-collection">


        <SCRIPT>
        <xsl:text><![CDATA[securityconstraintWRCUP = addItem(securityconstraintWRC,"images/onepix.gif","Url pattern: ]]></xsl:text><b><xsl:call-template name='illegalChar-replace'><xsl:with-param name='text'><xsl:value-of select="url-pattern" disable-output-escaping='yes'/></xsl:with-param></xsl:call-template></b><xsl:text><![CDATA[","");]]></xsl:text>
        </SCRIPT>

                
        <xsl:if test="http-method">
        <SCRIPT>
        <xsl:text><![CDATA[httpmethod = addItem(securityconstraintWRC,"images/onepix.gif","HTTP methods","");]]></xsl:text>
        </SCRIPT>
        <xsl:apply-templates select="http-method"/>
        </xsl:if>



                
</xsl:template>   

                            

<xsl:template match="http-method">

        <SCRIPT>
        <xsl:text><![CDATA[httpmethodN = addItem(httpmethod,"images/onepix.gif","HTTP method: ]]></xsl:text><b><xsl:value-of select="."/></b><xsl:text><![CDATA[","");]]></xsl:text>
        
        </SCRIPT>
 
</xsl:template>                               

<xsl:template match="auth-constraint">
	<SCRIPT>
        <xsl:if test="description"><xsl:text><![CDATA[rolenameD = addItem(authconstraint,"images/onepix.gif","Description: ]]></xsl:text><b><xsl:call-template name='illegalChar-replace'><xsl:with-param name='text'><xsl:value-of select='description' disable-output-escaping='yes'/></xsl:with-param></xsl:call-template></b><xsl:text><![CDATA[","");]]></xsl:text></xsl:if>
        <xsl:text><![CDATA[rolename = addItem(authconstraint,"images/onepix.gif","Role Names:","");]]></xsl:text>
        </SCRIPT>
        <xsl:apply-templates select="role-name"><xsl:sort select="role-name"/></xsl:apply-templates>
</xsl:template>

<xsl:template match="role-name">
        <SCRIPT>
        <xsl:text><![CDATA[rolenameRN = addItem(rolename,"images/onepix.gif","Role name: ]]></xsl:text><b><xsl:value-of select="."/></b><xsl:text><![CDATA[","");]]></xsl:text>
        </SCRIPT>
</xsl:template> 

<xsl:template match="user-data-constraint">        
          

        <SCRIPT>
        <xsl:text><![CDATA[userdataconstraintTG = addItem(userdataconstraint,"images/onepix.gif","Transport guarantee: ]]></xsl:text><b><xsl:value-of select="transport-guarantee"/></b><xsl:text><![CDATA[","");]]></xsl:text>
        <xsl:if test="description"><xsl:text><![CDATA[userdataconstraintD = addItem(userdataconstraintTG,"images/onepix.gif","Description: ]]></xsl:text><b><xsl:call-template name='illegalChar-replace'><xsl:with-param name='text'><xsl:value-of select='description' disable-output-escaping='yes'/></xsl:with-param></xsl:call-template></b><xsl:text><![CDATA[","");]]></xsl:text></xsl:if>
        
        </SCRIPT>

      
</xsl:template> 

<xsl:template match="login-config">
        

        <SCRIPT>
        <xsl:text><![CDATA[loginconfigAM = addItem(loginconfig,"images/onepix.gif","Auth method: ]]></xsl:text><b><xsl:value-of select="auth-method"/></b><xsl:text><![CDATA[","");]]></xsl:text>
        <xsl:text><![CDATA[loginconfigRN = addItem(loginconfig,"images/onepix.gif","Realm name: ]]></xsl:text><b><xsl:value-of select="realm-name"/></b><xsl:text><![CDATA[","");]]></xsl:text>
        
        </SCRIPT>


        
        <xsl:if test="form-login-config">
        <SCRIPT>
        <xsl:text><![CDATA[formloginconfig = addItem(loginconfig,"images/onepix.gif","Form login config","");]]></xsl:text>
        </SCRIPT>
        <xsl:apply-templates select="form-login-config"/>
        </xsl:if>

</xsl:template>                   

<xsl:template match="form-login-config">
            

        <SCRIPT>
        <xsl:text><![CDATA[formloginconfigLP = addItem(formloginconfig,"images/onepix.gif","Form login page: ]]></xsl:text><b><xsl:value-of select="form-login-page"/></b><xsl:text><![CDATA[","");]]></xsl:text>
        <xsl:text><![CDATA[formloginconfigEP = addItem(formloginconfig,"images/onepix.gif","Form error page: ]]></xsl:text><b><xsl:value-of select="form-error-page"/></b><xsl:text><![CDATA[","");]]></xsl:text>
        
        </SCRIPT>
      
              
</xsl:template>         





<xsl:template match="security-role">

        <SCRIPT>
        <xsl:text><![CDATA[securityroleRN = addItem(securityrole,"images/onepix.gif","Role name: ]]></xsl:text><b><xsl:value-of select="role-name"/></b><xsl:text><![CDATA[","");]]></xsl:text>
        <xsl:if test="description"><xsl:text><![CDATA[securityroleD = addItem(securityroleRN,"images/onepix.gif","Description: ]]></xsl:text><b><xsl:call-template name='illegalChar-replace'><xsl:with-param name='text'><xsl:value-of select='description' disable-output-escaping='yes'/></xsl:with-param></xsl:call-template></b><xsl:text><![CDATA[","");]]></xsl:text></xsl:if>
        
        </SCRIPT>
        

                     
</xsl:template>                   

<xsl:template match="env-entry">
        <SCRIPT>
        <xsl:text><![CDATA[enventryN = addItem(enventry,"images/onepix.gif","Env entry name: ]]></xsl:text><b><xsl:value-of select="env-entry-name"/></b><xsl:text><![CDATA[","");]]></xsl:text>
        <xsl:text><![CDATA[enventryT = addItem(enventryN,"images/onepix.gif","Type: ]]></xsl:text><b><xsl:value-of select="env-entry-type"/></b><xsl:text><![CDATA[","");]]></xsl:text>
        <xsl:text><![CDATA[enventryV = addItem(enventryN,"images/onepix.gif","Value: ]]></xsl:text><b><xsl:call-template name='illegalChar-replace'><xsl:with-param name='text'><xsl:value-of select="env-entry-value" disable-output-escaping='yes'/></xsl:with-param></xsl:call-template></b><xsl:text><![CDATA[","");]]></xsl:text>
        </SCRIPT>
      

</xsl:template>
                 

<xsl:template match="ejb-ref">

        <SCRIPT>
        <xsl:text><![CDATA[ejbrefN = addItem(ejbref,"images/onepix.gif","EJB ref name: ]]></xsl:text><b><xsl:value-of select="ejb-ref-name"/></b><xsl:text><![CDATA[","");]]></xsl:text>
        <xsl:text><![CDATA[ejbrefT = addItem(ejbrefN,"images/onepix.gif","Type: ]]></xsl:text><b><xsl:value-of select="ejb-ref-type"/></b><xsl:text><![CDATA[","");]]></xsl:text>        
        <xsl:text><![CDATA[ejbrefH = addItem(ejbrefN,"images/onepix.gif","Home: ]]></xsl:text><b><xsl:value-of select="home"/></b><xsl:text><![CDATA[","");]]></xsl:text>
        <xsl:text><![CDATA[ejbrefR = addItem(ejbrefN,"images/onepix.gif","Remote: ]]></xsl:text><b><xsl:value-of select="remote"/></b><xsl:text><![CDATA[","");]]></xsl:text>
        <xsl:text><![CDATA[ejbrefL = addItem(ejbrefN,"images/onepix.gif","EJB link: ]]></xsl:text><b><xsl:call-template name='illegalChar-replace'><xsl:with-param name='text'><xsl:value-of select="ejb-link" disable-output-escaping='yes'/></xsl:with-param></xsl:call-template></b><xsl:text><![CDATA[","");]]></xsl:text>
        </SCRIPT>

</xsl:template>    




  <xsl:template name="br-replace">
    <xsl:param name="text"/>
    <xsl:variable name="cr" select="'&#xa;'"/>
    <xsl:choose>
      <!-- If the value of the $text parameter contains carriage ret -->
      <xsl:when test="contains($text,$cr)">
        <!-- Return the substring of $text before the carriage return -->
        <xsl:value-of select="substring-before($text,$cr)"/>
        <!-- And construct a <br/> element -->
        <!--<br/>-->
        <!--
         | Then invoke this same br-replace template again, passing the
         | substring *after* the carriage return as the new "$text" to
         | consider for replacement
         +-->
        <xsl:call-template name="br-replace">
          <xsl:with-param name="text" select="substring-after($text,$cr)"/>
        </xsl:call-template>
      </xsl:when>      
      <xsl:otherwise>
        <xsl:value-of select="$text"/>
      </xsl:otherwise>
   </xsl:choose>
  </xsl:template>
  


  <xsl:template name="quote-replace">
    <xsl:param name="text"/>
    <xsl:variable name="qo" select="'&quot;'"/>
    <xsl:choose>
      <!-- If the value of the $text parameter contains carriage ret -->
      <xsl:when test="contains($text,$qo)">
        <!-- Return the substring of $text before the carriage return -->
        <xsl:value-of select="substring-before($text,$qo)"/>
        <!-- And construct a <br/> element -->
        <code>\"</code>
        <!--
         | Then invoke this same br-replace template again, passing the
         | substring *after* the carriage return as the new "$text" to
         | consider for replacement
         +-->
        <xsl:call-template name="quote-replace">
          <xsl:with-param name="text" select="substring-after($text,$qo)"/>
        </xsl:call-template>
      </xsl:when>      
      <xsl:otherwise>
        <xsl:value-of select="$text"/>
      </xsl:otherwise>
   </xsl:choose>
  </xsl:template>

  

  <xsl:template name="leftcurly-replace">
    <xsl:param name="text"/>
    <xsl:variable name="qo" select="'&#123;'"/>
    <xsl:choose>
      <!-- If the value of the $text parameter contains carriage ret -->
      <xsl:when test="contains($text,$qo)">
        <!-- Return the substring of $text before the carriage return -->
        <xsl:value-of select="substring-before($text,$qo)"/>
        <!-- And construct a <br/> element -->
        <code>{</code>
        <!--
         | Then invoke this same br-replace template again, passing the
         | substring *after* the carriage return as the new "$text" to
         | consider for replacement
         +-->
        <xsl:call-template name="leftcurly-replace">
          <xsl:with-param name="text" select="substring-after($text,$qo)"/>
        </xsl:call-template>
      </xsl:when>      
      <xsl:otherwise>
        <xsl:value-of select="$text"/>
      </xsl:otherwise>
   </xsl:choose>
  </xsl:template>
  
    <xsl:template name="rightcurly-replace">
    <xsl:param name="text"/>
    <xsl:variable name="qo" select="'&#125;'"/>
    <xsl:choose>
      <!-- If the value of the $text parameter contains carriage ret -->
      <xsl:when test="contains($text,$qo)">
        <!-- Return the substring of $text before the carriage return -->
        <xsl:value-of select="substring-before($text,$qo)"/>
        <!-- And construct a <br/> element -->
        <code>}</code>
        <!--
         | Then invoke this same br-replace template again, passing the
         | substring *after* the carriage return as the new "$text" to
         | consider for replacement
         +-->
        <xsl:call-template name="rightcurly-replace">
          <xsl:with-param name="text" select="substring-after($text,$qo)"/>
        </xsl:call-template>
      </xsl:when>      
      <xsl:otherwise>
        <xsl:value-of select="$text"/>
      </xsl:otherwise>
   </xsl:choose>
  </xsl:template>


  <xsl:template name="illegalChar-replace">
    <xsl:param name="text"/>
    <xsl:variable name="ltCurly" select="'&#123;'"/>
    <xsl:variable name="rtCurly" select="'&#125;'"/>    
    <xsl:variable name="quote" select="'&quot;'"/>
    <xsl:variable name="break" select="'&#xa;'"/>
    <xsl:variable name="backSlash" select="'&#92;'"/>

    <!--<xsl:variable name="backSlash" select="'\'"/>-->

    <xsl:choose>
      <xsl:when test="contains($text,$quote)">
        <xsl:value-of select="substring-before($text,$quote)"/>
        <code>\"</code>
        <xsl:call-template name="illegalChar-replace">
          <xsl:with-param name="text" select="substring-after($text,$quote)"/>
        </xsl:call-template>
      </xsl:when>      
      <xsl:otherwise>

            <xsl:choose>
              <xsl:when test="contains($text,$break)">
                <xsl:value-of select="substring-before($text,$break)"/>
                <!-- replace with nothing -->
                <xsl:call-template name="illegalChar-replace">
                  <xsl:with-param name="text" select="substring-after($text,$break)"/>
                </xsl:call-template>
              </xsl:when>      
              <xsl:otherwise>
                
                    <xsl:choose>
                      <xsl:when test="contains($text,$ltCurly)">
                        <xsl:value-of select="substring-before($text,$ltCurly)"/>
                        <code>{</code>                        
                        <xsl:call-template name="illegalChar-replace">
                          <xsl:with-param name="text" select="substring-after($text,$ltCurly)"/>
                        </xsl:call-template>
                      </xsl:when>      
                      <xsl:otherwise>
                      
                            <xsl:choose>
                              <xsl:when test="contains($text,$rtCurly)">
                                <xsl:value-of select="substring-before($text,$rtCurly)"/>
                                <code>}</code>                        
                                <xsl:call-template name="illegalChar-replace">
                                  <xsl:with-param name="text" select="substring-after($text,$rtCurly)"/>
                                </xsl:call-template>
                              </xsl:when>      
                              <xsl:otherwise>
                              
                                    <xsl:choose>
                                      <xsl:when test="contains($text,$backSlash)">
                                        <xsl:value-of select="substring-before($text,$backSlash)"/>
                                        <code>\\</code>                        
                                        <xsl:call-template name="illegalChar-replace">
                                          <xsl:with-param name="text" select="substring-after($text,$backSlash)"/>
                                        </xsl:call-template>
                                      </xsl:when>      
                                      <xsl:otherwise>
                                      
                                        <xsl:value-of select="$text"/>
                                      
                                      </xsl:otherwise>                              
                                   </xsl:choose>
                              
                              </xsl:otherwise>                              
                           </xsl:choose>


                      </xsl:otherwise>
                   </xsl:choose>
                
                  

              </xsl:otherwise>
           </xsl:choose>
           

               
      </xsl:otherwise>
   </xsl:choose>
   

  </xsl:template>



  <xsl:template name="replace-string">
    <xsl:param name="text"/>
    <xsl:param name="replace"/>
    <xsl:param name="with"/>
    <xsl:choose>
      <xsl:when test="contains($text,$replace)">
        <xsl:value-of select="substring-before($text,$replace)"/>
        <xsl:value-of select="$with"/>
        <xsl:call-template name="replace-string">
          <xsl:with-param name="text" select="substring-after($text,$replace)"/>
          <xsl:with-param name="replace" select="$replace"/>
          <xsl:with-param name="with" select="$with"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="$text"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>




 
           
</xsl:stylesheet>

