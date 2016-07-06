package com.indo.payment.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents the payment options
 * 
 * @author tbhakre
 *
 */
@Entity
@Table(name = "VT_Services")
public class PaymentInformation {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String image;
	private String description;
	private Boolean branding;
	private Double rating;

	@Column(name = "setup_fee")
	private Boolean setupFee;

	@Column(name = "transaction_fees")
	private String transactionFee;

	@Column(name = "how_to_url")
	private String howToUrl;

	private String currencies;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getBranding() {
		return branding;
	}

	public void setBranding(Boolean branding) {
		this.branding = branding;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Boolean getSetupFee() {
		return setupFee;
	}

	public void setSetupFee(Boolean setupFee) {
		this.setupFee = setupFee;
	}

	public String getTransactionFee() {
		return transactionFee;
	}

	public void setTransactionFee(String transactionFee) {
		this.transactionFee = transactionFee;
	}

	public String getHowToUrl() {
		return howToUrl;
	}

	public void setHowToUrl(String howToUrl) {
		this.howToUrl = howToUrl;
	}

	public String getCurrencies() {
		return currencies;
	}

	public void setCurrencies(String currencies) {
		this.currencies = currencies;
	}

}
