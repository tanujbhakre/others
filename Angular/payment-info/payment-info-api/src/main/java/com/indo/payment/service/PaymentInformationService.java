package com.indo.payment.service;

import com.indo.payment.bean.SearchDetails;
import com.indo.payment.bean.SearchPageResponse;
import com.indo.payment.domain.PaymentInformation;

/**
 * Service for operating on Payment Information
 * 
 * @author tbhakre
 *
 */
public interface PaymentInformationService {
	/**
	 * Gets the search details from DAO
	 * 
	 * @param details
	 *            Query details
	 * @return Payment information for provided details
	 */
	SearchPageResponse<PaymentInformation> search(SearchDetails details);

	/**
	 * Returns the payment information for the passes Id
	 * 
	 * @param id
	 * @return
	 */
	PaymentInformation findPaymentInformationById(Integer id);

	/**
	 * Coordinates with DAO to persist the payment information in the database
	 * 
	 * @param paymentInformation
	 * @return
	 */
	PaymentInformation createPaymentInformation(
			PaymentInformation paymentInformation);

	/**
	 * Coordinates with DAO to update the payment information present in the
	 * data base
	 * 
	 * @param paymentInformation
	 * @return
	 */
	PaymentInformation updatePaymentInformation(
			PaymentInformation paymentInformation);

	/**
	 * Coordinates with DAO to Delete the payment information with the provided
	 * ID
	 * 
	 * @param id
	 */
	void deletePaymentInformationById(Integer id);

	/**
	 * Checks if payment information isi already present in the DB or not
	 * 
	 * @param id
	 * @return
	 */
	Boolean isPaymentInformationExist(Integer id);

}
