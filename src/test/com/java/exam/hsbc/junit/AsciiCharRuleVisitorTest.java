package test.com.java.exam.hsbc.junit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.java.exam.hsbc.rules.visitor.AsciiCharRuleVisitor;
import com.java.exam.hsbc.rules.visitor.StringItem;

public class AsciiCharRuleVisitorTest {

    @Test
    public void testVisitWithAsciiString() {
       
        AsciiCharRuleVisitor visitor = new AsciiCharRuleVisitor();
        StringItem stringItem = new StringItem("ASCII String");

       
        try {
            visitor.visit(stringItem);
            // If no exception is thrown, the test passes
        } catch (UnsupportedEncodingException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testVisitWithNonAsciiString() {
        
        AsciiCharRuleVisitor visitor = new AsciiCharRuleVisitor();
        StringItem stringItem = new StringItem("Non-ASCII String: ©");

        
        try {
            visitor.visit(stringItem);
            fail("Expected UnsupportedEncodingException was not thrown");
        } catch (UnsupportedEncodingException e) {
            // If an UnsupportedEncodingException is thrown, the test passes
            assertTrue(true);
        }
    }
}
