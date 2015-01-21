package com.gongxm.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory factory;
	static{
		Configuration cfg=new Configuration();
		cfg.configure();//���������ļ�
		factory=cfg.buildSessionFactory();
	}
	
	public static Session getSession(){
		return factory.openSession();
	}
}
