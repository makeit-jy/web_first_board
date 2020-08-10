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


@WebServlet("/reprofile.do")
public class Reprofile extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Reprofile() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/reprofile.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("sid");
		
		if(id==null) {
			//비로그인상태 로그인하세요
		}
		else {
			MemberDao memberDao = MemberDao.getInstance();
			MemberDto memberDto = new MemberDto();
			
			String animal = request.getParameter("animal");
			
			memberDao.updateWriterProfileById(id, animal);
			System.out.println("동물프로필변경완료");
			
			memberDto = memberDao.getMemberDtoById(id);
			
			request.setAttribute("memberDto", memberDto);
			RequestDispatcher rd = request.getRequestDispatcher("/mypage.jsp");
			rd.forward(request, response);
		}		
		
	}

}
