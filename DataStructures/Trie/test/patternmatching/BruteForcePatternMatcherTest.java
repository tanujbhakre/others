package patternmatching;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

import patternmatching.impl.BruteForcePatternMatcher;

public class BruteForcePatternMatcherTest {

	@Test
	public void matchNone() {
		PatternMatcher matcher = new BruteForcePatternMatcher(
				"Test case for pattern matching.");
		Iterator<Integer> indexesIterator = matcher.match("no");
		assertFalse(indexesIterator.hasNext());
	}

	@Test
	public void matchSingle() {
		PatternMatcher matcher = new BruteForcePatternMatcher(
				"Test case for pattern matching.");
		Iterator<Integer> indexesIterator = matcher.match("case");
		assertTrue(indexesIterator.hasNext());
	}

	@Test
	public void matchMultiple() {
		PatternMatcher matcher = new BruteForcePatternMatcher(
				"This is a test case i");
		Iterator<Integer> indexesIterator = matcher.match("is");
		assertTrue(indexesIterator.next() == 2);
		assertTrue(indexesIterator.next() == 5);
	}

	@Test
	public void matchSingleS() {
		PatternMatcher matcher = new BruteForcePatternMatcher(
				"abaababaabaabaababaabb");
		Iterator<Integer> indexesIterator = matcher.match("abaababaabb");
		assertTrue(indexesIterator.hasNext());
	}
}
