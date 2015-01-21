package com.gongxm.service;

import java.util.List;

import com.gongxm.bean.Image;
import com.gongxm.bean.Menu;
import com.gongxm.bean.User;

public interface Service {
	public abstract void addUser(User user);

	public abstract void deleteUser(String username);

	public abstract void updateUser(User user);

	public abstract User findUserByName(String username);

	public abstract User findUser(String username, String password);

	public abstract List<User> findAll();

	public abstract void addImg(Image image);

	public abstract void deleteImg(String name);

	public abstract void updateImg(Image image);

	public abstract Integer getTotalRecords();

	public abstract List<Image> findImageList(int startIndex, int pageSize);

	public abstract void clearDataBase();

	public abstract Image findImageById(String id);

	public abstract int getCategoryRecods(String category);

	public abstract List<Image> findCategoryImageList(String category,
			int startNum, int pageSize);

	public abstract Image findImageByName(String title);

	// 菜单的增删改查
	public abstract void addMenu(Menu menu);

	// 查找所有菜单
	public abstract List<Menu> findMenus();

	// 根据ID删除菜单
	public abstract void deleteMenu(String id);

	// 根据menu查找菜单
	public abstract Menu findMenu(String menu);
}
