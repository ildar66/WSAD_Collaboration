<?xml version="1.0"?>

<!-- IBM Confidential OCO Source Material -->
<!-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 -->
<!-- The source code for this program is not published or otherwise divested -->
<!-- of its trade secrets, irrespective of what has been deposited with the -->
<!-- U.S. Copyright Office. -->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xml:space="default">
  <xsl:param name="contextRoot" select="default"/>
  <xsl:param name="pluginId" select="default"/>
  <xsl:param name="embedded" select="false"/>

  <xsl:output method="xml" 
              indent="yes"/>

  <xsl:template match="/">
    <xsl:apply-templates select="views"/>
  </xsl:template>

  <xsl:template match="views">
    <xsl:apply-templates select="tabDefinition"/>
    <xsl:apply-templates select="quickSearchDefinition"/>
    <xsl:apply-templates select="contentDefinition"/>
  </xsl:template>

  <xsl:template match="contentDefinition">
    <definition name="{@id}" path="/secure/layouts/contentLayout.jsp">
        <put name="descTitle" value="{@title}"/>
        <xsl:if test="$embedded='false'">
            <put name="descImage" value="//{$contextRoot}/{@icon}"/>
        </xsl:if>
        <xsl:if test="$embedded='true'">
            <put name="descImage" value="/{$pluginId}/{@icon}"/>
        </xsl:if>
        <put name="descText" value="{@description}"/>
        <putList name="contentList">
            <xsl:apply-templates select="contentItem"/>
        </putList>
    </definition>
  </xsl:template>

  <xsl:template match="contentItem">
    <xsl:if test="@url">
      <xsl:if test="$embedded='false'">
          <add value="//{$contextRoot}{@url}" />
      </xsl:if>
      <xsl:if test="$embedded='true'">
          <add value="/{$pluginId}{@url}" />
      </xsl:if>
    </xsl:if>
    <xsl:if test="@view">
      <add value="{@view}" />
    </xsl:if>
  </xsl:template>
  
  <xsl:template match="tabDefinition">
    <definition name="{@id}" path="/secure/layouts/tabsLayout.jsp">
        <put name="selectUri" value="navigatorCmd.do?forwardName={@parentId}"/>
        <put name="selectedIndex" value="0"/>
        <put name="parameterName" value="tabIndex"/>
        <putList name="tabList">
            <xsl:apply-templates select="tabItem"/>
        </putList>
    </definition>
  </xsl:template>

  <xsl:template match="tabItem">
    <xsl:if test="@url">
        <xsl:if test="$embedded='false'">
            <item value="{@title}" link="//{$contextRoot}{@url}" classtype="org.apache.struts.tiles.beans.SimpleMenuItem"/>
        </xsl:if>
        <xsl:if test="$embedded='true'">
            <item value="{@title}" link="/{$pluginId}{@url}" classtype="org.apache.struts.tiles.beans.SimpleMenuItem"/>
        </xsl:if>
    </xsl:if>
    <xsl:if test="@view">
      <item value="{@title}" link="{@view}" classtype="org.apache.struts.tiles.beans.SimpleMenuItem"/>
    </xsl:if>
  </xsl:template>
  
  <xsl:template match="quickSearchDefinition">
    <definition name="{@id}" extends="quicksearch.main">
        <put name="selectUri" value="{@uri}"/>
        <put name="collectionForm" value="{@formId}"/>
        <putList name="searchList">
            <xsl:apply-templates select="searchItem"/>
        </putList>
    </definition>
  </xsl:template>
  
  <xsl:template match="searchItem">
      <add value="{@value}"/>
  </xsl:template>
  
</xsl:stylesheet>
