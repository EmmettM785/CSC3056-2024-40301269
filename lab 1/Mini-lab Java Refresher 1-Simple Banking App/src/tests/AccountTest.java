package tests;

import model.Account;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AccountTest {
	//TODO 5
	public static void testConstructor() {
		
		LocalDate openingDate = LocalDate.of(2024, 6, 10);
        Date testOpeningDate = Date.from(openingDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        String testAccountNumber = "11223344";
        String testAccountHolderUsername = "Jane_Smith";
        String testAccountType = "Current Account";

        Account account = new Account(testAccountNumber, testAccountHolderUsername, testAccountType, testOpeningDate);

        assert testAccountNumber.equals(account.getAccount_holder()) : "Account number does not match expected value.";
        assert testAccountHolderUsername.equals(account.getUsername_of_account_holder()) : "Account holder's username does not match expected value.";
        assert testAccountType.equals(account.getAccount_type()) : "Account type does not match expected value.";
        assert testOpeningDate.equals(account.getAccount_opening_date()) : "Opening date does not match expected value.";

        System.out.println("testConstructors passed successfully.");
    }
	
	public static void testSetters() {
		
		Account account = new Account("00000", "DefaultUser", "DefaultType", new Date());
        String updatedAccountNumber = "54321";
        String updatedAccountHolderUsername = "Eve_Adams";
        String updatedAccountType = "Fixed Deposit";
        LocalDate updatedOpeningDate = LocalDate.of(2023, 7, 20);
        Date newOpeningDate = Date.from(updatedOpeningDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        account.setAccount_holder(updatedAccountNumber);
        account.setUsername_of_account_holder(updatedAccountHolderUsername);
        account.setAccount_type(updatedAccountType);
        account.setAccount_opening_date(newOpeningDate);

        assert updatedAccountNumber.equals(account.getAccount_holder()) : "Failed to update account number.";
        assert updatedAccountHolderUsername.equals(account.getUsername_of_account_holder()) : "Failed to update account holder's username.";
        assert updatedAccountType.equals(account.getAccount_type()) : "Failed to update account type.";
        assert newOpeningDate.equals(account.getAccount_opening_date()) : "Failed to update opening date.";

        System.out.println("testSetters passed successfully.");
   
		
	}
	
	public static void main(String[] args) {
		testConstructor();
		testSetters();
	}
	
	
}
