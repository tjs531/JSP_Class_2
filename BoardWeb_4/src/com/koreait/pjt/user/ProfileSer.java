package com.koreait.pjt.user;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.UserDAO;
import com.koreait.pjt.vo.UserVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("/profile")
public class ProfileSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	//프로필 화면 (나의 프로필 이미지, 이미지 변경 가능한 화면)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		UserVO loginUser = MyUtils.getLoginUser(request);
		request.setAttribute("data", UserDAO.selUser(loginUser.getI_user()));
		ViewResolver.forward("user/profile", request, response);
		
	}

	
	//이미지 변경 처리 (파일 업로드는 무조건 post 방식. get방식은 길이의 한계가 있다.)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVO loginUser = MyUtils.getLoginUser(request);
		String savePath = getServletContext().getRealPath("img") + "\\user\\" + loginUser.getI_user();			//이미지가 저장되는 경로.
		System.out.println(savePath);
		
		//만약 폴더(디렉토리)가 없다면 폴더 생성
		File directory = new File(savePath);
		if(!directory.exists()) {
			directory.mkdirs();
		}
		
		int maxFileSize = 10_485_760; 		//1024 * 1024 * 10 (10mb)				//최대 파일 사이즈 크기
		String fileNm = "";
		String originFileNm = "";
		String saveFileNm="";
		
		try {
			MultipartRequest mr = new MultipartRequest(request, savePath, maxFileSize, "UTF-8", new DefaultFileRenamePolicy());
			
			Enumeration files = mr.getFileNames();
			
			while(files.hasMoreElements()) {
				String key = (String)files.nextElement();
				fileNm = mr.getFilesystemName(key);					//중복된 이름이 있다면 자동으로 이름 변경해서 저장해줌
				//originFileNm = mr.getOriginalFileName(key);			//지정한 이름 그대로 저장.
				
				String ext =  fileNm.substring(fileNm.lastIndexOf("."));
				saveFileNm = UUID.randomUUID() +ext;
			
				System.out.println(saveFileNm);
				
				File oldFile = new File(savePath + "\\" + fileNm);
				File newFile = new File(savePath + "\\" + saveFileNm);
				
				oldFile.renameTo(newFile);
			
			}
		}catch(Exception e) {
			e.printStackTrace();
		} 
		
		if(saveFileNm != null) {	//DB에 프로필 파일명 저장
			UserVO param = new UserVO();
			param.setProfile_img(saveFileNm);
			param.setI_user(loginUser.getI_user());
			UserDAO.updUser(param);
		}
		
		response.sendRedirect("/profile");
	}

}
