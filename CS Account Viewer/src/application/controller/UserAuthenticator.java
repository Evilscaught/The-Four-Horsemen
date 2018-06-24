/******************************************************************************
 *  Compilation:  javac UserController.java
 *  Execution:    java  UserController
 *  Dependencies:
 *
 *  @author(s)      Jack Cummings
 *  @version        0.0.0
 *  @group          The Four Horsemen
 *  @copyright      None
 *  @date_created   Monday, November 27th, 2017 @3:24 p.m.
 *
 * 
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
 *  % java UserController
 *
 ******************************************************************************/

package application.controller;

import application.Account;
import application.IOAccounts;

public class UserAuthenticator 
{
    private IOAccounts ioAcct;
    private String curUser = "";
    private String curUserFirstLast = "";
    private Boolean isAdmin = false;
    
    public UserAuthenticator(IOAccounts acts) 
    {
        this.ioAcct = acts;
    }

    public boolean verifyUser(String username, String password) 
    {
        for (Account act : ioAcct.getAccounts()) 
        {
            if (act.getUsername().equals(username) &&
                act.getPassword().equals(password)) {
            	curUserFirstLast = act.getName();
                return true;
            }   	
        }
        return false;
    }
    
    public String getCurUser() 
    {
        return curUser;
    }

    public void setCurUser(String curUser) 
    {
        this.curUser = curUser;
        this.isAdmin = this.ioAcct.getAccount(curUser).getAdmin();
    }
    
    public String getCurUserFirstLast() 
    {
    	return curUserFirstLast;
    }
    
    public Boolean isAdmin() 
    {
        return isAdmin;
    }
}