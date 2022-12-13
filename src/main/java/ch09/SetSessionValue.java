package ch09;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SetSessionValue
 */
@WebServlet("/ch09/setSession")
public class SetSessionValue extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//session.setMaxInactiveInterval(5);		//유효시간을 5초로 설정
		
		String id = session.getId();
		int MaxInactiveInterval = session.getMaxInactiveInterval();
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<h1>isNew: " + session.isNew() + "</h1>");
		out.print("<h1>id: " + id + "</h1>");
		out.print("<h1>MaxInactiveInterval: " + MaxInactiveInterval + "</h1>");
		
		session.invalidate();	//세션을 강제로 삭제
		
	}

}