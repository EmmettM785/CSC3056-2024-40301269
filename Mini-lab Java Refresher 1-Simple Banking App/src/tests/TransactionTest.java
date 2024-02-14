package tests;

import java.util.Calendar;
import java.util.Date;

import model.Transaction;

public class TransactionTest {
	public static void main(String[] args) {
		 Calendar calendar = Calendar.getInstance();
	        
	        calendar.set(Calendar.YEAR, 2024);
	        calendar.set(Calendar.MONTH, Calendar.MAY); 
	        calendar.set(Calendar.DAY_OF_MONTH, 5);
	        
	        Date transactionDate = calendar.getTime();
		Transaction testTransaction = new Transaction("40303840", 500,transactionDate
				); 
		
		System.out.println(testTransaction);
	}
}

