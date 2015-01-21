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
import com.gongxm.service.Service;
import com.gongxm.service.ServiceImpl;
import com.gongxm.utils.MyCosntants;
import com.gongxm.utils.StringUtils;
import com.gongxm.utils.TextUtils;

public class GetImages extends HttpServlet {
	// ���е��б�ҳ������
	private List<String> urls = new ArrayList<String>();
	// ���е�ͼƬ������
	private List<String> imgGroupPages = new ArrayList<String>();
	// ���е�ͼƬ������ͼ����
	private List<String> imgGroupPageIcons = new ArrayList<String>();
	// ����Ƿ��ȡ�����е�ͼƬҳ�����ӣ���count=urls�Ĵ�Сʱ��˵����ȡ������ͼƬҳ��������

	private ResourceBundle bundle = ResourceBundle.getBundle("config");
	// �ɼ���վ�ı��뼯
	private String charset;
	// ��ǰ���
	private String category;

	// ҵ���߼�
	private Service s = new ServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡ��������Ĳ���
		category = request.getParameter("category");
		String startIndex = request.getParameter("startIndex");
		String endIndex = request.getParameter("endIndex").split("\\?")[0];
		if (TextUtils.isEmpty(startIndex))
			startIndex = "1";
		final int start = Integer.parseInt(startIndex);
		if (TextUtils.isEmpty(endIndex)) {
			endIndex = "1";
		}
		final int end = Integer.parseInt(endIndex);
		// ��ȡ�ɼ���վ�ı��뼯
		charset = bundle.getString("charset");
		// ��ǰ���
		// category = bundle.getString("category");
		response.setContentType("text/html");
		new Thread() {
			public void run() {
				// ��ȡ�������б�����
				System.out.println("��ʼ��ȡ�б����ӡ���");
				getList(start, end);
				System.out.println("�ѻ�ȡ�������б����ӣ���" + urls.size() + "����¼");
				System.out.println("��ʼ��ȡ����ͼƬ��ҳ�����ӡ���");
				getAllFirstPages();
				System.out.println("�ѻ�ȡ������ͼƬ�����ӣ���" + imgGroupPages.size()
						+ "����¼");
				System.out.println("��ʼ��ȡ����ͼƬҳ�����ӡ���");
				getAllImages();
			};
		}.start();

		PrintWriter out = response.getWriter();
		out.println("<font color='red'>�ɼ���ʼ�ˣ������ص���̨</font>");
		response.setHeader("refresh", "2;url=" + MyCosntants.url + "/admin");
	}

	/**
	 * ��������
	 * 
	 * @param url
	 *            ��Ҫ�����url
	 * @return �������󵽵�����
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public String getHttpContent(String url) throws ClientProtocolException,
			IOException {
		// ����HttpClientʵ��
		HttpClient httpclient = new DefaultHttpClient();
		// ����Get����ʵ��
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
	 * ��ȡ���е�ͼƬҳ�������&&��ȡ����ͼƬ������
	 */
	private void getAllImages() {
		for (int x = 0; x < imgGroupPages.size(); x++) {
			String url = imgGroupPages.get(x);
			try {
				String content = getHttpContent(url);

				if (content == null) {
					System.out.println("������");
					continue;
				}
				String num_content = content
						.split("<div class=\"pages c mt5\">")[1]
						.split("next_link")[0];
				// ��ȡͼƬ����
				String title = content.split("<title>")[1]
						.split("_��Ů֮��</title>")[0];
				Image img = s.findImageByName(title);
				if (img != null) {
					System.out.println("�ü�¼�Ѵ��ڣ�������");
					continue;
				}
				// ��ȡͼƬҳ��
				String num = num_content.split("��")[1].split("ҳ")[0];
				int imagePages = Integer.parseInt(num);
				StringBuilder urls = new StringBuilder();
				// ��ȡ����ͼƬҳ������
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
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("ͼƬҳ���ȡ�쳣,����ʧ�ܣ�ԭ��" + e.getMessage());
				}
				if(urls.length()==0)//���û�в��ҵ����ݣ�����
				{
					System.out.println("û�в��ҵ����ݣ�����");
					continue;
				}
				urls.setLength(urls.length() - 1);// ȥ�����һ��#��
				Image image = new Image();
				image.setFilename(title);
				image.setFilepath(imgGroupPageIcons.get(x));
				image.setImagesPath(urls.toString());
				image.setCategory(category);
				s.addImg(image);

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("url=" + url);
				System.out.println("ͼƬ��ҳ���ȡ�쳣,����ʧ�ܣ�ԭ��" + e.getMessage());
			}
		}
	}

	/**
	 * ��ȡ���е�ͼƬ�������
	 * 
	 * @throws IOException
	 * @throws MalformedURLException
	 */
	private void getAllFirstPages() {
		for (final String url : urls) {
			try {
				String content = getHttpContent(url);
				// ��ȡ����ͼƬҳ�����ӵ��ǲ�������
				content = content.split(bundle.getString("startTag"))[1]
						.split(bundle.getString("endTag"))[0];
				// ͼƬҳ�泬����ƥ�����
				// String base_url = bundle.getString("base_url");
				String reg = bundle.getString("imgPageReg");
				// ͼƬ������ƥ�����
				Pattern p = Pattern.compile(reg);
				Matcher m = p.matcher(content);
				while (m.find()) {
					String page_url = m.group();
					imgGroupPages.add(page_url);
				}
				// ͼƬ������ͼ����ƥ�����
				String reg2 = bundle.getString("imgGroupIconReg");
				Pattern p2 = Pattern.compile(reg2);
				Matcher m2 = p2.matcher(content);
				while (m2.find()) {
					String icon_url = m2.group();
					imgGroupPageIcons.add(icon_url);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("�б�ҳ���ȡ�쳣!" + url + "ԭ��" + e.getMessage());
			}
		}
	}

	/**
	 * ��ȡ���е��б�
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
