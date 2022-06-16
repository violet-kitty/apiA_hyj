<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>sample01.jsp</title>
</head>
<body>
<h2>sample01.jsp 입니다.</h2>

<!-- 
	home.jsp에서 sample01.do 링크 클릭시 
	sample01.jsp로 포워딩 하세요.
	이때 컨트롤러에서는 '안녕하세요'라는 데이터를 받아와서 출력하세요.
 -->
${str}


	<!-- sample01에서 받은 데이터를 sample02에 출력하기 -->
	<!-- 같은 위치에 있으므로 앞에 / 없이 보냄 -->
	<form action="sample2.do" method="post">
		data : <input type="text" name="testData"><br>
		<input type="submit">
	</form>



</body>
</html>