<?xml version='1.0'?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"> 

<!-- IBM Confidential OCO Source Material -->
<!-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 -->
<!-- The source code for this program is not published or otherwise divested -->
<!-- of its trade secrets, irrespective of what has been deposited with the -->
<!-- U.S. Copyright Office. -->

   <xsl:template match="/">
                <xsl:apply-templates select="toc"/>
   </xsl:template>

<xsl:template match="toc">

<NOSCRIPT>
        <xsl:text disable-output-escaping="yes">&#13;</xsl:text>
                <xsl:for-each select="topic">
                        <xsl:text disable-output-escaping="yes"><![CDATA[<div class="indent1">]]></xsl:text><xsl:value-of select="@label"/><xsl:text disable-output-escaping="yes"><![CDATA[</div>]]>&#13;</xsl:text>
                        <xsl:for-each select="topic"> 
                        <xsl:text disable-output-escaping="yes"><![CDATA[<div class="indent2"><a  target='HelpDetail' href='../]]></xsl:text><xsl:value-of select="@href"/><xsl:text disable-output-escaping="yes"><![CDATA['>]]></xsl:text><xsl:value-of select="@label"/><xsl:text disable-output-escaping="yes"><![CDATA[</a></div>]]>&#13;</xsl:text>
                                <xsl:for-each select="topic">
                        <xsl:text disable-output-escaping="yes"><![CDATA[<div class="indent3"><a  target='HelpDetail' href='../]]></xsl:text><xsl:value-of select="@href"/><xsl:text disable-output-escaping="yes"><![CDATA['>]]></xsl:text><xsl:value-of select="@label"/><xsl:text disable-output-escaping="yes"><![CDATA[</a></div>]]>&#13;</xsl:text>
                                </xsl:for-each>
                        </xsl:for-each>
                </xsl:for-each>
</NOSCRIPT>




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


 
           
</xsl:stylesheet>

