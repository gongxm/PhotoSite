package com.gongxm.utils;

import java.util.List;

public class Page {
	private List records; // ��ҳ��ѯ���ļ�¼
	private int currentPage; // ��ǰҳ��
	private int totalPageNum;// ��ҳ��
	private int totalRecords; // �ܼ�¼��
	private int previousPage;// ��һҳҳ��
	private int nextPage;// ��һҳҳ��
	private int startIndex;// ÿҳ��ʼ��¼������
	private int endIndex;// ÿҳ������¼������
	private int pageSize = 16;// ÿҳ��ʾ��¼������Ĭ��Ϊ10��
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
		// ����������ı�����ֵ
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
