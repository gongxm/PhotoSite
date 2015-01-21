package com.gongxm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
//¹ýÂÇÔà»°
public class DirtyWordsFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
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
			throw new RuntimeException("non http request");
		}
		
		DwRequest dwrequest=new DwRequest(request);
		
		chain.doFilter(dwrequest, response);
	}

	@Override
	public void destroy() {
	}

}

class DwRequest extends HttpServletRequestWrapper{
	private String[] words={"¹¨ÙþÃô","¶¹±È","¶º±È","SB"};
	public DwRequest(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		String value=super.getParameter(name);
		if(value==null)
			return null;
		for(String s:words){
			value=value.replace(s, "**");
		}
		return value;
	}
	
	
	
}
