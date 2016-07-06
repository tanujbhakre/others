package com.code.hub.bean;

import java.util.Map;

import com.code.hub.util.SearchUtil;

/**
 * This class represents all the information to be considered while searching
 * for records
 * 
 * @author tbhakre
 *
 */
public class SearchDetails {
	private Long offset;
	private Long size;
	private Long pageNumber;
	private Map<String, String> filter;
	private Map<String, Boolean> sort;

	private final static String START_KEY = "page[offset]";
	private final static String SIZE_KEY = "page[size]";
	private final static String FILTER_KEY_PREFIX = "filter[";
	private final static String FILTER_KEY_SUFFIX = "]";
	private final static String SORT_KEY = "sort";

	public SearchDetails(Map<String, String> requestParams) {

		this.offset = SearchUtil.getLong(requestParams, START_KEY);
		this.size = SearchUtil.getLong(requestParams, SIZE_KEY);
		this.filter = SearchUtil.getMap(requestParams, FILTER_KEY_PREFIX,
				FILTER_KEY_SUFFIX);
		this.sort = SearchUtil.getSort(requestParams, SORT_KEY);

		if (this.offset != null && this.size != null) {
			this.setPageNumber(((this.offset - 1) / this.size) + 1);
		} else {
			this.setPageNumber(1l);
		}

	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Map<String, String> getFilter() {
		return filter;
	}

	public void setFilter(Map<String, String> filter) {
		this.filter = filter;
	}

	public Long getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Long pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Long getOffset() {
		return offset;
	}

	public void setOffset(Long offset) {
		this.offset = offset;
	}

	public Map<String, Boolean> getSort() {
		return sort;
	}

	public void setSort(Map<String, Boolean> sort) {
		this.sort = sort;
	}

}
