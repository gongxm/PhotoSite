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
		// 解决中文乱码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ServletOutputStream out = response.getOutputStream();
		// 获取到要下载的文件id:
		String fileid = request.getParameter("id");
		String sindex = request.getParameter("index");
		if (fileid == null || fileid.length() == 0) {
			out.write("该图片已经不存在！".getBytes("utf-8"));
			response.setHeader("refresh", "2;url=" + request.getContextPath() + "/index.jsp");
			return;
		}
		Image image = s.findImageById(fileid);
		// 判断图片是否存在
		if (image == null) {
			out.write("该图片已经不存在！".getBytes("utf-8"));
			response.setHeader("refresh", "2;url=" + request.getContextPath() + "/index.jsp");
			return;
		}
		int index = 1;
		if (!TextUtils.isEmpty(sindex)) {
			index = Integer.parseInt(sindex);
		}
		index--;// 传递过来的index比实际的索引大一，使它恢复正常状态

		// 取出图片组中的所有图片地址
		// String[] images = image.getImagesPath().split("#");
		String path = image.getFilepath();
		if (TextUtils.isEmpty(path)) {
			out.write("该图片已经不存在！".getBytes("utf-8"));
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
