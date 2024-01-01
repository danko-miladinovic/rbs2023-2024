package com.zuehlke.securesoftwaredevelopment.domain;

public class Rating {

    private int giftId;

    private int userId;

    private int rating;

    public Rating(int giftId, int userId, int rating) {
        this.giftId = giftId;
        this.userId = userId;
        this.rating = rating;
    }

    public int getGiftId() {
        return giftId;
    }

    public void setGiftId(int giftId) {
        this.giftId = giftId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
