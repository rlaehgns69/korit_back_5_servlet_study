package com.study.servlet_study.repository;



import java.util.ArrayList;
import java.util.List;

import com.study.servlet_study.entity.Product;

public class ProductRepository {
	private List<Product> productList;
	private static ProductRepository instance;
	
		private ProductRepository() {
			productList = new ArrayList<>();
		}
	
	public static ProductRepository getInstance() {
		if(instance == null) {
			instance = new ProductRepository();
		}
		return instance;
	}
	
	public int saveProduct(Product product) {
		productList.add(product);
		return 1;
	}
	
	public Product findProductByUsername(String username) {
		Product findProduct = null;
		
		for(Product product : productList) {
			findProduct = product;
			break;
		}
		
		return findProduct;
	}
	
	
}
