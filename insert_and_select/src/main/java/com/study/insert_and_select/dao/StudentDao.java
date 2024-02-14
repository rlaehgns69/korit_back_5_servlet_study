package com.study.insert_and_select.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.study.insert_and_select.entity.Student;

import config.DBConfig;

public class StudentDao {
	private static StudentDao instance;
	
	private StudentDao() {}
	
	public static StudentDao getInstance() {
		if(instance == null) {
			instance = new StudentDao();
		}
		return instance;
	}
	
//	private String url = "jdbc:mysql://mysql-db.cp06k8cc266u.ap-northeast-2.rds.amazonaws.com/db_study";
//	private String username = "aws";
//	private String password = "1q2w3e4!!";
	
	// 이름가지고 student객체 찾는 거
	public Student findStudentByName(String name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Student student = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");// 데이터베이스 커넥터 드라이브 클래스 이름
			
			con = DriverManager.getConnection(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);
			String sql = "select * from student_tb where student_name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name); //json의 
			rs = pstmt.executeQuery();
			
			if(rs.next()) {//조회 한줄 존재
				student = Student.builder()
						.studentId(rs.getInt(1))
						.name(rs.getString(2))
						.age(rs.getInt(3))
						.build();
			}// rs = null;
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		try {
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
		
		return student;
	}// select ResultSet 매개변수 name 객체 리턴
	
	public int saveStudent(Student student) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int successCount = 0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// 데이터베이스 커넥터 드라이브 클래스 이름
			
			con = DriverManager.getConnection(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);
			String sql = "select * from student_tb where student_name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student.getName()); 
			pstmt.setInt(2, student.getAge()); 
			successCount = pstmt.executeUpdate();

		} catch(Exception e) {
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
		return successCount;
		
	}// insert ResultSet x 객체 insert 횟수 리턴
		public List<Student> getStudentListAll() {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<Student> students = new ArrayList<>();
			
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");// 데이터베이스 커넥터 드라이브 클래스 이름
				
				con = DriverManager.getConnection(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);
				String sql = "select * from student_tb";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {//조회 한줄 존재
					Student student = Student.builder()
							.studentId(rs.getInt(1))
							.name(rs.getString(2))
							.age(rs.getInt(3))
							.build();
					
					students.add(student);
				}// rs = null;
			} catch(Exception e) {
				e.printStackTrace();
				
			} finally {
				try {
					if(rs != null) {
						rs.close();
					}
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
			return students;
		
	}
}
