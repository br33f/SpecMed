package com.i4m1s1.specmed.core;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class Opinion {
    private String comment;
    private int stars; //w teorii będzie zesłownikowane ale jak na razie nie jest

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
