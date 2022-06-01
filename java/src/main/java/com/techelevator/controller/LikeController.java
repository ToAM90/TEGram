package com.techelevator.controller;

import com.techelevator.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

public class LikeController {

    @RestController
    @CrossOrigin
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
    }

    // check if liked
    // add like
    // remove like
    // get num of likes
}
