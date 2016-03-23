package trie.domain;

import java.util.LinkedList;

/**
 * This class represents a trie node
 * 
 * @author Tanuj Bhakre
 * 
 */
public class TrieNode {
	private Character data;
	private LinkedList<TrieNode> children;
	private Integer childCount;
	private boolean isEndOfWord;

	public TrieNode() {
		children = new LinkedList<TrieNode>();
		childCount = 0;
		isEndOfWord = false;
	}

	public TrieNode(Character data) {
		this();
		this.data = data;
	}

	/**
	 * This method gets the node from the child nodes which has the same data as
	 * passed
	 * 
	 * @param data
	 * @return Returns the node which.
	 */
	public TrieNode getChildNode(Character data) {
		TrieNode childNode = null;
		TrieNode node = new TrieNode(data);
		int indexOfChild = children.indexOf(node);
		if (childCount != 0 && indexOfChild != -1) {
			childNode = children.get(indexOfChild);
		}
		return childNode;
	}

	/**
	 * @return the data
	 */
	public Character getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Character data) {
		this.data = data;
	}

	/**
	 * @return the children
	 */
	public LinkedList<TrieNode> getChildren() {
		return children;
	}

	/**
	 * @param children
	 *            the children to set
	 */
	public void setChildren(LinkedList<TrieNode> children) {
		this.children = children;
	}

	/**
	 * @return the isEndOfWord
	 */
	public boolean isEndOfWord() {
		return isEndOfWord;
	}

	/**
	 * @param isEndOfWord
	 *            the isEndOfWord to set
	 */
	public void setEndOfWord(boolean isEndOfWord) {
		this.isEndOfWord = isEndOfWord;
	}

	public Integer getChildCount() {
		return childCount;
	}

	public void setChildCount(Integer childCount) {
		this.childCount = childCount;
	}

	@Override
	public boolean equals(Object obj) {
		TrieNode node = (TrieNode) obj;
		return this.getData().equals(node.getData());
	}

	@Override
	public String toString() {
		return data.toString();
	}
}
