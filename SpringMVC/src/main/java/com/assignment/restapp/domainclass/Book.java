package com.assignment.restapp.domainclass;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Book {
    private long isbn;
    private String title;
    @JsonProperty ("num-pages") private int pages;
    
    @JsonProperty ("publication-date") private String publicationDate;
    private String status;
    //private String authors;
    private String language;
    
    private List<Author> authors ;
    
    private List<Review> review = new ArrayList<Review>();
    
	public long getIsbn() {
		return isbn;
	}
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	public List<Review> getReview() {
		return review;
	}
	public void setReview(Review review) {
		this.review.add(review);
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	
	/*public List<Author> getAuthors() {
		return authors;
	}*/
	/*public void setAuthors(String[] authors) {
		this.authors = authors;
	}*/
	
	

    // add more fields here

    
}
