class Application {

	public static void main(String[] args) throws InterruptedException {
		final ThreadClass threads = new ThreadClass();
		Thread t1 = new Thread(){
		    public void run(){
		    	try {
		    		threads.serve();
		    	} catch (InterruptedException e) {
		    		System.out.println(e.getMessage());
		    	}
		    }
		};

		Thread t2 = new Thread(){
		    public void run(){
		    	try {
		    		threads.newCustomer();
		    	} catch (Exception e) {
//		    		e.printStackTrace();
		    	}
		    }
		};

		t1.start();
//		Thread.sleep(22000);
		t2.start();
		
	}// end main
	
}// end class
