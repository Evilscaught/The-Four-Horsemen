/******************************************************************************
 *  Compilation:  javac Transaction.java
 *  Execution:    java  Transaction
 *  Dependencies:
 *
 *  @author(s)		Jake Wolfe
 *  @version   		0.0.0
 *  @group			The Four Horsemen
 *  @copyright   	None
 *  @date_created   Sometime between October and November
 *
 *  
 *
 *     *
 *
 *     *
 *
 *     *
 *
 *  BUG:
 *
 *  FEATURE:
 *
 *  NOTE:
 *
 *  % java Transaction
 *
 ******************************************************************************/


package application;

import java.text.DecimalFormat;

public class Transaction 
{

	protected String recipientAcct;
	protected String customer;
	protected String date;
	protected double amount;
	protected double adjustedamount;
	protected String description;
	protected String type;
	protected DecimalFormat df1 = new DecimalFormat(".##");

	//Create transaction with a description and code
	public Transaction(String recipientAcct, String customer, String date, double amount, String description, String code)
	{
		this.recipientAcct = recipientAcct;
		this.customer = customer;
		this.date = date;
		this.amount = amount;
		this.description = description;
		this.type = "None";
		adjustedamount = amount;
	}
	
	//Create transaction with a description
	public Transaction(String recipientAcct, String customer, String date, double amount, String description)
	{
		this.recipientAcct = recipientAcct;
		this.customer = customer;
		this.date = date;
		this.amount = amount;
		this.description = description;
		this.type = "None";
		adjustedamount = amount;
	}
	
	//Create transaction without a description
	public Transaction(String recipientAcct, String customer, String date, double amount)
	{
		this.recipientAcct = recipientAcct;
		this.customer = customer;
		this.date = date;
		this.amount = amount;
		this.description = "None";
		this.type = "None";
		adjustedamount = amount;
	}

	public String viewInfo()
	{
		String output;
		String type2 = "";
		
		if (type == "Credit Card") 
		{
			type2 = "CC";
		}
		else if (type == "Check") 
		{
			type2 = "Check";
		}
		else if (type == "Expense") 
		{
			type2 = "Exp.";
		}
		else {
			type2 = "None";
		}
		
		if (customer.length() > 7 && recipientAcct.length() <= 10) {
			output = recipientAcct + "\t\t| " + customer + "\t|  " + type2 + "\t|  "  + "$ " + df1.format(adjustedamount);
		}
		else if (customer.length() <= 7 && recipientAcct.length() <= 10) {
			output = recipientAcct + "\t\t| " + customer + "\t\t|  " + type2 + "\t|  "  + "$ " + df1.format(adjustedamount);
		}
		else if (customer.length() > 7 && recipientAcct.length() > 10) {
			output = recipientAcct + "\t| " + customer + "\t|  " + type2 + "\t|  " + "$ " + df1.format(adjustedamount);
		}
		else 
		{
			output = recipientAcct + "\t| " + customer + "\t\t|  " + type2 + "\t|  " +  "$ " + df1.format(adjustedamount);
		}
		
		return output;
	}

	public String toString() 
	{
		String output = "" + recipientAcct + "," + customer + "," + date + "," + amount + "," + description + "," + type;
		return output;
	}
	
	public String getCustomer()
	{
		return customer;
	}

	public void editCustomer(String customer) 
	{
		this.customer = customer;
	}
	
	public String getDate()
	{
		return date;
	}

	public void editDate(String date) 
	{
		this.date = date;
	}

	public void editAmount(int amount) 
	{
		this.amount = amount;
	}

	public String getDescription()
	{
		return description;
	}
	
	public void editDescription(String description) 
	{
		this.description = description;
	}

	public double getAmount() 
	{
		return adjustedamount;
	}
	
	public String getType() 
	{
		return type;
	}

	public String getRecipientAcct()
	{
		return recipientAcct;
	}

}
