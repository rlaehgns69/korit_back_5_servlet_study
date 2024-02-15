package com.study.product.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class ResponseEntity {

	public static <T> void ofJson(HttpServletResponse response, int status, T body) throws IOException {
		Gson gson = new Gson();
		response.setStatus(status);
		response.setContentType("application/json");
		response.getWriter().println(gson.toJson(body)); // void response.getWriter
		// <T, R> R
		//1. 리턴타입이 <T> 대입하는 대상 
		//2. 매개변수가 Map -> 타입은 Map String, Dto ->이 타입이됨.
	}
	
}
