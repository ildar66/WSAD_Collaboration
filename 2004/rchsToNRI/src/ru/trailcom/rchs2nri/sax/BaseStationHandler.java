package ru.trailcom.rchs2nri.sax;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import ru.trailcom.rchs2nri.domain.BaseStation;

import java.io.CharArrayWriter;
import java.math.BigDecimal;

public class BaseStationHandler  extends DefaultHandler {
	public final static String ALIAS = "BS";
//	Local current circle reference...
    private BaseStation  currentBaseStation;

    // Parent...
    ContentHandler parent;

    // 	XML Parser
    XMLReader parser;

    // Buffer for collecting data from
    // 	the "characters" SAX event.
    private CharArrayWriter contents = new CharArrayWriter();


    protected void collect( XMLReader parser,
           ContentHandler parent,
           BaseStation newBaseStation ) {
    	this.parent = parent;
    	this.parser = parser;
    	parser.setContentHandler( this );
    	currentBaseStation = newBaseStation;
    }
    
    public void startElement( String namespaceURI, String localName, String qName, Attributes attr ) throws SAXException {
    	contents.reset();
    }

    public void endElement( String namespaceURI, String localName, String qName ) throws SAXException {
    	if ( localName.equalsIgnoreCase( "ID_BS_BD" ) ) {
    		currentBaseStation.setIdBsBd(new Integer(contents.toString().trim()));
        }

        if ( localName.equalsIgnoreCase( "Id_BS_Zaj" ) ) {
            currentBaseStation.setIdBsZaj(new Integer(contents.toString().trim()));
        }

        //TODO: here
        if ( localName.equalsIgnoreCase( "BS_ZAJ_number" ) && contents.toString().trim().length()>0) {
    		try{
    			currentBaseStation.setNumber(new Integer(contents.toString().trim()));
    		}catch (NumberFormatException e) {
    			e.printStackTrace();
			}
        }

        if ( localName.equalsIgnoreCase( "BS_BD_number" ) ) {
            currentBaseStation.setBsBdNumber(new Integer(contents.toString().trim()));
        }

        if ( localName.equalsIgnoreCase( "BS_name" ) ) {
    		currentBaseStation.setName(contents.toString().trim());
        }
    	
    	if ( localName.equalsIgnoreCase( "BS_address" ) ) {
    		currentBaseStation.setAddress(contents.toString().trim());
        }
    	
    	if ( localName.equalsIgnoreCase( "latitude" ) ) {
    		currentBaseStation.setLatitude(new BigDecimal(contents.toString().trim().replace(',', '.')));
        }
    	
       	if ( localName.equalsIgnoreCase( "longitude" ) ) {
    		currentBaseStation.setLongitude(new BigDecimal(contents.toString().trim().replace(',', '.')));
        }
    	//end of BS
    	if ( localName.equalsIgnoreCase( BaseStationHandler.ALIAS ) ) {
    		// swap content handler back to parent
    		parser.setContentHandler(parent);
    	 }

    }
    
    public void characters( char[] ch, int start, int length ) throws SAXException {
		// accumulate the contents into a buffer.
		contents.write( ch, start, length );
		//System.out.println(contents.toString());
	}

}
