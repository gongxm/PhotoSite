package com.gongxm.web.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gongxm.bean.User;
import com.gongxm.utils.OnlineUtil;

public class KitUser extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			response.getWriter().write("对不起，您还没有登陆，请先登陆再操作！2秒后转到登陆页面！");
			response.setHeader("refresh", "2;url="+request.getContextPath()+"/login.jsp");
			return;
		}
		if(!"root".equals(user.getPermission())){
			response.getWriter().write("对不起，您的权限不足，无法进行该操作！2秒后转到主页！");
			response.setHeader("refresh", "2;url="+request.getContextPath());
			return;
		}
		String username=request.getParameter("username");
		if(username!=null){
			OnlineUtil.remove(username);
		}
		Map<String,HttpSession> map=OnlineUtil.getOnlineUser();
		request.getSession().setAttribute("online", map);
		request.getRequestDispatcher("/WEB-INF/kituser.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
