package com.gongxm.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.gongxm.bean.User;
import com.gongxm.dao.UserDao;
import com.gongxm.utils.DBCPUtil;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
	private QueryRunner qr=new QueryRunner(DBCPUtil.getDataSource());
	@Override
	public List<User> findAll() {
		try {
			return qr.query("select * from user", new BeanListHandler<User>(
					User.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public User findUser(String username) {
		try {
			return qr.query("select * from user where username=?",
					new BeanHandler<User>(User.class), username);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public User findUser(String username, String password) {
		try {
			return qr.query(
					"select * from user where username=? and password=?",
					new BeanHandler<User>(User.class), username, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
