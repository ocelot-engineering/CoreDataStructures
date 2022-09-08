import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AnalyzerTest {

    @Test
    void readFile() {
        // Read in test file
        String path = "resources/test/reviews.txt";
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
        // Read in test file
        String path = "resources/test/reviewsSmall.txt";
        List<Sentence> sentences = Analyzer.readFile(path);
        Set<Word> wordSet = Analyzer.allWords(sentences);

        // Check set is not empty
        assertFalse(wordSet.isEmpty());

        // Build testing objects - these will be contained in the set
        Word wordPositive = new Word("positive");
        Word wordNegative = new Word("negative");
        Word wordWords = new Word("words");

        wordPositive.increaseTotal(2);
        wordNegative.increaseTotal(-1);
        wordWords.increaseTotal(2);
        wordWords.increaseTotal(-1);

        // Check resulting set contains testing objects
        assertTrue(wordSet.contains(wordPositive));
        assertTrue(wordSet.contains(wordNegative));
        assertTrue(wordSet.contains(wordWords));
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