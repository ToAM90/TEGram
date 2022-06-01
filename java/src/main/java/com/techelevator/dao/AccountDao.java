package com.techelevator.dao;

import com.techelevator.model.Account;

public interface AccountDao {

    void createAccount(long userId);

    Account getAccount(long userId);

    void updateAccount(Account account, long userId);


}
