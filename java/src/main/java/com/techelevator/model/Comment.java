package com.techelevator.model;

public class Comment {

    private int commentId;
    private int postId;
    private int accountId;
    private String commentText;
    private String username;

    public Comment(){}

    public Comment(int commentId, int postId, int accountId, String commentText, String username) {
        this.commentId = commentId;
        this.postId = postId;
        this.accountId = accountId;
        this.commentText = commentText;
        this.username = username;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

