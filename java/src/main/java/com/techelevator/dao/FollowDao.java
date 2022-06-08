package com.techelevator.dao;

import com.techelevator.model.Account;

import java.util.List;

public interface FollowDao {

    void follow (int selfId, int followingId);

    void unFollow (int selfId, int followingId);

    int countFollowers(int accountId);

    int countFollowing(int accountId);

    boolean isFollowed(int selfId, int followingId);

    List<Account> listFollowing (int accountId);

    List<Account> listFollowers (int accountId);
}
