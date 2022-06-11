package com.techelevator.dao;

import com.techelevator.model.Account;

import java.util.List;

public interface AccountDao {

    void createAccount(long userId, String username);

    Account getAccountByUserId(long userId);

    Account getAccountByAccountId(long accountId);

    void updateAccount(Account account, long userId);

    public List<Account> listAccounts();


}
