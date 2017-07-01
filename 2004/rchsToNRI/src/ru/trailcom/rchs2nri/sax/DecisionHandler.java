package ru.trailcom.rchs2nri.sax;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import ru.trailcom.rchs2nri.domain.Decision;
import ru.trailcom.rchs2nri.domain.Declaration;

import java.io.CharArrayWriter;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;


public class DecisionHandler  extends DefaultHandler {
	public final static String ALIAS = "Desision";
	
	public final static String BS_ALIAS = "BS_DES";
	
	private Declaration  currentDeclaration;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	
	
//	Local current circle reference...
    private Decision  currentDecision;

    // Parent...
    ContentHandler parent;

    // 	XML Parser
    XMLReader parser;

    // Buffer for collecting data from
    // 	the "characters" SAX event.
    private CharArrayWriter contents = new CharArrayWriter();
    
    
    private MainLicenseHandler licenseHandler = new MainLicenseHandler();
    
    protected void collect( XMLReader parser, ContentHandler parent, Decision newDecision, Declaration  aCurrentDeclaration) {
    	this.parent = parent;
    	this.parser = parser;
    	parser.setContentHandler( this );
    	currentDecision = newDecision;
    	this.currentDeclaration = aCurrentDeclaration;
    }
    
    protected Log logger = LogFactory.getLog(this.getClass());
    
    public void startElement( String namespaceURI, String localName, String qName, Attributes attr ) throws SAXException {

    	contents.reset();

    	if ( localName.equals( DecisionHandler.BS_ALIAS ) ) {
    		BaseStationList.addBaseStation(attr.getValue( "ID_BS_ZAJ" ), currentDeclaration.getBaseStations(), currentDecision.getListBaseStation());
		}

    	if ( localName.equals( MainLicenseHandler.ALIAS_LICENSES ) ) {
			//System.out.println(MainLicenseHandler.ALIAS_LICENSES );
	        licenseHandler.collectLicenses( parser, this, currentDecision.getLicenses(), MainLicenseHandler.ALIAS_LICENSES, currentDeclaration);
		}
    }

    public void endElement( String namespaceURI,
            			String localName,
            			String qName ) throws SAXException {
    	if ( localName.equals( "Decision_number" ) ) {
    		currentDecision.setNumber(contents.toString().trim());
        }
    	
    	if ( localName.equals( "Decision_date" ) && contents.toString().trim().length()>0) {
    		try{
    			currentDecision.setDate(sdf.parse(contents.toString().trim(), new ParsePosition(0)));
    		}catch (Exception e) {
				e.printStackTrace();
			}
        }
    	
    	if ( localName.equals( "Number_Rossv_vh" ) ) {
    		currentDecision.setNumberRossvIn(contents.toString().trim());
        }
    	
    	if ( localName.equals( "Date_Rossv_vh" ) && contents.toString().trim().length()>0) {
    		try{
    			currentDecision.setDateRossvIn(sdf.parse(contents.toString().trim(), new ParsePosition(0)));
    		}catch (Exception e) {
				e.printStackTrace();
			}
        }
    	if ( localName.equals( "Number_Rossv_ish" ) ) {
    		currentDecision.setNumberRossvOut(contents.toString().trim());
        }
    	
    	if ( localName.equals( "Date_Rossv_ish" ) && contents.toString().trim().length()>0) {
    		try{
    			currentDecision.setDateRossvOut(sdf.parse(contents.toString().trim(), new ParsePosition(0)));
    		}catch (Exception e) {
				e.printStackTrace();
			}
        }
    	
    	if ( localName.equals( "Date_Licence_Prognoz" ) ) {
    		try{
    			currentDecision.setDateLicensePrognoses(sdf.parse(contents.toString().trim(), new ParsePosition(0)));
    		}catch (Exception e) {
				e.printStackTrace();
			}
        }
    	
    	//end of DECISION
    	if ( localName.equals( DecisionHandler.ALIAS ) ) {
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
