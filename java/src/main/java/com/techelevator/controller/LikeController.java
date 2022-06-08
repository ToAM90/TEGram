package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class LikeController {

    private PostDao postDao;
    private UserDao userDao;
    private AccountDao accountDao;
    private LikedDao likedDao;
    private CommentDao commentDao;
    private FavoritedDao favoritedDao;

    @Autowired
    public LikeController(PostDao postDao, UserDao userDao, AccountDao accountDao, LikedDao likedDao, CommentDao commentDao, FavoritedDao favoritedDao) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.accountDao = accountDao;
        this.likedDao = likedDao;
        this.commentDao = commentDao;
        this.favoritedDao = favoritedDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/like/{postId}")
    public void like(@PathVariable int postId, Principal principal) {
        long userId = userDao.findIdByUsername(principal.getName());
        int accountId = accountDao.getAccountByUserId(userId).getAccountId();
        likedDao.like(postId, accountId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/like/{postId}", method = RequestMethod.DELETE)
    public void unlike(@PathVariable int postId, Principal principal) {
        long userId = userDao.findIdByUsername(principal.getName());
        int accountId = accountDao.getAccountByUserId(userId).getAccountId();
        likedDao.unlike(postId, accountId);
    }

    @RequestMapping(path = "/liked")
    public List<Post> listLikedPosts(Principal principal) {
        long userId = userDao.findIdByUsername(principal.getName());
        int accountId = accountDao.getAccountByUserId(userId).getAccountId();
        List<Post> postList = likedDao.getListOfLikedPostsByAccountId(accountId);
        for(Post post : postList){
            post.setComments(commentDao.listComments(post.getPostId()));
            post.setLikesCount(likedDao.getNumLikes(post.getPostId()));
            post.setLiked(likedDao.isLiked(post.getPostId(), accountId));
            post.setFavorited(favoritedDao.isFavorited(post.getPostId(), accountId));
        }
        return postList;
    }


}
