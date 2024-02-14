package com.study.servlet_study.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.study.servlet_study.config.DBConnectionMgr;
import com.study.servlet_study.entity.Author;

public class DBConnectionTestMain {

	public static void main(String[] args) {
		DBConnectionMgr pool = DBConnectionMgr.getInstance(); // static 누워있음. getInstance 무조건 Singleton
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		try {
			// pool 수영장 자바 db연결 connection주소 하나 singletone 하나의 객체로 객체관리
			con = pool.getConnection();
			String name = "junil";
			//String sql = "select * from author_tb where author_name ?" concate;
			//String sql = "select * from author_tb where author_name ?" name= "%"+"aaa"+"%";
			String sql = "select * from author_tb where author_name =?";
			pstmt = con.prepareStatement(sql); // 쿼리창
			pstmt.setString(1, name); // 일련의 표현식
			// insert ,select, update ,delete 4가지
			rs = pstmt.executeQuery(); // 실행-쿼리의 실행결과 
			// 결과창 반복 한줄씩 
			
			
			List<Author> authorList = new ArrayList<>();
			// iterator hasnext has 
			while(rs.next()) {
				authorList.add(Author.builder()
						.authorId(rs.getInt(1))
						.authorName(rs.getString(2))
						.build());
				// author id
//				System.out.println("id: " + rs.getInt(1));
//				// author name
//				System.out.println("name: "+ rs.getString(2));
			}
			// (Consumer<>action)
			authorList.forEach(author -> System.out.println(author));
			for(Author author : authorList) {
				System.out.println(author);
			}
			for(int i = 0; i < authorList.size(); i++) {
				Author author = authorList.get(i);
				System.out.println(author);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}

}
