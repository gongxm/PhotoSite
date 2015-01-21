package com.gongxm.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gongxm.utils.MyCosntants;

public class UrlDownload extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
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
	public static boolean isBusy = false;
	private static int percent;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (isBusy) {
			response.getOutputStream().write(
					("已有任务正在运行中,下载进度为"+percent+"%,即将返回后台页面！").getBytes("utf-8"));
			response.setHeader("refresh", "2;url=" + MyCosntants.url+"/admin");
			return;
		}
		isBusy = true;
		String url = request.getQueryString();
		String uri=url.split("uri=")[1];
		if (uri == null || uri.length() == 0) {
			response.getOutputStream()
					.write("uri不正确！即将返回主页！".getBytes("utf-8"));
			response.setHeader("refresh", "2;url=" + MyCosntants.url);
			return;
		}

		String realPath = getServletContext().getRealPath(
				MyCosntants.DOWNLOADDIR);
		File downloadDir = new File(realPath);
		if (!downloadDir.exists())
			downloadDir.mkdirs();
		response.getOutputStream()
		.write("即将开始下载！即将返回后台页面！".getBytes("utf-8"));
		download(downloadDir, uri);
		response.setHeader("refresh", "2;url=" + MyCosntants.url+"/admin");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private static void download(final File destDir, final String string)
			throws MalformedURLException, IOException,
			UnsupportedEncodingException {
		new Thread() {
			public void run() {
				try {
					URL url = new URL(string);
					URLConnection connection = url.openConnection();
					double length = connection.getContentLength();
					Map<String, List<String>> map = connection
							.getHeaderFields();
					List<String> list = map.get("Content-Disposition");
					String name = null;
					if (list != null) {
						String str = new String(list.get(0).getBytes(
								"iso-8859-1"), "utf-8");
						// 取到文件名
						name = str.substring(str.indexOf("filename=\"") + 10,
								str.indexOf("\"",
										str.indexOf("filename=\"") + 10));
					} else {
						name = string.substring(string.lastIndexOf("/") + 1);
					}
					if (name == null || name.length() == 0) {
						isBusy = false;
						return;
					}
					File destFile = new File(destDir, name);
					InputStream is = null;
					OutputStream out = null;
					try {
						is = url.openStream();
						out = new FileOutputStream(destFile);
						int len = 0;
						byte[] buf = new byte[1024 * 1024];
						double count = 0;
						percent = 0;
						while ((len = is.read(buf)) != -1) {
							out.write(buf, 0, len);
							count += len;
							if (percent != (int) Math.round(count / length
									* 100)) {
								percent = (int) Math
										.round(count / length * 100);
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
						isBusy=false;
					} finally {
						if (out != null) {
							try {
								out.close();
								out = null;
							} catch (IOException e) {
								e.printStackTrace();
							}
						}

						if (is != null) {
							try {
								is.close();
								is = null;
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				} catch (Exception e) {
					isBusy = false;
				}
				isBusy = false;//
			};
		}.start();

	}

}
