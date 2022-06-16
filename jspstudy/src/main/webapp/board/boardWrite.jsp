<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
if(session.getAttribute("midx") == null){
	//로그인 후 다시 이 페이지로 돌아와야 하므로 세션에 해당 페이지 url을 담음
	session.setAttribute("saveUrl", request.getRequestURI());
	
	out.println("<script>alert('로그인을 해주세요.'); location.href='"+request.getContextPath()+"/member/memberLogin.do'; </script>");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<link href="<%= request.getContextPath() %>/css/board.css" rel="stylesheet">
<script>
function check(){
	var fm = document.frm;

	if(fm.subject.value == ""){
		alert("제목을 입력하세요");
		fm.subject.focus();
		return;
	}
	else if(fm.writer.value == ""){
		alert("작성자를 입력하세요");
		fm.writer.focus();
		return;
	}
	else if(fm.content.value == ""){
		alert("내용을 입력하세요");
		fm.content.focus();
		return;
	}
	
	fm.action="<%= request.getContextPath() %>/board/boardWriteAction.do";
	fm.method="post";
	fm.enctype="multipart/form-data";	// 파일 올리기 위한 속성. form의 내용과 이미지가 같이 보내지도록.
	fm.submit();
	
	return;
}

</script>
</head>
<body>
<h1>게시판 글쓰기</h1>
<hr>
<br>
<div class="roundDiv">
<form name = "frm">
<table>
	<thead>
		<tr>
		<th width="10%" height="50px" style="background-color:#ffe6f2">제목</th>
		<th width = "60%"><input type="text" name="subject"></th>
		<th width = "10%" style="background-color:#ffe6f2">작성자</th>
		<th width = "20%"><input type="text" name="writer" value="<%= session.getAttribute("memberName") %>" readonly="readonly"></th>
		</tr>
	</thead>
	<tbody>
		<tr>
		<td colspan="4"><textarea rows="40" cols="200" name="content"></textarea></td>
		</tr>
		<tr>
		<!-- 파일은 하나만 올리기 가능 -->
		<td width="10%" height="50px" style="background-color:#ffe6f2">파일 올리기</td>
		<td colspan="3"><input type="file" name="filename"></td>
		</tr>
	</tbody>
	<tfoot>
		<tr><th colspan="4" style="border : none;">
		<input type="button" value = "확인" class="pinkbutton" onclick="check()">
		<input type="reset" value = "리셋" class="pinkbutton">
		<input type="button" value = "목록" class="pinkbutton" onclick="location.href='<%= request.getContextPath() %>/board/boardList.do';">
		</th></tr>
	</tfoot>
</table>
</form>
</div>
<br><br>
</body>
</html>