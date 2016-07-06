package com.indo.payment.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.indo.payment.bean.SearchDetails;
import com.indo.payment.bean.SearchPageResponse;
import com.indo.payment.domain.PaymentInformation;
import com.indo.payment.service.PaymentInformationService;

/**
 * Handles requests related to payment options
 */
@RestController
@RequestMapping("/payment/options")
public class PaymentInformationController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PaymentInformationController.class);

	@Autowired
	private PaymentInformationService paymentInformationService;

	/**
	 * For searching the records
	 * 
	 * @param requestParams
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<SearchPageResponse<PaymentInformation>> search(
			@RequestParam Map<String, String> requestParams) {

		LOGGER.info("Inside paymentinfo search.");
		SearchDetails searchDetails = new SearchDetails(requestParams);

		SearchPageResponse<PaymentInformation> pageResponse = paymentInformationService
				.search(searchDetails);
		// If no content found
		if (pageResponse.getData().isEmpty()) {
			return new ResponseEntity<SearchPageResponse<PaymentInformation>>(
					HttpStatus.NO_CONTENT);
		}
		// Return with success
		return new ResponseEntity<SearchPageResponse<PaymentInformation>>(
				pageResponse, HttpStatus.OK);
	}

	/**
	 * Creates the payment information
	 * 
	 * @param paymentInformation
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<PaymentInformation> createPaymentInformation(
			@RequestBody PaymentInformation paymentInformation) {

		if (paymentInformationService
				.isPaymentInformationExist(paymentInformation.getId())) {
			return new ResponseEntity<PaymentInformation>(HttpStatus.CONFLICT);
		}

		PaymentInformation createdPaymentInformation = paymentInformationService
				.createPaymentInformation(paymentInformation);

		return new ResponseEntity<PaymentInformation>(
				createdPaymentInformation, HttpStatus.CREATED);
	}

	/**
	 * Gets the payment information for the passed ID
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<PaymentInformation> getPaymentInformation(
			@PathVariable("id") Integer id) {

		PaymentInformation paymentInformation = paymentInformationService
				.findPaymentInformationById(id);

		if (paymentInformation == null) {
			return new ResponseEntity<PaymentInformation>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<PaymentInformation>(paymentInformation,
				HttpStatus.OK);

	}

	/**
	 * Updates payment information
	 * 
	 * @param paymentInformation
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<PaymentInformation> updatePaymentInformation(
			@PathVariable("id") Integer id,
			@RequestBody PaymentInformation paymentInformation) {

		if (!paymentInformationService.isPaymentInformationExist(id)) {
			return new ResponseEntity<PaymentInformation>(HttpStatus.NOT_FOUND);
		}

		paymentInformation.setId(id);
		PaymentInformation updatedPaymentInformation = paymentInformationService
				.updatePaymentInformation(paymentInformation);

		return new ResponseEntity<PaymentInformation>(
				updatedPaymentInformation, HttpStatus.OK);

	}

	/**
	 * Gets the payment information for the passed ID
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Void> deletePaymentInformation(
			@PathVariable("id") Integer id) {

		if (!paymentInformationService.isPaymentInformationExist(id)) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		paymentInformationService.deletePaymentInformationById(id);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

	}
}
