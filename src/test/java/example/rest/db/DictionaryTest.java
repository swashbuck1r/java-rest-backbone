package example.rest.db;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;

import example.rest.IDictionaryService;
import example.rest.Word;
import example.rest.db.GuiceJUnitRunner.GuiceModules;
import example.rest.db.GuiceJUnitRunner.GuicePersist;

@RunWith(GuiceJUnitRunner.class)
@GuiceModules({ DictionaryDBModule.class})
@GuicePersist("testPU")
public class DictionaryTest {
	@Inject
	private IDictionaryService dict;

	@Test
	public void testAddWords() throws Exception {
		// add a word
		Word w = new Word("cat", "A small domesticated carnivorous mammal",
				"noun");
		dict.addWord(w);

		// look up the work
		Word w2 = dict.getWord("cat");

		// verify the word in the dictionary is the same
		assertEquals(w.definition, w2.definition);
		assertEquals(w.id, w2.id);
		assertEquals(w.type, w2.type);
		assertEquals(w.word, w2.word);
	}
}
