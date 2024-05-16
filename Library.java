import java.util.*;

public class Library {

	private Map<Integer, User> users;
    private Map<Integer, Book> books;
    
    public Library()
    {
    	// bdds et api
    	// faire les books, users et historique associées
    }
    
    
    public void registration(User user)
    {
    	this.users.put(user.getId(), user);
    }
    
    public void editUserInfo(int id, String name, int maxBorrow, History history, Book ...books)
    {
    	// users.get(id) = new User(les attributs);
    }
    
    public User searchUser(int id)
    {
        return this.users.get(id);
    }
    
    public List<Book> searchBooks(String keyWord, String ...keyWords)
    {
    	List<Book> results = new ArrayList<Book>();
    	
    	for (Book book : this.books.values()) // for each book
    	{
    		if(book.containsWord(keyWord)) // if the book contains the keyWord parameter
    		{
    			results.add(book);	// add it to the list of results
    		}
    		else // verify if the book contains one of the other keywords so
    		{
    			for(String word : keyWords) // for each other keyword
    			{
    				if(book.containsWord(word)) // if the book contains it
    	    			results.add(book); // add it to the list 
    			}
	    	}
    	}
        return results; // return the list
    }
    
    public List<Book> mostBorrowed()
    {
    	List<Book> results = new ArrayList<Book>();
    	/*			faut prendre en comptes la quantité de chaque livres
    	int i = 0;
    	Book book;
    			
    	
    	do{
    		book = null;
    		
    		for(int j = 0; j < this.books.size(); j++) // for each book of the library
    		{
    			if(!results.contains(this.books.get(i))) // if it is not already in the results list
    			{
    				if(book == null || this.books.get(i).getBorrowNumber() > book.getBorrowNumber())
    				{
    					book = this.books.get(i)
    				}
    			}
    		}
    		
    		if(book != null)
    		{
    			results.add(book);
    		}
    		
    	}while(i < 30 && book != null);*/
    	
    	return results;
    }
    
    public void borrow(User user, Book book) // throws exception
    {
    	List<ExemplaryBook> exemplaries = book.getExemplaries();
    	ExemplaryBook exemplary = null;
    	int i = 0;
    	int size = exemplaries.size();
    	
    	while(exemplary == null && i < size) // we search an exemplary of the book which is not borrowed
    	{
    		if(exemplaries.get(i).getBorrower() == null)
    			exemplary = exemplaries.get(i);
    		i++;
    	}
    	
    	if(exemplary != null && user.getBooks().size() < user.getMaxBorrow())
    	{
    		user.borrow(book);
        	exemplary.setBorrower(user);
    	}
    	else
    	{
    		// throw exception
    	}
    }
    
}
