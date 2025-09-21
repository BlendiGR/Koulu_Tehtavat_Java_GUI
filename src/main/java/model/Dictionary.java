package model;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Dictionary {
    private final Map<String, String> entries = new HashMap<>();

    public void addWord(String word, String meaning) {
        if (word == null || word.isBlank()) {
            throw new IllegalArgumentException("Word cannot be empty");
        }
        if (meaning == null || meaning.isBlank()) {
            throw new IllegalArgumentException("Meaning cannot be empty");
        }
        entries.put(word.trim().toLowerCase(), meaning.trim());
    }

    public String getMeaning(String word) {
        if (word == null || word.isBlank()) {
            throw new IllegalArgumentException("Word cannot be empty");
        }
        String key = word.trim().toLowerCase();
        String meaning = entries.get(key);
        if (meaning == null) {
            throw new NoSuchElementException("Word not found: " + word);
        }
        return meaning;
    }
}
