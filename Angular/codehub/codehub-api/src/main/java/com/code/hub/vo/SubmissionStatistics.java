package com.code.hub.vo;

import java.util.List;

/**
 * Represents submission related statistics
 * 
 * @author tbhakre
 *
 */
public class SubmissionStatistics {

	private List<CountStaistics> topLanguages;
	private List<CountStaistics> topAttemptedSubmissions;
	private List<CountStaistics> submissionsPerLevel;
	private Long totalSubmissions;

	public List<CountStaistics> getTopLanguages() {
		return topLanguages;
	}

	public void setTopLanguages(List<CountStaistics> topLanguages) {
		this.topLanguages = topLanguages;
	}

	public List<CountStaistics> getTopAttemptedSubmissions() {
		return topAttemptedSubmissions;
	}

	public void setTopAttemptedSubmissions(
			List<CountStaistics> topAttemptedSubmissions) {
		this.topAttemptedSubmissions = topAttemptedSubmissions;
	}

	public List<CountStaistics> getSubmissionsPerLevel() {
		return submissionsPerLevel;
	}

	public void setSubmissionsPerLevel(List<CountStaistics> submissionsPerLevel) {
		this.submissionsPerLevel = submissionsPerLevel;
	}

	public Long getTotalSubmissions() {
		return totalSubmissions;
	}

	public void setTotalSubmissions(Long totalSubmissions) {
		this.totalSubmissions = totalSubmissions;
	}

}
