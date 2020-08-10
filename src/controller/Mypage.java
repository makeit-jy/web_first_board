package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.MemberDao;
import db.MemberDto;

public class Mypage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Mypage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("sid");
		
		if(id==null) {
			//비로그인상태 로그인하세요
		}
		else {
			MemberDao memberDao = MemberDao.getInstance();
			MemberDto memberDto = memberDao.getMemberDtoById(id);
			
			request.setAttribute("memberDto", memberDto);
			RequestDispatcher rd = request.getRequestDispatcher("/mypage.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
