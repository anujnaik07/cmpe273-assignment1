package com.assignment.restapp.controller;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assignment.restapp.dto.*;

import com.assignment.restapp.domainclass.*;
import com.assignment.restapp.util.BookFactory;
import com.assignment.restapp.util.LibraryUtil;
@Component
@Controller
@RequestMapping("/v1")
public class LibraryController {
	
	/*
	 * API 1 
	 */
	@RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public LinksDto add() 
	{
		LinksDto links = new LinksDto();
		links.addLink(new LinkDto("create-book", "/books", "POST"));
		return links;
     }
	
	/*
	 * API 2 
	 */
	@RequestMapping(value = "/books" ,method = RequestMethod.POST)
    @ResponseBody
    public LinksDto createBook(@RequestBody Book book)
	{
		long isbnId=LibraryUtil.getISBNId();
		book.setIsbn(isbnId);
		List<Author> alist =book.getAuthors();
		
		for (Author author : alist) 
		{
			 int authorId=LibraryUtil.getIAuthorId();
			 author.setAuthorId(authorId);
		}
		BookFactory.putBook(isbnId, book);
		
		LinksDto linksResponse = new LinksDto();
	    linksResponse.addLink(new LinkDto("view-book", "/books/"+isbnId, "GET"));
	    linksResponse.addLink(new LinkDto("update-book", "/books/"+isbnId, "PUT"));
	    linksResponse.addLink(new LinkDto("delete-book", "/books/"+isbnId, "DELETE"));
	    linksResponse.addLink(new LinkDto("create-review", "/books/"+isbnId+"/reviews", "POST"));
		return linksResponse;		
		
	}
	
	/*
	 * API 3 
	 */
	@RequestMapping(value="books/{isbn}", method = RequestMethod.GET )
	public @ResponseBody BookDto findBookById(@PathVariable("isbn") long isbn) 
	{    
		    
		Book book = BookFactory.getBook(isbn);
		BookDto bookResponse = new BookDto(book);
		
		bookResponse.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(),"GET"));
		bookResponse.addLink(new LinkDto("update-book","/books/" + book.getIsbn(), "PUT"));
		bookResponse.addLink(new LinkDto("delete-book","/books/" + book.getIsbn(), "DELETE"));
		bookResponse.addLink(new LinkDto("create-review","/books/" + book.getIsbn()+"/reviews", "POST"));
		

        List<Review> reviewList = BookFactory.getBook(isbn).getReview();
	    
	    if(!reviewList.isEmpty())
	    {
	    
	    	bookResponse.addLink(new LinkDto("view-all-reviews", "/books/"+book.getIsbn(), "GET"));
	    	
	    }	
		
		return bookResponse;
	    }

	/*
	 * API 4
	 */	
	@RequestMapping(method = RequestMethod.DELETE, value = "books/{id}")
    @ResponseBody
    public LinksDto remove(@PathVariable("id") long id) 
	{
		
		LinksDto linkResponse = new LinksDto();
    	BookFactory.removeBook(id);
    	linkResponse.addLink(new LinkDto("create-book",	"/books/", "POST"));
		return linkResponse;
    }
    
	/*
	 * API 5 
	 */
	@RequestMapping(value = "/books/{isbn}" ,method = RequestMethod.PUT)
    @ResponseBody
    public LinksDto updateBook(@PathVariable("isbn") long isbn,@RequestParam("status") String newvalue)
	{
	    Book book = new Book();
	    book=BookFactory.getBook(isbn);
	    book.setStatus(newvalue);
	    BookFactory.putBook(isbn,book);
		LinksDto linksResponse = new LinksDto();
	    linksResponse.addLink(new LinkDto("view-book", "/books/"+book.getIsbn(), "GET"));
	    linksResponse.addLink(new LinkDto("update-book", "/books/"+book.getIsbn(), "PUT"));
	    linksResponse.addLink(new LinkDto("delete-book", "/books/"+book.getIsbn(), "DELETE"));
	    linksResponse.addLink(new LinkDto("create-review", "/books/"+book.getIsbn()+"/reviews", "POST"));
	    
	    List<Review> reviewList = BookFactory.getBook(isbn).getReview();
	    
	    if(!reviewList.isEmpty())
	    {
	    
	    	linksResponse.addLink(new LinkDto("view-all-reviews", "/books/"+book.getIsbn(), "GET"));
	    	
	    }	
	    
	    
	    return linksResponse;		
	}
	
	/*
	 * API 6
	 */
	@RequestMapping(value = "/books/{id}/reviews" ,method = RequestMethod.POST)
    @ResponseBody
    public LinksDto createReviews(@RequestBody Review review,@PathVariable("id") long id)
	{
		LinksDto links = new LinksDto();
		Book nBook = new Book();
		nBook = BookFactory.getBook(id);
		nBook.setReview(review);
		List<Review> alist =nBook.getReview();
		
		int rId=0; //
		
		for (Review nreview : alist) 
		{
			 if(nreview.getReviewId()==0)
			 {	 
			 int reviewId=LibraryUtil.getReviewId();
			 nreview.setReviewId(reviewId);
			 rId=reviewId;//
			 }
		}
		
		
		BookFactory.putBook(id, nBook);
		links.addLink(new LinkDto("“view-review”", "/books/"+id+"/reviews/"+rId, "GET"));
 	    return links;
	}
	

	/*
	 * API 7 
	 */	
	@RequestMapping(value="/books/{isbn}/reviews/{id}", method = RequestMethod.GET )
	public @ResponseBody ReviewDto findReviewsById(@PathVariable("isbn") long isbn,@PathVariable("id") int id) 
	{    
	
		
		List<Review> reviewList = BookFactory.getBook(isbn).getReview(); 
		Review review=new Review();
		review.setReviewId(id);
		if(reviewList.indexOf(review)!=-1)
		{
			
			review = reviewList.get(reviewList.indexOf(review));
		}
		
		ReviewDto response = new ReviewDto(review); //
		
		response.addLink(new LinkDto( "view-review","/books/"+isbn+"/reviews/"+id,"GET")); //
		
		return response;
		
	}
	
	/*
	 * API 8 
	 */
	@RequestMapping(value="books/{isbn}/reviews", method = RequestMethod.GET )
	public @ResponseBody List<Review> findAllReviews(@PathVariable("isbn") long isbn) 
	{    
		return BookFactory.getBook(isbn).getReview();
	}
	
	/*
	 * API 9 
	 */ 
	@RequestMapping(value=" /books/{isbn}/authors/{id}", method = RequestMethod.GET )
	public @ResponseBody AuthorDto findAuthorById(@PathVariable("isbn") long isbn,@PathVariable("id") int id) 
	{    
		List<Author> authorList=BookFactory.getBook(isbn).getAuthors();
		
		Author auth=new Author();
		auth.setAuthorId(id);
		if(authorList.indexOf(auth)!=-1)
		{
			auth = authorList.get(authorList.indexOf(auth));
		}
		
		AuthorDto authorResponse = new AuthorDto(auth);
		
		authorResponse.addLink(new LinkDto("View-author","/books/"+isbn+"/authors/"+id,"GET"));
		return authorResponse;
	}
	
	/*
	 * API 10 
	 */	
	@RequestMapping(value="books/{id}/authors", method = RequestMethod.GET )
	public @ResponseBody List<Author> findAllAuthors(@PathVariable("id") long id) 
	{   
		
		return  BookFactory.getBook(id).getAuthors();
	}
}