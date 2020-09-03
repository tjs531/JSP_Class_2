<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
	.err {
		color:red;
	}
</style>
</head>
<body>
	<h1>회원가입</h1>
	<div id="container">
		<div class="err">${msg}</div>
		<div>
			<form id="frm" action="/join" method="post" onsubmit="return chk()">
				<div><label><input type="text" name="user_id" placeholder="아이디" value="${data.user_id}" }required></label></div>
				<div><label><input type="password" name="user_pw" placeholder="비밀번호" required></label></div>
				<div><label><input type="password" name="user_pwre" placeholder="비밀번호확인"></label></div>
				<div><input type="text" name="nm" placeholder="이름" value="${data.nm}" required></div>
				<div><input type="email" name="email" placeholder="이메일" value="${data.email}"></div>
				<div><input type="submit" value="회원가입"></div>
			</form>
		</div>
	</div>
	
	<script>
		function chk(){
			
			const korean = /[^가-힣]/					//한글 정규식 : /[가-힣]/ : 한글이 들어가있으면 true반환. ^(not)붙여서 한글만 있는경우 false 반환
			const email = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

			if(frm.user_id.value.length < 5) {
				alert('아이디는 5글자 이상이어야합니다.');
				frm.user_id.focus();
				return false;
			} 
			if(frm.user_pw.value.length < 5){
				alert('비밀번호는 5글자 이상이어야합니다.');
				frm.user_pw.focus();
				return false;
			} 
			if(frm.user_pw.value != frm.user_pwre.value){
				alert('비밀번호를 확인해주세요'); 
				frm.user_pw.focus();
				return false;
			} 
			if(korean.test(frm.nm.value)){				//한글 정규식을 만족하지 않을 경우.(이름에 한글이 아닌 문자가 있을 경우)
				alert('이름을 다시 입력해주세요');
				frm.nm.focus();
				return false;
			} 
			if(!email.test(frm.email.value)){			//이메일 정규식을 만족하지 않을 경우.
				alert('이메일을 확인해주세요');
				frm.email.focus();
				return false;
			}
		
		}
	</script>
</body>
</html>
