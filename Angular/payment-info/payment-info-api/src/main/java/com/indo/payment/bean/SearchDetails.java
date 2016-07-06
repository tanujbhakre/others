package com.indo.payment.bean;

import java.util.Map;

import com.indo.payment.util.SearchUtil;

/**
 * This class represents all the information to be considered while searching
 * for records
 * 
 * @author tbhakre
 *
 */
public class SearchDetails {
	private Integer offset;
	private Integer size;
	private Integer pageNumber;
	private Map<String, String> filter;
	private Map<String, Boolean> sort;

	private final static String START_KEY = "page[offset]";
	private final static String SIZE_KEY = "page[size]";
	private final static String FILTER_KEY_PREFIX = "filter[";
	private final static String FILTER_KEY_SUFFIX = "]";
	private final static String SORT_KEY = "sort";

	public SearchDetails(Map<String, String> requestParams) {

		this.offset = SearchUtil.getInteger(requestParams, START_KEY);
		this.size = SearchUtil.getInteger(requestParams, SIZE_KEY);
		this.filter = SearchUtil.getMap(requestParams, FILTER_KEY_PREFIX,
				FILTER_KEY_SUFFIX);
		this.sort = SearchUtil.getSort(requestParams, SORT_KEY);

		if (this.offset != null && this.size != null) {
			this.setPageNumber(((this.offset - 1) / this.size) + 1);
		} else {
			this.setPageNumber(1);
		}

	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Map<String, String> getFilter() {
		return filter;
	}

	public void setFilter(Map<String, String> filter) {
		this.filter = filter;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Map<String, Boolean> getSort() {
		return sort;
	}

	public void setSort(Map<String, Boolean> sort) {
		this.sort = sort;
	}

}
