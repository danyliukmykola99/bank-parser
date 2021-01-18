package danyliuk.mykola;

import org.junit.Test;
import org.mockito.Mockito;
import org.xml.sax.InputSource;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.XMLReader;

public class MainTest {

    @Test
    public void testParser() {

        XMLReader xmlReader;
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
    }

    @Test
    public void testReadXml() {
        XMLReader xmlReader = null;
        try {
            SAXParserFactory spfactory = SAXParserFactory.newInstance();
            SAXParser saxParser = spfactory.newSAXParser();
            xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(new XmlReader());
            InputSource source = new InputSource("test1.xml");
            InputSource source1 = new InputSource("test2.xml");
            InputSource source2 = new InputSource("test3.xml");
            xmlReader.parse(source);
            xmlReader.parse(source1);
            xmlReader.parse(source2);
        }catch (Exception ex){
            ex.printStackTrace();
            System.exit(1);
        }
    }


    @Test
    public void testResultParse(){
        XmlReader provider = Mockito.mock(XmlReader.class);
        XmlReader controller = new XmlReader(provider);

        final String value = "card";
        final String value1 = "10.15";
        final String value2 = "place";
        final String value3 = "";
        final String value4 = null;

        controller.nextElement(value);
        controller.nextElement(value1);
        controller.nextElement(value2);
        controller.nextElement(value3);
        controller.nextElement(value4);

        provider.nextElement(Mockito.eq(value));
        provider.nextElement(Mockito.eq(value1));
        provider.nextElement(Mockito.eq(value2));
        provider.nextElement(Mockito.eq(value3));
        provider.nextElement(Mockito.eq(value4));
    }

}
