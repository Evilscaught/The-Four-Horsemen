/******************************************************************************
 *  Compilation:  javac CCTransaction.java
 *  Execution:    java  CCTransaction
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
 *  % java CCTransaction
 *
 ******************************************************************************/

package application;

public class CCTransaction extends Transaction 
{
	public CCTransaction(String account, String customer, String date, double amount, String description, String code)
	{
		super(account, customer, date, amount, description, code);
		this.adjustedamount = this.amount - (this.amount* 0.12);
		this.fee = (this.amount*.12);
		this.type = "Credit Card";
	}
}
