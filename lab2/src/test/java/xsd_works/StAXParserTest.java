package xsd_works;

import lombok.SneakyThrows;
import xsd_works.parser.StAXParser;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StAXParserTest {
    @Test
    @SneakyThrows
    public void parseTest() {
        var papers = StAXParser.apply(Main.XMLPath).getPapers();
        assertEquals(papers.size(), 3);
        assertEquals(papers.get(0), MockData.paper);
    }
}
