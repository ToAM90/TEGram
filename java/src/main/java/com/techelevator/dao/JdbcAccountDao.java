package com.techelevator.dao;

import com.techelevator.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcAccountDao implements AccountDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createAccount(long userId, String username) {
        String sql = "INSERT INTO accounts (user_id, display_name) VALUES(?, ?)";
        jdbcTemplate.update(sql, userId, username);
    }

    @Override
    public Account getAccount(long userId) {
        Account account = new Account();
        String sql = "SELECT account_id, user_id, profile_img, display_name, biography FROM accounts WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

        if(results.next()){
            account = MapRowToAccount(results);}

        return account;
    }

    @Override
    public void updateAccount(Account account, long userId) {
        String sql = "UPDATE accounts SET biography = ?, profile_img = ?, display_name = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, account.getBiography(), account.getProfileImg(), account.getDisplayName(), userId);
    }

    private Account MapRowToAccount(SqlRowSet results){
        Account account = new Account();

        account.setAccountID(results.getInt("account_id"));
        account.setUserID(results.getInt("user_id"));
        account.setDisplayName(results.getString("display_name"));
        account.setBiography(results.getString("biography"));
        account.setProfileImg(results.getString("profile_img"));

        return account;
    }
}
