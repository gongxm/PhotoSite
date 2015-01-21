package com.gongxm.utils;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.gongxm.bean.WebBean;

public class BeanFillUtil {
	
	public static WebBean fillBean(HttpServletRequest request) {
		WebBean bean=new WebBean();
		try {
			BeanUtils.populate(bean, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return bean;
	}
}
