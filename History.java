import java.util.*;

public class History {
	private int length;
	private List<Book> books;
	private List<Date> dates;
	private List<Integer> lates;
	
	public History()
	{
		this.length = 0;
		this.books = new ArrayList<Book>();
		this.dates = new ArrayList<Date>();
		this.lates = new ArrayList<Integer>();
	}
	
	public void add(Book book, Date date, int late)
	{
		this.length++;
		this.books.add(book);
		this.dates.add(date);
		this.lates.add(late);
	}
	
	public List<Book> getBooks()
	{
		return this.books;
	}
	
	public List<Date> getDates()
	{
		return this.dates;
	}
	
	public List<Integer> getLates()
	{
		return this.lates;
	}
	
	public int getLength()
	{
		return this.length;
	}
}
