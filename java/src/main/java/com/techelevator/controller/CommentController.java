package com.techelevator.controller;

import com.techelevator.dao.AccountDao;
import com.techelevator.dao.CommentDao;
import com.techelevator.dao.PostDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Comment;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
public class CommentController {
    private PostDao postDao;
    private UserDao userDao;
    private AccountDao accountDao;
    private CommentDao commentDao;

    @Autowired
    public CommentController(PostDao postDao, UserDao userDao, AccountDao accountDao, CommentDao commentDao){
        this.postDao = postDao;
        this.userDao = userDao;
        this.accountDao = accountDao;
        this.commentDao = commentDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/comments", method = RequestMethod.POST)
        public void submitComment(@RequestBody Comment comment, Principal principal){
        long userId = userDao.findIdByUsername(principal.getName());
        int accountId = accountDao.getAccountByUserId(userId).getAccountId();
        comment.setAccountId(accountId);
        commentDao.submitComment(comment);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path="/comments/{commentId}",  method = RequestMethod.DELETE)
        public void deleteComment(@PathVariable int commentId, Principal principal){
        long userId = userDao.findIdByUsername(principal.getName());
        User user = userDao.getUserById(userId);
        int accountId = accountDao.getAccountByUserId(userId).getAccountId();

        Comment comment = commentDao.getComment(commentId);

        if(accountId == comment.getAccountId() || user.getAuthorities().contains("ADMIN")){
            commentDao.deleteComment(commentId);
        }
    }

}