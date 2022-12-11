package ch07;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PlayerUpdate
 */
@WebServlet("/ch07/updatePlayerInfo")
public class PlayerUpdate extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int backNo = Integer.parseInt(request.getParameter("backNo"));
		
		PlayerDAO dao = new PlayerDAO();
		Player p = dao.getPlayer(backNo);		
		
		response.setCharacterEncoding("utf-8");		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String data = "<!DOCTYPE html>"
				+ "<html lang=\"ko\">"
				+ "<head>"
				+ "    <meta charset=\"UTF-8\">"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
				+ "    <title>선수 정보 수정</title>"
				+ "    <style>"
				+ "        td { text-align: center; padding: 3px;}"
				+ "    </style>"
				+ "</head>"
				+ "<body style=\"margin: 40px;\">"
				+ "    <h1>선수 정보 수정</h1>"
				+ "    <hr>"
				+ "    <form action=\"/jw/ch07/updatePlayerInfo\" method=\"post\">"
				+ "        <input type=\"hidden\" name=\"backNo\" value=\"" + p.getBackNo() +"\">"
				+ "        <table>"
				+ "            <tr>"
				+ "                <td>선수 Back Number 입력</td>"
				+ "                <td><input type=\"number\" name=\"backNo\" value=\"" + p.getBackNo() + "\" disabled></td>"
				+ "            </tr>"
				+ "            <tr>"
				+ "                <td>선수 성명</td>"
				+ "                <td><input type=\"text\" name=\"name\" value=\"" + p.getName() +"\"></td>"
				+ "            </tr>"
				+ "            <tr>"
				+ "                <td>포지션</td>"
				+ "                <td><input type=\"text\" name=\"position\" value=\"" + p.getPosition() + "\"></td>"
				+ "            </tr>"
				+ "            <tr>"
				+ "                <td>선수 생년월일</td>"
				+ "                <td><input type=\"text\" name=\"birthday\" value=\"" + p.getBirthday() + "\"></td>"
				+ "            </tr>"
				+ "            <tr>"
				+ "                <td>선수 키</td>"
				+ "                <td><input type=\"number\" name=\"height\" value=\"" + p.getHeight() + "\"></td>"
				+ "            </tr>"
				+ "            <tr>"
				+ "                <td colspan=\"2\"><input type=\"submit\" value=\"수정\"></td>"
				+ "            </tr>"
				+ "        </table>"
				+ "    </form>"
				+ "</body>"
				+ "</html>";
		out.print(data);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int backNo = Integer.parseInt(request.getParameter("backNo"));
		String name = request.getParameter("name");
		String position = request.getParameter("position");
		String birthday = request.getParameter("birthday");
		int height = Integer.parseInt(request.getParameter("height"));
		
		Player p = new Player(backNo, name, position, birthday, height);
		PlayerDAO dao = new PlayerDAO();
		dao.upatePlayer(p);	
		
		response.sendRedirect("/jw/ch07/playerList");
	}

}
