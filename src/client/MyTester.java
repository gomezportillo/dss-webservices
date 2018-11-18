package client;

import org.junit.Test;
import static org.junit.Assert.*;

import javax.ws.rs.client.WebTarget;

public class MyTester 
{
	private WebTarget target;
	private static final int INITIAL_BOOKS = 2;
	
	public MyTester(WebTarget target)
	{
		this.target = target;
	}
	
	public void testServerIsUp()
	{
		// TODO Auto-generated method stub
	}

	public void testAddBook() 
	{
		// TODO Auto-generated method stub
	}

	public void testGetBook() 
	{
		// TODO Auto-generated method stub		
	}

	public void testCountBooks() 
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
}
