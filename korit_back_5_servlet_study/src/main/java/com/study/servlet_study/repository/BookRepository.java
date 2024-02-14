package com.study.servlet_study.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.study.servlet_study.config.DBConnectionMgr;
import com.study.servlet_study.entity.Author;
import com.study.servlet_study.entity.Book;
import com.study.servlet_study.entity.Publisher;

public class BookRepository {
	private static BookRepository instance;
	private DBConnectionMgr pool;
	
	private BookRepository() {
		pool = DBConnectionMgr.getInstance();
	}
	
	public static BookRepository getInstance() {
		if(instance == null) {
			instance = new BookRepository();
		}
		return instance;
	}
	
	public int saveBook(Book book) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = pool.getConnection();
			String sql = "insert into author_tb values (0, ?)";
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
			//조금 다름 sql, 매개변수 statement sql insert할 때만 넣어줌.
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
		
		return 1;
	}
	// doGet
	public Book findBookByBookId(int bookId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Book findBook = null;
		
		
		try {
			con = pool.getConnection();
			String sql = "select * from book_view where book_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bookId); // 단권 조회
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				findBook = Book.builder()
						.bookId(rs.getInt(1))
						.bookName(rs.getString(2))
						.author(Author.builder()
								.authorId(rs.getInt(3))
								.authorName(rs.getString(4))
								.build())
						.publisher(Publisher.builder()
								.publisherId(rs.getInt(5))
								.publisherName(rs.getString(6))
								.build())
								.build();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 멤버변수 생성되어질때 객체 생성
		finally {
			pool.freeConnection(con ,pstmt, rs);
		}
		return findBook;
	}
	
	public List<Book> searchBookList(Map<String, String> params) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Book> bookList = new ArrayList<>();
		
		Map<String, String> keyData = new HashMap<>();
		keyData.put("bookName", "book_name");
		keyData.put("authorName", "author_name");
		keyData.put("publisherName", "publisher_name");
		
		try {
			con = pool.getConnection();
			String sql = "select * from book_view where ? = 1";
			for(String key : params.keySet()) {
				sql += " or " + keyData.get(key) + " like ?";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, params.isEmpty() ? 1 : 0);
			
			int i = 2;
			for(String key : params.keySet()) {
				pstmt.setString(i + 2, "%" + params.get(key) + "%");
				i++;
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Book book = Book.builder()
						.bookId(rs.getInt(1))
						.bookName(rs.getString(2))
						.author(Author.builder().authorId(rs.getInt(3)).authorName(rs.getString(4)).build())
						.publisher(Publisher.builder().publisherId(rs.getInt(5)).publisherName(rs.getString(6)).build())
						.build();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con ,pstmt,rs);
		}
		return bookList;
	}
}
