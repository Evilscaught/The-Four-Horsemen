package application;

public class CheckTransaction extends Transaction {

	public CheckTransaction(String account, String customer, String date, double amount, String description) {
		super(account, customer, date, amount, description);
		this.adjustedamount = this.amount - (this.amount * 0.04);
		this.type = "Check";
	}
}
