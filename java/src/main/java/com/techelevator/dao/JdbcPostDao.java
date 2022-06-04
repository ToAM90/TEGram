package com.techelevator.dao;

import com.techelevator.model.Account;
import com.techelevator.model.Post;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPostDao implements PostDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcPostDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public void createPost(Post post) {
        String sql = "INSERT INTO posts(account_id, img, caption, privated, post_date) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, post.getAccountId(), post.getImg(), post.getCaption(), post.isPrivated(), post.getPostDate());
    }

    @Override
    public Post getPost(int postId) {
        Post post = new Post();
        String sql = "SELECT post_id, account_id, img, caption, post_date, privated FROM posts WHERE post_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, postId);

        if(results.next()){
            post = MapRowToPost(results);}
        return post;
    }

    @Override
    public List<Post> getPostsByAccountId(int accountId) {
        List<Post> postList = new ArrayList<>();
        String sql = "SELECT post_id, account_id, img, caption, post_date, privated FROM posts\n" +
                "WHERE account_id = ?\n" +
                "ORDER BY post_date DESC";

        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql, accountId);

        while(sqlRowSet.next()){
            Post post = MapRowToPost(sqlRowSet);
            postList.add(post);
        }
        return postList;
    }

    @Override
    public List<Post> getAllPost() {
        List<Post> postList = new ArrayList<>();
        String sql = "SELECT post_id, account_id, img, caption, post_date, privated FROM posts\n" +
                "WHERE privated IS FALSE ORDER BY post_date DESC";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while(results.next()){
            Post post = MapRowToPost(results);
            postList.add(post);
        }
        return postList;
    }

    @Override
    public void deletePost(int postId) {
        String sql = "DELETE FROM posts WHERE post_id = ?";
        jdbcTemplate.update(sql);
    }

    private Post MapRowToPost(SqlRowSet results){
        Post post = new Post();

        post.setPostId(results.getInt("post_id"));
        post.setAccountId(results.getInt("account_id"));
        post.setImg(results.getString("img"));
        post.setPostDate(results.getTimestamp("post_date").toLocalDateTime());
        post.setPrivated(results.getBoolean("privated"));

        return post;
    }
}
