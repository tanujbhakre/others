package patternmatching.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import patternmatching.PatternMatcher;

/**
 * The KMP pattern matching optimizes the brute force method and depending upon
 * the pattern matched before mismatch, shifting is determined so as to skip the
 * characters from text which will definitely not be in the pattern
 * 
 * @author Tanuj Bhakre
 * 
 */
public class KMPPatternMatcher implements PatternMatcher {

	private String text;

	public KMPPatternMatcher(String text) {
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
					int shiftBy = getShiftByNumber(pattern, jCount);
					if (shiftBy > 0) {
						iCount += shiftBy;
						// Subtracting one from number of places to jump as for
						// loop will increment it
						iCount--;
					}
					break;
				} else if (jCount == pattern.length() - 1) {
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

	/**
	 * This method determines the number of steps to shift before start to match
	 * the pattern again
	 * 
	 * @param pattern
	 *            The pattern which is being searched for
	 * @param mismatchAtPosition
	 *            The position at which mismatch happened
	 * @return
	 */
	private int getShiftByNumber(String pattern, int mismatchAtPosition) {
		int shiftBy = 0;
		// Matching every character of mismatched prefix of pattern
		for (int iCount = 1; iCount < mismatchAtPosition; iCount++) {
			shiftBy = 0;
			int kCount = iCount;
			// Flag to check if the prefix matched or not
			boolean prefixMatched = true;
			// Matching every character of mismatched prefix of pattern
			for (int jCount = 0; jCount < mismatchAtPosition - iCount; jCount++) {
				if (pattern.charAt(kCount) != pattern.charAt(jCount)) {
					prefixMatched = false;
					break;
				} else {
					kCount++;
					shiftBy++;
				}
			}
			// If a prefix was found the break the outer loop
			// Make sure the next character of matched prefix of pattern is not
			// same as the character at mismatched position
			if (prefixMatched
					&& pattern.charAt(mismatchAtPosition) != pattern
							.charAt(shiftBy)) {
				break;
			}
		}
		System.out.println("Shift By=" + shiftBy);
		return shiftBy;
	}
}
