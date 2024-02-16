package com.study.product.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.product.dto.InsertUserReqDto;
import com.study.product.utils.RequestUtil;
import com.study.product.utils.ResponseEntity;

@WebServlet("/user")
public class InsertUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public InsertUserServlet() {
        super();
       
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InsertUserReqDto dto = RequestUtil.convertJsonData(request, InsertUserReqDto.class);
		System.out.println(dto);
		ResponseEntity.ofJson(response, 200, dto);
		// 데이터를 가지고와야만 서블릿으로 날릴 수 있다.
	}
}
