package resource;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import model.Book;
import model.DAOBook;

public class BookResource
{
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;

	public BookResource(UriInfo uriInfo, Request request, String id) 
	{
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public Book getBookHTML() 
	{
		return this.getBookFromDAO(id);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Book getBook() 
	{
		return this.getBookFromDAO(id);
	}

	@DELETE
	public void deleteBook() 
	{
		DAOBook.INSTANCE.getModel().remove(id);
	}
	
	private Book getBookFromDAO(String id)
	{
		Book book = DAOBook.INSTANCE.getModel().get(id);
		return book;
	}
	
}