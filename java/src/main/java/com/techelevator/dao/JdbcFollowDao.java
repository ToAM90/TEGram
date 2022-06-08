package com.techelevator.dao;

import com.techelevator.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        Integer followers = jdbcTemplate.queryForObject(sql, Integer.class, accountId);
        return followers;
    }

    @Override
    public int countFollowing(int accountId) {
        String sql = "SELECT COUNT (*) FROM following WHERE account_id_follower = ?";
        Integer following = jdbcTemplate.queryForObject(sql, Integer.class, accountId);
        return following;
    }

    @Override
    public boolean isFollowed(int selfId, int followingId) {
        String sql = "SELECT COUNT(*) FROM following WHERE account_id_follower = ? AND account_id_following = ?";
        Integer following = jdbcTemplate.queryForObject(sql, Integer.class, selfId, followingId);
        return following == 1;
    }

    @Override
    public List<Account> listFollowing(int accountId) {
        List<Account> accountList = new ArrayList<>();
        String sql = "SELECT * FROM following JOIN accounts ON account_id_following = account_id WHERE account_id_follower = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId);

        while(results.next()){
            Account account = MapRowToAccount(results);
            accountList.add(account);}

        return accountList;
    }

    @Override
    public List<Account> listFollowers(int accountId) {
        List<Account> accountList = new ArrayList<>();
        String sql = "SELECT * FROM following JOIN accounts ON account_id_follower = account_id WHERE account_id_following = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId);

        while(results.next()){
            Account account = MapRowToAccount(results);
            accountList.add(account);}

        return accountList;
    }

    private Account MapRowToAccount(SqlRowSet results){
        Account account = new Account();

        account.setAccountId(results.getInt("account_id"));
        account.setUserId(results.getInt("user_id"));
        account.setDisplayName(results.getString("display_name"));
        account.setBiography(results.getString("biography"));
        account.setProfileImg(results.getString("profile_img"));

        return account;
    }
}
