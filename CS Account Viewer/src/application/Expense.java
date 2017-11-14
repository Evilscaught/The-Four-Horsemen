package application;

public class Expense extends Transaction {

	public Expense(String account, String customer, String date, double amount, String description) {
		super(account, customer, date, amount, description);
		adjustedamount = amount;
		this.type = "Expense";
		
	}

}
