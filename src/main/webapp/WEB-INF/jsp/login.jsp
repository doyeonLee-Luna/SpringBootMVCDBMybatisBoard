<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>


<script src="//cdn.jsdelivr.net/npm/alertifyjs@1.14.0/build/alertify.min.js"></script>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.14.0/build/css/alertify.min.css"/>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.14.0/build/css/themes/bootstrap.min.css"/>

<title>로그인</title>
</head>
<body>
	<!--  validation check  
		브라우저 내잔 유효성 검사 x -> <form novalisate>
		Bootstrap 제공 유효성 검사를한다
	-->
	<div class="container">
	
		<div class="mb-3 mt-5 d-flex justify-content-center">
			<h1 class="display-4">Hello World!</h1>
		</div>
		
		<div class="mb-3">
			<h2>로그인</h2>
		</div>
		
	
	
		<form novalidate>
			<div class="mb-3">
  				<label for="userEmail" class="form-label">User Email:</label>
  				<input type="email" class="form-control" id="userEmail" name="userEmail" placeholder="User Email" value="dodo@mail.com">
			</div>
			<div class="mb-3">
  				<label for="userPassword" class="form-label">User Password:</label>
  				<input type="password" class="form-control" id="userPassword" name="userPassword" placeholder="Enter User Password" value="1234">
			</div>
		</form>
		<div>
			<button type="button" id="btnLogin" class="btn btn-success">로그인</button>
		</div>
	
	</div>
	

<script>
	window.onload = function(){
		// btnLogin 처리
		document.querySelector("#btnLogin").onclick = function(){
			// validation check
			if(document.querySelector("#userEmail").value.length == '' || document.querySelector("#userPassword").value.length == ''){
				alert("입력이 올바르지 않습니다.");
				return;
			}
			
			login();
		}
		
		
		
	}
	
	
	
	// 회원 가입
	async function login(){
		// 사용자 입력 값
		let userEmail = document.querySelector("#userEmail").value;
		let userPassword = document.querySelector("#userPassword").value;
		// x-www-form-urlencoded
		let urlParams = new URLSearchParams({
			userEmail: userEmail,
			userPassword: userPassword,	
		});
		
		// fetch options
		let fetchOptions = {
			method: "POST",
			body: urlParams
		}
		
		let response = await fetch("/auth/login", fetchOptions);
		let data = await response.json();
		
		if(data.result == "success"){
			// 게시판 페이지 이동
			window.location.href="/pages/board";
		} else {
			//alert("이메일 또는 비밀번호가 올바르지 않습니다.");
			 alertify.error('이메일 또는 비밀번호가 올바르지 않습니다.');
		}
	}
</script>
</body>
</html>







