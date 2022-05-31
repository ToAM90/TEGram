package com.techelevator.controller;

import com.techelevator.dao.AccountDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Account;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
public class AccountController {
    AccountDao accountDao;
    UserDao userDao;

    @Autowired
    public AccountController(AccountDao accountDao, UserDao userDao){
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    @RequestMapping(path= "/profile")
    public Account getAccountSelf(Principal principal){
        int userId = userDao.findIdByUsername(principal.getName());

        return accountDao.getAccount(userId);
    }

    @RequestMapping("/profile/{username}")
    public Account getAccountOther(@PathVariable String username){
        int userId = userDao.findIdByUsername(username);

        return accountDao.getAccount(userId);
    }

    @PostMapping("/profile")
    public void updateAccount(@RequestBody Account account, Principal principal){
        int userId = userDao.findIdByUsername(principal.getName());
        accountDao.updateAccount(account, userId);
    }

}
