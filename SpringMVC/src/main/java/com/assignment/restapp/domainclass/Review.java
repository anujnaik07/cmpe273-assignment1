package com.assignment.restapp.domainclass;

public class Review {
	private int reviewId;
	private double rating;
	private String comment;
	
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public int hashCode() {
		return getReviewId();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
		{
			return false;
		}
		if (!this.getClass().equals(obj.getClass()))
		{
			return false;
		}
		if (getReviewId() ==((Review)obj).getReviewId())
		{
			return true;
		}
		return false;
	}
}
