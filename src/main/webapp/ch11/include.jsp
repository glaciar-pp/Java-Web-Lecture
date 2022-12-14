<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Include 디렉티브 사용</title>
</head>
<body>
	<h1>안녕하세요. 쇼핑몰 중심 JSP 시작입니다.</h1>
	<%@ include file="duke_image.jsp" %><br>		<!-- 다른 파일을 불러왔음.! -->
		<%@ include file="_duke.jspf" %>
	<h1>안녕하세요, 쇼핑몰 중심 JSP 끝 입니다.</h1>
</body>
</html>