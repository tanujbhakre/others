package com.code.hub.persistence;

import java.util.List;

import com.code.hub.bean.SearchDetails;
import com.code.hub.entity.Submission;
import com.code.hub.vo.CountStaistics;

public interface SubmissionDAO {

	/**
	 * Searches the data as per provided search details
	 * 
	 * @param details
	 *            Details for filtering data
	 * 
	 * @return List of submissions qualifying the search criteria
	 */
	List<Submission> search(SearchDetails details);

	/**
	 * Count of all the records present in the Table
	 * 
	 * @return Count of records present in the table
	 */
	Long count();

	/**
	 * Count of all the records qualifying the search criteria
	 * 
	 * @param details
	 *            Details for filtering data
	 * @return
	 */
	Long count(SearchDetails details);

	/**
	 * Gets the top N languages being used
	 * 
	 * @param size
	 *            Number of top languages to retrieve
	 * @return Language and count details
	 */
	List<CountStaistics> getTopLanguage(Long size);

	/**
	 * Gets the top N topics on which submission is done
	 * 
	 * @param size
	 *            Number of top submissions to fetch
	 * @return
	 */
	List<CountStaistics> getTopAttemptedSubmissions(Long size);

	/**
	 * Get level count of the passed level
	 * 
	 * @param level
	 *            Level which is to be searched in the metadata
	 * @return Number of submissions with the provided levels
	 */
	Long getMetaDataLevelCount(String level);

	/**
	 * Searches custom values on level, language and title, also filters on the
	 * basis of status
	 * 
	 * @param details
	 *            Filtering details
	 * @return Matched submissions
	 */
	List<Submission> customSearch(SearchDetails details);

	/**
	 * Finds total count of records matching teh search criteria
	 * 
	 * @param details
	 *            Filtering details
	 * @return Count of matched data
	 */
	Long countCustomSearch(SearchDetails details);

}
