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
import com.gongxm.utils.TextUtils;

public class Detail extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Service s=new ServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//图片ID
		String id=request.getParameter("id");
		//图片组中的第几张图片
		String sindex=request.getParameter("index");
		PrintWriter writer=response.getWriter();
		//判断ID是否为空
		if(id==null||id.equals("")){
			writer.write("您要找的图片不存在！2秒后回到主页！");
			response.setHeader("refresh", "2;url="+request.getContextPath());
			return;
		}
		//根据ID查找图片
		Image image=s.findImageById(id);
		if(image==null){
			writer.write("您要找的图片不存在！2秒后回到主页！");
			response.setHeader("refresh", "2;url="+request.getContextPath());
			return;
		}
		
		//图片索引
		int index=1;
		
		if(!TextUtils.isEmpty(sindex)){
			index=Integer.parseInt(sindex);
		}
		
		//图片组分页
		Page p=new Page(index, 1, request.getContextPath()+"/servlet/Detail?id="+id+"&index=");
		p.setPageSize(1);
		request.getSession().setAttribute("page", p);
		//把图片传过去
		request.getSession().setAttribute("image", image);
		
		
		int totalRecods=s.getTotalRecords();
		int num=totalRecods-5;
		if(num<=0){
			num=1;
		}
		//随机推荐的图片组
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
