package com.study.insert_and_select.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import com.study.insert_and_select.dao.StudentDao;
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
//		System.out.println(map.get("name"));
//		System.out.println(map.get("age"));
		
		// 2. Json -> Entity객체
		
		Student student = gson.fromJson(builder.toString(), Student.class);
		System.out.println(student);
		System.out.println(student.getName());
		System.out.println(student.getAge());
		
		
		
//		Connection con = null; 			// 데이터베이스 연결
//		String sql = null; 	   			// SQl 쿼리문 작성
//		PreparedStatement pstmt = null;	// SQL 쿼리문 입력
//		int successCount = 0;			// SQL insert, update delete 실행 결과(성공횟수)
//		ResultSet rs = null;
//		Student findStudent = null;
//		
//		String url = "jdbc:mysql://mysql-db.cp06k8cc266u.ap-northeast-2.rds.amazonaws.com/db_study";
//		String username = "aws";
//		String password = "1q2w3e4!!";
		
//		try {
//			
//			Class.forName("com.mysql.cj.jdbc.Driver");// 데이터베이스 커넥터 드라이브 클래스 이름
//			
//			con = DriverManager.getConnection(url, username, password);
//			sql = "select * from student_tb where student_name = ?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, student.getName()); //json의 
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {//조회 한줄 존재
//				findStudent = Student.builder()
//						.name(rs.getString(2))
//						.age(rs.getInt(3))
//						.build();
//			}// rs = null;
//			
//		} catch(Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(rs != null) {
//					rs.close();
//				}
//				if(pstmt != null) {
//					pstmt.close();
//				}
//				if(con != null) {
//					con.close();
//				}
//				
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
		
		StudentDao studentDao = StudentDao.getInstance();
		
		Student findStudent = studentDao.findStudentByName(student.getName());
		
		if(findStudent != null) {
			Map<String, Object> errorMap = new HashMap<>();
			errorMap.put("errorMessage", "이미 등록된 이름입니다.");
			
			response.setStatus(400);
			response.setContentType("application/json");
			response.getWriter().println(gson.toJson(errorMap));
			return;
		}
		
		int successCount = studentDao.saveStudent(student);
		
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("status", 201);
		responseMap.put("data", "응답데이터");
		responseMap.put("successCount", successCount);
		
		response.setStatus(201);
		response.setContentType("application/json");
//		response.setStatus(400);
		
		PrintWriter writer = response.getWriter();
		writer.println(gson.toJson(responseMap));
	}
}
		
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");// 데이터베이스 커넥터 드라이브 클래스 이름
//			
//			
//			con = DriverManager.getConnection(url, username, password);
//			sql = "insert into student_tb(student_name, student_age) values(?, ?)";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, student.getName());
//			pstmt.setInt(2, student.getAge());
//			successCount = pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(pstmt != null) {
//					pstmt.close();
//				}
//				if(con != null) {
//					con.close();
//				}
//				
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
		

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


