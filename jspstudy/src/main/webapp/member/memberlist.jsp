<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "jspstudy.domain.*" %>
<%@ page import = "java.util.ArrayList" %>

<%
	/*
	jsp에서만 처리를 하다가 javabean, class 이용하기 시작한게 Model1 방식
	jsp를 바로 처리하는게 아니라 servlet으로 변환 작업이 있음
	그래서 jsp는 서버가 해야하는 변환작업이 많이 서버부하가 걸릴 수 있음
	그럼 처음부터 servlet으로 만들자! > jsp 대신 java로 구현(function.jsp 대신 MemberDao.java, dbcon.jsp 대신 DBconn.java)
	*/
	//SELECT 쿼리를 사용하기 위해서 function에서 메소드를 만든다.
	//memberSelectAll 메소드 호출
	//MemberDao md = new MemberDao();
	//ArrayList<MemberVo> alist = md.memberSelectAll();
	
	ArrayList<MemberVo> alist = (ArrayList<MemberVo>)request.getAttribute("alist");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
<link href="<%= request.getContextPath() %>/css/list.css" rel="stylesheet">
</head>
<body>
<!-- 세션 사용 -->
<%
if(session.getAttribute("midx")!=null){
	out.println("회원 아이디 : "+session.getAttribute("memberId")+"<br>");
	out.println("회원 이름 : "+session.getAttribute("memberName")+"<br>");
	
	//로그아웃
	out.println("<a href='"+request.getContextPath()+"/member/memberLogout.do' class='logout'>로그아웃</a>");
}

%>

<h1>회원목록 리스트</h1>
<table class="list">
	<thead>
		<tr>
		<th>번호</th>
		<th>회원이름</th>
		<th>회원연락처</th>
		<th>가입일</th>
		</tr>
	</thead>
	<tbody>
		<%
			for(MemberVo mv : alist){
		%>		
			<tr>
			<td><% out.println(mv.getMidx()); %></td>
			<td><% out.println(mv.getMembername()); %></td>	
			<td><% out.println(mv.getMemberphone()); %></td>
			<td><% if(mv.getWriteday()!=null)out.println(mv.getWriteday()); else out.println("데이터 없음"); %></td>
			</tr>
		<%		
			}
				
%>				
	</tbody>
</table>
<br><br><br>
<div>
	<input type="button" value = "메인으로" class="pinkbutton" onclick="location.href='<%= request.getContextPath() %>/member/main.do';"><br>
</div>
<br><br>
</body>
</html>