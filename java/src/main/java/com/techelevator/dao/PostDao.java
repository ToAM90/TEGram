package com.techelevator.dao;

import com.techelevator.model.Post;

import java.util.List;

public interface PostDao {

void createPost(Post post);

Post getPostbyPostId(int postId);

List<Post> getPostsbyAccountId(int accountId);

List<Post> getAllPost();
}
