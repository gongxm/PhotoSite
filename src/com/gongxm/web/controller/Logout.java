package com.gongxm.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.utils.MyCosntants;

public class Logout extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("user");
		Cookie cookie=new Cookie("user","");
		cookie.setPath(request.getContextPath());
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		response.getWriter().write("ע���ɹ���2���ת����ҳ��");
		response.setHeader("refresh", "2;url="+MyCosntants.url);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
