package controller;

import model.Dictionary;

import java.util.NoSuchElementException;

public class DictionaryController {
    private final Dictionary dictionary;

    public DictionaryController() {
        this.dictionary = new Dictionary();
        dictionary.addWord("polymorphism", "The ability of the same code to operate on different types.");
        dictionary.addWord("encapsulation", "Bundling data with the methods that operate on that data.");
        dictionary.addWord("inheritance", "Mechanism to derive new classes from existing ones.");
        dictionary.addWord("hashmap", "Key-value data structure offering average O(1) lookup.");
    }

    public String searchMeaning(String word) {
        return dictionary.getMeaning(word);
    }

    public void addEntry(String word, String meaning) {
        dictionary.addWord(word, meaning);
    }
}
