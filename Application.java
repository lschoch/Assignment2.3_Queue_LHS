/**
 * A class to demonstrate a line of customers at a burger restaurant
 * using a queue ADT. Customers are served when they reach the front
 * of the line. Wait time for each customer is tracked in real time 
 * by running two threads simultaneously, one to initialize the line
 * of customers and then serve them in order. Second thread allows user 
 * to input new arrivals to the line and to close the line at any time. 
 * Prints summary statistics and good-bye message when the line is empty.
 * 
 * @author Lawrence Schoch
 * @version 1.0
 */
class Application {

	public static void main(String[] args) { 
		final ThreadClass threads = new ThreadClass();
		
		// Thread to initialize the line and then serve customers.
		Thread t1 = new Thread(){
		    public void run(){
		    		threads.serve();
		    }
		};
		
		// Thread to allow user to add new customers to the line.
		Thread t2 = new Thread(){
		    public void run(){
		    		threads.newCustomer();
		    }
		};

		t1.start();
		t2.start();
		
	}// end main
	
}// end Application
