/**
 * QuoteDetail_Helper.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * 4
 */

package com.ibm.odcb.tutorial.webservice;

public class QuoteDetail_Helper {
    // Type metadata
    private static com.ibm.ws.webservices.engine.description.TypeDesc typeDesc =
        new com.ibm.ws.webservices.engine.description.TypeDesc(QuoteDetail.class);

    static {
        com.ibm.ws.webservices.engine.description.FieldDesc field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("change");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://webservice.tutorial.odcb.ibm.com", "change"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "float"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("date");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://webservice.tutorial.odcb.ibm.com", "date"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("high");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://webservice.tutorial.odcb.ibm.com", "high"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "float"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("lastPrice");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://webservice.tutorial.odcb.ibm.com", "lastPrice"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "float"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("low");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://webservice.tutorial.odcb.ibm.com", "low"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "float"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("open");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://webservice.tutorial.odcb.ibm.com", "open"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "float"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("symbol");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://webservice.tutorial.odcb.ibm.com", "symbol"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("time");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://webservice.tutorial.odcb.ibm.com", "time"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("volume");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://webservice.tutorial.odcb.ibm.com", "volume"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "float"));
        typeDesc.addFieldDesc(field);
    };

    /**
     * Return type metadata object
     */
    public static com.ibm.ws.webservices.engine.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static com.ibm.ws.webservices.engine.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class javaType,  
           javax.xml.namespace.QName xmlType) {
        return 
          new QuoteDetail_Ser(
            javaType, xmlType, typeDesc);
    };

    /**
     * Get Custom Deserializer
     */
    public static com.ibm.ws.webservices.engine.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class javaType,  
           javax.xml.namespace.QName xmlType) {
        return 
          new QuoteDetail_Deser(
            javaType, xmlType, typeDesc);
    };

}
