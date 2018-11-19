package client;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import test.Tester;

public class MyClient 
{
	private static final String MY_URI = "http://localhost:8080/dss-webservices";
	
	public static void main(String[] args) 
	{
		WebTarget target = configureClient();

		Tester tester = new Tester(target);
		
		System.out.println("\n======TESTING SERVER IS UP======");
		tester.testServerIsUp();
		
		System.out.println("\n======TESTING COUNTING BOOKS======");
		tester.testCountBooks();
		
		System.out.println("\n======TESTING GETTING ALL BOKS======");
		tester.testGetAllBooks();
		
		System.out.println("\n======TESTING GETTING SINGLE BOOK======");
		tester.testGetSingleBook();

		System.out.println("\n======TESTING POSTING NEW BOOK======");
		tester.testPostBook();
		
		System.out.println("\n======TESTING DELETING POSTED BOOK======");
		tester.testDeleteBook();
		
		System.out.println("\n======TESTING PUTTING BOOK======");
		tester.testPutBook();
		
		System.out.println("\n======TESTING UPDATE BOOK======");
		tester.testUpdateBook();
	}
	
	private static WebTarget configureClient()
	{
		ClientConfig config = new ClientConfig();

		Client client = ClientBuilder.newClient(config);

		URI uri = UriBuilder.fromUri(MY_URI).build();

		return client.target(uri);
	}

}