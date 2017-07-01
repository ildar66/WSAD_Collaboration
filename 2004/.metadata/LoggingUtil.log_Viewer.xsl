
<?xml version="1.0" encoding="ISO-8859-1"?>


<!DOCTYPE xsl:stylesheet [

    <!ENTITY nbsp "&#xA0;">

]>


<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="html" encoding="ISO-8859-1" indent="no"/>

<!-- Template for the 'document' element: -->
<xsl:template match="document">

    <html>
    <body>
        <b> This file can be properly displayed using IE 6.0.28+
        <br/> The content of the file is extracted from C:\workSpasesNew\WSAD_Collaboration\.metadata\LoggingUtil.log </b><br/>
        <pre>

        <!-- Iterate the first-level child elements of the 'document' element: -->
        <xsl:for-each select="node()">

            <xsl:apply-templates select="."/>

        <br/>

        </xsl:for-each>
        </pre>

    </body>
    </html>

</xsl:template>

<!-- Template to recursively check attribute values for the tab (&#x9;) entity reference: -->
<xsl:template name="tabSubstitution">

    <xsl:param name="attributeValue"/>

    <!-- Check if the attribute value contains a tab (&#x9;) entity reference: -->
    <xsl:if test="contains($attributeValue,'&#x9;')">

        <!-- Check the part of the attribute value before the tab (&#x9;) entity reference for a new line (&#xA;) entity reference: -->
        <xsl:call-template name="newlineSubstitution">
            <xsl:with-param name="attributeValue" select="substring-before($attributeValue,'&#x9;')"/>
        </xsl:call-template>

        <!-- Substitute four spaces for a tab: -->
        <xsl:text>&nbsp;&nbsp;&nbsp;&nbsp;</xsl:text>

        <!-- Check the part of the attribute value after the tab (&#x9;) entity reference for other tab (&#x9;) entity references: -->
        <xsl:call-template name="tabSubstitution">
            <xsl:with-param name="attributeValue" select="substring-after($attributeValue,'&#x9;')"/>
        </xsl:call-template>

    </xsl:if>

    <!-- Check if the attribute value does not contain a tab (&#x9;) entity reference: -->
    <xsl:if test="not(contains($attributeValue,'&#x9;'))">

        <!-- Check the attribute value for a new line (&#xA;) entity reference: -->
        <xsl:call-template name="newlineSubstitution">
            <xsl:with-param name="attributeValue" select="$attributeValue"/>
        </xsl:call-template>

    </xsl:if>

</xsl:template>

<!-- Template to recursively check attribute values for the new line (&#xA;) entity reference: -->
<!-- Assumption:  All &#xD; entity references are replaced with &#xA; entity references by the XML parser: -->
<xsl:template name="newlineSubstitution">

    <xsl:param name="attributeValue"/>

    <!-- Check if the attribute value contains a new line (&#xA;) entity reference: -->
    <xsl:if test="contains($attributeValue,'&#xA;')">

        <!-- Check the part of the attribute value before the new line (&#xA;) entity reference for a space (&#x20;) entity reference: -->
        <xsl:call-template name="spaceSubstitution">
            <xsl:with-param name="attributeValue" select="substring-before($attributeValue,'&#xA;')"/>
        </xsl:call-template>

        <!-- Substitute <br/> for a new line: -->
        <br/>

        <!-- Check the part of the attribute value after the new line (&#xA;) entity reference for other new line (&#xA;) entity references: -->
        <xsl:call-template name="newlineSubstitution">
            <xsl:with-param name="attributeValue" select="substring-after($attributeValue,'&#xA;')"/>
        </xsl:call-template>

    </xsl:if>

    <!-- Check if the attribute value does not contain a new line (&#xA;) entity reference: -->
    <xsl:if test="not(contains($attributeValue,'&#xA;'))">

        <!-- Check the attribute value for a space (&#x20;) entity reference: -->
        <xsl:call-template name="spaceSubstitution">
            <xsl:with-param name="attributeValue" select="$attributeValue"/>
        </xsl:call-template>

    </xsl:if>

</xsl:template>


<!-- Template to recursively check attribute values for the space (&#x20;) entity reference: -->
<xsl:template name="spaceSubstitution">

    <xsl:param name="attributeValue"/>

    <!-- Check if the attribute value contains a space (&#x20;) entity reference: -->
    <xsl:if test="contains($attributeValue,'&#x20;')">

        <!-- Output the attribute value before the space (&#x20;) entity reference: -->
        <xsl:value-of select="substring-before($attributeValue,'&#x20;')"/>

        <!-- Substitute &nbsp; for a space: -->
        <xsl:text>&nbsp;</xsl:text>

        <!-- Check the part of the attribute value after the space (&#x20;) entity reference for other space (&#x20;) entity references: -->
        <xsl:call-template name="spaceSubstitution">
            <xsl:with-param name="attributeValue" select="substring-after($attributeValue,'&#x20;')"/>
        </xsl:call-template>

    </xsl:if>

    <!-- Check if the attribute value does not contain a space (&#x20;) entity reference: -->
    <xsl:if test="not(contains($attributeValue,'&#x20;'))">

        <!-- Output the attribute value: -->
        <xsl:value-of select="$attributeValue"/>

    </xsl:if>

</xsl:template>

<!-- Template for elements: -->
<xsl:template match="*">

      <!-- Indent nested open tag: -->
      <xsl:for-each select="ancestor::*">
          <xsl:text>&nbsp;&nbsp;</xsl:text>
      </xsl:for-each>

      <font color="blue">&lt;</font>

      <!-- Output element name: -->
      <font color="brown"><xsl:value-of select="name()"/></font>

      <!-- Iterate all attributes: -->
      <xsl:for-each select="@*">

          <xsl:text>&nbsp;</xsl:text>

          <!-- Output attribute name: -->
          <font color="brown"><xsl:value-of select="name()"/></font>

          <font color="blue">="</font>

          <b>
              <xsl:element name="{name()}">

                  <!-- Recursively check attribute value for known entity references: -->
                  <xsl:call-template name="tabSubstitution">
                      <xsl:with-param name="attributeValue" select="."/>
                  </xsl:call-template>

              </xsl:element>
          </b>

          <xsl:text>"</xsl:text>

      </xsl:for-each>

      <!-- End the empty tag if no nested elements: -->
      <xsl:if test="not(descendant::*)">
          <font color="blue">/&gt;</font>
      </xsl:if>

      <xsl:if test="descendant::*">

          <font color="blue">&gt;</font>

          <br/>

          <!-- Iterate the first-level child elements of this current element: -->
          <xsl:for-each select="node()">
              <xsl:apply-templates select="."/>
          </xsl:for-each>

          <!-- Indent nested close tag: -->
          <xsl:for-each select="ancestor::*">
              <xsl:text>&nbsp;&nbsp;</xsl:text>
          </xsl:for-each>

          <font color="blue">&lt;/</font>

          <font color="brown"><xsl:value-of select="name()"/></font>

          <font color="blue">&gt;</font>

      </xsl:if>

      <br/>

</xsl:template>

</xsl:stylesheet>