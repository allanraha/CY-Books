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
	
	public boolean isLate() {
		// Check if the borrow dates list is null or empty
		if (this.borrowDates == null || this.borrowDates.isEmpty()) {
			return false;  // If so, the book is not late
		}

		// Retrieve the most recent borrow date
		Date lastBorrowDate = this.borrowDates.get(this.borrowDates.size() - 1);

		// Calculate the deadline by adding maxTime days to the borrow date
		long deadlineInMillis = lastBorrowDate.getTime() + (long) this.maxTime * 24 * 60 * 60 * 1000;
		Date deadline = new Date(deadlineInMillis);

		// Compare the deadline with the current date
		return new Date().after(deadline);
	}

}
