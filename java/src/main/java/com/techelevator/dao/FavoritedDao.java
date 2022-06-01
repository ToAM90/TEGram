package com.techelevator.dao;

import com.techelevator.model.Post;

import java.util.List;

public interface FavoritedDao {

    boolean isFavorited(int postId, int accountId);

    void favorite(int postId, int accountId);

    void unfavorite(int postId, int accountId);

    List<Post> getFavorites(int accountId);
}
