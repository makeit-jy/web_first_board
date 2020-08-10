<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
 
	function passwordCheckk(){
		var password = document.getElementById("password").value;
		var passwordCheck = document.getElementById("passwordCheck").value;
		if(passwordCheck==""){
			document.getElementById("passwordCheckText").value = "";
		}
		else if (password!=passwordCheck){
			document.getElementById("passwordCheckText").value = "비밀번호가 일치하지 않습니다.";
			document.getElementById("passwordCheckText").style.color='red';
		}
		else{
			document.getElementById("passwordCheckText").value = "비밀번호가 일치합니다!";
			document.getElementById("passwordCheckText").style.color='blue';
		}
	}
</script>
</head>
<body>

	<%	String id = "";
		String idok = "";
		String email = "";
		String number = "";
		String numberinput = "";
		String numberok = "";
		if(request.getAttribute("id")!=null){
			id = (String)request.getAttribute("id");
		}
		if(request.getAttribute("idok")!=null){
			System.out.println("실행!");
			idok = (String)request.getAttribute("idok");
		}
		if(request.getAttribute("email")!=null){
			email =	(String)request.getAttribute("email");
		}
		if(request.getAttribute("number")!=null){
			number = (String)request.getAttribute("number");
		}
		if(request.getAttribute("numberinput")!=null){
			numberinput = (String)request.getAttribute("numberinput");
		}
		if(request.getAttribute("numberok")!=null){
			numberok = (String)request.getAttribute("numberok");
		}
		System.out.println(id+","+idok+","+email+","+number+","+numberinput+","+numberok);
	%>
	
	
	<h1>여기는 회원가입 입니다 </h1>
	<hr/>
	
	<table border="1">
		<tr>
			<td><a href="home.do"><button>홈</button></a></td>
			<td><a href="board.do"><button>게시판</button></a></td>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	<%if((String)session.getAttribute("sid")!=null){ //추가해야할듯! 로그인되어있으면 회원가입은 못하도록?%>
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
	
	
	
	<table border="1" title="인증요구항목">	<!-- Q.이 인증요구항목은 글로 써지는데 없나? -->
		<tr>
			<td>
				아이디
			</td>
			<td>	
				<form action="checknewid.do" method="post">
					<input type="text" name="id" value="<%=id%>" required="required">
					<input type="submit" value="중복확인">
				</form>
			</td>
			<td>
				${idok}
			</td>
		</tr>
		<tr>
			<td>
				이메일
			</td>
			<td>	
				<form action="sendemail.do" method="post">
				<%if(idok.equals("yes")){ %>
					<input type="email" name="email" value="<%=email%>" required="required">
				<%}%>
				<%if(!idok.equals("yes")){ %>
					<input type="email" name="email" disabled>
				<%}%>
					<input type="hidden" name="id" value="${id}">
					<input type="hidden" name="idok" value="${idok}">
				<%if(idok.equals("yes")){ %>
					<input type="submit" value="인증메일발송"> 
				<%}%>
				<%if(!idok.equals("yes")){ %>
					<input type="submit" value="인증메일발송" disabled> 
				<%}%>
				</form>
			</td>
			<td>
				이메일..
			</td>
		</tr>
		<tr>
			<td>
				인증번호
			</td>
			<td>
				<form action="checknumber.do" method="post">
					<%if(!number.equals("")){ %>
						<input type="text" name="numberinput" value="${numberinput}" required="required">
					<%}%>
					<%if(number.equals("")){ %>
						<input type="text" name="numberinput" disabled>
					<%}%>
					<input type="hidden" name="id" value="${id}">
					<input type="hidden" name="idok" value="${idok}">
					<input type="hidden" name="email" value="${email}">
					<input type="hidden" name="number" value="${number}">
					<%if(!number.equals("")){ %>
						<input type="submit" value="인증">
					<%}%>
					<%if(number.equals("")){ %>
						<input type="submit" value="인증" disabled> 
					<%}%>
				</form>
			</td>
			<td>
				${numberok}
			</td>
		</tr>
	</table>
	
	
	
	<hr/>
	
	<%if(numberok.equals("yes")){ %>
	<form action="join.do" method="post">
		<table border="1" title="나머지 항목">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" required="required"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" id="password" name="pw" required="required"></td>
			</tr>
			<tr>
				<td>비밀번호확인</td>
				<td><input type="password" id="passwordCheck" name="pw" onkeyup="passwordCheckk()" required="required"></td>
				<td><input type="text" id="passwordCheckText" disabled></td>
			</tr>
			<tr>
				<td colspan='3'><input type="submit" value="회원가입" required="required"></td>
				<td><input type="hidden" name="id" value="${id}"></td>
				<td><input type="hidden" name="email" value="${email}"></td>
			</tr>
		</table>
	</form>
	<%}%>
	
	<%if(!numberok.equals("yes")){ %>
	<form>
		<table border="1" title="나머지 항목">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" required="required" disabled></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="text" id="password" name="pw" required="required" disabled></td>
			</tr>
			<tr>
				<td>비밀번호확인</td>
				<td><input type="text" id="passwordCheck" name="pw" onkeyup="passwordCheckk()" required="required" disabled></td>
				<td><input type="text" id="passwordCheckText" disabled></td>
			</tr>
			<tr>
				<td colspan='3'><input type="submit" value="가입" required="required" disabled></td>
			</tr>
		</table>
	</form>
	<%}%>
	
	
	
</body>
</html>