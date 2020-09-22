package com.koreait.matzip.rest;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.matzip.Const;
import com.koreait.matzip.SecurityUtils;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.rest.model.RestDMI;
import com.koreait.matzip.rest.model.RestPARAM;

@Controller
@RequestMapping("/rest")
public class RestController {
	
	@Autowired
	private RestService service;	
	
	@RequestMapping("/map")
	public String restMap(Model model) {
		model.addAttribute(Const.TITLE, "��������");
		model.addAttribute(Const.VIEW, "rest/restMap");
		return ViewRef.TEMP_MENU_TEMP;
	}	
	
	@RequestMapping(value="/ajaxGetList", produces= "application/json; charset=utf-8")
	@ResponseBody 
	public List<RestDMI> ajaxGetList(RestPARAM param) {
		System.out.println("sw_lat : " + param.getSw_lat());
		System.out.println("sw_lng : " + param.getSw_lng());
		System.out.println("ne_lat : " + param.getNe_lat());
		System.out.println("ne_lng : " + param.getNe_lng());
		
		return service.selRestList(param);
	}
	
	@RequestMapping(value="/restReg", method = RequestMethod.GET)
	   public String restReg(Model model) {
	      final int I_M = 1; //ī�װ� �ڵ�
	      
	      model.addAttribute("categoryList", service.selCategoryList());
	      
	      model.addAttribute(Const.TITLE, "���� ���");
	      model.addAttribute(Const.VIEW, "rest/restReg");
	      return ViewRef.TEMP_MENU_TEMP;
	   }
	
	@RequestMapping(value="/restReg", method = RequestMethod.POST)
	   public String restReg(RestPARAM param, HttpSession hs) {
			param.setI_user(SecurityUtils.getLoginUserPk(hs));	
			
			int result = service.insRest(param);
			return "redirect:/rest/map";
	}
	
	@RequestMapping(value="/detail", method = RequestMethod.GET)
	   public String detail(RestPARAM param, Model model) {
			RestDMI data = service.selRest(param);
			
			model.addAttribute("data",data);
			model.addAttribute(Const.TITLE,data.getNm());
			model.addAttribute(Const.VIEW, "rest/restDetail");
			return ViewRef.TEMP_MENU_TEMP;
	}
	
}