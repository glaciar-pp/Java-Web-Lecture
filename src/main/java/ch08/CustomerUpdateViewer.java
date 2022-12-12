package ch08;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerUpdateViewer
 */
@WebServlet("/ch08/updateView")
public class CustomerUpdateViewer extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//controller에서 보낸 request를 여기서 바로 받아서 같은 정보를 받게된다!
		request.setCharacterEncoding("utf-8");
		Customer c = (Customer)request.getAttribute("customer");
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
				+ "    <title>회원 수정</title>"
				+ "    <style>"
				+ "        td { text-align: center; padding: 3px;}"
				+ "    </style>"
				+ "</head>"
				+ "<body style=\"margin: 40px;\">"
				+ "    <h1>회원 수정</h1>"
				+ "    <hr>"
				+ "    <form action=\"/jw/ch07/updateCustomer\" method=\"post\">";
		data +=  "<input type=\"hidden\" name=\"uid\" value=\"" + c.getUid() + "\">";
		data +=  "        <table>"
				+ "            <tr>"
				+ "                <td>사용자 ID</td>";
		data += "<td><input type=\"text\" name=\"uid\"  value=\"" + c.getUid() + "\" disabled></td>";
		data += "            </tr>"
				+ "            <tr>"
				+ "                <td>사용자명</td>";
		data +=	"<td><input type=\"text\" name=\"uname\"  value=\"" + c.getUname() + "\"></td>";
		data +=	"            </tr>"
				+ "            <tr>"
				+ "                <td>가입일</td>";
		data += "<td><input type=\"text\" name=\"regDate\" value=\"" + c.getRegDate() + "\" disabled></td>";
		data += "            </tr>"
				+ "             <tr>"
				+ "                <td colspan=\"2\"><input type=\"submit\" value=\"수정\"></td>"
				+ "            </tr>"				
				+ "        </table>"
				+ "    </form>"
				+ "</body>"
				+ "</html>";
		out.print(data);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
