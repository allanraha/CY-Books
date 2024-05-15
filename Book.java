import java.util.*;

public class Book {
	private int isbn;
	private String title;
	private String author;
	private String genre;
	private int maxTime;
	private List<Date> borrowDates;
	private int borrowNumber;
	private User borrower;
	
	public Book(int isbn, String title, String author, String genre, int maxTime, List<Date> borrowDates, User borrower) 
	{
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.maxTime = maxTime;
		this.borrowDates = borrowDates;
		
		if(borrowDates != null)
			this.borrowNumber = borrowDates.size();
		else
			this.borrowNumber = 0;
		
		this.borrower = borrower;
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
	public int getMaxTime()
	{
		return this.maxTime;
	}
	public List<Date> getBorrowDates()
	{
		return this.borrowDates;
	}
	public int getBorrowNumber()
	{
		return this.borrowNumber;
	}
	
	public boolean containsWord(String word)
	{
		// toLowerCase is used so 'A' match with 'a' (contains is case sensitive)
		word = word.toLowerCase();
		
		// we verify if the word is in either the title, the genre, the author's name or the borrower's name (if there is a borrower) 
		if(this.title.toLowerCase().contains(word) || this.author.toLowerCase().contains(word) ||
		this.genre.toLowerCase().contains(word) || 
		(this.borrower != null && this.borrower.getName().toLowerCase().contains(word)))
		{
			return true;
		}
		
		return false;
	}
	
	public void setBorrower(User user)
	{
		this.borrower = user;
	}
	
	public boolean isLate() 
	{
		// maxTime unité de temps ?
		
		return true;
	}
}
