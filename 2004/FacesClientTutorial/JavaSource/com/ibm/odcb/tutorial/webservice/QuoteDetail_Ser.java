/**
 * QuoteDetail_Ser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * 4
 */

package com.ibm.odcb.tutorial.webservice;

public class QuoteDetail_Ser extends com.ibm.ws.webservices.engine.encoding.ser.BeanSerializer {
    /**
     * Constructor
     */
    public QuoteDetail_Ser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    public void serialize(
        javax.xml.namespace.QName name,
        org.xml.sax.Attributes attributes,
        java.lang.Object value,
        com.ibm.ws.webservices.engine.encoding.SerializationContext context)
        throws java.io.IOException
    {
        context.startElement(name, addAttributes(attributes,value,context));
        addElements(value,context);
        context.endElement();
    }
    protected org.xml.sax.Attributes addAttributes(
        org.xml.sax.Attributes attributes,
        java.lang.Object value,
        com.ibm.ws.webservices.engine.encoding.SerializationContext context)
        throws java.io.IOException
    {
        return attributes;
    }
    protected void addElements(
        java.lang.Object value,
        com.ibm.ws.webservices.engine.encoding.SerializationContext context)
        throws java.io.IOException
    {
        QuoteDetail bean = (QuoteDetail) value;
        java.lang.Object propValue;
        javax.xml.namespace.QName propQName;
        {
          propQName = QName_2_13;
          propValue = new Float(bean.getChange());
          context.serialize(propQName, null, 
              propValue, 
              QName_1_22,
              true,null);
          propQName = QName_2_14;
          propValue = bean.getDate();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            context.serialize(propQName, null, 
              propValue, 
              QName_1_12,
              true,null);
          }
          propQName = QName_2_15;
          propValue = new Float(bean.getHigh());
          context.serialize(propQName, null, 
              propValue, 
              QName_1_22,
              true,null);
          propQName = QName_2_16;
          propValue = new Float(bean.getLastPrice());
          context.serialize(propQName, null, 
              propValue, 
              QName_1_22,
              true,null);
          propQName = QName_2_17;
          propValue = new Float(bean.getLow());
          context.serialize(propQName, null, 
              propValue, 
              QName_1_22,
              true,null);
          propQName = QName_2_18;
          propValue = new Float(bean.getOpen());
          context.serialize(propQName, null, 
              propValue, 
              QName_1_22,
              true,null);
          propQName = QName_2_19;
          propValue = bean.getSymbol();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            context.serialize(propQName, null, 
              propValue, 
              QName_1_12,
              true,null);
          }
          propQName = QName_2_20;
          propValue = bean.getTime();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            context.serialize(propQName, null, 
              propValue, 
              QName_1_12,
              true,null);
          }
          propQName = QName_2_21;
          propValue = new Float(bean.getVolume());
          context.serialize(propQName, null, 
              propValue, 
              QName_1_22,
              true,null);
        }
    }
        public final static javax.xml.namespace.QName QName_2_20 = 
               com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                      "http://webservice.tutorial.odcb.ibm.com",
                      "time");
        public final static javax.xml.namespace.QName QName_2_16 = 
               com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                      "http://webservice.tutorial.odcb.ibm.com",
                      "lastPrice");
        public final static javax.xml.namespace.QName QName_2_15 = 
               com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                      "http://webservice.tutorial.odcb.ibm.com",
                      "high");
        public final static javax.xml.namespace.QName QName_2_19 = 
               com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                      "http://webservice.tutorial.odcb.ibm.com",
                      "symbol");
        public final static javax.xml.namespace.QName QName_2_17 = 
               com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                      "http://webservice.tutorial.odcb.ibm.com",
                      "low");
        public final static javax.xml.namespace.QName QName_2_14 = 
               com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                      "http://webservice.tutorial.odcb.ibm.com",
                      "date");
        public final static javax.xml.namespace.QName QName_1_22 = 
               com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                      "http://www.w3.org/2001/XMLSchema",
                      "float");
        public final static javax.xml.namespace.QName QName_2_18 = 
               com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                      "http://webservice.tutorial.odcb.ibm.com",
                      "open");
        public final static javax.xml.namespace.QName QName_2_21 = 
               com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                      "http://webservice.tutorial.odcb.ibm.com",
                      "volume");
        public final static javax.xml.namespace.QName QName_1_12 = 
               com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                      "http://www.w3.org/2001/XMLSchema",
                      "string");
        public final static javax.xml.namespace.QName QName_2_13 = 
               com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                      "http://webservice.tutorial.odcb.ibm.com",
                      "change");
}
