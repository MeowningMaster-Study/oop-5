package xsd_works;

import lombok.SneakyThrows;
import xsd_works.parser.SAXParser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SAXParserTest {
    @Test
    @SneakyThrows
    public void parseTest() {
        var papers = SAXParser.apply(Main.XMLPath).getPapers();
        assertEquals(papers.size(), 3);
        assertEquals(papers.get(0), MockData.paper);
    }
}
