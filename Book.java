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
		// pour chaque attribut verifie si le mot est pr�sent ou non
		return false;
	}
	
	public void setBorrower(User user)
	{
		this.borrower = user;
	}
	
	public boolean isLate() 
	{
		// v�rifie si le livre est emprunt� et en retard
		return true;
	}
}
