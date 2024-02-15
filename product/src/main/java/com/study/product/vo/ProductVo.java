package com.study.product.vo;

import com.study.product.dto.InsertProductRespDto;
import com.study.product.dto.SearchProductRespDto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
// Vo는 데이터베이스 따라가면됨.
// DTO는 JSON을 따라가면됨.
public class ProductVo {
	private int productId;
	private String productName;
	private int productPrice;
	private String productSize;
	
	public InsertProductRespDto toInsertDto(int successCount) {
		return InsertProductRespDto.builder()
				.successCount(successCount)
				.productId(productId)
				.productName(productName)
				.productPrice(productPrice)
				.productSize(productSize)
				.build();
		// successCount x
	}
	public SearchProductRespDto toSearchDto() {
		return SearchProductRespDto.builder()
				.productId(productId)
				.productName(productName)
				.productPrice(productPrice)
				.productSize(productSize)
				.build();
	}
	// vo db dolumn 많은 column dto 골라담기
	// JSON<->DTO(JSON이랑 맞춰져있음. DB 아님)
	// 부분적으로 맞춰서 사용 DTO
	// servlet<-dto->service<-vo->dao
}
