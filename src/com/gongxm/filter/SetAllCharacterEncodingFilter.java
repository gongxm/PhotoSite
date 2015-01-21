package com.gongxm.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class SetAllCharacterEncodingFilter implements Filter {
	private FilterConfig config;
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config=config;
	}

	@Override
	public void destroy() {
	}

	@Override
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
		
		//获取配置文件中的编码：
		String encoding=config.getInitParameter("encoding");
		if(encoding==null||encoding.equals(""))
			encoding="utf-8";
			
		
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		response.setContentType("text/html;charset="+encoding);
		MyRequest mrequest=new MyRequest(request);
		chain.doFilter(mrequest, response);
	}

}

class MyRequest extends HttpServletRequestWrapper{

	public MyRequest(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		String value=super.getParameter(name);
		if(value==null)
			return null;
		if("get".equalsIgnoreCase(super.getMethod())){
			try {
				value=new String(value.getBytes("iso-8859-1"),super.getCharacterEncoding());
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
		return value;
	}
	
}
