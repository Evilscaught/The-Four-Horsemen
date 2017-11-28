/******************************************************************************
 *  Compilation:  javac CheckTransaction.java
 *  Execution:    java  CheckTransaction
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
 *  % java CheckTransaction
 *
 ******************************************************************************/

package application;

public class CheckTransaction extends Transaction 
{
	public CheckTransaction(String account, String customer, String date, double amount, String description, String code) 
	{
		super(account, customer, date, amount, description);
		this.adjustedamount = this.amount - (this.amount * 0.04);
		this.type = "Check";
	}
}
