package com.techelevator.controller;

import com.techelevator.dao.PostDao;
import com.techelevator.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class PostController {
    PostDao postDao;
    UserDao userDao;

    @Autowired
    public PostController(PostDao postDao, UserDao userDao){
        this.postDao = postDao;
        this.userDao = userDao;
    }


}
