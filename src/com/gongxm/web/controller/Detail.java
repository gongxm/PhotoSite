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
		//ͼƬID
		String id=request.getParameter("id");
		//ͼƬ���еĵڼ���ͼƬ
		String sindex=request.getParameter("index");
		PrintWriter writer=response.getWriter();
		//�ж�ID�Ƿ�Ϊ��
		if(id==null||id.equals("")){
			writer.write("��Ҫ�ҵ�ͼƬ�����ڣ�2���ص���ҳ��");
			response.setHeader("refresh", "2;url="+request.getContextPath());
			return;
		}
		//����ID����ͼƬ
		Image image=s.findImageById(id);
		if(image==null){
			writer.write("��Ҫ�ҵ�ͼƬ�����ڣ�2���ص���ҳ��");
			response.setHeader("refresh", "2;url="+request.getContextPath());
			return;
		}
		
		//ͼƬ����
		int index=1;
		
		if(!TextUtils.isEmpty(sindex)){
			index=Integer.parseInt(sindex);
		}
		
		//ͼƬ���ҳ
		Page p=new Page(index, 1, request.getContextPath()+"/servlet/Detail?id="+id+"&index=");
		p.setPageSize(1);
		request.getSession().setAttribute("page", p);
		//��ͼƬ����ȥ
		request.getSession().setAttribute("image", image);
		
		
		int totalRecods=s.getTotalRecords();
		int num=totalRecods-5;
		if(num<=0){
			num=1;
		}
		//����Ƽ���ͼƬ��
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
