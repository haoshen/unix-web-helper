package com.haoshen.utils;

public class SecurityUtil {
	
 	private static final String PASSWORD = "abc123";
 	
 	public static boolean check(String password) {
 		return PASSWORD.equals(password);
 	}

}
