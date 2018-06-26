/******************************************************************************
 *  Compilation:  javac IOAccounts.java
 *  Execution:    java  IOAccounts
 *  Dependencies:
 *
 *  @author(s)      Scott McKay, Jack Cummings, Jake Wolfe
 *  @version        0.0.0
 *  @group          The Four Horsemen
 *  @copyright      None
 *  @date_created   Tuesday, November 7th, 2017 @3:52 p.m. MST
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
 *  NOTE: Handles input and output for accounts.
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
import java.util.*;

public class IOAccounts
{
    private String path;
    private ArrayList<Account> accountArr;
    private ArrayList<String>  usernames;

    public IOAccounts(String path)
    {
        this.path = path;
        
        //Create an accounts file 'Accounts.txt' if one does not already exist.
        File accountsFile = new File("Accounts.txt");
        try 
        {
			accountsFile.createNewFile();
		}
        catch (IOException ioException) 
        {
			ioException.printStackTrace();
		}
    }

    //Get accounts from database and read into Account array.
    public void readAccounts() throws FileNotFoundException
    {
        //Create file scanner
        Scanner file = new Scanner(new File(path));
        accountArr = new ArrayList<Account>(20);
        usernames  = new ArrayList<String>(20);

        while(file.hasNext())
        {
            //Copy each line of file into token, then scan token.
            String currentLine = file.nextLine();
            Scanner token = new Scanner(currentLine);
            token.useLocale(Locale.US);
            token.useDelimiter(",");

            //Read in string and create account objects.
            while(token.hasNext())
            {
            	//            First Name    Last Name     User-name     Password      Email         Description   Sec-Q1        Sec-Q2        Sec-Q3        is Admin?                          Balance
                createAccount(token.next(), token.next(), token.next(), token.next(), token.next(), token.next(), token.next(), token.next(), token.next(), Boolean.parseBoolean(token.next()),token.nextDouble());
            }
            token.close();
        }
        file.close();
    }

    private void createAccount(String firstName, String lastName, String username, String password, String email, String description, String secQuestion1, String secQuestion2, String secQuestion3, Boolean isAdmin, double accTotal)
    {
        Account account = new Account(firstName, lastName, username, password);
        account.setEmail(email);
        account.setAdmin(isAdmin);
        account.setBalance(accTotal);
        account.setDescription(description);
        account.setSecurityQuestion1(secQuestion1);
        account.setSecurityQuestion2(secQuestion2);
        account.setSecurityQuestion3(secQuestion3);
        account.setBalance(accTotal);

        accountArr.add(account);
    }



    public void createAccount(Account account)
    {
    	accountArr.add(account);
    }

    public void deleteAccount(int index)
    {
    	accountArr.remove(index);
    }

    public ArrayList<Account> getAccounts()
    {
    	return accountArr;
    }

    public Account getAccount(String username)
    {
        for (Account act : this.getAccounts())
        {
            if (act.getUsername().equals(username)||act.getName().equals(username))
            {
                return act;
            }
        }
        return null;

    }

    public ArrayList<String> getUsernames()
    {
    	for (Account acct : getAccounts())
    	{
    		usernames.add(acct.getUsername());
    	}
    	return usernames;
    }

    public void saveAccounts() throws IOException
    {
        FileWriter out = new FileWriter(new File(path));
        for(Account account : getAccounts())
        {
            out.write(account.toString());
            out.write('\n');
        }
        out.close();
    }

	public static void main(String[] args) throws IOException
    {
		IOAccounts IOAcct = new IOAccounts("Accounts.txt");

		IOAcct.readAccounts();
		IOAcct.createAccount("Testing FN", "Testing LN", "Testing UN", "Testing Email", "Testing Password", "Testing Description", "Test SecQ1", "Test SecQ2", "Test SecQ3", false,0.0);
		IOAcct.saveAccounts();

		for (Account account : IOAcct.getAccounts())
		{
			System.out.println(account.toString());
		}
    }
}