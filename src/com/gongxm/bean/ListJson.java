package com.gongxm.bean;

import java.util.List;

public class ListJson {
	private List<Image> list;
	private boolean hasNext;
	public List<Image> getList() {
		return list;
	}
	public void setList(List<Image> list) {
		this.list = list;
	}
	public boolean isHasNext() {
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	
}
