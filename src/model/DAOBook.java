package model;

import java.util.HashMap;
import java.util.Map;

import model.Book;

public enum DAOBook 
{
	INSTANCE;

	private Map<String, Book> contentProvider = new HashMap<>();

	private DAOBook()
	{
		Book aux;
		// Creating two initial books
		
		aux = new Book("1", "El capitán Alatriste", "Pérez-Reverte", "Alfaguara", 9);
		this.contentProvider.put(aux.getId(), aux);
		
		aux = new Book("2", "Elantris", "Brandon Sanderson", "Nova", 3);
		this.contentProvider.put(aux.getId(), aux);
	}
	
	public Map<String, Book> getModel()
	{
		return contentProvider;
	}
}