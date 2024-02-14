package com.study.servlet_study.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.servlet_study.entity.Product1;
import com.study.servlet_study.service.ProductService1;

@WebServlet("/product1")
public class ProductServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService1 productService;
       
    public ProductServlet1() {
        super();
        productService = ProductService1.getInstance();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productName = request.getParameter("productName");
		Product1 product = productService.getProduct(productName);
		response.setStatus(200);
		response.getWriter().println(product);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productName = request.getParameter("productName");
		int price = Integer.parseInt(request.getParameter("price"));
		String size = request.getParameter("size");
		String color = request.getParameter("color");
		
		Product1 product = Product1.builder()
					.productName(productName)
					.price(price)
					.size(size)
					.color(color)
					.build();
		
		int body = productService.addProduct(product);
		response.setStatus(200);
		response.getWriter().println(body);
		
	}

}
