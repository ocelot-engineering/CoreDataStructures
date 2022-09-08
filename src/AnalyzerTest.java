import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnalyzerTest {

    @Test
    void readFile() {
        // Read in test file
        String path = "resources/reviews.txt";
        List<Sentence> sentences = Analyzer.readFile(path);

        // Check scores
        assertEquals(-1, sentences.get(6).getScore());
        assertEquals(2, sentences.get(1).getScore());

        // Check text
        String text6 = "Narratively , Trouble Every Day is a plodding mess .";
        String text1 = "This quiet , introspective and entertaining independent is worth seeking .";
        assertEquals(text6, sentences.get(6).getText());
        assertEquals(text1, sentences.get(1).getText());
    }

    @Test
    void allWords() {
        fail("Not yet implemented.");
    }

    @Test
    void calculateScores() {
        fail("Not yet implemented.");
    }

    @Test
    void calculateSentenceScore() {
        fail("Not yet implemented.");
    }
}