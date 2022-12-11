package ch07;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PlayerServlet
 */
@WebServlet("/ch07/playerList")
public class PlayerServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PlayerDAO dao = new PlayerDAO();
		List<Player> list = dao.getPlayers();
		
		response.setCharacterEncoding("utf-8");		// 굳이 안해도 인코딩 오류 발생하지 않음 
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<!DOCTYPE html>");
		out.print("<html lang=\"ko\">");
		out.print("<head>");
		out.print("	<meta charset=\"UTF-8\">");
		out.print("	<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
		out.print("	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		out.print("	<title>선수 명단</title>");
		out.print("<style>");
		out.print("td { text-align: center; padding: 3px;}");
		out.print("</style>");
		out.print("</head>");
		out.print("<body style=\"margin: 40px;\">");
		out.print("	<h1>선수 명단</h1>");
		out.print("	<hr>");
		out.print("<table border=\"1\">");
		out.print("	    <tr>");
		out.print("	      <th>Back Number</th><th>성명</th><th>포지션</th><th>생년월일</th><th>키(cm)</th><th>기능</th>");
		out.print("	    </tr>"); 
		
		for (Player p : list) {	
			out.print("	    <tr>");
			out.print("       <td>" + p.getBackNo() + "</td>");
			out.print("       <td>" + p.getName() + "</td>");
			out.print("       <td>" + p.getPosition() + "</td>");
			out.print("       <td>" + p.getBirthday() + "</td>");
			out.print("       <td>" + p.getHeight() + "</td>");
			
			out.print("       <td>" + "<a href=\"/jw/ch07/updatePlayerInfo?backNo="+ p.getBackNo() +"\">수정</a>&nbsp;&nbsp;" + 
							"<a href=\"/jw/ch07/releasePlayer?backNo="+ p.getBackNo() +"\">방출</a>" + "</td>");
			//+ c.getUid() + -> 여기서 for loop 돌면서 적합한 아이디가 들어가게 만들어줌. 하나로 고정해서 적어두는건 NO!
			out.print("	    </tr>");
		}
		
		out.print("	</table>");
		out.print("	<br>");
		out.print("<a href=\"/jw/ch07/registerPlayer.html\">신규 선수 가입</a>");
		out.print("</body>");
		out.print("</html>");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
