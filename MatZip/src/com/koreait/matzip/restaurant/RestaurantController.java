package com.koreait.matzip.restaurant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.koreait.matzip.CommonDAO;
import com.koreait.matzip.CommonUtils;
import com.koreait.matzip.Const;
import com.koreait.matzip.SecurityUtils;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.user.UserService;
import com.koreait.matzip.vo.RestaurantVO;
import com.koreait.matzip.vo.UserVO;

public class RestaurantController {
	
	private RestaurantService service;
	
	public RestaurantController() {
		service = new RestaurantService();
	}
	
	public String restMap(HttpServletRequest request) {
		request.setAttribute(Const.TITLE, "지도보기");
		request.setAttribute(Const.VIEW, "restaurant/restMap");
		return ViewRef.TEMP_MENUTEMP;
	}
	
	public String restReg(HttpServletRequest request) {
		final int I_M = 1;		//카테고리 코드
		request.setAttribute("categoryList", CommonDAO.selCodeList(I_M));
		
		request.setAttribute(Const.TITLE, "가게 등록");
		request.setAttribute(Const.VIEW, "restaurant/restReg");
		return ViewRef.TEMP_MENUTEMP;
	}
	
	public String restRegProc(HttpServletRequest request) {
		
		UserVO loginUser =SecurityUtils.getLoginUser(request);
		int i_user = loginUser.getI_user();
		
		String nm = request.getParameter("nm");
		String addr = request.getParameter("addr");
		String latStr = request.getParameter("lat");
		double lat =  CommonUtils.parseStringToDouble(latStr);
		String lngStr = request.getParameter("lng");
		double lng = CommonUtils.parseStringToDouble(lngStr);
		int cd_category = CommonUtils.parseStringToInt(request.getParameter("cd_category"));
		
		RestaurantVO param = new RestaurantVO();
		
		param.setNm(nm);
		param.setAddr(addr);
		param.setLat(lat);
		param.setLng(lng);
		param.setCd_category(cd_category);
		param.setI_user(i_user);
		
		int result = service.restReg(param);
		
		return "redirect:/restaurant/restMap";
	}
	
	public String ajaxGetList(HttpServletRequest request) {
		return "ajax:" + service.getRestList();
	}
	
}
