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

import java.util.ArrayList;

import application.Account;

public class UserController 
{
    private ArrayList<Account> accountArr;
    private String curUser = "";
    
    public UserController(ArrayList<Account> acts) 
    {
        this.accountArr = acts;
    }

    public boolean verifyUser(String username, String password) 
    {
        for (Account act : accountArr) 
        {
            if (act.getUsername().equals(username) &&
                act.getPassword().equals(password))
                return true;
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
    }
}
