package com.coo.ta.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.coo.exception.CooException;
import com.coo.member.model.vo.Member;
import com.coo.ta.model.service.TaDataService;
import com.coo.ta.model.service.WorkTimeService;
import com.coo.ta.model.vo.WorkTime;

/**
 * Servlet implementation class StartTime
 */
@WebServlet("/sTime.ta")
public class StartTime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartTime() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//	session에 저장된 empCode 받기
		HttpSession session = request.getSession(false);
		Member m = (Member) session.getAttribute("member");
		int empCode = m.getEmpCode();

		//	ajax data로 전송된 type 받기
		String taType = request.getParameter("type");
		
		//	현재 시간
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
		String timeStr = sdf.format(now);
		
		int taTime = -1;
		
		Date d = new Date();
		
		try {
			d = sdf.parse(timeStr);
		} catch (ParseException e1) {}
		long nowlong = d.getTime()+32400000;
		nowlong = nowlong/60000;
		
		long hour = nowlong/60;
		long min = nowlong%60;
		
		String hh = "";
		String mm = "";
		
		hh = ""+hour;
		if( min < 10 ) {
			mm = "0"+min;
		}else {
			mm = ""+min;
		}
		
		taTime = Integer.parseInt(hh+mm);
		
		//	WorkTime 객체 생성
		WorkTime wt = new WorkTime(empCode, taType, taTime);
		
		//	service로 객체 전송 해서 결과 받기
		int result = 0;
		try {
			result = new WorkTimeService().wtT1Insert(wt);
		} catch (CooException e) {
			request.setAttribute("msg", "출근 시간 등록 중 오류 발생!");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
		//	ajax 결과 넘기기
		String msg = null;
		
		if( result >= 0 ) {
			if( 900 > taTime ) {
				msg = "출근 등록 완료!";
			}else {
				msg = "출근 등록 완료! \n 지각입니다!!"; 
			}
		}else {
			msg = "출근 시간 등록 실패! 다시 시도하세요";
		}
		
		System.out.println("출근시간 INSERT / 근무일 +1");
		response.getWriter().print(msg);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
