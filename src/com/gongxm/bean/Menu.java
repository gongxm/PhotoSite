package com.gongxm.bean;

import java.io.Serializable;

/**
 * ≤Àµ•∂‘œÛ
 * 
 * @author gongxm
 * 
 */
public class Menu implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private int id;
	private String menu;
	private String name;
	private int startIndex;
	private int endIndex;


	
	public Menu(String menu, String name, int startIndex, int endIndex) {
		super();
		this.menu = menu;
		this.name = name;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}

	public Menu() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}
