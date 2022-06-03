package com.techelevator.controller;

import com.techelevator.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
public class LikeController {

//
    public class PostController {
        private PostDao postDao;
        private UserDao userDao;
        private AccountDao accountDao;
        private LikedDao likedDao;

        @Autowired
        public PostController(PostDao postDao, UserDao userDao, AccountDao accountDao, LikedDao likedDao){
            this.postDao = postDao;
            this.userDao = userDao;
            this.accountDao = accountDao;
            this.likedDao = likedDao;
        }

        @RequestMapping(path="/like/{postId}")
        public void like(@PathVariable int postId, Principal principal){
            long userId = userDao.findIdByUsername(principal.getName());
            int accountId = accountDao.getAccount(userId).getAccountID();
            likedDao.like(postId, accountId);
        }

        @RequestMapping(path="/like/{postId}", method = RequestMethod.DELETE)
        public void unlike(@PathVariable int postId, Principal principal){
            long userId = userDao.findIdByUsername(principal.getName());
            int accountId = accountDao.getAccount(userId).getAccountID();
            likedDao.unlike(postId, accountId);
        }

        @RequestMapping(path="/like/{postId}")
        public boolean likeStatus(@PathVariable int postId, Principal principal){
            long userId = userDao.findIdByUsername(principal.getName());
            int accountId = accountDao.getAccount(userId).getAccountID();
            return(likedDao.isLiked(postId, accountId));
        }
    }

}
