package com.code.hub.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.code.hub.bean.SearchDetails;
import com.code.hub.bean.SearchPageResponse;
import com.code.hub.entity.Submission;
import com.code.hub.service.SubmissionService;
import com.code.hub.vo.SubmissionStatistics;

/**
 * Controller to handle all the submission request
 * 
 * @author tbhakre
 *
 */
@RestController
@RequestMapping("/submissions")
public class SubmissionController {

	@Autowired
	private SubmissionService service;

	/**
	 * Search method supporting pagination, filtering and sorting
	 * 
	 * @param requestParam
	 *            Map containing all the request parameter
	 * @return Search result along with Page information
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<SearchPageResponse<Submission>> search(
			@RequestParam Map<String, String> requestParams) {

		SearchDetails searchDetails = new SearchDetails(requestParams);

		SearchPageResponse<Submission> pageResponse = service
				.search(searchDetails);
		// If no content found
		if (pageResponse.getData().isEmpty()) {
			return new ResponseEntity<SearchPageResponse<Submission>>(
					HttpStatus.NO_CONTENT);
		}
		// Return with success
		return new ResponseEntity<SearchPageResponse<Submission>>(pageResponse,
				HttpStatus.OK);
	}

	/**
	 * Gets all the statistics related to submissions
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/statistics")
	public SubmissionStatistics statistics() {
		return service.statistics();
	}

	/**
	 * Custom search supporting search over multiple fields
	 * 
	 * @param requestParams
	 *            Map containing all the request parameters
	 * @return Matched submissions along with the page information
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/search")
	public ResponseEntity<SearchPageResponse<Submission>> multiFieldSearch(
			@RequestParam Map<String, String> requestParams) {
		SearchDetails searchDetails = new SearchDetails(requestParams);

		SearchPageResponse<Submission> pageResponse = service
				.customSearch(searchDetails);
		// If no content found
		if (pageResponse.getData().isEmpty()) {
			return new ResponseEntity<SearchPageResponse<Submission>>(
					HttpStatus.NO_CONTENT);
		}
		// Return with success
		return new ResponseEntity<SearchPageResponse<Submission>>(pageResponse,
				HttpStatus.OK);
	}

	public void setService(SubmissionService service) {
		this.service = service;
	}

}
