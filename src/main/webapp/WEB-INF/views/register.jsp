<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset =utf-8" pageEncoding="utf-8" session="false"%>
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/resources/register.css" />" rel="stylesheet">
<script src="<c:url value="/resources/jquery.js" />"></script>
<title>회원가입</title>
</head>
<body>
<div class = 'main'>
	<h2>회원가입</h2>
	<form action="registerS" method="post" onsubmit="return submitCheck();">
		<p>
			<label> 이름</label>
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
		<input class="login" type="submit"  value="가입 완료">
		<a  class="register"href='<c:url value="/"/>'>뒤로가기</a>
	</form>
	</div>
</body>
<script type="text/javascript">
function submitCheck() {
	  name = $("#name").val();
	  id = $("#id").val();
	  pw = $("#pw").val();
	  
	   if(name == "" || id ==""|| pw=="" ) {
	   	   alert("빈칸을 다 입력 해 주세요");
		   return false;

	 	}
	   alert('가입 완료 되었습니다.');
	  return true

	}
</script>

</html>