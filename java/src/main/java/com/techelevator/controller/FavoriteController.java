package com.techelevator.controller;

import com.techelevator.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

public class FavoriteController {

    @RestController
    @CrossOrigin
    public class PostController {
        private PostDao postDao;
        private UserDao userDao;
        private AccountDao accountDao;
        private FavoritedDao favoritedDao;

        @Autowired
        public PostController(PostDao postDao, UserDao userDao, AccountDao accountDao, FavoritedDao favoritedDao){
            this.postDao = postDao;
            this.userDao = userDao;
            this.accountDao = accountDao;
            this.favoritedDao = favoritedDao;
        }

        // check if favorited
        // get favorites
        // add favorite
        // remove favorite


}
}
