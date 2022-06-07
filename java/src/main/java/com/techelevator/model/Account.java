package com.techelevator.model;

public class Account {

    private int accountId;
    private int userId;
    private String displayName;
    private String biography;
    private String profileImg;


    public Account(){

    }

    public Account(int accountId, int userId, String displayName, String biography, String profileImg) {
        this.accountId = accountId;
        this.userId = userId;
        this.displayName = displayName;
        this.biography = biography;
        this.profileImg = profileImg;
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
}
