package ch10;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class EncodingFilter
 */
//@WebFilter("/ch10/*")
public class EncodingFilter extends HttpFilter implements Filter {
	ServletContext context;

    public EncodingFilter() {
        super();
        System.out.println("EncodingFilter 생성자");
    }


	public void destroy() {
		System.out.println("EncodingFilter destroy()");
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		String path = ((HttpServletRequest)request).getContextPath();
		String pathInfo = ((HttpServletRequest)request).getRequestURI();
		String realPath = request.getRealPath(pathInfo);
		String remoteAddr = request.getRemoteAddr();
		String data = "context정보: " + context
						+ "\nURI 정보:" + pathInfo
						+ "\n물리적 경로:" + realPath
						+ "\n접속 정보: " + remoteAddr;
		System.out.println(data);
		long begin = System.currentTimeMillis();
		
		chain.doFilter(request, response);
		
		long end = System.currentTimeMillis();
		System.out.println("소요시간: " + (end - begin) + "ms");
		
		//System.out.println("EncodingFilter after chain.doFilter");
	}

	public void init(FilterConfig fConfig) throws ServletException {
		context = fConfig.getServletContext();
		//System.out.println("EncodingFilter init()");
	}

}
