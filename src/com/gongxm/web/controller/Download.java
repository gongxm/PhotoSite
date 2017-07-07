package com.gongxm.web.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.bean.Image;
import com.gongxm.service.Service;
import com.gongxm.service.ServiceImpl;
import com.gongxm.utils.TextUtils;

public class Download extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Service s = new ServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �����������
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ServletOutputStream out = response.getOutputStream();
		// ��ȡ��Ҫ���ص��ļ�id:
		String fileid = request.getParameter("id");
		String sindex = request.getParameter("index");
		if (fileid == null || fileid.length() == 0) {
			out.write("��ͼƬ�Ѿ������ڣ�".getBytes("utf-8"));
			response.setHeader("refresh", "2;url=" + request.getContextPath() + "/index.jsp");
			return;
		}
		Image image = s.findImageById(fileid);
		// �ж�ͼƬ�Ƿ����
		if (image == null) {
			out.write("��ͼƬ�Ѿ������ڣ�".getBytes("utf-8"));
			response.setHeader("refresh", "2;url=" + request.getContextPath() + "/index.jsp");
			return;
		}
		int index = 1;
		if (!TextUtils.isEmpty(sindex)) {
			index = Integer.parseInt(sindex);
		}
		index--;// ���ݹ�����index��ʵ�ʵ�������һ��ʹ���ָ�����״̬

		// ȡ��ͼƬ���е�����ͼƬ��ַ
		// String[] images = image.getImagesPath().split("#");
		String path = image.getFilepath();
		if (TextUtils.isEmpty(path)) {
			out.write("��ͼƬ�Ѿ������ڣ�".getBytes("utf-8"));
			response.setHeader("refresh", "2;url=" + request.getContextPath() + "/index.jsp");
			return;
		}
		String filename = null;
		filename = image.getFilename();// +".jpg";
		String realPath = getServletContext().getRealPath("/WEB-INF/images");
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));
		path = realPath+"/"+path;
		InputStream in = new FileInputStream(path);
		int len = 0;
		byte[] buf = new byte[1024];
		while ((len = in.read(buf)) != -1) {
			out.write(buf, 0, len);
		}
		in.close();

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
