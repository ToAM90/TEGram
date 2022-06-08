package com.techelevator.model;

public class Account {

    private int accountId;
    private int userId;
    private String displayName;
    private String biography;
    private String profileImg;
    private int numFollowers;
    private int numFollowing;
    private boolean followed;


    public Account(){

    }

    public Account(int accountId, int userId, String displayName, String biography, String profileImg, int numFollowers, int numFollowing, boolean followed) {
        this.accountId = accountId;
        this.userId = userId;
        this.displayName = displayName;
        this.biography = biography;
        this.profileImg = profileImg;
        this.numFollowers = numFollowers;
        this.numFollowing = numFollowing;
        this.followed = followed;
    }

        public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public int getNumFollowers() {
        return numFollowers;
    }

    public void setNumFollowers(int numFollowers) {
        this.numFollowers = numFollowers;
    }

    public int getNumFollowing() {
        return numFollowing;
    }

    public void setNumFollowing(int numFollowing) {
        this.numFollowing = numFollowing;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }
}
