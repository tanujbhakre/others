package com.indo.payment.persistence.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.indo.payment.bean.ExcludeDetails;
import com.indo.payment.bean.SearchDetails;
import com.indo.payment.domain.PaymentInformation;
import com.indo.payment.persistence.PaymentInformationDAO;
import com.indo.payment.util.HibernateUtil;

public class PaymentInformationDAOImpl implements PaymentInformationDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Integer count() {
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "SELECT count(id) FROM PaymentInformation";
		Query query = session.createQuery(hql);
		return ((Long) query.uniqueResult()).intValue();
	}

	@Override
	public Integer count(SearchDetails details) {
		Session session = this.sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(PaymentInformation.class);
		criteria = HibernateUtil.populateCriteria(details, criteria,
				ExcludeDetails.getOnlyCount());
		criteria.setProjection(Projections.rowCount());
		try {
			return (criteria.uniqueResult() != null ? ((Long) criteria
					.uniqueResult()).intValue() : 0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public List<PaymentInformation> search(SearchDetails details) {
		Session session = this.sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(PaymentInformation.class);
		criteria = HibernateUtil.populateCriteria(details, criteria);
		try {
			return criteria.list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	@Override
	public PaymentInformation getPaymentInformationById(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (PaymentInformation) session.get(PaymentInformation.class, id);
	}

	@Override
	public PaymentInformation createPaymentInformation(
			PaymentInformation paymentInformation) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(paymentInformation);
		return paymentInformation;
	}

	@Override
	public PaymentInformation updatePaymentInformation(
			PaymentInformation paymentInformation) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(paymentInformation);
		return paymentInformation;
	}

	@Override
	public void deletePaymentInformationById(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		PaymentInformation paymentInformation = (PaymentInformation) session
				.get(PaymentInformation.class, id);
		session.delete(paymentInformation);
	}

	@Override
	public Boolean isPaymentInformationExist(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(PaymentInformation.class);
		criteria.add(Restrictions.eq("id", id));
		criteria.setProjection(Projections.property("id"));
		return criteria.uniqueResult() != null;
	}
}
