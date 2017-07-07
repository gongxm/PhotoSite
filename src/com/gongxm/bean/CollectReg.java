package com.gongxm.bean;

public class CollectReg {
	private String name;   //规则名称
	private String category; //分类
	private String url;      //列表url,以*代替index
	private int startIndex;  //列表开始页码
	private int endIndex;//列表结束页码
	private String charset;//采集网站的字符集
	
	
	private String startTag;//图片组链接开始区域
	private String endTag;//图片组链接结束区域
	private String imgGroupReg;//图片组链接匹配规则
	
	private String titleStartTag;//标题开始
	
}
