package patternmatching.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import patternmatching.PatternMatcher;

public class BruteForcePatternMatcher implements PatternMatcher {

	private String text;

	public BruteForcePatternMatcher(String text) {
		this.text = text;
	}

	@Override
	public Iterator<Integer> match(String pattern) {
		if (pattern == null) {
			throw new IllegalArgumentException();
		}
		List<Integer> startingIndexes = new ArrayList<Integer>();
		// Matching starting from every character in text
		for (int iCount = 0; iCount < text.length(); iCount++) {
			boolean patternMatched = false;
			// Matching starting from every character in pattern
			for (int jCount = 0; jCount < pattern.length()
					&& (jCount + iCount) < text.length(); jCount++) {
				// If characters are unequal
				if (text.charAt(iCount + jCount) != pattern.charAt(jCount)) {
					break;
				}
				if (jCount == pattern.length() - 1) {
					// When all the characters of the pattern matched
					patternMatched = true;
				}
			}
			if (patternMatched) {
				startingIndexes.add(iCount);
			}
		}

		return startingIndexes.iterator();
	}
}
