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
		model.addAttribute(Const.TITLE,"�α���");
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
	public String join(Model model, @RequestParam(required=false, defaultValue="0") int err) {				//@RequestParam�� ������ String���� �ȹް� �ٷ� int�� ���� ���� ����. return���� �����ִ� �̸��� ������ (Value="") ����. (required=true)������ err�� �ʼ��� ��. true�� ����Ʈ ���̶�(required=false) ������� ��. 
		
		if(err > 0) {
			model.addAttribute("msg", "������ �߻��߽��ϴ�");
		}
		
		model.addAttribute(Const.TITLE,"ȸ������");
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