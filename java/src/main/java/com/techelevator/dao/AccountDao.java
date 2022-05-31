package com.techelevator.dao;

import com.techelevator.model.Account;

public interface AccountDao {

    void createAccount(int userId);

    Account getAccount(int userId);

    void updateAccount(Account account, int userId);


}
