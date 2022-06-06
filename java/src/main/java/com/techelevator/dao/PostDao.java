package com.techelevator.dao;

import com.techelevator.model.Post;

import java.util.List;

public interface PostDao {

void createPost(Post post);

Post getPost(int postId);

List<Post> getPublicPostsByAccountId(int accountId);

List<Post> getPublicAndPrivatePostsByAccountId(int accountId);

List<Post> getAllPost();

void deletePost(int postId);
}
