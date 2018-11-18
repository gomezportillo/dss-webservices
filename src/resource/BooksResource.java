package resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import model.Book;
import model.DAOBook;

@Path("/books")
public class BooksResource 
{
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Book> getBooksBrowser() 
	{
		return this.getAllBooksFromDAO();
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Book> getBooks() 
	{
		return this.getAllBooksFromDAO();
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() 
	{
		int number_of_books = DAOBook.INSTANCE.getModel().size();
		return String.valueOf(number_of_books);
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newBook(
			@FormParam("id") String id, 
			@FormParam("title") String title, 
			@FormParam("author") String author, 
			@FormParam("publisher") String publisher, 
			@FormParam("edition") int edition,
			@FormParam("summary") String summary,
			@Context HttpServletResponse servletResponse) throws IOException
	{
		Book book = new Book(id, title, author, publisher, edition);
		if (summary != null) 
		{
			book.setSummary(summary);
		}
		DAOBook.INSTANCE.getModel().put(id, book);

		servletResponse.sendRedirect("../add_book.html");
	}

	// For being able to pass arguments to the server operations
	// For instance http://localhost:8080/dss-webservices/rest/book/1 
	@Path("{book}")
	public BookResource getBook(@PathParam("book") String id) 
	{
		return new BookResource(uriInfo, request, id);
	}
	
	private List<Book> getAllBooksFromDAO()
	{
		List<Book> books_list = new ArrayList<Book>();
		Collection<Book> books_col =  DAOBook.INSTANCE.getModel().values();
		books_list.addAll( books_col );
		return books_list;
	}
}
