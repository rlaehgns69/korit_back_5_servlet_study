package com.study.product.servlet;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.product.dto.InsertProductReqDto;
import com.study.product.service.ProductService;
import com.study.product.utils.RequestUtil;
import com.study.product.utils.ResponseEntity;


@WebServlet("/product")
public class InsertProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductService productService; 
	
    public InsertProductServlet() {
        super();
        productService = ProductService.getInstance();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String requestJsonData = RequestUtil.getJsonData(request);
//		Gson gson = new Gson();
		
//		InsertProductReqDto reqDto = gson.fromJson(requestJsonData, InsertProductReqDto.class); 
//		// InsertProductReqDto변환하면 JSON을 객체로 변환하는 작업과정 객체로 묶을수 있지않을까?
		InsertProductReqDto reqDto = RequestUtil.convertJsonData(request, InsertProductReqDto.class);
		
		if(productService.isDuplicatedProductName(reqDto.getProductName())) {
			//응답을 Map
			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("errorMessage", "이미 등록된 상품명");
			
			ResponseEntity.ofJson(response, 400, responseMap);
			return;
			//이번에는 반대로 리턴타입이 void 보내준 데이터타입이 Map<String, Object> responseMap
		}
		ResponseEntity.ofJson(response, 201, productService.addProduct(reqDto));
		//Gson gson = new Gson();
		//response.setStatus(status);
		//response.setContentType("application/json");
		//response.getWriter().println(gson.toJson(body)); // void response.getWriter 한줄로
		
		// 앞에있는 자료형 따라감. -> <T> InsertProductReqDto 요청이 들어왔을 때 dto로 변환된다.
		// System.out.println(reqDto);
		//InsertProductReqDto dto = test();//Object
		// V InsertProductReqDto a=InsertProductReqDto
		
		// 서블릿 함수로 뺌. 매개변수만 이해하면 됨.InsertProductReqDto타입 InsertProductReqDto클래스 변환
		// 제네릭부분이 InsertProductReqDto로변한다. 로 보냈기 때문에
//		// doGet(request, response); 405 doPost로 들어왔는데 doGet으로요청
//				// JSON받을 수 있도록
//				BufferedReader reader = request.getReader();
//				//BufferedReader -request.getReader();
//				
//				String requestJsonData = null;
//				StringBuilder builder = new StringBuilder();
//				// JSON을 문자열로 변환 builder(json)
//				String readLineData = null;
//				
//				
//				
//				while((readLineData = reader.readLine()) != null) {
//					builder.append(readLineData);
//				}
//				
//				requestJsonData = builder.toString();
//				// JSON변환
		
	}
	public <T> T test() {
		
		// String a = null;
		// String 말고 제네릭
		T a= null;
		
		return a;
		// a가 T타입 리턴이 T타입
		// 대상만 바꿔주면 T값이 설정된다.
	}
}
