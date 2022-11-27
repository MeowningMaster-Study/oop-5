package xsd_works;

import xsd_works.parser.DOMParser;
import xsd_works.parser.SAXParser;
import xsd_works.parser.StAXParser;

public class Main {
    static String XMLPath = "src/main/resources/xsd_works/papers.xml";
    static String XSDPath = "src/main/resources/xsd_works/papers.xsd";

    public static void main(String[] args) throws Exception {
        {
            System.out.println("DOM");
            var parser = new DOMParser();
            var papers = parser.parseXml(XMLPath, XSDPath);
            System.out.println(papers);
            System.out.println();
        }

        {
            System.out.println("StAX");
            var parser = new StAXParser();
            var papers = parser.parseXml(XMLPath);
            System.out.println(papers);
            System.out.println();
        }

        {
            System.out.println("SAX");
            var parser = new SAXParser();
            var papers = parser.parseXml(XMLPath);
            System.out.println(papers);
            System.out.println();
        }
    }
}
