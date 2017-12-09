package com.i4m1s1.specmed.dto;

import com.i4m1s1.specmed.core.Opinion;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class NewOpinionDTO {
    private String comment;
    private int stars;
    private String visitId;

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

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public Opinion createOpinion() {
        Opinion opinion = new Opinion();
        opinion.setComment(this.comment);
        opinion.setStars(this.stars);
        return opinion;
    }

}
