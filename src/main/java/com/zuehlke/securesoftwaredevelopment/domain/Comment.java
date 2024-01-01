package com.zuehlke.securesoftwaredevelopment.domain;

public class Comment {
    private int giftId;
    private Integer userId;
    private String comment;

    public Comment() {
    }

    public Comment(int giftId, Integer userId, String comment) {
        this.giftId = giftId;
        this.userId = userId;
        this.comment = comment;
    }

    public int getGiftId() {
        return giftId;
    }

    public void setGiftId(int giftId) {
        this.giftId = giftId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
