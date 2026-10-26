package junitlab.bank;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import junitlab.bank.impl.GreatSavingsBank;

class BankTest {

	@Test
	void testOpenAccount() throws AccountNotExistsException {
		Bank bank = new GreatSavingsBank();
		String accountNumber = bank.openAccount();
		assertEquals(0, bank.getBalance(accountNumber)); //Sikeres a teszt, ha az egyenleg 0
	}

	@Test
	void testUniqueAccount() {
	    Bank bank = new GreatSavingsBank();
	    String account1 = bank.openAccount();
	    String account2 = bank.openAccount();
	    assertNotEquals(account1, account2); //Sikeres a teszt, ha nem egyenlő a két megnyitott számlaszám
	}
	

	@Test
	void testInvalidAccount() {
		Bank bank = new GreatSavingsBank();
		assertThrows(AccountNotExistsException.class, () -> bank.getBalance("nemletezo123")); //Nem létezik a számlaszám, megfelelő teszt eredmény: error
	}

	@Test
	void testDeposit() throws AccountNotExistsException {
	    Bank bank = new GreatSavingsBank();
	    String account = bank.openAccount();
	    bank.deposit(account, 2000);
	    assertEquals(2000, bank.getBalance(account)); //Sikeres volt a pénz befizetés
	}
}
