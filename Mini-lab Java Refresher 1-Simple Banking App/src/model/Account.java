package model;
import java.util.Date;

//3.2.2
public class Account {
	
	String account_holder;
	String username_of_account_holder;
	String account_type;
	Date account_opening_date;
	
	public Account(String accoun_holder, String username_of_account_holder, String account_type,
			Date account_opening_date) {
		super();
		this.account_holder = accoun_holder;
		this.username_of_account_holder = username_of_account_holder;
		this.account_type = account_type;
		this.account_opening_date = account_opening_date;
	}
	
	@Override
	public String toString() {
	    // Assuming getters for each field exist in your Account class
	    return String.format("%-15s | %-25s | %-15s | %-25s",
	        this.getAccount_holder(),
	        this.getUsername_of_account_holder(),
	        this.getAccount_type(),
	        this.getAccount_opening_date()); 
	}
	
	public String getAccount_holder() {
		return account_holder;
	}
	public void setAccount_holder(String accoun_holder) {
		this.account_holder = accoun_holder;
	}
	public String getUsername_of_account_holder() {
		return username_of_account_holder;
	}
	public void setUsername_of_account_holder(String username_of_account_holder) {
		this.username_of_account_holder = username_of_account_holder;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public Date getAccount_opening_date() {
		return account_opening_date;
	}
	public void setAccount_opening_date(Date account_opening_date) {
		this.account_opening_date = account_opening_date;
	}

}

