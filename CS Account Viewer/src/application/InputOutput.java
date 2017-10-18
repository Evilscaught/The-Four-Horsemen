/* Author:			 Scott McKay
 * Co-Authors:
 * Date of Creation: Monday, October 16th, 2017 @6:49 p.m. MST
 * Version:			 0.0.0
 * Group Name:		 The Four Horsemen
 * Copyright:		 None
 * Purpose of Class: Handles all input and output, such as getting and saving data.
 */

package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class InputOutput
{
	private short accountArrPos;
	
	private File accountsPath;
	private Account[] accountArr;
	
	//Constructor not required. 
	
	//Get accounts from database and read into Account array.
	public void readAccounts(String path) throws FileNotFoundException
	{
		this.accountsPath = new File(path);

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
			
			//Resize accountArr if index surpasses capacity.
			if (accountArrPos >= accountArr.length)
			{
				accountArr = resizeArr(accountArr, (accountArr.length * 2));
			}
			
			//Read in string and create account objects.
			while(input.hasNext())
			{
				accountArr[accountArrPos] = new Account(input.next(), input.next(), input.next(), input.next(), input.next());
				accountArrPos++;
			}
			
			input.close();
		}
		
		file.close();
	}
	
	public void createAccount(String firstName, String lastName)
	{
		accountArr[accountArrPos] = new Account(firstName, lastName);
		
		//Increment accountArrPos to next null position in accountArr
		accountArrPos++;
		
		if (accountArrPos >= accountArr.length)
		{
			accountArr = resizeArr(accountArr, (accountArr.length * 2));
		}
	}
	
	public void deleteAccount(int index)
	{
		accountArr[index] = accountArr[(accountArrPos - 1)];
		accountArr[accountArrPos] = null;
		--accountArrPos;
	}

	public void saveAccounts() throws IOException
	{
		BufferedWriter out = new BufferedWriter(new FileWriter(accountsPath));
		
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
	
	//Resizes arrays of type 'Account', would like to make it accept any arrays in the future.
	private Account[] resizeArr(Account[] itemArr, int capacity)
    {   
        Account tempItemArr[] = new Account[capacity];
        
        //Copy all items to temporary array with larger capacity.
        for (int index = 0; index < itemArr.length; index++)
        {
            tempItemArr[index] = itemArr[index];
        }
        
        return tempItemArr;
    }
	
	//Unit Testing
	public static void main(String[] args)
	{
		System.out.println("Starting");
		
		InputOutput IO = new InputOutput();
		Account[] accountArr = new Account[10];
		
		try 
		{
			IO.readAccounts("./src/Accounts.txt");
		} 
		catch (FileNotFoundException event) 
		{
			event.printStackTrace();
		}
		//Get the account array.
		accountArr = IO.getAccountArr();
		
		short index = 0;
		while(accountArr[index] != null)
		{
			System.out.println(accountArr[index].toString());
			index++;
		}
		
		try 
		{
			IO.saveAccounts();
		} 
		catch (IOException event) 
		{
			event.printStackTrace();
		}

		System.out.println("Ending");
	}
}