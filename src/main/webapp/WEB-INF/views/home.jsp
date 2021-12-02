<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset =utf-8" pageEncoding="utf-8" session="false" %>
<html>
<head>
	<title>로그인</title>
</head>
<body>
 <h1>로그인</h1>
<form action="Login" method="post">
	<p>
	<input type="text" placeholder="아이디" name="id">
	</p>
	<p>
	<input type="text" placeholder="비밀번호" name="pw">
	</p>
	
	<input type="submit" value="로그인">
	
</form>
<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
