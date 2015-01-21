package com.gongxm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gongxm.bean.User;
import com.gongxm.service.Service;
import com.gongxm.service.ServiceImpl;
import com.gongxm.utils.MD5Util;
import com.gongxm.utils.OnlineUtil;

public class AutoLoginFilter implements Filter {
	private Service s = new ServiceImpl();

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
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			Cookie cs[] = request.getCookies();
			if (cs != null && cs.length > 0) {
				for (Cookie c : cs) {
					if ("user".equals(c.getName())) {
						String username = c.getValue().split("_")[0];
						String password = c.getValue().split("_")[1];
						username =MD5Util.base64DeEncod(username);
						HttpSession se=OnlineUtil.getOnlineUser().get(username);
						if(se!=null){
							user = s.findUser(username, password);
							if (user != null) {
								request.getSession().setAttribute("user", user);
							}
						}
						break;
					}
				}
			}
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
