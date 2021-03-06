package com.coo.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.coo.member.model.service.MemberService;
import com.coo.member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateSearchServlet
 */
@WebServlet("/empSearch.me")
public class MemberSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String empId = request.getParameter("eSearch");
		Member m = new MemberService().searchMember(empId);
		String btn = (String) session.getAttribute("works");
		request.setAttribute("searchResult", m);
		
		switch(btn) {
		
		case "1":request.getRequestDispatcher("views/manager/join.jsp").forward(request, response); break;
		case "2":request.getRequestDispatcher("views/manager/modifyEmp.jsp").forward(request, response); break;
		case "3":request.getRequestDispatcher("views/manager/deleteEmp.jsp").forward(request, response); break;
		case "4":request.getRequestDispatcher("views/manager/updateSalary.jsp").forward(request, response); break;
		case "5":request.getRequestDispatcher("views/manager/imgUpload.jsp").forward(request, response); break;
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
