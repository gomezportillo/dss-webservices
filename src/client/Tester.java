package client;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
	}

	public void testCountBooks() 
	{
		final String initial_number_of_books = "2";
		String response = target.path("rest").path("books").path("count").request().accept(MediaType.TEXT_PLAIN).get(String.class);

		assertEquals(response, initial_number_of_books);
	}
	
	public void testAddBook() 
	{
		// TODO Auto-generated method stub
	}

	public void testGetBook() 
	{
		// TODO Auto-generated method stub		
	}

	public void testDeleteBook() 
	{
		// TODO Auto-generated method stub	
	}

	public void testUpdateBook() 
	{
		// TODO Auto-generated method stub	
	}
	
	public String getXMLBooks()
	{
		return target.path("rest").path("books").request().accept(MediaType.TEXT_XML).get(String.class);
	}
}
