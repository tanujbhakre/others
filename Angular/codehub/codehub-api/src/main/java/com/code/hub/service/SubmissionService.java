package com.code.hub.service;

import com.code.hub.bean.SearchDetails;
import com.code.hub.bean.SearchPageResponse;
import com.code.hub.entity.Submission;
import com.code.hub.vo.SubmissionStatistics;

/**
 * Services for Submission details
 * 
 * @author tbhakre
 *
 */
public interface SubmissionService {

	/**
	 * Gets all the Submission depending on the Search details
	 * 
	 * @param details
	 *            Details to be considered while searching
	 * @return
	 */
	SearchPageResponse<Submission> search(SearchDetails details);

	/**
	 * Gets statistics for the submission
	 * 
	 * @return Submission statistics
	 */
	SubmissionStatistics statistics();

	/**
	 * Custom search for searching value over title, language and level
	 * 
	 * @param details
	 * @return
	 */
	SearchPageResponse<Submission> customSearch(SearchDetails details);
}
