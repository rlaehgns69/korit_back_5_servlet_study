package com.study.product.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.study.product.config.DBConfig;
import com.study.product.vo.Product;

public class ProductDao2 {
	private static ProductDao2 instance;
	
	private ProductDao2() {}
	
	public static ProductDao2 getInstance() {
		if(instance == null) {
			instance = new ProductDao2();
		}
		return instance;
	}
	public Product findProductByProductName(String productName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Product product = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");// 데이터베이스 커넥터 드라이브 클래스 이름
			
			con = DriverManager.getConnection(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);
			String sql = "select * from student_tb where student_name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, productName); //json의 
			rs = pstmt.executeQuery();
			
			if(rs.next()) {//조회 한줄 존재
				product = Product.builder()
						.productId(rs.getInt(1))
						.productName(rs.getString(2))
						.productPrice(rs.getString(3))
						.productSize(rs.getString(4))
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
	}
		
		return product;
	}
	
	
	public int saveProduct(Product product) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int successCount = 0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// 데이터베이스 커넥터 드라이브 클래스 이름
			
			con = DriverManager.getConnection(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);
			String sql = "insert into product_tb(product_name, product_price, product_size) values(?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, product.getProductName()); 
			pstmt.setString(2, product.getProductPrice()); 
			pstmt.setString(3, product.getProductSize());
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
		
	}
	public List<Product> getProducttListAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> products = new ArrayList<>();
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");// 데이터베이스 커넥터 드라이브 클래스 이름
			
			con = DriverManager.getConnection(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);
			String sql = "select * from product_tb";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {//조회 한줄 존재
				Product product = Product.builder()
						.productId(rs.getInt(1))
						.productName(rs.getString(2))
						.productPrice(rs.getString(3))
						.productSize(rs.getString(4))
						.build();
				
				products.add(product);
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
		return products;	
	}
}
