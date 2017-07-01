package ru.trailcom.rchs2nri.sax;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import ru.trailcom.rchs2nri.domain.Declaration;
import ru.trailcom.rchs2nri.domain.License;

import java.io.CharArrayWriter;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;


public class LicenseHandler  extends DefaultHandler {
	protected final static String ALIAS = "Licens";
	protected final static String TEMP_ALIAS = "templicens";
	
	public final static String BS_ALIAS = "BS_Lic";
	//public final static String TEMP_BS_ALIAS = "tLIC";
	
//	Local current circle reference...
    private License  currentLicense;
    private Declaration  currentDeclaration;

    
    // Parent...
    ContentHandler parent;

    // 	XML Parser
    XMLReader parser;
    
    String endPrefix;

    // Buffer for collecting data from
    // 	the "characters" SAX event.
    private CharArrayWriter contents = new CharArrayWriter();
    
	private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");


    protected void collect( XMLReader parser,
           ContentHandler parent,
           License newItem, Declaration aCurrentDeclaration, String aPrefix ) {
    	this.parent = parent;
    	this.parser = parser;
    	parser.setContentHandler( this );
    	currentLicense = newItem;
    	currentDeclaration = aCurrentDeclaration;
    	endPrefix = aPrefix;
    }
    
    public void startElement( String namespaceURI,
            String localName,
           String qName,
           Attributes attr ) throws SAXException {

    	contents.reset();
    	//
    	if ( localName.equals( LicenseHandler.BS_ALIAS ) ) {
	        BaseStationList.addBaseStation(attr.getValue( "ID_BS_ZAJ" ),  currentDeclaration.getBaseStations(), currentLicense.getLicensedBaseStations());
		}
    }

    public void endElement( String namespaceURI,
            			String localName,
            			String qName ) throws SAXException {
    	if ( localName.equals( "License_Number" ) ) {
    		currentLicense.setNumber(contents.toString().trim());
        }
    	
    	if ( localName.equals( "Receive_date" ) ) {
    		try{
    			currentLicense.setReceiveDate(sdf.parse(contents.toString().trim(), new ParsePosition(0)));
    		}catch (Exception e) {
				e.printStackTrace();
			}
        }
    	
    	//<IsTemporary>False</IsTemporary>
    	if ( localName.equals( "IsTemporary" ) ) {
   			if(contents.toString().trim().equals("False")){
   				currentLicense.setIsTemporary(new Boolean(false));
   			}else{
   				currentLicense.setIsTemporary(new Boolean(true));
   			}
    	}
    	
    	if ( localName.equals( "Realise_Date" ) ) {
    		try{
    			currentLicense.setRealiseDate(sdf.parse(contents.toString().trim(), new ParsePosition(0)));
    		}catch (Exception e) {
				e.printStackTrace();
			}
        }
    	
    	if ( localName.equals( "Expire_date" ) &&  contents.toString().trim().length()>0) {
    		try{
    			currentLicense.setExpireDate(sdf.parse(contents.toString().trim(), new ParsePosition(0)));
    		}catch (Exception e) {
				e.printStackTrace();
			}
        }
    	
    	//end of DECISION
    	if ( localName.equals( endPrefix) ) {
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
