import java.util.*;
	
public class User {
	private int id;
	private String name;
	private int maxBorrow;
    private List<Book> books;
    private History history;
    
    /*private historyBorrow; // history(livre, date et retard) emprunt livre date et retard (date ou boolean)
    private historyDate;
    private historyLate;
    
    liste history (history[i].getBook())
    ou 
    liste attribut dans history (history.getBook(i))
    */
    
    
    public User(int id, String name, int maxBorrow, History history, List<Book> books) 
    {        
        this.id = id;
        this.name = name;
        this.maxBorrow = maxBorrow;
        this.history = history; //add to history
        
        this.books = new ArrayList<Book>();
        for(Book book : books)
        {
        	if(this.books.size() < this.maxBorrow)
        		this.books.add(book);
        }
    }
    
    public int getId()
    {
    	return this.id;
    }
    
    public String getName()
    {
    	return this.name;
    }
    
    public List<Book> getBooks()
	{
		return this.books;
	}
    
    public int getMaxBorrow()
	{
		return this.maxBorrow;
	}
    
    public void borrow(Book book)
    {
    	this.books.add(book);
    	this.history.add(book, new Date(), 0); // 0 because the book can't be late if it has just been borrowed
    }
}
