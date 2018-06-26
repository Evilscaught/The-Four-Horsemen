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

import java.util.Comparator;

public class Account implements Comparable<Account>
{
    private String firstName; 
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String description;
    private String secQuestion1;
    private String secQuestion2;
    private String secQuestion3;
    private double balance;

    private Boolean admin = false;

    //Constructor:
    public Account(String firstName, String lastName, String username, String password)
    {
        this.firstName = firstName;
        this.lastName  = lastName;
        this.username  = username;
        this.password  = password;
        
        email = "N/A";
        balance = 0.0;
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

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }

    public void setSecurityQuestion1(String secQuestion1)
    {
        this.secQuestion1 = secQuestion1;
    }

    public String getSecurityQuestion1()
    {
        return secQuestion1;
    }

    public void setSecurityQuestion2(String secQuestion2)
    {
        this.secQuestion2 = secQuestion2;
    }

    public String getSecurityQuestion2()
    {
        return secQuestion2;
    }

    public void setSecurityQuestion3(String secQuestion3)
    {
        this.secQuestion3 = secQuestion3;
    }

    public String getSecurityQuestion3()
    {
        return secQuestion3;
    }

    public Boolean getAdmin() {
        return admin;
    }


    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public void setBalance(double total){
        this.balance = total;
    }

    public double getBalance(){
        return balance;
    }
    
    public boolean isAdmin()
    {
    	return admin;
    }

    //--------------------------------------------//
    //             		Other		              //
    //--------------------------------------------//

    public String toString()
    {
        return firstName + "," + lastName + "," + username + "," + password + "," + email + "," + description + "," + secQuestion1 + "," + secQuestion2 + "," + secQuestion3 + "," + admin + ","+ balance;
    }

    //Unit Testing
    public static void main(String[] args)
    {

        
    }
}
