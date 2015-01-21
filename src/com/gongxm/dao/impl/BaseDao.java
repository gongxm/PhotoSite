package com.gongxm.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.apache.commons.dbutils.QueryRunner;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.gongxm.dao.Dao;
import com.gongxm.utils.DBCPUtil;
import com.gongxm.utils.HibernateUtil;

public class BaseDao<T> implements Dao<T> {
	private Class<?> clazz;
	public BaseDao(){
		ParameterizedType type=(ParameterizedType)this.getClass().getGenericSuperclass();
		clazz=(Class<?>)type.getActualTypeArguments()[0];
	}
	@Override
	public void add(T t) {
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		session.save(t);
		tx.commit();
		session.close();
	}

	@Override
	public void update(T t) {
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		session.update(t);
		tx.commit();
		session.close();
	}

	@Override
	public void delete(Serializable id) {
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		session.delete(id);
		tx.commit();
		session.close();
	}

	@Override
	@SuppressWarnings("unchecked")
	public T findOne(Serializable id) {
		Session session=HibernateUtil.getSession();
		T bean=(T)session.get(clazz, id);
		return bean;
	}
	
}
