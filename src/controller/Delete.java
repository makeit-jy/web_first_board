package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.BoardDao;

public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Delete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		int id = Integer.parseInt(request.getParameter("id"));
		String writer = request.getParameter("writer");
		String loginId = (String) request.getSession().getAttribute("sid");
		if(writer.equals(loginId)) {		//	게시글작성자와 로그인 아이디가 일치하면
			BoardDao.getInstance().delete(id);
			PrintWriter out = response.getWriter();
			out.println("<script>alert('삭제되었습니다.'); location.href='board.do';</script>");
			out.flush();
		}
		else {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('삭제 권한이 없습니다.'); history.back();</script>");
			out.flush();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
