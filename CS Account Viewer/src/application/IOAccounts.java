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
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class IOAccounts 
{
    private int accountArrPos;

    private InputStream accountsPath;
    private Account[] accountArr;
	
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
}