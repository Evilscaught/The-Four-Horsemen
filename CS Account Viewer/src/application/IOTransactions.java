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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class IOTransactions
{
    private String path;
    private ArrayList<Transaction> transactionArr;

    public IOTransactions(String path)
    {
    	this.path = path;
    	
        //Create an transactions file 'transactions.txt' if one does not already exist.
        File transactionsFile = new File("Transactions.txt");
        try 
        {
        	transactionsFile.createNewFile();
		}
        catch (IOException ioException) 
        {
        	ioException.printStackTrace();
		}
    }

    public void readTransactions() throws FileNotFoundException
    {
        //Create file scanner
        Scanner file = new Scanner(new File(path));
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
                createTransaction(input.next(), input.next(), input.next(), input.nextDouble(), input.next(), input.next(), input.next());
            }
            input.close();
        }
        file.close();
    }

    public void createTransaction(String recipientAcct, String customer, String date, double amount, String description, String type, String code)
    {
    	if(type.equals("Credit Card"))
    	{

    		transactionArr.add(new CCTransaction(recipientAcct, customer, date, amount, description, code));

    	}
    	else if (type.equals("Check"))
    	{
    		transactionArr.add(new CheckTransaction(recipientAcct, customer, date, amount, description, code));
    	}
    	else if (type.equals("Expense"))
    	{
    		transactionArr.add(new Expense(recipientAcct, customer, date, amount, description, code));
    	}
    	else
    	{
            transactionArr.add(new Transaction(recipientAcct, customer, date, amount, description, code));
    	}
    }

    public void deleteTransaction(int index)
    {
    	transactionArr.remove(index);
    }

    public void saveTransactions() throws IOException
    {
        FileWriter out = new FileWriter(new File(path));
        
		for(Transaction transaction : getTransactions())
		{
		    out.write(transaction.toString());
		    out.write('\n');
		}
		out.close();
    }

    public ArrayList<Transaction> getTransactions()
    {
        return transactionArr;
    }
    
    public boolean isEmpty()
    {
    	return transactionArr.isEmpty();
    }

    public static void main(String[] args) throws FileNotFoundException
    {
    	System.out.println("Debugging IOTransaction");
    	IOTransactions IOTrans = new IOTransactions("Transactions.txt");
    	IOTrans.readTransactions();

    	for (Transaction transaction : IOTrans.getTransactions())
    	{
    		System.out.println(transaction.toString());
    		System.out.println(transaction.getClass());
    		System.out.println("\n");
    	}
    }
}
