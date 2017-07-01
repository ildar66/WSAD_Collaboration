<?xml version="1.0" ?>

<!-- IBM Confidential OCO Source Material -->
<!-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 -->
<!-- The source code for this program is not published or otherwise divested -->
<!-- of its trade secrets, irrespective of what has been deposited with the -->
<!-- U.S. Copyright Office. -->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"> 
<xsl:output indent="no"/>
<xsl:key name="ptfApplied" match="ptf-applied" use="@ptf-id"/>

<xsl:template match="docRoot">
    <xsl:apply-templates/>    
</xsl:template>           


<xsl:template match="ptf">
    <tr valign="top">
        <td class="table-text"  valign="top" scope="row">
            <xsl:value-of select="./@id"/>
        </td>
        <td class="table-text"  valign="top">
            <xsl:value-of select="./@build-version"/>
        </td>
        <td class="table-text"  valign="top">
            <xsl:value-of select="./@build-date"/>
        </td>
        <td class="table-text"  valign="top">
            <xsl:apply-templates select="component-name"/>
        </td>
    </tr>
</xsl:template>

<xsl:template match="component-name">
               <xsl:if test="not(position()=last())">
                    <xsl:value-of select="."/>,  
               </xsl:if>
               <xsl:if test="(position()=last())">
                    <xsl:value-of select="."/>
               </xsl:if>                         
</xsl:template>


</xsl:stylesheet>



