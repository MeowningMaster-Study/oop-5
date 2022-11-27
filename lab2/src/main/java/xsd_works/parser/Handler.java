package xsd_works.parser;

import xsd_works.Strings;
import xsd_works.model.Paper;
import xsd_works.model.Chars;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Handler extends DefaultHandler {
    private String value;
    private List<Paper> papers = new ArrayList<>();

    @Override
    public void startElement(String url, String name, String attributeName, Attributes attributes) {
        switch (attributeName) {
            case Strings.PAPER -> {
                Paper paper = new Paper();
                papers.add(paper);
            }
            case Strings.CHARS -> {
                Chars chars = new Chars();
                getLastPaper().setChars(chars);
            }
        }
    }

    @Override
    public void endElement(String uri, String name, String attributeName) {
        switch (attributeName) {
            case Strings.ID -> getLastPaper().setId(Integer.parseInt(value));
            case Strings.TITLE -> getLastPaper().setTitle(value);
            case Strings.TYPE -> getLastPaper().setType(value);
            case Strings.MONTHLY -> getLastPaper().setMonthly(Boolean.parseBoolean(value));
            case Strings.COLORED -> getLastPaper().getChars().setColored(Boolean.parseBoolean(value));
            case Strings.PAGES_COUNT -> getLastPaper().getChars().setPagesCount(Integer.parseInt(value));
            case Strings.GLOSSY -> getLastPaper().getChars().setGlossy(Boolean.parseBoolean(value));
            case Strings.SUBSCRIPTION_INDEX -> getLastPaper().getChars().setSubscriptionIndex(value);
        }
    }

    public void setField(String attributeName, String str, Map<String, String> attributes) {
        switch (attributeName) {
            case Strings.PAPER -> {
                Paper paper = new Paper();
                papers.add(paper);
            }
            case Strings.ID -> getLastPaper().setId(Integer.parseInt(str));
            case Strings.TITLE -> getLastPaper().setTitle(value);
            case Strings.TYPE -> getLastPaper().setType(value);
            case Strings.MONTHLY -> getLastPaper().setMonthly(Boolean.parseBoolean(value));
            case Strings.COLORED -> getLastPaper().getChars().setColored(Boolean.parseBoolean(value));
            case Strings.PAGES_COUNT -> getLastPaper().getChars().setPagesCount(Integer.parseInt(value));
            case Strings.GLOSSY -> getLastPaper().getChars().setGlossy(Boolean.parseBoolean(value));
            case Strings.SUBSCRIPTION_INDEX -> getLastPaper().getChars().setSubscriptionIndex(value);
            case Strings.CHARS -> {
                Chars chars = new Chars();
                getLastPaper().setChars(chars);
            }

        }
    }

    public List<Paper> getPapersList() {
        return papers;
    }

    @Override
    public void startDocument() throws SAXException {
        papers = new ArrayList<>();
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        value = new String(ch, start, length);
    }

    private Paper getLastPaper() {
        return papers.get(papers.size() - 1);
    }

    public String getName() {
        return Strings.PAPER;
    }
}