import java.util.Date;
import java.util.List;

public class ExemplaryBook 
{

	private int maxTime;
	private List<Date> borrowDates;
	private int borrowNumber;
	private User borrower;
	
	public ExemplaryBook(int isbn, String title, String author, String genre, int maxTime, List<Date> borrowDates, User borrower) 
	{
		this.maxTime = maxTime;
		this.borrowDates = borrowDates;
		
		if(borrowDates != null)
			this.borrowNumber = borrowDates.size();
		else
			this.borrowNumber = 0;
		
		this.borrower = borrower;
    }
	
	public int getMaxTime()
	{
		return this.maxTime;
	}
	public List<Date> getBorrowDates()
	{
		return this.borrowDates;
	}
	public int getBorrowNumber()
	{
		return this.borrowNumber;
	}
	public User getBorrower()
	{
		return this.borrower;
	}
	
	public void setBorrower(User user)
	{
		this.borrower = user;
	}
	
	public int howLate() 
	{
		// on part du principe que maxTime est en jour
		int last = this.borrowDates.size() - 1;
		int days = (int) (this.borrowDates.get(last).getTime() / 86400000); 
		int maxDays = days + this.maxTime;
		Date d1 = new Date();
		int currentDays = (int) (d1.getTime() / 86400000);
		
		if(maxDays < currentDays)
		{
			return currentDays - maxDays;
		}
		
		return 0;
	}
}
