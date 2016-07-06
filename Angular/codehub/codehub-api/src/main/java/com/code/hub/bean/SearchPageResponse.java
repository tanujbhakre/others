package com.code.hub.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Represents the page response to be sent which is resulted from search
 * 
 * @author tbhakre
 *
 * @param <T>
 */
public class SearchPageResponse<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<T> data;
	private Long limit;
	private Long page;
	private Long total;

	public SearchPageResponse(List<T> data, Long limit, Long page,
			Long total) {
		this.data = data;
		this.limit = limit;
		this.page = page;
		this.total = total;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Long getLimit() {
		return limit;
	}

	public void setLimit(Long limit) {
		this.limit = limit;
	}

	public Long getPage() {
		return page;
	}

	public void setPage(Long page) {
		this.page = page;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

}
