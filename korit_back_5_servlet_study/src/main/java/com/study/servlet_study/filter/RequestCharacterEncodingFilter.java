package com.study.servlet_study.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName = "RequestCharacterEncodingFilter", urlPatterns = { "/*" })
public class RequestCharacterEncodingFilter extends HttpFilter implements Filter {
 
    public RequestCharacterEncodingFilter() {
        super();
       
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		
		String[] methods = new String[] {"POST","PUT"};
		
		// System.out.println(httpRequest.getMethod()); POST(대문자)
		// contains if else if equalsIgnoreCase contains같은 경우 일치
		if(Arrays.asList(methods).contains(httpRequest.getMethod().toUpperCase())) {
			//		리스트	포함하고있니(포스트) 요청때 들어온 메소드
			httpRequest.setCharacterEncoding("utf-8");
		}
		
		//httpRequest.setCharacterEncoding("utf-8"); get delete x
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
