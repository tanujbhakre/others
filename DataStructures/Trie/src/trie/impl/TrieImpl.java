package trie.impl;

import trie.Trie;
import trie.domain.TrieNode;

public class TrieImpl implements Trie {

	private TrieNode root;

	public TrieImpl() {
		root = new TrieNode();
	}

	@Override
	public boolean insert(String word) {
		boolean isInserted = false;
		if (word == null || word.isEmpty()) {
			throw new IllegalArgumentException();
		}
		TrieNode node = root;
		for (char character : word.toCharArray()) {
			TrieNode childNode = node.getChildNode(character);
			// The character node is not present in the tree
			if (childNode == null) {
				TrieNode newNode = new TrieNode(character);
				node.getChildren().add(newNode);
				node.setChildCount(node.getChildCount() + 1);
				node = newNode;
			} else {
				node = childNode;
			}
		}
		if (!node.isEndOfWord()) {
			node.setEndOfWord(true);
			isInserted = true;
		}
		return isInserted;
	}

	@Override
	public boolean search(String word) {
		boolean isPresent = false;
		if (word == null || word.isEmpty()) {
			throw new IllegalArgumentException();
		}
		TrieNode node = root;
		for (char character : word.toCharArray()) {
			TrieNode childNode = node.getChildNode(character);
			node = childNode;
			// The character node is not present in the tree
			if (childNode == null) {
				break;
			}
		}
		if (node != null && node.isEndOfWord()) {
			isPresent = true;
		}
		return isPresent;
	}

	@Override
	public boolean remove(String word) {
		boolean isDeleted = false;
		if (search(word)) {
			isDeleted = true;
			TrieNode node = root;
			TrieNode deleteFrom = null;
			TrieNode deleteNode = null;
			TrieNode childNode = null;
			TrieNode parentNode = null;
			for (char character : word.toCharArray()) {
				childNode = node.getChildNode(character);
				if (node.isEndOfWord() && childNode.getChildCount() <= 1) {
					// Tracking which node and its children to be deleted
					deleteFrom = node;
					deleteNode = childNode;
				}
				parentNode = node;
				node = childNode;
			}

			if (deleteFrom != null && deleteNode != null) {
				// When we have details of node to be deleted
				deleteFrom.getChildren().remove(deleteNode.getData());
				deleteFrom.setChildCount(deleteFrom.getChildCount() - 1);
			} else if (node.getChildCount() == 0) {
				// When the node is leaf node
				parentNode.getChildren().remove(node.getData());
				parentNode.setChildCount(parentNode.getChildCount() - 1);
			} else {
				// When the node is internal node and its word to be deleted
				node.setEndOfWord(false);
			}
		}
		return isDeleted;
	}
}
