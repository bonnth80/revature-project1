package com.novabank.accountBO.test;

import java.util.Date;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import com.novabank.accountBO.AccountBoImp;
import com.novabank.exception.BusinessException;
import com.novabank.to.Account;

public class AccountBoTest {
	@Test
	void testUserGetter() {
		Account account = new Account(
				0,
				0,
				new Date(),
				0,
				250.00F,
				0.00F);
		
		try {
			Account retAccount = new AccountBoImp().getAccountByAccountNumber(0);
			assertEquals(account, retAccount);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
