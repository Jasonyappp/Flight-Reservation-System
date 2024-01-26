package com.example.finalasignment;

public class ReviewData {
    private String userName;
    private float rating;
    private String reviewText;

    public ReviewData(String userName, float rating, String reviewText) {
        this.userName = userName;
        this.rating = rating;
        this.reviewText = reviewText;
    }

    public String getUserName() {
        return userName;
    }

    public float getRating() {
        return rating;
    }

    public String getReviewText() {
        return reviewText;
    }
}
