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
 * Servlet implementation class SessionMethod
 */
@WebServlet("/ch09/sessionMethod")
public class SessionMethod extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		boolean isNew = session.isNew();
		String id = session.getId();
		int MaxInactiveInterval = session.getMaxInactiveInterval();
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<h1>isNew: " + isNew + "</h1>");
		out.print("<h1>id: " + id + "</h1>");
		out.print("<h1>MaxInactiveInterval: " + MaxInactiveInterval + "</h1>");
		out.print("<h1>isNew: " + session.isNew() + "</h1>");
		//isNew 기능 false 상태에서 밥먹고와서 새로고침해보니 true가 되었음!
		//엣지에도 해당 서블릿 적용해보니 true 뜸, 차이점은 브라우저가 다르기 때문에 세션 ID가 달라짐
	}
}
