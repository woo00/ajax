<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>aJax index</title>
<link type="text/css" rel="stylesheet" href="/satang/css/w3.css">
<link type="text/css" rel="stylesheet" href="/satang/css/smart.css">
<script type="text/javascript" src="/satang/js/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#jbtn').click(function(){
			$(location).attr('href','/satang/member/join.smart');
		});
		$('#mlist').click(function(){
			$('#lContent').html('');
			$.ajax({
				url : '/satang/member/memberList.smart',
				type : 'post',
				dataType: 'json',
				success: function(data){
					// 이 경우 data에 담긴 내용은 배열일 것
					//{"result" : [ {"id": ???? , "mno" : ????},{} ]}
					
					for(i = 0 ; i< data.result.length; i++){
						var jdata = data["result"][i];
						var str = '<div class = "w3-col w3-button w3-blue temp" id="' + jdata.mno + '">' + jdata.name + '</div>';
						
						$('#lContent').append(str);
					}
						$('#lContent').removeClass('w3-hide');
				},
				error: function(){
					alert('###통신에러###');
				}
			});
		});
		$('#lContent').on('click', '.temp', function(){
			var sno = $(this).attr('id');
			alert(sno);
			$.ajax({
				url: '/satang/member/getMail.smart',
				type: 'post',
				dataType: 'text',
				data:{
					mno: sno
				},
				success: function(data){
					alert(data);
					$('#mail').html(data);
					$('#mail').parent().parent().removeClass('w3-hide');
				},
				error: function(){
					alert('###통신에러###');
				}
			});
		});
	});
</script>
</head>
<body>
	<div class="w3-content mxw700 w3-center">
		<h1 class="w3-padding w3-card-4 blu1">aJax Index Page</h1>
		<div class="w3-col">
			<div class="w3-button blu2" id="jbtn">회원가입</div>
			<div class="w3-button blu3" id="lbtn">로그인</div>
			<div class="w3-button blu4" id="mlist">회원리스트</div>
			<!-- 
				위 버튼을 클릭해서 리스트버튼이 완성되면
				회원버튼을 클릭해서 그 회원의 메일을 alert창에 띄워주는 기능추가
				
				요청 주소 : /member/getMail.smart
				서블릿 이름 : MemberMail
			 -->
			<div class="w3-col w3-margin-top w3-hide" id="lContent">
			</div>
			<div class="w3-col w3-margin-top w3-hide">
				<h2>Mail : <span id = "mail"></span></h2>			
			</div>
		</div>
	</div>
</body>
</html>