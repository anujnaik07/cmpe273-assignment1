package com.assignment.restapp.util;

import java.util.HashMap;

import com.assignment.restapp.domainclass.Book;

public class BookFactory {
	
	public static HashMap<Long,Book> bookMap = new HashMap<Long,Book>();
	
	public static Book putBook(Long bookId, Book book){
		return bookMap.put(bookId, book);
	}
	
	public static Book getBook(Long bookId){
		return bookMap.get(bookId);
	}
	
	public static void removeBook(Long bookId){
		bookMap.remove(bookId);
	}
}
