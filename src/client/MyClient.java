package client;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class MyClient 
{
	private static final String MY_URI = "http://localhost:8080/dss-webservices";
	
	public static void main(String[] args) 
	{
		WebTarget target = configureClient();

		MyTester tester = new MyTester(target);
		
		tester.testServerIsUp();

		tester.testCountBooks();
		
		tester.testGetBook();

		tester.testAddBook();
		
		tester.testDeleteBook();
		
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