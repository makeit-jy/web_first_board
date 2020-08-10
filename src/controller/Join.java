package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.MemberDao;
import db.MemberDto;

public class Join extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Join() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");					// 이전에 아이디 중복검사 실험할때 만들었던 것 같음.
//		String joinAble = request.getParameter("joinAble");
//		if(joinAble.equals("yes")) {
//			request.setAttribute("joinAble", "yes");
//		}
//		else {
//			request.setAttribute("joinAble", "no");
//		}
//		RequestDispatcher rd = request.getRequestDispatcher("/join.jsp");
//		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		MemberDto memberDto = new MemberDto();
		memberDto.setId(id);
		memberDto.setPw(pw);
		memberDto.setName(name);
		memberDto.setEmail(email);
		
		System.out.println("회원가입 여부"+MemberDao.getInstance().join(memberDto));
		
		PrintWriter out = response.getWriter();
		out.println("<script>alert('회원가입을 축하합니다.'); location.href='login.jsp';</script>");
		out.flush();
		
//		RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
//		rd.forward(request, response);
	}

}
