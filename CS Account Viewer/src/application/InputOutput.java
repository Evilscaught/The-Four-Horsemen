/******************************************************************************
 *  Compilation:  javac InputOutput.java
 *  Execution:    java  InputOutput
 *  Dependencies:
 *
 *  @author(s)		Scott McKay
 *  @version   		0.0.1
 *  @group			The Four Horsemen
 *  @copyright   	None
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;


public class InputOutput
{
	private int accountArrPos;

	private File accountsPath;
	private Account[] accountArr;

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

			//Read in string and create account objects.
			while(input.hasNext())
			{
				createAccount(input.next(), input.next(), input.next(), input.next(), input.next());
			}
			input.close();
		}

		file.close();
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
			IO.readAccounts("Accounts2.txt");
		}
		catch (FileNotFoundException event)
		{
			event.printStackTrace();
		}
		
		//Get the account array.
		accountArr = IO.getAccountArr();

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
	}
	//*/
}
