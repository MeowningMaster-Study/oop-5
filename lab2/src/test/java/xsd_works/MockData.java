package xsd_works;

import xsd_works.model.Chars;
import xsd_works.model.Paper;

public class MockData {
    static Paper paper = new Paper(
            1,
            "The Times",
            "magazine",
            true,
            new Chars(
                    true,
                    15,
                    true,
                    "16AB43"));
}
