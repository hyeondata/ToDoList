<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원가입</title>
</head>
<body>
	<h2>회원가입</h2>
	<form action="registerS" method="post">
		<p>
			<label>이름</label>
			<input type="text" name="name" id="name"></input>
		</p>
		<p>
			<label>아이디</label>
			<input type="text" name="id" id="id"></input>
		</p>
		<p>
			<label>비밀번호</label>
			<input type="text" name="pw" id="pw"></input>
		</p>
		<input type="submit" value="가입 완료">
	</form>
</body>
</html>