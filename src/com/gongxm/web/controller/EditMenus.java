package com.gongxm.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.bean.Menu;
import com.gongxm.bean.User;
import com.gongxm.service.Service;
import com.gongxm.service.ServiceImpl;
import com.gongxm.utils.MyCosntants;
import com.gongxm.utils.TextUtils;

public class EditMenus extends HttpServlet {
	private Service s = new ServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operate = request.getParameter("operate");
		String sid = request.getParameter("id");
		String menu = request.getParameter("menu");
		String name = request.getParameter("name");
		String startIndex = request.getParameter("startIndex");
		String endIndex = request.getParameter("endIndex");
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.getWriter().write("对不起，您还没有登陆，请先登陆再操作！1秒后转到登陆页面！");
			response.setHeader("refresh", "1;url=" + request.getContextPath()
					+ "/login.jsp");
			return;
		}
		if (!"root".equals(user.getPermission())) {
			response.getWriter().write("对不起，您的权限不足，无法进行该操作！1秒后转到主页！");
			response.setHeader("refresh", "1;url=" + MyCosntants.url);
			return;
		}
		
		int id=0;
		int start=0;
		int end=0;
		
		//转换int
		if(!TextUtils.isEmpty(sid)){
			id=Integer.parseInt(sid);
		}
		if(!TextUtils.isEmpty(startIndex)){
			start=Integer.parseInt(startIndex);
		}
		if(!TextUtils.isEmpty(endIndex)){
			end=Integer.parseInt(endIndex);
		}

		if (!TextUtils.isEmpty(operate)) {
			if ("delete".equals(operate)) {
				if (!TextUtils.isEmpty(sid)) {
					s.deleteMenu(sid);
				}
			} else if ("add".equals(operate)) {
				if (!TextUtils.isEmpty(menu) && !TextUtils.isEmpty(name)) {
					Menu m1 = s.findMenu(menu);// 先查找是否已存在
					if (m1 == null) {// 如果不存在就添加
						Menu m = new Menu(menu, name,start,end);
						s.addMenu(m);
					}
				}
			}else if("modify".equals(operate)&&!TextUtils.isEmpty(sid)){
				Menu m = new Menu(menu, name, start,end);
				m.setId(id);
				s.updateMenu(m);
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
