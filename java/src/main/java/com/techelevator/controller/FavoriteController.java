package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class FavoriteController {

    private PostDao postDao;
    private UserDao userDao;
    private AccountDao accountDao;
    private FavoritedDao favoritedDao;
    private CommentDao commentDao;
    private LikedDao likedDao;

    @Autowired
    public FavoriteController(PostDao postDao, UserDao userDao, AccountDao accountDao, FavoritedDao favoritedDao, CommentDao commentDao, LikedDao likedDao) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.accountDao = accountDao;
        this.favoritedDao = favoritedDao;
        this.commentDao = commentDao;
        this.likedDao = likedDao;
    }

    @RequestMapping(path = "/favorite/{postId}")
    public void favorite(@PathVariable int postId, Principal principal) {
        long userId = userDao.findIdByUsername(principal.getName());
        int accountId = accountDao.getAccountByUserId(userId).getAccountId();
        favoritedDao.favorite(postId, accountId);
    }

    @RequestMapping(path = "/favorite/{postId}", method = RequestMethod.DELETE)
    public void unfavorite(@PathVariable int postId, Principal principal) {
        long userId = userDao.findIdByUsername(principal.getName());
        int accountId = accountDao.getAccountByUserId(userId).getAccountId();
        favoritedDao.unfavorite(postId, accountId);
    }

    @RequestMapping(path = "/favorites")
    public List<Post> listFavoritePosts(Principal principal) {
        long userId = userDao.findIdByUsername(principal.getName());
        int accountId = accountDao.getAccountByUserId(userId).getAccountId();
        List<Post> postList = favoritedDao.getFavorites(accountId);
        for(Post post : postList){
            post.setComments(commentDao.listComments(post.getPostId()));
            post.setLikesCount(likedDao.getNumLikes(post.getPostId()));
            post.setLiked(likedDao.isLiked(post.getPostId(), accountId));
            post.setFavorited(favoritedDao.isFavorited(post.getPostId(), accountId));
        }
        return postList;
    }
}

