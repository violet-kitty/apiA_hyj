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
<h2>sample01.jsp �Դϴ�.</h2>

<!-- 
	home.jsp���� sample01.do ��ũ Ŭ���� 
	sample01.jsp�� ������ �ϼ���.
	�̶� ��Ʈ�ѷ������� '�ȳ��ϼ���'��� �����͸� �޾ƿͼ� ����ϼ���.
 -->
${str}


	<!-- sample01���� ���� �����͸� sample02�� ����ϱ� -->
	<!-- ���� ��ġ�� �����Ƿ� �տ� / ���� ���� -->
	<form action="sample2.do" method="post">
		data : <input type="text" name="testData"><br>
		<input type="submit">
	</form>



</body>
</html>