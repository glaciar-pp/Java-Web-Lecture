<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num = (int) Math.ceil(Math.random()*10);
	String result = (num % 2 == 0) ? "짝수" : "홀수";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>홀수 짝수</title>
</head>
<body>
	<h1>홀수 짝수</h1>
	<hr>
	<h3>난수를 통해서 얻은 숫자: <%= num %></h3>
	<h3><%= result %>입니다.</h3>
</body>
</html>

<!-- 이런 형태가 MVC model 1. 앞으로는 분리된 형태로 작성하기

MVC model 1 - 하나의 파일 안에서 Model, Viewer, Controller 구분

MVC model 2 - 별도의 파일로 구분  -->