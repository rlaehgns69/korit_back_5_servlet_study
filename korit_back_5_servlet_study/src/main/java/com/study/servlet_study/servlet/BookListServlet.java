package com.study.servlet_study.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.servlet_study.service.BookService;

@WebServlet("/books")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookService bookService;
       
    public BookListServlet() {
        super();
        bookService = BookService.getInstance();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// params
		// bookName -> like 조회
		// authorName -> like 조회
		// publisherName -> like 조회
		// 3가지 입력 파라미터 bookName이 있으면 where에 있어야 된다. bookName과 authorName 파라미터가 있을 경우 and
		String bookName = request.getParameter("bookName");
		String authorName = request.getParameter("authorName");
		String publisherName = request.getParameter("publisherName");
		
		Map<String, String> params = new HashMap<>();
		if(bookName != null) {
			params.put("bookName", bookName);
		}
		if(authorName != null) {
			params.put("authorName", authorName);
		}
		if(publisherName != null) {
			params.put("publisherName", publisherName);
		}
		
		params.size();
	}

}
