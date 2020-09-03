package com.koreait.pjt.board;

import java.io.IOException;
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
import com.koreait.pjt.db.UserDAO;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;

/**
 * Servlet implementation class ToggleLikeSer
 */
@WebServlet("/board/toggleLike")
public class ToggleLikeSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String i_board = request.getParameter("i_board");
		String yn_like = request.getParameter("yn_like");
		
		HttpSession hs = (HttpSession)request.getSession();
		
		int page = MyUtils.getIntParameter(request, "page");
		page = (page==0 ? 1 : page);
		
		int recordCnt = MyUtils.getIntParameter(request, "record_cnt");
		recordCnt = (recordCnt == 0? 10: recordCnt);
		UserVO loginUser = MyUtils.getLoginUser(request);
		
		BoardVO vo = new BoardVO();
		
		vo.setI_board(Integer.parseInt(i_board));
		vo.setI_user(loginUser.getI_user());
		
		
		if(Integer.parseInt(yn_like) == 0) {
			BoardDAO.insLike(vo);
		} else {
			BoardDAO.delLike(vo);
		}
			
		response.sendRedirect("/board/detail?i_board="+i_board+"&page=" +page+"&record_cnt="+recordCnt);
	}

}
