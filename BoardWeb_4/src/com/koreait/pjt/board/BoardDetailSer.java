package com.koreait.pjt.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardCmtDAO;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.db.UserDAO;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;
import com.koreait.pjt.vo.BoardCmtVO;

@WebServlet("/board/detail")
public class BoardDetailSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserVO loginUser = MyUtils.getLoginUser(request);
		HttpSession hs = (HttpSession)request.getSession();
		
		if(loginUser == null) {
			response.sendRedirect("/login");
			return;
		}
		
		String i_board = request.getParameter("i_board");

		//ServletContext application = getServletContext();
		//Integer readI_user = (Integer)application.getAttribute("read_" + i_board);		//int는 null을 변환하면 에러터지지만 Integer는 괜찮음.(객체형)
		
		BoardVO vo = new BoardVO();
		vo.setI_board(Integer.parseInt(i_board));
		vo.setI_user(loginUser.getI_user());
		
		
		//단독으로 조회수 올리기 방지 (새로고침 할 시 조회수+1 방지)
	/*	if(readI_user == null || readI_user != loginUser.getI_user()) {
			//조회수 올리기
			BoardDAO.updHits(vo);
			application.setAttribute("read_" + i_board, loginUser.getI_user());
		}*/
		
		//t_hits 테이블 따로 만들어서 삽입 후 업데이트
		int result = BoardDAO.insHits(vo);
		
		if(result == 1) {
			BoardDAO.updHits(vo);
		}
		
		//댓글 리스트
		request.setAttribute("cmtlist", BoardCmtDAO.selCmtList(vo));
		//like누른사람 리스트
		//request.setAttribute("likelist", BoardDAO.selLikeList(vo,"like"));
		
		request.setAttribute("likelist", BoardDAO.selBoardLikeList(vo.getI_board()));
		request.setAttribute("vo", BoardDAO.selDetail(vo));
		ViewResolver.forwardLoginChk("board/detail", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
