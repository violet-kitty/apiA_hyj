<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "jspstudy.domain.BoardVo" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 내용보기</title>
<link href="<%= request.getContextPath() %>/css/board.css" rel="stylesheet">
<%
	BoardVo bv = (BoardVo)request.getAttribute("bv");
%>
</head>
<body>
<h1>게시판 내용 보기</h1>
<hr>
<br>
<div class="roundDiv">
<table>
	<thead>
		<tr>
		<th width="10%" height="50px" style="background-color:#ffe6f2;">제목</th>
		<th width = "60%" style=" text-align : left; padding-left : 5px;"><%= bv.getSubject() %></th>
		<th width = "10%" style="background-color:#ffe6f2">작성자</th>
		<th width = "20%"><%= bv.getWriter() %></th>
		</tr>
	</thead>
	<tbody>
		<tr>
		<td colspan="4"><%= bv.getContent().replace("\n", "<br>") %></td>
		</tr>
		<tr>
		<!-- 파일이 존재하는 경우만 파일의 이미지 보여주기 -->
		<% if(bv.getFilename() != null) {%>
		<td width="10%" height="50px" style="background-color:#ffe6f2">파일</td>
		<td colspan="3">
		<img src="<%= request.getContextPath()%>/images/<%= bv.getFilename() %>"><br>
		<a href="<%= request.getContextPath()%>/board/fileDownload.do?filename=<%= bv.getFilename() %>"><%= bv.getFilename() %></a>
		</td>
		<%}%>
		</tr>
	</tbody>
	<tfoot>
		<tr><th colspan="4" style="border : none;">
		<input type="button" value = "수정" class="pinkbutton" onclick="location.href='<%= request.getContextPath() %>/board/boardModify.do?bidx=<%= bv.getBidx() %>'">
		<input type="button" value = "삭제" class="pinkbutton" onclick="location.href='<%= request.getContextPath() %>/board/boardDelete.do?bidx=<%= bv.getBidx() %>'">
		<input type="button" value = "답변" class="pinkbutton" onclick="location.href='<%= request.getContextPath() %>/board/boardReply.do?bidx=<%= bv.getBidx() %>&originbidx=<%= bv.getOriginbidx() %>&depth=<%= bv.getDepth() %>&level_=<%= bv.getLevel_() %>'">
		<input type="button" value = "목록" class="pinkbutton" onclick="location.href='<%= request.getContextPath() %>/board/boardList.do';">
		</th></tr>
	</tfoot>
</table>
</div>
<br><br>
</body>
</html>