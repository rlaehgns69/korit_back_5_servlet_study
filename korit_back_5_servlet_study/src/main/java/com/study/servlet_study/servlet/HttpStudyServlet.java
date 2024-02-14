package com.study.servlet_study.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.servlet_study.utils.ParamsConverter;


@WebServlet("/http")
public class HttpStudyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HttpStudyServlet() {
        super();
        
    }
    
    // HTTP 메소드
    // POST요청    -> C reate(추가) - 로그인 조회 body x-www-form
    // GET요청     -> R ead(조회) params
    // PUT요청     -> U pdate(수정)
    // DELETE요청  -> D elete(삭제)
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Map<String, String[]> map = request.getParameterMap();
//    	request.setCharacterEncoding("utf-8"); 필터 처리후 삭제
    	// 요청때 encoding
		Map<String, String> paramsMap = new HashMap<>();
		
		// map 키값 상관없음 (자체로 맵)
		request.getParameterMap().forEach((k,v)->{
//			String[] valueArray = v;
			StringBuilder builder = new StringBuilder();
			// 잘려있는 문자열 합칠 때 비어있는 공간(String 합칠 수 있는 공간)
			Arrays.asList(v).forEach(value -> builder.append(value));
			// 모든 배열을 리스트로 리스트.forEach(배열 반복돌려도됨.)
//			System.out.println(k + ": "+ builder.toString()); // builder 객체 최종적으로 toString
			paramsMap.put(k, builder.toString());
			
//			for(String value : v) {
//				System.out.print(value);
//			}
//			System.out.println();
		});
		System.out.println(paramsMap);
		// 요청때 들어오는 이름들을 출력하고 싶다. enumeration iterator객체로 변환
		// iterator list arrayList array set 다 있음. 무한루프 false가 될때까지
		//
		Map<String, String> paramsMap2 = new HashMap<>();//1번
		Iterator<String> ir = request.getParameterNames().asIterator();
		while(ir.hasNext()) {
			String key = ir.next();
			paramsMap2.put(key, request.getParameter(key)); // value
			//System.out.println(ir.next()); 방법 2가지
		}// name, phone
		// iterator 반복
	//	System.out.println(request.getParameter("name")); 키값 알아야 됨. 반복을 돌려서 키값넣기
	//	System.out.println(request.getParameterNames()); // 네임 키값 모음 parameterValues랑 matching
		
		// 배열 name [0, 1, 9, 1, 9 ,8, 8] S S[] -> forEach String String forEach 하나하나씩 append
		// [0|1|0||] toString 합쳐짐. forEach끝 builder가 다쌓였음.
		// paramsMap String toString 결과: {phone=02224515, name=e e1}
		
		// 하나씩 꺼냄. key값 알아야
//    	String nameParams = request.getParameter("name");
//		String phoneParams = request.getParameter("phone");
//		String emailParams = request.getParameter("email");
//		String addressParams = request.getParameter("address"); // 조건들 보냄(딸기, 얼음)-> C -> S ->DB
//	
//		System.out.println(nameParams);
//		System.out.println(phoneParams);
//		System.out.println(emailParams);
//		System.out.println(addressParams);
    }
    // POST 데이터 숨김.
	// get요청 params - 주소창에 데이터추가
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> paramsMap = ParamsConverter.convertParamsMapToMap(request.getParameterMap());

//				new HashMap<>();
//		
//		request.getParameterMap().forEach((k,v) -> {
//		StringBuilder builder = new StringBuilder();
//		
//		Arrays.asList(v).forEach(value -> builder.append(value));
//		
//		paramsMap.put(k, builder.toString());
//	});
	}
	
	
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
