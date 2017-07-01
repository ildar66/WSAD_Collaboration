package ru.trailcom.rchs2nri.sax;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.regexp.RE;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import ru.trailcom.rchs2nri.domain.BaseStation;
import ru.trailcom.rchs2nri.domain.Decision;
import ru.trailcom.rchs2nri.domain.Declaration;

import java.io.CharArrayWriter;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

public class DeclarationHandler extends DefaultHandler {
	private static final String ALIAS = "Declaration";
	//Local current circle reference...
    private Declaration   currentDeclaration;

    // Parent...
    ContentHandler parent;

    // 	XML Parser
    XMLReader parser;

    // Buffer for collecting data from
    // 	the "characters" SAX event.
    private CharArrayWriter contents = new CharArrayWriter();
    
    private BaseStationHandler baseStationHandler = new BaseStationHandler();
    private DecisionHandler decisionHandler = new DecisionHandler();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

	//private MainLicenseHandler licenseHandler = new MainLicenseHandler();


    protected void collectDeclaration( XMLReader parser, ContentHandler parent, Declaration newDeclaration ) {
    	this.parent = parent;
    	this.parser = parser;
    	parser.setContentHandler( this );
    	currentDeclaration = newDeclaration;
    }
    
    public void startElement( String namespaceURI, String localName, String qName, Attributes attr ) throws SAXException {
    	contents.reset();
    	
    	if ( localName.equals( BaseStationHandler.ALIAS ) ) {
            BaseStation aBaseStation = new BaseStation();
            //aCircle.name = attr.getValue( "name" );
            currentDeclaration.getBaseStations().add(aBaseStation);
            baseStationHandler.collect( parser, this, aBaseStation);
		}
    	
    	if ( localName.equals( DecisionHandler.ALIAS ) ) {
	        Decision aDecision = new Decision();
	        aDecision.setDeclaration(currentDeclaration);
	        //aCircle.name = attr.getValue( "name" );
	        currentDeclaration.getDecisions().add(aDecision);
	        decisionHandler.collect( parser, this, aDecision, currentDeclaration);
    	}

//		
//		if ( localName.equals( MainLicenseHandler.ALIAS_TMP_LICENSES ) ) {
//	        licenseHandler.collectLicenses( parser, this, currentDeclaration.getLicensesTemp(), MainLicenseHandler.ALIAS_TMP_LICENSES, currentDeclaration);
//		}
		//Lic
    	
    }

    protected Log logger = LogFactory.getLog(this.getClass());
    
    public void endElement( String namespaceURI,
            			String localName,
            			String qName ) throws SAXException {
    	if ( localName.equals( "Id_Declaration" ) ) {
    		currentDeclaration.setId(new Integer(contents.toString().trim()));
        }
    	if ( localName.equals( "Decl_Definition" ) ) {
    		currentDeclaration.setDefinition(contents.toString().trim());
        }
    	if ( localName.equals( "diap" ) ) {
    		RE re = new RE("(\\d+)");
    		boolean match = re.match(contents.toString().trim());
    		String diap = "";
    		if(match){
    			diap = re.getParen(0);
    		}
    		logger.info(diap);
    		currentDeclaration.setDiap(new Integer(diap));
        }
    	if ( localName.equals( "Count_Bs_in_decl" ) ) {
    		//currentDeclaration.setDiap();
        }
    	if ( localName.equals( "Company" ) ) {
    		currentDeclaration.setCompany(contents.toString().trim());
        }
    	if ( localName.equals( "Region" ) ) {
    		currentDeclaration.setRegion(contents.toString().trim());
        }
    	if ( localName.equals( "Date_From_Projecter" ) ) {
    		try{
    			currentDeclaration.setProjectDate(sdf.parse(contents.toString().trim(), new ParsePosition(0)));
    		}catch (Exception e) {
				e.printStackTrace();
			}
        }   
    	
    	if ( localName.equals( "Number_GRCC_ish" ) ) {
    		currentDeclaration.setNumberGRCCout(contents.toString().trim());
        }
    	
    	if ( localName.equals( "Date_GRCC_ish" ) ) {
    		try{
    			currentDeclaration.setDateGRCCout(sdf.parse(contents.toString().trim(), new ParsePosition(0)));
    		}catch (Exception e) {
				e.printStackTrace();
			}
        }
    	 	
    	if ( localName.equals( "Number_GRCC_vhod" ) ) {
    		currentDeclaration.setNumberGRCCin(contents.toString());
        }

    	if ( localName.equals( "Date_GRCC_vhod" ) ) {
    		try{
    			currentDeclaration.setDateGRCCin(sdf.parse(contents.toString().trim(), new ParsePosition(0)));
    		}catch (Exception e) {
				e.printStackTrace();
			}
        }

    	if ( localName.equals( "Date_decision_prognoz" ) ) {
    		try{
    			currentDeclaration.setDateDecisionPrognosis(sdf.parse(contents.toString().trim(), new ParsePosition(0)));
    		}catch (Exception e) {
				e.printStackTrace();
			}
        }
    	//end of declaration
    	if ( localName.equals( DeclarationHandler.ALIAS ) ) {
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