/******************************************************************************
 *  Compilation:  javac IOAccounts.java
 *  Execution:    java  IOAccounts
 *  Dependencies:
 *
 *  @author(s)      Scott McKay, Jack Cummings, Jake Wolfe
 *  @version        0.0.0
 *  @group          The Four Horsemen
 *  @copyright      None
 *  @date_created   Tuesday, November 7th, 2017 @3:53 p.m. MST
 *
 *  Blueprint for account data-type.
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
 *  NOTE: Handles input and output for transactions. 
 *
 *  % java Account
 *
 ******************************************************************************/


package application;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class IOTransactions 
{
    private InputStream transactionsPath;
    private ArrayList<Transaction> transactionArr;
	
    public IOTransactions(String path)
    {
    	transactionsPath = this.getClass().getResourceAsStream(path);
    }
    
    public void readTransactions() throws FileNotFoundException
    {
        //Create file scanner
        Scanner file = new Scanner(transactionsPath);
        transactionArr = new ArrayList<Transaction>(10);
        
        while(file.hasNext())
        {
            //Copy each line of the file into token, then scan token
            String token = file.nextLine();
            Scanner input = new Scanner(token);
            input.useDelimiter(",");
            
            //Read in String and Create Transaction Objects
            while (input.hasNext())
            {	
                createTransaction(input.next(), input.next(), input.next(), input.nextDouble(), input.next());
            }
            input.close();
        }
        file.close();
    }
	
    public void createTransaction(String type, String customer, String date, double amount, String description)
    {
    	if      (type.equals("Credit Card"))
    	{
    		transactionArr.add(new CCTransaction(customer, date, amount, description));
    	}
    	else if (type.equals("Check"))
    	{
    		transactionArr.add(new CheckTransaction(customer, date, amount, description));
    	}
    	else if (type.equals("Expense"))
    	{
    		transactionArr.add(new Expense(customer, date, amount, description));
    	}
    	else
    	{
            transactionArr.add(new Transaction(customer, date, amount, description));
    	}
    }
    
    public void deleteTransaction(int index)
    {
    	transactionArr.remove(index);
    }
    
    public void saveTransactions() throws IOException
    {
        BufferedWriter out = new BufferedWriter(new FileWriter(transactionsPath.toString()));
        
        for (Transaction transaction : getTransactionArr())
        {
        	out.write(transaction.toString());
        	out.newLine();
        }
        out.close();
    }
    
    public ArrayList<Transaction> getTransactionArr()
    {
        return transactionArr;
    }

    public static void main(String[] args) throws FileNotFoundException
    {
    	System.out.println("Debugging IOTransaction");
    	IOTransactions IOTrans = new IOTransactions("src/Transactions.txt");
    	IOTrans.readTransactions();
    	
    	for (Transaction transaction : IOTrans.getTransactionArr())
    	{
    		System.out.println(transaction.toString());
    		System.out.println(transaction.getClass());
    		System.out.println("\n");
    	}	
    }
}
