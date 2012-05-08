package example.rest.local;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import example.rest.IDictionaryService;
import example.rest.Word;

public class DictionaryMemService implements IDictionaryService {
    Map<String, Word> words = new TreeMap<String, Word>();

    DictionaryMemService() {
        addWord("dog",
                "A domesticated carnivorous mammal that typically has a long snout, an acute sense of smell, and a barking, howling, or whining voice. It is widely kept as a pet or for work or field sports.",
                "noun");
        addWord("cat",
                "A small domesticated carnivorous mammal (Felis catus), with soft fur, a short snout, and retractile claws.",
                "noun");

    }

    private Word addWord(String word, String definition, String type) {
        Word w = new Word(word, definition, type);
        words.put(word, w);
        return w;
    }

    @Override
    public Word addWord(Word w) {
        if (words.containsKey(w.word)) {
            throw new IllegalArgumentException("Word already exists: " + w.word);
        }

        Word newWord = new Word(w.word, w.definition, w.type);
        words.put(w.word, newWord);
        return newWord;
    }

    @Override
    public Word getWord(String word) {
        return words.get(word);
    }

    @Override
    public List<Word> getWords() {
        return new ArrayList(words.values());
    }
}
