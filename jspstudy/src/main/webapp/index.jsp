<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
	<style>
		body{
        	background-color: #ffe6f2;
        }
        hr{
        	border : 1px solid #ff0080;
        }
        h1{
        	text-align : center;
        	color : #ff0080;
        }
		div{
			text-align: center;
		}
		.pinkbutton{
        		background-color: #ffe6f2;
        		border-radius : 5px;
        		border : 1px solid #ff0080;
        		color : #ff0080;
        		font-size : 1.2em;
        		display : inline-block;
        		margin : 10px;
        		width : 200px;
        		height : 50px;
        		cursor : pointer;
        	}
	</style>
</head>
<body>
<h1>메인!111111111</h1>
<hr>
<br>
<div>
	<!-- 경로에 request.getContextPath()을 붙여줘야함. 안그러면 404 오류가 남.(페이지 뿐 아니라 이미지 등 경로 들어가는 모든것에서 오류...) -->
	<input type="button" value = "회원가입" class="pinkbutton" onclick="location.href='<%= request.getContextPath() %>/member/memberjoin.do';">
	<input type="button" value = "회원목록" class="pinkbutton" onclick="location.href='<%= request.getContextPath() %>/member/memberList.do';">
	<input type="button" value = "회원로그인" class="pinkbutton" onclick="location.href='<%= request.getContextPath() %>/member/memberLogin.do';"><br>
</div>
<div>
	<input type="button" value = "게시판 글쓰기" class="pinkbutton" onclick="location.href='<%= request.getContextPath() %>/board/boardWrite.do';">
	<input type="button" value = "게시판 리스트" class="pinkbutton" onclick="location.href='<%= request.getContextPath() %>/board/boardList.do';">
	<input type="button" value = "차트 테스트" class="pinkbutton" onclick="location.href='<%= request.getContextPath() %>/board/boardTest.jsp';"><br>
	<img src="<%= request.getContextPath() %>/home.jpg" width="800" height="500">
</div>	
</body>
</html>