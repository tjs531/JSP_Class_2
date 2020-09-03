package com.koreait.pjt.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.Const;
import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/board/list")
public class BoardListSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession hs = (HttpSession)request.getSession();
		
		UserVO loginUser = (UserVO) hs.getAttribute(Const.LOGIN_USER);
		
		
		String searchType = request.getParameter("searchType");
		searchType = (searchType == null) ? "a" : searchType;
		
		String searchText = request.getParameter("searchText");
		searchText = (searchText == null ? "" : searchText);
		
		int page = MyUtils.getIntParameter(request, "page");
		page = (page==0 ? 1 : page);
		
		int recordCnt = MyUtils.getIntParameter(request, "record_cnt");
		recordCnt = (recordCnt == 0? 10: recordCnt);
		
		
		
		BoardVO param= new BoardVO();
		
		if(loginUser != null) {
			param.setI_user(loginUser.getI_user());
		}
		param.setRecord_cnt(recordCnt); //한 페이지 당 뿌리는 갯수
		param.setSearchText("%"+ searchText + "%");
		param.setSearchType(searchType);
	//	param.setI_user(loginUser.getI_user());
		int pagingCnt = BoardDAO.selPagingCnt(param);			//페이지 개수
		
		if(pagingCnt < page) {
			page = pagingCnt;
		}
		
		int eIdx = page * recordCnt;
		int sIdx = eIdx - recordCnt;
		
		param.setSldx(sIdx);
		param.setEldx(eIdx);
		
		hs.setAttribute("searchText", searchText);
		hs.setAttribute("searchType", searchType);
		
		request.setAttribute("page", page);
		request.setAttribute("pagingCnt", pagingCnt);
		
		List<BoardVO> list = BoardDAO.selBoardList(param);
		
		//하이라이트 처리
		if(!"".equals(searchText) && "a".equals(searchType) || "c".equals(searchType)) {
			for(BoardVO item : list) {
				
				String title = item.getTitle();
				title = title.replace(searchText, "<span class=\"highlight\">" + searchText + "</span>");
				item.setTitle(title);
				
			}
		}
		
		
		request.setAttribute("list", list );
		
		//hs.setAttribute("recordCnt", recordCnt);

		ViewResolver.forwardLoginChk("board/list", request, response);
	}
}