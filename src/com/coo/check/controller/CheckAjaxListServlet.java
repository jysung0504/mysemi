package com.coo.check.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.coo.check.model.service.CheckService;
import com.coo.check.model.vo.CheckDoc;
import com.coo.check.model.vo.PageInfo;
import com.coo.member.model.vo.Member;
import com.google.gson.Gson;

/**
 * Servlet implementation class CheckAjaxListServlet
 */
@WebServlet("/calist.ch")
public class CheckAjaxListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckAjaxListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		//세션 들고오기 
		HttpSession session = request.getSession(false);
		if(session == null) {
		 request.setAttribute("msg", "로그인인 해주세요");
		 request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
		 //세션에서 empno 가져오기
		Member m = (Member) session.getAttribute("member");
		int id = m.getEmpCode();
		//int status = Integer.valueOf(request.getParameter("ASTAT"));
		int status= Integer.valueOf(request.getParameter("status"));
		int search = Integer.valueOf(request.getParameter("search"));
		String keyword = request.getParameter("keyword");
		//System.out.println(status);
		
		ArrayList<CheckDoc> list = null;
		//페이징 처리
		
		//1.  총 게시글수
		int checkListCount;
		
		//2. 페이징시 시작 
		int startPaging;
		
		//3. 페이징 끝
		
		int endPaging;
		
		//4. 마지막 페이징
		int maxPaging;
		
		//5. 한줄에 들어갈 페이징수
		int limitPaging;
		
		//6. 한번에 보일 페이지
		int limitPage;
		
		//7. 현재  있는 페이징
		int currentPage;
		
		limitPaging = 5;
		limitPage = 10;
		
		currentPage = 1;
		
		
		
		
		if(request.getParameter("currentPage")!=null) {
			currentPage = Integer.valueOf(request.getParameter("currentPage"));
		}
		//페이징 시작
		CheckService cs = new CheckService();
		
		
		startPaging = ((int)((double)currentPage/limitPaging +0.9)-1)*limitPaging+1;
		
		endPaging = startPaging +limitPaging -1;
		
		if(search==0 ||keyword.equals("") ) {
		//게시글 총갯수
		checkListCount = cs.getListCount(id,status);
	
		
		
		maxPaging = (int)((double)checkListCount/limitPage+0.9); 
		
		
		
		list = cs.getList(currentPage,limitPage, id, status);
	
		
		}else {
			checkListCount = cs.getSearchListCount(id,search,keyword);
			
			
			maxPaging = (int)((double)checkListCount/limitPage+0.9); 
			
			
			list  = cs.getSearchList(id,search,keyword,currentPage,limitPage);
					
					
		}
		 
		
		if(maxPaging< endPaging) {
			endPaging = maxPaging;
		}
		
		if(list != null) {
			
		
	
		Map pafull = new HashMap();
		pafull.put("list", list);
				
		PageInfo pi = new PageInfo(checkListCount,startPaging,endPaging,maxPaging,limitPaging,limitPage,currentPage);

	    pafull.put("pi",pi);
				
				new Gson().toJson(pafull,response.getWriter());
		}else {
			request.setAttribute("msg", "페이징실패");
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
