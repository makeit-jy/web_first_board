package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.JoayoDao;

public class Joayo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Joayo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String loginId = request.getParameter("loginId");
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		System.out.println("보드아이디 : "+boardId);
		
		JoayoDao.getInstance().joayo(boardId,loginId);
		
		RequestDispatcher rd = request.getRequestDispatcher("/article.do?id="+boardId+"&hitCheck=no");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("좋아요 dopost");
		doGet(request, response);
	}

}
