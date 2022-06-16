<%@page import="jspstudy.domain.BoardVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardVo bv = (BoardVo)request.getAttribute("bv");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%= bv.getContent() %>
<form action="<%= request.getContextPath() %>/board/editorModify.do">
<input type="hidden" name="bidx" value="<%= bv.getBidx() %>">
<button>수정</button>
</form>
</body>
</html>