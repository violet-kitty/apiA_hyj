<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jspstudy.domain.BoardVo" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
	BoardVo bv = (BoardVo)request.getAttribute("bv");

	if(session.getAttribute("midx") == null){
		out.println("<script>alert('로그인을 해주세요.'); location.href='"+request.getContextPath()+"/member/memberLogin.do'; </script>");
	}
%>
<link href="<%= request.getContextPath() %>/css/board.css" rel="stylesheet">
<title>글 수정</title>
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
	
	fm.action="<%= request.getContextPath() %>/board/boardModifyAction.do";
	fm.method="post";
	fm.submit();
	
	return;
}

</script>
</head>
<body>
<h1>게시판 글 수정</h1>
<hr>
<br>
<div class="roundDiv">
<form name="frm">
<input type="hidden" name="bidx" value="<%= bv.getBidx() %>">
<table>
	<thead>
		<tr>
		<th width="10%" height="50px" style="background-color:#ffe6f2">제목</th>
		<th width = "60%"><input type="text" name="subject" value="<%= bv.getSubject() %>"></th>
		<th width = "10%" style="background-color:#ffe6f2">작성자</th>
		<th width = "20%"><input type="text" name="writer" value="<%= bv.getWriter() %>"></th>
		</tr>
	</thead>
	<tbody>
		<tr>
		<td colspan="4"><textarea rows="40" cols="200" name="content"><%= bv.getContent() %></textarea></td>
		</tr>
	</tbody>
	<tfoot>
		<tr><th colspan="4" style="border : none;">
		<input type="button" value = "확인" class="pinkbutton" onclick="check()">
		<input type="button" value = "취소" class="pinkbutton" onclick="location.href='<%= request.getContextPath() %>/board/boardContent.do?bidx=<%= bv.getBidx() %>';"><br>
		</th></tr>
	</tfoot>
</table>
</form>
</div>
<br><br>
</body>
</html>