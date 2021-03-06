package com.coo.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coo.board.model.service.BoardService;
import com.coo.board.model.vo.Board;
import com.coo.exception.CooException;

/**
 * Servlet implementation class BoardInsertServlet
 */
@WebServlet("/bInsert.bo")
public class BoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String category = request.getParameter("category");
		int type = Integer.parseInt(request.getParameter("category"));
		String writer = request.getParameter("writer");
		String content = request.getParameter("ir1");
		String deptview = request.getParameter("deptview");
		
		int result = 0;
		
		Board b = new Board();
		b.setBtype(type);
		b.setBtitle(title);
		b.setCategory(category);
		b.setBwriter(writer);
		b.setBcontent(content);
		b.setBdeptCode(deptview);
		
		result = new BoardService().insertBoard(b);
		
		if(result > 0) {
			response.sendRedirect("close.do");
		}else {
			request.setAttribute("msg","게시글 작성 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
