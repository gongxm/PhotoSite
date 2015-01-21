package com.gongxm.utils;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class MD5Util {
	public static String base64Encoding(String str){
		byte[] b=str.getBytes();
		return new BASE64Encoder().encode(b);
	}
	
	public static String base64DeEncod(String str){
		try {
			return new String(new BASE64Decoder().decodeBuffer(str));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String MD5(String str){
		try {
			MessageDigest md=MessageDigest.getInstance("md5");
			byte[] b=md.digest(str.getBytes());
		return new BASE64Encoder().encode(b);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
}
