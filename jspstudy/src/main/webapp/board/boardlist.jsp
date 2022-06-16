<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "jspstudy.domain.*" %>
<%@ page import = "java.util.ArrayList" %>

<%
	ArrayList<BoardVo> alist = (ArrayList<BoardVo>)request.getAttribute("alist");  // controller에서 보낸 alist
	PageMaker pm = (PageMaker)request.getAttribute("pm");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
<link href="<%= request.getContextPath() %>/css/list.css" rel="stylesheet">
</head>
<body>
<h1>게시글 리스트</h1>
<form name="frm" action="<%= request.getContextPath() %>/board/boardList.do" method="post">
<table class="search">
<tr>
	<td style="width:760px;"><select name="searchType">
	<option value="subject">제목</option>
	<option value="writer">작성자</option>
	</select></td>
	<td><input type="text" name="keyword" size="20"></td>
	<td>
	<input type="submit" name="submit" value="검색">
	</td>
</tr>
</table>
</form>
<table class="list">
	<thead>
		<tr>
		<th style="width:10%">bidx번호</th>
		<th style="width:45%">제목</th>
		<th style="width:20%">작성자</th>
		<th style="width:25%">작성일</th>
		</tr>
	</thead>
	<tbody>
	<% for(BoardVo bv : alist) {%>
		<tr>
		<td><%=bv.getBidx() %></td>
		<!-- 제목을 왼쪽 정렬을 하지 않을 경우 답글 들여쓰기가 이상하게됨 -->
		<td style="text-align : left">
		<%
			//답글인 경우 들여쓰기
			for(int i=1; i<=bv.getLevel_(); i++){
				out.print("&nbsp;&nbsp;&nbsp;");
				if(i==bv.getLevel_()){
					out.print("➥");
				}
			}
		%>
		<a href="<%=request.getContextPath()%>/board/boardContent.do?bidx=<%= bv.getBidx()%>"><%=bv.getSubject() %></a></td>
		<td><%=bv.getWriter() %></td>
		<td><%=bv.getWriteday() %></td>
		</tr>
	<% } %>
	</tbody>
</table>
<table class="paging">
	<tbody>
	<%
	// 만약 검색했는데 검색된 데이터가 없을 경우
	if(pm.getTotalCount() == 0){
		out.print("<tr><td colspan='4'>검색된 데이터가 없습니다.</td></tr>");
	}
	%>
		<tr>
		<td style="width:20%">
		<%
		if(pm.isPrev() == true){
			out.print("<a href='"+request.getContextPath()+"/board/boardList.do?page="+(pm.getStartPage()-1)+"&keyword="+pm.encoding(pm.getScri().getKeyword())+"&searchType="+pm.getScri().getSearchType()+"'>◀</a>");
		}
		%>
		</td>
		<td style="width:60%">
		<%
		for(int i=pm.getStartPage(); i<=pm.getEndPage();i++){
			if(i == pm.getScri().getPage()){
				out.println("<a href='"+request.getContextPath()+"/board/boardList.do?page="+i+"&keyword="+pm.encoding(pm.getScri().getKeyword())+"&searchType="+pm.getScri().getSearchType()+"' style='font-weight:bold;color:#ff0080;font-size:1.2em'>"+i+"</a>");
			}
			else {
				out.println("<a href='"+request.getContextPath()+"/board/boardList.do?page="+i+"&keyword="+pm.encoding(pm.getScri().getKeyword())+"&searchType="+pm.getScri().getSearchType()+"'>"+i+"</a>");
			}
		}
		%>
		</td>
		<td style="width:20%">
		<%
		if(pm.isNext() && pm.getEndPage() > 0){
			out.print("<a href='"+request.getContextPath()+"/board/boardList.do?page="+(pm.getEndPage()+1)+"&keyword="+pm.encoding(pm.getScri().getKeyword())+"&searchType="+pm.getScri().getSearchType()+"'>▶</a>");
		}
		%>
		</td>
		</tr>
	</tbody>
</table>
<br><br>
<div>
	<input type="button" value = "게시판 글쓰기" class="pinkbutton" onclick="location.href='<%= request.getContextPath() %>/board/boardWrite.do';">
	<input type="button" value = "메인으로" class="pinkbutton" onclick="location.href='<%= request.getContextPath() %>/member/main.do';"><br>
</div>
<br><br>
</body>
</html>