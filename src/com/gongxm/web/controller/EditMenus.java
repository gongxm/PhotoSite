package com.gongxm.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.bean.Menu;
import com.gongxm.service.Service;
import com.gongxm.service.ServiceImpl;
import com.gongxm.utils.TextUtils;

public class EditMenus extends HttpServlet {
	private Service s = new ServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String menu = request.getParameter("menu");
		String name = request.getParameter("name");
		if (!TextUtils.isEmpty(id)) {
			s.deleteMenu(id);
		}
		if (!TextUtils.isEmpty(menu) && !TextUtils.isEmpty(name)) {
			Menu m1 = s.findMenu(menu);//先查找是否已存在 
			if (m1 == null) {//如果不存在就添加
				Menu m = new Menu(menu, name);
				s.addMenu(m);
			}
		}
		List<Menu> menus = s.findMenus();
		request.getSession().setAttribute("menus", menus);
		request.getRequestDispatcher("/WEB-INF/menus.jsp").forward(request,
				response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
