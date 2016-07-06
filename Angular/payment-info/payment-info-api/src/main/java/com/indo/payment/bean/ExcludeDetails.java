package com.indo.payment.bean;

/**
 * Represents the information about what all the criteria to consider/remove
 * while creating criteria
 * 
 * @author tbhakre
 *
 */
public class ExcludeDetails {

	private Boolean paging;
	private Boolean filter;
	private Boolean sort;

	public ExcludeDetails(Boolean paging, Boolean filter, Boolean sort) {
		this.paging = paging;
		this.filter = filter;
		this.sort = sort;
	}

	public ExcludeDetails() {
		this.paging = false;
		this.filter = false;
		this.sort = false;
	}

	public Boolean getPaging() {
		return paging;
	}

	public void setPaging(Boolean paging) {
		this.paging = paging;
	}

	public Boolean getFilter() {
		return filter;
	}

	public void setFilter(Boolean filter) {
		this.filter = filter;
	}

	public Boolean getSort() {
		return sort;
	}

	public void setSort(Boolean sort) {
		this.sort = sort;
	}

	/**
	 * Provides Exclude details for getting count
	 * 
	 * @return
	 */
	public static ExcludeDetails getOnlyCount() {
		return new ExcludeDetails(true, false, true);
	}
}
