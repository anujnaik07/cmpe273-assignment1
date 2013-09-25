package com.assignment.restapp.dto;


import com.assignment.restapp.domainclass.Book;

public class BookDto extends LinksDto {
    private Book book;

    /**
     * @param book
     */
    
    public BookDto(Book book) {
	super();
	this.book = book;
	System.out.println("Book Response"+ book.getIsbn());
    }

    /**
     * @return the book
     */
    public Book getBook() {
    	System.out.println("Book Response"+ book.getTitle());
	return book;
    }

    /**
     * @param book
     *            the book to set
     */
    public void setBook(Book book) {
	this.book = book;
    }
}
