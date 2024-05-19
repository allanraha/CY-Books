import java.util.*;
import java.io.*;
import java.net.*;

public class Book {
	private List<ExemplaryBook> exemplaries;
	private int quantity;
	private int isbn;
	private String title;
	private String author;
	private String genre;
	
	public Book(int isbn, String title, String author, String genre, List<ExemplaryBook> exemplaries)
	{
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.genre = genre;
		
		this.exemplaries = exemplaries;
		this.quantity = exemplaries.size();
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
	
	public int getAllBorrow30Days()
	{
		int sum = 0;
		ExemplaryBook exemplary;
		Date date = new Date();
		long milli = date.getTime();
		
		for(int i = 0; i < this.quantity; i++)
		{
			exemplary = this.exemplaries.get(i);
			for(int j = 0; j < exemplary.getBorrowDates().size(); j++)
			{
				if((milli - exemplary.getBorrowDates().get(j).getTime()) / 86400000 < 30)
					sum++;
			}
			
		}
		return sum;
	}
	
	public boolean containsWord(String word)
	{
		//////// API 
		/*
		String apiUrl = "http://catalogue.bnf.fr/api/SRU";
        String queryString = String.format("bib.anywhere %s \"%s\"",
                relation,
                URLEncoder.encode(query, "UTF-8"));
        String urlString = apiUrl + "?version=1.2&operation=searchRetrieve&query=" + URLEncoder.encode(queryString, "UTF-8");

        // Faire la requête HTTP
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        
		 */
		
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
