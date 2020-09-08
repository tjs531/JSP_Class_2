package com.koreait.matzip.user;

import com.koreait.matzip.SecurityUtils;
import com.koreait.matzip.vo.UserVO;

public class UserService {
	
	private UserDAO dao;
	
	public UserService() {
		dao = new UserDAO();
	}
	
	public int join(UserVO param) {
		String pw = param.getUser_pw();
		String salt = SecurityUtils.generateSalt();
		String encryptPw = SecurityUtils.getEncrypt(pw, salt);
		
		param.setUser_pw(encryptPw);
		param.setSalt(salt);		
		
		return dao.join(param);
	}
	
	//result (1):로그인 성공 (2):아이디 없음 (3):비밀번호 틀림 
	public int login(UserVO param) {
		int result=0;
		
		UserVO dbResult = dao.selUser(param);
		
		
		if(dbResult.getI_user() == 0){
			result= 2;
		}else {
			String pw = param.getUser_pw();
			String encryptPw = SecurityUtils.getEncrypt(pw, dbResult.getSalt());
			if(encryptPw.equals(dbResult.getUser_pw())){
				result= 1;
			}else {
				result =3;
			}
		}

		return result;
	}
}
