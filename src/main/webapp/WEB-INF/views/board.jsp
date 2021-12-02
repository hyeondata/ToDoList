<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset =utf-8" pageEncoding="utf-8" session="false" %>
<html>
<head>

	<script src="<c:url value="/resources/jquery.js" />"></script>
	<link href="<c:url value="/resources/board.css" />" rel="stylesheet">
	<title>로그인</title>
</head>
<body>
 <h1>메인</h1>

<P>  The time on the server is ${member}. </P>
	<div class="board">
		<div class="do" >
			<h1>할일</h1>
			<div class = new_box>
				<div class = "left">
					<input type="text" class = desc_in  name="desc" placeholder="내용">
					<input type="date" class = time_in  name= "date" >
				</div>
				<div class = "right">
					<input type="button" class="new_submit"  value="확인">
					<input type="button" class="clear" value="취소">
				</div>
			</div>
			
			<div class = new_box>
				<div class = "left">
					<div class = desc>a</div>
					<div class = time>a</div>
				</div>
				<div class = "right">
					<input type="button" class="submit" value="완료">
					<input type="button" class="cancel" value="취소">
				</div>
			</div>
		</div>
		
		
		<div class="doing">하는중</div>
		<div class="end">끝</div>
	</div>
</body>
<script>
$(document).ready(function(){
	Do();
	 
	});


	function Do(){
	$.ajax({
	    type:'GET',
	    url:'http://localhost:8080/todo/Do',
	    data:{desc:"${desc}", date:"${date}"},
	    success: function(data) {
	    	$('.submit').val(data['desc'][0]);
	    	$('.cancel').val(data['date'][0]);
	    	console.log('succ');
	    	
	    },
	    error:function (data, textStatus) {
	        console.log('error');
	    }
	
	});
	}
	
	$('.clear').click(function(){
		$('.desc_in').val('');
		$('.time_in').val('');
	});
    $('.new_submit').click(function(){
        const desc_in = $('.desc_in').val();
        const time_in = $('.time_in').val();
        
        console.log('btn click');
        if(desc_in.length === 0 || desc_in === null) return alert("내용을 입력 하세요");

        $.ajax({
            type:'post',
            async:false, //false가 기본값임 - 비동기
            url:'http://localhost:8080/todo/CreateWork',
            dataType:'text',
            data:{desc:desc_in, date:time_in},
            success: function(data, textStatus) {
            	Do();
            	$('.desc_in').val('');
            	$('.time_in').val('');
            	console.log('succ');
            	
            },
            error:function (data, textStatus) {
                console.log('error');
            }
        }) ;   //ajax

    });
</script>
</html>
      