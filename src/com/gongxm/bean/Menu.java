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

	public Menu(String menu, String name) {
		super();
		this.menu = menu;
		this.name = name;
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
}
