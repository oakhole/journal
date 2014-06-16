package com.oakhole.common.model;

import java.util.List;

public class PageEntity<T> {

	private int currentPage;
	private int start;
	private int pageSize;
	private int pageCount;
	private int totalCount;
	private List<T> result;
	private List<Integer> pageNum;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int tatolCount) {
		this.totalCount = tatolCount;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public List<Integer> getPageNum() {
		return pageNum;
	}

	public void setPageNum(List<Integer> pageNum) {
		this.pageNum = pageNum;
	}

}
