package com.gongxm.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.bean.Menu;
import com.gongxm.service.ServiceImpl;
import com.gongxm.utils.DBCPUtil;
import com.google.gson.Gson;

public class getMenus extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	/*	//要执行的SQL语句
		String sql="select category from (select category,count(*) as temp from images  group by category having temp>1) as categories;";
		//获取数据源
		Connection con = DBCPUtil.getConnection();
		Gson gson=new Gson();
		try {
			Statement statement = con.createStatement();
			//执行SQL语句
			ResultSet rs = statement.executeQuery(sql);
			//查询到的数据存到这个集合里面
			List<String> menus=new ArrayList<String>();
			//判断是否有数据
			while(rs.next()){
				String result = rs.getString("category");
				menus.add(result);
			}
			statement.close();
			con.close();//关闭连接
			String json = gson.toJson(menus);
			response.getWriter().write(json);
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		List<Menu> menus = new ServiceImpl().findMenus();
		Gson gson=new Gson();
		String json = gson.toJson(menus);
		response.getWriter().write(json);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
