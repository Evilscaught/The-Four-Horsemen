package application;

import java.text.DecimalFormat;

public class Transaction {

	protected String customer;
	protected String date;
	protected double amount;
	protected double adjustedamount;
	protected String description;
	protected String type;
	protected DecimalFormat df1 = new DecimalFormat(".##");

	public Transaction(String customer, String date, double amount, String description){

		this.customer = customer;
		this.date = date;
		this.amount = amount;
		this.description = description;
		this.type = "None";
		adjustedamount = amount;

	}

	public String viewInfo() {
		String output = customer + "  " + type + "  " + description + "  " + "Gross: " + "$ "+ df1.format(amount) + "  " + "$ " + df1.format(adjustedamount);
		return output;
	}

	public String toString() {
		String output = type + "," + customer + "," + date + "," + amount + "," + description;
		return output;
	}
	
	public String getType()
	{
		return type;
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


}
