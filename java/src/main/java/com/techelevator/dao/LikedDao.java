package com.techelevator.dao;

import com.techelevator.model.Post;

import java.util.List;

public interface LikedDao {

    boolean isLiked(int postId, int accountId);

    void like(int postId, int accountId);

    void unlike(int postId, int accountId);

    int getNumLikes(int postId);

    List<Post> getListOfLikedPostsByAccountId(int accountId);

}
