package example.rest.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.Session;
import org.hibernate.Transaction;

import example.rest.IDictionaryService;
import example.rest.Word;

public class DictionaryDBService implements IDictionaryService {

    DictionaryDBService() {
    }

    @Override
    public Word addWord(Word w) throws Exception {
        Session session = Utils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            PersistentWord word = new PersistentWord();
            word.setId(w.word);
            word.setDefinition(w.definition);
            word.setType(w.type);
            word.setWord(w.word);
            session.save(word);
            tx.commit();
            return w;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public Word getWord(String word) throws Exception {
        Session session = Utils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            PersistentWord pw = PersistentWord.findById(word);
            Word w = new Word(pw.getWord(), pw.getDefinition(), pw.getType());
            return w;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }

    @Override
    public List<Word> getWords() {
        Session session = Utils.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        ArrayList<Word> l = new ArrayList<Word>();
        try {
            List<PersistentWord> words = PersistentWord.findAll();
            for (PersistentWord pw : words) {
                Word w = new Word(pw.getWord(), pw.getDefinition(),
                        pw.getType());
                l.add(w);
            }
            return l;
        } catch (Exception e) {
            tx.rollback();
            return l;
        }
    }
}
