package com.techelevator.controller;

import com.techelevator.dao.AccountDao;
import com.techelevator.dao.PostDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@RestController
@CrossOrigin
public class PostController {
    private PostDao postDao;
    private UserDao userDao;
    private AccountDao accountDao;

    @Autowired
    public PostController(PostDao postDao, UserDao userDao, AccountDao accountDao){
        this.postDao = postDao;
        this.userDao = userDao;
        this.accountDao = accountDao;
    }

    @RequestMapping(path="/posts")
    public List<Post> listPosts(){
        List<Post> postList = postDao.getAllPost();
        return postList;
    }

    @RequestMapping(path="/posts/{id}")
    public List<Post> listAccountPosts(@PathVariable int id){
        List<Post> postList = postDao.getPostsbyAccountId(id);
        return postList;
    }

    @RequestMapping(path="/post/{id}")
    public Post getPost(@PathVariable int id){
        Post post = postDao.getPostbyPostId(id);
        return post;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path="/post")
    public void addPost(@Valid @RequestBody Post post, Principal principal){
        System.out.println(principal.getName());
        int id = userDao.findIdByUsername(principal.getName());
        int accountId = accountDao.getAccount(id).getAccountID();
        System.out.println(accountId);
        post.setAccountId(accountId);
        postDao.createPost(post);
    }


}
