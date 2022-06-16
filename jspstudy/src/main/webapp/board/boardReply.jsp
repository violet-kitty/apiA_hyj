<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "jspstudy.domain.BoardVo" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답변하기</title>
<link href="<%= request.getContextPath() %>/css/board.css" rel="stylesheet">
<% BoardVo bv = (BoardVo)request.getAttribute("bv"); %>
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
	
	fm.action="<%= request.getContextPath() %>/board/boardReplyAction.do";
	fm.method="post";
	fm.submit();
	
	return;
}

</script>
</head>
<body>
<h1>게시판 답변하기</h1>
<hr>
<br>
<div class="roundDiv">
<form name = "frm">
<!-- boardReplyAction에서 쓸 변수들 넘겨주기 -->
<input type="hidden" name="bidx" value="<%= bv.getBidx() %>">
<input type="hidden" name="originbidx" value="<%= bv.getOriginbidx() %>">
<input type="hidden" name="depth" value="<%= bv.getDepth() %>">
<input type="hidden" name="level_" value="<%= bv.getLevel_() %>">
<table>
	<thead>
		<tr>
		<th width="10%" height="50px" style="background-color:#ffe6f2">제목</th>
		<th width = "60%"><input type="text" name="subject"></th>
		<th width = "10%" style="background-color:#ffe6f2">작성자</th>
		<th width = "20%"><input type="text" name="writer"></th>
		</tr>
	</thead>
	<tbody>
		<tr>
		<td colspan="4"><textarea rows="40" cols="200" name="content"></textarea></td>
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