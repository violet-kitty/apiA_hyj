<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 각 매개변수 받아와 출력하는 코드 -->
<!-- 	
	<p>이름 : ${name}</p>
	<p>나이 : ${age}</p>
	<p>주소 : ${addr}</p>
	<p>연락처 : ${phone}</p>
-->
	<!-- vo로 값 받아와 출력하는 코드 -->
	<!-- vo가 어느 범위에 있는 애인지 정의 안되어 있으므로 모든 범위의 것들을 다 뒤지고 가장 좁은 범위의 것을 사용 -->
	<!-- vo의 필드명과 맞춰주면 편하게 불러오기 가능 -->
	${vo.name}<br>
	${vo.age}<br>
	${vo.addr}<br>
	${vo.phone}<br>
	
</body>
</html>