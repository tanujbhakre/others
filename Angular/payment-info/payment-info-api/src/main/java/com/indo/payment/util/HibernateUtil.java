package com.indo.payment.util;

import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.indo.payment.bean.ExcludeDetails;
import com.indo.payment.bean.SearchDetails;

/**
 * Contains all the utility methods for Hibernate
 * 
 * @author tbhakre
 *
 */
public final class HibernateUtil {

	/**
	 * This method populates the criteria with the help of details by excluding
	 * the details as per exclude object
	 * 
	 * @param details
	 *            Details to be considered for populating the criteria
	 * @param criteria
	 *            Criteria to be populated
	 * @param exclude
	 *            Details of what all thing to eclude
	 * @return
	 */
	public static Criteria populateCriteria(SearchDetails details,
			Criteria criteria, ExcludeDetails exclude) {

		// Adding filter criteria
		if (!exclude.getFilter()) {
			for (Map.Entry<String, String> entry : details.getFilter()
					.entrySet()) {
				criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
			}
		}
		// Adding sort order
		if (!exclude.getSort()) {
			for (Map.Entry<String, Boolean> entry : details.getSort()
					.entrySet()) {
				String field = entry.getKey();
				if (entry.getValue()) {
					criteria.addOrder(Order.asc(field));
				} else {
					criteria.addOrder(Order.desc(field));
				}
			}
		}
		// Adding paging filter
		if (!exclude.getPaging()) {
			if (details.getOffset() != null && details.getSize() != null) {
				criteria.setFirstResult(details.getOffset() - 1);
				criteria.setMaxResults(details.getSize());
			}
		}
		return criteria;
	}

	/**
	 * This method populates the criteria with the help of details
	 * 
	 * @param details
	 *            Details to be considered for populating the criteria
	 * 
	 * @param criteria
	 *            Criteria to be populated
	 */
	public static Criteria populateCriteria(SearchDetails details,
			Criteria criteria) {

		return HibernateUtil.populateCriteria(details, criteria,
				new ExcludeDetails());
	}
}
