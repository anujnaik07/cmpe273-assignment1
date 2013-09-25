package com.assignment.restapp.dto;

import java.util.List;

import com.assignment.restapp.domainclass.Author;


public class AuthorDto extends LinksDto{
	
	private Author author;
	
    private List<Author> authorList;
    /**
     * @param Author
     */
    
    public AuthorDto(Author author) {
	super();
	this.author = author;
    }
    
    public AuthorDto(List<Author> authorlist)
    {
      super();    
      this.authorList = authorlist;
    	
    }
    
    

    /**
     * @return the Author
     */
    public Author getAuthor() {
	return author;
    }

    /**
     * @param Author
     *            the Author to set
     */
    public void setAuthor(Author author) {
	this.author = author;
    }

}
