package com.koreait.matzip.user;

import javax.servlet.http.HttpServletRequest;

import com.koreait.matzip.Const;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.vo.UserVO;

public class UserController {

	private UserService service;
	
	public UserController() {
		service = new UserService();
	}
	
	//		/user/login
	public String login(HttpServletRequest request) {
		
		String error = request.getParameter("error");
		
		if(error != null) {
			switch(error) {
			case "2":
				request.setAttribute("msg", "아이디 없음");
				break;
			case "3":
				request.setAttribute("msg", "비밀번호 틀림");
				break;
			}
		}
		request.setAttribute(Const.TITLE, "로그인");
		request.setAttribute(Const.VIEW, "user/login");
		return ViewRef.TEMP_DEFAULT;
	}
	
	public String join(HttpServletRequest request) {
		request.setAttribute(Const.TITLE, "회원가입");
		request.setAttribute(Const.VIEW, "user/join");
		return ViewRef.TEMP_DEFAULT;
	}
	
	public String joinProc(HttpServletRequest request) {
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");				//암호화 시켜야함
		String nm = request.getParameter("nm");
		
		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw(user_pw);
		param.setNm(nm);
		
		int result = service.join(param);
		
		return "redirect:/user/login";
	}
	
	public String loginProc(HttpServletRequest request) {
		
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw(user_pw);
		
		int result = service.login(param);
		
		if(result == 1) {
			return "redirect:/restaurant/restMap";
		}else {
			return "redirect:/user/login?user_id="+ user_id + "&error=" + result;
		}
		
	}
	
	public String ajaxIdChk(HttpServletRequest request) {
		String user_id = request.getParameter("user_id");
		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw("");
		
		int result = service.login(param);

		return String.format("ajax:{\"result\": %s}", result);
	}
}
