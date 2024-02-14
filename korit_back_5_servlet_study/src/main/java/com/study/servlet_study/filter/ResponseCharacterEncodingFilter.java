package com.study.servlet_study.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter("/*")
public class ResponseCharacterEncodingFilter extends HttpFilter implements Filter {

	public void destroy() {
		
	}

	// Http 안붙은거 다운캐스팅해서 사용
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request; // 업캐스팅 멤버개수 제한
		HttpServletResponse httpResponse = (HttpServletResponse) response; // 주소같다.
		
		httpResponse.setCharacterEncoding("utf-8");// doGet 전처리
		// 전처리
		chain.doFilter(request, response); // 최종필터 -> Servlet doGet
		// 후처리 과정
//		httpResponse.getWriter().println("무조건 후처리함.");
	
	}


	public void init(FilterConfig fConfig) throws ServletException {
	
	}

}
