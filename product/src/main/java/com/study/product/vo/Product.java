package com.study.product.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Product {
	private int productId;
	private String productName;
	private String productPrice;
	private String productSize; 
}
