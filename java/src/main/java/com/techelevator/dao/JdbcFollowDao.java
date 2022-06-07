package com.techelevator.dao;

import com.techelevator.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcFollowDao implements FollowDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcFollowDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void follow(int selfId, int followingId) {
        String sql = "INSERT INTO following (account_id_follower, account_id_following) VALUES (?, ?)";
        jdbcTemplate.update(sql, selfId, followingId);

    }

    @Override
    public void unFollow(int selfId, int followingId) {
        String sql = "DELETE FROM following WHERE account_id_follower = ? AND account_id_following = ?";
        jdbcTemplate.update(sql, selfId,followingId);

    }

    @Override
    public int countFollowers(int accountId) {
        String sql = "SELECT COUNT (*) FROM following WHERE account_id_following = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, accountId);
    }

    @Override
    public int countFollowing(int accountId) {
        String sql = "SELECT COUNT (*) FROM following WHERE account_id_follower = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, accountId);
    }

    @Override
    public boolean isFollowed(int selfId, int followingId) {
        String sql = "SELECT COUNT(*) FROM following WHERE account_id_follower = ? AND account_id_following = ?";
        Integer following = jdbcTemplate.queryForObject(sql, Integer.class, selfId, followingId);
        return following == 1;
    }

    @Override
    public List<Account> listFollowing(int accountId) {
        return null;
    }

    @Override
    public List<Account> listFollowers(int accountId) {
        return null;
    }
}
