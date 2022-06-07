package com.techelevator.controller;

import com.techelevator.dao.AccountDao;
import com.techelevator.dao.FollowDao;
import com.techelevator.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
public class FollowController {

    private FollowDao followDao;
    private UserDao userDao;
    private AccountDao accountDao;

    @Autowired
    public FollowController(FollowDao followDao, UserDao userDao, AccountDao accountDao) {
        this.followDao = followDao;
        this.userDao = userDao;
        this.accountDao = accountDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/follow/{followingId}", method = RequestMethod.POST)
        public void follow(@PathVariable int followingId, Principal principal){
        long userId = userDao.findIdByUsername(principal.getName());
        int accountId = accountDao.getAccountByUserId(userId).getAccountId();
        followDao.follow(accountId, followingId);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path="/follow/{followingId}", method = RequestMethod.DELETE)
    public void unfollow(@PathVariable int followingId, Principal principal){
        long userId = userDao.findIdByUsername(principal.getName());
        int accountId = accountDao.getAccountByUserId(userId).getAccountId();
        followDao.unFollow(accountId, followingId);
    }




}
