package com.techelevator.dao;

import com.techelevator.model.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcFavoritedDao implements FavoritedDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcFavoritedDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public boolean isFavorited(int postId, int accountId) {
        String sql = "SELECT COUNT(*) FROM favorites WHERE post_id = ? AND account_id = ?";
        Integer favorited = jdbcTemplate.queryForObject(sql, Integer.class, postId, accountId);
        return favorited == 1;
    }

    @Override
    public void favorite(int postId, int accountId) {
        String sql = "INSERT INTO favorites (post_id, account_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, postId, accountId);

    }

    @Override
    public void unfavorite(int postId, int accountId) {
        String sql = "DELETE FROM favorites WHERE post_id = ? AND account_id = ?";
        jdbcTemplate.update(sql, postId, accountId);

    }

    @Override
    public List<Post> getFavorites(int accountId) {
        List<Post> favoritePosts = new ArrayList<>();
        String sql = "SELECT img, caption, post_date, privated FROM posts p\n" +
                "JOIN favorites f ON f.post_id = p.post_id" +
                "JOIN accounts a ON a.account_id = p.account_id" +
                "WHERE f.account_id = ? ORDER BY post_date DESC";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId);

        while(results.next()){
            Post post = MapRowToPost(results);
            favoritePosts.add(post);
        }
        return favoritePosts;
    }

    private Post MapRowToPost(SqlRowSet results){
        Post post = new Post();

        post.setAccountId(results.getInt("account_id"));
        post.setImg(results.getString("img"));
        post.setCaption(results.getString("caption"));
        post.setPostDate(results.getTimestamp("post_date").toLocalDateTime());
        post.setPrivated(results.getBoolean("privated"));

        return post;
    }
}
