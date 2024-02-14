package com.study.array.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class EncodingFilter extends HttpFilter implements Filter {
       

	public void destroy() {
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setHeader("Access-control-Allow-Headers", "Content-Type");
		httpServletResponse.setHeader("Access-Control-Allow-Origin","*");

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		chain.doFilter(request, response); //getWriter println 이전 필터->servlet->필터
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
