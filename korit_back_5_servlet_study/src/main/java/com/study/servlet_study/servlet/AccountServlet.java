package com.study.servlet_study.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.servlet_study.entity.Account;
import com.study.servlet_study.service.AccountService;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AccountService accountService;   
	
	
    public AccountServlet() {
        super();
        accountService = AccountService.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		// username 서비스-레포지토리까지 가야돼
		// 껐다 키면 서버 재시작 null DB에 연결해야됨.
		Account account = accountService.getAccount(username);
		response.setStatus(200); // OK
		response.getWriter().println(account);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		// Presentaion영역 -> Service영역 Repository영역
		Account account = Account.builder()
				.username(username)
				.password(password)
				.name(name)
				.email(email)
				.build();
		
		int body = accountService.addAccount(account);
		response.setStatus(201);
		response.getWriter().println(body);
	}

}
