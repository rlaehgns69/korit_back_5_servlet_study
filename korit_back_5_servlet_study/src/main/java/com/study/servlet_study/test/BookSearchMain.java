package com.study.servlet_study.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.study.servlet_study.config.DBConnectionMgr;
import com.study.servlet_study.entity.Author;
import com.study.servlet_study.entity.Book;
import com.study.servlet_study.entity.Publisher;

public class BookSearchMain {

	public static void main(String[] args) {
		DBConnectionMgr pool = DBConnectionMgr.getInstance();
		
		Scanner scanner = new Scanner(System.in);
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 검색할 도서명을 입력하세요 >>> 글
		
		// 도서명 / 저자명 / 출판사
		try {
			
			con = pool.getConnection();
			System.out.print("검색할 도서명을 입력하세요 >>>");
			String searchBookName = scanner.nextLine();
			String sql="SELECT \r\n"
					+ "book_tb.book_id,\r\n"
					+ "book_tb.book_name,\r\n"
					+ "author_tb.author_id, \r\n"
					+ "author_tb.author_name,\r\n"
					+ "publisher_tb.publisher_id,\r\n"
					+ "publisher_tb.publisher_name\r\n"
					+ "                    FROM book_tb \r\n"
					+ "                    LEFT OUTER JOIN author_tb ON (book_tb.author_id = author_tb.author_id) \r\n"
					+ "                    LEFT OUTER JOIN publisher_tb ON (book_tb.publisher_id = publisher_tb.publisher_id)\r\n"
					+ "                    WHERE \r\n"
					+ "						book_tb.name like"
					+"						\'%"+searchBookName+"%\'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			List<Book> bookList = new ArrayList<>();
			while(rs.next()) {
				bookList.add(Book.builder()
						.bookId(rs.getInt(1))
						.bookName(rs.getString(2))
						.author(Author.builder().authorId(rs.getInt(3)).authorName(rs.getString(4)).build())
						.publisher(Publisher.builder().publisherId(rs.getInt(5)).publisherName(rs.getString(6)).build())
						.build());
			}
			bookList.forEach(book ->System.out.println(book));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}

}
