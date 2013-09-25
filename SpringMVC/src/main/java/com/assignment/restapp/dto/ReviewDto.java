package com.assignment.restapp.dto;

import com.assignment.restapp.domainclass.*;

public class ReviewDto extends LinksDto {
    private Review review;

    /**
     * @param book
     */
    
    public ReviewDto(Review review) {
	super();
	this.review = review;
    }

    /**
     * @return the book
     */
    public Review getReview() {
	return review;
    }

    /**
     * @param review
     *            the review to set
     */
    public void setReview(Review review) {
	this.review = review;
    }
}
