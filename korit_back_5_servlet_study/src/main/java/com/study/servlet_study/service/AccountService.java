package com.study.servlet_study.service;

import com.study.servlet_study.entity.Account;
import com.study.servlet_study.repository.AccountRepository;

public class AccountService {
	private AccountRepository accountRepository;
	// 객체만 달라짐. AccountRepository와 동일
	private static AccountService instance;
	
	private AccountService() {
		accountRepository = AccountRepository.getInstance();
		// 싱글톤
		// 얘가 생성되서 쟤가 생성되는 연관성이 있는건 아님. 각자 생성 가능
		// 결합도가 높은건 아님.
	}
	
	public static AccountService getInstance() {
		if(instance == null) {
			instance = new AccountService();
		}
		return instance;
	}
	
	// 레포지토리에 접근(멤버변수로 뻄.)
	public int addAccount(Account account) {
		return accountRepository.saveAccount(account);
	}
	
	public Account getAccount(String username) {
		return accountRepository.findAccountByUsername(username);
	}
}
