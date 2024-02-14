package com.study.servlet_study.repository;

import java.util.ArrayList;
import java.util.List;

import com.study.servlet_study.entity.Account;

// 싱글톤-레포지토리 싱글톤
public class AccountRepository {
	private List<Account> accountList;
	private static AccountRepository instance;
	// 1.자기자신을 담을 수있는 객체 인스턴스
	
	// 공유개념이 아니라 생성개념. public private
	// 생성자는 외부에서 생성되지 않게 막음.
	//(NoArgsConstructor)
		private AccountRepository() {
			accountList = new ArrayList<>();
		}
	
	// 2.객체 생성 만들기
	public static AccountRepository getInstance(){
		if(instance == null) {
			instance = new AccountRepository();
			// 한번만 생성
		}
		return instance;
	}
	public int saveAccount(Account account) {
		accountList.add(account);
		return 1; // db연결 이후
	}
	
	// 리스트에 어카운트들 반복으로 어카운트꺼내서 어카운트 유저네임이 찾고자 하는 유저네임이 파라미터 유저네임과 일치하면
	public Account findAccountByUsername(String username) {
		Account findAccount = null;
		
		for(Account account: accountList) {
			if(account.getUsername().equals(username)) {
				findAccount = account;
				break;
			}	
			// 반복될때 까지 못찾으면 null
		}
	
		return findAccount;
	}
}
