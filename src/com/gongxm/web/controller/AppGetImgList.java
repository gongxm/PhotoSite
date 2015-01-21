package com.gongxm.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.bean.Image;
import com.gongxm.bean.ListJson;
import com.gongxm.service.Service;
import com.gongxm.service.ServiceImpl;
import com.google.gson.Gson;

/**
 * 获取图片列表
 * 
 * @author gongxm
 * 
 */
public class AppGetImgList extends HttpServlet {
	private Service s = new ServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 查询条件中的分类
		String category = request.getParameter("category");
		// 查询的开始位置
		String index = request.getParameter("index");
		if (index == null)
			index = "1";
		// 查询开始位置
		int start = Integer.parseInt(index);
		// 所要查询的记录的总记录数
		int totalRecods = 0;
		if (category == null)
			totalRecods = s.getTotalRecords();
		else
			totalRecods = s.getCategoryRecods(category);// 如果是带类型参数的，查询分类的记录数。
		// 保存查询到的记录
		List<Image> list = null;
		if (category == null)
			list = s.findImageList(start, 10);
		else
			list = s.findCategoryImageList(category, start, 10);
		// 是否还有下一页
		boolean hasNext = (start + 10) < totalRecods;// 如果记录条数大于总记录，则没有下一页了。
		// 查询到的所有记录
		ListJson obj = new ListJson();
		obj.setList(list);
		obj.setHasNext(hasNext);
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		response.getWriter().write(json);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
