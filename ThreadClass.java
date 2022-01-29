import java.io.IOException;
import java.util.NoSuchElementException;
import java.time.Instant;
import java.util.Scanner;
import java.util.Random;
/**
 * A class to create two methods to run simultaneously, each in a 
 * separate thread.
 *  
 * @author Lawrence Schoch
 * @version 1.0
 */
public class ThreadClass {
	private final LinkedQueue<Customer> line = new LinkedQueue<Customer>();
	
	/** A method to enqueue a specified number of customers at staggered 
	 * intervals and then dequeue based after a random wait time which ranges
	 * between half the maximum wait time and the maximum wait time. Prints 
	 * summary statistics and good-bye message when the line is empty.
	 */
    public void serve() {
    	try {
	    	System.out.print("\n<<<< Welcome to Vegeburger Palace! >>>>>\n\n");
	    	line.enqueue(new Customer("Bill", Instant.now().getEpochSecond()));
			System.out.print(line.toString() + "\n\n");
			System.out.print("Waiting ... \n\n"); // Let's wait for another customer.\n\n");
			Thread.sleep(4000);
			System.out.print("Here comes Alice!\n");
			Thread.sleep(1000);
	    	line.enqueue(new Customer("Alice", Instant.now().getEpochSecond()));
	    	System.out.print(line.toString() + "\n\n");
			System.out.print("Waiting ... \n\n"); // for another customer.\n\n");
	    	Thread.sleep(4000);
	    	System.out.print("Now Bob is coming to get in line.\n");
	    	Thread.sleep(1000);
	    	line.enqueue(new Customer("Bob", Instant.now().getEpochSecond()));
	    	System.out.print(line.toString() + "\n\n");
	    	Thread.sleep(2000);
	    	System.out.print("<<<< Enter a name at any time to get in line. >>>>\n\n");
	    	System.out.print("<<<< Enter 'q' at any time to close the line. >>>>\n\n");
	    	System.out.print("<<<< Customers will be served when they reach the front"
	    			+ " of the line. >>>>\n\n");
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	} // end catch
    	
    	String served = "";
    	int numberServed = 0;
    	long waitTime = 0;
    	long totalWaitTime = 0;
		final long MAX_WAIT_TIME = 30;
		Random random = new Random();
		double randomInterval = (MAX_WAIT_TIME/2)*random.nextDouble() 
				+ MAX_WAIT_TIME/2;
    	    	
    	while (!line.isEmpty())
    	{
    		/* Check waitTimeime for the customer at the front of the
    		 * line. */
    		waitTime = Instant.now().getEpochSecond()
    			- line.getFront().getInLineStartTime();
    		if (waitTime >= randomInterval) {
    			served = line.dequeue().getName();
    			System.out.print("\n---------------> " + served + " was served after "
    				+ "waiting " + waitTime + " seconds. <---------------\n\n");
    			System.out.print(line.toString() + "\n\n");
    			totalWaitTime+=waitTime;
    			numberServed++;
    			// Set randomInterval for next customer.
    			randomInterval = (MAX_WAIT_TIME/2)*random.nextDouble() 
    					+ MAX_WAIT_TIME/2;
    		}
    	}
    	// The line is empty.
    	if (!line.isEmpty())
    		System.out.print(line.toString() + "\n");
    	if (numberServed > 0)
    		if (numberServed == 1)
    			System.out.print("\n" + numberServed + " customer was served with "
    				+ "an average wait time of " + totalWaitTime/(numberServed) 
    				+ " seconds.\n\n");
    		else
    			System.out.print("\n" + numberServed + " customers were served with "
    				+ "an average wait time of " + totalWaitTime/(numberServed) 
    				+ " seconds.\n\n");
    	else
    		System.out.print("\nNo customers were served.\n\n");
    	System.out.println("<<<<< Good-bye. Come back to the Vegeburger Palace "
    		+ "real soon! >>>>>\n");
    	try {
    		System.in.close();
    	} catch (IOException e) {
    		System.out.println(e.getMessage());
    	}// end catch
    	
    }// end serve
    
    /** A method that allows user to input new customers while existing 
     * customers are being served from the line. User can close the line 
     * at any time or leave it open until it's empty. */
    public void newCustomer() {
    	try {
	    	Scanner scan = new Scanner(System.in);
			String newInLine = "";
			while (!newInLine.toLowerCase().equals("q")) {
				// Start user input 
				newInLine = scan.nextLine();
				if (newInLine.toLowerCase().equals("q")) {
					System.out.print("The line is closed.\n");
					if (!line.isEmpty())
							System.out.print("All those remaining in line will be served.\n");
					break;
				}
				else if (!newInLine.isBlank()) {
					line.enqueue(new Customer(newInLine, Instant.now().getEpochSecond()));
					System.out.print(line.toString() + "\n");
				}
			}
			scan.close();
    	} catch (NoSuchElementException e) {
    		/* No need to do anything here. This exception will be thrown
    		 * when the serve method closes System.in while this method is
    		 * waiting for user input.*/
    	}// end catch
    	
    }// end newCustomer
    
}// end ThreadClass
