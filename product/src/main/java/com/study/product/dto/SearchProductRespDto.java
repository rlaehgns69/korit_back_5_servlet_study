package com.study.product.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SearchProductRespDto {
	private int productId;
	private String productName;
	private int productPrice;
	private String productSize;

}
// ProductVo랑 같다
// client - JSON - servlet - dto - service - vo - dao
// 순서대로 vo가 끝
// 반대로 select vo service vo를 dto로 변환해서 dto를 json으로 변환해서