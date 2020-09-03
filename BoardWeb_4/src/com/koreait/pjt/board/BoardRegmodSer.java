package com.koreait.pjt.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.Const;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/board/regmod")
public class BoardRegmodSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//화면 띄우는 용도(등록창,수정창)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String i_board = request.getParameter("i_board");		//Detail에서 보내는 i_board
		BoardVO vo = new BoardVO();
		
		if(i_board !=null) {
			vo.setI_board(Integer.parseInt(i_board));
			request.setAttribute("vo",BoardDAO.selDetail(vo));
		}
		
		ViewResolver.forwardLoginChk("board/regmod", request, response);
	}
	
	//처리용도 (DB에 등록/수정) 실시
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String i_board = request.getParameter("i_board");			//regmod에서 보내는 i_board
		String title = request.getParameter("title");
		String ctnt = request.getParameter("ctnt");
		
		HttpSession hs = request.getSession();
		UserVO loginUser = (UserVO) hs.getAttribute(Const.LOGIN_USER);
		BoardVO param = new BoardVO();
		
		String filter1 = scriptFilter(ctnt);
		
		
		param.setTitle(title);
		param.setCtnt(filter1);
		param.setI_user(loginUser.getI_user());
		
		int result;
		
		if(!i_board.equals("")) {							//넘어오는 i_board가 있긴 한데 값이 없어서 그냥 빈칸. 빈칸이 아니면 수정
			param.setI_board(Integer.parseInt(i_board));
			result = BoardDAO.updBoard(param);
			response.sendRedirect("/board/detail?i_board=" + i_board);
		}else {												//i_board가 빈칸. 등록.
			result = BoardDAO.insBoard(param);
			response.sendRedirect("/board/list");
		}
		
		if(result != 1) {			//에러발생
			request.setAttribute("msg", "에러가 발생했습니다. 관리자에게 문의 ㄱ");
			request.setAttribute("data", param);
			ViewResolver.forwardLoginChk("board/regmod", request, response);
			return;
		}
		
		
	}
	
	//스크립트 필터
	private String scriptFilter(final String ctnt) {
		String[] filters = {"<script>"};
		String[] filterReplaces = {"&lt;script&gt;" , "&lt;/script&gt;"};
		
		String result=ctnt;
		
		for(int i=0; i<filters.length;i++) {
			result = result.replace(filters[i], filterReplaces[i]);
		}
		return result;
	}
}