package com.code.hub.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.code.hub.bean.SearchDetails;
import com.code.hub.bean.SearchPageResponse;
import com.code.hub.entity.Submission;
import com.code.hub.persistence.SubmissionDAO;
import com.code.hub.service.SubmissionService;
import com.code.hub.vo.CountStaistics;
import com.code.hub.vo.Level;
import com.code.hub.vo.SubmissionStatistics;

public class SubmissionServiceImpl implements SubmissionService {

	private static Long TOP_LANGUAGE_COUNT = 5l;
	private static Long TOP_SUBMISSION_COUNT = 2l;

	private SubmissionDAO submissionDAO;

	public void setSubmissionDAO(SubmissionDAO submissionDAO) {
		this.submissionDAO = submissionDAO;
	}

	@Override
	public SearchPageResponse<Submission> search(SearchDetails details) {
		SearchPageResponse<Submission> response = null;

		List<Submission> submissions = submissionDAO.search(details);

		Long pageSize = details.getSize() != null ? details.getSize()
				: submissions.size();

		response = new SearchPageResponse<Submission>(submissions, pageSize,
				details.getPageNumber(), submissionDAO.count(details));

		return response;
	}

	@Override
	public SubmissionStatistics statistics() {
		SubmissionStatistics statistics = new SubmissionStatistics();

		// Getting all the level counts
		List<CountStaistics> levelDetails = new ArrayList<CountStaistics>();
		levelDetails.add(new CountStaistics(Level.EASY.getLevel(),
				submissionDAO.getMetaDataLevelCount(Level.EASY.getLevel())));
		levelDetails.add(new CountStaistics(Level.MEDIUM.getLevel(),
				submissionDAO.getMetaDataLevelCount(Level.MEDIUM.getLevel())));
		levelDetails.add(new CountStaistics(Level.HARD.getLevel(),
				submissionDAO.getMetaDataLevelCount(Level.HARD.getLevel())));

		statistics.setTopLanguages(submissionDAO
				.getTopLanguage(TOP_LANGUAGE_COUNT));

		statistics.setTotalSubmissions(submissionDAO.count());

		statistics.setTopAttemptedSubmissions(submissionDAO
				.getTopAttemptedSubmissions(TOP_SUBMISSION_COUNT));

		statistics.setSubmissionsPerLevel(levelDetails);

		return statistics;
	}

	@Override
	public SearchPageResponse<Submission> customSearch(SearchDetails details) {
		SearchPageResponse<Submission> response = null;

		List<Submission> submissions = submissionDAO.customSearch(details);

		Long pageSize = details.getSize() != null ? details.getSize()
				: submissions.size();

		response = new SearchPageResponse<Submission>(submissions, pageSize,
				details.getPageNumber(),
				submissionDAO.countCustomSearch(details));

		return response;
	}

}
