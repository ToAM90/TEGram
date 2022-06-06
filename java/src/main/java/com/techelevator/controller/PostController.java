package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.model.Post;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
public class PostController {
    private PostDao postDao;
    private UserDao userDao;
    private AccountDao accountDao;
    private CommentDao commentDao;
    private LikedDao likedDao;
    private FavoritedDao favoritedDao;
    @Autowired
    public PostController(PostDao postDao, UserDao userDao, AccountDao accountDao, CommentDao commentDao, LikedDao likedDao, FavoritedDao favoritedDao){
        this.postDao = postDao;
        this.userDao = userDao;
        this.accountDao = accountDao;
        this.commentDao = commentDao;
        this.likedDao = likedDao;
        this.favoritedDao = favoritedDao;
    }

    @RequestMapping(path="/posts")
    public List<Post> listPosts(Principal principal){
        long userId = userDao.findIdByUsername(principal.getName());
        int accountId = accountDao.getAccountByUserId(userId).getAccountId();
        List<Post> postList = postDao.getAllPost();
        for(Post post : postList){
            post.setComments(commentDao.listComments(post.getPostId()));
            post.setLikesCount(likedDao.getNumLikes(post.getPostId()));
            post.setLiked(likedDao.isLiked(post.getPostId(), accountId));
            post.setFavorited(favoritedDao.isFavorited(post.getPostId(), accountId));
        }
        return postList;
    }

    @RequestMapping(path="/{otherId}/posts")
    public List<Post> listAccountPosts(@PathVariable int otherId, Principal principal){
        List<Post> postList = new ArrayList<>();
        long userId = userDao.findIdByUsername(principal.getName());
        int accountId = accountDao.getAccountByUserId(userId).getAccountId();
        if(accountId != otherId){
            postList = postDao.getPublicPostsByAccountId(otherId);
        } else {
            postList = postDao.getPublicAndPrivatePostsByAccountId(accountId);
        }
        for(Post post : postList){
            post.setComments(commentDao.listComments(post.getPostId()));
            post.setLikesCount(likedDao.getNumLikes(post.getPostId()));
            post.setLiked(likedDao.isLiked(post.getPostId(), accountId));
            post.setFavorited(favoritedDao.isFavorited(post.getPostId(), accountId));
        }
        return postList;
    }

    @RequestMapping(path="/post/{postId}")
    public Post getPost(@PathVariable int postId, Principal principal){
        long userId = userDao.findIdByUsername(principal.getName());
        int accountId = accountDao.getAccountByUserId(userId).getAccountId();
        Post post = postDao.getPost(postId);
        post.setComments(commentDao.listComments(post.getPostId()));
        post.setLikesCount(likedDao.getNumLikes(postId));
        post.setLiked(likedDao.isLiked(post.getPostId(), accountId));
        post.setFavorited(favoritedDao.isFavorited(post.getPostId(), accountId));
        return post;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path="/post")
    public void addPost(@Valid @RequestBody Post post, Principal principal){
        long userId = userDao.findIdByUsername(principal.getName());
        int accountId = accountDao.getAccountByUserId(userId).getAccountId();

        post.setAccountId(accountId);
        post.setPostDate(LocalDateTime.now());

        postDao.createPost(post);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path="/post/{id}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable int id, Principal principal){
        long userId = userDao.findIdByUsername(principal.getName());
        User user = userDao.getUserById(userId);
        int accountId = accountDao.getAccountByUserId(userId).getAccountId();

        Post post = postDao.getPost(id);

        if(accountId == post.getAccountId() || user.getAuthorities().contains("ADMIN")){
            postDao.deletePost(id);
        }

    }



}
