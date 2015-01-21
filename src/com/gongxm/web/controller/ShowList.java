package com.gongxm.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.bean.Image;
import com.gongxm.service.Service;
import com.gongxm.service.ServiceImpl;
import com.gongxm.utils.Page;

public class ShowList extends HttpServlet {
	private Service s=new ServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String index=request.getParameter("index");
		if(index==null)
			index="1";
		int totalRecods=s.getTotalRecords();
		Page page=new Page(Integer.parseInt(index),totalRecods,request.getContextPath()+"/servlet/ShowList");
		List<Image> list=s.findImageList(page.getStartNum(), page.getPageSize());
		page.setRecords(list);
		request.getSession().setAttribute("page", page);
		
		Random r=new Random();
		Page repage=new Page(1,totalRecods,request.getContextPath()+"/servlet/ShowList");
		int num=totalRecods-5;
		if(num<=0)
			num=1;
		List<Image> recomm=s.findImageList(r.nextInt(num)+1, 5);
		repage.setRecords(recomm);
		request.getSession().setAttribute("recomm", repage);
		request.getRequestDispatcher("/list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
