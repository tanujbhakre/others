package patternmatching;

import java.util.Iterator;

/**
 * This class provides operations with pattern matching
 * 
 * @author tbhakre
 * 
 */
public interface PatternMatcher {

	/**
	 * This method takes the pattern and return an iterator over all the
	 * starting indexes of matched pattern
	 * 
	 * @param pattern
	 * @return
	 */
	public Iterator<Integer> match(String pattern);
}
