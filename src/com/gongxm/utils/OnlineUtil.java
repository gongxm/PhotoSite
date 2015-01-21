package com.gongxm.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class OnlineUtil {
	private static Map<String,HttpSession> map=new HashMap<String,HttpSession>();
	
	public static void add(String name,HttpSession session){
		map.put(name, session);
	}
	
	public static void remove(String username){
		HttpSession session = map.get(username);
		try{
		if(session!=null)
			session.removeAttribute("user");
		}catch(Exception e){
			
		}
		map.remove(username);
	}
	
	public static Map<String,HttpSession> getOnlineUser(){
		return map;
	}
}
