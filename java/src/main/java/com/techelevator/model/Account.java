package com.techelevator.model;

public class Account {

    private int accountID;
    private int userID;
    private String displayName;
    private String biography;
    private String profileImg;


    public Account(){

    }

    public Account(int accountID, int userID, String displayName, String biography, String profileImg) {
        this.accountID = accountID;
        this.userID = userID;
        this.displayName = displayName;
        this.biography = biography;
        this.profileImg = profileImg;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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
