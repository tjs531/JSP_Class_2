package com.koreait.matzip.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.matzip.Const;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.user.model.UserDTO;
import com.koreait.matzip.user.model.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute(Const.TITLE,"로그인");
		model.addAttribute(Const.VIEW, "user/login");
		return ViewRef.TEMP_DEFAULT;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST) 
		public String login(UserDTO param) {
			//System.out.println("id : " + param.getUser_id());
			//System.out.println("pw: " + param.getUser_pw());
			int result = service.login(param);
			
			if(result == 1) {
				return "redirect:/rest/map";
			}
			
			return "redirect:/user/login?err="+ result;
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(Model model, @RequestParam(required=false, defaultValue="0") int err) {				//@RequestParam을 보내면 String으로 안받고 바로 int로 받을 수도 있음. return에서 보내주는 이름과 같으면 (Value="") 생략. (required=true)적으면 err이 필수가 됨. true가 디폴트 값이라서(required=false) 적어줘야 함. 
		
		if(err > 0) {
			model.addAttribute("msg", "에러가 발생했스니다");
		}
		
		model.addAttribute(Const.TITLE,"회원가입");
		model.addAttribute(Const.VIEW, "user/join");
		return ViewRef.TEMP_DEFAULT;
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST) 
		public String join(UserVO param) {
			int result = service.join(param);
			
			if(result==1) {
				return "redirect:/user/login";
			}
		
			return "redirect:/user/join?err=" + result;
	}
}