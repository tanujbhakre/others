package com.indo.payment.bean;

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
	private Integer limit;
	private Integer page;
	private Integer total;

	public SearchPageResponse(List<T> data, Integer limit, Integer page,
			Integer total) {
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

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
