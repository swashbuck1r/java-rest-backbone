package example.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DictionaryService implements IDictionaryService {
    Map<String, Word> words = new TreeMap<String, Word>();

    DictionaryService() {
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
    
    /* (non-Javadoc)
     * @see example.rest.IDictionaryService#addWord(example.rest.Word)
     */
    @Override
    public Word addWord(Word w) {
        if(words.containsKey(w.word)){
            throw new IllegalArgumentException("Word already exists: " + w.word);
        }
        
        Word newWord = new Word(w.word, w.definition, w.type);
        words.put(w.word, newWord);
        return newWord;
    }

    /* (non-Javadoc)
     * @see example.rest.IDictionaryService#getWord(java.lang.String)
     */
    @Override
    public Word getWord(String word) {
        return words.get(word);
    }

    /* (non-Javadoc)
     * @see example.rest.IDictionaryService#getWords()
     */
    @Override
    public List<Word> getWords() {
        return new ArrayList(words.values());
    }
}
