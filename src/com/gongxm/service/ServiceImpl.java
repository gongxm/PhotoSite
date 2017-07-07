package com.gongxm.service;

import java.util.List;

import com.gongxm.bean.CollectReg;
import com.gongxm.bean.Image;
import com.gongxm.bean.Menu;
import com.gongxm.bean.User;
import com.gongxm.dao.ImageDao;
import com.gongxm.dao.MenuDao;
import com.gongxm.dao.UserDao;
import com.gongxm.dao.impl.ImageDaoImpl;
import com.gongxm.dao.impl.MenuDaoImpl;
import com.gongxm.dao.impl.UserDaoImpl;

public class ServiceImpl implements Service {
	private UserDao udao = new UserDaoImpl();
	private ImageDao idao = new ImageDaoImpl();
	private MenuDao mdao = new MenuDaoImpl();

	@Override
	public void addUser(User user) {
		udao.add(user);
	}

	@Override
	public void deleteUser(String username) {
		User user = udao.findUser(username);
		if (user != null) {
			udao.delete(user.getId());
		}
	}

	@Override
	public void updateUser(User user) {
		udao.update(user);
	}

	@Override
	public List<User> findAll() {
		return udao.findAll();
	}

	@Override
	public void addImg(Image image) {
		idao.add(image);
	}

	@Override
	public void deleteImg(String filename) {
		Image image = idao.findImage(filename);
		if (image != null)
			idao.delete(image.getId());
	}

	@Override
	public void updateImg(Image image) {
		idao.update(image);
	}

	@Override
	public List<Image> findImageList(int startIndex, int pageSize) {
		return idao.findImageList(startIndex, pageSize);
	}

	@Override
	public User findUserByName(String username) {
		return udao.findUser(username);
	}

	@Override
	public User findUser(String username, String password) {
		return udao.findUser(username, password);
	}

	@Override
	public Integer getTotalRecords() {
		return idao.getTotalRecords();
	}

	@Override
	public void clearDataBase() {
		idao.clearDataBase();
	}

	@Override
	public Image findImageById(String id) {
		return idao.findImage(id);
	}

	@Override
	public int getCategoryRecods(String category) {
		return idao.getCategoryRecods(category);
	}

	@Override
	public List<Image> findCategoryImageList(String category, int startIndex,
			int pageSize) {
		return idao.findCategoryImageList(category, startIndex, pageSize);
	}

	@Override
	public Image findImageByName(String title) {
		return idao.findImageByName(title);
	}

	// 增加菜单
	@Override
	public void addMenu(Menu menu) {
		mdao.add(menu);
	}

	@Override
	public List<Menu> findMenus() {
		return mdao.findAll();
	}

	@Override
	public void deleteMenu(String id) {
		mdao.deleteMenu(id);
	}

	@Override
	public Menu findMenu(String menu) {
		return mdao.findMenu(menu);
	}

	// 更新菜单
	@Override
	public void updateMenu(Menu m) {
		mdao.update(m);
	}

	@Override
	public List<CollectReg> findAllReg() {
		// TODO Auto-generated method stub
		return null;
	}

}
