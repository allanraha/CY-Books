import java.util.*;

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
}
