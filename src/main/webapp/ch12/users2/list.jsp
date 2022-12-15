<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="ch12.users2.*" %>
<%
	String sessionUid = (String)session.getAttribute("uid");		//별도로 세션 선언하지 않아도 사용가능
	List<User> list = (List<User>) request.getAttribute("userList");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>사용자 목록</title>
    <style>
        td { text-align: center; padding: 3px;}
    </style>
</head>
<body style="margin: 40px;">
    <h1>사용자 목록</h1>
   <% if (sessionUid != null) {	// 로그인 된 상태 %>
		<button onclick="location.href='/jw/ch12/users2/logout'">로그아웃</button>
	<%= session.getAttribute("uname") %>님 환영합니다.
	<% } else { %>
  		<button onclick="location.href='/jw/ch12/users2/login'">로그인</button>
	<% } %>
    <hr>
    <table border="1">
        <tr>
            <th>UID</th><th>PWD</th><th>Name</th>
            <th>email</th><th>등록일자</th><th>액션</th>
        </tr>
          <% for (User u: list) { %>
        <tr>
            <td><%= u.getUid() %></td>
            <td><%= u.getPwd() %></td>
            <td><%= u.getUname() %></td>
            <td><%= u.getEmail() %></td>
            <td><%= u.getRegDate() %></td>
            <td>
             <%-- 본인만이 수정 권한이 있음 --%>
            <% if (sessionUid == null || !sessionUid.equals(u.getUid())) { %>
            	<button disabled>수정</button>
            <% } else { %>
            	<button onclick="location.href='/jw/ch12/users2/update?uid=<%=u.getUid()%>'">수정</button>
            <% } %>
                
            <%-- 관리자(admin) 만이 삭제 권한이 있음 --%>
            <% if (sessionUid == null || !sessionUid.equals("admin")) { %>
            	<button disabled>삭제</button>
            <% } else { %>
                <button onclick="location.href='/jw/ch12/users2/delete?uid=<%=u.getUid()%>'">삭제</button>
            <% } %>
            </td>
        </tr>
    <% } %>
    </table>
    <br>
    <a href="/jw/ch12/users2/register">회원가입</a>
</body>
</html>