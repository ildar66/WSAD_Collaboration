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

            <xsl:apply-templates select="ejb-jar"/>




      </BODY>
    </HTML>
  </xsl:template>




<xsl:template match="ejb-jar">

      <xsl:variable name="maxCount">1000</xsl:variable>
      <xsl:choose>
          <xsl:when test="count(//*)>$maxCount">
    
              <!--  This is the large item number path -->
              <H3>ejb-jar.xml
                <xsl:if test="display-name">
                  <xsl:text> for "</xsl:text>
                  <xsl:value-of select="display-name"/>
                  <xsl:text>"</xsl:text>
                </xsl:if>
              </H3>
              <div class="indent2">
                <form>
                  <textarea cols="70" rows="25" WRAP="off">
                        <xsl:copy-of select="/node()"/>
                  </textarea>
                </form>
              </div>
          </xsl:when>
    
          <!--  This is the small item number path -->
          <xsl:otherwise>

      
    <NOSCRIPT>
      <H3>ejb-jar.xml
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
        




                                          

        

      <xsl:text><![CDATA[domain = addRoot("images/onepix.gif","]]></xsl:text>
      <b>
        <xsl:text><![CDATA[Ejb-jar.xml for \"]]></xsl:text>
        <xsl:if test="display-name">
          <xsl:value-of select="display-name"/>
        </xsl:if>
        <xsl:text><![CDATA[\"]]></xsl:text>
      </b>
      <xsl:text><![CDATA[","");]]></xsl:text>
      <xsl:if test="description">
        <xsl:text><![CDATA[domainD = addItem(domain,"images/onepix.gif","Description: ]]></xsl:text>
        <xsl:call-template name='illegalChar-replace'>
          <xsl:with-param name='text'>
            <xsl:value-of select='description' disable-output-escaping='yes'/>
          </xsl:with-param>
        </xsl:call-template>
        <xsl:text><![CDATA[","");]]></xsl:text>
      </xsl:if>
      <xsl:if test="small-icon">
        <xsl:text><![CDATA[icons1 = addItem(domain,"images/onepix.gif","Small icon: ]]></xsl:text>
        <b>
          <xsl:value-of select='small-icon' />
        </b>
        <xsl:text><![CDATA[","");]]></xsl:text>
      </xsl:if>
      <xsl:if test="large-icon">
        <xsl:text><![CDATA[icons2 = addItem(domain,"images/onepix.gif","Large icon: ]]></xsl:text>
        <b>
          <xsl:value-of select='large-icon' />
        </b>
        <xsl:text><![CDATA[","");]]></xsl:text>
      </xsl:if>
         
                
  
        

      <xsl:if test="enterprise-beans">
        

        <xsl:if test="enterprise-beans/entity">
                    

          <xsl:text><![CDATA[entitybean = addItem(domain,"images/onepix.gif","]]></xsl:text>
          <xsl:text><![CDATA[Entity Beans]]></xsl:text>
          <xsl:text><![CDATA[","");]]></xsl:text>
                

          <xsl:apply-templates select="enterprise-beans/entity">
            <xsl:sort select="enterprise-beans/entity/ejb-name"/>
          </xsl:apply-templates>
        </xsl:if>
        <xsl:if test="enterprise-beans/session">
                    

          <xsl:text><![CDATA[sessionbean = addItem(domain,"images/onepix.gif","]]></xsl:text>
          <xsl:text><![CDATA[Session Beans]]></xsl:text>
          <xsl:text><![CDATA[","");]]></xsl:text>
                

          <xsl:apply-templates select="enterprise-beans/session">
            <xsl:sort select="enterprise-beans/session/ejb-name"/>
          </xsl:apply-templates>
        </xsl:if>
        <xsl:if test="enterprise-beans/message-driven">
                    

          <xsl:text><![CDATA[mdbean = addItem(domain,"images/onepix.gif","]]></xsl:text>
          <xsl:text><![CDATA[Message-driven Beans]]></xsl:text>
          <xsl:text><![CDATA[","");]]></xsl:text>
                
                

          <xsl:apply-templates select="enterprise-beans/message-driven">
            <xsl:sort select="enterprise-beans/message-driven/ejb-name"/>
          </xsl:apply-templates>
        </xsl:if>
      </xsl:if>

               

                                                        
        


      <xsl:if test="assembly-descriptor/*">
                    

        <xsl:text><![CDATA[assembly = addItem(domain,"images/onepix.gif","]]></xsl:text>
        <xsl:text><![CDATA[Assembly Descriptor]]></xsl:text>
        <xsl:text><![CDATA[","");]]></xsl:text>
                
        
              

        <xsl:apply-templates select="assembly-descriptor"/>
                

      </xsl:if>
        


      <xsl:if test="relationships">
                    

        <xsl:text><![CDATA[relationships = addItem(domain,"images/onepix.gif","]]></xsl:text>
        <xsl:text><![CDATA[Relationships]]></xsl:text>
        <xsl:text><![CDATA[","");]]></xsl:text>
        <xsl:if test="relationships/description">
          <xsl:text><![CDATA[relationshipsD = addItem(relationships,"images/onepix.gif","Description: ]]></xsl:text>
          <b>
            <xsl:call-template name='illegalChar-replace'>
              <xsl:with-param name='text'>
                <xsl:value-of select='relationships/description' disable-output-escaping='yes'/>
              </xsl:with-param>
            </xsl:call-template>
          </b>
          <xsl:text><![CDATA[","");]]></xsl:text>
        </xsl:if>
                                        
                
        
              

        <xsl:apply-templates select="ejb-relation"/>
                

      </xsl:if>



     
    

        

      <xsl:text><![CDATA[
        
                setShowExpanders(true);
                setExpandDepth(1);
        
                initialize();       
        ]]></xsl:text>
        


    </SCRIPT>

           
      </xsl:otherwise>
    </xsl:choose>


  </xsl:template>
  
  
  

  <xsl:template match="enterprise-beans/entity">
                
                  
        
                        

    <xsl:text><![CDATA[entitybeanname = addItem(entitybean,"images/onepix.gif","EJB Name: ]]></xsl:text>
    <b>
      <xsl:value-of select="ejb-name"/>
    </b>
    <xsl:text><![CDATA[","");]]>&#13;</xsl:text>
    <xsl:if test="disply-name">
      <xsl:text><![CDATA[entitybeandisp = addItem(entitybeanname,"images/onepix.gif","Display name: ]]></xsl:text>
      <xsl:value-of select="display-name"/>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
    <xsl:if test="description">
      <xsl:text><![CDATA[entitybeandesc = addItem(entitybeanname,"images/onepix.gif","Description: ]]></xsl:text>
      <xsl:call-template name='illegalChar-replace'>
        <xsl:with-param name='text'>
          <xsl:value-of select='description' disable-output-escaping='yes'/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
    <xsl:if test="small-icon">
      <xsl:text><![CDATA[entitybeansi = addItem(entitybeanname,"images/onepix.gif","Small icon: ]]></xsl:text>
      <xsl:value-of select="small-icon"/>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
    <xsl:if test="large-icon">
      <xsl:text><![CDATA[entitybeanli = addItem(entitybeanname,"images/onepix.gif","Large icon: ]]></xsl:text>
      <xsl:value-of select="large-icon"/>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
                        

    <xsl:if test="home">
      <xsl:text><![CDATA[entitybeanhome = addItem(entitybeanname,"images/onepix.gif","Home: ]]></xsl:text>
      <b>
        <xsl:value-of select="home"/>
      </b>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
    <xsl:if test="remote">
      <xsl:text><![CDATA[entitybeanremote = addItem(entitybeanname,"images/onepix.gif","Remote: ]]></xsl:text>
      <b>
        <xsl:value-of select="remote"/>
      </b>
      <xsl:text><![CDATA[","");]]>&#13;</xsl:text>
    </xsl:if>
                        

    <xsl:if test="local-home">
      <xsl:text><![CDATA[entitybeanslh = addItem(entitybeanname,"images/onepix.gif","Local home: ]]></xsl:text>
      <xsl:value-of select="local-home"/>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
    <xsl:if test="local">
      <xsl:text><![CDATA[entitybeanloc = addItem(entitybeanname,"images/onepix.gif","Local: ]]></xsl:text>
      <xsl:value-of select="local"/>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
                        

    <xsl:if test="cmp-version">
      <xsl:text><![CDATA[entitybeancmpv = addItem(entitybeanname,"images/onepix.gif","CMP version: ]]></xsl:text>
      <xsl:value-of select="cmp-version"/>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
    <xsl:if test="abstract-schema-name">
      <xsl:text><![CDATA[entitybeanabsn = addItem(entitybeanname,"images/onepix.gif","Abstract schema name: ]]></xsl:text>
      <xsl:value-of select="abstract-schema-name"/>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
    <xsl:text><![CDATA[entitybeanclass = addItem(entitybeanname,"images/onepix.gif","EJB class: ]]></xsl:text>
    <b>
      <xsl:value-of select="ejb-class"/>
    </b>
    <xsl:text><![CDATA[","");]]>&#13;</xsl:text>
    <xsl:text><![CDATA[entitybeanpersistence = addItem(entitybeanname,"images/onepix.gif","Persistence type: ]]></xsl:text>
    <b>
      <xsl:value-of select="persistence-type"/>
    </b>
    <xsl:text><![CDATA[","");]]></xsl:text>
    <xsl:text><![CDATA[entitybeanprimkey = addItem(entitybeanname,"images/onepix.gif","Prim-key-class: ]]></xsl:text>
    <b>
      <xsl:value-of select="prim-key-class"/>
    </b>
    <xsl:text><![CDATA[","");]]>&#13;</xsl:text>
                        

    <xsl:if test="primkey-field">
      <xsl:text><![CDATA[entitybeanpkf = addItem(entitybeanname,"images/onepix.gif","APrimkey-field: ]]></xsl:text>
      <xsl:value-of select="primkey-field"/>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
    <xsl:text><![CDATA[entitybeanreent = addItem(entitybeanname,"images/onepix.gif","Re-entrant: ]]></xsl:text>
    <b>
      <xsl:value-of select="reentrant"/>
    </b>
    <xsl:text><![CDATA[","");]]>&#13;</xsl:text>
    <!--         <xsl:if test="security-identity"><xsl:text><![CDATA[entitybeansec = addItem(entitybeanname,"images/onepix.gif","Security identity: ]]></xsl:text><b><xsl:value-of select="security-identity"/></b><xsl:text><![CDATA[","");]]>&#13;</xsl:text></xsl:if>        -->

    <xsl:if test="security-identity">
      <xsl:text><![CDATA[securityidentity = addItem(entitybeanname,"images/onepix.gif","Security Identity","");]]></xsl:text>
      <xsl:apply-templates select="security-identity">
        <xsl:sort select="description"/>
      </xsl:apply-templates>
    </xsl:if>



    <xsl:if test="env-entry">
      <xsl:text><![CDATA[env = addItem(entitybeanname,"images/onepix.gif","Environment Entries","");]]></xsl:text>
      <xsl:apply-templates select="env-entry">
        <xsl:sort select="env-entry-name"/>
      </xsl:apply-templates>
    </xsl:if>
    <xsl:if test="ejb-ref">
      <xsl:text><![CDATA[ejbref = addItem(entitybeanname,"images/onepix.gif","EJB References","");]]></xsl:text>
      <xsl:apply-templates select="ejb-ref">
        <xsl:sort select="ejb-ref-name"/>
      </xsl:apply-templates>
    </xsl:if>
    <xsl:if test="ejb-local-ref">
      <xsl:text><![CDATA[ejblocalref = addItem(entitybeanname,"images/onepix.gif","EJB References","");]]></xsl:text>
      <xsl:apply-templates select="ejb-local-ref">
        <xsl:sort select="ejb-ref-name"/>
      </xsl:apply-templates>
    </xsl:if>
                        

    <xsl:if test="security-role-ref">
      <xsl:text><![CDATA[secroleref = addItem(entitybeanname,"images/onepix.gif","Security Role References","");]]></xsl:text>
      <xsl:apply-templates select="security-role-ref">
        <xsl:sort select="role-name"/>
      </xsl:apply-templates>
    </xsl:if>
    <xsl:if test="resource-ref">
      <xsl:text><![CDATA[resref = addItem(entitybeanname,"images/onepix.gif","Resource References","");]]></xsl:text>
      <xsl:apply-templates select="resource-ref">
        <xsl:sort select="res-ref-name"/>
      </xsl:apply-templates>
    </xsl:if>
    <xsl:if test="resource-env-ref">
      <xsl:text><![CDATA[resenvref = addItem(entitybeanname,"images/onepix.gif","Resource Environment References","");]]></xsl:text>
      <xsl:apply-templates select="resource-env-ref">
        <xsl:sort select="resource-env-ref-name"/>
      </xsl:apply-templates>
    </xsl:if>
                        

    <xsl:if test="cmp-field">
      <xsl:text><![CDATA[cmpfield = addItem(entitybeanname,"images/onepix.gif","CMP Fields","");]]></xsl:text>
      <xsl:apply-templates select="cmp-field">
        <xsl:sort select="@id"/>
      </xsl:apply-templates>
    </xsl:if>
                        

    <xsl:if test="query">
      <xsl:text><![CDATA[query = addItem(entitybeanname,"images/onepix.gif","Queries","");]]></xsl:text>
      <xsl:apply-templates select="query">
        <xsl:sort select="role-name"/>
      </xsl:apply-templates>
    </xsl:if>

                       
                        
                        


                        
                       

  </xsl:template>
        



        

  <xsl:template match="enterprise-beans/session">
                



                        

    <xsl:text><![CDATA[sessionbeanname = addItem(sessionbean,"images/onepix.gif","EJB Name: ]]></xsl:text>
    <b>
      <xsl:value-of select="ejb-name"/>
    </b>
    <xsl:text><![CDATA[","");]]>&#13;</xsl:text>
    <xsl:if test="disply-name">
      <xsl:text><![CDATA[sessionbeandisp = addItem(sessionbeanname,"images/onepix.gif","Display name: ]]></xsl:text>
      <xsl:value-of select="display-name"/>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
    <xsl:if test="description">
      <xsl:text><![CDATA[sessionbeandesc = addItem(sessionbeanname,"images/onepix.gif","Description: ]]></xsl:text>
      <xsl:call-template name='illegalChar-replace'>
        <xsl:with-param name='text'>
          <xsl:value-of select='description' disable-output-escaping='yes'/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
    <xsl:if test="small-icon">
      <xsl:text><![CDATA[sessionbeansi = addItem(sessionbeanname,"images/onepix.gif","Small icon: ]]></xsl:text>
      <xsl:value-of select="small-icon"/>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
    <xsl:if test="large-icon">
      <xsl:text><![CDATA[sessionbeanli = addItem(sessionbeanname,"images/onepix.gif","Large icon: ]]></xsl:text>
      <xsl:value-of select="large-icon"/>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
                        

    <xsl:text><![CDATA[sessionbeanhome = addItem(sessionbeanname,"images/onepix.gif","Home: ]]></xsl:text>
    <b>
      <xsl:value-of select="home"/>
    </b>
    <xsl:text><![CDATA[","");]]></xsl:text>
    <xsl:text><![CDATA[sessionbeanremote = addItem(sessionbeanname,"images/onepix.gif","Remote: ]]></xsl:text>
    <b>
      <xsl:value-of select="remote"/>
    </b>
    <xsl:text><![CDATA[","");]]>&#13;</xsl:text>
                         

    <xsl:if test="local-home">
      <xsl:text><![CDATA[sessionbeanslh = addItem(sessionbeanname,"images/onepix.gif","Local home: ]]></xsl:text>
      <xsl:value-of select="local-home"/>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
    <xsl:if test="local">
      <xsl:text><![CDATA[sessionbeanloc = addItem(sessionbeanname,"images/onepix.gif","Local: ]]></xsl:text>
      <xsl:value-of select="local"/>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
                                                

    <xsl:text><![CDATA[sessionbeanclass = addItem(sessionbeanname,"images/onepix.gif","EJB-class: ]]></xsl:text>
    <b>
      <xsl:value-of select="ejb-class"/>
    </b>
    <xsl:text><![CDATA[","");]]>&#13;</xsl:text>
    <xsl:text><![CDATA[sessionbeansess = addItem(sessionbeanname,"images/onepix.gif","Session type: ]]></xsl:text>
    <b>
      <xsl:value-of select="session-type"/>
    </b>
    <xsl:text><![CDATA[","");]]></xsl:text>
    <xsl:text><![CDATA[sessionbeantrans= addItem(sessionbeanname,"images/onepix.gif","Transaction type: ]]></xsl:text>
    <b>
      <xsl:value-of select="transaction-type"/>
    </b>
    <xsl:text><![CDATA[","");]]>&#13;</xsl:text>
 
    <!--       <xsl:if test="security-identity"><xsl:text><![CDATA[sessionbeansec = addItem(sessionbeanname,"images/onepix.gif","Security identity: ]]></xsl:text><b><xsl:value-of select="security-identity"/></b><xsl:text><![CDATA[","");]]>&#13;</xsl:text></xsl:if>       -->
    <xsl:if test="security-identity">
      <xsl:text><![CDATA[securityidentity = addItem(sessionbeanname,"images/onepix.gif","Security Identity","");]]></xsl:text>
      <xsl:apply-templates select="security-identity">
        <xsl:sort select="description"/>
      </xsl:apply-templates>
    </xsl:if>

                

    <xsl:if test="env-entry">
      <xsl:text><![CDATA[env = addItem(sessionbeanname,"images/onepix.gif","Environment Entries","");]]></xsl:text>
      <xsl:apply-templates select="env-entry">
        <xsl:sort select="env-entry-name"/>
      </xsl:apply-templates>
    </xsl:if>
    <xsl:if test="ejb-ref">
      <xsl:text><![CDATA[ejbref = addItem(sessionbeanname,"images/onepix.gif","EJB References","");]]></xsl:text>
      <xsl:apply-templates select="ejb-ref">
        <xsl:sort select="ejb-ref-name"/>
      </xsl:apply-templates>
    </xsl:if>
                        

    <xsl:if test="ejb-local-ref">
      <xsl:text><![CDATA[ejblocalref = addItem(sessionbeanname,"images/onepix.gif","EJB References","");]]></xsl:text>
      <xsl:apply-templates select="ejb-local-ref">
        <xsl:sort select="ejb-ref-name"/>
      </xsl:apply-templates>
    </xsl:if>

    <xsl:if test="security-role-ref">
      <xsl:text><![CDATA[secroleref = addItem(sessionbeanname,"images/onepix.gif","Security Role References","");]]></xsl:text>
      <xsl:apply-templates select="security-role-ref">
        <xsl:sort select="role-name"/>
      </xsl:apply-templates>
    </xsl:if>
    <xsl:if test="resource-ref">
      <xsl:text><![CDATA[resref = addItem(sessionbeanname,"images/onepix.gif","Resource References","");]]></xsl:text>
      <xsl:apply-templates select="resource-ref">
        <xsl:sort select="res-ref-name"/>
      </xsl:apply-templates>
    </xsl:if>
                        

    <xsl:if test="resource-env-ref">
      <xsl:text><![CDATA[resenvref = addItem(sessionbeanname,"images/onepix.gif","Resource Environment References","");]]></xsl:text>
      <xsl:apply-templates select="resource-env-ref">
        <xsl:sort select="resource-env-ref-name"/>
      </xsl:apply-templates>
    </xsl:if>

                        

                       
                        

                        
                        

  </xsl:template>

  <xsl:template match="enterprise-beans/message-driven">
                



                        

    <xsl:text><![CDATA[mdbeanname = addItem(mdbean,"images/onepix.gif","EJB Name: ]]></xsl:text>
    <b>
      <xsl:value-of select="ejb-name"/>
    </b>
    <xsl:text><![CDATA[","");]]>&#13;</xsl:text>
    <xsl:if test="disply-name">
      <xsl:text><![CDATA[mdbeandisp = addItem(mdbeanname,"images/onepix.gif","Display name: ]]></xsl:text>
      <xsl:call-template name='illegalChar-replace'>
        <xsl:with-param name='text'>
      <xsl:value-of select="display-name" disable-output-escaping='yes'/>
      </xsl:with-param>
      </xsl:call-template>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
    <xsl:if test="description">
      <xsl:text><![CDATA[mdbeandesc = addItem(mdbeanname,"images/onepix.gif","Description: ]]></xsl:text>
      <xsl:call-template name='illegalChar-replace'>
        <xsl:with-param name='text'>
          <xsl:value-of select='description' disable-output-escaping='yes'/>
        </xsl:with-param>
      </xsl:call-template>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
    <xsl:if test="small-icon">
      <xsl:text><![CDATA[mdbeansi = addItem(mdbeanname,"images/onepix.gif","Small icon: ]]></xsl:text>
      <xsl:value-of select="small-icon"/>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
    <xsl:if test="large-icon">
      <xsl:text><![CDATA[mdbeanli = addItem(mdbeanname,"images/onepix.gif","Large icon: ]]></xsl:text>
      <xsl:value-of select="large-icon"/>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
                        
                         
                                               

    <xsl:text><![CDATA[mdbeanclass = addItem(mdbeanname,"images/onepix.gif","EJB-class: ]]></xsl:text>
    <b>
      <xsl:value-of select="ejb-class"/>
    </b>
    <xsl:text><![CDATA[","");]]>&#13;</xsl:text>
    <xsl:text><![CDATA[mdbeantrans= addItem(mdbeanname,"images/onepix.gif","Transaction type: ]]></xsl:text>
    <b>
      <xsl:value-of select="transaction-type"/>
    </b>
    <xsl:text><![CDATA[","");]]>&#13;</xsl:text>
 
                        

    <xsl:if test="message-selector">
      <xsl:text><![CDATA[mdbeanselect= addItem(mdbeanname,"images/onepix.gif","Message selector: ]]></xsl:text>
      <b>
        <xsl:value-of select="message-selector"/>
      </b>
      <xsl:text><![CDATA[","");]]>&#13;</xsl:text>
    </xsl:if>
    <xsl:if test="acknowledge-mode">
      <xsl:text><![CDATA[mdbeanack= addItem(mdbeanname,"images/onepix.gif","Acknowledge mode: ]]></xsl:text>
      <b>
        <xsl:value-of select="acknowledge-mode"/>
      </b>
      <xsl:text><![CDATA[","");]]>&#13;</xsl:text>
    </xsl:if>
    <xsl:if test="message-driven-destination">
      <xsl:text><![CDATA[mdbeanmddest = addItem(mdbeanname,"images/onepix.gif","Message-driven destination","");]]>&#13;</xsl:text>
                                

      <xsl:text><![CDATA[mdbeanmddestt = addItem(mdbeanmddest,"images/onepix.gif","Type: ]]></xsl:text>
      <b>
        <xsl:value-of select="message-driven-destination/destination-type"/>
      </b>
      <xsl:text><![CDATA[","");]]>&#13;</xsl:text>
      <xsl:text><![CDATA[mdbeanmddests = addItem(mdbeanmddest,"images/onepix.gif","Subscription durability: ]]></xsl:text>
      <b>
        <xsl:value-of select="message-driven-destination/subscription-durability"/>
      </b>
      <xsl:text><![CDATA[","");]]>&#13;</xsl:text>
    </xsl:if>
    <!--           <xsl:if test="security-identity"><xsl:text><![CDATA[mdbeansec = addItem(mdbeanname,"images/onepix.gif","Security identity: ]]></xsl:text><b><xsl:value-of select="security-identity"/></b><xsl:text><![CDATA[","");]]>&#13;</xsl:text></xsl:if>          -->
    <xsl:if test="security-identity">
      <xsl:text><![CDATA[securityidentity = addItem(mdbeanname,"images/onepix.gif","Security Identity","");]]></xsl:text>
      <xsl:apply-templates select="security-identity">
        <xsl:sort select="description"/>
      </xsl:apply-templates>
    </xsl:if>



    <xsl:if test="env-entry">
      <xsl:text><![CDATA[env = addItem(mdbeanname,"images/onepix.gif","Environment Entries","");]]></xsl:text>
      <xsl:apply-templates select="env-entry">
        <xsl:sort select="env-entry-name"/>
      </xsl:apply-templates>
    </xsl:if>
    <xsl:if test="ejb-ref">
      <xsl:text><![CDATA[ejbref = addItem(mdbeanname,"images/onepix.gif","EJB References","");]]></xsl:text>
      <xsl:apply-templates select="ejb-ref">
        <xsl:sort select="ejb-ref-name"/>
      </xsl:apply-templates>
    </xsl:if>
                        

    <xsl:if test="ejb-local-ref">
      <xsl:text><![CDATA[ejblocalref = addItem(mdbeanname,"images/onepix.gif","EJB References","");]]></xsl:text>
      <xsl:apply-templates select="ejb-local-ref">
        <xsl:sort select="ejb-ref-name"/>
      </xsl:apply-templates>
    </xsl:if>

    <xsl:if test="resource-ref">
      <xsl:text><![CDATA[resref = addItem(mdbeanname,"images/onepix.gif","Resource References","");]]></xsl:text>
      <xsl:apply-templates select="resource-ref">
        <xsl:sort select="res-ref-name"/>
      </xsl:apply-templates>
    </xsl:if>
                        

    <xsl:if test="resource-env-ref">
      <xsl:text><![CDATA[resenvref = addItem(mdbeanname,"images/onepix.gif","Resource Environment References","");]]></xsl:text>
      <xsl:apply-templates select="resource-env-ref">
        <xsl:sort select="resource-env-ref-name"/>
      </xsl:apply-templates>
    </xsl:if>

                        

                       
                        

                        
                        

  </xsl:template>


         


  <xsl:template match="cmp-field">

    <xsl:text><![CDATA[cmpfieldID = addItem(cmpfield,"images/onepix.gif","CMP field ID: ]]></xsl:text>
    <b>
      <xsl:value-of select="@id"/>
    </b>
    <xsl:text><![CDATA[","");]]></xsl:text>
    <xsl:text><![CDATA[cmpfieldN = addItem(cmpfieldID,"images/onepix.gif","Field name: ]]></xsl:text>
    <b>
      <xsl:value-of select="field-name"/>
    </b>
    <xsl:text><![CDATA[","");]]></xsl:text>
        
        
                     

  </xsl:template>





  <xsl:template match="security-role-ref">

    <xsl:text><![CDATA[secrolerefN = addItem(secroleref,"images/onepix.gif","Role name: ]]></xsl:text>
    <b>
      <xsl:value-of select="role-name"/>
    </b>
    <xsl:text><![CDATA[","");]]></xsl:text>
    <xsl:text><![CDATA[secrolerefL = addItem(secrolerefN,"images/onepix.gif","Role link: ]]></xsl:text>
    <b>
      <xsl:value-of select="role-link"/>
    </b>
    <xsl:text><![CDATA[","");]]></xsl:text>
        
        
                     

  </xsl:template>






  <xsl:template match="ejb-relation">


    <xsl:text><![CDATA[rels = addItem(relationships,"images/onepix.gif","EJB relation","");]]></xsl:text>
    <xsl:if test="ejb-relation-name">
      <xsl:text><![CDATA[relname = addItem(rels,"images/onepix.gif","EJB relation name: ]]></xsl:text>
      <b>
        <xsl:value-of select="role-name"/>
      </b>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
    <xsl:if test="description">
      <xsl:text><![CDATA[reldesc = addItem(rels,"images/onepix.gif","Description: ]]></xsl:text>
      <b>
        <xsl:call-template name='illegalChar-replace'>
          <xsl:with-param name='text'>
            <xsl:value-of select='description' disable-output-escaping='yes'/>
          </xsl:with-param>
        </xsl:call-template>
      </b>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
        
        

    <xsl:apply-templates select="ejb-relationship-role">
      <xsl:sort select="ejb-relationship-role-name"/>
    </xsl:apply-templates>

                     

  </xsl:template>


  <xsl:template match="ejb-relationship-role">


    <xsl:if test="ejb-relationship-role-name">
      <xsl:text><![CDATA[relname = addItem(rels,"images/onepix.gif","EJB relationship role name: ]]></xsl:text>
      <b>
        <xsl:value-of select="ejb-relationship-role-name"/>
      </b>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
    <xsl:if test="description">
      <xsl:text><![CDATA[relroleD = addItem(rels,"images/onepix.gif","Description: ]]></xsl:text>
      <b>
        <xsl:call-template name='illegalChar-replace'>
          <xsl:with-param name='text'>
            <xsl:value-of select='description' disable-output-escaping='yes'/>
          </xsl:with-param>
        </xsl:call-template>
      </b>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
    <xsl:text><![CDATA[relroleM = addItem(rels,"images/onepix.gif","Mutltiplicity: ]]></xsl:text>
    <b>
      <xsl:value-of select="multiplicity"/>
    </b>
    <xsl:text><![CDATA[","");]]></xsl:text>
    <xsl:text><![CDATA[relroleS = addItem(rels,"images/onepix.gif","Relationship role source: ]]></xsl:text>
    <b>
      <xsl:value-of select="relationship-role-source"/>
    </b>
    <xsl:text><![CDATA[","");]]></xsl:text>
    <xsl:if test="cascade-delete">
      <xsl:text><![CDATA[relroleCD = addItem(rels,"images/onepix.gif","Cascade delete: ]]></xsl:text>
      <b>
        <xsl:value-of select="cascade-delete"/>
      </b>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
    <xsl:if test="cmr-field">
      <xsl:text><![CDATA[relroleCF = addItem(rels,"images/onepix.gif","CMR field: ]]></xsl:text>
      <b>
        <xsl:value-of select="cmr-field"/>
      </b>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
        
                     

  </xsl:template>





  <xsl:template match="env-entry">

    <xsl:text><![CDATA[envname = addItem(env,"images/onepix.gif","Environment Entry name: ]]></xsl:text>
    <b>
      <xsl:value-of select="env-entry-name"/>
    </b>
    <xsl:text><![CDATA[","");]]></xsl:text>
    <xsl:text><![CDATA[envtype = addItem(envname,"images/onepix.gif","Type: ]]></xsl:text>
    <b>
      <xsl:value-of select="env-entry-type"/>
    </b>
    <xsl:text><![CDATA[","");]]>&#13;</xsl:text>
    <xsl:text><![CDATA[envvalue = addItem(envname,"images/onepix.gif","Value: ]]></xsl:text>
    <b>
      <xsl:call-template name='illegalChar-replace'>
        <xsl:with-param name='text'>
      <xsl:value-of select="env-entry-value" disable-output-escaping='yes'/>
          </xsl:with-param>
        </xsl:call-template>
    </b>
    <xsl:text><![CDATA[","");]]></xsl:text>
    <xsl:if test="description">
      <xsl:text><![CDATA[envnamed = addItem(envname,"images/onepix.gif","Description: ]]></xsl:text>
      <b>
        <xsl:call-template name='illegalChar-replace'>
          <xsl:with-param name='text'>
            <xsl:value-of select='description' disable-output-escaping='yes'/>
          </xsl:with-param>
        </xsl:call-template>
      </b>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
        
                     

  </xsl:template>


  <xsl:template match="ejb-ref">

    <xsl:text><![CDATA[ejbrefname = addItem(ejbref,"images/onepix.gif","EJB Reference name: ]]></xsl:text>
    <b>
      <xsl:value-of select="ejb-ref-name"/>
    </b>
    <xsl:text><![CDATA[","");]]></xsl:text>
    <xsl:text><![CDATA[ejbreftype = addItem(ejbrefname,"images/onepix.gif","Type: ]]></xsl:text>
    <b>
      <xsl:value-of select="ejb-ref-type"/>
    </b>
    <xsl:text><![CDATA[","");]]>&#13;</xsl:text>
    <xsl:text><![CDATA[ejbrefhome = addItem(ejbrefname,"images/onepix.gif","Home: ]]></xsl:text>
    <b>
      <xsl:value-of select="home"/>
    </b>
    <xsl:text><![CDATA[","");]]></xsl:text>
    <xsl:text><![CDATA[ejbrefremote = addItem(ejbrefname,"images/onepix.gif","Remote: ]]></xsl:text>
    <b>
      <xsl:value-of select="remote"/>
    </b>
    <xsl:text><![CDATA[","");]]></xsl:text>
    <xsl:text><![CDATA[ejbreflink = addItem(ejbrefname,"images/onepix.gif","Link: ]]></xsl:text>
    <b>
      <xsl:value-of select="ejb-link"/>
    </b>
    <xsl:text><![CDATA[","");]]></xsl:text>
    <xsl:if test="description">
      <xsl:text><![CDATA[ejbrefnamed = addItem(ejbrefname,"images/onepix.gif","Description: ]]></xsl:text>
      <b>
        <xsl:call-template name='illegalChar-replace'>
          <xsl:with-param name='text'>
            <xsl:value-of select='description' disable-output-escaping='yes'/>
          </xsl:with-param>
        </xsl:call-template>
      </b>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
                     

  </xsl:template>


  <xsl:template match="ejb-local-ref">

    <xsl:text><![CDATA[ejblocalrefname = addItem(ejblocalref,"images/onepix.gif","EJB local reference name: ]]></xsl:text>
    <b>
      <xsl:value-of select="ejb-ref-name"/>
    </b>
    <xsl:text><![CDATA[","");]]></xsl:text>
    <xsl:text><![CDATA[ejblocalreftype = addItem(ejblocalrefname,"images/onepix.gif","Local type: ]]></xsl:text>
    <b>
      <xsl:value-of select="ejb-ref-type"/>
    </b>
    <xsl:text><![CDATA[","");]]>&#13;</xsl:text>
    <xsl:text><![CDATA[ejblocalrefhome = addItem(ejblocalrefname,"images/onepix.gif","Local home: ]]></xsl:text>
    <b>
      <xsl:value-of select="local-home"/>
    </b>
    <xsl:text><![CDATA[","");]]></xsl:text>
    <xsl:text><![CDATA[ejblocalrefremote = addItem(ejblocalrefname,"images/onepix.gif","Local: ]]></xsl:text>
    <b>
      <xsl:value-of select="local"/>
    </b>
    <xsl:text><![CDATA[","");]]></xsl:text>
    <xsl:if test="ejb-link">
      <xsl:text><![CDATA[ejblocalreflink = addItem(ejblocalrefname,"images/onepix.gif","EJB link: ]]></xsl:text>
      <b>
        <xsl:value-of select="ejb-link"/>
      </b>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
    <xsl:if test="description">
      <xsl:text><![CDATA[ejblocalrefnamed = addItem(ejblocalrefname,"images/onepix.gif","Description: ]]></xsl:text>
      <b>
        <xsl:call-template name='illegalChar-replace'>
          <xsl:with-param name='text'>
            <xsl:value-of select='description' disable-output-escaping='yes'/>
          </xsl:with-param>
        </xsl:call-template>
      </b>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
                     

  </xsl:template>

  <xsl:template match="security-identity">
    <xsl:text><![CDATA[securityidentitydesc = addItem(securityidentity,"images/onepix.gif","Security Identity description:]]></xsl:text>
    <b>
        <xsl:call-template name='illegalChar-replace'>
          <xsl:with-param name='text'>
      <xsl:value-of select="description" disable-output-escaping='yes'/>
          </xsl:with-param>
        </xsl:call-template>
    </b>
    <xsl:text><![CDATA[","");]]></xsl:text>
    <xsl:if test="use-caller-identity">
      <xsl:text><![CDATA[securityusecalleridentity = addItem(securityidentitydesc,"images/onepix.gif","Use Caller Identity:]]></xsl:text>
      <b>
        <xsl:value-of select="use-caller-identity"/>
      </b>
      <xsl:text><![CDATA[","");]]>&#13;</xsl:text>
    </xsl:if>
    <xsl:if test="run-as">
      <xsl:text><![CDATA[securityrunas = addItem(securityidentitydesc,"images/onepix.gif","Run as description:]]></xsl:text>
      <b>
          <xsl:call-template name='illegalChar-replace'>
            <xsl:with-param name='text'>
        <xsl:value-of select="description" disable-output-escaping='yes'/>
          </xsl:with-param>
        </xsl:call-template>
      </b>
      <xsl:text><![CDATA[","");]]>&#13;</xsl:text>
      <xsl:text><![CDATA[securityrunasname = addItem(securityidentitydesc,"images/onepix.gif","Run as role-name:]]></xsl:text>
      <b>
        <xsl:value-of select="role-name"/>
      </b>
      <xsl:text><![CDATA[","");]]>&#13;</xsl:text>
    </xsl:if>
  </xsl:template>


  <xsl:template match="resource-ref">

    <xsl:text><![CDATA[resrefname = addItem(resref,"images/onepix.gif","Resource reference name: ]]></xsl:text>
    <b>
      <xsl:value-of select="res-ref-name"/>
    </b>
    <xsl:text><![CDATA[","");]]></xsl:text>
    <xsl:text><![CDATA[resreftype = addItem(resrefname,"images/onepix.gif","Type: ]]></xsl:text>
    <b>
      <xsl:value-of select="res-type"/>
    </b>
    <xsl:text><![CDATA[","");]]></xsl:text>
    <xsl:text><![CDATA[resrefauth = addItem(resrefname,"images/onepix.gif","Authorization: ]]></xsl:text>
    <b>
      <xsl:value-of select="res-auth"/>
    </b>
    <xsl:text><![CDATA[","");]]></xsl:text>
                  

  </xsl:template>


  <xsl:template match="resource-env-ref">

    <xsl:text><![CDATA[resenvrefname = addItem(resenvref,"images/onepix.gif","Resource environment reference name: ]]></xsl:text>
    <b>
      <xsl:value-of select="resource-env-ref-name"/>
    </b>
    <xsl:text><![CDATA[","");]]></xsl:text>
    <xsl:text><![CDATA[resenvreftype = addItem(resenvrefname,"images/onepix.gif","Resource environment reference type: ]]></xsl:text>
    <b>
      <xsl:value-of select="resource-env-ref-type"/>
    </b>
    <xsl:text><![CDATA[","");]]></xsl:text>
    <xsl:if test="description">
      <xsl:text><![CDATA[ejblocalrefnamed = addItem(resenvrefname,"images/onepix.gif","Description: ]]></xsl:text>
      <b>
        <xsl:call-template name='illegalChar-replace'>
          <xsl:with-param name='text'>
            <xsl:value-of select='description' disable-output-escaping='yes'/>
          </xsl:with-param>
        </xsl:call-template>
      </b>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
                  

  </xsl:template>


  <xsl:template match="assembly-descriptor">

        

    <xsl:if test="security-role">
      <xsl:text><![CDATA[secrole = addItem(assembly,"images/onepix.gif","Security Role References","");]]>&#13;</xsl:text>
      <xsl:apply-templates select="security-role">
        <xsl:sort select="role-name"/>
      </xsl:apply-templates>
    </xsl:if>

    <xsl:if test="method-permission">
      <xsl:text><![CDATA[methodperm = addItem(assembly,"images/onepix.gif","Method Permissions","");]]></xsl:text>
      <xsl:apply-templates select="method-permission"/>
    </xsl:if>
    <xsl:if test="container-transaction">
      <xsl:text><![CDATA[container = addItem(assembly,"images/onepix.gif","Container Transactions","");]]></xsl:text>
      <xsl:apply-templates select="container-transaction"/>
    </xsl:if>
    <xsl:if test="exclude-list">
      <xsl:text><![CDATA[exclude = addItem(assembly,"images/onepix.gif","Exclude list","");]]></xsl:text>
      <xsl:apply-templates select="exclude-list"/>
    </xsl:if>
       
        
       
        


  </xsl:template>


  <xsl:template match="security-role">

    <xsl:text><![CDATA[secroleN = addItem(secrole,"images/onepix.gif","Role name: ]]></xsl:text>
    <b>
      <xsl:value-of select="role-name"/>
    </b>
    <xsl:text><![CDATA[","");]]></xsl:text>
    <xsl:if test="description">
      <xsl:text><![CDATA[secroleD = addItem(secroleN,"images/onepix.gif","Description: ]]></xsl:text>
      <b>
        <xsl:call-template name='illegalChar-replace'>
          <xsl:with-param name='text'>
            <xsl:value-of select='description' disable-output-escaping='yes'/>
          </xsl:with-param>
        </xsl:call-template>
      </b>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
  


  </xsl:template>

  <xsl:template match="query">

    <xsl:text><![CDATA[queryN = addItem(query,"images/onepix.gif","Query name: ]]></xsl:text>
    <b>
      <xsl:value-of select="role-name"/>
    </b>
    <xsl:text><![CDATA[","");]]></xsl:text>
    <xsl:text><![CDATA[ejbqueryql = addItem(queryN,"images/onepix.gif","EJB ql: ]]></xsl:text>
    <b>
       <xsl:call-template name='illegalChar-replace'>
          <xsl:with-param name='text'>
      <xsl:value-of select="ejb-ql" disable-output-escaping='yes'/>
          </xsl:with-param>
        </xsl:call-template>
    </b>
    <xsl:text><![CDATA[","");]]></xsl:text>
    <xsl:if test="result-type-mapping">
      <xsl:text><![CDATA[queryRTM = addItem(queryN,"images/onepix.gif","Result type mapping: ]]></xsl:text>
      <b>
        <xsl:value-of select="result-type-mapping"/>
      </b>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
    <xsl:if test="description">
      <xsl:text><![CDATA[queryD = addItem(queryN,"images/onepix.gif","Description: ]]></xsl:text>
      <b>
        <xsl:call-template name='illegalChar-replace'>
          <xsl:with-param name='text'>
            <xsl:value-of select='description' disable-output-escaping='yes'/>
          </xsl:with-param>
        </xsl:call-template>
      </b>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>
    <xsl:if test="query-method">
      <xsl:text><![CDATA[queryM = addItem(queryN,"images/onepix.gif","Query Methods","");]]></xsl:text>
      <xsl:apply-templates select="query-method">
        <xsl:sort select="method-name"/>
      </xsl:apply-templates>
        

    </xsl:if>
        
        


  </xsl:template>


  <xsl:template match="method-permission">

    <xsl:if test="method">
        

      <xsl:text><![CDATA[amethod = addItem(methodperm,"images/onepix.gif","Method: ]]></xsl:text>
      <b>
        <xsl:value-of select="method/method-name"/>
      </b>
      <xsl:text><![CDATA[","");]]>&#13;</xsl:text>
                
                    

      <xsl:apply-templates select="method">
        <xsl:sort select="ejb-name"/>
      </xsl:apply-templates>
        

    </xsl:if>

    <xsl:if test="description">
        

      <xsl:if test="description">
        <xsl:text><![CDATA[description = addItem(methodperm,"images/onepix.gif","Description: ]]></xsl:text>
        <b>
          <xsl:call-template name='illegalChar-replace'>
            <xsl:with-param name='text'>
              <xsl:value-of select='description' disable-output-escaping='yes'/>
            </xsl:with-param>
          </xsl:call-template>
        </b>
        <xsl:text><![CDATA[","");]]>&#13;</xsl:text>
      </xsl:if>
                

                

    </xsl:if>

    <xsl:if test="role-name">
        
               

      <xsl:text><![CDATA[methroleN = addItem(methodperm,"images/onepix.gif","Role name: ]]></xsl:text>
      <b>
        <xsl:value-of select="role-name"/>
      </b>
      <xsl:text><![CDATA[","");]]>&#13;</xsl:text>
                

    </xsl:if>

  </xsl:template>





  <xsl:template match="method">


    <xsl:text><![CDATA[methodN = addItem(amethod,"images/onepix.gif","EJB name: ]]></xsl:text>
    <b>
      <xsl:value-of select="ejb-name"/>
    </b>
    <xsl:text><![CDATA[","");]]>&#13;</xsl:text>
    <xsl:if test="method-intf">
      <xsl:text><![CDATA[methodintf = addItem(methodN,"images/onepix.gif","Method intf: ]]></xsl:text>
      <b>
        <xsl:value-of select="method-intf"/>
      </b>
      <xsl:text><![CDATA[","");]]>&#13;</xsl:text>
    </xsl:if>
    <xsl:if test="method-name">
      <xsl:text><![CDATA[methodN1 = addItem(methodN,"images/onepix.gif","Method name: ]]></xsl:text>
      <b>
        <xsl:value-of select="method-name"/>
      </b>
      <xsl:text><![CDATA[","");]]>&#13;</xsl:text>
    </xsl:if>
        
        

    <xsl:if test="method-params[method-param]">
      <xsl:if test="method-param">
        <xsl:apply-templates select="method-param"/>
      </xsl:if>
    </xsl:if>
        
        


  </xsl:template>

  <xsl:template match="query-method">


                

    <xsl:text><![CDATA[methodN = addItem(queryM,"images/onepix.gif","Query method name: ]]></xsl:text>
    <b>
      <xsl:value-of select="method-name"/>
    </b>
    <xsl:text><![CDATA[","");]]>&#13;</xsl:text>
        
                

    <xsl:if test="method-param">
      <xsl:apply-templates select="method-param"/>
    </xsl:if>

        
        


  </xsl:template>



  <xsl:template match="method-param">
        

    <xsl:text><![CDATA[methodpararm = addItem(methodN,"images/onepix.gif","Method parameter: ]]></xsl:text>
    <b>
        <xsl:call-template name='illegalChar-replace'>
          <xsl:with-param name='text'>
      <xsl:value-of select="method-parameter" disable-output-escaping='yes'/>
          </xsl:with-param>
        </xsl:call-template>
    </b>
    <xsl:text><![CDATA[","");]]></xsl:text>
        

  </xsl:template>



  <xsl:template match="container-transaction">

    <xsl:if test="trans-attribute">
      <xsl:text><![CDATA[trans = addItem(container,"images/onepix.gif","Trans attribute: ]]></xsl:text>
      <b>
        <xsl:call-template name='illegalChar-replace'>
          <xsl:with-param name='text'>
        <xsl:value-of select="trans-attribute" disable-output-escaping='yes'/>
          </xsl:with-param>
        </xsl:call-template>

      </b>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>

    <xsl:if test="method">
       

      <xsl:text><![CDATA[amethod = addItem(container,"images/onepix.gif","Methods","");]]></xsl:text>
      

      <xsl:apply-templates select="method"/>
                

    </xsl:if>
        
        

  </xsl:template>


  <xsl:template match="exclude-list">

    <xsl:if test="description">
      <xsl:text><![CDATA[trans = addItem(exclude,"images/onepix.gif","Description: ]]></xsl:text>
      <b>
        <xsl:call-template name='illegalChar-replace'>
          <xsl:with-param name='text'>
            <xsl:value-of select='description' disable-output-escaping='yes'/>
          </xsl:with-param>
        </xsl:call-template>
      </b>
      <xsl:text><![CDATA[","");]]></xsl:text>
    </xsl:if>

    <xsl:if test="method">
       

      <xsl:text><![CDATA[amethod = addItem(exlude,"images/onepix.gif","Methods","");]]></xsl:text>
      

      <xsl:apply-templates select="method"/>
                

    </xsl:if>
        
        

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

