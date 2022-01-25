/**
 * A class of customers specified by name and the time
 * in milliseconds from the epoch when they were
 * enqueued.
 * 
 * @author Lawrence Schoch
 * @version 1.0
 */
public class Customer {
	private String name;
	/* The time in milliseconds from the epoch when
	 * the customer was enqueued. */
	private long inLineStartTime; 
	
	public Customer(String name, long inLineStartTime) {
		super();
		this.name = name;
		this.inLineStartTime = inLineStartTime;
	}// end constructor

	/** Gets the customer's name.
	 * @return the name */
	public String getName() {
		return name;
	}// end getName

	/** Sets the customer's name.
	 * @param name the name to set*/
	public void setName(String name) {
		this.name = name;
	}// end setName

	/** Time in milliseconds from the epoch when the
	 * customer enqueued.
	 * @return the inLineStartTime */
	public long getInLineStartTime() {
		return inLineStartTime;
	}// end getInLineStartTime

	/** Sets the inLineStartTime in milliseconds from
	 * the epoch.
	 * @param inLineStartTime the inLineStartTime to set */
	public void setInLineStartTime(long inLineStartTime) {
		this.inLineStartTime = inLineStartTime;
	}// end setInLineStartTime
	
	/** Method to retrieve the customer's name as a String
	 * @return the customer's name */
	@Override
	public String toString() {
		return name;
	}// end toString
	
}// end class
