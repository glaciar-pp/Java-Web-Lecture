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
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/ch07/customerList")
public class CustomerServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerDAO dao = new CustomerDAO();
		List<Customer> list= dao.getCustomers();
		
		//뷰어 부분은 나중에 JSP로 바꿀 수  있기 때문에 Controller 부분과 분리하게됨
		response.setCharacterEncoding("utf-8");		// 굳이 안해도 인코딩 오류 발생하지 않음
		response.setContentType("text/html; charset=utf-8");	// 반드시 세팅해주어야 함
		PrintWriter out = response.getWriter();
		out.print("<!DOCTYPE html>");
		out.print("<html lang=\"ko\">");
		out.print("<head>");
		out.print("	<meta charset=\"UTF-8\">");
		out.print("	<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
		out.print("	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		out.print("	<title>회원 리스트</title>");
		out.print("</head>");
		out.print("<body style=\"margin: 40px;\">");
		out.print("	<h1>회원 리스트</h1>");
		out.print("	<hr>");
		out.print("<table border=\"1\">");
		out.print("	    <tr>");
		out.print("	      <th>사용자ID</th><th>사용자명</th><th>가입일</th><th>액션</th>");
		out.print("	    </tr>"); 
		
		for (Customer c: list) {	
			out.print("	    <tr>");
			out.print("       <td>" + c.getUid() + "</td>");
			out.print("       <td>" + c.getUname() + "</td>");
			out.print("       <td>" + c.getRegDate().toString() + "</td>");
			out.print("       <td>" + "<a href=\"/jw/ch07/updateCustomer?uid="+ c.getUid() +"\">수정</a>&nbsp;&nbsp;" + 
							"<a href=\"/jw/ch07/deleteCustomer?uid="+ c.getUid() +"\">삭제</a>" + "</td>");
			//+ c.getUid() + -> 여기서 for loop 돌면서 적합한 아이디가 들어가게 만들어줌. 하나로 고정해서 적어두는건 NO!
			out.print("	    </tr>");
		}
		
		out.print("	</table>");
		out.print("	<br>");
		out.print("<a href=\"/jw/ch07/registerCustomer.html\">회원 가입</a>");
		out.print("</body>");
		out.print("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}


