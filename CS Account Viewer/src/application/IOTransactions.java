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
import java.util.Scanner;

public class IOTransactions 
{
    private int transArrPos;

    private InputStream transactionsPath;
    private Transaction[] transactionArr;
	
    public void readTransactions(String path) throws FileNotFoundException
    {
        transactionsPath = this.getClass().getResourceAsStream(path);
        
        //Create file scanner
        transArrPos = 0;
        Scanner file = new Scanner(transactionsPath);
        transactionArr = new Transaction[10];
        
        while(file.hasNext())
        {
            //Copy each line of the file into token, then scan token
            String token = file.nextLine();
            Scanner input = new Scanner(token);
            input.useDelimiter(",");
            
            //Read in String and Create Transaction Objects
            while (input.hasNext())
            {
                createTransaction(input.next(), input.next(), input.nextDouble(), input.next());
            }
            input.close();
        }
        file.close();
    }
	
    public void createTransaction(String customer, String date, double amount, String description)
    {
        transactionArr[transArrPos] = new Transaction(customer, date, amount, description);
        
        //Increment transArrPos to next null position in transactionArr
        transArrPos++;
        
        if (transArrPos >= transactionArr.length)
        {
            transactionArr = (Transaction[]) resizeArray(transArrPos, (transactionArr.length * 2));
        }   
    }
    
    public void createCCTransaction(String customer, String date, double amount, String description)
    {
        transactionArr[transArrPos] = new CCTransaction(customer, date, amount, description);
        
        //Increment transArrPos to next null position in transactionArr
        transArrPos++;
        
        if (transArrPos >= transactionArr.length)
        {
            transactionArr = (Transaction[]) resizeArray(transArrPos, (transactionArr.length * 2));
        }   
    }
    
    public void createCheckTransaction(String customer, String date, double amount, String description)
    {
        transactionArr[transArrPos] = new CheckTransaction(customer, date, amount, description);
        
        //Increment transArrPos to next null position in transactionArr
        transArrPos++;
        
        if (transArrPos >= transactionArr.length)
        {
            transactionArr = (Transaction[]) resizeArray(transArrPos, (transactionArr.length * 2));
        }   
    }
    
    public void createExpense(String customer, String date, double amount, String description)
    {
        transactionArr[transArrPos] = new Expense(customer, date, amount, description);
        
        //Increment transArrPos to next null position in transactionArr
        transArrPos++;
        
        if (transArrPos >= transactionArr.length)
        {
            transactionArr = (Transaction[]) resizeArray(transArrPos, (transactionArr.length * 2));
        }   
    }
    
    public void deleteTransaction(int index)
    {
        //If array is already empty or index is out of range:
        if (transArrPos <= 0 || index >= transArrPos || index < 0)
        {
            return;
        }
        else
        {
            //Swap last entry in array with entry that is pending deletion
            transactionArr[index] = transactionArr[(transArrPos - 1)];
            //Set last entry in array to null (Garbage Collection)
            transactionArr[transArrPos - 1] = null;
            //Decrement Size of TransactionArr
            --transArrPos;
        }
        
        if ((transArrPos < (transactionArr.length * (1/4))) && (transArrPos > 5))
        {
            transactionArr = (Transaction[]) resizeArray(transactionArr, (transactionArr.length * 1/2));
        }
    }
    
    public void saveTransactions() throws IOException
    {
        BufferedWriter out = new BufferedWriter(new FileWriter(transactionsPath.toString()));
        
        for(int index = 0; index < transArrPos; index++)
        {
            out.write(transactionArr[index].toString());
            out.newLine();
        }
        out.close();
    }
    
    public int getTransactionArrPos()
    {
        return transArrPos;
    }
    
    public Transaction[] getTransactionArr()
    {
        return transactionArr;
    }
    
    @SuppressWarnings("rawtypes")
    private static Object resizeArray (Object oldArray, int newSize) 
    {
           int oldSize = java.lang.reflect.Array.getLength(oldArray);
           Class elementType = oldArray.getClass().getComponentType();
           Object newArray = java.lang.reflect.Array.newInstance(elementType, newSize);
           int preserveLength = Math.min(oldSize, newSize);
           if (preserveLength > 0)
           {
              System.arraycopy(oldArray, 0, newArray, 0, preserveLength);
           }
           
           return newArray; 
    }
}
