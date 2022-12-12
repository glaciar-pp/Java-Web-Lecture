package ch08.player;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Pcontroller
 */
@WebServlet({ "/ch08/player/list", "/ch08/player/register", "/ch08/player/update", "/ch08/player/release" })
public class Pcontroller extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String servletPath = request.getServletPath();
		PlayerDAO dao = new PlayerDAO();
		response.setContentType("text/html; charset=utf-8");
		
		if (servletPath.indexOf("list") > 0) {
			List<Player> list = dao.getPlayers();
			request.setAttribute("playerList", list);
			RequestDispatcher rd = request.getRequestDispatcher("/ch08/player/listView");
			rd.forward(request, response);
			
		}else if (servletPath.indexOf("register") > 0) {
			response.sendRedirect("/jw/ch08/player/register.html");
			//client 요청 관련 명령은 /jw 까지 붙여주어야함, localhost가 아니라서 위치를 모르니까..
			
		}else if (servletPath.indexOf("update") > 0) {
			int backNo = Integer.parseInt(request.getParameter("backNo"));
			Player p = dao.getPlayer(backNo);
			request.setAttribute("player", p);
			RequestDispatcher rd = request.getRequestDispatcher("/ch08/player/updateView");
			rd.forward(request, response);
			
		}else if (servletPath.indexOf("release") > 0) {
			int backNo = Integer.parseInt(request.getParameter("backNo"));
			dao.releasePlayer(backNo);
			response.sendRedirect("/jw/ch08/player/list");			
		}else {
			System.out.println("GET의 잘못된 경로 입니다");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String servletPath = request.getServletPath();
		PlayerDAO dao = new PlayerDAO();
		response.setContentType("text/html; charset=utf-8");
		
		//POST는 register와 update 두가지 필요.
		if (servletPath.indexOf("register") > 0) {
			int backNo = Integer.parseInt(request.getParameter("backNo"));
			String name = request.getParameter("name");
			String position = request.getParameter("position");
			String birthday = request.getParameter("birthday");
			int height = Integer.parseInt(request.getParameter("height"));
			
			//유효성 검증
			Player p = dao.getPlayer(backNo);
			if (p.getName() != null)		//기존 사용자가 있는 경우
				response.sendRedirect("/jw/ch08/player/register.html");
			else {						//기존 사용자가 없는 경우
				p = new Player(backNo, name, position, birthday, height);
				dao.insertPlayer(p);
				response.sendRedirect("/jw/ch08/player/list");
			}
		}else if (servletPath.indexOf("update") > 0) {
			int backNo = Integer.parseInt(request.getParameter("backNo"));
			String name = request.getParameter("name");
			String position = request.getParameter("position");
			String birthday = request.getParameter("birthday");
			int height = Integer.parseInt(request.getParameter("height"));
			
			Player p = new Player(backNo, name, position, birthday, height);
			dao.updatePlayer(p);
			response.sendRedirect("/jw/ch08/player/list");
		}else {
			System.out.println("POST의 잘못된 경로 입니다");
		}
	}
}
