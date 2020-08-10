<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

		<h2>동물 프로필 사진 변경</h2>
		
		<form action="reprofile.do" method="post">
			<input type="radio" name="animal" value="강아지" checked> 강아지<br>
			<input type="radio" name="animal" value="고양이"> 고양이<br>
			<input type="radio" name="animal" value="닭"> 닭<br>
			<input type="radio" name="animal" value="돼지"> 돼지<br>
			<input type="radio" name="animal" value="병아리"> 병아리<br>
			<input type="radio" name="animal" value="양"> 양<br>
			<input type="radio" name="animal" value="젖소"> 젖소<br>
			<input type="radio" name="animal" value="쥐"> 쥐 <br>
			<input type="radio" name="animal" value="곰"> 곰<br>
			<input type="radio" name="animal" value="토끼"> 토끼<br>
			<input type="radio" name="animal" value="별"> 별<br><br>
			
			<input type="submit" value="변경하기">
		
		</form>

</body>
</html>