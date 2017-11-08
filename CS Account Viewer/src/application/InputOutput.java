/******************************************************************************
 *  Compilation:  javac InputOutput.java
 *  Execution:    java  InputOutput
 *  Dependencies:
 *
 *  @author(s)      Scott McKay
 *  @version        0.0.1
 *  @group          The Four Horsemen
 *  @copyright      None
 *  @date_created   Monday, October 16th, 2017 @6:49 p.m. MST
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
 *  NOTE: Handles all input and output, such as getting and saving data.
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


public class InputOutput
{
    private int accountArrPos;
    private int transArrPos;

    private InputStream accountsPath;
    private InputStream transactionsPath;
    private Account[] accountArr;
    private Transaction[] transactionArr;

    //Get accounts from database and read into Account array.
    public void readAccounts(String path) throws FileNotFoundException 
    {   
        accountsPath = this.getClass().getResourceAsStream(path);

        //Create file scanner
        accountArrPos = 0;
        Scanner file = new Scanner(accountsPath);
        accountArr = new Account[10];

        while(file.hasNext())
        {
            //Copy each line of file into token, then scan token.
            String token = file.nextLine();
            Scanner input = new Scanner(token);
            input.useDelimiter(",");

            //Read in string and create account objects.
            while(input.hasNext())
            {
                createAccount(input.next(), input.next(), input.next(), input.next(), input.next());
            }
            input.close();
        }
        file.close();
    }
    
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
        
        if (transArrPos >= accountArr.length)
        {
            transactionArr = (Transaction[]) resizeArray(transArrPos, (transactionArr.length * 2));
        }   
    }
    
    public void createCCTransaction(String customer, String date, double amount, String description)
    {
        transactionArr[transArrPos] = new CCTransaction(customer, date, amount, description);
        
        //Increment transArrPos to next null position in transactionArr
        transArrPos++;
        
        if (transArrPos >= accountArr.length)
        {
            transactionArr = (Transaction[]) resizeArray(transArrPos, (transactionArr.length * 2));
        }   
    }
    
    public void createCheckTransaction(String customer, String date, double amount, String description)
    {
        transactionArr[transArrPos] = new CheckTransaction(customer, date, amount, description);
        
        //Increment transArrPos to next null position in transactionArr
        transArrPos++;
        
        if (transArrPos >= accountArr.length)
        {
            transactionArr = (Transaction[]) resizeArray(transArrPos, (transactionArr.length * 2));
        }   
    }
    
    public void createExpense(String customer, String date, double amount, String description)
    {
        transactionArr[transArrPos] = new Expense(customer, date, amount, description);
        
        //Increment transArrPos to next null position in transactionArr
        transArrPos++;
        
        if (transArrPos >= accountArr.length)
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

    public void createAccount(String firstName, String lastName, String username, String email, String password)
    {
        accountArr[accountArrPos] = new Account(firstName, lastName, username, email, password);

        //Increment accountArrPos to next null position in accountArr
        accountArrPos++;

        if (accountArrPos >= accountArr.length)
        {
            accountArr = (Account[]) resizeArray(accountArr, (accountArr.length * 2));
        }
    }

    public void deleteAccount(int index)
    {
        if (accountArrPos <= 0)
        {
            return; //Do Nothing
        }
        else
        {
            //Swap last entry in array with entry that is pending deletion
            accountArr[index] = accountArr[(accountArrPos - 1)];
            //Set last entry in array to null (Garbage Collection)
            accountArr[accountArrPos - 1] = null;
            //Decrement Size of AccountArr
            --accountArrPos;
        }
        
        //Reduce length of the array if less than 1/4 the capacity is being used.
        if ((accountArrPos < (accountArr.length * (1/4))) && (accountArrPos > 5))
        {
            accountArr = (Account[]) resizeArray(accountArr, (accountArr.length * 1/2));
        }
    }

    public void saveAccounts() throws IOException
    {
        BufferedWriter out = new BufferedWriter(new FileWriter(accountsPath.toString()));

        for(short index = 0; index < accountArrPos; index++)
        {
            out.write(accountArr[index].toString());
            out.newLine();
        }
        out.close();
    }

    public Account[] getAccountArr()
    {
        return accountArr;
    }

    public int getAccountArrPos()
    {
        return accountArrPos;
    }

    //http://www.source-code.biz/snippets/java/3.htm
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
    
    //Unit Testing
    ///*
    public static void main(String[] args)
    {
        InputOutput IO = new InputOutput();
        Account[] accountArr;
        
        
        try
        {
            IO.readAccounts("src/Accounts.txt");
        }
        catch (FileNotFoundException event)
        {
            event.printStackTrace();
        }
        
        
        
        //Get the account array.
        IO.createAccount("Jack", "Metcaf", "N/A", "N/A", "csci323");
        accountArr = IO.getAccountArr();



        short index = 0;
        while(accountArr[index] != null)
        {
            System.out.println(accountArr[index].toString());
            index++;
        }
        index = 0;
        
        try
        {
            IO.saveAccounts();
        }
        catch (IOException event)
        {
            event.printStackTrace();
        }
    }
    //*/
}