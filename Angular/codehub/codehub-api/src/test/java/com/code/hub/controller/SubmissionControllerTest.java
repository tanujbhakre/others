package com.code.hub.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.code.hub.bean.SearchDetails;
import com.code.hub.bean.SearchPageResponse;
import com.code.hub.entity.Submission;
import com.code.hub.service.SubmissionService;

@RunWith(MockitoJUnitRunner.class)
public class SubmissionControllerTest {

	@InjectMocks
	private SubmissionController controller = new SubmissionController();

	@Mock
	private SubmissionService service;

	@Test
	public void multiFieldSearch() {

		Map<String, String> params = new HashMap<String, String>();
		List<Submission> submissions = new ArrayList<Submission>();
		submissions.add(new Submission());

		SearchPageResponse<Submission> serviceResponse = new SearchPageResponse<Submission>(
				submissions, 0l, 0l, 0l);

		when(service.customSearch(any(SearchDetails.class))).thenReturn(
				serviceResponse);

		ResponseEntity<SearchPageResponse<Submission>> response = controller
				.multiFieldSearch(params);

		verify(service).customSearch(any(SearchDetails.class));

		Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
		Assert.assertTrue(response.getBody().getData().size() == 1);

	}

	@Test
	public void multiFieldSearchNoData() {

		Map<String, String> params = new HashMap<String, String>();
		List<Submission> submissions = new ArrayList<Submission>();

		SearchPageResponse<Submission> serviceResponse = new SearchPageResponse<Submission>(
				submissions, 0l, 0l, 0l);

		when(service.customSearch(any(SearchDetails.class))).thenReturn(
				serviceResponse);

		ResponseEntity<SearchPageResponse<Submission>> response = controller
				.multiFieldSearch(params);

		verify(service).customSearch(any(SearchDetails.class));

		Assert.assertTrue(response.getStatusCode() == HttpStatus.NO_CONTENT);
		Assert.assertTrue(response.getBody() == null);

	}
}
