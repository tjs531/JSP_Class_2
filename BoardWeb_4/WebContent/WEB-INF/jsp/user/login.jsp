<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
	.err {color:red;}
	.container { text-align:center;}
	.contain { width: 500px; height: 300px; margin:0 auto; text-align:center; background-color:#F8F8F4; padding-top:30px; border-radius: 50%; }
	.text {margin:3px; height:28px;}
	#id {margin-top:60px;}
	#pw {margin-bottom: 30px;}
	#btn {border-radius: 10%; width:70px; height:30px;margin:10px 0px; background-color:cornsilk; border-color: antiquewhite;}
	#join {color:black;}
	a {text-decoration:none;}
	
</style>
</head>
<body>
	<div class="container">
	<h1>Login</h1>
	<div class="err">${msg }</div>
	<div class="contain">
		<form action="/login" method="post">
			<div><input class="text" id="id" type="text" name="user_id" value="${user_id}" placeholder="아이디"></div>
			<div><input class="text" id="pw" type="password" name="user_pw" placeholder="비밀번호"></div>
			<div><input id="btn" type="submit" value="로그인"></div>
		</form>
		<a href="/join" id="join">회원가입</a>
	</div>
	</div>
</body>
</html>