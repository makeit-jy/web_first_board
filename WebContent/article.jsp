<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>여기는 게시글 입니다</h1>
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
	
	<table border="1">
		<tr>
			<td width=250><b>#${boardDto.getId()}(${boardDto.getCategory()}) ${boardDto.getTitle() }</b></td>
			<td width=150>작성일 : ${boardDto.getWdate() }</td>
		</tr>
		
		<tr>
			<td width=250>작성자 : ${boardDto.getWriter() }</td>
			<td width=150>조회수 : ${boardDto.getHit() }</td>
		</tr>
		
		<tr height=200>
			<td colspan=2 style="white-space: pre-wrap;">${boardDto.getContent() }</td> <!-- pre-line에서 pre-wrap으로 변경해봄 + article.java에서 쓰는 getDto(int id) 변경.-->
		</tr>
	
	</table>
	<a href="board.do"><Button>목록으로</Button></a>
	<Button>수정??아직안함</Button>
	<a href="delete.do?id=${boardDto.getId()}&writer=${boardDto.getWriter()}"><Button>삭제</Button></a>
	<a href="joayo.do?loginId=${sid}&boardId=${boardDto.getId()}"><Button>좋아요 ${boardDto.getJoayo()} 👍</Button></a>
</body>
</html>