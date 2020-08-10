<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="db.MemberDao"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>여기는 마이페이지 입니다</h1>
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
	
	<% String sid =(String)session.getAttribute("sid");		// 추가함.
	
	MemberDao memberDao = MemberDao.getInstance();
	String sprofile = memberDao.getWriterProfileById(sid); %>
		
	<p>아이디 : ${memberDto.getId() }</p>
	<p>이름 : ${memberDto.getName() }</p>
	<p>이메일 : ${memberDto.getEmail() }</p>
	프로필 사진 : <p><img src="<%=request.getContextPath()%>/img/${memberDto.getId()}.jpg" width=300 height=200/></p>
	동물 프로필 사진 : <p><img src="<%=request.getContextPath()%>/img/icon/<%=sprofile%>.png" width=50 height=50></p>
	<a href='reprofile.do'><button>동물 프로필 사진 변경</button></a>
	
</body>
</html>