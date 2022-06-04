package com.techelevator.dao;

import com.techelevator.model.Account;

public interface AccountDao {

    void createAccount(long userId, String username);

    Account getAccountByUserId(long userId);

    Account getAccountByAccountId(long accountId);

    void updateAccount(Account account, long userId);


}
