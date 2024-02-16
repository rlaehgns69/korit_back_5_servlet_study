package com.study.product.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/signup")
public class SignUpPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public SignUpPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("gd");
		ServletContext context = request.getServletContext();
		context.setAttribute("key4", "value1");
		context.setAttribute("key2", "value2");
		context.setAttribute("key3", "value3");
		// 전역
		request.setAttribute("key1", "value4");
		request.setAttribute("key5", "value5");
		request.setAttribute("key6", "value6");
		// 요청 -> 응답 될때까지
		HttpSession session =  request.getSession();
		
		session.setMaxInactiveInterval(1000 * 60);
		// 1초 * 요새 세션안쓰고 JWT
		
		session.setAttribute("key4", "value7");
		session.setAttribute("key8", "value8");
		session.setAttribute("key9", "value9");
		
		request.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(request, response);
		// jsp 경로 숨기기 가능
		// 서블릿이 jsp연결해준다.
	}
}
