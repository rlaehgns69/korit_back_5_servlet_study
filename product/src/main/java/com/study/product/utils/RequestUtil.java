package com.study.product.utils;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.study.product.dto.InsertProductReqDto;

public class RequestUtil {
	
	public static String getJsonData(HttpServletRequest request) throws IOException {
				String requestJsonData = null;
				StringBuilder builder = new StringBuilder();

				BufferedReader reader = request.getReader();
				
				
				
				String readLineData = null;
				
				
				
				while((readLineData = reader.readLine()) != null) {
					builder.append(readLineData);
				}
				
				requestJsonData = builder.toString();
				
				return requestJsonData;
				
	}// json문자열
	
	// T -Class<T> InsertProductReqDto리턴 Class<InsertProductReqDto>
	// 여기서만 제네릭 사용하겠다.
	// 호출되고 리턴될때 대상에 따라 타입이 결정된다. <T> T
	public static <T> T convertJsonData(HttpServletRequest request, Class<T> classOfT) throws IOException {
		String requestJsonData = null;
		StringBuilder builder = new StringBuilder();

		BufferedReader reader = request.getReader();
		
		
		
		String readLineData = null;
		
		
		
		while((readLineData = reader.readLine()) != null) {
			builder.append(readLineData);
		}
		
		requestJsonData = builder.toString();
		
		
		Gson gson = new Gson();
		// classOfT fromJson 매개변수 쓰려고
		
		return gson.fromJson(requestJsonData, classOfT);
		// gson JSON 변환하는 과정을 합쳐놓음. 객체 변환까지 함.
	}//객체까지
}
