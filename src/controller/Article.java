package controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.BoardDao;
import db.BoardDto;

public class Article extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Article() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");		
		URLEncoder.encode("UTF-8");
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		String bId = "";										//새로고침 시, 조회수 올라가는 것 방지 (쿠키로 board_id 체크해서) //F5누르면 이 Article.java를 다시 돌테니
		Cookie[] cookies = request.getCookies();			//getCookies() : 없으면 쿠키 새로 만들고, 있으면 쿠키 불러오는 메서드
		if(request.getCookies()!=null) {		//처음이 아니면 이 if문을 돌겠지
			for(int i=0; i<cookies.length; i++) {
				if(cookies[i].getName().equals("board_id")) {	//"board_id"라는 이름의 쿠키와 같은 쿠키 찾기 ("board_id"라는 집합?)
					bId=cookies[i].getValue();					//같은걸 찾으면 그 안에 들어있는 값 (즉, 게시글 번호 ex)32) 을 bId에 저장해
				}
			}
		}
		
		if(bId.equals(id+"")) {		//맨 위에 받아온 보드의 id와 쿠키의 bId를 비교
			
		}
		else {						// 다르다는건 이전 게시글과 다르다는 거니까 조회수를 up해준다
			BoardDao.getInstance().hitUp(id);			
		}
		
		
	
	
//		String hitCheck = request.getParameter("hitCheck");
//		
//		if(hitCheck.equals("yes")) {
//			BoardDao.getInstance().hitUp(id);
//		}
		
		BoardDao boardDao = BoardDao.getInstance();
		BoardDto boardDto = boardDao.getDto(id);
		
		Cookie cok = new Cookie("board_id",id+"");			// 처음 쿠키 생성
		response.addCookie(cok);		
		request.setAttribute("boardDto", boardDto);
		RequestDispatcher rd = request.getRequestDispatcher("/article.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
