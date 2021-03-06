<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html>
<head>
<title>Test struts-logic Presence Tags</title>
</head>
<body bgcolor="white">

<div align="center">
<h1>Test struts-logic Presence Tags</h1>
</div>

<jsp:useBean id="bean" scope="page" class="org.apache.struts.webapp.exercise.TestBean"/>

<table border="1">
  <tr>
    <th>Test Type</th>
    <th>Correct Value</th>
    <th>Test Result</th>
  </tr>
  <tr>
    <td>Bean</td>
    <td>present</td>
    <td>
      <logic:present name="bean">
        present
      </logic:present>
      <logic:notPresent name="bean">
        notPresent
      </logic:notPresent>
    </td>
  </tr>
  <tr>
    <td>Bean</td>
    <td>notPresent</td>
    <td>
      <logic:present name="FOOBAR">
        present
      </logic:present>
      <logic:notPresent name="FOOBAR">
        notPresent
      </logic:notPresent>
    </td>
  </tr>
  <tr>
    <td>Cookie</td>
<% if (request.isRequestedSessionIdFromCookie()) { %>
    <td>present</td>
<% } else { %>
    <td>notPresent</td>
<% } %>
    <td>
      <logic:present cookie="JSESSIONID">
        present
      </logic:present>
      <logic:notPresent cookie="JSESSIONID">
        notPresent
      </logic:notPresent>
    </td>
  </tr>
  <tr>
    <td>Cookie</td>
    <td>notPresent</td>
    <td>
      <logic:present cookie="FOOBAR">
        present
      </logic:present>
      <logic:notPresent cookie="FOOBAR">
        notPresent
      </logic:notPresent>
    </td>
  </tr>
  <tr>
    <td>Header</td>
    <td>present</td>
    <td>
      <logic:present header="User-Agent">
        present
      </logic:present>
      <logic:notPresent header="User-Agent">
        notPresent
      </logic:notPresent>
    </td>
  </tr>
  <tr>
    <td>Header</td>
    <td>notPresent</td>
    <td>
      <logic:present header="FOOBAR">
        present
      </logic:present>
      <logic:notPresent header="FOOBAR">
        notPresent
      </logic:notPresent>
    </td>
  </tr>
  <tr>
    <td>Parameter</td>
    <td>present</td>
    <td>
      <logic:present parameter="param1">
        present
      </logic:present>
      <logic:notPresent parameter="param1">
        notPresent
      </logic:notPresent>
    </td>
  </tr>
  <tr>
    <td>Parameter</td>
    <td>notPresent</td>
    <td>
      <logic:present parameter="FOOBAR">
        present
      </logic:present>
      <logic:notPresent parameter="FOOBAR">
        notPresent
      </logic:notPresent>
    </td>
  </tr>
  <tr>
    <td>Property</td>
    <td>present</td>
    <td>
      <logic:present name="bean" property="stringProperty">
        present
      </logic:present>
      <logic:notPresent name="bean" property="stringProperty">
        notPresent
      </logic:notPresent>
    </td>
  </tr>
  <tr>
    <td>Property</td>
    <td>notPresent</td>
    <td>
      <logic:present name="bean" property="nullProperty">
        present
      </logic:present>
      <logic:notPresent name="bean" property="nullProperty">
        notPresent
      </logic:notPresent>
    </td>
  </tr>
</table>

</body>
</html>
