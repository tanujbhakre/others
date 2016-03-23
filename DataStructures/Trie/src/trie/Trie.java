package trie;

/**
 * This interface exposes the operations possible with Trie
 * 
 * @author Tanuj Bhakre
 * 
 */
public interface Trie {
	/**
	 * This method inserts a word in Trie
	 * 
	 * @param word
	 *            The word to be inserted in the trie
	 * @return returns true if the word was inserted, returns false if the word
	 *         was already present
	 */
	public boolean insert(String word);

	/**
	 * This method searches for a word in trie
	 * 
	 * @param word
	 *            The word which is to be searched in the Trie
	 * @return returns true if the word is found in Trie
	 */
	public boolean search(String word);

	/**
	 * This method removes the word from the Trie
	 * 
	 * @param word
	 *            The word which is to be removed
	 * @return returns true if word was removed returns false if the word was
	 *         not present already
	 */
	public boolean remove(String word);

}
