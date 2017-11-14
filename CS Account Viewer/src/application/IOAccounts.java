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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class IOAccounts 
{
    private InputStream accountsPath;
    private ArrayList<Account> accountArr;
    
    public IOAccounts(String path)
    {
        accountsPath = this.getClass().getResourceAsStream(path);
    }
	
    //Get accounts from database and read into Account array.
    public void readAccounts() throws FileNotFoundException 
    {    
        //Create file scanner
        Scanner file = new Scanner(accountsPath);
        accountArr = new ArrayList<Account>(20);

        while(file.hasNext())
        {
            //Copy each line of file into token, then scan token.
            String currentLine = file.nextLine();
            Scanner token = new Scanner(currentLine);
            token.useDelimiter(",");

            //Read in string and create account objects.
            while(token.hasNext())
            {
                createAccount(token.next(), token.next(), token.next(), token.next(), token.next());
            }
            token.close();
        }
        file.close();
    }
    
    public void createAccount(String firstName, String lastName, String username, String email, String password)
    {
        Account account = new Account(firstName, lastName, username, email, password); 
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
    
    public void saveAccounts() throws IOException
    {
        BufferedWriter out = new BufferedWriter(new FileWriter(accountsPath.toString()));

        for(Account account : getAccounts())
        {
        	out.write(account.toString());
        	out.newLine();
        }
        out.close();
    }

	public static void main(String[] args) throws IOException
    {
		IOAccounts IOAcct = new IOAccounts("src/Accounts.txt");
		
		IOAcct.readAccounts();
		IOAcct.createAccount("Testing", "Testing", "Testing", "Testing", "Testing");
		IOAcct.saveAccounts();
		for (Account account : IOAcct.getAccounts())
		{
			System.out.println(account.toString());
		}

		
		File file = new File("src/something.txt");
		//BufferedWriter out = new BufferedWriter(new FileWriter(file));
		//out.write("asdfasd");
		//out.close();
		
		@SuppressWarnings("resource")
		Scanner fileScanner = new Scanner(file);
		//I continue to get "asdfasd" in the console,
		//Even when the above BufferedWriter has been commented out
		//And the file 'something.txt' is empty...
		System.out.println(fileScanner.nextLine());
    }
}
















