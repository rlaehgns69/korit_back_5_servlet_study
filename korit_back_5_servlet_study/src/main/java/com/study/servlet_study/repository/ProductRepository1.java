package com.study.servlet_study.repository;



import java.util.ArrayList;
import java.util.List;

import com.study.servlet_study.entity.Product1;

public class ProductRepository1 {
	private List<Product1> productList;
	private static ProductRepository1 instance;
	
		private ProductRepository1() {
			productList = new ArrayList<>();
		}
	
	public static ProductRepository1 getInstance() {
		if(instance == null) {
			instance = new ProductRepository1();
		}
		return instance;
	}
	
	public int saveProduct(Product1 product) {
		productList.add(product);
		return 1;
	}
	
	public Product1 findProductByUsername(String username) {
		Product1 findProduct = null;
		
		for(Product1 product : productList) {
			findProduct = product;
			break;
		}
		
		return findProduct;
	}
	
	
}
