package ch08;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InitParamServlet
 */
@WebServlet(
		asyncSupported = true, 
		urlPatterns = { 
				"/ch08/init", 
				"/ch08/init2"
		}, 
		initParams = { 
				@WebInitParam(name = "email", value = "admin@web.com", description = "관리자 이메일 주소"), 
				@WebInitParam(name = "tel", value = "010-2345-6789", description = "관리자 전화번호")
		})
public class InitParamServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = getInitParameter("email");
		String tel = getInitParameter("tel");
		String servletPath = request.getServletPath();
		System.out.println(email);
		System.out.println(tel);
		System.out.println(servletPath);
		
		//path 구분을 통해 조건문을 걸어 가를 수 있음.!
		if (servletPath.indexOf("init2") >= 0)
			System.out.println("init2에서 처리해 주어야 하는 일");
		else
			System.out.println("init에서 처리해 주어야 하는 일");
			
	}

}
