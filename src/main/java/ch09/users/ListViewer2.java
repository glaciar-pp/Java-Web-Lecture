package ch09.users;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ListViewer2
 */
@WebServlet("/ch09/users/listView")
public class ListViewer2 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset = utf-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String sessionUid = (String)session.getAttribute("uid");
		
		List<User> list = (List<User>) request.getAttribute("userList");
		
		String data = "<!DOCTYPE html>"
				+ "<html lang=\"ko\">"
				+ "<head>"
				+ "    <meta charset=\"UTF-8\">"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
				+ "    <title>사용자 목록</title>"
				+ "    <style>"
				+ "        td { text-align: center; padding: 3px;}"
				+ "    </style>"
				+ "</head>"
				+ "<body style=\"margin: 40px;\">"
				+ "    <h1>사용자 목록</h1>";
		if (sessionUid != null) {	//로그인된 상태(세션 내부내용을 확인하는 것)
		data += "<button onclick=\"location.href='/jw/ch09/users/logout'\">로그아웃</button>";
		data += session.getAttribute("uname") + "님 환영합니다.";
		} else 		//세션에서 내용을 확인했으나 null인 경우라면 로그인 버튼으로 감!
		data += "<button onclick=\"location.href='/jw/ch09/users/login.html'\">로그인</button>";
				
		data += "<hr>"
				+ "    <table border=\"1\">"
				+ "        <tr>"
				+ "            <th>UID</th><th>PWD</th><th>Name</th><th>email</th><th>등록일자</th><th>액션</th>";
		for (User u : list) {
			data += "<tr>";
			data += "<td>" + u.getUid() + "</td>";
			data += "<td>" + u.getPwd() + "</td>";
			data += "<td>" + u.getUname() + "</td>";
			data += "<td>" + u.getEmail() + "</td>";
			data += "<td>" + u.getRegDate() + "</td>";
			data += "<td>";
			
			//본인만이 수정 권한이 있음
			if (sessionUid == null || !sessionUid.equals(u.getUid())) //u.getUid()와 session에 들어있는 정보가 같은가?를 보는 것
				data += "<button onclick=\"location.href='/jw/ch09/users/update?uid='\" disabled>수정</button>";
			else
				data += "<button onclick=\"location.href='/jw/ch09/users/update?uid='\"" + sessionUid + "\">수정</button>";
			
			// 관리자(admin) 만이 삭제 권한이 있음
			if (sessionUid == null || !sessionUid.equals("admin"))
				data += "<button onclick=\"location.href='/jw/ch09/users/delete?uid='\" disabled>삭제</button>";			
			else
				data += "<button onclick=\"location.href='/jw/ch09/users/delete?uid='\">삭제</button>";
				/* 관리자 삭제권한 부분 || 를 통해 단축함
				 * if(sessionUid.equals("admin"))
				 * "<button onclick=\"location.href='/jw/ch09/users/delete?uid='\">삭제</button>";
				 * else data +=
				 * "<button onclick=\"location.href='/jw/ch09/users/delete?uid='\" disabled>삭제</button>";
				 * 
				 * 관련 내용
				 * Short circuit rule
				 * if(A || B) - A 가 참이면 B는 테스트 하지 않는다. 
				 * if(S && B) - A 가 거짓이면 B는 테스트 하지 않는다. 
				 * 
				 * 신기하게도, 이런 로직에 대한 부분은 홈페이지를 열어 페이지 소스 보기를 하면 나오지 않음!
				 * 로직에 따라 실행된 부분이 나오기 때문! 우리가 사용하는 많은 페이지가 이렇게 속속들이 로직이 숨어있음!!
				 */			
			data += "</td>";
			data += "</tr>";
		}
		data += "</table>"
				+ "    <br>"
				+ "    <a href=\"/jw/ch09/users/register.html\">회원가입</a>"
				+ "</body>"
				+ "</html>";
		out.print(data);
			
	}
}
