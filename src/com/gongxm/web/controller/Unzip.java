package com.gongxm.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import com.gongxm.utils.MyCosntants;

public class Unzip extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		File destFile = (File) getServletContext().getAttribute("destFile");
		if(destFile == null){
			String dir = getServletContext().getRealPath(MyCosntants.DOWNLOADDIR);
			destFile =new File(dir,"temp.zip");
		}
		if (destFile == null || !destFile.exists()||destFile.isDirectory()) {
			response.getWriter().write("需要解压的文件不存在！即将返回后台页面！");
			response.setHeader("refresh", "2;url="+MyCosntants.url+"/admin");
			return;
		}
		unzip(destFile);
		getServletContext().setAttribute("destFile", null);// 解压完成后把这个属性置为空！
		response.getWriter().write("解压文件成功！即将更新图片！");
		response.setHeader("refresh", "2;url=" + request.getContextPath()+"/servlet/UpdataImages");
	}

	/**
	 * 解压zip,
	 * 
	 * @param zipFile
	 */
	public  void unzip(File zfile){
		String targetPath=getServletContext().getRealPath("/WEB-INF/images");
		BufferedOutputStream bos=null;
		BufferedInputStream bis=null;
		try{
		ZipFile zipFile = new ZipFile(zfile);
		Enumeration<ZipEntry> emu = zipFile.getEntries();
		while (emu.hasMoreElements()) {
			ZipEntry entry = emu.nextElement();

			String fileName = entry.getName().toLowerCase();
			bis = new BufferedInputStream(
					zipFile.getInputStream(entry));
			File file = new File(targetPath,fileName);
			if(entry.isDirectory()){
				if(!file.exists())
					file.mkdirs();
				continue;
			}
			// 一次读1M
			int BUFFER = 1024*1024;
			FileOutputStream fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos, BUFFER);

			int count;
			byte data[] = new byte[BUFFER];
			while ((count = bis.read(data, 0, BUFFER)) != -1) {
				bos.write(data, 0, count);
			}
			bos.flush();
			bos.close();
			bis.close();
		}
		zipFile.close();
		}catch(Exception excption){
			excption.printStackTrace();
		}finally{
			if(bos!=null)
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if(bis!=null)
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
