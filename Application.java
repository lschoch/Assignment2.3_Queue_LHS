import java.util.Scanner;
import java.time.Instant;
/**
 * Class to demonstrate a queue of customers at a burger
 * restaurant using a LinkedQueue. Some customer arrivals 
 * are scheduled and others are input by the user. Pseudo-
 * random counter wait times after reaching the the front
 * of the line with upper and lower limits. Number of customers
 * served and average counter wait time are reported on exit.
 *  
 * @author Lawrence Schoch
 * @version 1.0 */
public class Application 
{
	public static void main(String[] args) throws InterruptedException
	{
		System.out.print("\nWelcome to Vegeburger Palace!\n\n");
		final QueueInterface<Customer> line = new LinkedQueue<Customer>();
		
		// Staggered arrival of new customers.
		line.enqueue(new Customer("Bill", Instant.now().toEpochMilli()));
		System.out.print(line.toString() + "\n\n");
		System.out.print("Let's wait for another customer.\n\n");
		Thread.sleep(8000);
		System.out.print("Here comes Alice!\n");
		Thread.sleep(2000);
    	line.enqueue(new Customer("Alice", Instant.now().toEpochMilli()));
    	System.out.print(line.toString() + "\n\n");
		System.out.print("Waiting for another customer.\n\n");
    	Thread.sleep(8000);
    	System.out.print("Now Bob is coming to get in line.\n");
    	Thread.sleep(2000);
    	line.enqueue(new Customer("Bob", Instant.now().toEpochMilli()));
    	System.out.print(line.toString() + "\n\n");
    	
    	String served = "";
    	int numberServed = 0;
    	long timeAtCounter = 0;
    	long totalTimeAtCounter = 0;
    	long counterDepartureTime = Instant.now().toEpochMilli();;
    	Scanner scan = new Scanner(System.in);
		String newInLine = "";
		final long MAX_TIME_AT_COUNTER = 30000;
		final long MIN_TIME_AT_COUNTER = 15000;
    	
    	while (!line.isEmpty())
    	{
    		/* while loop to serve those customers whose time at counter
    		 * is greater than either the random interval or the 
    		 * MAX_TIME_AT_COUNTER. */
    		while (!line.isEmpty()) {
	    		// Check time at the counter for the first in line
	    		timeAtCounter = Instant.now().toEpochMilli()
	    			- counterDepartureTime;
	    		if (timeAtCounter >= 
	    			(long)((MAX_TIME_AT_COUNTER-MIN_TIME_AT_COUNTER)*Math.random())
	    				+ MIN_TIME_AT_COUNTER) {
	    			timeAtCounter = Math.min(MAX_TIME_AT_COUNTER, timeAtCounter);
	    			counterDepartureTime+= timeAtCounter;
	    			served = line.dequeue().getName();
	    			System.out.print("\n ---------------> " + served 
						+ " was served after " + timeAtCounter/1000 
						+ " seconds at the counter. <---------------\n\n");
	    			System.out.print(line.toString() + "\n\n");
	    			totalTimeAtCounter+=timeAtCounter;
	    			numberServed++;
	    		} else 
	    			break;
    		}// end while
    		
    		// Start user input 
    		System.out.println("Enter your name to get in line or 'q' to quit>>\n");
    		newInLine = scan.nextLine();
    		if (newInLine.toLowerCase().equals("q"))
    			break;
    		else if (!newInLine.isBlank()) {
    			line.enqueue(new Customer(newInLine, Instant.now().toEpochMilli()));
    			System.out.print(line.toString() + "\n");
    		}
    	}// end while
    	scan.close();
    	
    	/* while loop to serve those customers whose time at counter
		 * is greater than either the random interval or the 
		 * MAX_TIME_AT_COUNTER. */
		while (!line.isEmpty()) {
    		// Check time at the counter for the first in line
    		timeAtCounter = Instant.now().toEpochMilli()
    			- counterDepartureTime;
    		if (timeAtCounter >= 
    			(long)((MAX_TIME_AT_COUNTER-MIN_TIME_AT_COUNTER)*Math.random())
    				+ MIN_TIME_AT_COUNTER) {
    			timeAtCounter = Math.min(MAX_TIME_AT_COUNTER, timeAtCounter);
    			counterDepartureTime+= timeAtCounter;
    			served = line.dequeue().getName();
    			System.out.print("\n ---------------> " + served 
					+ " was served after " + timeAtCounter/1000 
					+ " seconds at the counter. <---------------\n\n");
    			System.out.print(line.toString() + "\n\n");
    			totalTimeAtCounter+=timeAtCounter;
    			numberServed++;
    		} else 
    			break;
		}// end while
    	
    	System.out.print("Quitting: \n");
    	System.out.print(line.toString() + "\n");
    	if (numberServed > 0)
    		if (numberServed == 1)
    			System.out.print(numberServed + " customer was served with an average counter wait time of "
        			+ totalTimeAtCounter/(numberServed*1000) + " seconds.\n\n");
    		else
    			System.out.print(numberServed + " customers were served with an average counter wait time of "
    				+ totalTimeAtCounter/(numberServed*1000) + " seconds.\n\n");
    	else
    		System.out.print("No customers were served.\n\n");
    	System.out.println("Come back to the Vegeburger Palace real soon!\n");
    	
	}// end main
	
}// end class
