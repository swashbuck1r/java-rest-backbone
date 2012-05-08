package example.rest;

import java.util.List;

public interface IDictionaryService {

    public abstract Word addWord(Word w) throws Exception;

    public abstract Word getWord(String word) throws Exception;

    public abstract List<Word> getWords() throws Exception;

}