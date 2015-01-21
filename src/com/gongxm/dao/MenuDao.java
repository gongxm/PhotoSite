package com.gongxm.dao;

import java.util.List;

import com.gongxm.bean.Menu;

public interface MenuDao extends Dao<Menu> {
	public abstract List<Menu> findAll();

	// ����IDɾ���˵�
	public abstract void deleteMenu(String id);
	//����menu���Ҳ˵�
	public abstract Menu findMenu(String menu);
}
