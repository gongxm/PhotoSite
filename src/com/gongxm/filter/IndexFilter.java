package com.gongxm.filter;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.bean.Image;
import com.gongxm.service.Service;
import com.gongxm.service.ServiceImpl;
import com.gongxm.utils.Page;

public class IndexFilter implements Filter {
	private Service s=new ServiceImpl();
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		try {
			request = (HttpServletRequest) req;
			response = (HttpServletResponse) resp;
		} catch (Exception e) {
			throw new RuntimeException("non http request or response");
		}
		
		int totalRecods=s.getTotalRecords();
		Page page=new Page(1,totalRecods,request.getContextPath()+"/servlet/ShowList");
		int num=totalRecods/2;
		if(num<=0)
			num=1;
		Random r=new Random();
		int pageNum=r.nextInt(num);
		List<Image> list=s.findImageList(pageNum, page.getPageSize());
		page.setRecords(list);
		request.getSession().setAttribute("page", page);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	@Override
	public void destroy() {
	}

}
