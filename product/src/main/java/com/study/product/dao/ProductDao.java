package com.study.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.study.product.config.DBConnectionMgr;
import com.study.product.vo.ProductVo;

public class ProductDao {
	private static ProductDao instance;
	private DBConnectionMgr pool;
	// 싱글톤 pool class forname x driver connection x
	private ProductDao() {
		pool = DBConnectionMgr.getInstance();
	}
	
	public static ProductDao getInstance() {
		if(instance == null) {
			instance = new ProductDao();
		}
		return instance;
	}
	// ->Service ProductService
	public List<ProductVo> getProductList() {
		List<ProductVo> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = pool.getConnection();
			String sql = "select * from product_tb";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductVo productVo = ProductVo.builder()
								.productId(rs.getInt(1))
								.productName(rs.getString(2))
								.productPrice(rs.getInt(3))
								.productSize(rs.getString(4))
								.build();
				
				list.add(productVo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return list;
	}
	
	
	public ProductVo findProductByProductName(String productName) {
		ProductVo productVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try {
			con = pool.getConnection();
			String sql = "select * from product_tb where product_name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, productName);
			rs = pstmt.executeQuery();
			// unique
			// 데이터 들고왔는지 확인
			if(rs.next()) {
				productVo = ProductVo.builder()
							.productId(rs.getInt(1))
							.productName(rs.getString(2))
							.productPrice(rs.getInt(3))
							.productSize(rs.getString(4))
							.build();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		// finally pool.freeConnection
		// 라이브러리 pool 관리 간소화됨.
		return productVo;
	}
	
	// dto를 중간에 vo로 변환
	public int save(ProductVo productVo) {
		int successCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//generic key
		try {
			con = pool.getConnection();
			String sql = "insert into product_tb values(0, ?, ?, ?)";
			//pstmt 생성부분 sql, 오토인크리먼트 키값을 알고싶다.
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS );
			
			pstmt.setString(1,  productVo.getProductName());
			pstmt.setInt(2,  productVo.getProductPrice());
			pstmt.setString(3,  productVo.getProductSize());
			
			successCount = pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();// resultSet
			// insert 한건
			// AI key 값 넣기위해서 resultSet
			if(rs.next()) {
				productVo.setProductId(rs.getInt(1));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return successCount;
	}
}
