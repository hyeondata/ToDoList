<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset =utf-8" pageEncoding="utf-8" session="false"%>
<html>
<head>
<link href="<c:url value="/resources/home.css" />" rel="stylesheet">
<script src="<c:url value="/resources/jquery.js" />"></script>
<title>로그인</title>
</head>
<body>
	<div class='main'>
		<h1>로그인</h1>
		<form action="Login" method="post" >
			<p>
				<input type="text" placeholder="아이디" name="id">
			</p>
			<p>
				<input type="password" placeholder="비밀번호" name="pw">
			</p>
			<div class="sub">
				<input class="login" type="submit" value="로그인"> <a class="register" href='<c:url value="/register"/>'>회원가입</a>
			</div>
		</form>


	</div>

</body>

	<script type="text/javascript">
function submitCheck() {

	  id = $("#id").val();
	  pw = $("#pw").val();
	  
	   if(id =="" || pw=="" ) {
	   	   alert("빈칸을 다 입력 해 주세요");
		   return false;

	 }
	   alert('로그인 완료');
	   alert(pw);
	  return true

	}
</script>
</html>
