<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<% /* 이전에 하던대로 EL안쓰고 대신 getAttribute 사용해도됨 */ %>
<html>
<head>
	<title>Home</title>
	<meta charset="UTF-8">
</head>
<body>
<h1>
	Hello world!  
</h1>
<!-- 주석 잘못쓰면 오류난다... 조심 조심. $와 {} 붙여쓰지 말자... -->
<!-- $ 어쩌구는 controller에서 보낸 키가 들어가는 부분. 받아온 데이터 쓰는 부분 -->
<!-- 여기쓴 serverTime은 EL. 표현하는 언어..? -->
<P>  The time on the server is ${serverTime}. </P>

<a href="sample1.do">sample1.do</a>
<a href="sample2.do">sample2.do</a>
<br><br><br>
<a href="user/register.do">register</a>
<br><br>
<a href="user/join.do">회원가입하기</a>
</body>
</html>
