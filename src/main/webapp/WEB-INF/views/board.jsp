<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset =utf-8" pageEncoding="utf-8" session="false"%>
<html>
<head>

<script src="<c:url value="/resources/jquery.js" />"></script>
<link href="<c:url value="/resources/board.css" />" rel="stylesheet">
<title>로그인</title>
</head>
<body>
	<div class="_table">
		<h2 class="_user"> ${member}의 ToDoList</h2>

		<div class="board">
		
			<h1 class="_do _text">할일</h1>
			<h1 class = "_doing _text">하는중</h1>
			<h1 class = "_end _text">끝</h1>
			<div class="do _text"></div>
			<div class="doing _text"></div>
			<div class="end _text"></div>
			<footer>
	<h1>
		<a href='<c:url value="/"/>'>로그아웃</a>
	</h1>
		</div>
		
	</div>
</body>

</footer>
<script>



$(document).ready(function() {
	_Main();
	
});	

function _submit(click_id) {
	$.ajax({
        type:'post',
        async:false, 
        url:"<c:url value="/submit"/>",
        dataType:'text',
        data:{_id : click_id},
        success: function(data) {
        	_Main();
        	console.log('succ');
        	
        },
        error:function (data) {
            console.log('error');
        }
    }) ;
	
}

function _cancel(click_id) {
	$.ajax({
        type:'post',
        async:false, 
        url:"<c:url value="/cancle"/>",
        dataType:'text',
        data:{_id : click_id},
        success: function(data) {
        	_Main();
        	console.log('succ');
        	
        },
        error:function (data) {
            console.log('error');
        }
    }) ;
	
}


var obj = {"name": "${member}"};

function _Main() {
		 $.ajax({
		        url: "<c:url value="/test" />",
		        type: "post",
		        data: JSON.stringify(obj),
		        dataType: "json",
		        contentType: "application/json",
		        success: function(data) {
		        	$('.new_box').remove();
		        	let temp_new = `<div class=new_box>
									<div class="left">
										<input type="text" class=desc_in name="desc" placeholder="내용"> <input type="date" class=time_in name="date">
									</div>
									<div class="right">
										<input type="button" class="new_submit" onclick="_new_do()" value="작성"> <input type="button" class="clear" onclick="window.location.reload();" value="취소">
									</div>
									</div>`
					$('.do').append(temp_new);
		         	
		            	
		            for (let i = 0; i <data['id'].length; i++) {
		            let _date = data['date'][i];
		            let _desc = data['desc'][i];
					let _id = data["id"][i];
					let status = data["status"][i];
					
		            let temp_html = `<div class = new_box>
										<div class = "left">
										<div class = desc>`+_desc+`</div>
										<div class = time>`+_date+`</div>
									</div>
									<div class = "right">
										<input type="button" class="submit" id =`+_id+` onClick="_submit(this.id)" value="완료">
										<input type="button" class="cancel" id = `+_id+` onClick="_cancel(this.id)" value="취소">
									</div>
									</div>`;
					if (status == "1"){
		            	$('.do').append(temp_html);
		            }
					else if (status == "2"){
						$('.doing').append(temp_html);
					}
					else if (status == "3"){
						$('.end').append(temp_html);
		            
					}
		            }
		        },
		        error: function(errorThrown) {
		            alert(errorThrown.statusText);
		        }
		    	});
	}




		

	
	function _clear(){
		$('.desc_in').val('');
		$('.time_in').val('');
	}
   function _new_do(){
        const desc_in = $('.desc_in').val();
        const time_in = $('.time_in').val();
        
        console.log('btn click');
        if(desc_in.length === 0 || desc_in === null) return alert("내용을 입력 하세요");

        $.ajax({
            type:'post',
            async:false, 
            url:"<c:url value="/CreateWork"/>",
            dataType:'text',
            data:{desc:desc_in, date:time_in},
            success: function(data) {
            	_Main();
            	$('.desc_in').val('');
            	$('.time_in').val('');
            	console.log('succ');
            	
            },
            error:function (data) {
                console.log('error');
            }
        }) ;   

    }
</script>
</html>
