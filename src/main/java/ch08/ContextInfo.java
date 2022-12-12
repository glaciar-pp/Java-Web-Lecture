package ch08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContextInfo
 */
@WebServlet("/ch08/info")
public class ContextInfo extends HttpServlet {
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// context와 ctx동일, 같은 것을 상속받기 때문에!
		ServletContext context = request.getServletContext();
		ServletContext ctx = getServletContext();
		
		//정보 확인해보기
		System.out.println("MajorVersion: " + ctx.getMajorVersion());
		System.out.println("RealPath: " + ctx.getRealPath("/ch08/info"));
		System.out.println("ServerInfo: " + ctx.getServerInfo());
		System.out.println("ServletContextName: " + ctx.getServletContextName());
		/* 콘솔에 나온 정보
		 * MajorVersion: 4 
		 * RealPath: C:\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JavaWeb\ch08\info 
		 * ServerInfo: Apache Tomcat/9.0.70 
		 * ServletContextName: JavaWeb
		 */
				
		//web.xml에 등록해두었던 초기 설정값 부름
		String menu = ctx.getInitParameter("menu");
		System.out.println("web.xml데이터(menu): " + menu); //콘솔에 menu: 짬뽕 짜장면 탕수육 으로 출력됨
		
		//WEB-INF/temp/test.txt 에서 데이터 읽기
		InputStream is = ctx.getResourceAsStream("WEB-INF/temp/test.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String data = ""; //책이랑 다른 방식 보여주심!
		while(true) {
			String temp = br.readLine();
			if (temp ==null)
				break;
			data += temp;
		}
		System.out.println("file data: " + data);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
