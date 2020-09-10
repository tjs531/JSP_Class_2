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
		
		if(dbResult.getI_user() == 0) {
			result= 2;
		} else {
			String pw = param.getUser_pw();
			String encryptPw = SecurityUtils.getEncrypt(pw, dbResult.getSalt());
			
			if(encryptPw.equals(dbResult.getUser_pw())) {			//로그인 성공
				param.setUser_pw(null);												//param에 비밀번호 지워주고 원하는 값 넣어주기.
				param.setI_user(dbResult.getI_user());
				param.setNm(dbResult.getNm());
				param.setProfile_img(dbResult.getProfile_img());
				
				//param = dbResult;													이렇게 하면 절대 안됨. 안됨.안됨. 보내는 param과 매개변수의 param은 주소만 같을 뿐 엄연히 다른 놈.
				result= 1;
			} else {
				result =3;
			}
		}

		return result;
	}
}
