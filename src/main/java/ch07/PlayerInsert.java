package ch07;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PlayerInsert
 */
@WebServlet("/ch07/registerPlayer")
public class PlayerInsert extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int backNo = Integer.parseInt(request.getParameter("backNo"));
		String name = request.getParameter("name");
		String position = request.getParameter("position");
		String birthday = request.getParameter("birthday");
		int height = Integer.parseInt(request.getParameter("height"));
		
		PlayerDAO dao = new PlayerDAO();
		//유효성 검증
		Player p = dao.getPlayer(backNo);
		if (p.getName() != null)	//기존 사용자가 있는 경우
			response.sendRedirect("/jw/ch07/registerPlayer.html");  //초기화면으로 돌려보냄
		else {
			p = new Player(backNo, name, position, birthday, height);
			dao.insertPlayer(p);
			response.sendRedirect("/jw/ch07/playerList");
		}
	}
}
