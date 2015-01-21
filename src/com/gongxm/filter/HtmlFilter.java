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
//对提交的数据过滤html标签
public class HtmlFilter implements Filter {

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
			throw new RuntimeException("non http request");
		}
		
		MyHtmlRequest mrequest=new MyHtmlRequest(request);
		chain.doFilter(mrequest, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}





class MyHtmlRequest extends HttpServletRequestWrapper
{
	public MyHtmlRequest(HttpServletRequest request) {
		super(request);
	}
	@Override
	public String getParameter(String name) {
		String value=super.getParameter(name);
		if(value==null)
			return null;
		value=htmlFilter(value);
		return value;
	}

	private String htmlFilter(String value) {
		char[] chs=value.toCharArray();
		StringBuilder sb=new StringBuilder();
		for(char c:chs){
			switch(c){
			case '<':
				sb.append("&lt;");
				break;
			case '>':
				sb.append("&gt;");
				break;
			case '"':
				sb.append("&quot;");
				break;
			case '&':
				sb.append("&amp;");
				break;
			default:
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	
	
}
