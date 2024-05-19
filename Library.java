import java.util.*;

public class Library {

	private Map<Integer, User> users;
    private Map<Integer, Book> books;
    
    public Library()
    {
    	///// bdd a faire
    	// faire les books, users et historique associées
    }
    
    
    public void registration(User user)
    {
    	///// bdd a faire
    	this.users.put(user.getId(), user);
    }
    
    public void editUserInfo(int id, String name, int maxBorrow, History history, List<Book> books)
    {
    	///// bdd a faire
    	this.users.put(id, new User(id, name, maxBorrow, history, books));
    }
    
    public User searchUser(int id)
    {
        return this.users.get(id);
    }
    
    //
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
    	
    	int i = 0;
    	Book book;
    			
    	
    	do{
    		book = null;
    		
    		for(int j = 0; j < this.books.size(); j++) // for each book of the library
    		{
    			if(!results.contains(this.books.get(j))) // if it is not already in the results list
    			{
    				if(book == null || this.books.get(j).getAllBorrow30Days() > book.getAllBorrow30Days())
    				{
    					book = this.books.get(j);
    				}
    			}
    		}
    		
    		if(book != null)
    		{
    			results.add(book);
    		}
    		i++;
    		
    	}while(i < 30 && book != null);
    	
    	return results;
    }
    
    public void borrow(User user, Book book) // throws exception
    {
    	///// bdd a faire
    	
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
