package ch08;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DestRedirect
 */
@WebServlet("/ch08/dst1")
public class DestRedirect extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String msg = request.getParameter("msg");
		// URLDecoder.decode(msg, utf-8); // 한글 관련
		
		//request 저장소를 통한 redirect는 데이터를 전송할 수 없다.
		//Client를 갔다가 온 request이므로 src1에서 보낸  request와 다르다.
		String addr = (String)request.getAttribute("addr"); //타입 잘못 주어 에러남, 이 상태만으론 null이 나옴
		
		HttpSession session = request.getSession();
		String sessAddr = (String)session.getAttribute("addr");
		
		
		//Application 저장소를 통해 데이터 전달, 바람직하지 않으나 예시를 위해 진행
		ServletContext ctx = getServletContext();
		String ctxAddr = (String)ctx.getAttribute("addr");
		
		//결과보기
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<h1>sendRedirect를 이용한 화면 이동</h1>");
		out.print("<h1>" + msg + "</h1>");
		out.print("<h1>Request: " + addr + "</h1>");
		out.print("<h1>Session: " + sessAddr + "</h1>");
		out.print("<h1>Application: " + ctxAddr + "</h1>");
		/*우리가 크롬이 기본이라 엣지에서 틀면 request.~는 src->dst 방식으로 데이터를 받기 때문에 
		 * 해당 방식을 이용하면 결과가 나오고 바로 dst를 확인하면 안나옴
		 * 쎄션은 브라우저를 기준으로 하다보니 안나옴
		 * Application은 종료전까지 유지되므로 나옴
		 * 그리고 브라우저가 사라지는 기준은 탭이 아니고 구동되는 브라우저 자체가 
		 * 꺼지고 작업중 요소에서 사라져야 사라지는 것 */
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
