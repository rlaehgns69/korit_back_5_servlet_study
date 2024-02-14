package com.study.servlet_study.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	// 요청 톰캣 request, response filter있는지 확인 요청주소확인 reponse /hello 헬로만 /*모든 요청 다운캐스팅해서 사용 Reponse
	// setCharacterEncoding("utf-8") HelloServlet...setCharacterEncoding("utf-8")
	// chain.dofilter 최종 필터로 어떤요청이 들어왔는지 서블릿중에서 get 찾는다. 주소가지고 (req, res)전달 다운캐스팅해서
	// 정보들을 활용 전처리 chain.dofilter로 돌아왔다. 이후에 무조건 실행
	// 응답 httpResponse.getWriter().println("무조건 후처리함.")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getMethod());
		System.out.println(request.getRequestURL());
		System.out.println(request.getRequestURI());
		System.out.println(response.getStatus());
		
		response.setContentType("text/plain");
//		response.setCharacterEncoding("utf-8");
		System.out.println(response.getContentType());
		response.getWriter().println("hello");
//		response.getWriter().println("헬로");
		
		System.out.println("요청이 들어옴!!!");
		
	}
}
