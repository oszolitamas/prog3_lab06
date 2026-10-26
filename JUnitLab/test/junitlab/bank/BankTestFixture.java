package junitlab.bank;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import junitlab.bank.impl.GreatSavingsBank;

class BankTestFixture {
	Bank bank;
    String account1;
    String account2;
    
    @BeforeEach
    void setUp() throws AccountNotExistsException {
        bank = new GreatSavingsBank();
        account1 = bank.openAccount();
        account2 = bank.openAccount();
        bank.deposit(account1, 1500);
        bank.deposit(account2, 12000);
    }

    @Test
    void testTransfer() throws BankException {
    	bank.transfer(account2, account1, 3456);
    	assertEquals(4956, bank.getBalance(account1));
    	assertEquals(8544, bank.getBalance(account2)); //Sikeres teszt, ha az átutalás után a megfelelő összegek lesznek a számlákon
    }

    @Test
    void testTransferWithoutEnoughFunds() {
        assertThrows(NotEnoughFundsException.class, () -> bank.transfer(account1, account2, 3456));
    }
}
