package xsd_works.parser;

import org.w3c.dom.Document;
import xsd_works.model.Papers;
import xsd_works.model.Paper;
import xsd_works.model.Chars;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static xsd_works.Strings.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DOMParser {
    public static Papers apply(String xmlPath) {
        var xml = new File(xmlPath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            var documentBuilder = factory.newDocumentBuilder();
            var document = documentBuilder.parse(xml);
            document.getDocumentElement().normalize();
            var rootNode = document.getDocumentElement();
            var tariffHandler = new Handler();
            var tariffsNodesList = rootNode.getElementsByTagName(tariffHandler.getName());
            for (int tariffsNode = 0; tariffsNode < tariffsNodesList.getLength(); tariffsNode++) {
                Element tariffElement = (Element) tariffsNodesList.item(tariffsNode);
                traverseNodes(tariffElement, tariffHandler);
            }
            return new Papers(tariffHandler.getPapersList());
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }
    private static void traverseNodes(Node node, Handler tariffHandler) {
        if(node.getNodeType() == Node.ELEMENT_NODE) {
            Map<String, String> attributes = new HashMap<>();
            if(node.getAttributes() != null) {
                for (int i = 0; i < node.getAttributes().getLength(); i++) {
                    attributes.put(node.getAttributes().item(i).getNodeName(),
                        node.getAttributes().item(i).getTextContent());
                }
            }
            tariffHandler.setField(node.getNodeName(), node.getTextContent(), attributes);
            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                traverseNodes(node.getChildNodes().item(i), tariffHandler);
            }
        }
    }
}
