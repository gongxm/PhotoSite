package com.gongxm.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.bean.Image;
import com.gongxm.service.Service;
import com.gongxm.service.ServiceImpl;

public class Download extends HttpServlet {
	private Service s=new ServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//解决中文乱码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ServletOutputStream out=response.getOutputStream();
		//获取到要下载的文件id:
		String fileid=request.getParameter("id");
		if(fileid==null||fileid.length()==0){
			out.write("该图片已经不存在！".getBytes("utf-8"));
			response.setHeader("refresh", "2;url="+request.getContextPath()+"/index.jsp");
			return;
		}
		Image image=s.findImageById(fileid);
		//判断图片是否存在
		if(image==null){
			out.write("该图片已经不存在！".getBytes("utf-8"));
			response.setHeader("refresh", "2;url="+request.getContextPath()+"/index.jsp");
			return;
		}
		String filename=image.getFilename();
		String filepath=image.getFilepath();
		File file=new File(filepath);
		//判断图片是否存在
		/*if(!file.exists()){
			out.write("该图片已经不存在！".getBytes("utf-8"));
			response.setHeader("refresh", "2;url="+request.getContextPath()+"/index.jsp");
			return;
		}*/
		InputStream in=null;
		if(file.exists())
			in=new FileInputStream(file);
		else
			in=new URL(filepath).openStream();
		if(in==null){
			out.write("该图片已经不存在！".getBytes("utf-8"));
			response.setHeader("refresh", "2;url="+request.getContextPath()+"/index.jsp");
			return;
		}
		response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(filename,"utf-8"));
		int len=0;
		byte[] buf=new byte[1024];
		while((len=in.read(buf))!=-1){
			out.write(buf, 0, len);
		}
		in.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
