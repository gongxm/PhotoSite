package com.gongxm.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.bean.User;
import com.gongxm.bean.WebBean;
import com.gongxm.service.Service;
import com.gongxm.service.ServiceImpl;
import com.gongxm.utils.BeanFillUtil;
import com.gongxm.utils.MD5Util;

public class RegistServlet extends HttpServlet {
	private Service s=new ServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer=response.getWriter();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String repassword=request.getParameter("repassword");
		WebBean bean=BeanFillUtil.fillBean(request);
		if(!password.equals(repassword)){
			writer.write("两次输入的密码不一致！2秒后转到注册页面！");
			request.getSession().setAttribute("bean", bean);
			response.setHeader("refresh", "2;url="+request.getContextPath()+"/regist.jsp");
			return;
		}
		User user=s.findUserByName(username);
		if(user!=null){
			writer.write("该用户名已存在，请使用其他用户名注册！2秒后转到注册页面！");
			response.setHeader("refresh", "2;url="+request.getContextPath()+"/regist.jsp");
			return;
		}
		user=new User();
		user.setUsername(username);
		user.setPassword(MD5Util.MD5(password));
		s.addUser(user);
		writer.write("注册成功！2秒后转到登陆页面！");
		response.setHeader("refresh", "2;url="+request.getContextPath()+"/login.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
