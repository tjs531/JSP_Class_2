package com.koreait.matzip;

import java.io.File;

import javax.servlet.http.Part;

public class FileUtils {
	public static void makeFolder(String path) {
		File dir = new File(path);
		
		if(!dir.exists()) {
			dir.mkdirs();					//mkdir은 딱 하나만 만든다. mkdirs는 여러개 만들어줌.
		}
	}

	public static String getExt(String fileNm) {
		return fileNm.substring(fileNm.lastIndexOf("."));
	}

}
