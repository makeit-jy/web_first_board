package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Board extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Board() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		String sid = (String) session.getAttribute("sid");
		String pageNum = request.getParameter("pageNum");
		String pageBar = request.getParameter("pageBar");
		System.out.println("아 뭐냐고 : "+pageNum);
		
		if(sid==null) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 후 이용 가능합니다.'); location.href='login.jsp';</script>");
			out.flush();
//			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
//			rd.forward(request, response);
		}
		else {
			if(pageNum==null) {
				request.setAttribute("pageNum", "1");
				if(pageBar==null) {
					request.setAttribute("pageBar", "1");
				}
				else {
					request.setAttribute("pageBar", pageBar);
				}
			}
			else {
				request.setAttribute("pageNum", pageNum);
				if(pageBar==null) {
					request.setAttribute("pageBar", "1");
				}
				else {
					request.setAttribute("pageBar", pageBar);
				}
			}
			RequestDispatcher rd = request.getRequestDispatcher("/board.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("보드 doPost");
		doGet(request, response);
	}

}
