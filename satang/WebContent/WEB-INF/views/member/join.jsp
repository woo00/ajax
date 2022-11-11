<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Smart Join Page</title>
<link type="text/css" rel="stylesheet" href="/satang/css/w3.css">
<link type="text/css" rel="stylesheet" href="/satang/css/smart.css">
<script type="text/javascript" src="/satang/js/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#idck').click(function(){
			// 아이디 입력내용 꺼내기
			var sid = $('#id').val();
			// 입력된 아이디 서버에 보내고 결과 받기
			// 비동기 통신에 해당하므로 ajax()로 처리한다.
			$.ajax({
				url:'/satang/member/idCheck.smart',
				type:'POST',
				dataType:'json',
				data: {
					id : sid
				},
				success: function(data){
// 					alert(data.result);
// 					console.log(data.result);
					if(data.result == 'OK'){
						$('#idckmsg').removeClass('w3-text-red').addClass('w3-text-green').html('*사용할 수 있는 아이디입니다.');
					}else{
						$('#idckmsg').removeClass('w3-text-green').addClass('w3-text-red').html('*사용할 수 없는 아이디입니다.');
						$('#id').val('');
						$('#id').focus();
					}
						$('#idckmsg').css('display','block');
				},
				error: function(){
					alert('### 통신실패 ###');
				}
			});
		});
	});
</script>

<style type="text/css">
	html, body{
		height: 100%;
	}
</style>
</head>
<body>
	<div class="w3-content mxw700">
		<h1 class="w3-padding w3-pink w3-center w3-card-4 w3-round-large mgb20">Smart join</h1>
		
		<form method="get" action="result.html" name="frm" id="frm" class="w3-col w3-round-large w3-card-4 mgb20 pdh30">
			<!-- 이름 입력 태그 -->
			<div class="w3-col mgb15">
				<div class="w3-col m3 w3-right-align pdr10 ft18 w3-text-gray bd">
					<label for="name" >이 름 : </label>
				</div>
				<div class="w3-col m9">
					<input type="text" name="name" id="name" class="w3-col m10 w3-input w3-border"	required>
				</div>
			</div>
			<!-- 아이디 입력 태그 -->
			<div class="w3-col mgb15">
				<div class="w3-col m3 w3-right-align pdr10 ft18 bd w3-text-gray">
					<label for="id" >I D : </label>
				</div>
				<div class="w3-col m9">
					<div class="w3-col m10">
						<div class="w80 w3-right w3-button w3-light-gray w3-hover-gray" id = "idck">check</div>
						<div class="w3-rest pdr10">
							<input type="text" name="id" id="id" class="w3-input w3-border"
																placeholder="아이디를 입력하세요." required >
						</div>
						<p class="w3-col w3-center" id="idckmsg" style="display : none; margin: 0px;">아이디체크 메시지</p>
						
					</div>
				</div>
			</div>
			<div class="w3-col mgb15">
				<div class="w3-col m3 w3-right-align pdr10 ft18 bd w3-text-gray">
					<label for="pw" >P W : </label>
				</div>
				<div class="w3-col m9">
					<input type="password" name="pw" id="pw" class="w3-col m10 w3-input w3-border"
							placeholder="비밀번호를 입력하세요." required>
				</div>
			</div>
			<div class="w3-col mgb15">
				<div class="w3-col m3 w3-right-align pdr10 ft18 bd w3-text-gray">
					<label for="pw" >reP W : </label>
				</div>
				<div class="w3-col m9">
					<input type="password" name="pw" id="pw" class="w3-col m10 w3-input w3-border"
							placeholder="비밀번호를 입력하세요." required>
				</div>
			</div>
			<div class="w3-col mgb15">
				<div class="w3-col m3 w3-right-align pdr10 ft18 bd w3-text-gray">
					<label for="mail" >이메일 : </label>
				</div>
				<div class="w3-col m9">
					<input type="text" name="mail" id="mail" class="w3-col m10 w3-input w3-border"
							placeholder="이메일을 입력하세요." required>
				</div>
			</div>
			<div class="w3-col mgb15">
				<div class="w3-col m3 w3-right-align pdr10 ft18 bd w3-text-gray">
					<label for="phone" >휴대폰 : </label>
				</div>
				<div class="w3-col m9">
					<input type="text" name="phone" id="phone" class="w3-col m10 w3-input w3-border"
							placeholder="전화번호를 입력하세요." required>
				</div>
			</div>
			<div class="w3-col mgb15">
				<div class="w3-col m3 w3-right-align pdr10 ft18 bd w3-text-gray">
					<label>성 별 : </label>
				</div>
				<div class="w3-col m9">
					<div class="w3-col m10">
						<div class="w3-half w3-center">
							<input type="radio" name="gen" id="mgen" class="w3-radio" value="M" onchange="getGen(this)">
							<label for="mgen" class="glbl ft12">남 성</label>
						</div>
						<div class="w3-half w3-center">
							<input type="radio" name="gen" id="wgen" class="w3-radio" value="F" onclick="getGen(this)">
							<label for="wgen" class="glbl ft12">여 성</label>
						</div>
					</div>
				</div>
			</div>
			<!-- 아바타선택 태그 -->
			<div class="w3-col mgb15" id="selAvt" style="display: none;">
				<div class="w3-col m3 w3-right-align pdr10 ft18 bd w3-text-gray">
					<label>아바타 : </label>
				</div>
				<div class="w3-col m9">
					<div class="w3-col m10 w3-center">
						<!--  남자아바타 선택그룹  -->
						<div class="w3-col" id="mavt">
							<div class="inblock">
								<div class="avtfr inblock ">
									<label for="a1" class="w3-col avtimg w3-border">
										<img src="/satang/img/avatar/img_avatar1.png" class="avtimg">
									</label>
									<div class="w3-col">
										<input type="radio" id="avt" id="a1" value="1" class="w3-radio">			
									</div>
								</div>
							</div>
							<div class="inblock">
								<div class="avtfr inblock ">
									<label for="a2" class="w3-col avtimg w3-border">
										<img src="/satang/img/avatar/img_avatar2.png" class="avtimg">
									</label>
									<div class="w3-col">
										<input type="radio" id="avt" id="a2" value="2" class="w3-radio">			
									</div>
								</div>
							</div>
							<div class="inblock">
								<div class="avtfr inblock ">
									<label for="a3" class="w3-col avtimg w3-border">
										<img src="/satang/img/avatar/img_avatar3.png" class="avtimg">
									</label>
									<div class="w3-col">
										<input type="radio" id="avt" id="a3" value="3" class="w3-radio">			
									</div>
								</div>
							</div>
						</div>
						<!--  여자아바타 선택그룹  -->
						<div class="w3-col" id="favt">
							<div class="inblock">
								<div class="avtfr inblock ">
									<label for="a4" class="w3-col avtimg w3-border">
										<img src="/satang/img/avatar/img_avatar4.png" class="avtimg">
									</label>
									<div class="w3-col">
										<input type="radio" id="avt" id="a4" value="4" class="w3-radio">			
									</div>
								</div>
							</div>
							<div class="inblock">
								<div class="avtfr inblock ">
									<label for="a5" class="w3-col avtimg w3-border">
										<img src="/satang/img/avatar/img_avatar5.png" class="avtimg">
									</label>
									<div class="w3-col">
										<input type="radio" id="avt" id="a5" value="5" class="w3-radio">			
									</div>
								</div>
							</div>
							<div class="inblock">
								<div class="avtfr inblock ">
									<label for="a6" class="w3-col avtimg w3-border">
										<img src="/satang/img/avatar/img_avatar6.png" class="avtimg">
									</label>
									<div class="w3-col">
										<input type="radio" id="avt" id="a6" value="6" class="w3-radio">			
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>	
		
		<div class="w3-col w3-card-4 w3-round-large hidden ">
			<div class="w3-col w3-third w3-green w3-hover-lime w3-button" id="hbtn">home</div>
			<div class="w3-col w3-third w3-orange w3-hover-khaki w3-button" id="jbtn">join</div>
			<div class="w3-col w3-third w3-blue w3-hover-aqua w3-button" id="lbtn">login</div>
		</div>
	</div>
<script type="text/javascript">
function getGen(el){
	var gval = el.value;
	if(gval == 'M'){
		document.getElementById('favt').style.display = 'none';
		document.getElementById('mavt').style.display = 'block';
		document.getElementById('selAvt').style.display = 'block';
	} else if(gval == 'F') {
		document.getElementById('favt').style.display = 'block';
		document.getElementById('mavt').style.display = 'none';
		document.getElementById('selAvt').style.display = 'block';
	}
}

</script>
</body>
</html>