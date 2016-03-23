package patternmatching;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

import patternmatching.impl.KMPPatternMatcher;

public class KMPPatternMatcherTest {

	@Test
	public void matchNone() {
		PatternMatcher matcher = new KMPPatternMatcher("abaababaabaab");
		Iterator<Integer> indexesIterator = matcher.match("abs");
		assertFalse(indexesIterator.hasNext());
	}

	@Test
	public void matchSingle() {
		PatternMatcher matcher = new KMPPatternMatcher("abaababaabaabaababaabb");
		Iterator<Integer> indexesIterator = matcher.match("abaababaabb");
		assertTrue(indexesIterator.hasNext());
	}

	@Test
	public void matchMultiple() {
		PatternMatcher matcher = new KMPPatternMatcher("This is a test case i");
		Iterator<Integer> indexesIterator = matcher.match("is");
		assertTrue(indexesIterator.next() == 2);
		assertTrue(indexesIterator.next() == 5);
	}
}
