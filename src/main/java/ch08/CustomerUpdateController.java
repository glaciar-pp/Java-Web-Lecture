package ch08;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerUpdateController
 */
@WebServlet("/ch08/updateCustomer")
public class CustomerUpdateController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자로부터 입력받음
		request.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		
		//DB에서 필요한 정보를 가져옴
		CustomerDAO dao = new CustomerDAO();
		Customer c = dao.getCustomer(uid);
		
		//Viewer 호출 
		request.setAttribute("customer", c);
		RequestDispatcher rd = request.getRequestDispatcher("/ch08/updateView");
		rd.forward(request, response); //실제 이동은 forward 에서 진행! 땅 하고 출발하는 느낌, 뷰어 호출!
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//POST 부분은 ch07에서와 동일함
		request.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		String uname = request.getParameter("uname");
		
		Customer c = new Customer(uid, uname);	
		CustomerDAO dao = new CustomerDAO();
		dao.updateCustomer(c);
		
		response.sendRedirect("/jw/ch08/customer");
	}

}
