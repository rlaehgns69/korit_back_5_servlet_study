package com.study.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;
import com.study.product.dao.ProductDao;
import com.study.product.dto.InsertProductReqDto;
import com.study.product.dto.InsertProductRespDto;
import com.study.product.dto.SearchProductRespDto;
import com.study.product.vo.ProductVo;

public class ProductService {
	private static ProductService instance;
	private ProductDao productDao;
	// ProductDao
	private ProductService() {
		productDao = ProductDao.getInstance();
	}
	public static ProductService getInstance() {
		if(instance ==null) {
			instance = new ProductService();
		}
		return instance;
	}
	// 중복확인하는 로직
	public boolean isDuplicatedProductName(String productName) {
		//boolean result = false;
		// result = productDao.findProductByProductName(productName) != null;
		// 중복됐다. true
		return productDao.findProductByProductName(productName) != null;// false / true
	}
	
	// void -> insertProductResp
	public InsertProductRespDto addProduct(InsertProductReqDto insertProductReqDto) {
		ProductVo productVo = insertProductReqDto.toVo();
		
		int successCount = productDao.save(productVo);
		return productVo.toInsertDto(successCount);
		
//		ProductVo productVo = insertProductReqDto.toVo();
//		
//		int successcount = productDao.save(productVo);
//		
//		productVo.getProductId();
		//productDao.save(insertProductReqDto.toVo());//200번 주소
	}
	// DAO -> SERVICE 
	// DTO생성
	public List<SearchProductRespDto> searchProducts() {
		List<SearchProductRespDto> searchProductRespDtos = new ArrayList<>();
		List<ProductVo> productVos = productDao.getProductList(); // 텅비어있는 리스트
//		 [vo, vo, vo, vo ... 안에서 각각 toDto가지고 있음]
//		 반복을 돌려서 꺼내면 vo.toSearchDto()-> SearchProductRespDto 하나씩 만들어줌
//		 vos꺼내서 dto만들어서 추가
		for(ProductVo productVo : productVos) {
			searchProductRespDtos.add(productVo.toSearchDto());
		}
		return searchProductRespDtos;
		
		// List 
		// stream으로 람다식
		
		// map
		// [1, 2, 3, 4, 5]			->		[2, 4, 6, 8, 10]
		// let nums = [1 2, 3, 4, 5]
		// nums.map(num => num * 2) [2, 4, 6, 8 ,10]
				//productVos(List) .map 사용 x .stream 사용가능 자바스크립트 배열
				// stream.map(vo -> vo.toSearchDto()) vo -> dto
//		Stream<String> strStream = Stream.of("a","b","c","d");
//		strStream.peek(str -> System.out.println(str));
//		
//		List<String> strList = strStream.peek(System.out::println).collect(Collectors.toList());
		
//		return productDao.getProductList().stream()
//				.map(vo -> vo.toSearchDto()) // stream 가공하는 영역
//				.collect(Collectors.toList()); // list 변환하는 영역
		//strStream.collect(Collectors.toList());
		// 배열이랑 같다 반복활용 할 때 사용한다.
		
//		return productDao.getProductList().stream()
//					.map(ProductVo::toSearchDto)
//					.collect(Collectors.toList());
		
		
		
	}
}
