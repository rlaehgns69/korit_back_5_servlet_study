package com.study.servlet_study.test;

import java.util.function.Consumer;

import com.study.servlet_study.entity.Author;

class Print<T> implements Consumer<T> {
	// 추상메소드 (오버라이드)
	@Override
	public void accept(T t) {
		System.out.println(t);
	}	
}
// 컬렉션-list, set 제네릭
// 클래스 구현, 익명클래스, 람다식 (모두 같다.)
public class LamdaMain {

	public static void main(String[] args) {
		Consumer<Author> print0 = new Print<Author>();
		// 컨슈머로 업캐스팅 컨슈머 임플리먼트
		Consumer<Author> print1 = new Consumer<Author>() {
			@Override
			public void accept(Author author) {
				System.out.println(author);
			}
		};
		// 대입되어야할 객체 주소 -> new... (author)
		Consumer<Author> print2 = (author) -> System.out.println(author); 
		// 람다 추상메소드가 하나(무조건 2개면 익명 클래스) 익명클래스의 객체 생성 짧게
		
		print0.accept(Author.builder().authorId(1).authorName("김준일").build());
		print1.accept(Author.builder().authorId(2).authorName("김준이").build());
		print2.accept(Author.builder().authorId(3).authorName("김준삼").build());
		
		forEach(print2);
		
		forEach(author -> {
			System.out.println("<<< test >>>");
			System.out.println(author);
			});
	}
	public static void forEach(Consumer<Author> action) {
		action.accept(Author.builder().authorId(4).authorName("김준사").build());
	}
}
