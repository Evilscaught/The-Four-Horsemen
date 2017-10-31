package application;

public class CCTransaction extends Transaction {
	
	public CCTransaction(String customer, String date, double amount, String description) {
		super(customer, date, amount, description);
		this.adjustedamount = this.amount - (this.amount* 0.12);
		this.type = "Credit Card";
	}
}
