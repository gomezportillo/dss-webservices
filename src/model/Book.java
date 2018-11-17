package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Book 
{
	private String id;
	private String title;
	private String author;
	private String publisher;
	private int edition;

	public Book(String id, String title, String author, String publisher, int edition) 
	{
		this.id = id;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.edition = edition;
	}

	public Book()
	{
	}

	public String getId() 
	{
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getAuthor() 
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getPublisher()
	{
		return publisher;
	}

	public void setPublisher(String publisher) 
	{
		this.publisher = publisher;
	}

	public int getEdition() 
	{
		return edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}
}
