import java.util.Scanner;
import java.time.Instant;
/**
 * Class to demonstrate a queue of customers at a burger
 * restaurant using a LinkedQueue. Some customer arrivals 
 * are scheduled and others are input by the user. Pseudo-
 * random wait times after reaching the counter at the front
 * of the line with upper and lower limits.
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
		System.out.print("Waiting for another customer.\n\n");
		Thread.sleep(10000);
		System.out.print("Here comes Alice!\n");
		Thread.sleep(2000);
    	line.enqueue(new Customer("Alice", Instant.now().toEpochMilli()));
    	System.out.print(line.toString() + "\n\n");
		System.out.print("Waiting for another customer.\n\n");
    	Thread.sleep(10000);
    	System.out.print("Now Bob is coming to get in line.\n");
    	Thread.sleep(2000);
    	line.enqueue(new Customer("Bob", Instant.now().toEpochMilli()));
    	System.out.print(line.toString() + "\n\n");
    	
    	String served = "";
    	Scanner scan = new Scanner(System.in);
		String newInLine = "";
		long timeAtCounter = 0;
		final long MAX_TIME_AT_COUNTER = 30000;
		final long MIN_TIME_AT_COUNTER = 15000;
    	
    	while (!line.isEmpty())
    	{
    		/* while loop to process those customers whose time at counter
    		 * is greater than the random interval or greater than 
    		 * MAX_TIME_AT_COUNTER 
    		 */
    		while (!line.isEmpty()) {
	    		// Check current time at the counter for the first in line
	    		timeAtCounter = Instant.now().toEpochMilli() 
	    			- line.getFront().getInLineStartTime();
	    		if (timeAtCounter >= 
	    			(MAX_TIME_AT_COUNTER-MIN_TIME_AT_COUNTER)*((long)Math.random())
	    			+ MIN_TIME_AT_COUNTER) {
	    			served = line.dequeue().getName();
	    			System.out.print("\n ---------------> " + served 
							+ " was served after " + timeAtCounter/1000 //Math.min(MAX_TIME_AT_COUNTER/1000, timeAtCounter/1000) 
							+ " seconds at the counter. <---------------\n\n");
	    			System.out.print(line.toString() + "\n\n");
	    		} else 
	    			break;
    		}
    		
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
    	// Process customers who have met threshold for being served.
    	while (!line.isEmpty()) {
    		// Check time at counter for first in line
    		timeAtCounter = Instant.now().toEpochMilli() 
    			- line.getFront().getInLineStartTime();
    		if (timeAtCounter >= 
    				(MAX_TIME_AT_COUNTER-MIN_TIME_AT_COUNTER)*((long)Math.random())
	    			+ MIN_TIME_AT_COUNTER) {
    			served = line.dequeue().getName();
    			System.out.print("\n ---------------> " + served 
    					+ " was served after " + timeAtCounter/1000 //Math.min(MAX_TIME_AT_COUNTER/1000, timeAtCounter/1000)
						+ " seconds at the counter. <---------------\n\n");
    		} else 
    			break;
		}
    	System.out.print(line.toString() + "\n\n");
    	System.out.println("\nCome back to the Vegeburger Palace real soon!\n");
    	
	}// end main
	
}// end Application
