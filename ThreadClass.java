import java.io.IOException;
import java.time.Instant;
import java.util.Scanner;
import java.util.Random;

public class ThreadClass {
	private final LinkedQueue<Customer> line = new LinkedQueue<Customer>();

    public void serve() throws InterruptedException {
    	System.out.print("\n<<<< Welcome to Vegeburger Palace! >>>>>\n\n");
    	line.enqueue(new Customer("Bill", Instant.now().getEpochSecond()));
		System.out.print(line.toString() + "\n\n");
		System.out.print("Waiting ... \n\n"); // Let's wait for another customer.\n\n");
		Thread.sleep(6000);
		System.out.print("Here comes Alice!\n");
		Thread.sleep(2000);
    	line.enqueue(new Customer("Alice", Instant.now().getEpochSecond()));
    	System.out.print(line.toString() + "\n\n");
		System.out.print("Waiting ... \n\n"); // for another customer.\n\n");
    	Thread.sleep(8000);
    	System.out.print("Now Bob is coming to get in line.\n");
    	Thread.sleep(2000);
    	line.enqueue(new Customer("Bob", Instant.now().getEpochSecond()));
    	System.out.print(line.toString() + "\n\n");
    	System.out.print("<<<< Enter a name at any time to get in line. >>>>\n\n");
    	System.out.print("<<<< Enter 'q' to close the line. >>>>\n\n");
    	String served = "";
    	int numberServed = 0;
    	long waitTime = 0;
    	long totalWaitTime = 0;
		final long MAX_WAIT_TIME = 30;
		final long MIN_WAIT_TIME = 20;
		Random random = new Random();
		double randomInterval = (MAX_WAIT_TIME-MIN_WAIT_TIME)*random.nextDouble() 
				+ MIN_WAIT_TIME;
    	    	
    	while (!line.isEmpty())
    	{
    		/* Check waitTimeime for the customer at the front of the
    		 * line. */
    		waitTime = Instant.now().getEpochSecond()
    			- line.getFront().getInLineStartTime();
    		if (waitTime >= randomInterval) {
    			served = line.dequeue().getName();
    			System.out.print("\n---------------> " + served 
					+ " was served after waiting " + waitTime 
					+ " seconds. <---------------\n\n");
    			System.out.print(line.toString() + "\n\n");
    			totalWaitTime+=waitTime;
    			numberServed++;
    			// Set randomInterval for next customer.
    			randomInterval = (MAX_WAIT_TIME-MIN_WAIT_TIME)*random.nextDouble() 
	    				+ MIN_WAIT_TIME;
    		}
    	}
    	// The line is empty.
    	if (!line.isEmpty())
    		System.out.print(line.toString() + "\n");
    	if (numberServed > 0)
    		if (numberServed == 1)
    			System.out.print("\n" + numberServed + " customer was served with an average wait time of "
        			+ totalWaitTime/(numberServed) + " seconds.\n\n");
    		else
    			System.out.print("\n" + numberServed + " customers were served with an average wait time of "
    				+ totalWaitTime/(numberServed) + " seconds.\n\n");
    	else
    		System.out.print("\nNo customers were served.\n\n");
    	System.out.println("<<<<< Come back to the Vegeburger Palace real soon! >>>>>\n");
    	try {
    		System.in.close();
    	} catch (IOException e) {
    		System.out.println(e.getMessage());
    	}
    }// end Serve

    public void newCustomer() throws IOException {
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
    }
    
}// end MyClass
