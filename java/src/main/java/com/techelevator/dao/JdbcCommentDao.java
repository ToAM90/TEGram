package com.techelevator.dao;

import com.techelevator.model.Account;
import com.techelevator.model.Comment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCommentDao implements CommentDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcCommentDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Comment getComment(int commentId) {
        Comment comment = new Comment();
        String sql = "SELECT comment_id, c.account_id, post_id, comment_text, display_name FROM comments c JOIN accounts ON c.account_id = a.account_id a  WHERE comment_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, commentId);
        if(results.next()){
            comment = MapRowToComment(results);
        }
        return comment;
    }

    @Override
    public List<Comment> listComments(int postId) {
        List<Comment> commentsList = new ArrayList<>();
        String sql = "SELECT comment_id, c.account_id, post_id, comment_text, display_name FROM comments c JOIN accounts a ON c.account_id = a.account_id WHERE post_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, postId);

        while(results.next()){
            Comment comment = MapRowToComment(results);
            commentsList.add(comment);
        }

        return commentsList;
    }

    @Override
    public void submitComment(Comment comment) {

        String sql = "INSERT INTO comments (account_id, post_id, comment_text) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, comment.getAccountId(), comment.getPostId(), comment.getCommentText());

    }

    @Override
    public void deleteComment(int commentId) {
        String sql = "DELETE FROM comments WHERE comment_id = ?";
        jdbcTemplate.update(sql, commentId);

    }

    private Comment MapRowToComment(SqlRowSet results){
        Comment comment = new Comment();

        comment.setPostId(results.getInt("post_id"));
        comment.setCommentId(results.getInt("comment_id"));
        comment.setAccountId(results.getInt("account_id"));
        comment.setCommentText(results.getString("comment_text"));
        comment.setUsername(results.getString("display_name"));

        return comment;
    }
}
