<%@ page language="java" %>
<%@ page contentType = "text/html; charset=windows-1251" %>
<%--@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" --%>
<%--@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" --%>

<ibmcommon:detectLocale/>
<B>Status: режим в разработке !!!</B>
<tiles:insert definition="console.status.main" flush="true" />