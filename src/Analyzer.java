import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/*
 * SD2x Homework #3
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */
public class Analyzer {
	
	/*
	 * Implement this method in Part 1
	 */
	public static List<Sentence> readFile(String filename) {

		Scanner scanner = null;
		LinkedList<Sentence> sentences = new LinkedList<>();
		String line; // entire line includes both score and text
		int score;   // score only
		String text; // text only

		try {
			scanner = new Scanner(new BufferedReader(new FileReader(filename)));
			while (scanner.hasNext()) {
				line = scanner.nextLine();

				// If well formatted, then add sentence
				if (sentenceIsValid(line)) {
					score = Integer.parseInt(line.substring(0,2).trim());
					text = line.substring(2).trim();
					sentences.add(new Sentence(score, text));
				}
			}
		} catch (FileNotFoundException | NullPointerException e) {
			return sentences;
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}

		return sentences;
	}

	/*
	* Checking validity of line that generates sentence
	*/
	public static boolean sentenceIsValid(String line) {
		return isTextValid(line) && isScoreValid(line);
	}

	private static boolean isTextValid(String line) {
		return line.matches("^-?\\d{1}\\s\\w+.*");
	}

	private static boolean isScoreValid(String line) {
		int score = getScoreFromLine(line);
		return score <= 2 && score >= -2;
	}

	private static int getScoreFromLine(String line) {
		return Integer.parseInt(line.substring(0,2).trim());
	}
	
	/*
	 * Implement this method in Part 2
	 */
	public static Set<Word> allWords(List<Sentence> sentences) {

		// Early return for null or empty sentences
		if (sentences == null || sentences.isEmpty()) {
			return new HashSet<>();
		}

		HashMap<String, Word> words = new HashMap<>();
		Word word;
		StringTokenizer sentenceTokens;
		String wordKey;
		String[] wordKeySplit;

		for (Sentence sentence : sentences) {
			try {
				sentenceTokens = new StringTokenizer(sentence.getText());

				while (sentenceTokens.hasMoreTokens()) {
					// Clean word so it can be used as key
					wordKey = getStemmedWord(sentenceTokens.nextToken());
					if (wordKey == null || wordKey.isEmpty()) {
						continue;
					}

					// Add or update word in hashset
					if (words.containsKey(wordKey)) {
						words.get(wordKey).increaseTotal(sentence.getScore());
					} else {
						word = new Word(wordKey);
						word.increaseTotal(sentence.getScore());
						words.put(wordKey, word);
					}
				}
			} catch (NullPointerException ignored) {
			}
		}

		// Homework assignment requires to output set rather than map. Hashmap is the better data structure for this
		// functionality, so used that above and converted here for the output.
		return new HashSet<>(words.values());
	}
	
	/*
	 * Implement this method in Part 3
	 */
	public static Map<String, Double> calculateScores(Set<Word> words) {

		Map<String, Double> wordScores = new HashMap<>();

		if (words == null || words.isEmpty()) {
			return wordScores;
		}

		for (Word word : words) {
			if (word == null) {
				continue;
			}
			wordScores.put(word.getText(), word.calculateScore());
		}

		return wordScores;
	}
	
	/*
	 * Implement this method in Part 4
	 */
	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {

		if (wordScores == null || wordScores.isEmpty() || sentence == null || sentence.isEmpty()) {
			return 0;
		}

		double sentenceScoreSum = 0;
		double sentenceWordCount = 0; // includes words both in and not in the wordScores set
		StringTokenizer sentenceTokens = new StringTokenizer(sentence.toLowerCase());
		String word;

		while (sentenceTokens.hasMoreTokens()) {
			sentenceWordCount += 1;
			word = getStemmedWord(sentenceTokens.nextToken());
			if (word == null || word.isEmpty() || !wordScores.containsKey(word)) {
				continue;
			}
			sentenceScoreSum += wordScores.get(word);
		}

		// No valid word case
		if (sentenceWordCount == 0) {
			return 0;
		}

		return sentenceScoreSum / sentenceWordCount;
	}

	/*
	* Effectively stems the word and removes anything after punctuation.
	*/
	private static String getStemmedWord(String word) {
		String[] wordSplit = word.toLowerCase().trim().split("\\p{Punct}");
		if (wordSplit.length == 0) {
			return null;
		}
		return wordSplit[0].trim();
	}
	
	/*
	 * This method is here to help you run your program. Y
	 * You may modify it as needed.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please specify the name of the input file");
			System.exit(0);
		}
		String filename = args[0];
		System.out.print("Please enter a sentence: ");
		Scanner in = new Scanner(System.in);
		String sentence = in.nextLine();
		in.close();
		List<Sentence> sentences = Analyzer.readFile(filename);
		Set<Word> words = Analyzer.allWords(sentences);
		Map<String, Double> wordScores = Analyzer.calculateScores(words);
		double score = Analyzer.calculateSentenceScore(wordScores, sentence);
		System.out.println("The sentiment score is " + score);
	}
}
