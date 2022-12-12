package ch08.player;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateView
 */
@WebServlet("/ch08/player/updateView")
public class PupdateViewer extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Player p = (Player)request.getAttribute("player");
		//윗 두줄은 데이터베이스에서 가져오는게 아니라 controller통해 불러준 데이터를 쓰겠다는 뜻
		
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
				+ "		   #LGC1 { background-color: rgb(196, 4, 82); color: white;}"
				+ "    </style>"
				+ "</head>"
				+ "<body style=\"margin: 40px;\">"
				+ "    <h1 id=\"LGC1\">선수 정보 수정</h1>"
				+ "    <hr>"
				+ "    <form action=\"/jw/ch08/player/update\" method=\"post\">"
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
				+ "                <td><input type=\"date\" name=\"birthday\" value=\"" + p.getBirthday() + "\"></td>"
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

}
