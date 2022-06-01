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
        String sql = "INSERT INTO posts(account_id, img, caption, privated) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, post.getAccountId(), post.getImg(), post.getCaption(), post.isPrivated());
    }

    @Override
    public Post getPostbyPostId(int postId) {
        Post post = new Post();
        String sql = "SELECT img, caption, post_date, privated FROM posts WHERE post_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, postId);

        if(results.next()){
            post = MapRowToPost(results);}
        return post;
    }

    @Override
    public List<Post> getPostsbyAccountId(int accountId) {
        List<Post> postList = new ArrayList<>();
        String sql = "SELECT img, caption, post_date, privated FROM posts\n" +
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
        String sql = "SELECT img, caption, post_date, privated FROM posts\n" +
                "WHERE privated IS TRUE ORDER BY post_date DESC";

        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql);

        while(sqlRowSet.next()){
            Post post = MapRowToPost(sqlRowSet);
            postList.add(post);
        }
        return postList;
    }

    private Post MapRowToPost(SqlRowSet results){
        Post post = new Post();

        post.setAccountId(results.getInt("account_id"));
        post.setImg(results.getString("img"));
        post.setCaption(results.getString("caption"));
//        also commenting this out to run front end :)
//        post.setPostDate(results.getTimestamp("post_date").toLocalDateTime());
        post.setPrivated(results.getBoolean("privated"));

        return post;
    }
}
