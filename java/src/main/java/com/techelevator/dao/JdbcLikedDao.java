package com.techelevator.dao;

import com.techelevator.model.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcLikedDao implements LikedDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcLikedDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean isLiked(int postId, int accountId) {
        String sql = "SELECT COUNT(*) FROM likes WHERE post_id = ? AND account_id = ?";
        Integer liked = jdbcTemplate.queryForObject(sql, Integer.class, postId, accountId);
        return liked == 1;
    }

    @Override
    public void like(int postId, int accountId) {
        String sql = "INSERT INTO likes (account_id, post_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, accountId, postId);

    }

    @Override
    public void unlike(int postId, int accountId) {
        String sql = "DELETE FROM likes WHERE post_id = ? AND account_id = ?";
        jdbcTemplate.update(sql, postId, accountId);

    }

    @Override
    public int getNumLikes(int postId) {
        String sql = "SELECT COUNT(*) FROM likes WHERE post_id = ?";
        Integer likes = jdbcTemplate.queryForObject(sql, Integer.class, postId);
        return likes;
    }

    @Override
    public List<Post> getListOfLikedPostsByAccountId(int accountId){
        List<Post> favoritePosts = new ArrayList<>();
        String sql = "SELECT posts.post_id, posts.account_id, posts.img, posts.caption, posts.post_date, posts.privated FROM likes\n" +
                "INNER JOIN posts ON likes.post_id = posts.post_id\n" +
                "INNER JOIN accounts ON likes.account_id = accounts.account_id\n" +
                "WHERE accounts.account_id = 1\n" +
                "ORDER BY posts.post_date DESC;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId);

        while (results.next()) {
            Post post = MapRowToPost(results);
            favoritePosts.add(post);
        }
        return favoritePosts;
    }

    private Post MapRowToPost(SqlRowSet results) {
        Post post = new Post();

        post.setPostId(results.getInt("post_id"));
        post.setAccountId(results.getInt("account_id"));
        post.setImg(results.getString("img"));
        post.setPostDate(results.getTimestamp("post_date").toLocalDateTime());
        post.setPrivated(results.getBoolean("privated"));
        post.setCaption(results.getString("caption"));

        return post;
    }
}
