package trie;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import trie.impl.TrieImpl;

/**
 * This class tests all the methods exposed by Tries
 * 
 * @author Tanuj Bhakre
 * 
 */
public class TrieTest {
	private Trie trie;

	@Before
	public void setUp() {
		trie = new TrieImpl();
	}

	@Test
	public void insert() {
		String word = "apple";
		boolean isAdded = trie.insert(word);
		assertTrue(isAdded);
	}

	@Test
	public void search() {
		String word = "apple";
		trie.insert(word);
		boolean isFound = trie.search(word);
		assertTrue(isFound);

	}

	@Test
	public void remove() {
		String word = "app";
		assertTrue(trie.insert("apple"));
		assertTrue(trie.insert(word));
		assertTrue(trie.remove(word));
		assertTrue(trie.search("apple"));
		boolean isFound = trie.search(word);
		assertFalse(isFound);
	}

	@Test
	public void allOperations() {
		assertTrue(trie.insert("apple"));
		assertTrue(trie.insert("apples"));
		assertTrue(trie.insert("app"));
		assertTrue(trie.insert("ap"));
		assertTrue(trie.remove("ap"));
		assertFalse(trie.search("ap"));
		assertTrue(trie.search("apple"));
	}

}
