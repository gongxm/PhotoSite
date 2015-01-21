package com.gongxm.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;

import com.gongxm.bean.Image;
import com.gongxm.dao.ImageDao;
import com.gongxm.utils.DBCPUtil;

public class ImageDaoImpl extends BaseDao<Image> implements ImageDao {
	private QueryRunner qr=new QueryRunner(DBCPUtil.getDataSource());
	@Override
	public List<Image> findImageList(int startIndex, int pageSize) {
		try {
			return qr.query("select * from images limit ?,?", new BeanListHandler<Image>(Image.class),startIndex,pageSize);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public Image findImage(String id) {
		try {
			return qr.query("select * from images where id=?", new BeanHandler<Image>(Image.class),id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public Integer getTotalRecords() {
		try {
			return ((Long)qr.query("select count(*) from images", new MapHandler()).get("count(*)")).intValue() ;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public void clearDataBase() {
		try {
			qr.update("delete from images");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public int getCategoryRecods(String category) {
		try {
			return ((Long)qr.query("select count(*) from images where category='"+category+"'", new MapHandler()).get("count(*)")).intValue() ;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public List<Image> findCategoryImageList(String category, int startIndex,
			int pageSize) {
		try {
			return qr.query("select * from images where category='"+category+"' limit ?,?", new BeanListHandler<Image>(Image.class),startIndex,pageSize);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public Image findImageByName(String title) {
		try {
			return qr.query("select * from images where filename=?", new BeanHandler<Image>(Image.class),title);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
