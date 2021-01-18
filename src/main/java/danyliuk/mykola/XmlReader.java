package danyliuk.mykola;

import danyliuk.mykola.dao.TransactionDao;
import danyliuk.mykola.model.Transaction;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlReader extends DefaultHandler {

    XmlReader xmlReader;

    String thisElement = "";

    Transaction transaction = new Transaction();

    TransactionDao transactionDao = new TransactionDao();

    public XmlReader() {

    }

    public XmlReader(XmlReader reader) {
        xmlReader = reader;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Tallying xml results...");
    }

    @Override
    public void startElement(String namespaceURI, String localName,
                             String qName, Attributes atts) throws SAXException {

        thisElement = qName;

    }

    @Override
    public void endElement(String uri, String localName,
                           String qName) throws SAXException {
        thisElement = "";

    }


    public void characters(char[] ch, int start, int length)
            throws SAXException {

        final String value = new String(ch, start, length);

        nextElement(value);

    }

    public void nextElement(String value) {
        final String result = value;
        if (thisElement.equals("card")) {
            transaction.setCard(result);
        } else if (thisElement.equals("amount")) {
            Double valOfDouble = new Double(result);
            transaction.setAmount(valOfDouble);
        } else if (thisElement.equals("place")) {
            transaction.setPlace(result);
        } else if (thisElement.equals("currency")) {
            transaction.setCurrency(result);
        } else if (thisElement.equals("firstName")) {
            transaction.setFirstName(result);
        } else if (thisElement.equals("lastName")) {
            transaction.setLastName(result);
        } else if (thisElement.equals("middleName")) {
            transaction.setMiddleName(result);
        } else if (thisElement.equals("inn")) {
            Integer valOfInt = new Integer(result);
            transaction.setInn(valOfInt);
            transactionSaveDB(transaction);
            System.out.println(transaction);
        } else {
            if (result == null) {
                Exception ex = new NullPointerException();
                ex.printStackTrace();
            }
            if (result.isEmpty()) {
                System.err.println("Read result is not empty!");
            }
        }
    }

    public void transactionSaveDB(Transaction trans) {
        if (trans != null) {
            transactionDao.save(trans);
        }
    }
}
