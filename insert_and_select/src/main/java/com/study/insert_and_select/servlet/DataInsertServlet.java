package com.study.insert_and_select.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.study.insert_and_select.entity.Student;

@WebServlet("/data/addition")
public class DataInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public DataInsertServlet() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder builder = new StringBuilder(); // 문자열을 합쳐주는 용도
		
		String readData = null;
		
		BufferedReader reader = request.getReader();
		
		/*
		 * " {							1
		 * 		"name" : "김준일",		2
		 * 		"age" :31				3
		 * 	 }"							4
		 * 						json 
		 */ 
		
		// readData 1번째 줄 2번째 줄 추가 추가 ...
		while((readData = reader.readLine()) != null) {
			builder.append(readData);
		}
		
		// 1.Json -> Map
		// Json -> Entity객체
		Gson gson = new Gson();
		Map<String, Object> map = gson.fromJson(builder.toString(), Map.class);
		
		System.out.println(map);
		System.out.println(map.get("name"));
		System.out.println(map.get("age"));
		
		// 2. Json -> Entity객체
		
		Student student = gson.fromJson(builder.toString(), Student.class);
		System.out.println(student);
		System.out.println(student.getName());
		System.out.println(student.getAge());
		
		Connection con = null; 			// 데이터베이스 연결
		String sql = null; 	   			// SQl 쿼리문 작성
		PreparedStatement pstmt = null;	// SQL 쿼리문 입력
		int successCount = 0;			// SQL insert, update delete 실행 결과(성공횟수)
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// 데이터베이스 커넥터 드라이브 클래스 이름
			
			String url = "jdbc:mysql://mysql-db.cp06k8cc266u.ap-northeast-2.rds.amazonaws.com/db_study";
			String username = "aws";
			String password = "1q2w3e4!!";
			
			con = DriverManager.getConnection(url, username, password);
			sql = "insert into student_tb(student_name, student_age) values(?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student.getName());
			pstmt.setInt(2, student.getAge());
			successCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("status", 201);
		responseMap.put("data", "응답데이터");
		responseMap.put("successCount", successCount);
		
		response.setContentType("application/json");
		
		PrintWriter writer = response.getWriter();
		writer.println(gson.toJson(responseMap));
		
//		catch(ClassNotFoundException e){
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}		
		
		
		
		
//		System.out.println(builder.toString());
//		
//		Student student = Student.builder()
//						.name("김도훈")
//						.age(27)
//						.build();
//		
//		// new Gson 보기 힘듬 setPrettyPrinting.create json 형태
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		
//		String studentJson = gson.toJson(student);
//		
//		System.out.println(studentJson);
	}

}
