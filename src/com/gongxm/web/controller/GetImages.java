package com.gongxm.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.gongxm.bean.Image;
import com.gongxm.bean.Menu;
import com.gongxm.service.Service;
import com.gongxm.service.ServiceImpl;
import com.gongxm.utils.MyCosntants;
import com.gongxm.utils.StringUtils;
import com.gongxm.utils.TextUtils;

public class GetImages extends HttpServlet {
	// 所有的列表页面链接
	private List<String> urls = new ArrayList<String>();
	// 所有的图片组链接
	private List<String> imgGroupPages = new ArrayList<String>();
	// 标记是否获取到所有的图片页面链接，当count=urls的大小时就说明获取到所有图片页面链接了

	private ResourceBundle bundle = ResourceBundle.getBundle("config");
	// 采集网站的编码集
	private String charset;
	// 当前类别
	private String category;

	// 业务逻辑
	private Service s = new ServiceImpl();
	private int start;
	private int end;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		// 获取传输过来的参数
		category = request.getParameter("category");
		if(TextUtils.isEmpty(category)){
			out.println("请选择正确的分类！");
			response.setHeader("refresh", "1;url=" + MyCosntants.url + "/admin");
			return;
		}
		String startIndex = request.getParameter("startIndex");
		String endIndex = request.getParameter("endIndex").split("\\?")[0];
		if (TextUtils.isEmpty(startIndex))
			startIndex = "1";
		if (TextUtils.isEmpty(endIndex)) {
			endIndex = "1";
		}
		 start = Integer.parseInt(startIndex);
		 end = Integer.parseInt(endIndex);
		
		if(start==0&&end==0){
			Menu menu = s.findMenu(category);
			start=menu.getStartIndex();
			end=menu.getEndIndex();
		}
		
		System.out.println(category+","+start+","+end);
		
		// 获取采集网站的编码集
		charset = bundle.getString("charset");
		// 当前类别
		response.setContentType("text/html");
		new Thread() {
			public void run() {
				// 获取到所有列表链接
				System.out.println("开始获取列表链接……");
				getList(start, end);
				System.out.println("已获取到所有列表链接，共" + urls.size() + "条记录");
				System.out.println("开始获取所有图片组页面链接……");
				getAllFirstPages();
				System.out.println("已获取到所有图片组链接，共" + imgGroupPages.size()
						+ "条记录");
				System.out.println("开始获取所有图片页面链接……");
				getAllImages();
				category=null;
			};
		}.start();
		out.println("<font color='red'>采集开始了，即将回到后台</font>");
		response.setHeader("refresh", "2;url=" + MyCosntants.url + "/admin");
	}

	/**
	 * 网络请求
	 * 
	 * @param url
	 *            需要请求的url
	 * @return 返回请求到的数据
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public String getHttpContent(String url) throws ClientProtocolException,
			IOException {
		// 创建HttpClient实例
		HttpClient httpclient = new DefaultHttpClient();
		// 创建Get方法实例
		HttpGet httpgets = new HttpGet(url);
		HttpResponse response;
		response = httpclient.execute(httpgets);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			InputStream instreams = entity.getContent();
			String str = StringUtils.readStream(instreams, charset);
			httpgets.abort();
			return str;
		}
		httpgets = null;
		httpclient = null;
		return null;
	}

	/**
	 * 获取所有的图片页面的链接&&获取所有图片的链接
	 */
	private void getAllImages() {
		for (int x = 0; x < imgGroupPages.size(); x++) {
			String url = imgGroupPages.get(x);
			try {
				String content = getHttpContent(url);

				if (content == null) {
					System.out.println("跳过！");
					continue;
				}
				String num_content = content
						.split("<div class=\"pages c mt5\">")[1]
						.split("next_link")[0];
				// 获取图片名称
				String title = content.split("<title>")[1]
						.split("_美女之家</title>")[0];
				Image img = s.findImageByName(title);
				if (img != null) {
					System.out.println("该记录已存在，跳过！");
					continue;
				}
				//获取图片缩略图
				String icon_content=content.split("<div class=\"art_picture\">")[1].split("<div class=\"mt5 art_txt\">")[0];
				String icon_reg = bundle.getString("imgIconReg");
				Pattern icon_p = Pattern.compile(icon_reg);
				Matcher icon_m = icon_p.matcher(icon_content);
				String iconPath=null;
				if(icon_m.find()){
					iconPath=icon_m.group();
					System.out.println("缩略图="+iconPath);
				}else{
					System.out.println("找不到缩略图，跳过！");
					continue;
				}
				// 获取图片页数
				String num = num_content.split("共")[1].split("页")[0];
				int imagePages = Integer.parseInt(num);
				StringBuilder urls = new StringBuilder();
				// 获取所有图片页面链接
				try {
					for (int i = 1; i <= imagePages; i++) {
						String imagePagesUrl = null;
						if (i == 1)
							imagePagesUrl = url;
						else
							imagePagesUrl = url.split("\\.html")[0] + "_" + i
									+ ".html";
						content = getHttpContent(imagePagesUrl);
						content = content
								.split("<div class=\"content re\" id=\"picView\">")[1]
								.split("function _load()")[0];
						String reg = "http://.{2,70}\\.jpg";
						Pattern p = Pattern.compile(reg);
						Matcher m = p.matcher(content);
						if (m.find()) {
							String imgurl = m.group();
							urls.append(imgurl + "#");
							System.out.println("title=" + title + "  imgurl="
									+ imgurl);
						}else{
							String reg2 = "uploads/.{5,100}\\.jpg";
							Pattern p2 = Pattern.compile(reg2);
							Matcher m2 = p2.matcher(content);
							if(m2.find()){
								String imgurl =bundle.getString("base_url")+ m2.group();
								urls.append(imgurl + "#");
								System.out.println("title2=" + title + "  imgurl="
										+ imgurl);
							}
						}
					}
				} catch (Exception e) {
					System.out.println("图片页面读取异常,下载失败！原因：" + e.getMessage());
				}
				if(urls.length()==0)//如果没有查找到内容，跳过
				{
					System.out.println("没有查找到内容，跳过");
					continue;
				}
				urls.setLength(urls.length() - 1);// 去掉最后一个#号
				Image image = new Image();
				image.setFilename(title);
				image.setFilepath(iconPath);
				image.setImagesPath(urls.toString());
				image.setCategory(category);
				s.addImg(image);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("url=" + url);
				System.out.println("图片组页面读取异常,下载失败！原因：" + e.getMessage());
			}
		}
	}

	/**
	 * 获取所有的图片组的链接
	 * 
	 * @throws IOException
	 * @throws MalformedURLException
	 */
	private void getAllFirstPages() {
		for (final String url : urls) {
			System.out.println(url);
			try {
				String content = getHttpContent(url);
				// 获取包含图片页面链接的那部分内容
				content = content.split(bundle.getString("startTag"))[1]
						.split(bundle.getString("endTag"))[0];
				// 图片页面超链接匹配规则
				String reg = bundle.getString("imgPageReg");
				// 图片组链接匹配规则
				Pattern p = Pattern.compile(reg);
				Matcher m = p.matcher(content);
				while (m.find()) {
					String page_url = m.group();
					imgGroupPages.add(page_url);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("列表页面读取异常!" + url + "原因：" + e.getMessage());
			}
		}
	}

	/**
	 * 获取所有的列表
	 */
	private void getList(int start, int end) {
		String url = bundle.getString("base_url") + category
				+ bundle.getString("list_url");
		String[] urlParts = url.split("#");
		for (int x = start; x <= end; x++) {
			String httpUrl = urlParts[0] + x + urlParts[1];
			urls.add(httpUrl);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
