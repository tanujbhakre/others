package com.code.hub.persistence.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.code.hub.bean.ExcludeDetails;
import com.code.hub.bean.SearchDetails;
import com.code.hub.entity.Submission;
import com.code.hub.exception.ApplicationRuntimeException;
import com.code.hub.persistence.SubmissionDAO;
import com.code.hub.util.HibernateUtil;
import com.code.hub.vo.CountStaistics;

/**
 * Implementation of search DAO
 * 
 * @author tbhakre
 *
 */
public class SubmissionDAOImpl implements SubmissionDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Submission> search(SearchDetails details) {
		Session session = this.sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Submission.class);
		criteria = HibernateUtil.populateCriteria(details, criteria);
		try {
			return criteria.list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public Long count(SearchDetails details) {
		Session session = this.sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Submission.class);
		criteria = HibernateUtil.populateCriteria(details, criteria,
				ExcludeDetails.getOnlyCount());
		criteria.setProjection(Projections.rowCount());
		try {
			Object count = criteria.uniqueResult();
			return (count != null ? ((Long) count) : 0);
		} catch (Exception e) {
			throw new ApplicationRuntimeException(e);
		}
	}

	@Override
	public Long count() {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "SELECT count(id) FROM Submission";
		Query query = session.createQuery(hql);
		return (Long) query.uniqueResult();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<CountStaistics> getTopLanguage(Long size) {

		List<CountStaistics> result = new ArrayList<CountStaistics>();

		Session session = this.sessionFactory.getCurrentSession();

		String hql = "SELECT COUNT(language), language FROM Submission GROUP BY language ORDER BY COUNT(language) DESC";
		Query query = session.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(size.intValue());

		List list = query.list();

		for (Object row : list) {
			Object[] rowArray = (Object[]) row;
			result.add(new CountStaistics((String) (rowArray[1]),
					(Long) (rowArray[0])));
		}

		return result;
	}

	@Override
	public List<CountStaistics> getTopAttemptedSubmissions(Long size) {

		List<CountStaistics> result = new ArrayList<CountStaistics>();

		Session session = this.sessionFactory.getCurrentSession();
		String hql = "SELECT COUNT(title), title FROM Submission GROUP BY title ORDER BY COUNT(title) DESC";

		Query query = session.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(size.intValue());

		for (Object row : query.list()) {
			Object[] rowArray = (Object[]) row;
			result.add(new CountStaistics((String) (rowArray[1]),
					(Long) (rowArray[0])));
		}

		return result;
	}

	@Override
	public Long getMetaDataLevelCount(String level) {

		Session session = this.sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(Submission.class);
		String likePattern = "%\"level\":\"" + level + "\"%";
		criteria.add(Restrictions.like("metadata", likePattern));
		criteria.setProjection(Projections.rowCount());

		return (Long) criteria.list().get(0);
	}

	private static final String MULTIFIELD_KEY = "multifield";
	private static final String STATUS_KEY = "status";

	@SuppressWarnings("unchecked")
	@Override
	public List<Submission> customSearch(SearchDetails details) {
		Session session = this.sessionFactory.getCurrentSession();

		Query query = session.createQuery(getCustomQuery(details));
		query.setProperties(getCustomQueryParameters(details));

		if (details.getOffset() != null && details.getSize() != null) {
			query.setFirstResult(details.getOffset().intValue() - 1);
			query.setMaxResults(details.getSize().intValue());
		}

		return query.list();
	}

	@Override
	public Long countCustomSearch(SearchDetails details) {
		Session session = this.sessionFactory.getCurrentSession();

		Query query = session.createQuery("SELECT count(id) "
				+ getCustomQuery(details));
		query.setProperties(getCustomQueryParameters(details));
		return (Long) query.list().get(0);
	}

	private String getCustomQuery(SearchDetails details) {
		String value = details.getFilter().get(MULTIFIELD_KEY);
		String status = details.getFilter().get(STATUS_KEY);

		StringBuilder hql = new StringBuilder("FROM Submission where 1=1 ");

		if (value != null) {
			hql.append(" and (title like :title or language like :language or metadata like :metadata)");
		}

		if (status != null) {
			hql.append(" and status like :status");
		}
		return hql.toString();
	}

	private Map<String, String> getCustomQueryParameters(SearchDetails details) {
		String value = details.getFilter().get(MULTIFIELD_KEY);
		String status = details.getFilter().get(STATUS_KEY);
		Map<String, String> map = new HashMap<String, String>();

		if (value != null) {
			String metaData = "%\"level\":\"" + value + "\"%";
			String likePattern = "%" + value + "%";

			map.put("title", likePattern);
			map.put("language", likePattern);
			map.put("metadata", metaData);
		}

		if (status != null) {
			String likeStatus = status + "%";
			map.put("status", likeStatus);
		}
		return map;
	}

}
