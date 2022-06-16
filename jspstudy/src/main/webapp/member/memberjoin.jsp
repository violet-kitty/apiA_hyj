<%@ page language = "java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link href="<%= request.getContextPath() %>/css/join.css" rel="stylesheet">
<title>회원가입</title>
<script>
	function check(){
		var fm = document.frm;
        	
		if(fm.memberid.value == ""){
			alert("아이디를 입력하세요");
			fm.memberid.focus();
			return;
		}
		else if(fm.membername.value==""){
			alert("이름을 입력하세요");
 			fm.membername.focus();
			return;
		}
		else if(fm.memberpwd.value==""){
			alert("비밀번호를 입력하세요");
			fm.memberpwd.focus();
			return;
		}
		else if(fm.memberpwdok.value==""){
			alert("비밀번호 확인을 입력하세요");
			fm.memberpwdok.focus();
			return;
		}
		else if(fm.memberpwd.value != fm.memberpwdok.value){
			alert("비밀번호가 일치하지 않습니다.");
			fm.memberpwdok.value="";
			fm.memberpwdok.focus();
			return;
		}
		else if(fm.memberemail.value==""){
			alert("이메일을 입력하세요");
			fm.memberemail.focus();
			return;
		}
		else if(fm.memberjumin.value==""){
			alert("주민번호를 입력하세요");
			fm.memberjumin.focus();
			return;
		}
		else if(fm.memberaddr.value==""){
			alert("지역을 입력하세요");
			fm.memberaddr.focus();
			return;
		}
		else if(fm.memberphone.value==""){
			alert("전화번호를 입력하세요");
			fm.memberphone.focus();
			return;
		}
		else{
			var chkYn = false;
			for(var i = 0;i<fm.memberhobby.length;i++){
			if(fm.memberhobby[i].checked == true){
				chkYn = true;
				break;
			}
		}
			//가상경로를 만들어 물리적인 주소를 알 수 없게 만들기.
			//원래 경로:./memberjoinOk.jsp
			//가상 경로:/member/memberJoinAction.do
			if(chkYn == false){
				var input = confirm("취미를 선택하지 않았습니다. 작성을 완료하시겠습니까?");
				if(input == true){
					//fm.action="./memberjoinOk.jsp";
					fm.action="<%= request.getContextPath() %>/member/memberjoinAction.do";
					fm.method="post";
					fm.submit();
					return;
				}
				else{
					return;
				}
			}
		}
        		
			//가상경로를 사용해서 페이지 이동
			//fm.action="./memberjoinOk.jsp";
			fm.action="<%= request.getContextPath() %>/member/memberjoinAction.do";
			//가상경로를 Servlet이 처리.
			fm.method="post";
			fm.submit();	
			return;
		}
</script>
</head>
<body>
<h1>회원가입 페이지</h1>
<hr>
<br><br>
<form name="frm">
	<fieldset class="join">
        <legend>회원가입</legend>
        <ul>
            <li><label>아이디 </label><input type="text" name="memberid" size="20"><br><br></li>
            <li><label>이름 </label><input type="text" name="membername" size="20"><br><br></li>
            <li><label>비밀번호 </label><input type="password" name="memberpwd" size="20"><br><br></li>
           	<li><label>비밀번호 확인 </label><input type="password" name="memberpwdok" size="20"><br><br></li>
            <li><label>이메일 </label><input type="email" name="memberemail" placeholder="abcd@abcd"><br><br></li>
            <li><label>성별 </label><input type="radio" name="membergender" value="M" checked>남성 
            	  <input type="radio" name="membergender" value="F">여성<br><br></li>
           	<li><label>주민번호 </label><input type="text" name="memberjumin" size="20" onkeyup="this.value=this.value.replace(/[^-0-9]/g,'');"><br><br></li>
            <li>
            	<label style="width : 165px;">지역</label>
            	<select name="memberaddr">
	                <option value="전주">전주</option>
	                <option value="서울">서울</option>
	                <option value="대전">대전</option>
	                <option value="군산">군산</option>
	                <option value="익산">익산</option>
            	</select>
            	<br><br>
            </li>
           	<li><label>전화번호 </label><input type="text" name="memberphone" size="20" placeholder="000-0000-0000" onkeyup="this.value=this.value.replace(/[^-0-9]/g,'');" ><br><br></li>
            <li><label>취미 </label><input type="checkbox" name="memberhobby" value="야구">야구
            <input type="checkbox" name="memberhobby" value="축구">축구
            <input type="checkbox" name="memberhobby" value="농구">농구<br><br></li>
        </ul>
        <br><br><br>
        <div class="center">
            <input type="button" name="btn" value="확인" onclick="check();" class="pinkbutton">
            <input type="reset" name="reset" value="다시 작성하기" class="pinkbutton">
            <input type="button" value="메인으로" class="pinkbutton" onclick="location.href='<%= request.getContextPath() %>/member/main.do'"><br>
		</div>
	</fieldset>
</form>
</body>
</html>