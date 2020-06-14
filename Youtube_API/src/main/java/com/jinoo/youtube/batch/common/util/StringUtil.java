package com.jinoo.youtube.batch.common.util;

public class StringUtil {
	
	public static String nullToBlank(String str) { 
		if(str == null) {
			return "";
		}else {
			return str;
		}
		
	}
	
	public static String substringByStd(String str, String std) {

		int index = 0;

		if(str == null) {
			return "";
		}else if(std == null) {
			return str;
		}else {
			index = str.indexOf(std);
			
			return str.substring(0, index);
		}
		
	}
}
