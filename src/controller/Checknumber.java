package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Checknumber extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Checknumber() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String idok = request.getParameter("idok");
		String email = request.getParameter("email");
		String number = request.getParameter("number");
		String numberinput = request.getParameter("numberinput");
		
		if(number.equals(numberinput)) {		// 인증 성공
			request.setAttribute("id", id);
			request.setAttribute("idok", idok);
			request.setAttribute("email", email);
			request.setAttribute("number", number);
			request.setAttribute("numberinput", numberinput);
			request.setAttribute("numberok", "yes");
			RequestDispatcher rd = request.getRequestDispatcher("/join.jsp");
			rd.forward(request, response);
		}
		else {
			request.setAttribute("id", id);
			request.setAttribute("idok", idok);
			request.setAttribute("email", email);
			request.setAttribute("number", number);
			request.setAttribute("numberinput", numberinput);
			request.setAttribute("numberok", "no");
			RequestDispatcher rd = request.getRequestDispatcher("/join.jsp");
			rd.forward(request, response);
		}
	}

}
