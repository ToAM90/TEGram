package com.techelevator.controller;

import com.techelevator.dao.AccountDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Account;
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

        return accountDao.getAccountByUserId(userId);
    }

    @RequestMapping("/profile/{accountId}")
    public Account getAccountByAccountId(@PathVariable int accountId){
        return accountDao.getAccountByAccountId(accountId);
    }

    @PostMapping("/profile")
    public void updateAccount(@RequestBody Account account, Principal principal){
        int userId = userDao.findIdByUsername(principal.getName());
        accountDao.updateAccount(account, userId);
    }

}
