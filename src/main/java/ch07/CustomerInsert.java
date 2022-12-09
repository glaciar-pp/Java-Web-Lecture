package ch07;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerInsert
 */
@WebServlet("/ch07/registerCustomer")
public class CustomerInsert extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		String uname = request.getParameter("uname");
		
		CustomerDAO dao = new CustomerDAO();
		//유효성 검증
		Customer c = dao.getCustomer(uid);
		if (c.getUid() != null)		//기존 사용자가 있는 경우
			response.sendRedirect("/jw/ch07/registerCustomer.html");	//초기화면으로 돌려보냄
		else {										//기존 사용자가 없는 경우
			c = new Customer(uid, uname);
			dao.insertCustomer(c);
			
			response.sendRedirect("/jw/ch07/customerList");
		}
	}
}
