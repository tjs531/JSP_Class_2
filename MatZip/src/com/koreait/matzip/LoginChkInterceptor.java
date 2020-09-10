package com.koreait.matzip;

import javax.servlet.http.HttpServletRequest;

public class LoginChkInterceptor {
	
	public static String routerChk(HttpServletRequest request) {
		
		String[] chkUserUriArr = {"login", "loginProc","join", "joinProc","ajaxIdChk"};
		//�α��� �Ǿ� ������ login, join�� ���� �Ǹ� �ȵȴ�.
		

		//�α��ο� ���� ���� ���ɿ��� �Ǵ� 
		//(�α����� �ȵǾ� ���� �� ������ �� �ִ� �ּҸ� ���⼭ üũ.  �������� ���� �α����� �Ǿ��־�� ����� �� �ִ� ������)
		
		boolean isLogout = SecurityUtils.isLogout(request);
		String[] targetUri = request.getRequestURI().split("/");
	
		if(targetUri.length < 3) {
			return null;
		}
		
		if(isLogout) {							//�α׾ƿ� ����
			if(targetUri[1].equals(ViewRef.URI_USER)){
				for(String uri : chkUserUriArr ) {
					if(uri.equals(targetUri[2])) {
						return null;
					}
				}
			}
			return "/user/login";
		} else { 									//�α��� ����
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
