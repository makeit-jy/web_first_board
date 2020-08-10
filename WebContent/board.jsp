<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="db.BoardDto"%>
<%@page import="java.util.List"%>
<%@page import="db.BoardDao"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>여기는 게시판 입니다 <img src="<%=request.getContextPath()%>/img/icon/게시판.png" width=45 height=35/></h1>
	<hr/>
	 	
	<table border="1">
		<tr>
			<td><a href="home.do"><button>홈</button></a></td>
			<td><a href="board.do"><button>게시판</button></a></td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	<%if((String)session.getAttribute("sid")!=null){ %>
			<td><a href="mypage.do"><button>마이페이지</button></a></td>
			<td><b>&nbsp;${sid}</b> 님, 환영합니다!!&nbsp;</td>
			<td> <a href="logout.do"><button>로그아웃</button></a></td>
		</tr> 
	</table>
	<hr/>
	
	<%}else{ %>
			<td><a href="login.do"><button>로그인</button></a></td>
		</tr> 
	</table>
	<hr/>
	<% } %>
	
	
	
		<img src="<%=request.getContextPath()%>/img/icon/집.png" width=800 height=200/>
	<hr/>
	
	
	
	<%BoardDao boardDao = BoardDao.getInstance(); 
	List<BoardDto> list = boardDao.getList();
	int pageNum = Integer.parseInt((String)request.getAttribute("pageNum")); 
	int pageCaculate = list.size()-1-(pageNum-1)*5;
	int totalPage;
	int namoge = list.size()%5;
	if(namoge==0){totalPage = list.size()/5;namoge=5;}
	else{
		totalPage = list.size()/5+1;
		if(pageNum!=totalPage){
			namoge=5;
		}
	}
	
	int pageBar = Integer.parseInt((String)request.getAttribute("pageBar")); %>
	
	
	<table border="1">
		<tr>
			<td>글번호</td>
			<td width="500">제목</td>
			<td>작성일</td>
			<td>조회수</td>
			<td>카테고리</td>
			<td>작성자</td>
		</tr>
	<%for(int i=pageCaculate;i>pageCaculate-namoge;i--){%>
		<tr>
			<td><%=i+1%></td>
			<td><a href="article.do?id=<%=list.get(i).getId()%>&hitCheck=yes"><%=list.get(i).getTitle()%></a></td>
			<td><%=list.get(i).getWdate()%></td>
			<td><%=list.get(i).getHit()%></td>
			<td><%=list.get(i).getCategory()%></td>
			<td><img src="<%=request.getContextPath()%>/img/icon/<%=list.get(i).getWriterprofile()%>.png" width=30 height=17/><%=list.get(i).getWriter()%></td>
		</tr>
	<%}%>
	</table>
	
	<hr/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="board.do?pageBar=<%=pageBar-1%>"><Button>이전</Button></a>
	<%for(int i=(pageBar-1)*5+1;i<(pageBar-1)*5+1+5;i++){%>
	<a href="board.do?pageNum=<%=i%>"><%=i%></a>
	<%}%>
	<a href="board.do?pageBar=<%=pageBar+1%>"><Button>다음</Button></a>
	<hr/>
	
	<a><Button>검색???</Button></a>
	<a href="write.jsp"><Button>새 글 작성</Button></a>

</body>
</html>