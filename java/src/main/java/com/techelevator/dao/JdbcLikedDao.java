package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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
        jdbcTemplate.update(sql, postId, accountId);

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
}
