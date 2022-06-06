package com.techelevator.dao;

import com.techelevator.model.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcFavoritedDao implements FavoritedDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcFavoritedDao(JdbcTemplate jdbcTemplate) {
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
        String sql = "SELECT posts.img, posts.caption, posts.post_date, posts.privated, posts.account_id, posts.post_id from accounts\n" +
                "INNER JOIN posts ON accounts.account_id = posts.account_id\n" +
                "INNER JOIN favorites ON favorites.post_id = posts.post_id\n" +
                "WHERE favorites.account_id = ?\n" +
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
