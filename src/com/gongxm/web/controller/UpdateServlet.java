package com.gongxm.web.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.bean.Image;
import com.gongxm.service.Service;
import com.gongxm.service.ServiceImpl;
import com.gongxm.utils.MyCosntants;

public class UpdateServlet extends HttpServlet {
	private Service s=new ServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//HTML中屏蔽以下内容，不更新图片文件夹
		String realPath=getServletContext().getRealPath("/WEB-INF/images");
		File file=new File(realPath);
		clearDataBase();
		treeWalk(file,response);
		response.getWriter().write("<font color='green' size=5>图片更新成功！即将回到主页</font><br/>");
		response.setHeader("refresh", "2;url="+MyCosntants.url);/**/
	}

	//清空数据库
	private void clearDataBase() {
		s.clearDataBase();
	}

	//遍历图片文件夹，图片信息存储到数据库中
	private void treeWalk(File file,HttpServletResponse response) throws IOException {
		if(file==null)
			return;
		if(file.isDirectory()){
			File[] files=file.listFiles();
			if(files==null||files.length==0){
				return;
			}
			for(File f:files)
				treeWalk(f,response);
		}else{
			Image image=new Image();
			image.setFilename(file.getName());
			image.setFilepath(file.getAbsolutePath());
			s.addImg(image);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
