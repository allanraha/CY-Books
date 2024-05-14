import java.util.*;

public class Book {
	private int isbn;
	private String title;
	private String genre;
	private int maxTime;
	private Date borrowDate;
	private User borrower;
	private int borrowNumber;
	
	public Book() 
	{
		
    }
	
	public boolean containsWord(String word)
	{
		// pour chaque attribut verifie si le mot est présent ou non
		return false;
	}
	
	public void setBorrower(User user)
	{
		this.borrower = user;
	}
	
	public boolean isLate() 
	{
		// vérifie si le livre est emprunté et en retard
		return true;
	}
}
