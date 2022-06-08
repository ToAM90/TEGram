package com.techelevator.controller;

import com.techelevator.dao.AccountDao;
import com.techelevator.dao.FollowDao;
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
    FollowDao followDao;

    @Autowired
    public AccountController(AccountDao accountDao, UserDao userDao,  FollowDao followDao){
        this.accountDao = accountDao;
        this.userDao = userDao;
        this.followDao = followDao;
    }

    @RequestMapping(path= "/profile")
    public Account getAccountSelf(Principal principal){
        int userId = userDao.findIdByUsername(principal.getName());
        Account account = accountDao.getAccountByUserId(userId);
        account.setNumFollowers(followDao.countFollowers(account.getAccountId()));
        account.setNumFollowing(followDao.countFollowing(account.getAccountId()));
        return account;

    }

    @RequestMapping("/profile/{accountId}")
    public Account getAccountByAccountId(@PathVariable int accountId, Principal principal){
        long userId = userDao.findIdByUsername(principal.getName());
        int selfId = accountDao.getAccountByUserId(userId).getAccountId();
        Account account = accountDao.getAccountByAccountId(accountId);
        account.setNumFollowers(followDao.countFollowers(accountId));
        account.setNumFollowing(followDao.countFollowing(accountId));
        account.setFollowed(followDao.isFollowed(selfId, accountId));


        return account;

    }

    @PutMapping("/profile")
    public void updateAccount(@RequestBody Account account, Principal principal){
        int userId = userDao.findIdByUsername(principal.getName());
        accountDao.updateAccount(account, userId);
    }

}
