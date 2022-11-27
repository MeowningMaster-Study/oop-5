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
            var papers = DOMParser.apply(XMLPath, XSDPath);
            System.out.println(papers);
            System.out.println();
        }

        {
            System.out.println("StAX");
            var papers = StAXParser.apply(XMLPath);
            System.out.println(papers);
            System.out.println();
        }

        {
            System.out.println("SAX");
            var papers = SAXParser.apply(XMLPath);
            System.out.println(papers);
            System.out.println();
        }
    }
}
