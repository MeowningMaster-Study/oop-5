package xsd_works.parser;

import xsd_works.model.Papers;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class StAXParser {
    public static Papers apply(String xmlPath) throws XMLStreamException, FileNotFoundException {
        var xml = new File(xmlPath);

        var xmlInputFactory = XMLInputFactory.newInstance();

        var handler = new Handler();
        var reader = xmlInputFactory.createXMLEventReader(new FileInputStream(xml));

        while (reader.hasNext()) {
            var nextXMLEvent = reader.nextEvent();

            if (nextXMLEvent.isStartElement()) {
                var startElement = nextXMLEvent.asStartElement();
                nextXMLEvent = reader.nextEvent();

                String name = startElement.getName().getLocalPart();
                if (nextXMLEvent.isCharacters()) {
                    var attributesList = new ArrayList<Attribute>();
                    var iter = startElement.getAttributes();

                    while (iter.hasNext()) {
                        attributesList.add(iter.next());
                    }

                    var attributeMap = new HashMap<String, String>();

                    for (var attribute : attributesList) {
                        attributeMap.put(
                                attribute.getName().getLocalPart(),
                                attribute.getValue());
                    }

                    handler.setField(name, nextXMLEvent.asCharacters().getData(), attributeMap);
                }
            }
        }
        return new Papers(handler.getPapersList());
    }
}
