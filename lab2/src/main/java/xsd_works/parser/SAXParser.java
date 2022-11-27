package xsd_works.parser;

import xsd_works.model.Papers;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SAXParser {

    public Papers parseXml(String xmlPath) throws SAXException, IOException, ParserConfigurationException {
        var xml = new File(xmlPath);

        var saxParserFactory = SAXParserFactory.newInstance();
        var saxParser = saxParserFactory.newSAXParser();

        var handler = new Handler();
        saxParser.parse(xml, handler);

        return new Papers(handler.getPapersList());
    }
}