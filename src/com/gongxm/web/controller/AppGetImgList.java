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
 * ��ȡͼƬ�б�
 * 
 * @author gongxm
 * 
 */
public class AppGetImgList extends HttpServlet {
	private Service s = new ServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ѯ�����еķ���
		String category = request.getParameter("category");
		// ��ѯ�Ŀ�ʼλ��
		String index = request.getParameter("index");
		if (index == null)
			index = "1";
		// ��ѯ��ʼλ��
		int start = Integer.parseInt(index);
		// ��Ҫ��ѯ�ļ�¼���ܼ�¼��
		int totalRecods = 0;
		if (category == null)
			totalRecods = s.getTotalRecords();
		else
			totalRecods = s.getCategoryRecods(category);// ����Ǵ����Ͳ����ģ���ѯ����ļ�¼����
		// �����ѯ���ļ�¼
		List<Image> list = null;
		if (category == null)
			list = s.findImageList(start, 10);
		else
			list = s.findCategoryImageList(category, start, 10);
		// �Ƿ�����һҳ
		boolean hasNext = (start + 10) < totalRecods;// �����¼���������ܼ�¼����û����һҳ�ˡ�
		// ��ѯ�������м�¼
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
