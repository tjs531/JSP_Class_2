package com.koreait.matzip;

import java.io.File;

import javax.servlet.http.Part;

public class FileUtils {
	public static void makeFolder(String path) {
		File dir = new File(path);
		
		if(!dir.exists()) {
			dir.mkdirs();					//mkdir�� �� �ϳ��� �����. mkdirs�� ������ �������.
		}
	}

	public static String getExt(String fileNm) {
		return fileNm.substring(fileNm.lastIndexOf("."));
	}

}
