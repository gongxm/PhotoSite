package com.gongxm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.bean.User;

public class PermissionFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override//权限过滤，不登陆不能访问列表页
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=null;
		HttpServletResponse response=null;
		try{
			request=(HttpServletRequest)req;
			response=(HttpServletResponse)resp;
		}catch(Exception e){
			throw new RuntimeException("non http request or response");
		}
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			response.getWriter().write("对不起，您还没有登陆，请先登陆再访问！2秒后转到登陆页面！");
			response.setHeader("refresh", "2;url="+request.getContextPath()+"/login.jsp");
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
