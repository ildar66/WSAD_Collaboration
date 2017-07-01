package ru.trailcom.rchs2nri.sax;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import ru.trailcom.rchs2nri.domain.Declaration;

import java.io.CharArrayWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DocumentHandler  extends DefaultHandler {
	
	protected Log logger = LogFactory.getLog(this.getClass());
	
	XMLReader parser;
	private List declarations = new ArrayList();
	
	private DeclarationHandler declarationHandler = new DeclarationHandler();
	
	private CharArrayWriter contents = new CharArrayWriter();

	
	public DocumentHandler( XMLReader parser ) {
		super();
        this.parser = parser;
	}
	
	public void startElement( String namespaceURI, String localName, String qName, Attributes attr ) throws SAXException {
		contents.reset();
		
		if ( localName.equals( "Declaration" ) ) {
            Declaration aDeclaration = new Declaration();
            //System.out.println("adding Declaration");
            declarations.add(aDeclaration);
            declarationHandler.collectDeclaration( parser, this, aDeclaration);
		}
	}
	public void endElement( String namespaceURI,
             				String localName,
             				String qName ) throws SAXException {

		// Nothing left for the Example 6 mapper
		// 	to handle in the endElement SAX event.
	}

	public void characters( char[] ch, int start, int length ) throws SAXException {
		// accumulate the contents into a buffer.
		contents.write( ch, start, length );
		//System.out.println(contents.toString());

	}
	
	//inner class
	//Dorg.xml.sax.driver=org.apache.xerces.parsers.SAXParser
	public static void main( String[] argv ){
	      try {
	         // Create SAX 2 parser...
	         XMLReader xr = new org.apache.xerces.parsers.SAXParser();

	         // Set the ContentHandler...
	         DocumentHandler documentHandler = new DocumentHandler(xr);
	         xr.setContentHandler( documentHandler );
	         Log logger = LogFactory.getLog(documentHandler.getClass());
	         // Parse the file...
	         xr.parse(new InputSource(new FileReader("c:\\java\\project\\BS.xml")));
	         logger.info("count="+documentHandler.getDeclarations().size());
	         logger.info(documentHandler.getDeclarations());
	      }catch (Exception e) {
	    	  e.printStackTrace();
	      }
	}
	
	public List getDeclarations() {
		return declarations;
	}

}
