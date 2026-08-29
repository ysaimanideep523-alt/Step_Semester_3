import java.util.*;

public class WordFrequencyReport {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a sentence: ");
        String sentence = sc.nextLine().toLowerCase();

        // Stop words
        Set<String> stopWords = new HashSet<>(Arrays.asList(
                "a", "an", "the", "is", "are",
                "am", "and", "or", "of", "to",
                "in", "on", "for", "with"
        ));

        // Remove punctuation
        sentence = sentence.replaceAll("[^a-zA-Z0-9 ]", "");

        String[] words = sentence.split("\\s+");

        HashMap<String, Integer> frequency = new HashMap<>();

        for (String word : words) {
            if (!stopWords.contains(word) && !word.isEmpty()) {
                frequency.put(word, frequency.getOrDefault(word, 0) + 1);
            }
        }

        // Sort words alphabetically
        List<String> sortedWords = new ArrayList<>(frequency.keySet());
        Collections.sort(sortedWords);

        System.out.println("\nWord Frequency Report");
        System.out.println("---------------------");

        for (String word : sortedWords) {
            System.out.println(word + " : " + frequency.get(word));
        }

        sc.close();
    }
}