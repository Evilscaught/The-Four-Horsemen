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
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class IOTransactions
{
    private String transactionsPath;
    private ArrayList<Transaction> transactionArr;
    private IOAccounts ioAccounts;
    private Account acc;
    private double accTotal;

    public IOTransactions(String path)
    {
    	transactionsPath = path;
    }

    public void readTransactions() throws FileNotFoundException
    {
        //Create file scanner
    	InputStream transactionsFile = this.getClass().getResourceAsStream(transactionsPath);
        Scanner file = new Scanner(transactionsFile);
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

         double transAmount =0.0;


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
        URL url = this.getClass().getResource(transactionsPath);
        File file;

        try
        {
            file = new File(url.toURI().getPath());

            FileWriter out = new FileWriter(file);
            for(Transaction transaction : getTransactions())
            {
                out.write(transaction.toString());
                out.write('\n');
            }
            out.close();
        }
        catch (URISyntaxException event)
        {
            event.printStackTrace();
        }
    }

    public ArrayList<Transaction> getTransactions()
    {
        return transactionArr;
    }

    public static void main(String[] args) throws FileNotFoundException
    {
    	System.out.println("Debugging IOTransaction");
    	IOTransactions IOTrans = new IOTransactions("src/Transactions.txt");
    	IOTrans.readTransactions();

    	for (Transaction transaction : IOTrans.getTransactions())
    	{
    		System.out.println(transaction.toString());
    		System.out.println(transaction.getClass());
    		System.out.println("\n");
    	}
    }
}
