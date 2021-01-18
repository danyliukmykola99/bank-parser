package danyliuk.mykola;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class App {
    public static void main(String[] arrgs) {
        App app = new App();
        app.parserXml();
    }

    public XMLReader parserXml() {
        XMLReader xmlReader = null;
        try {
            SAXParserFactory spfactory = SAXParserFactory.newInstance();
            SAXParser saxParser = spfactory.newSAXParser();
            xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(new XmlReader());
            InputSource source = new InputSource("Java_test.xml");
            xmlReader.parse(source);
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
        return xmlReader;
    }

}
