/******************************************************************************
 *  Compilation:  javac Expense.java
 *  Execution:    java  Expense
 *  Dependencies: Transaction.java
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
 *  % java Expense
 *
 ******************************************************************************/

package application;

public class Expense extends Transaction 
{
	public Expense(String account, String customer, String date, double amount, String description, String code) 
	{
		super(account, customer, date, amount, description, code);
		adjustedamount = amount;
		this.type = "Expense";
	}
}
