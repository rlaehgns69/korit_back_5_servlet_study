package com.study.insert_and_select.filter;

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
public class CommonFilter extends HttpFilter implements Filter {
       
 
    public CommonFilter() {
        super();
     
    }

	
	public void destroy() {
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		httpServletResponse.setHeader("Access-control-Allow-Headers", "Content-Type");
		httpServletResponse.setHeader("Access-Control-Allow-Origin","*");

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// httpServletResponse.setCharacterEncoding()
		
		chain.doFilter(request, response);
		//c -> f ()()s
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
