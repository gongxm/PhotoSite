package com.gongxm.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.bean.User;
import com.gongxm.service.Service;
import com.gongxm.service.ServiceImpl;
import com.gongxm.utils.MD5Util;
import com.gongxm.utils.MyCosntants;

public class LoginServlet extends HttpServlet {
	private Service s = new ServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		password = MD5Util.MD5(password);
		User user = s.findUser(username, password);
		if (user == null) {
			writer.write("�û������������1���ص���½ҳ��");
			response.setHeader("refresh", "1;url=" + request.getContextPath()
					+ "/login.jsp");
			return;
		}
		if (request.getParameter("remember") != null) {
			Cookie cookie = new Cookie("user", MD5Util.base64Encoding(username)
					+ "_" + password);
			cookie.setPath(request.getContextPath());
			cookie.setMaxAge(Integer.MAX_VALUE);
			response.addCookie(cookie);
		}
		request.getSession().setAttribute("user", user);
		if ("root".equals(user.getPermission())) {
			writer.write("��½�ɹ���1���ת����̨��");
			response.setHeader("refresh", "1;url=" + MyCosntants.url + "/admin");
		} else {
			writer.write("��½�ɹ���1���ת����ҳ��");
			response.setHeader("refresh", "1;url=" + MyCosntants.url
					+ "/index.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
