package com.techelevator.dao;

public interface LikedDao {

    boolean isLiked(int postId, int accountId);

    void like(int postId, int accountId);

    void unlike(int postId, int accountId);

    int getNumLikes(int postId);
}
