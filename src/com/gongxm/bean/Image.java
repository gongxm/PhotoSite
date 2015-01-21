package com.gongxm.bean;

import java.io.Serializable;

public class Image implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 12L;
	/**
	 * create table images(id int auto_increment,filename varchar(200),filepath varchar(500),primary key(id));
	 */
	private int id;
	private String filename;//图片组名称
	private String filepath;//图片组缩略图的链接
	private String imagesPath;//图片组中所有图片的链接
	private String category;//图片组的分类
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getImagesPath() {
		return imagesPath;
	}

	public void setImagesPath(String imagesPath) {
		this.imagesPath = imagesPath;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	

}
