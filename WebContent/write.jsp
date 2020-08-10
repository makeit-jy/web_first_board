<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>여기는 글쓰기 입니다</h1>
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
	
	<form action="write.do" method="post">
		<table border="0">
			<tr>
				<td>카테고리 </td>
				<td>
					<input type="radio" name="category" value="질문" checked="checked"/>질문
					<input type="radio" name="category" value="잡담"/>잡담
					<input type="radio" name="category" value="정보"/>정보
				</td>
			</tr>
			<tr>
				<td>제목 </td><td><textarea rows="1" cols="30" name="title"></textarea></td>
			</tr>
			<tr>
				<td>내용</td><td><textarea rows="20" cols="30" name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><button type="reset">초기화</button><button type="submit">저장</button></td>
			</tr>
		
		</table>
	</form>
</body>
</html>