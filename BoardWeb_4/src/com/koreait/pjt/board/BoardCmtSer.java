package com.koreait.pjt.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardCmtDAO;
import com.koreait.pjt.vo.BoardCmtVO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/board/cmt")
public class BoardCmtSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//댓글(삭제)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int i_cmt = MyUtils.getIntParameter(request, "i_cmt");
		int i_board = MyUtils.getIntParameter(request, "i_board");
		
		UserVO loginUser = MyUtils.getLoginUser(request);
		
		BoardCmtVO param = new BoardCmtVO();
		param.setI_cmt(i_cmt);
		param.setI_user(loginUser.getI_user());
		
		BoardCmtDAO.delCmt(param);
		
		response.sendRedirect("/board/detail?i_board="+i_board);
	}

	//댓글 (등록/수정)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strI_cmt = request.getParameter("i_cmt");
		String cmt = request.getParameter("cmt");
		String i_board = request.getParameter("i_board");
		UserVO loginUser = MyUtils.getLoginUser(request);
		
		BoardCmtVO vo = new BoardCmtVO();
		
		vo.setCmt(cmt);
		vo.setI_user(loginUser.getI_user());
		
		switch(strI_cmt) {
		case "0":			//등록
			vo.setI_board(Integer.parseInt(i_board));
			BoardCmtDAO.insCmt(vo);
			break;
		default:			//수정
			vo.setI_cmt(Integer.parseInt(strI_cmt));
			BoardCmtDAO.updCmt(vo);
			break;
		}
		
		//detail 서블릿으로 보내야 화면에 값이 뜸. 보낼때 i_board 같이 보내기.
		response.sendRedirect("/board/detail?i_board="+i_board);

		//이렇게 보내면 안됨. 서블릿 안거치고 바로 jsp화면으로 가버림.
		//request.setAttribute("vo", vo);
		//ViewResolver.forwardLoginChk("board/detail", request, response);
	}

}
