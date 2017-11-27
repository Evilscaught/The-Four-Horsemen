package application.controller;

import java.util.ArrayList;

import application.Account;

public class UserController {
    private ArrayList<Account> accountArr;

    public UserController(ArrayList<Account> acts) {
        this.accountArr = acts;
    }

    public boolean verifyUser(String username, String password) {
        for (Account act : accountArr) {
            if (act.getUsername().equals(username) &&
                act.getPassword().equals(password))
                return true;
        }
        return false;
    }

}
