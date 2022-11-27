package xsd_works.parser;

import xsd_works.model.Papers;
import xsd_works.model.Paper;
import xsd_works.model.Chars;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static xsd_works.Strings.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DOMParser {
    public static Papers apply(String xmlPath, String xsdPath)
            throws ParserConfigurationException, IOException, SAXException {
        var dbf = DocumentBuilderFactory.newInstance();
        var db = dbf.newDocumentBuilder();
        var doc = db.parse(new File(xmlPath));
        var paperNodeList = doc.getElementsByTagName(PAPER);
        var papers = new ArrayList<Paper>();
        for (int i = 0; i < paperNodeList.getLength(); i++) {
            var node = paperNodeList.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE)
                continue;
            var element = (Element) node;
            papers.add(createPaper(element));
        }
        return new Papers(papers);
    }

    private static Paper createPaper(Element element) {
        int id = Integer.parseInt(element.getAttribute(ID));
        String title = getByTag(element, TITLE);
        String type = getByTag(element, TYPE);
        boolean monthly = Boolean.parseBoolean(getByTag(element, MONTHLY));
        var chars = (Element) element.getElementsByTagName(CHARS).item(0);
        return new Paper(id, title, type, monthly, createChars(chars));
    }

    private static Chars createChars(Element element) {
        boolean colored = Boolean.parseBoolean(getByTag(element, COLORED));
        int pagesCount = Integer.parseInt(getByTag(element, PAGES_COUNT));
        boolean glossy = Boolean.parseBoolean(getByTag(element, GLOSSY));
        String subscriptionIndex = getByTag(element, SUBSCRIPTION_INDEX);
        return new Chars(colored, pagesCount, glossy, subscriptionIndex);
    }

    private static String getByTag(Element element, String tag) {
        return element.getElementsByTagName(tag).item(0).getTextContent();
    }
}
