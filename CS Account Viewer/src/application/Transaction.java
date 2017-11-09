package application;

import java.text.DecimalFormat;

public class Transaction {

	protected String account;
	protected String customer;
	protected String date;
	protected double amount;
	protected double adjustedamount;
	protected String description;
	protected String type;
	protected DecimalFormat df1 = new DecimalFormat(".##");

	public Transaction(String account, String customer, String date, double amount, String description){

		this.account = account;
		this.customer = customer;
		this.date = date;
		this.amount = amount;
		this.description = description;
		this.type = "None";
		adjustedamount = amount;

	}

	public String viewInfo() {
		String output;
		String type2 = "";
		
		if (type == "Credit Card") {
			type2 = "CC";
		}
		else if (type == "Check") {
			type2 = "Check";
		}
		else if (type == "Expense") {
			type2 = "Exp.";
		}
		else {
			type2 = "None";
		}
		
		if (customer.length() > 7 && account.length() <= 10) {
			output = account + "\t\t| " + customer + "\t|  " + type2 + "\t|  "  + "$ " + df1.format(adjustedamount);
		}
		else if (customer.length() <= 7 && account.length() <= 10) {
			output = account + "\t\t| " + customer + "\t\t|  " + type2 + "\t|  "  + "$ " + df1.format(adjustedamount);
		}
		else if (customer.length() > 7 && account.length() > 10) {
			output = account + "\t| " + customer + "\t|  " + type2 + "\t|  " + "$ " + df1.format(adjustedamount);
		}
		else {
			output = account + "\t| " + customer + "\t\t|  " + type2 + "\t|  " +  "$ " + df1.format(adjustedamount);
		}
		
		return output;
	}

	public String toString() {
		String output = "" + customer + "," + date + "," + amount + "," + description;
		return output;
	}

	public void editCustomer(String customer) {
		this.customer = customer;
	}

	public void editDate(String date) {
		this.date = date;
	}

	public void editAmount(int amount) {
		this.amount = amount;
	}

	public void editDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return adjustedamount;
	}
	
	public String getType() {
		return type;
	}


}
