package application;

public class Expense extends Transaction {

	public Expense(String customer, String date, double amount, String description) {
		super(customer, date, amount, description);
		adjustedamount = amount;
		this.type = "Expense";
		
	}

}
