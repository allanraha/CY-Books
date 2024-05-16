import java.util.*;

public class Book {
	private List<ExemplaryBook> exemplaries;
	private int quantity;
	private int isbn;
	private String title;
	private String author;
	private String genre;
	
	public Book(int isbn, String title, String author, String genre)
	{
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.genre = genre;
		
		
		////// EUH LA QUANTITE ET LES TRCS JE FAIS CMNT???????????
		
	}
	
	public int getIsbn()
	{
		return this.isbn;
	}
	public String getTitle()
	{
		return this.title;
	}
	public String getAuthor()
	{
		return this.author;
	}
	public String getGenre()
	{
		return this.genre;
	}
	public int getQuantity()
	{
		return this.quantity;
	}
	public List<ExemplaryBook> getExemplaries()
	{
		return this.exemplaries;
	}
	
	public boolean containsWord(String word)
	{
			//////// API BNF A COMPARER AUSSI !!!!!!!!!!!!!!!!
		
		// toLowerCase is used so 'A' match with 'a' (contains is case sensitive)
		word = word.toLowerCase();
		
		// we verify if the word is contained in either
		if(this.title.toLowerCase().contains(word) || this.author.toLowerCase().contains(word) || // the title or the genre
		this.genre.toLowerCase().contains(word) || // the author's name
		String.valueOf(this.isbn).toLowerCase().contains(word)) // the ISBN
		{
			return true;
		}
		
		return false;
	}
}
