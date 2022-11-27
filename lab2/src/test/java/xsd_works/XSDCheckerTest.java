package xsd_works;

import xsd_works.parser.XSDChecker;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class XSDCheckerTest {

    @Test
    public void ValidateTest() {
        boolean valid = XSDChecker.apply(Main.XMLPath, Main.XSDPath);
        assertTrue(valid);
    }
}
