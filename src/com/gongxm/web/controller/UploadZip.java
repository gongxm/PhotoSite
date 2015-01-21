package com.gongxm.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.gongxm.utils.MyCosntants;

public class UploadZip extends HttpServlet {
	private File destFile;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.println("ERROR, please using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
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
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// �Ƿ���ϴ�
		boolean isMultipartFormData = ServletFileUpload
				.isMultipartContent(request);
		if (!isMultipartFormData) {
			response.getOutputStream().write("���Ǳ��ϴ������ݣ�����������ҳ��".getBytes("utf-8"));
			response.setHeader("refresh", "2;url="+MyCosntants.url);
			return;
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);// ���ĵĽ�����
		try {
			List<FileItem> items = upload.parseRequest(request);
			if(items.size()==0){
				response.getOutputStream().write("û���ϴ��κ��ļ�������������ҳ��".getBytes("utf-8"));
				response.setHeader("refresh", "2;url="+MyCosntants.url);
				return;
			}
			for (FileItem item : items) {
				if (item.isFormField()) {// ��ͨ�ֶ�
				} else {
					processFile(item);
				}
			}
		} catch (FileUploadException e) {
			response.getOutputStream().write("�ļ��ϴ��쳣��".getBytes("utf-8"));
			return;
		}
		
		getServletContext().setAttribute("destFile", destFile);
		response.getOutputStream().write("�ļ��ϴ��ɹ���������ѹ����".getBytes("utf-8"));
		response.setHeader("refresh", "2;url="+request.getContextPath()+"/servlet/Unzip");
	}

	/**
	 * ���ϴ����ļ�д�뵽ָ�����ļ���
	 * 
	 * @param item
	 */
	private void processFile(FileItem item) {
		String dirpath = getServletContext().getRealPath("/WEB-INF");
		File dir = new File(dirpath,"IMGZIP");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// ��ȡ���ϴ����ļ����ļ���
		String fileName = item.getName();
		if (fileName != null)
			fileName = FilenameUtils.getName(fileName);
		try {
			destFile = new File(dir,fileName);
			item.write(destFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
