package com.gongxm.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.bean.CollectReg;
import com.gongxm.service.Service;
import com.gongxm.service.ServiceImpl;

public class RegManager extends HttpServlet {
	private Service s = new ServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<CollectReg> regs=s.findAllReg();
		request.getRequestDispatcher("/WEB-INF/regmanager.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
