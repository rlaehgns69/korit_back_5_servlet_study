package com.study.product.dto;

import com.study.product.vo.ProductVo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InsertProductReqDto {
	// 상품번호 x product-input
	private String productName;
	private int productPrice;
	private String productSize;
	
	public ProductVo toVo() {
		return ProductVo.builder()
				.productName(productName)
				.productPrice(productPrice)
				.productSize(productSize)
				.build();
		//dto를 vo로 변환하는 메서드
	}
}
