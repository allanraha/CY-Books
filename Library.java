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
    
    public void borrow(User user, Book book) // throws exception
    {
    	if(user.getBooks().size() < user.getMaxBorrow())
    	{
    		user.borrow(book);
        	book.setBorrower(user);
    	}
    	else
    	{
    		// throw exception
    	}
    }
    
}
