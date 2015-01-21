package com.gongxm.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.gongxm.bean.Image;
import com.gongxm.bean.Menu;
import com.gongxm.dao.MenuDao;
import com.gongxm.utils.DBCPUtil;

public class MenuDaoImpl extends BaseDao<Menu> implements MenuDao {
	private QueryRunner qr=new QueryRunner(DBCPUtil.getDataSource());
	@Override
	public List<Menu> findAll() {
		try {
			return qr.query("select * from menu", new BeanListHandler<Menu>(Menu.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public void deleteMenu(String id) {
		try {
			qr.update("delete from menu where id="+id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public Menu findMenu(String menu) {
		try {
			return qr.query("select * from menu where menu=?", new BeanHandler<Menu>(Menu.class),menu);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
