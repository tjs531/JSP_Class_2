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
@RequestMapping(value = "/hehe")								//클래스 위에 적어도 됨(이 주소를 타고 가야 아래 메소드 주소로 갈 수 있음. (hehe/hello)
public class HomeController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(HttpServletRequest request, Model model) {		//request, model 둘 다 써도 됨
		request.setAttribute("myName", "정혜선");
		model.addAttribute("age",29);
		return "hello";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );				//model 은 request.setAttribute 의 역할
		
		return "home";					//src/main/webapp/WEB-INF/views아래에 있는 home.jsp 파일
	}
	
}
