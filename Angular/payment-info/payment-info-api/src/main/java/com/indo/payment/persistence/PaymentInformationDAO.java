package com.indo.payment.persistence;

import java.util.List;

import com.indo.payment.bean.SearchDetails;
import com.indo.payment.domain.PaymentInformation;

/**
 * Provides methods to communicate with data base
 * 
 * @author tbhakre
 *
 */
public interface PaymentInformationDAO {

	/**
	 * Searching the data as per provided search details
	 * 
	 * @param details
	 *            List of payment information qualifying the search criteria
	 */
	List<PaymentInformation> search(SearchDetails details);

	/**
	 * Count of all the records present in the DB
	 * 
	 * @return Count of records present in the table
	 */
	Integer count();

	/**
	 * Count of all the records qualifying the search criteria
	 * 
	 * @param details
	 * @return
	 */
	Integer count(SearchDetails details);

	/**
	 * This method returns the payment information for the passes Id
	 * 
	 * @param id
	 * @return
	 */
	PaymentInformation getPaymentInformationById(Integer id);

	/**
	 * This method persists the payment information in the database
	 * 
	 * @param paymentInformation
	 * @return
	 */
	PaymentInformation createPaymentInformation(
			PaymentInformation paymentInformation);

	/**
	 * This method updates the payment information present in the data base
	 * 
	 * @param paymentInformation
	 * @return
	 */
	PaymentInformation updatePaymentInformation(
			PaymentInformation paymentInformation);

	/**
	 * Delete the payment information with the provided ID
	 * 
	 * @param id
	 */
	void deletePaymentInformationById(Integer id);

	/**
	 * Checks if payment information is already present in the DB or not
	 * 
	 * @param id
	 * @return
	 */
	Boolean isPaymentInformationExist(Integer id);
}
