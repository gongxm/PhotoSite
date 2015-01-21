package com.gongxm.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class GzipFilter implements Filter {

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
		String gzip=request.getHeader("Accept-Encoding");
		MyResponse myresponse=new MyResponse(response);
		chain.doFilter(request, myresponse);
		byte[] buf=myresponse.getBytes();
		if(gzip.contains(gzip)){
			System.out.println("—πÀı«∞£∫"+buf.length);
			ByteArrayOutputStream bao=new ByteArrayOutputStream();
			GZIPOutputStream gout=new GZIPOutputStream(bao);
			gout.write(buf);
			gout.close();
			buf=bao.toByteArray();
			System.out.println("—πÀı∫Û£∫"+buf.length);
			response.setHeader("Content-Encoding", "gzip");
		}
		response.getOutputStream().write(buf);
	}

	@Override
	public void destroy() {
	}

}


class MyResponse extends HttpServletResponseWrapper{
	private ByteArrayOutputStream bao=new ByteArrayOutputStream();
	private PrintWriter pw=null;
	public MyResponse(HttpServletResponse response){
		super(response);
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return new MyOutputStream(bao);
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		pw=new PrintWriter(new OutputStreamWriter(bao,super.getCharacterEncoding()));
		return pw;
	}

	public byte[] getBytes() {
		if(pw!=null)
			pw.close();
		return bao.toByteArray();
	}
	
	
}

class MyOutputStream extends ServletOutputStream
{
	private ByteArrayOutputStream bao;
	public MyOutputStream(ByteArrayOutputStream bao){
		this.bao=bao;
	}
	@Override
	public void write(int b) throws IOException {
		bao.write(b);
	}
	
}