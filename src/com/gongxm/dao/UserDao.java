package com.gongxm.dao;

import java.util.List;

import com.gongxm.bean.User;

public interface UserDao extends Dao<User>{
	public abstract User findUser(String username);
	public abstract List<User> findAll();
	public abstract User findUser(String username, String password);
}
