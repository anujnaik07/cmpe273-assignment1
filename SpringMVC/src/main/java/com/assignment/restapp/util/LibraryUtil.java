package com.assignment.restapp.util;

public class LibraryUtil {
	
	private static long isbnId=1;
	private static int authorId=1;
	private static int reviewId=1;
	
	public static long getISBNId(){
		return isbnId++;
	}
	
	public static int getIAuthorId(){
		return authorId++;
	}
	
	public static int getReviewId(){
		return reviewId++;
	}

}
