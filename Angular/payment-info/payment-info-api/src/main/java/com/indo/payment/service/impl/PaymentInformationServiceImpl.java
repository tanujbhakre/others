package com.indo.payment.service.impl;

import java.util.List;

import com.indo.payment.bean.SearchDetails;
import com.indo.payment.bean.SearchPageResponse;
import com.indo.payment.domain.PaymentInformation;
import com.indo.payment.persistence.PaymentInformationDAO;
import com.indo.payment.service.PaymentInformationService;

public class PaymentInformationServiceImpl implements PaymentInformationService {

	private PaymentInformationDAO paymentInformationDAO;

	public void setPaymentInformationDAO(
			PaymentInformationDAO paymentInformationDAO) {
		this.paymentInformationDAO = paymentInformationDAO;
	}

	@Override
	public SearchPageResponse<PaymentInformation> search(SearchDetails details) {

		SearchPageResponse<PaymentInformation> response = null;

		List<PaymentInformation> paymentInformation = paymentInformationDAO
				.search(details);

		Integer pageSize = details.getSize() != null ? details.getSize()
				: paymentInformation.size();

		response = new SearchPageResponse<PaymentInformation>(
				paymentInformation, pageSize, details.getPageNumber(),
				paymentInformationDAO.count(details));

		return response;
	}

	@Override
	public PaymentInformation findPaymentInformationById(Integer id) {
		return paymentInformationDAO.getPaymentInformationById(id);
	}

	@Override
	public PaymentInformation createPaymentInformation(
			PaymentInformation paymentInformation) {
		return paymentInformationDAO
				.createPaymentInformation(paymentInformation);
	}

	@Override
	public PaymentInformation updatePaymentInformation(
			PaymentInformation paymentInformation) {

		return paymentInformationDAO
				.updatePaymentInformation(paymentInformation);
	}

	@Override
	public void deletePaymentInformationById(Integer id) {
		paymentInformationDAO.deletePaymentInformationById(id);
	}

	@Override
	public Boolean isPaymentInformationExist(Integer id) {
		return paymentInformationDAO.isPaymentInformationExist(id);
	}
}
