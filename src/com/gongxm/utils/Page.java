package com.gongxm.utils;

import java.util.List;

public class Page {
	private List records; // 分页查询到的记录
	private int currentPage; // 当前页码
	private int totalPageNum;// 总页码
	private int totalRecords; // 总记录数
	private int previousPage;// 上一页页码
	private int nextPage;// 下一页页码
	private int startIndex;// 每页开始记录的索引
	private int endIndex;// 每页结束记录的索引
	private int pageSize = 16;// 每页显示记录条数，默认为10条
	private int startNum;
	private String url;

	public Page(int currentPage, int totalRecords,String url) {
		super();
		this.currentPage = currentPage;
		this.totalRecords = totalRecords;
		this.setUrl(url);
		init(currentPage, totalRecords);
	}

	private void init(int currentPage, int totalRecords) {
		// 计算出其他的变量的值
		int num = totalRecords / pageSize;
		totalPageNum = totalRecords % pageSize == 0 ? num : num + 1;
		num = currentPage - 1;
		previousPage = num > 0 ? num : 1;
		num = currentPage + 1;
		nextPage = num > totalPageNum ? totalPageNum : num;
		
		startNum=(currentPage-1)*pageSize;
		
		if (totalPageNum <= 9) {
			startIndex = 1;
			endIndex = totalPageNum;
		} else {
			startIndex = currentPage - 4;
			endIndex = currentPage + 4;
			if (startIndex < 1) {
				startIndex = 1;
				endIndex = startIndex + 8;
			}
			if (endIndex > totalPageNum) {
				endIndex = totalPageNum;
				startIndex = endIndex - 8;
			}
		}
	}

	public List getRecords() {
		return records;
	}

	public void setRecords(List records) {
		this.records = records;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getPreviousPage() {
		return previousPage;
	}

	public void setPreviousPage(int previousPage) {
		this.previousPage = previousPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		init(currentPage, totalRecords);
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Page [records=" + records + ", currentPage=" + currentPage
				+ ", totalPageNum=" + totalPageNum + ", totalRecords="
				+ totalRecords + ", previousPage=" + previousPage
				+ ", nextPage=" + nextPage + ", startIndex=" + startIndex
				+ ", endIndex=" + endIndex + ", pageSize=" + pageSize
				+ ", startNum=" + startNum + ", url=" + url + "]";
	}
}
