package com.koreait.matzip;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/hehe")								//Ŭ���� ���� ��� ��(�� �ּҸ� Ÿ�� ���� �Ʒ� �޼ҵ� �ּҷ� �� �� ����. (hehe/hello)
public class HomeController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(HttpServletRequest request, Model model) {		//request, model �� �� �ᵵ ��
		request.setAttribute("myName", "������");
		model.addAttribute("age",29);
		return "hello";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );				//model �� request.setAttribute �� ����
		
		return "home";					//src/main/webapp/WEB-INF/views�Ʒ��� �ִ� home.jsp ����
	}
	
}
