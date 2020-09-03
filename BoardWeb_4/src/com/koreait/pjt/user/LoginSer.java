package com.koreait.pjt.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.Const;
import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.UserDAO;
import com.koreait.pjt.vo.UserLoginHistoryVO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/login")
public class LoginSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		if(hs.getAttribute(Const.LOGIN_USER) == null) {
			ViewResolver.forward("user/login", request, response);
		}else {
			response.sendRedirect("/board/list");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String encrypt_pw = MyUtils.encryptString(user_pw);			//비밀번호 암호화

		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw(encrypt_pw);
		
		int result = UserDAO.login(param);	//param의 주소값이 넘어가기 때문에 받은 곳에서 변경을 하면 그대로 변경됨.
												//param에 i_user, user_id, nm 들어가있음.
		
		if(result != 1) {		//에러처리
			String msg = null;
			switch(result) {
			case 0:
				msg = "에러가 발생했습니다";
				break;
			case 2:
				msg = "비밀번호가 틀렸습니다.";
				break;
			case 3:
				msg = "아이디가 없음";
				break;
			}
			request.setAttribute("msg",msg);
			request.setAttribute("user_id", user_id);
			doGet(request,response);
			return;
		}
		
		String agent = request.getHeader("User-Agent");
		//System.out.println("agent: " + agent);
		String os = getOs(agent);
		String browser = getBrowser(agent);
		String ip_addr = request.getRemoteAddr();
		
		UserLoginHistoryVO ulhVO = new UserLoginHistoryVO();
		ulhVO.setI_user(param.getI_user());
		ulhVO.setOs(os);
		ulhVO.setIp_addr(ip_addr);
		ulhVO.setBrowser(browser);
		
		UserDAO.insUserLoginHistory(ulhVO);
		
		HttpSession hs = request.getSession();
		hs.setAttribute(Const.LOGIN_USER,param);
		
		System.out.println("로그인성공");
		response.sendRedirect("/board/list");
	}
	
	private String getBrowser(String agent) {
		if(agent.toLowerCase().contains("msie")) {
			return "ie";
		} else if(agent.toLowerCase().contains("chrome")) {
			return "chrome";
		} else if(agent.toLowerCase().contains("safari")) {
			return "safari";
		} 
		
		return "";
	}
	
	private String getOs(String agent) {

		if(agent.toLowerCase().contains("mac")) {
			return "mac";
		} else if(agent.toLowerCase().contains("windows")) {
			return "win";
		} else if(agent.toLowerCase().contains("x11")) {
			return "unix";
		} else if(agent.toLowerCase().contains("linux")) {
			return "linux";
		} else if(agent.toLowerCase().contains("android")) {
			return "android";
		} else if(agent.toLowerCase().contains("iphone")) {
			return "IOS";
		}
		
		return "";
	}
}
