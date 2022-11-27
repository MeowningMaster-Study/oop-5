package xsd_works;

import lombok.SneakyThrows;
import xsd_works.parser.DOMParser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DOMParserTest {

    @Test
    @SneakyThrows
    public void parseTest() {
        var papers = DOMParser.apply(Main.XMLPath, Main.XSDPath).getPapers();
        assertEquals(papers.size(), 3);
        assertEquals(papers.get(0), MockData.paper);
    }
}
