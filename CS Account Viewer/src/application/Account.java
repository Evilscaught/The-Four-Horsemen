/******************************************************************************
 *  Compilation:  javac Account.java
 *  Execution:    java  Account
 *  Dependencies: Comparable.java
 *
 *  @author(s)		Scott McKay
 *  @version   		0.0.0
 *  @group			The Four Horsemen
 *  @copyright   	None
 *  @date_created   Monday, October 16th, 2017 @7:03 p.m. MST
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
 *  NOTE:
 *
 *  % java Account
 *
 ******************************************************************************/
package application;

import java.util.Arrays;
import java.util.Comparator;

public class Account implements Comparable<Account>
{
	private String firstName, lastName;
	private String email;
	private String username;
	private String password;

	//Constructors:

	//Initialize without email, user-name, and default password
	public Account(String firstName, String lastName)
	{
		this.firstName = firstName;
		this.lastName  = lastName;

		password = "csadmin";
		email    = "N/A";
		username = "N/A";
	}


	//Initialize without email and default password
	public Account(String firstName, String lastName, String username)
	{
		this.firstName = firstName;
		this.lastName  = lastName;
		this.username  = username;

		password = "csadmin";
		email    = "N/A";
	}

	//Initialize with default password
	public Account(String firstName, String lastName, String username, String email)
	{
		this.firstName = firstName;
		this.lastName  = lastName;
		this.email     = email;
		this.username  = username;

		password = "csadmin";
	}

	//Initialize.
	public Account(String firstName, String lastName, String email, String username, String password)
	{
		this.firstName = firstName;
		this.lastName  = lastName;
		this.email     = email;
		this.username  = username;
		this.password  = password;
	}

	//--------------------------------------------//
	//                Comparators                 //
	//--------------------------------------------//

	//Compare Accounts by First Name
	public static Comparator<Account> byFirstName()
	{
		return new Comparator<Account>()
		{
			@Override
			public int compare(Account acct1, Account acct2)
			{
				return acct1.getFirstName().compareToIgnoreCase(acct2.getFirstName());
			}
		};
	}

	//Compare Accounts by Last Name
	public static Comparator<Account> byLastName()
	{
		return new Comparator<Account>()
		{
			@Override
			public int compare(Account acct1, Account acct2)
			{
				return acct1.getLastName().compareToIgnoreCase(acct2.getLastName());
			}
		};
	}

	@Override
	public int compareTo(Account acct)
	{
		throw new UnsupportedOperationException();
	}

	//--------------------------------------------//
	//             Setters & Getters              //
	//--------------------------------------------//
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public String getName()
	{
		return firstName + " " + lastName;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getEmail()
	{
		return email;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getUsername()
	{
		return username;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getPassword()
	{
		return password;
	}

	//--------------------------------------------//
	//             		Other		              //
	//--------------------------------------------//

	public String toString()
	{
		return firstName + "," + lastName + "," + email + "," + username + "," + password;
	}

	//Unit Testing
	public static void main(String[] args)
	{
		System.out.println("Starting Program\n");

		Account[] acctArr = new Account[5];

		acctArr[0] = new Account("Robyn", "Berg");
		acctArr[1] = new Account("Trish", "Duce");
		acctArr[2] = new Account("Michael", "Cassens");
		acctArr[3] = new Account("Travis", "Wheeler");
		acctArr[4] = new Account("Adam", "McManigal");

		//Print accounts in natural order
		System.out.println("Array of Accounts Created with the Following Elements:");
		System.out.println("[First-Name],[Last-Name],[Email],[Username],[Password]\n\n");
		for (Account acct : acctArr)
		{
			System.out.println(acct.toString() + "\n");
		}

		//Sort accounts by first-name and print.
		System.out.println("\n\n______________________");
		System.out.println("Sorting by First-Name:\n");
		Arrays.sort(acctArr, Account.byFirstName());
		for (Account acct : acctArr)
		{
			System.out.println(acct.toString() + "\n");
		}

		//Sort accounts by last-name and print.
		System.out.println("\n\n_____________________");
		System.out.println("Sorting by Last-Name:\n");
		Arrays.sort(acctArr, Account.byLastName());
		for (Account acct : acctArr)
		{
			System.out.println(acct.toString() + "\n");
		}

		System.out.println("\n\nEnding Program\n\n");
	}
}
