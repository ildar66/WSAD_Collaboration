<?xml version='1.0'?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"> 

<!-- IBM Confidential OCO Source Material -->
<!-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 1997, 2003 -->
<!-- The source code for this program is not published or otherwise divested -->
<!-- of its trade secrets, irrespective of what has been deposited with the -->
<!-- U.S. Copyright Office. -->

<xsl:output method="html" indent="yes"/>


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
                        .goback { margin-left: 0px; margin-bottom: 5px; font-size: 100%; font-weight:bold; color: #0000FF; font-family: Arial,Helvetica, sans-serif; }

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
        <BODY >
        
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

                <xsl:apply-templates select="application"/>



         
      </BODY>
    </HTML>
   </xsl:template>


<xsl:template match="application">


    <xsl:variable name="maxCount">1000</xsl:variable>
    <xsl:choose>
        <xsl:when test="count(//*)>$maxCount">

            <!--  This is the large item number path -->
            <H3>Application.xml
            <xsl:if test="display-name">
              <xsl:text> for "</xsl:text>
              <xsl:value-of select="display-name"/>
              <xsl:text>"</xsl:text>
            </xsl:if>
            </H3>
            <div class="indent2">
                <form>
                    <textarea cols="50" rows="35" WRAP="off">                                                                                         
                    <xsl:copy-of select="/node()"/>
                    </textarea>
                </form>
            </div>
        </xsl:when>

        <!--  This is the small item number path -->
        <xsl:otherwise>

                <NOSCRIPT>
                    <H3>Application.xml
                    <xsl:if test="display-name">
                      <xsl:text> for "</xsl:text>
                      <xsl:value-of select="display-name"/>
                      <xsl:text>"</xsl:text>
                    </xsl:if>
                    </H3>
                    <div class="indent2">
                        <form>
                            <textarea cols="50" rows="35" WRAP="off">                                                                                         
                            <xsl:copy-of select="/node()"/>
                            </textarea>
                        </form>
                    </div>
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
        <xsl:text><![CDATA[domain = addRoot("images/onepix.gif","<b>Application.xml - ]]></xsl:text><xsl:if test="display-name"><xsl:value-of select="display-name"/></xsl:if><xsl:text><![CDATA[</b>","");]]></xsl:text>
        <xsl:if test="description"><xsl:text><![CDATA[domainD = addItem(domain,"images/onepix.gif","Description: ]]></xsl:text><xsl:call-template name='illegalChar-replace'><xsl:with-param name='text'><xsl:value-of select='description' disable-output-escaping='yes'/></xsl:with-param></xsl:call-template><xsl:text><![CDATA[","");]]></xsl:text></xsl:if>
        
        <xsl:if test="icon">
                <xsl:text><![CDATA[icons = addItem(domain,"images/onepix.gif","Icons","");]]></xsl:text>               
                <xsl:if test="icon/small-icon"><xsl:text><![CDATA[icons1 = addItem(icons,"images/onepix.gif","Small icon: ]]></xsl:text><b><xsl:value-of select='icon/small-icon' /></b><xsl:text><![CDATA[","");]]></xsl:text></xsl:if>               
                <xsl:if test="icon/large-icon"><xsl:text><![CDATA[icons2 = addItem(icons,"images/onepix.gif","Large icon: ]]></xsl:text><b><xsl:value-of select='icon/large-icon' /></b><xsl:text><![CDATA[","");]]></xsl:text></xsl:if>
        </xsl:if>

        
        </SCRIPT> 
        
        
          
        
        <xsl:if test="module"> 
                
                <xsl:if test="module/ejb">                
                <SCRIPT>    
                        <xsl:text><![CDATA[ejbmodule = addItem(domain,"images/onepix.gif","EJB Modules","");]]></xsl:text>
                </SCRIPT>
                </xsl:if>
                

                <xsl:if test="module/java">
                <SCRIPT>    
                        <xsl:text><![CDATA[javamodule = addItem(domain,"images/onepix.gif","Java Client Modules","");]]></xsl:text>
                </SCRIPT>
                </xsl:if>
                
                <xsl:if test="module/connector">
                <SCRIPT>    
                        <xsl:text><![CDATA[connectormodule = addItem(domain,"images/onepix.gif","Connector Modules","");]]></xsl:text>
                </SCRIPT>
                </xsl:if>

                <xsl:if test="module/web">
                <SCRIPT>    
                        <xsl:text><![CDATA[webmodule = addItem(domain,"images/onepix.gif","Web Modules","");]]></xsl:text>
                </SCRIPT>
                </xsl:if>
                
                <xsl:apply-templates select="module"><xsl:sort select="web/web-uri|ejb|java|connector"/></xsl:apply-templates>                
                
                                        
        </xsl:if>

        
        <xsl:if test="security-role">  
                
                <SCRIPT>
                <xsl:text><![CDATA[securityrole = addItem(domain,"images/onepix.gif","Security Roles","");]]></xsl:text>
                </SCRIPT>
               
              
                <xsl:apply-templates select="security-role"><xsl:sort select="role-name"/></xsl:apply-templates>
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
  
  
<xsl:template match="module"> 
 
  
                <xsl:if test="web">
                <SCRIPT>
                <xsl:text><![CDATA[webmoduleURI = addItem(webmodule,"images/onepix.gif","Web Module URI: <b>]]></xsl:text><xsl:value-of select="web/web-uri"/><xsl:text><![CDATA[</b>","");]]></xsl:text>
                        <xsl:text><![CDATA[webmoduleCR = addItem(webmoduleURI,"images/onepix.gif","Context-root: <b>]]></xsl:text><xsl:value-of select="web/context-root"/><xsl:text><![CDATA[</b>","");]]></xsl:text>
                        <xsl:if test="alt-dd"><xsl:text><![CDATA[webmoduleadd = addItem(webmoduleURI,"images/onepix.gif","Alt Deployment Descriptor: <b>]]></xsl:text><xsl:call-template name='illegalChar-replace'><xsl:with-param name='text'><xsl:value-of select="alt-dd" disable-output-escaping='yes'/></xsl:with-param></xsl:call-template><xsl:text><![CDATA[</b>","");]]></xsl:text></xsl:if>
                </SCRIPT>
                </xsl:if>


                <xsl:if test="ejb">
                <SCRIPT>
                <xsl:text><![CDATA[ejbmoduleJ = addItem(ejbmodule,"images/onepix.gif","EJB module name: <b>]]></xsl:text><xsl:value-of select="ejb"/><xsl:text><![CDATA[</b>","");]]></xsl:text>                
                        <xsl:if test="alt-dd"><xsl:text><![CDATA[ejbmoduleadd = addItem(ejbmoduleJ,"images/onepix.gif","Alt Deployment Descriptor: <b>]]></xsl:text><xsl:call-template name='illegalChar-replace'><xsl:with-param name='text'><xsl:value-of select="alt-dd" disable-output-escaping='yes'/></xsl:with-param></xsl:call-template><xsl:text><![CDATA[</b>","");]]></xsl:text></xsl:if>
                </SCRIPT>               
                </xsl:if>



                <xsl:if test="java">
                <SCRIPT>
                <xsl:text><![CDATA[javamoduleJ = addItem(javamodule,"images/onepix.gif","Java client module name: <b>]]></xsl:text><xsl:value-of select="java"/><xsl:text><![CDATA[</b>","");]]></xsl:text>                
                <xsl:if test="alt-dd"><xsl:text><![CDATA[javamoduleadd = addItem(javamoduleJ,"images/onepix.gif","Alt Deployment Descriptor: <b>]]></xsl:text><xsl:call-template name='illegalChar-replace'><xsl:with-param name='text'><xsl:value-of select="alt-dd" disable-output-escaping='yes'/></xsl:with-param></xsl:call-template><xsl:text><![CDATA[</b>","");]]></xsl:text></xsl:if>
                </SCRIPT>
                
                 </xsl:if>
                
                
                <xsl:if test="connector">
                <SCRIPT>
                <xsl:text><![CDATA[connmoduleJ = addItem(connectormodule,"images/onepix.gif","Connector module name: <b>]]></xsl:text><xsl:value-of select="connector"/><xsl:text><![CDATA[</b>","");]]></xsl:text>                
                <xsl:if test="alt-dd"><xsl:text><![CDATA[connmoduleadd = addItem(connmoduleJ,"images/onepix.gif","Alt Deployment Descriptor: <b>]]></xsl:text><xsl:call-template name='illegalChar-replace'><xsl:with-param name='text'><xsl:value-of select="alt-dd" disable-output-escaping='yes'/></xsl:with-param></xsl:call-template><xsl:text><![CDATA[</b>","");]]></xsl:text></xsl:if>
                </SCRIPT> 
                             
                </xsl:if>

        
</xsl:template>






<xsl:template match="security-role">

        <SCRIPT>
        <xsl:text><![CDATA[securityroleN = addItem(securityrole,"images/onepix.gif","Role-name: <b>]]></xsl:text><xsl:value-of select="role-name"/><xsl:text><![CDATA[</b>","");]]></xsl:text>
        <xsl:if test="description"><xsl:text><![CDATA[securityroleD = addItem(securityroleN,"images/onepix.gif","Description: <b>]]></xsl:text><xsl:call-template name='illegalChar-replace'><xsl:with-param name='text'><xsl:value-of select='description' disable-output-escaping='yes'/></xsl:with-param></xsl:call-template><xsl:text><![CDATA[</b>","");]]></xsl:text></xsl:if>
        
        </SCRIPT>
        
   
             
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

