package com.study.servlet_study.service;

import com.study.servlet_study.entity.Product1;
import com.study.servlet_study.repository.ProductRepository1;

public class ProductService1 {
	private ProductRepository1 productRepository;
	private static ProductService1 instance;
	
	private ProductService1() {
		productRepository = ProductRepository1.getInstance();
	}
	public static ProductService1 getInstance() {
		if(instance == null) {
			instance = new ProductService1();
		}
		return instance;
	}
	
	public int addProduct(Product1 product) {
		return productRepository.saveProduct(product);
	}
	
	public Product1 getProduct(String productName) {
		return productRepository.findProductByUsername(productName);
	}
}
