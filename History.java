import java.util.*;

public class History {
	private int length;
	private List<Book> books;
	private List<Date> dates;
	private List<Integer> borrowDurations;
	private List<Integer> howLates;
	
	public History()
	{
		this.length = 0;
		this.books = new ArrayList<Book>();
		this.dates = new ArrayList<Date>();
		this.borrowDurations = new ArrayList<Integer>();
		this.howLates = new ArrayList<Integer>();
	}
	
	public void add(Book book, Date date, int duration, int late)
	{
		this.length++;
		this.books.add(book);
		this.dates.add(date);
		this.borrowDurations.add(duration);
		this.howLates.add(late);
	}
	
	public List<Book> getBooks()
	{
		return this.books;
	}
	public List<Date> getDates()
	{
		return this.dates;
	}
	public List<Integer> getHowLates()
	{
		return this.howLates;
	}
	public List<Integer> getBorrowDurations()
	{
		return this.borrowDurations;
	}
	public int getLength()
	{
		return this.length;
	}
}
