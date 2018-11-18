package client;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class MyClient 
{
	private static final String MY_URI = "http://localhost:8080/dss-webservices";
	
	public static void main(String[] args) 
	{
		WebTarget target = configureClient();

		String response = target.path("rest").path("book").request().accept(MediaType.TEXT_PLAIN).get(Response.class).toString();
		String plainAnswer = target.path("rest").path("book").request().accept(MediaType.TEXT_PLAIN).get(String.class);
		String xmlAnswer = target.path("rest").path("book").request().accept(MediaType.TEXT_XML).get(String.class);
		String htmlAnswer = target.path("rest").path("book").request().accept(MediaType.TEXT_HTML).get(String.class);

		System.out.println("Text plain: " + response);
		System.out.println("Text plain: " + plainAnswer);
		System.out.println("Text XML: "  + xmlAnswer);
		System.out.println("Text HTML: " + htmlAnswer);
	}
	
	private static WebTarget configureClient()
	{
		ClientConfig config = new ClientConfig();

		Client client = ClientBuilder.newClient(config);

		URI uri = UriBuilder.fromUri(MY_URI).build();

		return client.target(uri);
	}

}