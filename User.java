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
    
    
    public User() {
        this.books = new ArrayList<>();
    }
    
    public int getId()
    {
    	return this.id;
    }
    
    public void borrow(Book book)
    {
    	this.books.add(book);
    	// ajouter à historique
    }
}
