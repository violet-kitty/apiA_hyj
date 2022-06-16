<%@ page language = "java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link href="<%= request.getContextPath() %>/css/join.css" rel="stylesheet">
<title>로그인</title>
<script>
	function check(){
		var fm = document.frm;
        	
		if(fm.memberid.value == ""){
			alert("아이디를 입력하세요");
			fm.memberid.focus();
			return;
		}
		else if(fm.memberpwd.value==""){
			alert("비밀번호를 입력하세요");
			fm.memberpwd.focus();
			return;
		}
        		
		//가상경로를 사용해서 페이지 이동
		//fm.action="./memberjoinOk.jsp";
		fm.action="<%= request.getContextPath() %>/member/memberLoginAction.do";
		//가상경로를 Servlet이 처리.
		fm.method="post";
		fm.submit();	
		return;
	}
</script>
</head>
<body>
<h1>로그인 페이지</h1>
<hr>
<br><br>
<form name="frm">
	<fieldset class="login">
        <legend>로그인</legend>
        <ul>
            <li><label>아이디 </label><input type="text" name="memberid" size="20"><br><br></li>
            <li><label>비밀번호 </label><input type="password" name="memberpwd" size="20"><br><br></li>
        </ul>
        <br>
        <div class="center">
            <input type="button" name="btn" value="로그인" onclick="check();" class="pinkbutton">
            <input type="button" value="메인으로" class="pinkbutton" onclick="location.href='<%= request.getContextPath() %>/member/main.do'"><br>
		</div>
	</fieldset>
</form>
</body>
</html>