package example.rest.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Scope;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.google.inject.servlet.RequestScoped;

import example.rest.IDictionaryService;
import example.rest.Word;

public class DictionaryDBService implements IDictionaryService {
	@Inject
	EntityManager em;

	DictionaryDBService() {
	}

	@Transactional
	public Word addWord(Word w) throws Exception {
		PersistentWord word = new PersistentWord();
		word.setId(w.word);
		word.setDefinition(w.definition);
		word.setType(w.type);
		word.setWord(w.word);
		em.persist(word);
		System.out.println("Persisted word");
		return w;
	}

	@Override
	public Word getWord(String word) throws Exception {
		PersistentWord pw = findWordById(word);
		Word w = new Word(pw.getWord(), pw.getDefinition(), pw.getType());
		return w;
	}

	@Override
	public List<Word> getWords() {
		ArrayList<Word> l = new ArrayList<Word>();
		List<PersistentWord> words = findAllWords();
		for (PersistentWord pw : words) {
			Word w = new Word(pw.getWord(), pw.getDefinition(), pw.getType());
			l.add(w);
		}
		return l;
	}

	public PersistentWord findWordById(String id) {
		Query q = em.createQuery("from PersistentWord where id = :id");
		q.setParameter("id", id);
		return (PersistentWord) q.getSingleResult();
	}

	public List<PersistentWord> findAllWords() {
		Query q = em.createQuery("from PersistentWord");
		return (List<PersistentWord>) q.getResultList();
	}
}
