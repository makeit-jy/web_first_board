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

import db.BoardDao;
import db.BoardDto;
import db.MemberDao;

public class Write extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Write() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("느낌이 무한루프야");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		BoardDto boardDto = new BoardDto();
		HttpSession session = request.getSession();
		
		String writer = (String) session.getAttribute("sid");
		if(writer==null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('세션이 만료되었습니다.'); location.href='login.jsp';</script>");
			out.flush();
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}
		else {
			String category = request.getParameter("category");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int id = BoardDao.getInstance().getCurrentNumber()+1;
			
			boardDto.setId(id);
			boardDto.setWriter(writer);
			boardDto.setCategory(category);
			boardDto.setTitle(title);
			boardDto.setContent(content);
			boardDto.setWriterprofile(MemberDao.getInstance().getWriterProfileById(writer));
			BoardDao.getInstance().write(boardDto);
			
			System.out.println("여기까지는 올거아냐?");
			RequestDispatcher rd = request.getRequestDispatcher("/board.do");
			rd.forward(request, response);
			System.out.println("근데 왜 여기까지는 안감?");
		}
	}

}
