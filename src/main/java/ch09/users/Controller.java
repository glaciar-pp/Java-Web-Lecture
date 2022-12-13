package ch09.users;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Servlet implementation class Controller
 */
@WebServlet(
		name = "UserController", 
		urlPatterns = { 
				"/ch09/users/list", 
				"/ch09/users/login", 
				"/ch09/users/logout", 
				"/ch09/users/register",
				"/ch09/users/update",
				"/ch09/users/delete"
		})
public class Controller extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String requestUri = request.getRequestURI();
		UserDAO dao = new UserDAO();
		HttpSession session = request.getSession();
		
		//목록
		if (requestUri.contains("list")) {
			List<User> list = dao.listUsers();
			RequestDispatcher rd = request.getRequestDispatcher("/ch09/users/listView");
			request.setAttribute("userList", list);
			rd.forward(request, response);
		
			//로그아웃
		} else if (requestUri.contains("logout")) {
			System.out.println(session.getAttribute("uid"));
			System.out.println(session.getAttribute("uname"));
			session.invalidate();
			response.sendRedirect("/jw/ch09/users/list");
			//세션 기능을 통해 본인의 값이 있다면 로그인 상태, null 이라면 로그아웃 상태인 것을 알수 있음
		
			//업데이트 (GET 부분) update가 null로 가는걸 아직 해결 못함
		}else if (requestUri.contains("update")) {
			String uid = request.getParameter("uid");
			User u = dao.getUserInfo(uid);
			request.setAttribute("user", u);
			RequestDispatcher rd = request.getRequestDispatcher("/ch09/users/updateView");
			rd.forward(request, response);			
			
			//삭제
		}else if (requestUri.contains("delete")) {
			String uid = request.getParameter("uid");
			dao.deleteUser(uid);
			response.sendRedirect("/jw/ch09/users/list");			
		}else {
			System.out.println("GET의 잘못된 경로 입니다");
		}
		
	}
	//어지러운  if문을 switch문으로 정리 가능함...다중 if문 쓸땐 Identation 맞추는게 정말 중요함

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String requestUri = request.getRequestURI();
		UserDAO dao = new UserDAO();
		HttpSession session = request.getSession();
		
		response.setContentType("text/html; charset = utf-8");
		PrintWriter out = response.getWriter();
		
		//회원 로그인
		if (requestUri.contains("login")) {
			String uid = request.getParameter("uid");
			String pwd = request.getParameter("pwd");
			User u = dao.getUserInfo(uid);
			if ( u.getUid() != null) {	//uid 가 존재
				if(BCrypt.checkpw(pwd, u.getPwd())) {
				//System.out.println(u.getUid() + "," + u.getUname());
				session.setAttribute("uid", u.getUid());
				session.setAttribute("uname", u.getUname());
				
				//Welcome message
				out.print("<script>");
				out.print("alert('"+ u.getUname() +"님 환영합니다." + "');");
				out.print("location.href='" + "/jw/ch09/users/list" + "';");
				out.print("</script>");
			}else {
				//재로그인 페이지
				out.print("<script>");
				out.print("alert('잘못된 패스워드 입니다. 다시 입력하세요.');");
				out.print("location.href='" + "/jw/ch09/users/login.html" + "';");
				out.print("</script>");
			}
			}else {				//uid 가 없음
				// 회원 가입 페이지로 안내
				out.print("<script>");
				out.print("alert('정보가 확인되지 않아 회원 가입 페이지로 이동합니다.');");
				out.print("location.href='" + "/jw/ch09/users/register.html" + "';");
				out.print("</script>");
			}
			
		//회원 가입
		} else if (requestUri.contains("register")) {
			String uid = request.getParameter("uid");
			String pwd = request.getParameter("pwd");
			String uname = request.getParameter("uname");
			String email = request.getParameter("email");
			
				//유효성 검증
				User u = dao.getUserInfo(uid);
				if (u.getUid() != null) {		//기존 사용자가 있는 경우
					out.print("<script>");
					out.print("alert('중복된 사용자가 존재합니다. 다른 UID를 입력해주세요.');");
					out.print("location.href='" + "/jw/ch09/users/register.html" + "';");
					out.print("</script>");
				} else {					//기존 사용자가 없는 경우
					out.print("<script>");
					out.print("alert('가입을 환영합니다.');");
					out.print("</script>");
					u = new User(uid, pwd, uname, email);
					dao.registerUser(u);
					response.sendRedirect("/jw/ch09/users/list");
				}
		//회원 정보 수정
		} else if(requestUri.contains("update")) {
			String uid = request.getParameter("uid");
			String pwd = request.getParameter("pwd");
			String uname = request.getParameter("uname");
			String email = request.getParameter("email");
						
			User u = new User(uid, pwd, uname, email);
			dao.updateUser(u);
			response.sendRedirect("/jw/ch09/users/list");
			}else {
				System.out.println("POST의 잘못된 경로 입니다");
			}
		}
	}	

