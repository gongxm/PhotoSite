package com.gongxm.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

public class Detail extends HttpServlet {
	private Service s=new ServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id=request.getParameter("id");
		PrintWriter writer=response.getWriter();
		if(id==null||id.equals("")){
			writer.write("您要找的图片不存在！2秒后回到主页！");
			response.setHeader("refresh", "2;url="+request.getContextPath());
			return;
		}
		Image image=s.findImageById(id);
		request.getSession().setAttribute("image", image);
		
		int totalRecods=s.getTotalRecords();
		int num=totalRecods-5;
		if(num<=0){
			num=1;
		}
		Page page=new Page(1,totalRecods,request.getContextPath()+"/servlet/ShowList");
		Random r=new Random();
		List<Image> list=s.findImageList(r.nextInt(num)+1, 5);
		page.setRecords(list);
		request.getSession().setAttribute("recomm", page);
		request.getRequestDispatcher("/detail.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
