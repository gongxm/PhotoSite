package com.gongxm.dao;

import java.util.List;

import com.gongxm.bean.Image;

public interface ImageDao extends Dao<Image>{
	public abstract Image findImage(String id);
	List<Image> findImageList(int startIndex, int pageSize);
	public abstract Integer getTotalRecords();
	public abstract void clearDataBase();
	public abstract int getCategoryRecods(String category);
	public abstract List<Image> findCategoryImageList(String category,
			int startIndex, int pageSize);
	public abstract Image findImageByName(String title);
}
