
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Queue;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class HtmlValidatorTest {

    @Test
    void isValidHtml() throws IOException {
        testCorrect();
        testMissingClose();
        testCloseWrongOrder();
        testMissingOpen();
        testMultipleIssues();
    }

    // Tests are grouped by test resource and expected output.
    private void testCorrect() throws IOException {
        // Testing well formatted HTML.
        Stack<HtmlTag> actualOutput1 = isValidHtmlFromTestFile("correct1");
        Stack<HtmlTag> actualOutput2 = isValidHtmlFromTestFile("correct2");

        Stack<HtmlTag> expectedOutput = new Stack<>();

        assertEquals(expectedOutput, actualOutput1);
        assertEquals(expectedOutput, actualOutput2);
    }

    private void testMissingClose() throws IOException {
        Stack<HtmlTag> actualOutput = isValidHtmlFromTestFile("missingClose");

        Stack<HtmlTag> expectedOutput = new Stack<>();
        expectedOutput.add(new HtmlTag("html", true));
        expectedOutput.add(new HtmlTag("b", true));

        assertEquals(expectedOutput, actualOutput);
    }

    private void testCloseWrongOrder() throws IOException {
        Stack<HtmlTag> actualOutput = isValidHtmlFromTestFile("closeWrongOrder");

        Stack<HtmlTag> expectedOutput = new Stack<>();
        expectedOutput.add(new HtmlTag("b", true));
        expectedOutput.add(new HtmlTag("i", true));

        assertEquals(expectedOutput, actualOutput);
    }

    private void testMissingOpen() throws IOException {
        Stack<HtmlTag> actualOutput1 = isValidHtmlFromTestFile("missingOpen1");
        Stack<HtmlTag> actualOutput2 = isValidHtmlFromTestFile("missingOpen2");

        assertNull(actualOutput1);
        assertNull(actualOutput2);
    }

    private void testMultipleIssues() throws IOException {
        Stack<HtmlTag> actualOutput = isValidHtmlFromTestFile("multipleIssues");

        Stack<HtmlTag> expectedOutput = new Stack<>();
        expectedOutput.add(new HtmlTag("html", true));
        expectedOutput.add(new HtmlTag("head", true));
        expectedOutput.add(new HtmlTag("title", true));
        expectedOutput.add(new HtmlTag("p", true));

        assertEquals(expectedOutput, actualOutput);
    }


    // Simplify the isValidHtml() call to make tests easier to read.
    private Stack<HtmlTag> isValidHtmlFromTestFile(String testFile) throws IOException {
        String TEST_RESOURCE_DIR = "resources/test/";
        String testFilePath = TEST_RESOURCE_DIR + testFile + ".html";
        Queue<HtmlTag> tags = HtmlReader.getTagsFromHtmlFile(testFilePath);
        return HtmlValidator.isValidHtml(tags);
    }
}
