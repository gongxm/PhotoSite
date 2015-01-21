package com.gongxm.dao;

import java.util.List;

import com.gongxm.bean.Menu;

public interface MenuDao extends Dao<Menu> {
	public abstract List<Menu> findAll();

	// 根据ID删除菜单
	public abstract void deleteMenu(String id);
	//根据menu查找菜单
	public abstract Menu findMenu(String menu);
}
