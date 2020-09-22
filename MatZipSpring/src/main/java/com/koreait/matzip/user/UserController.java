package com.koreait.matzip.user;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.matzip.Const;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.user.model.UserPARAM;
import com.koreait.matzip.user.model.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(HttpSession hs) {
		hs.invalidate();
		return "redirect:/";
	}
	
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute(Const.TITLE, "�α���");
		model.addAttribute(Const.VIEW, "user/login");
		return ViewRef.TEMP_DEFAULT;
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST) 
	public String login(UserPARAM param, HttpSession hs, RedirectAttributes ra) {
		int result = service.login(param);
		
		if(result == Const.SUCCESS) {
			hs.setAttribute(Const.LOGIN_USER, param);
			return "redirect:/rest/map";
		}
		
		String msg = null;
		if(result == Const.NO_ID) {
			msg = "���̵� Ȯ���� �ּ���.";
		} else if(result == Const.NO_PW) {
			msg = "��й�ȣ�� Ȯ���� �ּ���.";
		}
		
		param.setMsg(msg);
		ra.addFlashAttribute("data", param);
		return "redirect:/user/login";
	}
	
	@RequestMapping(value="/join", method = RequestMethod.GET)
	public String join(Model model, @RequestParam(defaultValue="0") int err) {
		System.out.println("err : " + err);
		
		if(err > 0) {
			model.addAttribute("msg", "������ �߻��Ͽ����ϴ�.");
		}
		model.addAttribute(Const.TITLE, "ȸ������");
		model.addAttribute(Const.VIEW, "user/join");
		return ViewRef.TEMP_DEFAULT;
	}
	
	@RequestMapping(value="/join", method = RequestMethod.POST)
	public String join(UserVO param, RedirectAttributes ra) {
		int result = service.join(param);
		
		if(result == 1) {
			return "redirect:/user/login"; 
		}
		
		ra.addAttribute("err", result);
		return "redirect:/user/join";
	}
	
	@RequestMapping(value="/ajaxIdChk", method=RequestMethod.POST)
	@ResponseBody
	public String ajaxIdChk(@RequestBody UserPARAM param) {
		System.out.println("user_id : " + param.getUser_id());
		int result = service.login(param);
		return String.valueOf(result);
	}
}


