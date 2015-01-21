package com.gongxm.bean;

import java.io.Serializable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.gongxm.utils.OnlineUtil;

public class User implements Serializable, HttpSessionBindingListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 11L;
	private int id;
	private String username;
	private String password;
	private String permission;
	
	//  create table user(id int auto_increment,username varchar(100),password varchar(100),permission varchar(100),primary key(id));

	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();
		OnlineUtil.add(username, session);
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
	}

}
