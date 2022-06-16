<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 삭제</title>
<link href="<%= request.getContextPath() %>/css/confirm.css" rel="stylesheet">
<%
	String bidx = (String)request.getAttribute("bidx");
%>
</head>
<body>
<div class="confirm">
<div class="checkTop">
</div>
<div class="checkMid">
	정말 글을 삭제하시겠습니까?
</div>
<div class="checkBottom">
	<div class="left">
		<input type="button" value = "확인" class="pinkbutton" onclick="location.href='<%= request.getContextPath() %>/board/boardDeleteAction.do?bidx=<%= bidx %>';">
	</div>
	<div class="right">
		<input type="button" value = "취소" class="pinkbutton" onclick="location.href='<%= request.getContextPath() %>/board/boardContent.do?bidx=<%= bidx %>';">
	</div>
</div>
</div>
</body>
</html>