package com.gongxm.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.bean.Menu;
import com.gongxm.bean.User;
import com.gongxm.service.Service;
import com.gongxm.service.ServiceImpl;

public class Test extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Service s = new ServiceImpl();
		List<Menu> menus = s.findMenus();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		if(menus!=null&& menus.size()>0){
			for(Menu menu:menus){
				out.println(menu.getMenu()+":"+menu.getName()+"<br/>");
			}
		}
		//创建菜单表
//		create table menu (id int auto_increment primary key,menu varchar(100),name varchar(100));
		
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletInputStream stream = request.getInputStream();
		String string = StringUtils.readStream(stream, request.getCharacterEncoding());
		System.out.println(string);
		doGet(request, response);
	}

}
