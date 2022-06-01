package com.techelevator.controller;

import com.techelevator.dao.PostDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @PostMapping(path="/post")
    public void addPost(@RequestBody Post post){
        postDao.createPost(post);
    }


}
