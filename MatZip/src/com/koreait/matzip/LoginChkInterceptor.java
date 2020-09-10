package com.koreait.matzip;

import javax.servlet.http.HttpServletRequest;

public class LoginChkInterceptor {
	
	public static String routerChk(HttpServletRequest request) {
		
		String[] chkUserUriArr = {"login", "loginProc","join", "joinProc","ajaxIdChk"};
		//로그인 되어 있으면 login, join은 접속 되면 안된다.
		

		//로그인에 따른 접속 가능여부 판단 
		//(로그인이 안되어 있을 때 접속할 수 있는 주소만 여기서 체크.  나머지는 전부 로그인이 되어있어야 사용할 수 있는 페이지)
		
		boolean isLogout = SecurityUtils.isLogout(request);
		String[] targetUri = request.getRequestURI().split("/");
	
		if(targetUri.length < 3) {
			return null;
		}
		
		if(isLogout) {							//로그아웃 상태
			if(targetUri[1].equals(ViewRef.URI_USER)){
				for(String uri : chkUserUriArr ) {
					if(uri.equals(targetUri[2])) {
						return null;
					}
				}
			}
			return "/user/login";
		} else { 									//로그인 상태
				if(targetUri[1].equals(ViewRef.URI_USER)){
					for(String uri : chkUserUriArr ) {
						if(uri.equals(targetUri[2])) {
							return "/restaurant/restMap";
						}
					}
			}
			return null;
		}
	}
}
