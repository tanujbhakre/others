package com.code.hub.service.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.code.hub.bean.SearchDetails;
import com.code.hub.bean.SearchPageResponse;
import com.code.hub.entity.Submission;
import com.code.hub.persistence.SubmissionDAO;

@RunWith(MockitoJUnitRunner.class)
public class SubmissionServiceImplTest {

	@InjectMocks
	private SubmissionServiceImpl service = new SubmissionServiceImpl();

	@Mock
	private SubmissionDAO dao;

	@Test
	public void customSearch() {
		SearchDetails details = new SearchDetails(new HashMap<String, String>());
		List<Submission> submissions = new ArrayList<Submission>();
		submissions.add(new Submission());

		when(dao.customSearch(details)).thenReturn(submissions);
		when(dao.countCustomSearch(details)).thenReturn(1l);

		SearchPageResponse<Submission> response = service.customSearch(details);

		verify(dao).customSearch(details);
		verify(dao).countCustomSearch(details);

		Assert.assertTrue(response.getData() == submissions);
		Assert.assertTrue(response.getTotal() == 1L);
		Assert.assertTrue(response.getPage() == 1L);

	}
}
