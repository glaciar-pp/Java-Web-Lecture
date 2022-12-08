package ch06;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterMember
 */
@WebServlet("/ch06/regMember")
public class RegisterMember extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 입력
		System.out.println("RegisterMember.doGet() method");
		request.setCharacterEncoding("utf-8"); // 꼭 하지 않아도 인코딩 오류 발생하지 않음
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//파라미터 입력
		String uid = request.getParameter("uid");
		String pwd = request.getParameter("pwd");
		String pwd2 = request.getParameter("pwd2");
		String userName = request.getParameter("userName");
		String birthday = request.getParameter("birthday");
		String userEmail = request.getParameter("userEmail");
		String gender = request.getParameter("gender");
		String[] hobbies = request.getParameterValues("hobby");

		//데이터 모음 만들기
		String data ="uid: " + uid + "\n"; 		
		data += "pwd: " + pwd.equals(pwd2) + "\n";
		data += "userName: " + userName + "\n";
		data += "birthday: " + birthday + "\n";
		data += "userEmail: " + userEmail + "\n";
		data += "gender: " + gender + "\n";	
		for(String hobby : hobbies)
			data += "hobby: " + hobby + "\n";
		
		response.setCharacterEncoding("utf-8");		// 굳이 안해도 인코딩 오류 발생하지 않음
		response.setContentType("text/html; charset=utf-8");	// 반드시 세팅해주어야 함
		PrintWriter out = response.getWriter();
		out.print("<!DOCTYPE html>");
		out.print("<html lang=\"ko\">");
		out.print("<head>");
		out.print("<meta charset=\"UTF-8\">");
		out.print("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
		out.print("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		out.print("<title>회원가입</title>");
		out.print("</head>");
		out.print("<body style=\"margin: 40px;\">");
		out.print("<h1>회원가입 정보</h1>");
		out.print("<hr>");
		// out.print(data);
		String[] ulList = data.split("\n");
		out.print("     <ul>");
		for (String li : ulList) {
			out.print("     <li>" + li + "</li>");
		}
		out.print("     </ul>");
		out.print("</body>");
		out.print("</html>");
	}

}

/* 프로그램 가동에 문제가 발생했을 경우 시도 가능한 방법
 * 1. tomcat 실행 중지
 * 2. 프로젝트 탭 - 문제가 생긴 프로젝트에 대해 clean 진행
 * 3. 다시 Build 가동
 * 4. 이클립스 종료 후 재시작
 * 5. 재실행
 *  
 *  
 *  */
