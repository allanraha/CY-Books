import java.util.*;

public class Library {

	private Map<Integer, User> users;
    private Map<Integer, Book> books;
    
    public Library()
    {
    	// bdds et api
    }
    
    
    public void registration(User user)
    {
    	users.put(user.getId(), user);
    }
    
    public void editUserInfo()
    {
    	
    }
    
    public User searchUser()
    {
    	User result = null;
        
        return result;
    }
    
    public List<Book> searchBooks(String keyWord, String ...keyWords)
    {
    	List<Book> results = new ArrayList<>();
        
        return results;
    }
    
    public void borrow(User user, Book book)
    {
    	// setBorrow et user.borrow
    }
    
}
