<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="java.io.*,
                 org.xml.sax.*,
                 org.xml.sax.helpers.*,
                 javax.xml.transform.*,
                 javax.xml.transform.sax.*,
                 javax.xml.transform.stream.*,
                 com.ibm.etools.j2ee.xml.J2EEXmlDtDEntityResolver"
%>

<%		  
        String xmlFileName = (String)request.getParameter ("xmlFile");
        String xslFileName = (String)request.getParameter ("xslFile");
        StringWriter stringWriter = new StringWriter();
        TransformerFactory tFactory = null;
        XMLReader xmlReader = null;
        String originalValue = System.getProperty("org.xml.sax.driver");
        if (originalValue == null)
        {
            originalValue = "";
        }
        System.setProperty("org.xml.sax.driver" , "org.apache.xerces.parsers.SAXParser");
        try
        {
            tFactory = TransformerFactory.newInstance();     
            xmlReader = XMLReaderFactory.createXMLReader();
            J2EEXmlDtDEntityResolver  entityResolver= new J2EEXmlDtDEntityResolver();
            xmlReader.setEntityResolver(entityResolver);
        }
        catch (Exception e)
        {
        }
        try
        {
            ServletContext servletContext = (ServletContext)pageContext.getServletContext();
            InputStream inputStream = servletContext.getResourceAsStream(xmlFileName);
				InputSource inputSource = new InputSource (inputStream);
            SAXSource sAXSource = new SAXSource(xmlReader , inputSource);
            InputStream xslStream = servletContext.getResourceAsStream(xslFileName);
            Source xslSource = new StreamSource(xslStream);
            Transformer transformer = tFactory.newTransformer(xslSource);
            Result result = new StreamResult(stringWriter);
            transformer.transform(sAXSource, result);
        }
        catch (Exception e)
        {
            stringWriter = null;
        }
        System.setProperty("org.xml.sax.driver" , originalValue);

%>

<%
    request.setAttribute ("HelpTransformation", stringWriter);
%> 
