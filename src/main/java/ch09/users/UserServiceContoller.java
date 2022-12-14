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
 * Servlet implementation class UserServiceContoller
 */
@WebServlet({ "/ch09/users/list", "/ch09/users/login", "/ch09/users/logout", 
			 "/ch09/users/register", "/ch09/users/update", "/ch09/users/delete" })

public class UserServiceContoller extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String[] uri = request.getRequestURI().split("/");
		String action = uri[uri.length - 1];
		UserDAO dao = new UserDAO();
		HttpSession session = request.getSession();
		
		response.setContentType("text/html; charset = utf-8");
		PrintWriter out = response.getWriter();
		String uid = null, pwd=null, pwd2 = null, uname = null, email = null;
		User u = null;
		RequestDispatcher rd = null;
		//회원 로그인
		switch(action) {
		case "list":
			List<User> list = dao.listUsers();
			request.setAttribute("userList", list);
			rd = request.getRequestDispatcher("/ch09/users/listView");
			rd.forward(request, response);
			break;
		case "login":
			uid = request.getParameter("uid");
			pwd = request.getParameter("pwd");
			u = dao.getUserInfo(uid);
				if ( u.getUid() != null) {	//uid 가 존재
					if(BCrypt.checkpw(pwd, u.getPwd())) {
					//System.out.println(u.getUid() + "," + u.getUname());
					session.setAttribute("uid", u.getUid());
					session.setAttribute("uname", u.getUname());
					
					// Welcome message
//					out.print("<script>");
//					out.print("alert('" + u.getUname() + "님 환영합니다." + "');");
//					out.print("location.href = '" + "/jw/ch09/users/list" + "';");
//					out.print("</script>");
					request.setAttribute("msg", u.getUname() + "님 환영합니다.");
					request.setAttribute("url", "/jw/ch09/users/list");
					rd = request.getRequestDispatcher("/ch09/users/alertMsg.jsp");
					rd.forward(request, response);
				}else {
					// 재 로그인 페이지
//					out.print("<script>");
//					out.print("alert('잘못된 패스워드 입니다. 다시 입력하세요.');");
//					out.print("location.href = '" + "/jw/ch09/users/login.html" + "';");
//					out.print("</script>");
					request.setAttribute("msg", "잘못된 패스워드 입니다. 다시 입력하세요.");
					request.setAttribute("url", "/jw/ch09/users/login.html");
					rd = request.getRequestDispatcher("/ch09/users/alertMsg.jsp");
					rd.forward(request, response);
				}
			}else {				//uid 가 없음
				// 회원 가입 페이지로 안내
//				out.print("<script>");
//				out.print("alert('회원 가입 페이지로 이동합니다.');");
//				out.print("location.href = '" + "/jw/ch09/users/register.html" + "';");
//				out.print("</script>");
				request.setAttribute("msg", "회원 가입 페이지로 이동합니다.");
				request.setAttribute("url", "/jw/ch09/users/register.html");
				rd = request.getRequestDispatcher("/ch09/users/alertMsg.jsp");
				rd.forward(request, response);
			}
			break;
		case "logout":
			//System.out.println(session.getAttribute("uid"));
			//System.out.println(session.getAttribute("uname"));
			session.invalidate();
			response.sendRedirect("/jw/ch09/users/list");
			break;
		//회원 가입
		case "register":
			uid = request.getParameter("uid");
			pwd = request.getParameter("pwd");
			pwd2 = request.getParameter("pwd2"); //패스워드 검증용
			uname = request.getParameter("uname");
			email = request.getParameter("email");			
				
			if (pwd.equals(pwd2)) {
				u = new User(uid, pwd, uname, email);
				dao.registerUser(u);
				response.sendRedirect("/jw/ch09/users/list");
			} else {
				//out.print("<script>");
				//out.print("alert('패스워드 입력이 잘못되었습니다.');");
				//out.print("location.href = '" + "/jw/ch09/users/register.html" + "';");
				//out.print("</script>");
				request.setAttribute("msg", "패스워드 입력이 잘못되었습니다.");
				request.setAttribute("url", "/jw/ch09/users/register.html");
				rd = request.getRequestDispatcher("/ch09/users/alertMsg.jsp");
				rd.forward(request, response);
				
			}
			//uid 유효성 검증 - 추가해볼 예정				
			/* u = dao.getUserInfo(uid);
			 * if (u.getUid() != null) { //기존 사용자가 있는 경우 out.print("<script>");
			 * out.print("alert('중복된 사용자가 존재합니다. 다른 UID를 입력해주세요.');");
			 * out.print("location.href='" + "/jw/ch09/users/register.html" + "';");
			 * out.print("</script>"); } else { //기존 사용자가 없는 경우 out.print("<script>");
			 * out.print("alert('가입을 환영합니다.');"); out.print("</script>"); u = new User(uid,
			 * pwd, uname, email); dao.registerUser(u);
			 * response.sendRedirect("/jw/ch09/users/list"); }
			 */
			break;
		//회원 정보 수정
		case "update":
			if (request.getMethod().equals("GET")) {
				uid = request.getParameter("uid");
				 u = dao.getUserInfo(uid);
				request.setAttribute("user", u);
				rd = request.getRequestDispatcher("/ch09/users/updateView");
				rd.forward(request, response);	
			}else {
				uid = request.getParameter("uid");
				uname = request.getParameter("uname");
				email = request.getParameter("email");
				//String pwd = request.getParameter("pwd"); 수정에도 pwd 넣으면 로직이 복잡해져서 생략.!
							
				u = new User(uid, uname, email);
				dao.updateUser(u);
				response.sendRedirect("/jw/ch09/users/list");
			}
			break;
		case "delete":
			uid = request.getParameter("uid");
			dao.deleteUser(uid);
			response.sendRedirect("/jw/ch09/users/list");	
			break;
		default:
				System.out.println(request.getMethod() + "잘못된 경로 입니다");
			}
		}
	}

	/*
	 * 현재는 외관 형식이 html 인데 이는 주소만 알면 접속이 가능하니 보안이 취약하다는 문제가 있음
	 * WEB-INF 에 root 폴더 만들어서 넣을 예정
	 * 
	 */