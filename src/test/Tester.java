package test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Book;

public class Tester 
{
	private WebTarget target;

	public Tester(WebTarget target)
	{
		this.target = target;
	}

	@BeforeClass
	public static void setUp() {}

	@AfterClass 
	public static void tearDown() {}

	@Test
	public void testServerIsUp()
	{
		final int expected_code = 200;

		Response response = target.path("rest").path("books").request().accept(MediaType.TEXT_XML).get(Response.class);

		assertEquals(response.getStatus(), expected_code);
		System.out.println("HTTP code after accessing server (expected "+expected_code+"): " + response.getStatus());
	}

	@Test
	public void testCountBooks() 
	{
		final String expected_books = "2";

		String response = target.path("rest").path("books").path("count").request().accept(MediaType.TEXT_PLAIN).get(String.class);

		assertEquals(response, expected_books);
		System.out.println("Number of books in server (expected "+expected_books+"): " + response);
	}

	@Test
	public void testGetAllBooks()
	{
		String response = target.path("rest").path("books").request().accept(MediaType.TEXT_XML).get(String.class);

		assertFalse( response.equals(null) );
		System.out.println("Getting all books in server:\n" + response);
	}

	@Test
	public void testGetSingleBook() 
	{
		final String index = "1";
		String response = target.path("rest").path("books").path(index).request(MediaType.APPLICATION_XML).get(String.class);

		assertTrue( response.contains("<id>"+index+"</id>"));
		System.out.println("Response after getting book with index=" + index + ":\n" + response);
	}

	@Test
	public void testPostBook() 
	{
		final int expected_code = 200;

		Book book = new Book("3", "Nuncanoche", "Jay Kristoff", "Plaza & Janes", 4);

		Form form = new Form();
		form.param("id", book.getId());
		form.param("title", book.getTitle());
		form.param("author", book.getAuthor());
		form.param("publisher", book.getPublisher());
		form.param("edition", String.valueOf(book.getEdition()));
		form.param("summary", "La asesina Mia Corvere acaba de unirse a la banda más mortífera de la República.");
		Entity<Form> entity = Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED);

		Response response = target.path("rest").path("books").request().post(entity, Response.class);
		assertEquals(response.getStatus(), expected_code);
		System.out.println("HTTP code after posting book (expected "+expected_code+"): " + response.getStatus());

		String all_books = target.path("rest").path("books").request().accept(MediaType.TEXT_XML).get(String.class);
		assertTrue( all_books.contains("<title>" + book.getTitle() + "</title>") );
		System.out.println("List of books DOES contain new posted book");
	}

	@Test
	public void testDeleteBook() 
	{
		final int expected_code = 204;

		Book book = new Book("3", "Nuncanoche", "Jay Kristoff", "Plaza & Janes", 4);
		Response response = target.path("rest").path("books").path(book.getId()).request().delete(Response.class);

		assertEquals(response.getStatus(), expected_code);
		System.out.println("HTTP response code after deleting a book (expected "+expected_code+"): " + response.getStatus());

		String all_books = target.path("rest").path("books").request().accept(MediaType.TEXT_XML).get(String.class);

		assertFalse( all_books.contains("<title>"+book.getTitle()+"</title>") );
		System.out.println("List of books DOES NOT contain previous posted book");
	}

	@Test
	public void testPutBook() 
	{
		final int expected_code = 204;

		Book book = new Book("4", "El nombre del viento", "Patrick Rothfuss", "Debolsillo", 8);
		Entity<Book> entity = Entity.entity(book, MediaType.APPLICATION_XML);
		Response response = target.path("rest").path("books").path(book.getId()).request(MediaType.APPLICATION_XML).put(entity, Response.class);

		assertEquals(response.getStatus(), expected_code);
		System.out.println("HTTP response code after adding a book (expected "+expected_code+"): " + response.getStatus());

		String all_books = target.path("rest").path("books").request().accept(MediaType.TEXT_XML).get(String.class);

		assertTrue( all_books.contains("<title>"+book.getTitle()+"</title>") );
		System.out.println("List of books DOES contain previous put book");
		
		target.path("rest").path("books").path(book.getId()).request().delete();
	}

	@Test
	public void testUpdateBook() 
	{
		// Create the book
		Book book = new Book("4", "Juego de tronos", "J.R.R. Tolkien", "Gigamesh", 4);
		Entity<Book> entity = Entity.entity(book, MediaType.APPLICATION_XML);
		target.path("rest").path("books").path(book.getId()).request(MediaType.APPLICATION_XML).put(entity);
		
		// Update the book
		book.setAuthor("George R.R. Martin");
		entity = Entity.entity(book, MediaType.APPLICATION_XML);
		target.path("rest").path("books").path(book.getId()).request(MediaType.APPLICATION_XML).put(entity);
		
		// Checking it is updated
		String all_books = target.path("rest").path("books").request().accept(MediaType.TEXT_XML).get(String.class);
		System.out.println(all_books);
		assertTrue( all_books.contains("<author>"+book.getAuthor()+"</author>") );
		System.out.println("List of books DOES contain updated book");
		
		target.path("rest").path("books").path(book.getId()).request().delete();
	}
}
