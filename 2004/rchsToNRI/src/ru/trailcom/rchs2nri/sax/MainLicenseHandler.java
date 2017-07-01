

package ru.trailcom.rchs2nri.sax;

import java.io.CharArrayWriter;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import ru.trailcom.rchs2nri.domain.Declaration;
import ru.trailcom.rchs2nri.domain.License;

public class MainLicenseHandler extends DefaultHandler {
	protected static final String ALIAS_LICENSES = "LICENSES";
	protected static final String ALIAS_TMP_LICENSES = "templicens";
	//Local current circle reference...
    private List licenses;
    private Declaration currentDeclaration;
    
    // Parent...
    ContentHandler parent;

    // 	XML Parser
    XMLReader parser;

    // Buffer for collecting data from
    // 	the "characters" SAX event.
    private CharArrayWriter contents = new CharArrayWriter();
    
    private LicenseHandler licenseHandler = new LicenseHandler();

    private String prefix = ALIAS_LICENSES;

    protected void collectLicenses( XMLReader parser,
           ContentHandler parent, List objects, String prefix, Declaration aDeclaration ) {
    	this.parent = parent;
    	this.parser = parser;
    	parser.setContentHandler( this );
    	this.prefix = prefix; 
    	licenses = objects;
    	currentDeclaration = aDeclaration;
    }
    
    public void startElement( String namespaceURI,
            String localName,
           String qName,
           Attributes attr ) throws SAXException {
    	contents.reset();
    	
    
		if ( localName.equals(LicenseHandler.ALIAS) ) {
	        License aLicense = new License();
	        //aCircle.name = attr.getValue( "name" );
	        licenses.add(aLicense);
	        licenseHandler.collect( parser, this, aLicense, currentDeclaration, LicenseHandler.ALIAS);
		}
		
//		if ( localName.equals(LicenseHandler.TEMP_ALIAS) ) {
//	        License aLicense = new License();
//	        //aCircle.name = attr.getValue( "name" );
//	        licenses.add(aLicense);
//	        licenseHandler.collect( parser, this, aLicense, currentDeclaration, LicenseHandler.TEMP_ALIAS);
//		}	
    	
    }

    public void endElement( String namespaceURI,
            			String localName,
            			String qName ) throws SAXException {
    	
    	//end of declaration
    	if ( localName.equals( prefix ) ) {
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