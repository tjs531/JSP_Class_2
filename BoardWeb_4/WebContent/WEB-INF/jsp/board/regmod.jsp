<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${data == null? '등록':'수정' }</title>
<style>
	.container {text-align:center;}
	form {margin:100px auto; width:500px; height:300px; background-color:#F0E9F7; padding-top:80px; border-radius: 30%;}
	#title {margin-bottom: 30px; margin-top: -20px;}
	#title input {border:none; background-color: #F0E9F7; border-bottom: 1px solid black; text-align:center;}
	#ctnt {margin-bottom: 30px;}
	#ctnt textarea {width: 300px; height: 150px; }
	#btn input {margin-right:50px; width:80px; height:30px; background-color:#DBC9EC; border:none; border-radius:50%; }
	#btn {margin-left:100px;}
	#btn a {color:grey; text-decoration:none;}
</style>
</head>
<body>
	<div class="container">
		<form id="frm" action="/board/regmod" method="post" onsubmit="return chk()">
			<input type="hidden" name="i_board" value="${vo.i_board }">
			<div id="title"><input type="text" name="title" value="${vo.title }" placeholder="제목쓰세요"></div>
			<div id="ctnt"><textarea name="ctnt" placeholder="내용쓰세요">${vo.ctnt }</textarea></div>
			<div id="btn"><input type="submit" value="등록"> <a href="/board/list">뒤로</a></div>	
		</form>
	</div>
	
	<script>
		function eleValid(ele, nm){
			if(ele.value.length == 0 ){
				alert(nm + '을(를) 입력해주세요');
				ele.focus();
				return true;
			}
			
		}
		
		function chk() {
			if(eleValid(frm.title,'제목')) {
				return false;
			}
			if(eleValid(frm.ctnt,'내용')){
				return false;
			}
			if(frm.title.value.length > 100){
				alert("제목 줄여")
				return false;
			}
			if(frm.ctnt.value.length > 2000){
				alert("내용 줄여")
				return false;
			}
		}
	</script>
</body>
</html>