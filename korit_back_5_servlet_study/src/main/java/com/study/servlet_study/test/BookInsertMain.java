package com.study.servlet_study.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.study.servlet_study.config.DBConnectionMgr;
import com.study.servlet_study.entity.Author;
import com.study.servlet_study.entity.Book;
import com.study.servlet_study.entity.Publisher;

public class BookInsertMain {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String bookName = null;
		String authorName = null;
		String publisherName = null;
		
		
		System.out.print("도서명 >>> ");
		bookName = scanner.nextLine();
		System.out.print("저자명 >>> ");
		authorName = scanner.nextLine();
		System.out.print("출판사명 >>> ");
		publisherName = scanner.nextLine();
		
		Book book = Book.builder()
					.bookName(bookName)
					.author(Author.builder().authorName(authorName).build())
					.publisher(Publisher.builder().publisherName(publisherName).build())
					.build();
		
		DBConnectionMgr pool = DBConnectionMgr.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		// 결과가 없어서 insert resultSet없다.
		int count = 0;
		
		try {
			con = pool.getConnection();
			String sql = "insert into author_tb values (0, ?)";
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
			//조굼 다름 sql, 매개변수 statement sql insert할 때만 넣어줌.
			pstmt.setString(1, book.getAuthor().getAuthorName());
			pstmt.executeUpdate(); // 리턴값 int 업데이트 성공한 개수
			ResultSet rs = pstmt.getGeneratedKeys();
			// rs 따로만듬
			if(rs.next()) {
				book.getAuthor().setAuthorId(rs.getInt(1));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt); // 리절트 셋 없음.
		}
		
		// publisher_tb
		try {
			con = pool.getConnection();
			String sql = "insert into publisher_tb values (0, ?)";
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
			//조굼 다름 sql, 매개변수 statement sql insert할 때만 넣어줌.
			pstmt.setString(1, book.getPublisher().getPublisherName());
			pstmt.executeUpdate(); // 리턴값 int 업데이트 성공한 개수
			ResultSet rs = pstmt.getGeneratedKeys();
			// rs 따로만듬
			if(rs.next()) {
				book.getPublisher().setPublisherId(rs.getInt(1));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt); // 리절트 셋 없음.
		}
		
		// book_tb
		try {
			con = pool.getConnection();
			String sql = "insert into book_tb values (0, ?, ?, ?)"; // bookName, authorId, publisherId
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
			//조굼 다름 sql, 매개변수 statement sql insert할 때만 넣어줌.
			pstmt.setString(1, book.getBookName());
			pstmt.setInt(2, book.getAuthor().getAuthorId());
			pstmt.setInt(3, book.getPublisher().getPublisherId());
			pstmt.executeUpdate(); // 리턴값 int 업데이트 성공한 개수
			ResultSet rs = pstmt.getGeneratedKeys();
			// rs 따로만듬
			if(rs.next()) {
				book.setBookId(rs.getInt(1));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt); // 리절트 셋 없음.
		}
		
		System.out.println("추가된 도서 정보");
		System.out.println(book);
		
	}

}
