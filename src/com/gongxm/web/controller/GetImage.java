package com.gongxm.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.bean.Image;
import com.gongxm.service.Service;
import com.gongxm.service.ServiceImpl;

/**
 * Servlet implementation class GetImage
 */
@WebServlet("/getImage")
public class GetImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Service s = new ServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String id = req.getParameter("id");
		Image image = s.findImageById(id);
		String path = req.getSession().getServletContext().getRealPath("/WEB-INF/images") +"/"+ image.getFilepath();
		File file = new File(path);
		if (file.exists()) {
			String name = file.getName();
			resp.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "utf-8"));
			FileInputStream fis = new FileInputStream(file);
			ServletOutputStream out = resp.getOutputStream();
			byte[] arr = new byte[1024];
			int len;
			while ((len = fis.read(arr)) != -1) {
				out.write(arr, 0, len);
			}
			fis.close();
		}
	}

}
