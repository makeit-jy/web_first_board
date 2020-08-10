package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.MemberDao;

public class Checknewid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Checknewid() {
        super();
    }
    	// Q.doGet이랑 doPost 같은거 넣어놓은 이유 궁금? 무작위로 들어가나?? join.jsp에서 post로 보냈는데?
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		System.out.println("입력받은 id :"+id);
		String s = MemberDao.getInstance().checkIdExsist(id);		//아이디가 존재하면 s에 id를 리턴, 없으면null
		System.out.println("s = "+s);
		
		if(s.equals(null)) {	//	가입 가능			//Q. String인데 s.equals(null)이 아니라 s==""이 맞나? 왜그런거지? 일단 변경해봄.
			request.setAttribute("id", id);
			request.setAttribute("idok", "yes");
			RequestDispatcher rd = request.getRequestDispatcher("/join.jsp");
			rd.forward(request, response);
		}
		else {	// 가입 불가능
			request.setAttribute("id", id);
			request.setAttribute("idok", "no");
			RequestDispatcher rd = request.getRequestDispatcher("/join.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		System.out.println("입력받은 id :"+id);
		String s = MemberDao.getInstance().checkIdExsist(id);		//아이디가 존재하면 s에 id를 리턴, 없으면null
		System.out.println("s = "+s);
		
		if(s=="") {	//	가입 가능
			request.setAttribute("id", id);
			request.setAttribute("idok", "yes");
			RequestDispatcher rd = request.getRequestDispatcher("/join.jsp");
			rd.forward(request, response);
		}
		else {	// 가입 불가능
			request.setAttribute("id", id);
			request.setAttribute("idok", "no");
			RequestDispatcher rd = request.getRequestDispatcher("/join.jsp");
			rd.forward(request, response);
		}
		
	}

}
